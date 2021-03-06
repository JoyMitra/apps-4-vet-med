/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

package edu.ksu.cs.a4vm.bse;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.CreateCSV;

public class EditCollections extends AppCompatActivity {

    public String grpId = null;
    public String ranchInfo = null;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    int permission = -1;
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
        permission = ActivityCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        //TextView tvDelete = (TextView) findViewById(R.id.deleteGrp);
        if (ranchInfo!=null)
        {
            dateColleted = ranchInfo.substring(0,ranchInfo.indexOf(":"));
            name = ranchInfo.substring(ranchInfo.indexOf(":")+1,ranchInfo.length());
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
                if (permission != PackageManager.PERMISSION_GRANTED) {
                    // We don't have permission so prompt the user
                    ActivityCompat.requestPermissions(
                            EditCollections.this,
                            PERMISSIONS_STORAGE,
                            REQUEST_EXTERNAL_STORAGE
                    );
                }
                else{
                    File file;
                    File root = Environment.getExternalStorageDirectory();
                    File dir = new File (root.getAbsolutePath() + "/bull_collections");
                    dir.mkdirs();
                    file   =   new File(dir, "Data.csv");
                    try {
                        if(!file.exists())
                        {
                            file.createNewFile();
                        }
                        FileOutputStream out   =   null;

                        out = new FileOutputStream(file);

                        String head = Constant.CSV_HEADING;

                        ArrayList<ArrayList> list = CreateCSV.getData(getApplicationContext(),grpId);
                        if(Constant.morphHeaders!=null)
                        {
                            for(int i=0;i<Constant.morphHeaders.size();i++)
                            {
                                head = head + Constant.morphHeaders.get(i) + ",";
                            }
                        }
                        out.write(head.getBytes());
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
                        out.close();

                    } catch (IOException e) {
                        Log.e(Constant.CSV_MSG,"File writing error");
                        e.printStackTrace();
                    }


                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Exported file");
                    sendIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
                    sendIntent.setType("text/html");
                    startActivityForResult(sendIntent, 119);
                }
                //if (root.canWrite()){

                //}
                /*else
                {
                    Toast.makeText(getApplicationContext(),"Failed to mount dir!",Toast.LENGTH_SHORT).show();
                }*/
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantresults){
        if(requestCode==REQUEST_EXTERNAL_STORAGE){
            if(grantresults.length>0 && grantresults[0]==PackageManager.PERMISSION_GRANTED)
            {
                File file;
                File root = Environment.getExternalStorageDirectory();
                File dir = new File (root.getAbsolutePath() + "/bull_collections");
                dir.mkdirs();
                file   =   new File(dir, "Data.csv");
                try {
                    if(!file.exists())
                    {
                        file.createNewFile();
                    }
                    FileOutputStream out   =   null;

                    out = new FileOutputStream(file);

                    String head = Constant.CSV_HEADING;

                    ArrayList<ArrayList> list = CreateCSV.getData(getApplicationContext(),grpId);
                    if(Constant.morphHeaders!=null)
                    {
                        for(int i=0;i<Constant.morphHeaders.size();i++)
                        {
                            head = head + Constant.morphHeaders.get(i) + ",";
                        }
                    }
                    out.write(head.getBytes());
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
                    out.close();

                } catch (IOException e) {
                    Log.e(Constant.CSV_MSG,"File writing error");
                    e.printStackTrace();
                }


                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Exported file");
                sendIntent.putExtra(Intent.EXTRA_STREAM,Uri.fromFile(file));
                sendIntent.setType("text/html");
                startActivityForResult(sendIntent, 119);
            }
            else{
                Toast.makeText(getApplicationContext(),"Permission denied!",Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Log.d("BSE Msg","Request Storage permission failed!");
        }
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
        if(requestCode==119);
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
