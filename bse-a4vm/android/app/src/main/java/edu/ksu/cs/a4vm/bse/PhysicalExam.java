package edu.ksu.cs.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class PhysicalExam extends AppCompatActivity {

    private EditText field1 = null;
    private EditText field2 = null;
    private EditText field3 = null;
    private EditText field4 = null;
    private EditText field5 = null;
    private EditText field6 = null;
    private EditText field7 = null;
    private EditText field8 = null;
    private EditText field10 = null;
    private EditText field11 = null;
    private EditText field12 = null;

    private CheckBox check1 = null;
    private CheckBox check2 = null;
    private CheckBox check3 = null;
    private CheckBox check4 = null;
    private CheckBox check5 = null;
    private CheckBox check6 = null;
    private CheckBox check7 = null;
    private CheckBox check8 = null;
    private CheckBox check9 = null;
    private CheckBox check10 = null;
    private CheckBox check11 = null;
    private CheckBox check12 = null;

    private Button saveBtn = null;

    private Set<EditText> fields = null;
    private Set<CheckBox> checks = null;

    public String bullKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_exam);
        bullKey = getIntent().getStringExtra("bullKey");
        field1 = (EditText) findViewById(R.id.field1);
        field2 = (EditText) findViewById(R.id.field2);
        field3 = (EditText) findViewById(R.id.field3);
        field4 = (EditText) findViewById(R.id.field4);
        field5 = (EditText) findViewById(R.id.field5);
        field6 = (EditText) findViewById(R.id.field6);
        field7 = (EditText) findViewById(R.id.field7);
        field8 = (EditText) findViewById(R.id.field8);
        field10 = (EditText) findViewById(R.id.field10);
        field11 = (EditText) findViewById(R.id.field11);
        field12 = (EditText) findViewById(R.id.field12);

        fields = new LinkedHashSet<EditText>();
        fields.add(field1);
        fields.add(field2);
        fields.add(field3);
        fields.add(field4);
        fields.add(field5);
        fields.add(field6);
        fields.add(field7);
        fields.add(field8);
        fields.add(field10);
        fields.add(field11);
        fields.add(field12);

        check1 = (CheckBox) findViewById(R.id.checkbox1);
        check2 = (CheckBox) findViewById(R.id.checkbox2);
        check3 = (CheckBox) findViewById(R.id.checkbox3);
        check4 = (CheckBox) findViewById(R.id.checkbox4);
        check5 = (CheckBox) findViewById(R.id.checkbox5);
        check6 = (CheckBox) findViewById(R.id.checkbox6);
        check7 = (CheckBox) findViewById(R.id.checkbox7);
        check8 = (CheckBox) findViewById(R.id.checkbox8);
        check9 = (CheckBox) findViewById(R.id.checkbox9);
        check10 = (CheckBox) findViewById(R.id.checkbox10);
        check11 = (CheckBox) findViewById(R.id.checkbox11);
        check12 = (CheckBox) findViewById(R.id.checkbox12);

        checks = new LinkedHashSet<CheckBox>();
        checks.add(check1);
        checks.add(check2);
        checks.add(check3);
        checks.add(check4);
        checks.add(check5);
        checks.add(check6);
        checks.add(check7);
        checks.add(check8);
        checks.add(check10);
        checks.add(check11);
        checks.add(check12);
        checks.add(check9);

        saveBtn = (Button) findViewById(R.id.savePhyExam);

    }

    @Override
    public void onResume()
    {
        super.onResume();


        //load data
        Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_PHY_PRAMS_INFO, bullKey), fields);
        Util.setCheckBoxes(SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_PHY_PRAMS_INFO,bullKey), checks);
        //Toast.makeText(getApplicationContext(),getIntent().getStringExtra("bullKey"),Toast.LENGTH_SHORT).show();
        check9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    Util.setAllCheckBoxes(checks, true);
                }
                else
                {
                    Util.setAllCheckBoxes(checks, false);
                }
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    LinkedHashSet<String> data = new LinkedHashSet<String>();
                    data.add(field1.getHint().toString().trim()+"="+field1.getText().toString().trim().replace(",",";"));
                    data.add(field2.getHint().toString().trim()+"="+field2.getText().toString().trim().replace(",", ";"));
                    data.add(field3.getHint().toString().trim()+"="+field3.getText().toString().trim().replace(",", ";"));
                    data.add(field4.getHint().toString().trim()+"="+field4.getText().toString().trim().replace(",", ";"));
                    data.add(field5.getHint().toString().trim()+"="+field5.getText().toString().trim().replace(",", ";"));
                    data.add(field6.getHint().toString().trim()+"="+field6.getText().toString().trim().replace(",", ";"));
                    data.add(field7.getHint().toString().trim()+"="+field7.getText().toString().trim().replace(",", ";"));
                    data.add(field8.getHint().toString().trim()+"=" +field8.getText().toString().trim().replace(",",";"));
                    data.add(field10.getHint().toString().trim()+"=" +field10.getText().toString().trim().replace(",",";"));
                    data.add(field11.getHint().toString().trim()+"=" +field11.getText().toString().trim().replace(",",";"));
                    data.add(field12.getHint().toString().trim()+"=" +field12.getText().toString().trim().replace(",",";"));
                    if(check1.isChecked())
                        data.add(Integer.toString(check1.getId()).trim()+"="+field1.getHint().toString().trim());
                    if(check2.isChecked())
                        data.add(Integer.toString(check2.getId()).trim()+"="+field2.getHint().toString().trim());
                    if(check3.isChecked())
                        data.add(Integer.toString(check3.getId()).trim()+"="+field3.getHint().toString().trim());
                    if(check4.isChecked())
                        data.add(Integer.toString(check4.getId()).trim()+"="+field4.getHint().toString().trim());
                    if(check5.isChecked())
                        data.add(Integer.toString(check5.getId()).trim()+"="+field5.getHint().toString().trim());
                    if(check6.isChecked())
                        data.add(Integer.toString(check6.getId()).trim()+"="+field6.getHint().toString().trim());
                    if(check7.isChecked())
                        data.add(Integer.toString(check7.getId()).trim()+"="+field7.getHint().toString().trim());
                    if(check8.isChecked())
                        data.add(Integer.toString(check8.getId()).trim()+"="+field8.getHint().toString().trim());
                    if(check10.isChecked())
                        data.add(Integer.toString(check10.getId()).trim()+"="+field10.getHint().toString().trim());
                    if(check11.isChecked())
                        data.add(Integer.toString(check11.getId()).trim()+"="+field11.getHint().toString().trim());
                    if(check12.isChecked())
                        data.add(Integer.toString(check12.getId()).trim()+"="+field12.getHint().toString().trim());
                    if(check9.isChecked())
                        data.add(Integer.toString(check9.getId()).trim()+"="+"true");

                    //save to file
                    SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_PHY_PRAMS_INFO,bullKey,data);

                    Intent goPrev = new Intent(getApplicationContext(), PhysicalParameter.class);
                    goPrev.putExtra("bullKey",bullKey);
                    startActivity(goPrev);

                    //display
                    /*Util.setFields(SharedPrefUtil.getValue(getApplicationContext(),
                            Constant.PREFS_PHY_PRAMS_INFO,bullKey),fields);
                    Util.setCheckBoxes(SharedPrefUtil.getValue(getApplicationContext(),
                            Constant.PREFS_PHY_PRAMS_INFO,bullKey), checks);*/

                    Toast.makeText(getApplicationContext(),"Saved!",Toast.LENGTH_SHORT).show();
               // }

            }
        });

    }

    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), PhysicalParameter.class);
        goPrev.putExtra("bullKey", bullKey);
        startActivity(goPrev);
    }
}
