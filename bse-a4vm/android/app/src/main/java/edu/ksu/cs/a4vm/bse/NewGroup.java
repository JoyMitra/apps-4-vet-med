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
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;


public class NewGroup extends AppCompatActivity {

    private EditText ranchName = null;
    private EditText rancherName = null;
    private EditText email = null;
    private EditText address1 = null;
    private EditText address2 = null;
    private EditText city = null;
    private EditText state = null;
    private EditText zip = null;
    private EditText phone = null;
    private Button btn = null;
    HashSet<EditText> fields = null;

    String key = null;
    Boolean validateRanch = false;
    Boolean validatelRancher = false;
    Boolean validatePhone = false;
    Boolean validateAddr1 = false;
    Boolean validateCity = false;
    Boolean validateState = false;
    Boolean validateZip = false;
    Boolean validateEmail = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);

        btn = (Button) findViewById(R.id.save);
        ranchName = (EditText) findViewById(R.id.rName);
        rancherName = (EditText) findViewById(R.id.rnName);
        email = (EditText) findViewById(R.id.rancherEmail);
        address1 = (EditText) findViewById(R.id.addr1);
        address2 = (EditText) findViewById(R.id.addr2);
        city = (EditText) findViewById(R.id.city);
        state = (EditText) findViewById(R.id.state);
        zip = (EditText) findViewById(R.id.zip);
        phone = (EditText) findViewById(R.id.phone);
        key = getIntent().getStringExtra("grpKey");


        fields = new HashSet<EditText>();
        fields.add(ranchName);
        fields.add(rancherName);
        fields.add(email);
        fields.add(address1);
        fields.add(address2);
        fields.add(city);
        fields.add(state);
        fields.add(zip);
        fields.add(phone);


    }

    @Override
    public void onResume()
    {
        super.onResume();

        //display
        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_GROUP_INFO, key), fields);

        ranchName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (ranchName.getText().toString().trim().length() >= 0) {
                        ranchName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validateRanch = false;
                    } else {
                        validateRanch = true;
                        ranchName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Ranch name cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        rancherName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (rancherName.getText().toString().trim().length() >= 0) {
                        validatelRancher = false;
                        rancherName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    } else {
                        validatelRancher = true;
                        rancherName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Rancher name cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        address1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (address1.getText().toString().trim().length() >= 0) {
                        address1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validateAddr1 = false;
                    }
                    else {
                        validateAddr1 = true;
                        address1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Address1 cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (city.getText().toString().trim().length() >= 0) {
                        city.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validateCity = false;
                    } else {
                        validateCity = true;
                        city.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "city cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        state.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                           @Override
                                           public void onFocusChange(View v, boolean hasFocus) {
                                               if (!hasFocus) {
                                                   try {
                                                       int text = Integer.valueOf(state.getText().toString());
                                                       float text1 = Float.valueOf(state.getText().toString());
                                                       state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                       validateState = true;
                                                   } catch (NumberFormatException ne) {
                                                       if (state.getText().toString().trim().length()==0 || state.getText().toString().trim().length() == 2) {
                                                           state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                           validateState = false;
                                                       }
                                                       else {
                                                           validateState = true;
                                                           state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           Toast.makeText(getApplicationContext(), "State not valid", Toast.LENGTH_SHORT).show();
                                                       }
                                                   }
                                               }


                                           }
                                       }

        );

        zip.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                     {
                                         @Override
                                         public void onFocusChange(View v, boolean hasFocus) {
                                             try {
                                                 if (zip.getText().toString().trim().length() == 5 &&
                                                         Integer.valueOf(zip.getText().toString().trim()) > 0) {
                                                     zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                     validateZip = false;
                                                 }
                                                 else if(zip.getText().toString().trim().length() == 0)
                                                 {
                                                     zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                     validateZip = false;
                                                 }
                                                 else {
                                                     zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                     validateZip = true;
                                                     Toast.makeText(getApplicationContext(), "Zip must be a 5 digit number", Toast.LENGTH_SHORT).show();
                                                 }
                                             } catch (NumberFormatException ne) {
                                                 validateZip = true;
                                                 zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                 Toast.makeText(getApplicationContext(), "Zip must be a 5 digit number", Toast.LENGTH_SHORT).show();
                                             }
                                         }
                                     }

        );

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (phone.getText().toString().trim().length() == 10) {
                        String phn = phone.getText().toString().trim();
                        Pattern pattern = Pattern.compile("^[0-9]+$");
                        Matcher matcher = pattern.matcher(phn);
                        if (matcher.find()) {
                            phone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            validatePhone = false;
                        } else {
                            validatePhone = true;
                            phone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "invalid phone", Toast.LENGTH_SHORT).show();
                        }

                    }
                    else if(phone.getText().toString().trim().length() == 0)
                    {
                        phone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validatePhone = false;
                    }
                    else {
                        validatePhone = true;
                        phone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "invalid phone", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                       {
                                           @Override
                                           public void onFocusChange(View v, boolean hasFocus) {
                                               if (Util.isEmailValid(email.getText().toString().trim())) {
                                                   email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                   validateEmail = false;
                                               } else {
                                                   validateEmail = true;
                                                   email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                   Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       }

        );


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                if (!validateRanch && !validatelRancher
                        && !validateAddr1 && !validateCity
                        && !validateState && !validatePhone
                        && !validateZip && !validateEmail) {
                    if(ranchName.getText().toString().trim().length()>0 || rancherName.getText().toString().trim().length()>0
                            /*&& email.getText().toString().trim().length()>0 && address1.getText().toString().trim().length()>0
                            && city.getText().toString().trim().length()>0
                            && state.getText().toString().trim().length()>0 && zip.getText().toString().trim().length()>0
                            && zip.getText().toString().trim().length()>0 && phone.getText().toString().trim().length()>0*/)
                    {
                        HashSet<String> data = new LinkedHashSet<String>();
                        data.add(ranchName.getHint().toString().trim() + "=" + ranchName.getText().toString().trim().replace(",",";"));
                        data.add(rancherName.getHint().toString().trim() + "=" + rancherName.getText().toString().trim().replace(",", ";"));
                        data.add(email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";"));
                        data.add(address1.getHint().toString().trim() + "=" + address1.getText().toString().trim().replace(",", ";"));
                        data.add(address2.getHint().toString().trim() + "=" + address2.getText().toString().trim().replace(",", ";"));
                        data.add(city.getHint().toString().trim() + "=" + city.getText().toString().trim().replace(",", ";"));
                        data.add(state.getHint().toString().trim() + "=" + state.getText().toString().trim().replace(",", ";"));
                        data.add(zip.getHint().toString().trim() + "=" + zip.getText().toString().trim().replace(",", ";"));
                        data.add(phone.getHint().toString().trim() + "=" + phone.getText().toString().trim().replace(",",";"));

                        //capture timestamp
                        Date cDate = new Date();
                        String fDate = new SimpleDateFormat("dd-MM-yyyy hh.mm.ss").format(cDate);

                        data.add("TimeStamp=" + fDate);

                        //persist ranchInfo
                        final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                                getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);

                    /*
                        creating a copy of keySet because if sets retrieved from a shared pref file
                        is modified, it could lead to unexpected behavior.
                    */
                        Set<String> tmpkeySet = null;
                        if (keySet != null && key != null) {
                            Iterator<String> it = keySet.iterator();
                            tmpkeySet = new HashSet<String>();
                            while (it.hasNext()) {
                                tmpkeySet.add(it.next());
                            }
                            tmpkeySet.add(key);
                        } else if (key != null) {
                            tmpkeySet = new HashSet<String>();
                            tmpkeySet.add(key);

                        }
                        if (tmpkeySet != null && key != null) {
                            SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_GROUP_INFO,
                                    Constant.KEY_GROUP, tmpkeySet);
                            SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_GROUP_INFO, key, data);
                            //saving morph config for group
                            HashSet<String> grpMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                                    Constant.PREFS_GRP_MORPH_CONFIG,key);
                            if(grpMorphConfig==null)
                            {
                                HashSet<String> currMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                                        Constant.PREFS_FILE_MORPH_INFO,Constant.KEY_MORPHOLOGY);
                                SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, key, currMorphConfig);
                                Toast.makeText(getApplicationContext(), "Saved configuration!", Toast.LENGTH_LONG).show();
                            }

                            Toast.makeText(getApplicationContext(), "Saved group!", Toast.LENGTH_LONG).show();
                            Intent goPrev = new Intent(getApplicationContext(), Collections.class);
                            startActivity(goPrev);
                        } else {
                            Toast.makeText(getApplicationContext(), "Oops! Unable to save due to internal error", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Intent goPrev = new Intent(getApplicationContext(), Collections.class);
                        startActivity(goPrev);
                    }


                } else {
                    Toast.makeText(getApplicationContext(), "Fix fields marked in red before saving!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), Collections.class);
        startActivity(goPrev);
    }
}
