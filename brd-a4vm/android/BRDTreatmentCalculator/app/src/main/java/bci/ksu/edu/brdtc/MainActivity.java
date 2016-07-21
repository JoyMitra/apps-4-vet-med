package bci.ksu.edu.brdtc;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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

public class MainActivity extends AppCompatActivity implements OnClickListener {

    private static final String LOG_CAT = MainActivity.class.getSimpleName();
    private static DecimalFormat df = new DecimalFormat(".##");

    private ViewFlipper viewFlipper;

    // All Buttons
    private Button mMainPageNextButton;
    private Button mPopParamPageNextButton;
    private Button mPopParamPageBackButton;
    private Button mDrugOnePageNextButton;
    private Button mDrugOnePageBackButton;
    private Button mDrugTwoPageNextButton;
    private Button mDrugTwoPageBackButton;
    private Button mResultsPageResetButton;
    private Button mResultsPageBackButton;

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
        mPopParamPageNextButton = (Button) findViewById(R.id.popParamPageNextButton);
        mPopParamPageBackButton = (Button) findViewById(R.id.popParamPageBackButton);
        mDrugOnePageNextButton = (Button) findViewById(R.id.drugOnePageNextButton);
        mDrugOnePageBackButton = (Button) findViewById(R.id.drugOnePageBackButton);
        mDrugTwoPageNextButton = (Button) findViewById(R.id.drugTwoPageNextButton);
        mDrugTwoPageBackButton = (Button) findViewById(R.id.drugTwoPageBackButton);
        mResultsPageResetButton = (Button) findViewById(R.id.resultsPageResetButton);
        mResultsPageBackButton = (Button) findViewById(R.id.resultsPageBackButton);

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

        // Text View
        mResultsTextView = (TextView) findViewById(R.id.resultsTextView);
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

    public void onClick(View view) {
        List<EditText> editTexts = new ArrayList<>();
        List<EditText> percentRestrictedTexts = new ArrayList<>();
        switch (view.getId()) {
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
            case R.id.popParamPageBackButton :
                viewFlipper.showPrevious();
                break;
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
            case R.id.drugOnePageBackButton :
                viewFlipper.showPrevious();
                break;
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
                saveDrugsToFile("drugs.txt");

                viewFlipper.showNext();
                break;
            case R.id.drugTwoPageBackButton :
                viewFlipper.showPrevious();
                break;
            case R.id.resultsPageResetButton :
                savePopulationToFile("population.txt");
                setupParameters();
                mResultsTextView.setText(R.string.results);
                viewFlipper.showNext();
                break;
            case R.id.resultsPageBackButton :
                mResultsTextView.setText(R.string.results);
                viewFlipper.showPrevious();
                break;
        }
        editTexts.clear();
        percentRestrictedTexts.clear();
        hideSoftKeyboard();
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

    private void getAndDisplayResults(){
        float results = calculator.calcDifferenceInReturnToOAM(drugOne, drugTwo, population);
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
        mResultsTextView.setText("");
        mResultsTextView.append(resultsText);
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
            if (vp.test(editText)) {
                editText.setBackgroundResource(R.drawable.invalid_field);
                numBadFields++;
            } else {
                editText.setBackgroundResource(R.drawable.edit_text_style);
            }
        }
        return numBadFields;
    }

    private boolean existInvalidDrugParam(EditText editText, ValidatePredicate vp) {
        if (vp.test(editText)) {
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
        if (new CostOfGainPredicate().test(mCostOfGainEditText)) {
            Toast.makeText(this, "Invalid Cost of Gain", Toast.LENGTH_SHORT).show();
            mCostOfGainEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new PriceReceivedAtSalePredicate().test(mPriceReceivedAtSaleEditText)) {
            Toast.makeText(this, "Invalid Price Received At Sale", Toast.LENGTH_SHORT).show();
            mPriceReceivedAtSaleEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new ArrivalWeightPredicate().test(mArrivalWeightEditText)) {
            Toast.makeText(this, "Invalid Arrival Weight", Toast.LENGTH_SHORT).show();
            mArrivalWeightEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new DaysOnFeedPredicate().test(mDaysOnFeedEditText)) {
            Toast.makeText(this, "Invalid Days on Feed", Toast.LENGTH_SHORT).show();
            mDaysOnFeedEditText.setBackgroundResource(R.drawable.invalid_field);
            return true;
        } else if (new AdgHealthyCattlePredicate().test(mAdgHealthyCattleEditText)) {
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

    //TODO: Remove duplicate code
    private void savePopulationToFile(String fileName) {
        File file = new File(getFilesDir(), fileName);

        Gson gson = new Gson();
        String data = gson.toJson(population);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void saveDrugsToFile(String fileName) {
        File file = new File(getFilesDir(), fileName);

        List<Drug> drugs = new ArrayList<>();
        drugs.add(drugOne);
        drugs.add(drugTwo);
        Gson gson = new Gson();
        String data = gson.toJson(drugs);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //TODO: Remove duplicate code
    private List<Drug> readDrugsFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String json = sb.toString();
        Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Drug>>(){}.getType();
        return gson.fromJson(json, listType);
    }

    private Population readPopulationFromFile(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            FileInputStream fis = openFileInput(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String json = sb.toString();
        Gson gson = new Gson();
        return gson.fromJson(json, Population.class);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}