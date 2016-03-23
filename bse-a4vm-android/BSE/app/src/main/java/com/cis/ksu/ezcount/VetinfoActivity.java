package com.cis.ksu.ezcount;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;

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
                    if(!hasFocus)
                    {
                        if (firstName.getText().toString().trim().length() > 0)
                            firstName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        else
                            firstName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "First name cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            lastName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (lastName.getText().toString().trim().length() > 0)
                            lastName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        else
                            lastName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        Toast.makeText(getApplicationContext(), "Last name cannot be empty", Toast.LENGTH_SHORT).show();
                    }

                }
            });

            clinic.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (clinic.getText().toString().trim().length() > 0)
                            clinic.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        else {
                            clinic.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Clinic name cannot be empty", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });

            addr1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (addr1.getText().toString().trim().length() > 0)
                            addr1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        else {
                            addr1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "Address1 cannot be empty", Toast.LENGTH_SHORT).show();
                        }

                    }

                }
            });

            city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        if (city.getText().toString().trim().length() > 0)
                            city.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                        else {
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
                        } catch (NumberFormatException ne) {
                            if (state.getText().toString().trim().length() == 2)
                                state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            else {
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
                        if (!hasFocus) {
                            if (zip.getText().toString().trim().length() > 0)
                                zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            else {
                                zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                Toast.makeText(getApplicationContext(), "zip cannot be empty", Toast.LENGTH_SHORT).show();
                            }
                            }
                        }
                    }

                    );

                    email.setOnFocusChangeListener(new View.OnFocusChangeListener()

                    {
                        @Override
                        public void onFocusChange (View v,boolean hasFocus){
                        if (!hasFocus) {
                            if (email.getText().toString().trim().length() > 0)
                                email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                            else
                                email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                        }
                    }
                    }

                    );


                    saveBtn.setOnClickListener(new View.OnClickListener()

                                               {
                                                   @Override
                                                   public void onClick(View v) {
                                                       Boolean validateError = false;
                                                       if (firstName.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           firstName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (lastName.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           lastName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (clinic.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           clinic.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (addr1.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           addr1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (city.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           city.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (state.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           state.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (zip.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           zip.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (email.getText().toString().trim().length() == 0) {
                                                           Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                                                           email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                                           validateError = true;
                                                       }

                                                       if (!validateError) {
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
                                                       }

                                                   }
                                               }

                    );
                }
        }
