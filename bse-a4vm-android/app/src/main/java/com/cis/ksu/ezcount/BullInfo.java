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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class BullInfo extends AppCompatActivity {


    EditText idTag = null;
    EditText idTattoo = null;
    EditText idRfid = null;
    EditText idBrand = null;
    EditText dob = null;
    EditText ageYrs = null;
    EditText ageMths = null;
    EditText lot = null;
    EditText breed = null;
    EditText comments = null;
    Button save = null;
    Set<EditText> fields = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bull_info);

        idTag = (EditText) findViewById(R.id.IdTag);
        idTattoo = (EditText) findViewById(R.id.IdTattoo);
        idRfid = (EditText) findViewById(R.id.IdRfid);
        idBrand = (EditText) findViewById(R.id.IdBrand);
        dob = (EditText) findViewById(R.id.date);
        ageYrs = (EditText) findViewById(R.id.ageYrs);
        ageMths = (EditText) findViewById(R.id.ageMths);
        lot = (EditText) findViewById(R.id.lot);
        breed = (EditText) findViewById(R.id.breed);
        comments = (EditText) findViewById(R.id.comm);
        save = (Button) findViewById(R.id.saveBullInfo);

        fields = new HashSet<EditText>();
        fields.add(idTag);
        fields.add(idTattoo);
        fields.add(idRfid);
        fields.add(idBrand);
        fields.add(dob);
        fields.add(ageYrs);
        fields.add(ageMths);
        fields.add(lot);
        fields.add(breed);
        fields.add(comments);
    }

    public void onResume()
    {
        super.onResume();
        final String bullKey = getIntent().getStringExtra("bullKey");

        //load data
        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_BULL_INFO, bullKey), fields);
        //Toast.makeText(getApplicationContext(),getIntent().getStringExtra("bullKey"),Toast.LENGTH_LONG).show();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if (idTag.getText().toString().trim().length() > 0
                            || idTattoo.getText().toString().trim().length() > 0
                            || idRfid.getText().toString().trim().length() > 0
                            || idBrand.getText().toString().trim().length() > 0) {
                        if (Integer.valueOf(ageYrs.getText().toString().trim()) <= 25 ||
                                Integer.valueOf(ageMths.getText().toString().trim()) <= 11) {
                            //save bull info
                            HashSet<String> data = new LinkedHashSet<String>();
                            data.add(idTag.getHint().toString().trim() + "=" + idTag.getText().toString().trim());
                            data.add(idTattoo.getHint().toString().trim() + "=" + idTattoo.getText().toString().trim());
                            data.add(idRfid.getHint().toString().trim() + "=" + idRfid.getText().toString().trim());
                            data.add(idBrand.getHint().toString().trim() + "=" + idBrand.getText().toString().trim());
                            data.add(dob.getHint().toString().trim() + "=" + dob.getText().toString().trim());
                            data.add(ageYrs.getHint().toString().trim() + "=" + ageYrs.getText().toString().trim());
                            data.add(ageMths.getHint().toString().trim() + "=" + ageMths.getText().toString().trim());
                            data.add(lot.getHint().toString().trim() + "=" + lot.getText().toString().trim());
                            data.add(breed.getHint().toString().trim() + "=" + breed.getText().toString().trim());
                            data.add(comments.getHint().toString().trim() + "=" + comments.getText().toString().trim());

                            //capture timestamp
                            Date cDate = new Date();
                            String fDate = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss").format(cDate);

                            data.add("TimeStamp=" + fDate);

                            //persist VetInfo
                            if (bullKey != null) {
                                //saving key
                                final Set<String> keySet = SharedPrefUtil.getValue(getApplicationContext(),
                                        Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                                /*
                                creating a copy of keySet because if sets retrieved from a shared pref file
                                is modified, it could lead to unexpected behavior.
                                 */
                                Set<String> keySet1 = null;
                                if (keySet != null) {
                                    keySet1 = new HashSet<String>();
                                    Iterator<String> it = keySet.iterator();
                                    while(it.hasNext())
                                    {
                                        keySet1.add(it.next());
                                    }
                                    if (!keySet1.contains(bullKey)) {
                                        keySet1.add(bullKey);
                                    }
                                } else {
                                    keySet1 = new HashSet<String>();
                                    keySet1.add(bullKey);

                                }

                                if(keySet1!=null && bullKey!=null)
                                {
                                    SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_BULL_INFO,
                                            Constant.KEY_BULL, keySet1);

                                    //saving data
                                    SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_BULL_INFO, bullKey, data);
                                    Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_SHORT).show();

                                    //display
                                    Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                                            Constant.PREFS_BULL_INFO, bullKey), fields);
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Oops! could not save due to internal app error.", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Oops! something went wrong. Please try again", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(getApplicationContext(), "Date or DOB not filled correctly", Toast.LENGTH_LONG).show();
                            ageYrs.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            ageMths.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "At least one ID field should be filled", Toast.LENGTH_LONG).show();
                        idTag.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        idTattoo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        idRfid.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        idBrand.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));


                    }
                }
                catch(NumberFormatException e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Please correct the fields marked red", Toast.LENGTH_LONG).show();
                    ageYrs.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                    ageMths.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                    dob.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                }
            }
        });

        dob.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String birthDt = dob.getText().toString().trim();
                    if (birthDt.length() > 0) {
                        String[] arrBirthDt = birthDt.split("-");
                        try {
                            if (arrBirthDt != null && arrBirthDt.length == 3) {
                                int todayYr = Calendar.getInstance().get(Calendar.YEAR);
                                int todayMth = Calendar.getInstance().get(Calendar.MONTH) + 1;
                                int birthYr = Integer.valueOf(arrBirthDt[0]);
                                int birthMth = Integer.valueOf(arrBirthDt[1]);
                                int birthDay = Integer.valueOf((arrBirthDt[2]));
                                int ageY = 0;
                                int ageM = 0;


                                if (todayYr >= birthYr) {
                                    ageY = todayYr - birthYr;
                                    if (todayMth >= birthMth)
                                        ageM = todayMth - birthMth;
                                    else {
                                        ageM = birthMth - todayMth;
                                        ageY = ageY - 1;
                                    }
                                }
                                if (ageYrs != null && ageMths != null && ageY <= 25 && ageM < 12
                                        && birthDay <= Util.get_days_of_a_month(birthMth,birthYr)) {
                                    ageYrs.setText(String.valueOf(ageY));
                                    ageMths.setText(String.valueOf(ageM));
                                    dob.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                } else {
                                    Toast.makeText(getApplicationContext(), "Invalid DOB", Toast.LENGTH_SHORT).show();
                                    dob.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                }

                            } else {
                                Toast.makeText(getApplicationContext(), "Incorrect date format", Toast.LENGTH_SHORT).show();
                                dob.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            }

                        } catch (NumberFormatException ne) {
                            ne.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Birth date cannot have non-numbers", Toast.LENGTH_SHORT).show();
                            dob.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        } catch (Exception pe) {
                            pe.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Incorrect birth date", Toast.LENGTH_SHORT).show();
                            dob.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        }
                    }
                }
            }
        });

        ageYrs.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String year = ageYrs.getText().toString().trim();
                    try {
                        if (Integer.valueOf(year) > 25) {
                            ageYrs.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Max age is 25", Toast.LENGTH_SHORT).show();
                        } else
                            ageYrs.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    } catch (NumberFormatException ne) {
                        ageYrs.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Age cannot be non-number", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        ageYrs.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Invalid age", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        ageMths.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String year = ageMths.getText().toString().trim();
                    try {
                        if (Integer.valueOf(year) > 11) {
                            ageMths.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Month cannot be more than 11", Toast.LENGTH_SHORT).show();
                        } else
                            ageMths.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    } catch (NumberFormatException ne) {
                        ageMths.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Age cannot be non-number", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        ageMths.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Invalid age", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        idTag.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (idTag.getText().toString().trim().length() > 0) {
                        idTag.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idTattoo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idRfid.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idBrand.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    }
                }
            }
        });

        idTattoo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (idTattoo.getText().toString().trim().length() > 0)
                    {
                        idTag.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idTattoo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idRfid.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idBrand.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    }
                }
            }
        });

        idRfid.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (idRfid.getText().toString().trim().length() > 0) {
                        idTag.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idTattoo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idRfid.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idBrand.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    }
                }
            }
        });

        idBrand.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (idBrand.getText().toString().trim().length() > 0) {
                        idTag.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idTattoo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idRfid.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        idBrand.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    }
                }
            }
        });


    }
}
