package edu.ksu.cis.bse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import edu.ksu.cis.bse.Constants.Constant;
import edu.ksu.cis.bse.util.SharedPrefUtil;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

import static android.R.layout.simple_list_item_1;

public class MorphologyDashboard extends AppCompatActivity {

    private Button btn = null;
    private ListView lv = null;
    private String bullKey = null;
    private String morphKey = null;
    private Set<String> collectedTotals = null;
    private Set<String> collectedKeys = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphology_dashboard);

        btn = (Button)findViewById(R.id.addMorphology);
        lv= (ListView) findViewById(R.id.morphCollection);

    }



    @Override
    public void onResume()
    {
        super.onResume();
        bullKey = getIntent().getStringExtra("bullKey");

        final Set<String> keys = SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_MORPHOLOGY_COUNT_KEYS, Constant.KEY_MORPHOLOGY_COUNT_KEY);
        morphKey = Util.getBullKey(bullKey, keys);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMorphCount = new Intent(getApplicationContext(), MorphologyCount.class);
                goToMorphCount.putExtra("morphKey", morphKey);
                startActivity(goToMorphCount);
            }
        });

        if(keys!=null && !keys.isEmpty())
        {
            collectedTotals = new LinkedHashSet<String>();
            collectedKeys = new LinkedHashSet<String>();
            for(String key: keys)
            {
                String[] elems = key.split("_");
                if(elems.length==3 && (elems[0].trim()+"_"+elems[1].trim()).equalsIgnoreCase(bullKey))
                {
                    int total = 0;
                    String dateTime = null;
                    Set<String> morphologyCounts = SharedPrefUtil.getValue(getApplicationContext(),
                            Constant.PREFS_BULL_MORPHOLOGY_INFO, key);
                    if(morphologyCounts!=null && !morphologyCounts.isEmpty())
                    {
                        for(String entry: morphologyCounts)
                        {
                            //total = 0;
                            String[] morphologyCount = entry.trim().split("=");
                            if(morphologyCount!=null && morphologyCount.length==2
                                    && "TimeStamp".equalsIgnoreCase(morphologyCount[0]))
                            {
                                dateTime = morphologyCount[1];
                            }
                            else if(morphologyCount!=null && morphologyCount.length==2)
                            {
                                total = total + Integer.valueOf(morphologyCount[1]);
                            }
                        }
                    }
                    collectedTotals.add(Constant.MESSAGE_TOTAL + total + "\n" + Constant.DATE_MSG + dateTime);
                    collectedKeys.add(key);
                }
            }
        }

        if(collectedTotals!=null && !collectedTotals.isEmpty())
        {
            ArrayAdapter<Object> listOfCounts = new ArrayAdapter<Object>(this,simple_list_item_1,collectedTotals.toArray());
            lv.setAdapter(listOfCounts);
        }

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(collectedKeys!=null)
                {
                    Intent goToMorphCount = new Intent(getApplicationContext(), MorphologyCount.class);
                    goToMorphCount.putExtra("morphKey",collectedKeys.toArray()[position].toString());
                    startActivity(goToMorphCount);
                }



            }
        });
    }
}
