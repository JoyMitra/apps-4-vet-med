package bci.ksu.edu.brdtc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import bci.ksu.edu.brdtc.parameters.Drug;
import bci.ksu.edu.brdtc.parameters.Population;
import bci.ksu.edu.brdtc.predicates.AdgHealthyCattlePredicate;
import bci.ksu.edu.brdtc.predicates.ArrivalWeightPredicate;
import bci.ksu.edu.brdtc.predicates.CostOfGainPredicate;
import bci.ksu.edu.brdtc.predicates.CostOfTreatmentPredicate;
import bci.ksu.edu.brdtc.predicates.DaysOnFeedPredicate;
import bci.ksu.edu.brdtc.predicates.InvalidBoundsPredicate;
import bci.ksu.edu.brdtc.predicates.NullFieldPredicate;
import bci.ksu.edu.brdtc.predicates.PriceReceivedAtSalePredicate;
import bci.ksu.edu.brdtc.predicates.ValidatePredicate;
import bci.ksu.edu.brdtc.util.FileReadAndWrite;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String LOG_CAT = MainActivity.class.getSimpleName();
    private static DecimalFormat df = new DecimalFormat("#.00");

    private ViewFlipper viewFlipper;

    // All Buttons
    private Button mMainPageNextButton;
    private ImageButton mPopParamPageNextButton;
    private ImageButton mPopParamPageBackButton;
    private ImageButton mDrugOnePageNextButton;
    private ImageButton mDrugOnePageBackButton;
    private ImageButton mDrugTwoPageNextButton;
    private ImageButton mDrugTwoPageBackButton;
    private ImageButton mResultsPageResetButton;
    private ImageButton mResultsPageBackButton;

    // Population parameters
    private EditText mMorbidityEditText;
    private EditText mCostOfGainEditText;
    private EditText mPriceReceivedAtSaleEditText;
    private EditText mArrivalWeightEditText;
    private EditText mDaysOnFeedEditText;
    private EditText mAdgHealthyCattleEditText;

    // Drug one parameters
    private AutoCompleteTextView mDrugOneNameTextView;
    private EditText mDrugOneTreatmentFailurePercentEditText;
    private EditText mDrugOneCaseFatalityRiskEditText;
    private EditText mDrugOneCostOfTreatmentEditText;
    private EditText mDrugOneChronicPercentEditText;

    // Drug two parameters
    private AutoCompleteTextView mDrugTwoNameTextView;
    private EditText mDrugTwoTreatmentFailurePercentEditText;
    private EditText mDrugTwoCaseFatalityRiskEditText;
    private EditText mDrugTwoCostOfTreatmentEditText;
    private EditText mDrugTwoChronicPercentEditText;

    // Results
    private TextView mResultsTextView;
    private TextView mTableDrug1NameTextView;
    private TextView mTableDrug1CfrTextView;
    private TextView mTableDrug1CtTextView;
    private TextView mTableDrug1CprTextView;
    private TextView mTableDrug1TfpTextView;
    private TextView mTableDrug2NameTextView;
    private TextView mTableDrug2CfrTextView;
    private TextView mTableDrug2CtTextView;
    private TextView mTableDrug2CprTextView;
    private TextView mTableDrug2TfpTextView;
    private TextView mCostPivotTextView;

    // Parameters & Calculator
    private Calculator calculator;
    private Population population;
    private Drug drugOne;
    private Drug drugTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupUI();
        setupListeners();
        setupParameters();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void setupUI() {
        viewFlipper = (ViewFlipper) findViewById(R.id.viewFlipper);

        // Buttons
        mMainPageNextButton = (Button) findViewById(R.id.mainPageNextButton);
        mPopParamPageNextButton = (ImageButton) findViewById(R.id.popParamPageNextButton);
        mPopParamPageBackButton = (ImageButton) findViewById(R.id.popParamPageBackButton);
        mDrugOnePageNextButton = (ImageButton) findViewById(R.id.drugOnePageNextButton);
        mDrugOnePageBackButton = (ImageButton) findViewById(R.id.drugOnePageBackButton);
        mDrugTwoPageNextButton = (ImageButton) findViewById(R.id.drugTwoPageNextButton);
        mDrugTwoPageBackButton = (ImageButton) findViewById(R.id.drugTwoPageBackButton);
        mResultsPageResetButton = (ImageButton) findViewById(R.id.resultsPageResetButton);
        mResultsPageBackButton = (ImageButton) findViewById(R.id.resultsPageBackButton);

        // Population Widgets
        mMorbidityEditText = (EditText) findViewById(R.id.morbidityEditText);
        mCostOfGainEditText = (EditText) findViewById(R.id.costOfGainEditText);
        mPriceReceivedAtSaleEditText = (EditText) findViewById(R.id.priceReceivedAtSaleEditText);
        mArrivalWeightEditText = (EditText) findViewById(R.id.arrivalWeightEditText);
        mDaysOnFeedEditText = (EditText) findViewById(R.id.daysOnFeedEditText);
        mAdgHealthyCattleEditText = (EditText) findViewById(R.id.adgHealthyCattleEditText);

        // Drug One Widgets
        mDrugOneNameTextView = (AutoCompleteTextView) findViewById(R.id.drugOneNameTextView);
        ArrayAdapter<String> adapter = setAdapter();
        mDrugOneNameTextView.setAdapter(adapter);
        mDrugOneTreatmentFailurePercentEditText = (EditText) findViewById(R.id.drugOneTreatmentFailurePercentEditText);
        mDrugOneCaseFatalityRiskEditText = (EditText) findViewById(R.id.drugOneCaseFatalityRiskEditText);
        mDrugOneCostOfTreatmentEditText = (EditText) findViewById(R.id.drugOneCostOfTreatmentEditText);
        mDrugOneChronicPercentEditText = (EditText) findViewById(R.id.drugOneChronicPercentEditText);

        // Drug Two Widgets
        mDrugTwoNameTextView = (AutoCompleteTextView) findViewById(R.id.drugTwoNameTextView);
        ArrayAdapter<String> adapter2 = setAdapter();
        mDrugTwoNameTextView.setAdapter(adapter2);
        mDrugTwoTreatmentFailurePercentEditText = (EditText) findViewById(R.id.drugTwoTreatmentFailurePercentEditText);
        mDrugTwoCaseFatalityRiskEditText = (EditText) findViewById(R.id.drugTwoCaseFatalityRiskEditText);
        mDrugTwoCostOfTreatmentEditText = (EditText) findViewById(R.id.drugTwoCostOfTreatmentEditText);
        mDrugTwoChronicPercentEditText = (EditText) findViewById(R.id.drugTwoChronicPercentEditText);

        // Results Widgets
        mResultsTextView = (TextView) findViewById(R.id.resultsTextView);
        mTableDrug1NameTextView = (TextView) findViewById(R.id.tableDrug1NameTextView);
        mTableDrug1CfrTextView = (TextView) findViewById(R.id.tableDrug1CfrTextView);
        mTableDrug1CtTextView = (TextView) findViewById(R.id.tableDrug1CtTextView);
        mTableDrug1CprTextView = (TextView) findViewById(R.id.tableDrug1CprTextView);
        mTableDrug1TfpTextView = (TextView) findViewById(R.id.tableDrug1TfpTextView);
        mTableDrug2NameTextView = (TextView) findViewById(R.id.tableDrug2NameTextView);
        mTableDrug2CfrTextView = (TextView) findViewById(R.id.tableDrug2CfrTextView);
        mTableDrug2CtTextView = (TextView) findViewById(R.id.tableDrug2CtTextView);
        mTableDrug2CprTextView = (TextView) findViewById(R.id.tableDrug2CprTextView);
        mTableDrug2TfpTextView = (TextView) findViewById(R.id.tableDrug2TfpTextView);
        mCostPivotTextView = (TextView) findViewById(R.id.costPivotInfoTextView);
    }

    private void setupListeners() {
        mMainPageNextButton.setOnClickListener(this);
        mPopParamPageNextButton.setOnClickListener(this);
        mPopParamPageBackButton.setOnClickListener(this);
        mDrugOnePageNextButton.setOnClickListener(this);
        mDrugOnePageBackButton.setOnClickListener(this);
        mDrugTwoPageNextButton.setOnClickListener(this);
        mDrugTwoPageBackButton.setOnClickListener(this);
        mResultsPageResetButton.setOnClickListener(this);
        mResultsPageBackButton.setOnClickListener(this);
    }

    private void setupParameters() {
        calculator = new Calculator();
        population = new Population();
        drugOne = new Drug();
        drugTwo = new Drug();
    }

    private ArrayAdapter<String> setAdapter() {
        String[] drugNames = getResources().getStringArray(R.array.drug_names);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, drugNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    @Override
    public void onClick(View view) {
        List<EditText> editTexts = new ArrayList<>();
        List<EditText> percentRestrictedTexts = new ArrayList<>();
        switch (view.getId()) {
            // When user taps continue button on main page
            case (R.id.mainPageNextButton) :
                File drugFile = new File(getFilesDir(), "drugs.txt");
                File popFile = new File(getFilesDir(), "population.txt");
                boolean drugFileExists = drugFile.exists();
                boolean popFileExists = popFile.exists();
                if (drugFileExists && popFileExists) {
                    List<Drug> drugs = readDrugsFromFile("drugs.txt");
                    drugOne = drugs.get(0);
                    drugTwo = drugs.get(1);
                    population = readPopulationFromFile("population.txt");
                    setFields();
                }
                viewFlipper.showNext();
                break;
            // When user taps next button on population parameters page
            case R.id.popParamPageNextButton :
                editTexts.add(mMorbidityEditText);
                editTexts.add(mCostOfGainEditText);
                editTexts.add(mPriceReceivedAtSaleEditText);
                editTexts.add(mArrivalWeightEditText);
                editTexts.add(mDaysOnFeedEditText);
                editTexts.add(mAdgHealthyCattleEditText);
                percentRestrictedTexts.add(mMorbidityEditText);
                if (existNullFields(editTexts) || existInvalidBounds(percentRestrictedTexts) ||
                        existInvalidPopulationParam(editTexts)) {
                    break;
                }
                setPopulationParameters();
                setNormalBackground(editTexts);
                viewFlipper.showNext();
                break;
            // When user taps previous button on population parameters page
            case R.id.popParamPageBackButton :
                hideSoftKeyboard();
                viewFlipper.showPrevious();
                break;
            // When user taps next button on drug one parameters page
            case R.id.drugOnePageNextButton :
                editTexts.add(mDrugOneTreatmentFailurePercentEditText);
                editTexts.add(mDrugOneCaseFatalityRiskEditText);
                editTexts.add(mDrugOneCostOfTreatmentEditText);
                editTexts.add(mDrugOneChronicPercentEditText);
                percentRestrictedTexts.add(mDrugOneTreatmentFailurePercentEditText);
                percentRestrictedTexts.add(mDrugOneCaseFatalityRiskEditText);
                percentRestrictedTexts.add(mDrugOneChronicPercentEditText);
                if (existNullFields(editTexts) || existInvalidBounds(percentRestrictedTexts) ||
                        existEmptyNameField(mDrugOneNameTextView) || existInvalidDrugParam(
                        mDrugOneCostOfTreatmentEditText, new CostOfTreatmentPredicate())) {
                    break;
                }
                setDrugOneParameters();
                setNormalBackground(editTexts);
                viewFlipper.showNext();
                break;
            // When user taps previous button on drug one parameters page
            case R.id.drugOnePageBackButton :
                hideSoftKeyboard();
                viewFlipper.showPrevious();
                break;
            // When user taps next button on drug two parameters page
            case R.id.drugTwoPageNextButton :
                editTexts.add(mDrugTwoTreatmentFailurePercentEditText);
                editTexts.add(mDrugTwoCaseFatalityRiskEditText);
                editTexts.add(mDrugTwoCostOfTreatmentEditText);
                editTexts.add(mDrugTwoChronicPercentEditText);
                percentRestrictedTexts.add(mDrugTwoTreatmentFailurePercentEditText);
                percentRestrictedTexts.add(mDrugTwoCaseFatalityRiskEditText);
                percentRestrictedTexts.add(mDrugTwoChronicPercentEditText);
                if (existNullFields(editTexts) || existInvalidBounds(percentRestrictedTexts) ||
                        existEmptyNameField(mDrugTwoNameTextView) || existInvalidDrugParam(
                        mDrugTwoCostOfTreatmentEditText, new CostOfTreatmentPredicate())) {
                    break;
                }
                setDrugTwoParameters();
                getAndDisplayResults();
                setNormalBackground(editTexts);

                hideSoftKeyboard();
                viewFlipper.showNext();
                break;
            // When user taps previous button on drug two parameters page
            case R.id.drugTwoPageBackButton :
                hideSoftKeyboard();
                viewFlipper.showPrevious();
                break;
            // When user taps reset button on results page
            case R.id.resultsPageResetButton :
                saveDrugsToFile("drugs.txt");
                savePopulationToFile("population.txt");
                setupParameters();
                mResultsTextView.setText(R.string.results);
                viewFlipper.showNext();
                break;
            // When user taps previous button on results page
            case R.id.resultsPageBackButton :
                hideSoftKeyboard();
                List<TextView> tableViews = new ArrayList<>();
                tableViews.add(mTableDrug1CfrTextView);
                tableViews.add(mTableDrug1CprTextView);
                tableViews.add(mTableDrug1CtTextView);
                tableViews.add(mTableDrug1TfpTextView);
                tableViews.add(mTableDrug2CfrTextView);
                tableViews.add(mTableDrug2CprTextView);
                tableViews.add(mTableDrug2CtTextView);
                tableViews.add(mTableDrug2TfpTextView);
                setNormalTextView(tableViews);
                mResultsTextView.setText(R.string.results);
                mCostPivotTextView.setText(R.string.results_cost_diff_suggestion);
                viewFlipper.showPrevious();
                break;
        }
        hideSoftKeyboard();
        editTexts.clear();
        percentRestrictedTexts.clear();
    }

    private void setPopulationParameters() {
        population.setMorbidity(Float.parseFloat(mMorbidityEditText.getText().toString()));
        population.setCog(Float.parseFloat(mCostOfGainEditText.getText().toString()));
        population.setSp(Float.parseFloat(mPriceReceivedAtSaleEditText.getText().toString()));
        population.setPw(Integer.parseInt(mArrivalWeightEditText.getText().toString()));
        population.setDays(Integer.parseInt(mDaysOnFeedEditText.getText().toString()));
        population.setAhc(Float.parseFloat(mAdgHealthyCattleEditText.getText().toString()));
    }

    private void setDrugOneParameters() {
        drugOne.setName(mDrugOneNameTextView.getText().toString());
        drugOne.setTfp(Float.parseFloat(mDrugOneTreatmentFailurePercentEditText.getText().toString()));
        drugOne.setCfr(Float.parseFloat(mDrugOneCaseFatalityRiskEditText.getText().toString()));
        drugOne.setCt1((Float.parseFloat(mDrugOneCostOfTreatmentEditText.getText().toString())));
        drugOne.setCp(Float.parseFloat((mDrugOneChronicPercentEditText.getText().toString())));
    }

    private void setDrugTwoParameters() {
        drugTwo.setName(mDrugTwoNameTextView.getText().toString());
        drugTwo.setTfp(Float.parseFloat(mDrugTwoTreatmentFailurePercentEditText.getText().toString()));
        drugTwo.setCfr(Float.parseFloat(mDrugTwoCaseFatalityRiskEditText.getText().toString()));
        drugTwo.setCt1((Float.parseFloat(mDrugTwoCostOfTreatmentEditText.getText().toString())));
        drugTwo.setCp(Float.parseFloat((mDrugTwoChronicPercentEditText.getText().toString())));
    }

    private void setFields() {
        // Population
        mMorbidityEditText.setText(String.valueOf(population.getMorbidity()));
        mCostOfGainEditText.setText(String.valueOf(population.getCog()));
        mPriceReceivedAtSaleEditText.setText(String.valueOf(population.getSp()));
        mArrivalWeightEditText.setText(String.valueOf(population.getPw()));
        mDaysOnFeedEditText.setText(String.valueOf(population.getDays()));
        mAdgHealthyCattleEditText.setText(String.valueOf(population.getAhc()));

        // Drug One
        mDrugOneNameTextView.setText(drugOne.getName());
        mDrugOneTreatmentFailurePercentEditText.setText(String.valueOf(drugOne.getTfp()));
        mDrugOneCaseFatalityRiskEditText.setText(String.valueOf(drugOne.getCfr()));
        mDrugOneCostOfTreatmentEditText.setText(String.valueOf(drugOne.getCt1()));
        mDrugOneChronicPercentEditText.setText(String.valueOf(drugOne.getCp()));

        // Drug Two
        mDrugTwoNameTextView.setText(drugTwo.getName());
        mDrugTwoTreatmentFailurePercentEditText.setText(String.valueOf(drugTwo.getTfp()));
        mDrugTwoCaseFatalityRiskEditText.setText(String.valueOf(drugTwo.getCfr()));
        mDrugTwoCostOfTreatmentEditText.setText(String.valueOf(drugTwo.getCt1()));
        mDrugTwoChronicPercentEditText.setText(String.valueOf(drugTwo.getCp()));
    }

    private void getAndDisplayResults() {
        float results = calculator.differenceInReturnToOAM(drugOne, drugTwo, population);
        Log.d(LOG_CAT, "Got results: " + results);
        float costPivot = calculator.calculateCostPivot(results, drugOne, drugTwo, population);
        Log.d(LOG_CAT, "Calculated pivot" + costPivot);
        if (costPivot < 1) {
            mCostPivotTextView.append("0");
        }
        mCostPivotTextView.append(df.format(costPivot));
        Log.d(LOG_CAT, "Displaying Pivot");
        StringBuilder sb = new StringBuilder();
        String betterDrug;
        String notAsGoodDrug;
        sb.append("Drug ");
        if (results < 0) {
            betterDrug = drugOne.getName();
            notAsGoodDrug = drugTwo.getName();
            sb.append(betterDrug);
            results *= -1;
        } else {
            betterDrug = drugTwo.getName();
            notAsGoodDrug = drugOne.getName();
            sb.append(betterDrug);
        }
        sb.append(" ");
        sb.append("shows");
        sb.append(" $");
        if (String.valueOf(results).startsWith("0")) {
            sb.append("0");
        }
        sb.append(df.format(results));
        sb.append("/head");
        sb.append(" advantage over Drug ");
        sb.append(notAsGoodDrug);
        sb.append(" using the information you provided");
        String resultsText = sb.toString();
        setResultsPage(resultsText);
        Log.d(LOG_CAT, "Displaying results");
    }

    private void setResultsPage(String resultsText) {
        mResultsTextView.setText("");
        mResultsTextView.append(resultsText);
        // Set Table Results
        mTableDrug1NameTextView.setText("");
        mTableDrug1NameTextView.setText(drugOne.getName());
        mTableDrug2NameTextView.setText("");
        mTableDrug2NameTextView.setText(drugTwo.getName());
        displayTxValues(mTableDrug1TfpTextView, mTableDrug2TfpTextView, drugOne.getTfp(), drugTwo.getTfp());
        displayTxValues(mTableDrug1CfrTextView, mTableDrug2CfrTextView, drugOne.getCfr(), drugTwo.getCfr());
        displayTxValues(mTableDrug1CtTextView, mTableDrug2CtTextView, drugOne.getCt1(), drugTwo.getCt1());
        displayTxValues(mTableDrug1CprTextView, mTableDrug2CprTextView, drugOne.getCp(), drugTwo.getCp());
    }

    private void displayTxValues(TextView txView1, TextView txView2, float txValue1, float txValue2) {
        if (txValue1 < txValue2) {
            setNumberText(txView1, txValue1);
            txView1.setTextColor(getResources().getColor(R.color.white));
            txView1.setBackgroundColor(getResources().getColor(R.color.green));
            setNumberText(txView2, txValue2);
        } else {
            setNumberText(txView2, txValue2);
            txView2.setTextColor(getResources().getColor(R.color.white));
            txView2.setBackgroundColor(getResources().getColor(R.color.green));

            setNumberText(txView1, txValue1);
        }
    }

    private void setNormalTextView(List<TextView> textViews) {
        int defaultColor = mTableDrug1NameTextView.getCurrentTextColor();
        for (TextView textView : textViews) {
            textView.setTextColor(defaultColor);
            textView.setBackgroundColor(getResources().getColor(R.color.white));
        }
    }

    private void setNumberText(TextView view, float txValue) {
        StringBuilder sb = new StringBuilder();
        sb.append("$");
        if (txValue < 1) {
            sb.append("0");
        }
        sb.append(df.format(txValue));
        String number = sb.toString();
        view.setText("");
        view.setText(number);
    }

    private boolean existNullFields(List<EditText> editTexts) {
        int numNullFields = checkFields(editTexts, new NullFieldPredicate());
        if (numNullFields > 0) {
            Toast.makeText(this, "THERE ARE EMPTY FIELDS!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private boolean existInvalidBounds(List<EditText> editTexts) {
        int numInvalidFields = checkFields(editTexts, new InvalidBoundsPredicate());
        if (numInvalidFields > 0) {
            Toast.makeText(this, "INVALID PERCENT RANGE!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return false;
    }

    private int checkFields(List<EditText> editTexts, ValidatePredicate vp) {
        int numBadFields = 0;
        for (EditText editText : editTexts) {
            String input = editText.getText().toString();
            if (vp.test(input)) {
                editText.setBackgroundResource(R.drawable.invalid_field);
                numBadFields++;
            } else {
                editText.setBackgroundResource(R.drawable.edit_text_style);
            }
        }
        return numBadFields;
    }

    private boolean existInvalidDrugParam(EditText editText, ValidatePredicate vp) {
        String input = editText.getText().toString();
        if (vp.test(input)) {
            editText.setBackgroundResource(R.drawable.invalid_field);
            Toast.makeText(this, "INVALID DRUG PARAMETER!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            editText.setBackgroundResource(R.drawable.edit_text_style);
        }
        return false;
    }

    private boolean existEmptyNameField(AutoCompleteTextView nameTextView) {
        if (nameTextView.getText().toString().trim().length() == 0) {
            nameTextView.setBackgroundResource(R.drawable.invalid_field);
            Toast.makeText(this, "DRUG NAME FIELD IS EMPTY!", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            nameTextView.setBackgroundResource(R.drawable.edit_text_style);
        }
        return false;
    }

    private boolean existInvalidPopulationParam(List<EditText> editTexts) {
        setNormalBackground(editTexts);
        if (new CostOfGainPredicate().test(mCostOfGainEditText.getText().toString())) {
            Toast.makeText(this, "Invalid Cost of Gain", Toast.LENGTH_SHORT).show();
            mCostOfGainEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new PriceReceivedAtSalePredicate().test(mPriceReceivedAtSaleEditText.getText().toString())) {
            Toast.makeText(this, "Invalid Price Received At Sale", Toast.LENGTH_SHORT).show();
            mPriceReceivedAtSaleEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new ArrivalWeightPredicate().test(mArrivalWeightEditText.getText().toString())) {
            Toast.makeText(this, "Invalid Arrival Weight", Toast.LENGTH_SHORT).show();
            mArrivalWeightEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new DaysOnFeedPredicate().test(mDaysOnFeedEditText.getText().toString())) {
            Toast.makeText(this, "Invalid Days on Feed", Toast.LENGTH_SHORT).show();
            mDaysOnFeedEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new AdgHealthyCattlePredicate().test(mAdgHealthyCattleEditText.getText().toString())) {
            Toast.makeText(this, "Invalid Adg Healthy Cattle", Toast.LENGTH_SHORT).show();
            mAdgHealthyCattleEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else {
            setNormalBackground(editTexts);
            return false;
        }
    }

    private void hideSoftKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    private void setNormalBackground(List<EditText> editTexts) {
        for (EditText editText : editTexts) {
            editText.setBackgroundResource(R.drawable.edit_text_style);
        }
    }

    private void savePopulationToFile(String fileName) {
        File file = new File(getFilesDir(), fileName);
        Gson gson = new Gson();
        String data = gson.toJson(population);
        FileReadAndWrite fileRaW = new FileReadAndWrite();
        fileRaW.write(file, data);
    }

    private void saveDrugsToFile(String fileName) {
        File file = new File(getFilesDir(), fileName);
        List<Drug> drugs = new ArrayList<>();
        drugs.add(drugOne);
        drugs.add(drugTwo);
        Gson gson = new Gson();
        String data = gson.toJson(drugs);
        FileReadAndWrite fileRaW = new FileReadAndWrite();
        fileRaW.write(file, data);
    }

    private List<Drug> readDrugsFromFile(String fileName) {
        Type listType = new TypeToken<ArrayList<Drug>>(){}.getType();
        FileReadAndWrite fileRaW = new FileReadAndWrite();
        String json = fileRaW.read(fileName, this);
        Gson gson = new Gson();
        return gson.fromJson(json, listType);
    }

    private Population readPopulationFromFile(String fileName) {
        FileReadAndWrite fileRaW = new FileReadAndWrite();
        String json = fileRaW.read(fileName, this);
        Gson gson = new Gson();
        return gson.fromJson(json, Population.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}