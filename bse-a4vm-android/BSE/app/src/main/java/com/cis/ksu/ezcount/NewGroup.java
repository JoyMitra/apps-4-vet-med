package com.cis.ksu.ezcount;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private HashSet<EditText> fields = null;
    private String key = null;
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


        ranchName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (ranchName.getText().toString().trim().length() > 0)
                        ranchName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    else
                    {
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
                    if (rancherName.getText().toString().trim().length() > 0)
                        rancherName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    else
                    {
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
                    if (address1.getText().toString().trim().length() > 0)
                        address1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                    else {
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
                        } else {
                            phone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                            Toast.makeText(getApplicationContext(), "invalid phone", Toast.LENGTH_SHORT).show();
                        }

                    } else {
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
                                               if (!hasFocus) {
                                                   if (email.getText().toString().trim().length() > 0)
                                                       email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.focus_color));
                                                   else
                                                       email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                                               }
                                           }
                                       }

        );


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Boolean validateError = false;
                if (ranchName.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                    ranchName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                    validateError = true;
                }

                if (rancherName.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                    rancherName.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                    validateError = true;
                }

                if (email.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                    email.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                    validateError = true;
                }

                if (address1.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                    address1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
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

                if (phone.getText().toString().trim().length() == 0) {
                    Toast.makeText(getApplicationContext(), "Fields marked red are mandatory", Toast.LENGTH_LONG).show();
                    phone.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.highlight));
                    validateError = true;
                }

                if (!validateError) {
                    HashSet<String> data = new LinkedHashSet<String>();
                    data.add(ranchName.getHint().toString().trim() + "=" + ranchName.getText().toString().trim());
                    data.add(rancherName.getHint().toString().trim() + "=" + rancherName.getText().toString().trim());
                    data.add(email.getHint().toString().trim() + "=" + email.getText().toString().trim());
                    data.add(address1.getHint().toString().trim() + "=" + address1.getText().toString().trim());
                    data.add(address2.getHint().toString().trim() + "=" + address2.getText().toString().trim());
                    data.add(city.getHint().toString().trim() + "=" + city.getText().toString().trim());
                    data.add(state.getHint().toString().trim() + "=" + state.getText().toString().trim());
                    data.add(zip.getHint().toString().trim() + "=" + zip.getText().toString().trim());
                    data.add(phone.getHint().toString().trim() + "=" + phone.getText().toString().trim());

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
                    if (keySet != null && key!=null) {
                        Iterator<String> it = keySet.iterator();
                        tmpkeySet = new HashSet<String>();
                        while(it.hasNext())
                        {
                            tmpkeySet.add(it.next());
                        }
                        tmpkeySet.add(key);
                    } else if(key!=null){
                        tmpkeySet = new HashSet<String>();
                        tmpkeySet.add(key);

                    }
                    if(tmpkeySet!=null && key!=null)
                    {
                        SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_GROUP_INFO,
                                Constant.KEY_GROUP, tmpkeySet);
                        SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_GROUP_INFO, key, data);
                        Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                        //display
                        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                                Constant.PREFS_GROUP_INFO, key), fields);
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Oops! Unable to save due to internal error", Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }
}
