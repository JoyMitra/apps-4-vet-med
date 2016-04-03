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
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import static android.R.layout.simple_list_item_1;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class BullGroup extends AppCompatActivity {

    private ListView lv = null;
    private String grpId = null;
    private Set<String> bulls = null;
    private Set<String> bullIds = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bull_group);


    }

    @Override
    public void onResume()
    {
        super.onResume();
        grpId = getIntent().getStringExtra("grpId");
        Button btn = (Button) findViewById(R.id.addBullBtn);
        lv = (ListView) findViewById(R.id.bullId);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToNewGroup = new Intent(getApplicationContext(),BullExam.class);
                HashSet<String> bullKeys = (HashSet<String>)SharedPrefUtil.getValue(
                        getApplicationContext(), Constant.PREFS_BULL_INFO,Constant.KEY_BULL);
                String bullKey = Util.getBullKey(grpId, bullKeys);
                goToNewGroup.putExtra("bullKey", bullKey);
                startActivity(goToNewGroup);
            }
        });

        bulls = new LinkedHashSet<String>();
        //load group-specific bull keys
        if(SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_BULL_INFO,Constant.KEY_BULL)!=null)
        {
            for(String key : SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_BULL_INFO,Constant.KEY_BULL))
            {
                String[] entry = key.split("_");
                if(entry!=null && entry.length==2 && entry[0].equalsIgnoreCase(grpId))
                {
                    bulls.add(key);
                }
            }
            //getting bull ids(tag, tattoo,...)
            bullIds = Util.loadBullIds(bulls, grpId,Constant.BULL_INFO_ID_1.trim(), Constant.BULL_INFO_ID_2.trim(),
                    Constant.BULL_INFO_ID_3.trim(), Constant.BULL_INFO_ID_4.trim(), Constant.BULL_INFO_PREFIX.trim(),
                    getApplicationContext());
            if(bullIds != null)
            {
                ArrayAdapter<Object> dataAdapter = new ArrayAdapter<Object>(this, simple_list_item_1,bullIds.toArray());
                lv.setAdapter(dataAdapter);
            }
        }


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent goToBullExam = new Intent(getApplicationContext(), BullExam.class);
                if(bulls!=null && position < bulls.toArray().length)
                {
                    goToBullExam.putExtra("bullKey",bulls.toArray()[position].toString());
                }
                startActivity(goToBullExam);

            }
        });
    }
}
