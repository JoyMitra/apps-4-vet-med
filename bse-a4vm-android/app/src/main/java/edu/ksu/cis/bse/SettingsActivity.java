package edu.ksu.cis.bse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

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
