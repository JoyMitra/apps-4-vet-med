package edu.ksu.cis.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class MorphologyActivity extends AppCompatActivity {

    EditText field1 = null;
    EditText limit = null;
    EditText field2 = null;
    EditText field3 = null;
    EditText field4 = null;
    EditText field5 = null;
    EditText field6 = null;
    EditText field7 = null;
    EditText field8 = null;
    Button btn  =null;
    LinkedHashSet<EditText> fields = null;
    HashSet<String> morphologyLabels = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphology);

        btn = (Button) findViewById(R.id.saveMorphology);
        field1 = (EditText) findViewById(R.id.morphfield1);
        limit = (EditText) findViewById(R.id.morphLimit);
        field2 = (EditText) findViewById(R.id.morphfield2);
        field3 = (EditText) findViewById(R.id.morphField3);
        field4 = (EditText) findViewById(R.id.morphField4);
        field5 = (EditText) findViewById(R.id.morphField5);
        field6 = (EditText) findViewById(R.id.morphField6);
        field7 = (EditText) findViewById(R.id.morphField7);
        field8 = (EditText) findViewById(R.id.morphField8);

        fields = new LinkedHashSet<>();

        fields.add(field2);

        fields.add(field3);

        fields.add(field4);

        fields.add(field5);

        fields.add(field6);

        fields.add(field7);

        fields.add(field8);
        fields.add(limit);
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
                String[] text = label.split("=");
                if(label.contains("Limit="))
                {

                    if(text!=null && text.length==2)
                        limit.setText(text[1]);
                }
                else if(label.contains("Morphology Field 2"))
                {
                    if(text!=null && text.length==2)
                        field2.setText(text[1]);
                }
                else if(label.contains("Morphology Field 3"))
                {
                    if(text!=null && text.length==2)
                        field3.setText(text[1]);
                }
                else if(label.contains("Morphology Field 4"))
                {
                    if(text!=null && text.length==2)
                        field4.setText(text[1]);
                }
                else if(label.contains("Morphology Field 5"))
                {
                    if(text!=null && text.length==2)
                        field5.setText(text[1]);
                }
                else if(label.contains("Morphology Field 6"))
                {
                    if(text!=null && text.length==2)
                        field6.setText(text[1]);
                }
                else if(label.contains("Morphology Field 7"))
                {
                    if(text!=null && text.length==2)
                        field7.setText(text[1]);
                }
                else if(label.contains("Morphology Field 8"))
                {
                    if(text!=null && text.length==2)
                        field8.setText(text[1]);
                }

            }
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                    HashSet<String> keys = new HashSet<String>();

                    String key = field2.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 2="+ key;
                        keys.add(key);
                    }

                    key = field3.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 3="+ key;
                        keys.add(key);
                    }

                    key = field4.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 4="+ key;
                        keys.add(key);
                    }

                    key = field5.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 5="+ key;
                        keys.add(key);
                    }

                    key = field6.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 6="+ key;
                        keys.add(key);
                    }

                    key = field7.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 7="+ key;
                        keys.add(key);
                    }

                    key = field8.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Morphology Field 8="+ key;
                        keys.add(key);
                    }

                    key = limit.getText().toString().trim();
                    if(key.length()>0)
                    {
                        key = "Limit="+ key;
                        keys.add(key);
                    }

                    SharedPrefUtil.saveMorphologyKeys(getApplicationContext(),keys);
                    Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_LONG).show();
                    Intent goPrev = new Intent(getApplicationContext(), SettingsActivity.class);
                    startActivity(goPrev);

            }
        });

    }
}
