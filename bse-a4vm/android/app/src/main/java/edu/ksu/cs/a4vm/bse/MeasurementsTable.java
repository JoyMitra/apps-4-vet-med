/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */
package edu.ksu.cs.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;


public class MeasurementsTable extends AppCompatActivity {

    private EditText field1 = null;
    private EditText field2 = null;
    private EditText field3 = null;
    private EditText field4 = null;
    private EditText field5 = null;
    private EditText field6 = null;
    private EditText field7 = null;
    private EditText field8 = null;

    /*private CheckBox check1 = null;
    private CheckBox check2 = null;
    private CheckBox check3 = null;
    private CheckBox check4 = null;
    private CheckBox check5 = null;
    private CheckBox check6 = null;
    private CheckBox check7 = null;
    private CheckBox check8 = null;
    private CheckBox check9 = null;*/

    private Button saveBtn = null;

    private Set<EditText> fields = null;
    private Set<CheckBox> checks = null;

    String bullKey = null;

    public boolean valid1 = true;
    public boolean valid2 = true;
    public boolean valid3 = true;
    public boolean valid4 = true;
    public boolean valid5 = true;
    public boolean valid6 = true;
    public boolean valid8 = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measurements_table);

        field1 = (EditText) findViewById(R.id.field1);
        field2 = (EditText) findViewById(R.id.field2);
        field3 = (EditText) findViewById(R.id.field3);
        field4 = (EditText) findViewById(R.id.field4);
        field5 = (EditText) findViewById(R.id.field5);
        field6 = (EditText) findViewById(R.id.field6);
        field7 = (EditText) findViewById(R.id.field7);
        field8 = (EditText) findViewById(R.id.field8);

        fields = new LinkedHashSet<EditText>();
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);

        bullKey = getIntent().getStringExtra("bullKey");

        /*check1 = (CheckBox) findViewById(R.id.checkbox1);
        check2 = (CheckBox) findViewById(R.id.checkbox2);
        check3 = (CheckBox) findViewById(R.id.checkbox3);
        check4 = (CheckBox) findViewById(R.id.checkbox4);
        check5 = (CheckBox) findViewById(R.id.checkbox5);
        check6 = (CheckBox) findViewById(R.id.checkbox6);
        check7 = (CheckBox) findViewById(R.id.checkbox7);
        check8 = (CheckBox) findViewById(R.id.checkbox8);
        check9 = (CheckBox) findViewById(R.id.checkbox9);

        checks = new LinkedHashSet<CheckBox>();
        checks.add(check1);
        checks.add(check2);
        checks.add(check3);
        checks.add(check4);
        checks.add(check5);
        checks.add(check6);
        checks.add(check7);
        checks.add(check8);
        checks.add(check9);*/

        saveBtn = (Button) findViewById(R.id.saveMeasurement);
    }

    @Override
    public void onResume()
    {
        super.onResume();


        //load data
        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey), fields);
        Util.setCheckBoxes(SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey), checks);
        /*check9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Util.setAllCheckBoxes(checks, true);
                }
                else
                {
                    Util.setAllCheckBoxes(checks, false);
                }
            }
        });*/

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid1 && valid2 && valid3 && valid4 && valid5 && valid6 && valid8) {
                    LinkedHashSet<String> data = new LinkedHashSet<String>();
                    String text = field1.getText().toString().trim();
                    if(text.length() > 0 && text.contains("."))
                    {
                        String[] txtparts = text.split("\\.");
                        if(txtparts!=null && txtparts[1].length()>1)
                        {
                            text = text.substring(0,text.indexOf(".")+2);
                        }

                    }
                    data.add(field1.getHint().toString().trim() + "=" + text.replace(",", ";"));
                    data.add(field2.getHint().toString().trim() + "=" + field2.getText().toString().trim().replace(",", ";"));
                    data.add(field3.getHint().toString().trim() + "=" + field3.getText().toString().trim().replace(",", ";"));
                    data.add(field4.getHint().toString().trim() + "=" + field4.getText().toString().trim().replace(",", ";"));
                    data.add(field5.getHint().toString().trim() + "=" + field5.getText().toString().trim().replace(",", ";"));
                    data.add(field6.getHint().toString().trim() + "=" + field6.getText().toString().trim().replace(",", ";"));
                    data.add(field7.getHint().toString().trim() + "=" + field7.getText().toString().trim().replace(",", ";"));
                    data.add(field8.getHint().toString().trim() + "=" + field8.getText().toString().trim().replace(",", ";"));
                /*if (check1.isChecked())
                    data.add(Integer.toString(check1.getId()).trim() + "=" + field1.getHint().toString().trim());
                if (check2.isChecked())
                    data.add(Integer.toString(check2.getId()).trim() + "=" + field2.getHint().toString().trim());
                if (check3.isChecked())
                    data.add(Integer.toString(check3.getId()).trim() + "=" + field3.getHint().toString().trim());
                if (check4.isChecked())
                    data.add(Integer.toString(check4.getId()).trim() + "=" + field4.getHint().toString().trim());
                if (check5.isChecked())
                    data.add(Integer.toString(check5.getId()).trim() + "=" + field5.getHint().toString().trim());
                if (check6.isChecked())
                    data.add(Integer.toString(check6.getId()).trim() + "=" + field6.getHint().toString().trim());
                if (check7.isChecked())
                    data.add(Integer.toString(check7.getId()).trim() + "=" + field7.getHint().toString().trim());
                if (check8.isChecked())
                    data.add(Integer.toString(check8.getId()).trim() + "=" + field8.getHint().toString().trim());
                if (check9.isChecked())
                    data.add(Integer.toString(check9.getId()).trim() + "=" + "true");*/

                    //save to file
                    SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey, data);

                    Intent goPrev = new Intent(getApplicationContext(), PhysicalParameter.class);
                    goPrev.putExtra("bullKey", bullKey);
                    startActivity(goPrev);

                    //display
                    /*Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                            Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey), fields);
                    Util.setCheckBoxes(SharedPrefUtil.getValue(getApplicationContext(),
                            Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey), checks);*/

                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Correct the fields highlighted red", Toast.LENGTH_SHORT).show();
                }

            }
        });

        field1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field1.getText().toString().trim();
                    try {
                        if (text.length() == 0 || (Float.valueOf(text) >= 0.0 && Float.valueOf(text) < 61.0)) {
                            field1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            valid1 = true;
                        }

                        else {
                            field1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid1 = false;
                            Toast.makeText(getApplicationContext(), "Value must be between 0-60", Toast.LENGTH_SHORT).show();
                        }
                        /*if(valid1)
                        {
                            if(text.length() > 0 && text.contains("."))
                            {
                                String[] txtparts = text.split("\\.");
                                if(txtparts!=null && txtparts[1].length()>1)
                                {
                                    field1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                    valid1 = false;
                                    Toast.makeText(getApplicationContext(), "Precision more than 1 decimal place not allowed", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }*/

                    } catch (NumberFormatException ne) {
                        field1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid1 = false;
                        Toast.makeText(getApplicationContext(), "Number expected", Toast.LENGTH_SHORT).show();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.toString() + " Unexpected error due to invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        field2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field2.getText().toString().trim();
                    try {
                        if (text.length() == 0 || (Float.valueOf(text) >= 0.0 && Float.valueOf(text) < 10.0)) {
                            field2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            valid2 = true;
                        } else {
                            field2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid2 = false;
                            Toast.makeText(getApplicationContext(), "Value must be between 0-9", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException ne) {
                        field2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid2 = false;
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        field3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field3.getText().toString().trim();
                    try {
                        if (text.length() == 0 || (Float.valueOf(text) >= 8.0 && Float.valueOf(text) < 31.0)) {
                            field3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            valid3 = true;
                        } else {
                            field3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid3 = false;
                            Toast.makeText(getApplicationContext(), "Value must be between 8-30", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException ne) {
                        field3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid3 = false;
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        field4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field4.getText().toString().trim();
                    try {
                        if (text.length() == 0 || (Float.valueOf(text) >= 8.0 && Float.valueOf(text) < 31.0)) {
                            field4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            valid4 = true;
                        } else {
                            field4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid4 = false;
                            Toast.makeText(getApplicationContext(), "Value must be between 8-30", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException ne) {
                        field4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid4 = false;
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        field5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field5.getText().toString().trim();
                    try {
                        if (text.length() == 0 || (Float.valueOf(text) > 0.0 && Float.valueOf(text) < 21.0)) {
                            field5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            valid5 = true;
                        } else {
                            field5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid5 = false;
                            Toast.makeText(getApplicationContext(), "Value must be between 1-20", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException ne) {
                        field5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid5 = false;
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        field6.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field6.getText().toString().trim();
                    try {
                        if (text.length() == 0 || Float.valueOf(text) >= 0.0) {
                            field6.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            valid6 = true;
                        } else {
                            field6.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid6 = false;
                            Toast.makeText(getApplicationContext(), "Value must positive", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException ne) {
                        field6.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid6 = false;
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        field8.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = field8.getText().toString().trim();
                    try {
                        if (text.length() == 0 || text.equalsIgnoreCase("cm") || text.equalsIgnoreCase("inches")) {
                            valid8 = true;
                            field8.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        } else {
                            field8.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            valid8 = false;
                            Toast.makeText(getApplicationContext(), "cm or inches allowed", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        field8.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        valid8 = false;
                        Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }

    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), PhysicalParameter.class);
        goPrev.putExtra("bullKey", bullKey);
        startActivity(goPrev);
    }
}
