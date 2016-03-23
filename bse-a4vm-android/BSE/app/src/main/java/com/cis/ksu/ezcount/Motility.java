package com.cis.ksu.ezcount;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.util.LinkedHashSet;
import java.util.Set;

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
                            if (text.length()==0 || (Float.valueOf(text) >= 0.0 && Float.valueOf(text) < 100.0)) {
                                ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            } else {
                                ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "Value must be between 0-100", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException ne) {
                            ringsDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
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
                            if (text.length()==0 || (Float.valueOf(text) >= 0.0 && Float.valueOf(text) < 100.0)) {
                                scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            } else {
                                scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "Value must be between 0-100", Toast.LENGTH_SHORT).show();
                            }
                        } catch (NumberFormatException ne) {
                            scrotalDesc.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Invalid entry", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });

            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ringsDesc.getText().toString().trim().length() > 0
                            && scrotalDesc.getText().toString().trim().length() > 0) {
                        LinkedHashSet<String> data = new LinkedHashSet<String>();
                        data.add(tgbtn.getText().toString().trim() + "=" + tgbtn.getCurrentTextColor());
                        data.add(tgbtn1.getText().toString().trim() + "=" + tgbtn1.getCurrentTextColor());
                        data.add(tgbtn2.getText().toString().trim() + "=" + tgbtn2.getCurrentTextColor());
                        data.add(tgbtn3.getText().toString().trim() + "=" + tgbtn3.getCurrentTextColor());
                        data.add(ringsDesc.getHint().toString().trim() + "=" + ringsDesc.getText().toString().trim());
                        data.add(scrotalDesc.getHint().toString().trim() + "=" + scrotalDesc.getText().toString().trim());

                        //save to shared pref
                        SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_MOTILITY_INFO, bullKey, data);

                        //display fields
                        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MOTILITY_INFO,
                                bullKey), fields);
                        //display toggle buttons
                        Util.setToggleButtons(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MOTILITY_INFO,
                                bullKey), tgBtns);

                        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Nothing to save", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        catch(Exception e)
        {
            Toast.makeText(getApplicationContext(),"App not responding due to invalid data entered",Toast.LENGTH_SHORT).show();
        }


    }
}
