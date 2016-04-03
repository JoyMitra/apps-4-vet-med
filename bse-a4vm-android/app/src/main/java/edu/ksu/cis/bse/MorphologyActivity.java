package edu.ksu.cis.bse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import edu.ksu.cis.bse.Constants.Constant;
import edu.ksu.cis.bse.util.SharedPrefUtil;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class MorphologyActivity extends AppCompatActivity {

    EditText field1 = null;
    EditText limit1 = null;
    EditText field2 = null;
    EditText limit2 = null;
    EditText field3 = null;
    EditText limit3 = null;
    EditText field4 = null;
    EditText limit4 = null;
    EditText field5 = null;
    EditText limit5 = null;
    EditText field6 = null;
    EditText limit6 = null;
    EditText field7 = null;
    EditText limit7 = null;
    EditText field8 = null;
    EditText limit8 = null;
    Button btn  =null;
    LinkedHashSet<EditText> fields = null;
    HashSet<String> morphologyLabels = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphology);

        btn = (Button) findViewById(R.id.saveMorphology);
        field1 = (EditText) findViewById(R.id.morphfield1);
        limit1 = (EditText) findViewById(R.id.morphLimit1);
        field2 = (EditText) findViewById(R.id.morphfield2);
        limit2 = (EditText) findViewById(R.id.morphLimit2);
        field3 = (EditText) findViewById(R.id.morphField3);
        limit3 = (EditText) findViewById(R.id.morphLimit3);
        field4 = (EditText) findViewById(R.id.morphField4);
        limit4 = (EditText) findViewById(R.id.morphLimit4);
        field5 = (EditText) findViewById(R.id.morphField5);
        limit5 = (EditText) findViewById(R.id.morphLimit5);
        field6 = (EditText) findViewById(R.id.morphField6);
        limit6 = (EditText) findViewById(R.id.morphLimit6);
        field7 = (EditText) findViewById(R.id.morphField7);
        limit7 = (EditText) findViewById(R.id.morphLimit7);
        field8 = (EditText) findViewById(R.id.morphField8);
        limit8 = (EditText) findViewById(R.id.morphLimit8);

        fields = new LinkedHashSet<>();

        fields.add(field2);
        fields.add(limit2);

        fields.add(field3);
        fields.add(limit3);

        fields.add(field4);
        fields.add(limit4);

        fields.add(field5);
        fields.add(limit5);

        fields.add(field6);
        fields.add(limit6);

        fields.add(field7);
        fields.add(limit7);

        fields.add(field8);
        fields.add(limit8);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //loading stored data
        morphologyLabels = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_FILE_MORPH_INFO,Constant.KEY_MORPHOLOGY);
        if(morphologyLabels!=null && morphologyLabels.size() <= fields.size())
        {
            Iterator<EditText> it = fields.iterator();
            for(String label: morphologyLabels)
            {
                if(label.equalsIgnoreCase("Normal"))
                {
                    limit1.setText(SharedPrefUtil.getSingleValue(getApplicationContext(), Constant.PREFS_FILE_MORPH_INFO, label));
                }
                else
                {
                    if(it.hasNext())
                    {
                        EditText lbl = it.next();
                        EditText lmt = it.next();
                        if(lbl!=null && lmt!=null)
                        {
                            lbl.setText(label);
                            lmt.setText(SharedPrefUtil.getSingleValue(getApplicationContext(), Constant.PREFS_FILE_MORPH_INFO, label));
                        }
                    }
                }
            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean valid = true;
                if(limit1.getText().toString().trim().length() == 0)
                {
                    valid = false;
                }
                if(valid && field2.getText().toString().trim().length()>0 && limit2.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field2.getText().toString().trim().length()==0 && limit2.getText().toString().trim().length()>0)
                {
                    valid = false;
                }
                if(valid && field3.getText().toString().trim().length()>0 && limit3.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field3.getText().toString().trim().length()==0 && limit3.getText().toString().trim().length()>0)
                {
                    valid = false;
                }
                if(valid && field4.getText().toString().trim().length()>0 && limit4.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field4.getText().toString().trim().length()==0 && limit4.getText().toString().trim().length()>0)
                {
                    valid = false;
                }
                if(valid && field5.getText().toString().trim().length()>0 && limit5.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field5.getText().toString().trim().length()==0 && limit5.getText().toString().trim().length()>0)
                {
                    valid = false;
                }
                if(valid && field6.getText().toString().trim().length()>0 && limit6.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field6.getText().toString().trim().length()==0 && limit6.getText().toString().trim().length()>0)
                {
                    valid = false;
                }
                if(valid && field7.getText().toString().trim().length()>0 && limit7.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field7.getText().toString().trim().length()==0 && limit7.getText().toString().trim().length()>0)
                {
                    valid = false;
                }
                if(valid && field8.getText().toString().trim().length()>0 && limit8.getText().toString().trim().length()==0)
                {
                    valid = false;
                }
                if(valid && field8.getText().toString().trim().length()==0 && limit8.getText().toString().trim().length()>0)
                {
                    valid = false;
                }

                if(valid)
                {
                    HashSet<String> keys = new HashSet<String>();
                    String key = field1.getText().toString().trim();
                    String data = limit1.getText().toString().trim();
                    keys.add(key);
                    SharedPrefUtil.saveMorphologyLimits(getApplicationContext(), data, key);

                    key = field2.getText().toString().trim();
                    data = limit2.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    key = field3.getText().toString().trim();
                    data = limit3.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    key = field4.getText().toString().trim();
                    data = limit4.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    key = field5.getText().toString().trim();
                    data = limit5.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    key = field6.getText().toString().trim();
                    data = limit5.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    key = field7.getText().toString().trim();
                    data = limit7.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    key = field8.getText().toString().trim();
                    data = limit8.getText().toString().trim();
                    if(key.length()>0 && data.length()>0)
                    {
                        keys.add(key);
                        SharedPrefUtil.saveMorphologyLimits(getApplicationContext(),data, key);
                    }

                    SharedPrefUtil.saveMorphologyKeys(getApplicationContext(),keys);
                    Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Validation Errors",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
