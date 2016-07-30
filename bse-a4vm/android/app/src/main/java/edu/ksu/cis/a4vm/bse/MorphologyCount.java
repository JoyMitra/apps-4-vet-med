package edu.ksu.cis.a4vm.bse;

import android.app.ActionBar;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class MorphologyCount extends AppCompatActivity {

    public String morphKey = null;
    public String grpKey = null;
    //private Set<String> labels = null;
    private Set<String> initVals = null;
    private HashSet<String> morphologyLabels = null;
    private Set<String> morphologyCounts = null;
    private Button btn1 = null;
    private Button btn2 = null;
    private Button btn3 = null;
    private Button btn4 = null;
    private Button btn5 = null;
    private Button btn6 = null;
    private Button btn7 = null;
    private Button btn8 = null;
    private TextView tv = null;
    Button btn = null;
    String test = null;

    private String currentButton = null;
    TableRow row = null;
    public int limit = 1000000;

    int NormalCount = 0;
    double NormalProp = 0.00;
    int Lb2Count = 0;
    double Lb2Prop = 0.00;
    int Lb3Count = 0;
    double Lb3Prop = 0.00;
    int Lb4Count = 0;
    double Lb4Prop = 0.00;
    int Lb5Count = 0;
    double Lb5Prop = 0.00;
    int Lb6Count = 0;
    double Lb6Prop = 0.00;
    int Lb7Count = 0;
    double Lb7Prop = 0.00;
    int Lb8Count = 0;
    double Lb8Prop = 0.00;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphology_count);

        morphKey = getIntent().getStringExtra("morphKey");
        if(morphKey!=null)
            grpKey = (morphKey.split("_"))[0];

    }

    @Override
    public void onResume()
    {
        super.onResume();
        test = "Resume called...";
        final MediaPlayer btnChangeSound = MediaPlayer.create(getApplicationContext(), R.raw.button_changed);
        final MediaPlayer limitRchdSound = MediaPlayer.create(getApplicationContext(), R.raw.limit_reached);
        initVals = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_BULL_MORPHOLOGY_INFO, morphKey);
        /*morphologyLabels = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_FILE_MORPH_INFO,Constant.KEY_MORPHOLOGY);*/
        morphologyLabels = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_GRP_MORPH_CONFIG,grpKey);
        TableLayout table = new TableLayout(this);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        table.setLayoutParams(lp);
        table.setShrinkAllColumns(true);
        table.setStretchAllColumns(true);

        TableLayout.LayoutParams rowLp = new TableLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                0.5f);
        TableRow.LayoutParams cellLp = new TableRow.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                1.0f);

        TableRow.LayoutParams cell1Lp = new TableRow.LayoutParams(
                60,
                120,
                1.0f);



        if(initVals!=null)
        {
            for(String Val: initVals)
            {
                String[] values = Val.split("=");
                if(values!=null && values.length==2 && values[0].equalsIgnoreCase("Normal"))
                {
                    NormalCount = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                    NormalProp = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                }
                else if(morphologyLabels!=null)
                {
                    for(String label: morphologyLabels)
                    {
                        String[] lbls = label.split("=");
                        if(lbls!=null && lbls.length==2 && lbls[1].equalsIgnoreCase(values[0]))
                        {
                            if(lbls[0].equalsIgnoreCase("Morphology Field 2"))
                            {
                                Lb2Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb2Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 3"))
                            {
                                Lb3Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb3Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 4"))
                            {
                                Lb4Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb4Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 5"))
                            {
                                Lb5Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb5Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 6"))
                            {
                                Lb6Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb6Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 7"))
                            {
                                Lb7Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb7Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                            else if(lbls[0].equalsIgnoreCase("Morphology Field 8"))
                            {
                                Lb8Count = Integer.valueOf(values[1].trim().substring(0,values[1].trim().indexOf("(")));
                                Lb8Prop = Double.valueOf(values[1].trim().substring(values[1].trim().indexOf("(")+1,values[1].trim().indexOf("%")));
                            }
                        }
                    }
                }
            }
        }

        Constant.sum = NormalCount + Lb2Count + Lb3Count + Lb4Count + Lb5Count + Lb6Count + Lb7Count + Lb8Count;

        row = new TableRow(this);
        btn1 = new Button(this);
        btn1.setId(R.id.button1);
        btn1.setText("Normal:" + NormalCount + "(" + String.format("%.2f",NormalProp) + "%)");
        btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button));
        btn1.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        row.addView(btn1, cellLp);
        table.addView(row, rowLp);
        setContentView(table);
        if(morphologyLabels!=null)
        {
            Iterator<String> it;

            TableRow row2 = new TableRow(this);
            TableRow row3 = new TableRow(this);
            TableRow row4 = new TableRow(this);
            TableRow row5 = new TableRow(this);

            it = morphologyLabels.iterator();
            while(it.hasNext())
            {
                String label = it.next();
                String text[] = label.split("=");
                if(label.contains("Morphology Field 2"))
                {
                    btn2 = new Button(this);
                    btn2.setId(R.id.button2);
                    if(text!=null && text.length==2)
                    {
                        btn2.setText(text[1]+ ":"+ Lb2Count + "(" + String.format("%.2f",Lb2Prop) + "%)");
                    }
                    btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button2));
                    btn2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    btn2.setHeight(300);
                    row2.addView(btn2);
                }
                else if(label.contains("Morphology Field 4"))
                {
                    btn4 = new Button(this);
                    btn4.setId(R.id.button4);
                    if(text!=null && text.length==2)
                    {
                        btn4.setText(text[1]+ ":"+ Lb4Count + "(" + String.format("%.2f",Lb4Prop) + "%)");
                    }
                    btn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button4));
                    btn4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    btn4.setHeight(300);
                    row3.addView(btn4);
                }
                else if(label.contains("Morphology Field 6"))
                {
                    btn6 = new Button(this);
                    btn6.setId(R.id.button6);
                    if(text!=null && text.length==2)
                    {
                        btn6.setText(text[1]+ ":"+ Lb6Count + "(" + String.format("%.2f",Lb6Prop) + "%)");
                    }
                    btn6.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button6));
                    btn6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    btn6.setHeight(300);
                    row4.addView(btn6);
                }
                else if(label.contains("Morphology Field 8"))
                {
                    btn8 = new Button(this);
                    btn8.setId(R.id.button8);
                    if(text!=null && text.length==2)
                    {
                        btn8.setText(text[1]+ ":"+ Lb8Count + "(" + String.format("%.2f",Lb8Prop) + "%)");
                    }
                    btn8.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button8));
                    btn8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    //btn = new Button(this);
                    btn8.setHeight(300);
                    row5.addView(btn8);
                    //row5.addView(btn);
                }
            }

            it = morphologyLabels.iterator();
            while(it.hasNext())
            {
                String label = it.next();
                String text[] = label.split("=");
                if(label.contains("Limit"))
                {
                    if(text!=null && text.length==2)
                    {
                        limit = Integer.valueOf(text[1]);
                    }
                }
                else if(label.contains("Morphology Field 3"))
                {
                    btn3 = new Button(this);
                    btn3.setId(R.id.button3);
                    if(text!=null && text.length==2)
                    {
                        btn3.setText(text[1] + ":"+ Lb3Count + "(" + String.format("%.2f",Lb3Prop) + "%)");
                    }
                    btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button3));
                    btn3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    btn3.setHeight(300);
                    row2.addView(btn3);
                }
                else if(label.contains("Morphology Field 5"))
                {
                    btn5 = new Button(this);
                    btn5.setId(R.id.button5);
                    if(text!=null && text.length==2)
                    {
                        btn5.setText(text[1]+ ":"+ Lb5Count + "(" + String.format("%.2f",Lb5Prop) + "%)");
                    }
                    btn5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button5));
                    btn5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    btn5.setHeight(300);
                    row3.addView(btn5);
                }
                else if(label.contains("Morphology Field 7"))
                {
                    btn7 = new Button(this);
                    btn7.setId(R.id.button7);
                    if(text!=null && text.length==2)
                    {
                        btn7.setText(text[1]+ ":"+ Lb7Count + "(" + String.format("%.2f",Lb7Prop) + "%)");
                    }
                    btn7.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button7));
                    btn7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
                    btn7.setHeight(300);
                    row4.addView(btn7);
                }

            }


            table.addView(row2,rowLp);
            table.addView(row3,rowLp);
            table.addView(row4,rowLp);
            table.addView(row5,rowLp);

        }

        row = new TableRow(this);

        btn = new Button(this);
        btn.setId(R.id.button);
        btn.setText("Edit Morphology Counts");
        btn.setGravity(Gravity.CENTER);
        row.addView(btn, cell1Lp);

        tv= new TextView(this);
        tv.setId(R.id.totals);
        tv.setText("Total Count:" + Constant.sum);
        tv.setGravity(Gravity.CENTER);
        row.addView(tv,cell1Lp);
        //table.addView(row, rowLp);
        //setContentView(table);

        //row = new TableRow(this);

        table.addView(row, rowLp);
        setContentView(table);


        //initializing morphology counts
        if(initVals==null)
        {
            morphologyCounts = new HashSet<String>();
            if(btn1!=null){
                String initEntry = btn1.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn2!=null){
                String initEntry = btn2.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn3!=null){
                String initEntry = btn3.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn4!=null){
                String initEntry = btn4.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn5!=null){
                String initEntry = btn5.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn6!=null){
                String initEntry = btn6.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn7!=null){
                String initEntry = btn7.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
            if(btn8!=null){
                String initEntry = btn8.getText().toString().trim().replace(":","=");
                morphologyCounts.add(initEntry);
            }
        }
        else
        {
            morphologyCounts = new HashSet<String>();
            for(String initVal: initVals)
            {
                morphologyCounts.add(initVal);
            }
        }

        if(btn!=null)
        {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //SharedPrefUtil.saveGroup(getApplicationContext(),Constant.PREFS_BULL_MORPHOLOGY_INFO,morphKey,morphologyCounts);
                    Intent gotoEditCount = new Intent(getApplicationContext(), EditMorphologyCounts.class);
                    gotoEditCount.putExtra("morphKey", morphKey);
                    startActivity(gotoEditCount);
                }
            });
        }
        if(btn1!=null)
        {
            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //final MediaPlayer btnChangeSound = MediaPlayer.create(getApplicationContext(),R.raw.button_changed);
                    if(currentButton==null)
                        currentButton = "btn1";
                    else
                    {
                        if(!"btn1".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn1";
                        }
                    }
                    try{
                        String[] btnStrings = btn1.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                            btnStrings[1].trim().indexOf("%"));

                        NormalCount = Integer.valueOf(btnCount);
                        NormalProp = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + NormalCount + "(" + String.format("%.2f",NormalProp) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            NormalCount = NormalCount + 1;
                            Constant.sum = Constant.sum + 1;
                            NormalProp = (NormalCount*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + NormalCount + "(" + String.format("%.2f",NormalProp) + "%" + ")";
                            btn1.setText(newTxt);
                            newTxt = btnLbl + "=" + NormalCount + "(" + String.format("%.2f",NormalProp) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }

        if(btn2!=null)
        {
            tv.setText("Total Count:"+Constant.sum);
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //final MediaPlayer btnChangeSound = MediaPlayer.create(getApplicationContext(),R.raw.button_changed);
                    if(currentButton==null)
                        currentButton = "btn2";
                    else
                    {
                        if(!"btn2".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn2";
                        }
                    }

                    try{
                        String[] btnStrings = btn2.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb2Count = Integer.valueOf(btnCount);
                        Lb2Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb2Count + "(" + String.format("%.2f",Lb2Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb2Count = Lb2Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb2Prop = (Lb2Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb2Count + "(" + String.format("%.2f",Lb2Prop) + "%" + ")";
                            btn2.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb2Count + "(" + String.format("%.2f",Lb2Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn3!=null)
        {
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentButton==null)
                        currentButton = "btn3";
                    else
                    {
                        if(!"btn3".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn3";
                        }
                    }

                    try{
                        String[] btnStrings = btn3.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb3Count = Integer.valueOf(btnCount);
                        Lb3Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb3Count + "(" + String.format("%.2f",Lb3Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb3Count = Lb3Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb3Prop = (Lb3Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb3Count + "(" + String.format("%.2f",Lb3Prop) + "%" + ")";
                            btn3.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb3Count + "(" + String.format("%.2f",Lb3Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn4!=null)
        {
            btn4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentButton==null)
                        currentButton = "btn4";
                    else
                    {
                        if(!"btn4".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn4";
                        }
                    }

                    try{
                        String[] btnStrings = btn4.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb4Count = Integer.valueOf(btnCount);
                        Lb4Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb4Count + "(" + String.format("%.2f",Lb4Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb4Count = Lb4Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb4Prop = (Lb4Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb4Count + "(" + String.format("%.2f",Lb4Prop) + "%" + ")";
                            btn4.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb4Count + "(" + String.format("%.2f",Lb4Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn5!=null)
        {
            btn5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentButton==null)
                        currentButton = "btn5";
                    else
                    {
                        if(!"btn5".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn5";
                        }
                    }

                    try{
                        String[] btnStrings = btn5.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb5Count = Integer.valueOf(btnCount);
                        Lb5Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb5Count + "(" + String.format("%.2f",Lb5Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb5Count = Lb5Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb5Prop = (Lb5Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb5Count + "(" + String.format("%.2f",Lb5Prop) + "%" + ")";
                            btn5.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb5Count + "(" + String.format("%.2f",Lb5Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn6!=null)
        {
            btn6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentButton==null)
                        currentButton = "btn6";
                    else
                    {
                        if(!"btn6".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn6";
                        }
                    }

                    try{
                        String[] btnStrings = btn6.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb6Count = Integer.valueOf(btnCount);
                        Lb6Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb6Count + "(" + String.format("%.2f",Lb6Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb6Count = Lb6Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb6Prop = (Lb6Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb6Count + "(" + String.format("%.2f",Lb6Prop) + "%" + ")";
                            btn6.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb6Count + "(" + String.format("%.2f",Lb6Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn7!=null)
        {
            btn7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentButton==null)
                        currentButton = "btn7";
                    else
                    {
                        if(!"btn7".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn7";
                        }
                    }

                    try{
                        String[] btnStrings = btn7.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb7Count = Integer.valueOf(btnCount);
                        Lb7Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb7Count + "(" + String.format("%.2f",Lb7Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb7Count = Lb7Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb7Prop = (Lb7Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb7Count + "(" + String.format("%.2f",Lb7Prop) + "%" + ")";
                            btn7.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb7Count + "(" + String.format("%.2f",Lb7Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn8!=null)
        {
            btn8.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(currentButton==null)
                        currentButton = "btn8";
                    else
                    {
                        if(!"btn8".equalsIgnoreCase(currentButton))
                        {
                            //make sound
                            btnChangeSound.start();
                            currentButton = "btn8";
                        }
                    }

                    try{
                        String[] btnStrings = btn8.getText().toString().trim().split(":");
                        String btnLbl = btnStrings[0].trim();
                        String btnCount = btnStrings[1].trim().substring(0, btnStrings[1].trim().indexOf("("));
                        String btnprop = btnStrings[1].trim().substring(btnStrings[1].trim().indexOf("(") + 1,
                                btnStrings[1].trim().indexOf("%"));

                        Lb8Count = Integer.valueOf(btnCount);
                        Lb8Prop = Double.valueOf(btnprop);
                        if(Constant.sum < limit)
                        {
                            String oldTxt = btnLbl + "=" + Lb8Count + "(" + String.format("%.2f",Lb8Prop) + "%" + ")";
                            morphologyCounts.remove(oldTxt);
                            Lb8Count = Lb8Count + 1;
                            Constant.sum = Constant.sum + 1;
                            Lb8Prop = (Lb8Count*100.0)/Constant.sum;
                            String newTxt = btnLbl + ":" + Lb8Count + "(" + String.format("%.2f",Lb8Prop) + "%" + ")";
                            btn8.setText(newTxt);
                            newTxt = btnLbl + "=" + Lb8Count + "(" + String.format("%.2f",Lb8Prop) + "%" + ")";
                            morphologyCounts.add(newTxt);
                        }
                        else if(Constant.sum == limit)
                        {
                            limitRchdSound.start();
                            Constant.sum++;
                        }


                        if(Constant.sum<limit)
                            tv.setText("Total Count:"+Constant.sum);
                        else
                            tv.setText("Total Count:"+limit);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    @Override
    public void onPause()
    {
        super.onPause();
        if(morphologyCounts!=null && morphologyCounts.size()>0)
        {
            //capture date of collection
            if(morphologyCounts!=null && morphologyCounts.size()>0)
            {
                Date cDate = new Date();
                String fDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss").format(cDate);
                String timestamp = null;
                for(String item: morphologyCounts)
                {
                    if(item.contains("TimeStamp"))
                    {
                        timestamp = item;
                    }
                }
                if(timestamp!=null)
                    morphologyCounts.remove(timestamp);
                morphologyCounts.add("TimeStamp="+fDate);
            }
            //saving count
            morphologyCounts.add("Threshold="+this.limit);
            SharedPrefUtil.saveGroup(getApplicationContext(),Constant.PREFS_BULL_MORPHOLOGY_INFO,morphKey,morphologyCounts);
            //saving key
            final Set<String> keySet = SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MORPHOLOGY_COUNT_KEYS,
                    Constant.KEY_MORPHOLOGY_COUNT_KEY);
            if(keySet != null && !keySet.contains(morphKey))
            {
                Set<String> keySet1 = new HashSet<String>();
                Iterator<String> it = keySet.iterator();
                while(it.hasNext())
                {
                    keySet1.add(it.next());
                }
                keySet1.add(morphKey);
                SharedPrefUtil.clear_pref(getApplicationContext(),Constant.PREFS_MORPHOLOGY_COUNT_KEYS);
                SharedPrefUtil.saveGroup(getApplicationContext(),
                        Constant.PREFS_MORPHOLOGY_COUNT_KEYS,Constant.KEY_MORPHOLOGY_COUNT_KEY, keySet1);
            }
            else if(keySet==null)
            {
                Set<String> keySet1 = new HashSet<String>();
                keySet1.add(morphKey);
                SharedPrefUtil.saveGroup(getApplicationContext(),
                        Constant.PREFS_MORPHOLOGY_COUNT_KEYS,Constant.KEY_MORPHOLOGY_COUNT_KEY, keySet1);

            }
        }
    }



}
