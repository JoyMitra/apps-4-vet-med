package com.cis.ksu.ezcount;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.content.Intent;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;


import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import static android.R.layout.simple_list_item_1;


public class Collections extends AppCompatActivity{

    /*private static final String[] items = {"Date:RanchName1","Date:RanchName2",
            "Date:RanchName3"};*/



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
            for(String item : items)
            {
                Set<String> groups = null;
                groups = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_GROUP_INFO,item);
                if(groups != null)
                {
                    String name = null;
                    String datetime = null;
                    for(String group : groups)
                    {
                        String[] entry = group.split("=");
                        if(entry != null && entry.length==2 && entry[0].equalsIgnoreCase("Ranch Name"))
                        {
                            name = entry[1];
                            /*Date cDate = new Date();
                            String fDate = new SimpleDateFormat("dd-MM-yyyy").format(cDate);
                            ranchNames.add(fDate + ":" + entry[1]);
                            break;*/
                        }
                        if(entry != null && entry.length==2 && entry[0].equalsIgnoreCase("TimeStamp"))
                        {
                            datetime = entry[1];
                        }
                        if(name!=null && datetime!=null)
                        {
                            ranchNames.add(datetime + ":" + name);
                            break;
                        }

                    }
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
                Object[] grpIds = null;
                if (lv.getItemAtPosition(position) != null) {
                    selectedString = lv.getItemAtPosition(position).toString();
                }
                Intent goToEditColls = new Intent(getApplicationContext(), EditCollections.class);
                Bundle extras = new Bundle();
                extras.putString("ranch", selectedString);
                extras.putString("GrpId", orderedItems.toArray()[position].toString());
                /*if(items != null)
                {
                    grpIds = items.toArray();
                    if(position < grpIds.length) {
                        extras.putString("GrpId", items.toArray()[position].toString());
                    }
                }*/
                goToEditColls.putExtras(extras);
                //Toast.makeText(getApplicationContext(),items.toArray()[position].toString(),Toast.LENGTH_LONG).show();
                startActivity(goToEditColls);

            }
        });
    }
}

