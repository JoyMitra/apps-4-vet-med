package edu.ksu.cis.a4vm.bse;

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

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class MatingInfo extends AppCompatActivity {

    private ToggleButton tgbtn = null;
    private ToggleButton tgbtn1 = null;
    private ToggleButton tgbtn2 = null;
    private ToggleButton tgbtn3 = null;
    private ToggleButton sire1Btn = null;
    private ToggleButton sire2Btn = null;
    private ToggleButton sire3Btn = null;
    private EditText breedSeason = null;
    private EditText perfDesc = null;
    private EditText comments = null;
    private Button save = null;

    private Set<EditText> fields = null;
    private Set<ToggleButton> tgBtns = null;

    public String bullKey = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mating_info);

        bullKey = getIntent().getStringExtra("bullKey");

        tgbtn = (ToggleButton)findViewById(R.id.perfGood);
        tgbtn1 = (ToggleButton)findViewById(R.id.perfBad);
        tgbtn2 = (ToggleButton)findViewById(R.id.perfOther);
        tgbtn3 = (ToggleButton)findViewById(R.id.perfUnkown);

        breedSeason = (EditText) findViewById(R.id.mateSeason);
        perfDesc = (EditText) findViewById(R.id.perfDesc);
        comments = (EditText) findViewById(R.id.comments);

        sire1Btn = (ToggleButton)findViewById(R.id.singleSire);
        sire2Btn = (ToggleButton)findViewById(R.id.multiSire);
        sire3Btn = (ToggleButton)findViewById(R.id.NotUsedSire);

        save = (Button) findViewById(R.id.saveMatingInfo);


        fields = new LinkedHashSet<EditText>();
        fields.add(breedSeason);
        fields.add(perfDesc);
        fields.add(comments);

        tgBtns = new LinkedHashSet<ToggleButton>();
        tgBtns.add(tgbtn);
        tgBtns.add(tgbtn1);
        tgBtns.add(tgbtn2);
        tgBtns.add(tgbtn3);
        tgBtns.add(sire1Btn);
        tgBtns.add(sire2Btn);
        tgBtns.add(sire3Btn);

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

        sire1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.changeToggleColor(sire1Btn, getApplicationContext());
                sire2Btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                sire3Btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
            }
        });

        sire2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.changeToggleColor(sire2Btn, getApplicationContext());
                sire1Btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                sire3Btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
            }
        });

        sire3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.changeToggleColor(sire3Btn, getApplicationContext());
                sire1Btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
                sire2Btn.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.lightBlue));
            }
        });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //load data
        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MATING_INFO,
                bullKey), fields);
        //display toggle buttons
        Util.setToggleButtons(SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_MATING_INFO,
                bullKey),tgBtns);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),tgbtn.getText().toString().trim() + bullKey,Toast.LENGTH_SHORT).show();
                if (tgbtn.getCurrentTextColor() == ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                        tgbtn1.getCurrentTextColor() == ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                        tgbtn2.getCurrentTextColor() == ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                        tgbtn3.getCurrentTextColor() == ContextCompat.getColor(getApplicationContext(), R.color.colorAccent)) {
                    try {
                        if (breedSeason.getText().toString().trim().length() == 0 ||
                                (Integer.parseInt(breedSeason.getText().toString().trim()) > 0 &&
                                        Integer.parseInt(breedSeason.getText().toString().trim()) < 31)) {
                            LinkedHashSet<String> data = new LinkedHashSet<String>();
                            data.add(tgbtn.getText().toString().trim() + "=" + tgbtn.getCurrentTextColor());
                            data.add(tgbtn1.getText().toString().trim() + "=" + tgbtn1.getCurrentTextColor());
                            data.add(tgbtn2.getText().toString().trim() + "=" + tgbtn2.getCurrentTextColor());
                            data.add(tgbtn3.getText().toString().trim() + "=" + tgbtn3.getCurrentTextColor());
                            data.add(breedSeason.getHint().toString().trim() + "=" + breedSeason.getText().toString().trim());
                            data.add(perfDesc.getHint().toString().trim() + "=" + perfDesc.getText().toString().trim().replace(",", ";"));
                            data.add(comments.getHint().toString().trim() + "=" + comments.getText().toString().trim().replace(",", ";"));
                            data.add(sire1Btn.getText().toString().trim() + "=" + sire1Btn.getCurrentTextColor());
                            data.add(sire2Btn.getText().toString().trim() + "=" + sire2Btn.getCurrentTextColor());
                            data.add(sire3Btn.getText().toString().trim() + "=" + sire3Btn.getCurrentTextColor());

                            //save to shared pref
                            SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_MATING_INFO, bullKey, data);

                            Intent goPrev = new Intent(getApplicationContext(), BullExam.class);
                            goPrev.putExtra("bullKey", bullKey);
                            startActivity(goPrev);



                            Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Breed Season should be 1-30", Toast.LENGTH_LONG).show();
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        }
                    } catch (NumberFormatException ne) {
                        if (breedSeason.getText().toString().trim().length() > 0) {
                            Toast.makeText(getApplicationContext(), "Breed Season should be 1-30", Toast.LENGTH_SHORT).show();
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        } else {
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
                        }

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "Invalid information entered", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(getApplicationContext(), "Performance last season must be specified", Toast.LENGTH_LONG).show();
                }
            }
        });

        breedSeason.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String text = breedSeason.getText().toString().trim();
                    try {
                        if (Integer.valueOf(text) > 0 && Integer.valueOf(text) < 31) {
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        } else {
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Breed Season should be 1-30", Toast.LENGTH_SHORT).show();
                        }
                    } catch (NumberFormatException ne) {

                        if (text.length() > 0) {
                            ne.printStackTrace();
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Invalid breed season", Toast.LENGTH_SHORT).show();
                        } else {
                            breedSeason.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
                        }

                    }
                }
            }
        });

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
