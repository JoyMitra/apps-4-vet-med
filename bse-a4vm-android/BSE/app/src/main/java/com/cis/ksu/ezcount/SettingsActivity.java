package com.cis.ksu.ezcount;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView tx = (TextView) findViewById(R.id.vetInfo);
        tx.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent goToVetInfo = new Intent(getApplicationContext(), VetinfoActivity.class);
                startActivity(goToVetInfo);
            }
        });

        TextView tx1 = (TextView) findViewById(R.id.morphFields);
        tx1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent goToMorph = new Intent(getApplicationContext(), MorphologyActivity.class);
                startActivity(goToMorph);
            }
        });
    }
}
