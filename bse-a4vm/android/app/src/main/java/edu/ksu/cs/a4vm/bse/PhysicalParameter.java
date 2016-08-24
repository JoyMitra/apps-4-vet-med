/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */
package edu.ksu.cs.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class PhysicalParameter extends AppCompatActivity {

    private TextView measurement = null;
    private TextView phyExam = null;
    private String bullKey = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical_parameter);

        bullKey = getIntent().getStringExtra("bullKey");
        measurement = (TextView)findViewById(R.id.measure);
        measurement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMeasurements = new Intent(getApplicationContext(), MeasurementsTable.class);
                goToMeasurements.putExtra("bullKey",bullKey);
                startActivity(goToMeasurements);
            }
        });

        phyExam = (TextView) findViewById(R.id.exam);
        phyExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToPhysicalExam = new Intent(getApplicationContext(), PhysicalExam.class);
                goToPhysicalExam.putExtra("bullKey",bullKey);
                startActivity(goToPhysicalExam);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), BullExam.class);
        goPrev.putExtra("bullKey",bullKey);
        startActivity(goPrev);
    }
}
