package edu.ksu.cis.a4vm.bse;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

public class EditMorphologyCounts extends AppCompatActivity {

    HashSet<String> fields = null;
    HashSet<String> morphInfo = null;
    EditText et1 = null;
    EditText et2 = null;
    EditText et3 = null;
    EditText et4 = null;
    EditText et5 = null;
    EditText et6 = null;
    EditText et7 = null;
    EditText et8 = null;
    TextView tv1 = null;
    TextView tv2 = null;
    TextView tv3 = null;
    TextView tv4 = null;
    TextView tv5 = null;
    TextView tv6 = null;
    TextView tv7 = null;
    TextView tv8 = null;
    int NormalCount = 0;
    double NormalProp = 0.0;
    int Lb2Count = 0;
    double Lb2Prop = 0.0;
    int Lb3Count = 0;
    double Lb3Prop = 0.0;
    int Lb4Count = 0;
    double Lb4Prop = 0.0;
    int Lb5Count = 0;
    double Lb5Prop = 0.0;
    int Lb6Count = 0;
    double Lb6Prop = 0.0;
    int Lb7Count = 0;
    double Lb7Prop = 0.0;
    int Lb8Count = 0;
    double Lb8Prop = 0.0;
    Double limit = 150.0;
    String key = null;
    String grpKey = null;
    public boolean valid = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_morphology_counts);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        key = getIntent().getStringExtra("morphKey");
        if(key!=null)
            grpKey = (key.split("_"))[0];
        /*fields = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_FILE_MORPH_INFO, Constant.KEY_MORPHOLOGY);*/
        fields = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_GRP_MORPH_CONFIG,grpKey);
        morphInfo = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_BULL_MORPHOLOGY_INFO, key);
        TableLayout table = new TableLayout(this);
        TableRow row;
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        table.setLayoutParams(lp);
        table.setShrinkAllColumns(true);
        table.setStretchAllColumns(true);

        TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.5f);
        TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0f);



        if(morphInfo!=null && fields!=null)
        {
            for(String Val: morphInfo)
            {
                String[] values = Val.split("=");
                if(values!=null && values.length==2 && values[0].equalsIgnoreCase("Normal"))
                {
                    NormalCount = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                    NormalProp = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                }
                else
                {
                    for(String label: fields)
                    {
                        String[] lbls = label.split("=");
                        if(lbls!=null && lbls.length==2 && lbls[1].equalsIgnoreCase(values[0]))
                        {
                            if(lbls[0].equalsIgnoreCase("Morphology Field 2"))
                            {
                                Lb2Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb2Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 3"))
                            {
                                Lb3Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb3Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 4"))
                            {
                                Lb4Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb4Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 5"))
                            {
                                Lb5Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb5Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 6"))
                            {
                                Lb6Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb6Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 7"))
                            {
                                Lb7Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb7Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 8"))
                            {
                                Lb8Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb8Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                        }
                    }
                }
            }
        }

        row = new TableRow(this);
        et1 = new EditText(this);
        et1.setText(""+NormalCount);
        tv1 = new TextView(this);
        tv1.setText("Normal");
        row.addView(tv1);
        row.addView(et1);
        table.addView(row);
        setContentView(table);
        if(fields!=null)
        {
            Iterator<String> it;

            TableRow row2 = new TableRow(this);
            TableRow row3 = new TableRow(this);
            TableRow row4 = new TableRow(this);
            TableRow row5 = new TableRow(this);

            it = fields.iterator();
            while(it.hasNext())
            {
                String label = it.next();
                String text[] = label.split("=");
                String txt = null;
                if(text!=null && text.length==2)
                {
                    txt = text[1];
                }
                if(label.contains("Morphology Field 2"))
                {
                    et2 = new EditText(this);

                    et2.setText(""+Lb2Count);
                    et2.setHeight(150);
                    tv2 = new TextView(this);
                    tv2.setText(txt);
                    tv2.setHeight(150);
                    row2.addView(tv2);
                    row2.addView(et2);
                }
                else if(label.contains("Morphology Field 4"))
                {
                    et4 = new EditText(this);
                    et4.setHeight(150);
                    et4.setText(""+Lb4Count);
                    tv4 = new TextView(this);
                    tv4.setText(txt);
                    tv4.setHeight(150);
                    row3.addView(tv4);
                    row3.addView(et4);
                }
                else if(label.contains("Morphology Field 6"))
                {
                    et6 = new EditText(this);
                    et6.setHeight(150);
                    et6.setText(""+Lb6Count);
                    tv6 = new TextView(this);
                    tv6.setText(txt);
                    tv6.setHeight(150);
                    row4.addView(tv6);
                    row4.addView(et6);
                }
                else if(label.contains("Morphology Field 8"))
                {
                    et8 = new EditText(this);
                    //btn = new EditText(this);
                    et8.setHeight(150);
                    et8.setText(""+Lb8Count);
                    tv8 = new TextView(this);
                    tv8.setText(txt);
                    tv8.setHeight(150);
                    row5.addView(tv8);
                    row5.addView(et8);
                    //row5.addView(btn);
                }
            }

            it = fields.iterator();
            while(it.hasNext())
            {
                String label = it.next();
                String text[] = label.split("=");
                String txt = null;
                if(text!=null && text.length==2)
                {
                    txt = text[1];

                }
                if(label.contains("Limit"))
                {
                    if(text!=null && text.length==2)
                    {
                        limit = Double.valueOf(text[1]);
                    }
                }
                else if(label.contains("Morphology Field 3"))
                {
                    et3 = new EditText(this);
                    et3.setText(""+Lb3Count);
                    et3.setHeight(150);
                    tv3 = new TextView(this);
                    tv3.setText(txt);
                    tv3.setHeight(150);
                    row2.addView(tv3);
                    row2.addView(et3);
                }
                else if(label.contains("Morphology Field 5"))
                {
                    et5 = new EditText(this);
                    et5.setHeight(150);
                    et5.setText("" + Lb5Count);
                    tv5 = new TextView(this);
                    tv5.setText(txt);
                    tv5.setHeight(150);
                    row3.addView(tv5);
                    row3.addView(et5);
                }
                else if(label.contains("Morphology Field 7"))
                {
                    et7 = new EditText(this);
                    et7.setHeight(150);
                    et7.setText(""+Lb7Count);
                    tv7 = new TextView(this);
                    tv7.setText(txt);
                    tv7.setHeight(150);
                    row4.addView(tv7);
                    row4.addView(et7);
                }

            }


            table.addView(row2,rowLp);
            table.addView(row3,rowLp);
            table.addView(row4,rowLp);
            table.addView(row5,rowLp);

        }

        if(et1!=null)
        {
            et1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et1.getText().toString().trim();

                        try {
                            NormalCount = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;


                }
            });
        }


        if(et2!=null)
        {
            et2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et2.getText().toString().trim();

                        try {
                            Lb2Count = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;

                }
            });
        }


        if(et3!=null)
        {
            et3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et3.getText().toString().trim();
                        try {
                            Lb3Count = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;


                }
            });

        }

        if(et4!=null)
        {
            et4.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et4.getText().toString().trim();

                        try {
                            Lb4Count = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;


                }
            });
        }

        if(et5!=null)
        {
            et5.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et5.getText().toString().trim();

                        try {
                            Lb5Count = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;


                }
            });

        }

        if(et6!=null)
        {
            et6.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et6.getText().toString().trim();

                        try {
                            Lb6Count = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;


                }
            });

        }

        if(et7!=null)
        {
            et7.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String entry = et7.getText().toString().trim();

                        try {
                            Lb7Count = Integer.valueOf(entry);
                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                            Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                            valid = false;
                        } else
                            valid = true;


                }
            });

        }

        if(et8!=null)
        {
            et8.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    try {
                        String entry = et8.getText().toString().trim();

                            try {
                                Lb8Count = Integer.valueOf(entry);
                            } catch (NumberFormatException ne) {
                                ne.printStackTrace();
                                Toast.makeText(getApplicationContext(), "Only numeric values allowed", Toast.LENGTH_SHORT).show();
                                valid = false;
                            }
                            if (NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                                Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                                valid = false;
                            } else
                                valid = true;


                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "App failed!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }


    @Override
    public void onPause()
    {
        super.onPause();
        HashSet<String> editVals = new HashSet<String>();
        if(et1!=null)
        {
            String text = et1.getText().toString().trim();
            if(text.length()>0)
            {
                try{
                    Double count = Double.valueOf(text);
                    Double prop = (count/limit)*150.0;
                    editVals.add(tv1.getText().toString().trim() + "=" + text + "(" + String.format("%.2f",prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }

            }
            else
                valid = false;


        }
        if(et2!=null)
        {
            String text = et2.getText().toString().trim();
            if(text.length()>0)
            {

                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv2.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }

            }
            else
                valid = false;

        }

        if(et3!=null)
        {
            String text = et3.getText().toString().trim();
            if(text.length()>0)
            {
                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv3.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }
            }
            else
                valid = false;


        }
        if(et4!=null)
        {
            String text = et4.getText().toString().trim();
            if(text.length()>0)
            {
                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv4.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }
            }
            else
                valid = false;


        }
        if(et5!=null)
        {
            String text = et5.getText().toString().trim();
            if(text.length()>0)
            {
                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv5.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }
            }
            else
                valid = false;


        }
        if(et6!=null)
        {
            String text = et6.getText().toString().trim();
            if(text.length()>0)
            {
                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv6.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }
            }
            else
                valid = false;


        }
        if(et7!=null)
        {
            String text = et7.getText().toString().trim();
            if(text.length()>0)
            {
                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv7.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }
            }
            else
                valid = false;


        }
        if(et8!=null)
        {
            String text = et8.getText().toString().trim();
            if(text.length()>0)
            {
                try {
                    Double count = Double.valueOf(text);

                    Double prop = (count / limit) * 150.0;
                    editVals.add(tv8.getText().toString().trim() + "=" + text + "(" + String.format("%.2f", prop) + "%)");
                }
                catch(NumberFormatException ne)
                {
                    ne.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Only numeric values allowed",Toast.LENGTH_SHORT).show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"Oops! unexpected error occured",Toast.LENGTH_SHORT).show();
                }
            }
            else
                valid = false;


        }
        if(valid && key!=null)
        {
            SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_BULL_MORPHOLOGY_INFO, key, editVals);
            Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_SHORT).show();
        }

        else
            Toast.makeText(getApplicationContext(),"Could not save invalid data",Toast.LENGTH_SHORT).show();

    }
}
