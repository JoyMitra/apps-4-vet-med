package edu.ksu.cis.a4vm.bse;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.CreateCSV;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class EditCollections extends AppCompatActivity {

    public String grpId = null;
    public String ranchInfo = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_collections);
        Intent i = getIntent();
        Bundle extras = i.getExtras();
        ranchInfo = extras.getString("ranch");
        grpId = extras.getString("GrpId");
        String name = null;
        String dateColleted = null;
        TextView tvRanch = (TextView) findViewById(R.id.ranchName);
        TextView tvDate = (TextView) findViewById(R.id.ranchDate);
        TextView tvBull = (TextView) findViewById(R.id.editBulls);
        TextView tvExport = (TextView) findViewById(R.id.export);
        TextView tvEditGrp = (TextView) findViewById(R.id.editGrp);
        //TextView tvDelete = (TextView) findViewById(R.id.deleteGrp);
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
                Bundle items = new Bundle();
                items.putString("ranch",ranchInfo);
                items.putString("grpId", grpId);
                goToMorph.putExtras(items);
                startActivity(goToMorph);
            }
        });

        tvEditGrp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToMorph = new Intent(getApplicationContext(), NewGroup.class);
                goToMorph.putExtra("grpKey", grpId);
                startActivity(goToMorph);
            }
        });

        tvExport.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                File file;
                File root = Environment.getExternalStorageDirectory();
                if (root.canWrite()){
                    File dir = new File (root.getAbsolutePath() + "/bull_collections");
                    dir.mkdirs();
                    file   =   new File(dir, "Data.csv");
                    FileOutputStream out   =   null;
                    try {
                        out = new FileOutputStream(file);
                    } catch (FileNotFoundException e) {
                        Log.e(Constant.CSV_MSG,"CSV File not found");
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
                                String contents;
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
                        Log.e(Constant.CSV_MSG,"File writing error");
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        Log.e(Constant.CSV_MSG,"File closing error");
                        e.printStackTrace();
                    }

                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Exported file");
                    sendIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
                    sendIntent.setType("text/html");
                    startActivityForResult(sendIntent, 119);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Failed to mount dir!",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        Intent goPrev = new Intent(getApplicationContext(), Collections.class);
        startActivity(goPrev);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==119)
        {
            try{
                File file = null;
                File root = Environment.getExternalStorageDirectory();
                if (root.canWrite()) {
                    File dir = new File(root.getAbsolutePath() + "/bull_collections");
                    dir.mkdirs();
                    file = new File(dir, "Data.csv");
                    if(file.delete())
                    {
                        Log.e(Constant.CSV_MSG,"File deleted");
                    }

                }
            }
            catch(Exception e)
            {
                Log.e(Constant.CSV_MSG, "Exception occured while deleting CSV file");
            }

        }

    }

}
