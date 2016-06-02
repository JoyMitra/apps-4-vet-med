package edu.ksu.cis.a4vm.bse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.HashSet;
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


public class Collections extends AppCompatActivity{
    private Button btn = null;
    private ListView lv = null;
    private Set<String> ranchNames = null;
    private Set<String> orderedItems = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collections);




        btn = (Button) findViewById(R.id.addGrpBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                        getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                String key = Util.getKey(keySet);
                Intent goToNewGroup = new Intent(getApplicationContext(), NewGroup.class);
                goToNewGroup.putExtra("grpKey", key);
                startActivity(goToNewGroup);
            }
        });

        lv = (ListView) findViewById(R.id.ranchId);




    }

    @Override
    public void onResume()
    {
        super.onResume();
        //loading data from group
        final Set<String> items = SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_GROUP_INFO,Constant.KEY_GROUP);
        if(items != null)
        {
            ranchNames = new LinkedHashSet<>();
            orderedItems = new LinkedHashSet<>();
            String dispStr = null;
            for(String item : items)
            {
                Set<String> groups = null;
                groups = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_GROUP_INFO,item);
                if(groups != null)
                {
                    String name = "<>";
                    String rname = "<>";
                    String datetime = "<>";
                    for(String group : groups)
                    {
                        String[] entry = group.split("=");
                        if(entry != null && entry.length==2 && entry[0].equalsIgnoreCase("Ranch Name"))
                        {
                            name = entry[1];
                        }
                        if(entry != null && entry.length==2 && entry[0].equalsIgnoreCase("Rancher Name"))
                        {
                            rname = entry[1];
                        }
                        if(entry != null && entry.length==2 && entry[0].equalsIgnoreCase("TimeStamp"))
                        {
                            datetime = entry[1];
                        }
                        dispStr = datetime + ":" + name + "/" + rname;


                    }
                    ranchNames.add(dispStr);
                    orderedItems.add(item);
                }

            }
        }

        if(ranchNames!=null)
        {
            ArrayAdapter<Object> dataAdapter = new ArrayAdapter<Object>(this, simple_list_item_1,ranchNames.toArray());
            lv.setAdapter(dataAdapter);
        }

        lv.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedString = null;
                if (lv.getItemAtPosition(position) != null) {
                    selectedString = lv.getItemAtPosition(position).toString();
                }
                Intent goToEditColls = new Intent(getApplicationContext(), EditCollections.class);
                Bundle extras = new Bundle();
                extras.putString("ranch", selectedString);
                extras.putString("GrpId", orderedItems.toArray()[position].toString());
                goToEditColls.putExtras(extras);
                startActivity(goToEditColls);

            }
        });
    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(goPrev);
    }
}

