package edu.ksu.cis.a4vm.bse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class BullExam extends AppCompatActivity {

    Button bullInfoBtn = null;
    Button matingInfoBtn = null;
    Button phyParams = null;
    Button motilityBtn = null;
    Button classificationBtn = null;
    Button commentsButton = null;
    Button morphBtn = null;
    String key = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bull_exam);
        key = getIntent().getStringExtra("bullKey");
        bullInfoBtn = (Button)findViewById(R.id.bullInfo);
        matingInfoBtn = (Button)findViewById(R.id.matingInfo);
        phyParams = (Button)findViewById(R.id.phyParms);
        motilityBtn = (Button)findViewById(R.id.motlty);
        classificationBtn = (Button)findViewById(R.id.classification);
        commentsButton = (Button)findViewById(R.id.misc);
        morphBtn = (Button)findViewById(R.id.morphInfo);
    }

    public void onResume()
    {
        super.onResume();
        key = getIntent().getStringExtra("bullKey");
        final Set<String> bullInfo = SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, key);
        if(bullInfo!=null && !bullInfo.isEmpty())
        {
            Boolean lotFilled = false;
            Boolean breedFilled = false;
            Boolean ageYearFilled = false;
            Boolean ageMthFilled = false;
            Boolean dobFilled = false;
            for(String item: bullInfo)
            {
                String[] arrItem = item.split("=");
                if(arrItem!=null && arrItem.length==2 && arrItem[0].equalsIgnoreCase("Lot"))
                {
                    lotFilled = true;
                }
                else if(arrItem!=null && arrItem.length==2 && arrItem[0].equalsIgnoreCase("Breed"))
                {
                    breedFilled = true;
                }
                else if(arrItem!=null && arrItem.length==2 && arrItem[0].equalsIgnoreCase("Age(Years)"))
                {
                    ageYearFilled = true;
                }
                else if(arrItem!=null && arrItem.length==2 && arrItem[0].equalsIgnoreCase("Age(Months)"))
                {
                    ageMthFilled = true;
                }
                else if(arrItem!=null && arrItem.length==2 && arrItem[0].equalsIgnoreCase("yyyy-mm-dd"))
                {
                    dobFilled = true;
                }

            }
            if(lotFilled && breedFilled && ageMthFilled && ageYearFilled && dobFilled)
            {
                bullInfoBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));
            }
            else
            {
                bullInfoBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
            }
        }
        else
        {
            bullInfoBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }


        final Set<String> matingInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_MATING_INFO,key);
        if(matingInfo!=null)
        {
            Boolean partiallyFilled = true;
            for(String item: matingInfo)
            {
                String[] arrItem = item.split("=");
                if(arrItem!=null && arrItem.length==2 && (arrItem[0].equalsIgnoreCase("Multi-sire")
                        || arrItem[0].equalsIgnoreCase("Single-sire") || arrItem[0].equalsIgnoreCase("Not Used"))
                        && arrItem[1].equalsIgnoreCase(String.valueOf(ContextCompat.getColor(getApplicationContext(),
                                                        R.color.colorAccent))))
                {
                    partiallyFilled = false;
                    break;
                }

            }
            if(partiallyFilled)
            {
                matingInfoBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
            }
            else
            {
                matingInfoBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));
            }
        }
        else
        {
            matingInfoBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }

        final Set<String> phyParamsInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_PHY_PRAMS_INFO,key);
        final Set<String> measureInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_BULL_MEASUREMENT_INFO,key);
        if((phyParamsInfo!=null && !phyParamsInfo.isEmpty())
                || (measureInfo!=null && !measureInfo.isEmpty()))
        {
            Boolean partiallyFilled = false;
            if(phyParamsInfo!=null && !phyParamsInfo.isEmpty())
            {
                for(String item: phyParamsInfo)
                {
                    String[] arrItem = item.split("=");
                    if(arrItem!=null && arrItem.length!=2)
                    {
                        partiallyFilled = true;
                        break;
                    }
                }
            }
            if(partiallyFilled)
            {
                phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
            }
            else if(measureInfo!=null && !measureInfo.isEmpty())
            {
                for(String item: measureInfo)
                {
                    String[] arrItem = item.split("=");
                    if(arrItem!=null && arrItem.length!=2&& !arrItem[0].equalsIgnoreCase("Other measurement")
                            && !arrItem[0].equalsIgnoreCase("Other measurement units"))
                    {
                        partiallyFilled = true;
                        break;
                    }
                }
                if(partiallyFilled)
                {
                    phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
                }
                else if(measureInfo!=null && phyParamsInfo==null)
                {
                    phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
                }
                else if(measureInfo==null && phyParamsInfo!=null)
                {
                    phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
                }
                else if(measureInfo!=null && phyParamsInfo!=null)
                {
                    phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));
                }
                else {
                    phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
                }
            }
            else
            {
                phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
            }
        }
        else
        {
            phyParams.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }


        final Set<String> motilityInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_MOTILITY_INFO,key);
        if(motilityInfo!=null && !motilityInfo.isEmpty())
        {
            Boolean partiallyFilled = false;
            for(String item: motilityInfo)
            {
                String[] arrItem = item.split("=");
                if(arrItem!=null && arrItem.length!=2)
                {
                    partiallyFilled = true;
                    break;
                }
            }
            if(partiallyFilled)
            {
                motilityBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
            }
            else
            {
                motilityBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));
            }
        }
        else
        {
            motilityBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }


        final Set<String> classifyInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_CLASSIFICATION_INFO,key);
        if(classifyInfo!=null && !classifyInfo.isEmpty())
        {
            Boolean partiallyFilled = false;
            for(String item: classifyInfo)
            {
                String[] arrItem = item.split("=");
                if(arrItem!=null && arrItem.length!=2)
                {
                    partiallyFilled = true;
                    break;
                }
            }
            if(partiallyFilled)
            {
                classificationBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.partial_fill));
            }
            else
            {
                classificationBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));
            }
        }
        else
        {
            classificationBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }

        final Set<String> commInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_COMMENTS_INFO,key);
        if(commInfo!=null && !commInfo.isEmpty())
        {

            commentsButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));

        }
        else
        {
            commentsButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }


        final Set<String> morphologyInfo = SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_MORPHOLOGY_COUNT_KEYS
                , Constant.KEY_MORPHOLOGY_COUNT_KEY);
        if(morphologyInfo!=null && !morphologyInfo.isEmpty())
        {
            Boolean filled = false;
            for(String elem: morphologyInfo)
            {
                String[] elem_split = elem.split("_");
                String bullKey = elem_split[0] + "_" + elem_split[1];
                if(bullKey.equalsIgnoreCase(key))
                {
                    filled = true;
                    break;
                }
            }
            if(filled)
            {
                morphBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.all_fill));
            }
            else
                morphBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }
        else
        {
            morphBtn.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.no_fill));
        }

        bullInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToBullInfo = new Intent(getApplicationContext(), BullInfo.class);
                goToBullInfo.putExtra("bullKey",key);
                startActivity(goToBullInfo);
            }
        });

        matingInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)!=null
                        && SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)
                        .contains(key))
                {
                    Intent goToMatingInfo = new Intent(getApplicationContext(),MatingInfo.class);
                    goToMatingInfo.putExtra("bullKey", key);
                    startActivity(goToMatingInfo);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Set BullId first",Toast.LENGTH_SHORT).show();
                }

            }
        });
        phyParams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL) != null
                        && SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)
                        .contains(key)) {
                    Intent goToPhy = new Intent(getApplicationContext(), PhysicalParameter.class);
                    goToPhy.putExtra("bullKey", key);
                    startActivity(goToPhy);
                } else {
                    Toast.makeText(getApplicationContext(), "Set BullId first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        motilityBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)!=null
                        && SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)
                        .contains(key))
                {
                    Intent goToMotility = new Intent(getApplicationContext(),Motility.class);
                    goToMotility.putExtra("bullKey",key);
                    startActivity(goToMotility);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Set BullId first", Toast.LENGTH_SHORT).show();
                }

            }
        });

        classificationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)!=null
                        && SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)
                        .contains(key))
                {
                    Intent goToClassification = new Intent(getApplicationContext(), Classification.class);
                    goToClassification.putExtra("bullKey",key);
                    startActivity(goToClassification);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Set BullId first", Toast.LENGTH_SHORT).show();
                }
            }
        });
        commentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)!=null
                        && SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)
                        .contains(key))
                {
                    Intent goToComments = new Intent(getApplicationContext(),Comments.class);
                    goToComments.putExtra("bullKey", key);
                    startActivity(goToComments);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Set BullId first", Toast.LENGTH_SHORT).show();
                }
            }
        });

        morphBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL) != null
                        && SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL)
                        .contains(key)) {
                    Intent goToMorphology = new Intent(getApplicationContext(), MorphologyDashboard.class);
                    goToMorphology.putExtra("bullKey", key);
                    startActivity(goToMorphology);
                } else {
                    Toast.makeText(getApplicationContext(), "Set BullId first", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        String[] ids = key.split("_");
        Intent goPrev = new Intent(getApplicationContext(), BullGroup.class);
        if(ids!=null && ids.length==2)
            goPrev.putExtra("grpId", ids[0]);
        else
            goPrev.putExtra("grpId", "");
        startActivity(goPrev);
    }
}
