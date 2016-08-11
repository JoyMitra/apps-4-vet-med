package edu.ksu.cs.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class Motility extends AppCompatActivity {

    private ToggleButton tgbtn = null;
    private ToggleButton tgbtn1 = null;
    private ToggleButton tgbtn2 = null;
    private ToggleButton tgbtn3 = null;

    private EditText ringsDesc = null;
    private EditText scrotalDesc = null;

    private Button save = null;

    private Set<EditText> fields = null;
    private Set<ToggleButton> tgBtns = null;

    private String bullKey = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motility);

        bullKey = getIntent().getStringExtra("bullKey");

        tgbtn = (ToggleButton)findViewById(R.id.type1);
        tgbtn1 = (ToggleButton)findViewById(R.id.type2);
        tgbtn2 = (ToggleButton)findViewById(R.id.type3);
        tgbtn3 = (ToggleButton)findViewById(R.id.type4);

        ringsDesc = (EditText) findViewById(R.id.motility1);
        scrotalDesc = (EditText) findViewById(R.id.motility2);

        save = (Button) findViewById(R.id.saveMotility);

        tgBtns = new LinkedHashSet<ToggleButton>();
        tgBtns.add(tgbtn);
        tgBtns.add(tgbtn1);
        tgBtns.add(tgbtn2);
        tgBtns.add(tgbtn3);

        fields = new LinkedHashSet<EditText>();
        fields.add(ringsDesc);
        fields.add(scrotalDesc);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        try
        {
            //display fields
            Util.setFields(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MOTILITY_INFO,
                    bullKey), fields);
            //display toggle buttons
            Util.setToggleButtons(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MOTILITY_INFO,
                    bullKey), tgBtns);

            tgbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.changeToggleColor(tgbtn, getApplicationContext());
                    tgbtn1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                }
            });

            tgbtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.changeToggleColor(tgbtn1, getApplicationContext());
                    tgbtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                }
            });

            tgbtn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.changeToggleColor(tgbtn2, getApplicationContext());
                    tgbtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                }
            });

            tgbtn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Util.changeToggleColor(tgbtn3, getApplicationContext());
                    tgbtn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                    tgbtn2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                }
            });

            ringsDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String text = ringsDesc.getText().toString().trim();
                        try {
                            if (text.length()==0 || (Float.valueOf(text) >= 0.0 && Float.valueOf(text) <= 100.0)) {
                                ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            } else {
                                ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "Value must be between 0-100", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException ne) {
                            if(text.length()>0)
                            {
                                ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
                            }

                        }
                    }
                }
            });

            scrotalDesc.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String text = scrotalDesc.getText().toString().trim();
                        try {
                            if (text.length()==0 || (Float.valueOf(text) >= 0.0 && Float.valueOf(text) <= 100.0)) {
                                scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            } else {
                                scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "Value must be between 0-100", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException ne) {
                            if(text.length()>0)
                            {
                                scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
                            }

                        }
                    }
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (tgbtn1.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                            tgbtn2.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                            tgbtn3.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                            tgbtn.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                    {
                        LinkedHashSet<String> data = new LinkedHashSet<String>();
                        data.add(tgbtn.getText().toString().trim() + "=" + tgbtn.getCurrentTextColor());
                        data.add(tgbtn1.getText().toString().trim() + "=" + tgbtn1.getCurrentTextColor());
                        data.add(tgbtn2.getText().toString().trim() + "=" + tgbtn2.getCurrentTextColor());
                        data.add(tgbtn3.getText().toString().trim() + "=" + tgbtn3.getCurrentTextColor());
                        data.add(ringsDesc.getHint().toString().trim() + "=" + ringsDesc.getText().toString().trim().replace(",", ";"));
                        data.add(scrotalDesc.getHint().toString().trim() + "=" + scrotalDesc.getText().toString().trim().replace(",", ";"));

                        //save to shared pref
                        SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_MOTILITY_INFO, bullKey, data);

                        Intent goPrev = new Intent(getApplicationContext(), BullExam.class);
                        goPrev.putExtra("bullKey", bullKey);
                        startActivity(goPrev);

                        //display fields
                        /*Util.setFields(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MOTILITY_INFO,
                                bullKey), fields);
                        //display toggle buttons
                        Util.setToggleButtons(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MOTILITY_INFO,
                                bullKey), tgBtns);*/

                        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"App not responding due to invalid data entered",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), BullExam.class);
        goPrev.putExtra("bullKey", bullKey);
        startActivity(goPrev);
    }
}
