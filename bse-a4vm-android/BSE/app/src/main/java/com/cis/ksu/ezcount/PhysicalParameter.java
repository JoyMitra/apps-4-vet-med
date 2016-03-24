package com.cis.ksu.ezcount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
}