package edu.ksu.cis.a4vm.bse;

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

public class Classification extends AppCompatActivity {

    ToggleButton tgbtn = null;
    ToggleButton tgbtn1 = null;
    ToggleButton tgbtn2 = null;
    ToggleButton tgbtn3 = null;

    EditText comments = null;

    Button save = null;

    private Set<EditText> fields = null;
    private Set<ToggleButton> tgBtns = null;

    private String bullKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classification);

        bullKey = getIntent().getStringExtra("bullKey");

        tgbtn = (ToggleButton)findViewById(R.id.classifyType1);
        tgbtn1 = (ToggleButton)findViewById(R.id.classifyType2);
        tgbtn2 = (ToggleButton)findViewById(R.id.classifyType3);
        tgbtn3 = (ToggleButton)findViewById(R.id.classifyType4);
        comments = (EditText) findViewById(R.id.classify2);
        save = (Button) findViewById(R.id.saveClassification);

        tgBtns = new LinkedHashSet<ToggleButton>();
        tgBtns.add(tgbtn);
        tgBtns.add(tgbtn1);
        tgBtns.add(tgbtn2);
        tgBtns.add(tgbtn3);

        fields = new LinkedHashSet<EditText>();
        fields.add(comments);


        tgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.changeToggleColor(tgbtn,getApplicationContext());
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
    }

    @Override
    public void onResume()
    {
        super.onResume();
        try{

            //display fields
            Util.setFields(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_CLASSIFICATION_INFO,
                    bullKey), fields);
            //display toggle buttons
            Util.setToggleButtons(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_CLASSIFICATION_INFO,
                    bullKey), tgBtns);

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(tgbtn.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                            tgbtn1.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                            tgbtn2.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent) ||
                            tgbtn3.getCurrentTextColor()==ContextCompat.getColor(getApplicationContext(), R.color.colorAccent))
                    {
                        LinkedHashSet<String> data = new LinkedHashSet<String>();
                        data.add(tgbtn.getText().toString().trim() + "=" + tgbtn.getCurrentTextColor());
                        data.add(tgbtn1.getText().toString().trim() + "=" + tgbtn1.getCurrentTextColor());
                        data.add(tgbtn2.getText().toString().trim() + "=" + tgbtn2.getCurrentTextColor());
                        data.add(tgbtn3.getText().toString().trim() + "=" + tgbtn3.getCurrentTextColor());
                        data.add(comments.getHint().toString().trim() + "=" + comments.getText().toString().trim());

                        //save to shared pref
                        SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_CLASSIFICATION_INFO, bullKey, data);

                        //display fields
                        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_CLASSIFICATION_INFO,
                                bullKey), fields);
                        //display toggle buttons
                        Util.setToggleButtons(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_CLASSIFICATION_INFO,
                                bullKey), tgBtns);

                        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Classification has to be selected!",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"Invalid data entered",Toast.LENGTH_SHORT).show();
        }
    }
}
