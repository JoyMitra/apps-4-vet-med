package edu.ksu.cis.a4vm.bse;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.LinkedHashSet;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class VetinfoActivity extends AppCompatActivity
    {
        private Button saveBtn = null;
        private EditText firstName = null;
        private EditText lastName = null;
        private EditText clinic = null;
        private EditText addr1 = null;
        private EditText addr2 = null;
        private EditText city = null;
        private EditText state = null;
        private EditText zip = null;
        private EditText email = null;
        private HashSet<EditText> fields = null;
        Boolean validatefName = false;
        Boolean validatelName = false;
        Boolean validateClinic = false;
        Boolean validateAddr1 = false;
        Boolean validateCity = false;
        Boolean validateState = false;
        Boolean validateZip = false;
        Boolean validateEmail = false;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_vetinfo);
            saveBtn = (Button)findViewById(R.id.vetInfoSave);
            firstName = (EditText)findViewById(R.id.firstName);
            lastName = (EditText)findViewById(R.id.lastName);
            clinic = (EditText)findViewById(R.id.clinic);
            addr1 = (EditText)findViewById(R.id.addr1);
            addr2 = (EditText)findViewById(R.id.addr2);
            city = (EditText)findViewById(R.id.city);
            state = (EditText)findViewById(R.id.state);
            zip = (EditText)findViewById(R.id.zip);
            email = (EditText)findViewById(R.id.email);

            fields = new HashSet<EditText>();
            fields.add(firstName);
            fields.add(lastName);
            fields.add(clinic);
            fields.add(addr1);
            fields.add(addr2);
            fields.add(city);
            fields.add(state);
            fields.add(zip);
            fields.add(email);

        }

        @Override
        public void onResume()
        {
            super.onResume();
            //Date cDate = new Date();
            //String fDate = new SimpleDateFormat("dd-MM-yyyy").format(cDate);
            //Toast.makeText(getApplicationContext(), "Date="+fDate.toString(), Toast.LENGTH_LONG).show();

            //display
            Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                    Constant.PREFS_FILE_VET_INFO, Constant.KEY_VET), fields);


            firstName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (firstName.getText().toString().trim().length() > 0) {
                        firstName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validatefName = false;
                    } else {
                        firstName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        validatefName = true;
                        Toast.makeText(getApplicationContext(), "First name cannot be empty", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {

                    if (lastName.getText().toString().trim().length() > 0) {
                        lastName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validatelName = false;
                    } else {
                        lastName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        validatelName = true;
                        Toast.makeText(getApplicationContext(), "Last name cannot be empty", Toast.LENGTH_SHORT).show();
                    }


                }
            });

            clinic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                                                @Override
                                                public void onFocusChange(View v, boolean hasFocus) {

                                                    if (clinic.getText().toString().trim().length() > 0) {
                                                        clinic.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                        validateClinic = false;
                                                    } else {
                                                        clinic.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                        validateClinic = true;
                                                        Toast.makeText(getApplicationContext(), "Clinic name cannot be empty", Toast.LENGTH_SHORT).show();
                                                    }


                                                }
                                            }

            );

                addr1.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                               {
                                                   @Override
                                                   public void onFocusChange(View v, boolean hasFocus) {

                                                       if (addr1.getText().toString().trim().length() > 0) {
                                                           addr1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                           validateAddr1 = false;
                                                       } else {
                                                           addr1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateAddr1 = true;
                                                           Toast.makeText(getApplicationContext(), "Address1 cannot be empty", Toast.LENGTH_SHORT).show();
                                                       }


                                                   }
                                               }

                );

                city.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                              {
                                                  @Override
                                                  public void onFocusChange(View v, boolean hasFocus) {

                                                      if (city.getText().toString().trim().length() > 0) {
                                                          city.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                          validateCity = false;
                                                      }
                                                      else {
                                                          city.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                          validateCity = true;
                                                          Toast.makeText(getApplicationContext(), "city cannot be empty", Toast.LENGTH_SHORT).show();
                                                      }


                                                  }
                                              }

                );

                state.setOnFocusChangeListener(new View.OnFocusChangeListener()

                                               {
                                                   @Override
                                                   public void onFocusChange(View v, boolean hasFocus) {

                                                       try {
                                                           Integer.valueOf(state.getText().toString());
                                                           Float.valueOf(state.getText().toString());
                                                           state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                       } catch (NumberFormatException ne) {
                                                           if (state.getText().toString().trim().length() == 2) {
                                                               state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                               validateState = false;
                                                           }
                                                           else {
                                                               state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                               validateState = true;
                                                               Toast.makeText(getApplicationContext(), "State can have exactly 2 characters (Eg: KS)", Toast.LENGTH_SHORT).show();
                                                           }
                                                       }


                                                   }
                                               }

                );

                zip.setOnFocusChangeListener(new View.OnFocusChangeListener()

                {
                    @Override
                    public void onFocusChange (View v,boolean hasFocus){

                    try {
                        if (zip.getText().toString().trim().length() == 5 &&
                                Integer.valueOf(zip.getText().toString().trim()) > 0) {
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

                email.setOnFocusChangeListener(new View.OnFocusChangeListener()

                {
                    @Override
                    public void onFocusChange (View v,boolean hasFocus){
                    if(Util.isEmailValid(email.getText().toString().trim()))
                    {
                        email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        validateEmail = false;
                    }
                    else{
                        validateEmail = true;
                        email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                    }

                }
                }

                );


            saveBtn.setOnClickListener(new View.OnClickListener()

                                       {
                                           @Override
                                           public void onClick(View v) {
                                               if (!validatefName && !validatelName
                                                       && !validateClinic && !validateAddr1
                                                       && !validateCity && !validateState
                                                       && !validateZip && !validateEmail) {
                                                   HashSet<String> data = new LinkedHashSet<String>();
                                                   data.add(firstName.getHint().toString().trim() + "=" + firstName.getText().toString().trim());
                                                   data.add(lastName.getHint().toString().trim() + "=" + lastName.getText().toString().trim());
                                                   data.add(clinic.getHint().toString().trim() + "=" + clinic.getText().toString().trim());
                                                   data.add(addr1.getHint().toString().trim() + "=" + addr1.getText().toString().trim());
                                                   data.add(addr2.getHint().toString().trim() + "=" + addr2.getText().toString().trim());
                                                   data.add(city.getHint().toString().trim() + "=" + city.getText().toString().trim());
                                                   data.add(state.getHint().toString().trim() + "=" + state.getText().toString().trim());
                                                   data.add(zip.getHint().toString().trim() + "=" + zip.getText().toString().trim());
                                                   data.add(email.getHint().toString().trim() + "=" + email.getText().toString().trim());

                                                   //persist VetInfo
                                                   SharedPrefUtil.saveVetInfo(getApplicationContext(), data);
                                                   Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                                                   //display
                                                   Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                                                           Constant.PREFS_FILE_VET_INFO, Constant.KEY_VET), fields);
                                               } else {
                                                   Toast.makeText(getApplicationContext(), "Fix fields marked red before saving!", Toast.LENGTH_LONG).show();
                                               }

                                           }
                                       }

            );
            }
        }
