package com.cis.ksu.ezcount;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.CreateCSV;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class EditCollections extends AppCompatActivity {

    String grpId = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_collections);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        String ranchInfo = extras.getString("ranch");
        grpId = extras.getString("GrpId");
        //Toast.makeText(getApplicationContext(),grpId, Toast.LENGTH_LONG).show();
        String name = null;
        String dateColleted = null;
        TextView tvRanch = (TextView) findViewById(R.id.ranchName);
        TextView tvDate = (TextView) findViewById(R.id.ranchDate);
        TextView tvBull = (TextView) findViewById(R.id.editBulls);
        TextView tvExport = (TextView) findViewById(R.id.export);
        TextView tvDelete = (TextView) findViewById(R.id.deleteGrp);
        if (ranchInfo!=null)
        {
            name = ranchInfo.substring(0,ranchInfo.indexOf(":"));
            dateColleted = ranchInfo.substring(ranchInfo.indexOf(":")+1,ranchInfo.length());
        }
        tvRanch.setText(name);
        tvDate.setText(dateColleted);
        tvBull.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent goToMorph = new Intent(getApplicationContext(), BullGroup.class);
                goToMorph.putExtra("grpId", grpId);
                startActivity(goToMorph);
            }
        });

        tvExport.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = null;
                File root = Environment.getExternalStorageDirectory();
                if (root.canWrite()){
                    File dir = new File (root.getAbsolutePath() + "/bull_collections");
                    dir.mkdirs();
                    file   =   new File(dir, "Data.csv");
                    FileOutputStream out   =   null;
                    try {
                        out = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    try {
                        String head = Constant.CSV_HEADING;
                        out.write(head.getBytes());
                        ArrayList<ArrayList> list = CreateCSV.getData(getApplicationContext(),grpId);
                        if(list!=null)
                        {
                            for(int i=0;i<list.size();i++)
                            {
                                out.write("\n".getBytes());
                                ArrayList<String> row = list.get(i);
                                String contents = null;
                                if(row!=null)
                                {
                                    contents = row.get(0);
                                    for(int j=1;j<103;j++)
                                    {
                                        contents = contents + "," + row.get(j);
                                    }
                                    out.write(contents.getBytes());
                                }

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Exported file");
                    sendIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
                    sendIntent.setType("text/html");
                    startActivity(sendIntent);
                }
            }
        });

        tvDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try
                {
                    AlertDialog.Builder build = new AlertDialog.Builder(EditCollections.this);
                    build.setTitle("Delete Group");
                    build.setMessage("This will permanently delete the group. Are you sure?");
                    build.setCancelable(true);
                    build.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try {
                                final Set<String> groupIds = SharedPrefUtil.getValue(getApplicationContext(),
                                        Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                                Set<String> tmpGroupIds = groupIds;
                                for (String elem : groupIds) {
                                    if (grpId.equalsIgnoreCase(elem)) {
                                        tmpGroupIds.remove(grpId);
                                    }
                                }
                                SharedPrefUtil.saveGroup(getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP, tmpGroupIds);
                                Toast.makeText(getApplicationContext(), "Group deleted successfully", Toast.LENGTH_SHORT).show();
                            } catch (NullPointerException ne) {
                                Toast.makeText(getApplicationContext(), "Nothing to delete!", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                Toast.makeText(getApplicationContext(), "Unable to delete at this moment. Try again.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    build.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    AlertDialog alert = build.create();
                    alert.show();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Unable to delete. Try again later.",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

}