package edu.ksu.cis.a4vm.bse;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
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
    Double limit = 100.0;
    String key = null;
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
        fields = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_FILE_MORPH_INFO, Constant.KEY_MORPHOLOGY);
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
        et1.setText("Normal:" + NormalCount);
        row.addView(et1, cellLp);
        table.addView(row, rowLp);
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
                if(label.contains("Morphology Field 2"))
                {
                    et2 = new EditText(this);
                    if(text!=null && text.length==2)
                    {
                        et2.setText(text[1]+ ":"+ Lb2Count);
                    }
                    et2.setHeight(300);
                    row2.addView(et2);
                }
                else if(label.contains("Morphology Field 4"))
                {
                    et4 = new EditText(this);
                    if(text!=null && text.length==2)
                    {
                        et4.setText(text[1]+ ":"+ Lb4Count);
                    }
                    et4.setHeight(300);
                    row3.addView(et4);
                }
                else if(label.contains("Morphology Field 6"))
                {
                    et6 = new EditText(this);
                    if(text!=null && text.length==2)
                    {
                        et6.setText(text[1]+ ":"+ Lb6Count);
                    }
                    et6.setHeight(300);
                    row4.addView(et6);
                }
                else if(label.contains("Morphology Field 8"))
                {
                    et8 = new EditText(this);
                    if(text!=null && text.length==2)
                    {
                        et8.setText(text[1]+ ":"+ Lb8Count);
                    }
                    //btn = new EditText(this);
                    et8.setHeight(300);
                    row5.addView(et8);
                    //row5.addView(btn);
                }
            }

            it = fields.iterator();
            while(it.hasNext())
            {
                String label = it.next();
                String text[] = label.split("=");
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
                    if(text!=null && text.length==2)
                    {
                        et3.setText(text[1] + ":"+ Lb3Count);
                    }
                    et3.setHeight(300);
                    row2.addView(et3);
                }
                else if(label.contains("Morphology Field 5"))
                {
                    et5 = new EditText(this);
                    if(text!=null && text.length==2)
                    {
                        et5.setText(text[1]+ ":"+ Lb5Count);
                    }
                    et5.setHeight(300);
                    row3.addView(et5);
                }
                else if(label.contains("Morphology Field 7"))
                {
                    et7 = new EditText(this);
                    if(text!=null && text.length==2)
                    {
                        et7.setText(text[1]+ ":"+ Lb7Count);
                    }
                    et7.setHeight(300);
                    row4.addView(et7);
                }

            }


            table.addView(row2,rowLp);
            table.addView(row3,rowLp);
            table.addView(row4,rowLp);
            table.addView(row5,rowLp);

        }
        
        et1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et1.getText().toString().trim();
                if(entry.contains(":") && entry.split(":").length==2)
                {
                    String[] texts = entry.split(":");
                    NormalCount = Integer.valueOf(texts[1]);
                    if(NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit)
                    {
                        Toast.makeText(getApplicationContext(),"Total count cannot be greater than set limit",Toast.LENGTH_SHORT).show();
                        valid = false;
                    }
                    else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }
        });

        et2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et2.getText().toString().trim();
                if(entry.contains(":") && entry.split(":").length==2)
                {
                    String[] texts = entry.split(":");
                    Lb2Count = Integer.valueOf(texts[1]);
                    if(NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit)
                    {
                        Toast.makeText(getApplicationContext(),"Total count cannot be greater than set limit",Toast.LENGTH_SHORT).show();
                        valid = false;
                    }
                    else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }
        });

        et3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et3.getText().toString().trim();
                if (entry.contains(":") && entry.split(":").length == 2) {
                    String[] texts = entry.split(":");
                    Lb3Count = Integer.valueOf(texts[1]);
                    if (NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                        Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                        valid = false;
                    } else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }
        });

        et4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et4.getText().toString().trim();
                if (entry.contains(":") && entry.split(":").length == 2) {
                    String[] texts = entry.split(":");
                    Lb4Count = Integer.valueOf(texts[1]);
                    if (NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                        Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                        valid = false;
                    } else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }
        });

        et5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et5.getText().toString().trim();
                if (entry.contains(":") && entry.split(":").length == 2) {
                    String[] texts = entry.split(":");
                    Lb5Count = Integer.valueOf(texts[1]);
                    if (NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                        Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                        valid = false;
                    } else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }

            }
        });

        et6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et6.getText().toString().trim();
                if (entry.contains(":") && entry.split(":").length == 2) {
                    String[] texts = entry.split(":");
                    Lb6Count = Integer.valueOf(texts[1]);
                    if (NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit) {
                        Toast.makeText(getApplicationContext(), "Total count cannot be greater than set limit", Toast.LENGTH_SHORT).show();
                        valid = false;
                    } else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }
        });

        et7.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String entry = et7.getText().toString().trim();
                if(entry.contains(":") && entry.split(":").length==2)
                {
                    String[] texts = entry.split(":");
                    Lb7Count = Integer.valueOf(texts[1]);
                    if(NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit)
                    {
                        Toast.makeText(getApplicationContext(),"Total count cannot be greater than set limit",Toast.LENGTH_SHORT).show();
                        valid = false;
                    }
                    else
                        valid = true;

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                    valid = false;
                }
            }
        });

        et8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                try{
                    String entry = et8.getText().toString().trim();
                    if(entry.contains(":") && entry.split(":").length==2)
                    {
                        String[] texts = entry.split(":");
                        Lb8Count = Integer.valueOf(texts[1]);
                        if(NormalCount + Lb2Count + Lb3Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count > limit)
                        {
                            Toast.makeText(getApplicationContext(),"Total count cannot be greater than set limit",Toast.LENGTH_SHORT).show();
                            valid = false;
                        }
                        else
                            valid = true;

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Invalid input! edit only value",Toast.LENGTH_SHORT).show();
                        valid = false;
                    }
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"App failed!",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


    @Override
    public void onPause()
    {
        super.onPause();
        HashSet<String> editVals = new HashSet<String>();
        if(et1!=null)
        {
            if(et1.getText().toString().trim().contains(":") && et1.getText().toString().trim().split(":").length==2)
            {
                String[] text = et1.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et1.getText().toString().trim().replace(":","=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(et2!=null)
        {
            if(et2.getText().toString().trim().contains(":") && et2.getText().toString().trim().split(":").length==2)
            {
                String[] text = et2.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et2.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;

        }

        if(et3!=null)
        {
            if(et3.getText().toString().trim().contains(":") && et3.getText().toString().trim().split(":").length==2)
            {
                String[] text = et3.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et3.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(et4!=null)
        {
            if(et4.getText().toString().trim().contains(":") && et4.getText().toString().trim().split(":").length==2)
            {
                String[] text = et4.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et4.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(et5!=null)
        {
            if(et5.getText().toString().trim().contains(":") && et5.getText().toString().trim().split(":").length==2)
            {
                String[] text = et5.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et5.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(et6!=null)
        {
            if(et6.getText().toString().trim().contains(":") && et6.getText().toString().trim().split(":").length==2)
            {
                String[] text = et6.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et6.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(et7!=null)
        {
            if(et7.getText().toString().trim().contains(":") && et7.getText().toString().trim().split(":").length==2)
            {
                String[] text = et7.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et7.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(et8!=null)
        {
            if(et8.getText().toString().trim().contains(":") && et8.getText().toString().trim().split(":").length==2)
            {
                String[] text = et8.getText().toString().trim().split(":");
                Double count = Double.valueOf(text[1]);
                Double prop = (count/limit)*100.0;
                editVals.add(et8.getText().toString().trim().replace(":", "=") + "(" + String.format("%.2f",prop) + "%)");
            }
            else
                valid = false;


        }
        if(valid && key!=null)
            SharedPrefUtil.saveGroup(getApplicationContext(),Constant.PREFS_BULL_MORPHOLOGY_INFO,key,editVals);
        else
            Toast.makeText(getApplicationContext(),"Could not save invalid data",Toast.LENGTH_SHORT).show();

    }
}
