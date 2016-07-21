package edu.ksu.cis.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.LinkedHashSet;
import java.util.Set;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

import static android.R.layout.simple_list_item_1;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class MorphologyDashboard extends AppCompatActivity {

    private Button btn = null;
    private ListView lv = null;
    public String bullKey = null;
    public String morphKey = null;
    private Set<String> collectedTotals = null;
    private Set<String> collectedKeys = null;
    public String testMsg = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphology_dashboard);

        btn = (Button)findViewById(R.id.addMorphology);
        lv= (ListView) findViewById(R.id.morphCollection);
        bullKey = getIntent().getStringExtra("bullKey");

    }



    @Override
    public void onResume()
    {
        super.onResume();


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
                //testMsg = "looping keys";
                String[] elems = key.split("_");
                if(elems.length==3 && (elems[0].trim()+"_"+elems[1].trim()).equalsIgnoreCase(bullKey))
                {
                    //int total = 0;
                    testMsg = "building listview";
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
                            /*else if(morphologyCount!=null && morphologyCount.length==2)
                            {
                                total = total + Integer.valueOf(morphologyCount[1]);
                            }*/
                        }
                    }
                    //collectedTotals.add(Constant.MESSAGE_TOTAL + total + "\n" + Constant.DATE_MSG + dateTime);
                    collectedTotals.add(Constant.DATE_MSG + dateTime);
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
