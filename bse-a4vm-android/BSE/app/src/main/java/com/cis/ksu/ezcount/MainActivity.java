package com.cis.ksu.ezcount;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tx = (TextView) findViewById(R.id.collections);
        tx.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SharedPrefUtil.getValue(getApplicationContext(),
                        Constant.PREFS_FILE_VET_INFO, Constant.KEY_VET) != null) {
                    Intent goToCollections = new Intent(getApplicationContext(), Collections.class);
                    startActivity(goToCollections);
                }
                else
                    Toast.makeText(getApplicationContext(),"Go to settings and configure app first",Toast.LENGTH_SHORT).show();

            }
        });

        TextView tx1 = (TextView) findViewById(R.id.usrSetting);
        tx1.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent goToSettings = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(goToSettings);
            }
        });

        TextView txDlt = (TextView) findViewById(R.id.DeleteHistory);
        txDlt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) 
            {
                String msg = new deleteHistory().doInBackground();
                new deleteHistory().onPostExecute(msg);

            }
        });
    }

    private class deleteHistory extends AsyncTask<String, String, String>
    {
        protected String doInBackground(String... param)
        {
            String msg = null;
            Set<String> tmpGroupKeys = null;
            Set<String> tmpBullKeys = null;
            Set<String> tmpMorphKeys = null;
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            final Set<String> groupKeys = SharedPrefUtil.getValue(getApplicationContext(), Constant.PREFS_GROUP_INFO
                        ,Constant.KEY_GROUP);
            if(groupKeys!=null)
            {


                    tmpGroupKeys = new HashSet<String>();
                    tmpBullKeys = new HashSet<String>();
                    tmpMorphKeys = new HashSet<String>();
                    try
                    {

                        for(String grpKey : groupKeys)
                        {
                            final Set<String> grpInfo = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_GROUP_INFO,grpKey);
                            long days = 0;
                            if(grpInfo!=null)
                            {
                                for(String item : grpInfo)
                                {
                                    String[] split_item = item.split("=");
                                    if(split_item!=null && split_item.length==2 && "Timestamp".equalsIgnoreCase(split_item[0]))
                                    {
                                        try{
                                            Date dateCaptured = sdf.parse(split_item[1]);
                                            Date today = new Date();
                                            long diff = today.getTime() - dateCaptured.getTime();
                                            days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
                                            //Toast.makeText(getApplicationContext(),"Days="+days,Toast.LENGTH_SHORT).show();
                                        }
                                        catch(ParseException pe)
                                        {
                                            pe.printStackTrace();
                                        }


                                    }
                                }

                                if(days<=30)
                                {

                                    SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_GROUP_INFO,grpKey);
                                    final Set<String> bullkeys = SharedPrefUtil.getValue(getApplicationContext(),Constant.PREFS_BULL_INFO,
                                            Constant.KEY_BULL);
                                    if(bullkeys!=null)
                                    {
                                        for(String item: bullkeys)
                                        {
                                            String[] split_item = item.split("_");
                                            if(grpKey.equalsIgnoreCase(split_item[0]))
                                            {
                                                SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_BULL_INFO,item);
                                                SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_MATING_INFO,item);
                                                SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_PHY_PRAMS_INFO,item);
                                                SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_BULL_MEASUREMENT_INFO,item);
                                                SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_CLASSIFICATION_INFO,item);
                                                SharedPrefUtil.removeKey(getApplicationContext(),Constant.PREFS_COMMENTS_INFO,item);
                                                SharedPrefUtil.removeKey(getApplicationContext(), Constant.PREFS_MOTILITY_INFO, item);
                                                final Set<String> morphkeys = SharedPrefUtil.getValue(getApplicationContext(),
                                                        Constant.PREFS_MORPHOLOGY_COUNT_KEYS, Constant.KEY_MORPHOLOGY_COUNT_KEY);
                                                if(morphkeys!=null)
                                                {
                                                    for(String morphItem: morphkeys)
                                                    {
                                                        if(morphItem.contains(grpKey))
                                                        {
                                                            try{
                                                                SharedPrefUtil.removeKey(getApplicationContext(),
                                                                        Constant.PREFS_BULL_MORPHOLOGY_INFO, morphItem);
                                                            }
                                                            catch(Exception e)
                                                            {
                                                                e.printStackTrace();
                                                            }



                                                        }
                                                        else
                                                        {
                                                            tmpMorphKeys.add(morphItem);
                                                        }
                                                    }
                                                }


                                            }
                                            else {
                                                tmpBullKeys.add(item);
                                            }

                                        }
                                    }
                                }
                                else
                                {
                                    tmpGroupKeys.add(grpKey);
                                }
                            }
                        }

                        if(tmpGroupKeys!=null && !tmpGroupKeys.isEmpty())
                        {
                            SharedPrefUtil.clear_pref(getApplicationContext(),Constant.PREFS_GROUP_INFO);
                            SharedPrefUtil.saveGroup(getApplicationContext(),Constant.PREFS_GROUP_INFO,
                                    Constant.KEY_GROUP,tmpGroupKeys);
                        }
                        if(tmpBullKeys!=null && !tmpBullKeys.isEmpty())
                        {
                            SharedPrefUtil.clear_pref(getApplicationContext(),Constant.PREFS_BULL_INFO);
                            SharedPrefUtil.saveGroup(getApplicationContext(),Constant.PREFS_BULL_INFO,
                                    Constant.KEY_BULL,tmpBullKeys);
                        }
                        if(tmpMorphKeys!=null && !tmpMorphKeys.isEmpty())
                        {
                            SharedPrefUtil.clear_pref(getApplicationContext(),Constant.PREFS_MORPHOLOGY_COUNT_KEYS);
                            SharedPrefUtil.saveGroup(getApplicationContext(),Constant.PREFS_MORPHOLOGY_COUNT_KEYS,
                                    Constant.KEY_MORPHOLOGY_COUNT_KEY,tmpMorphKeys);
                        }

                        msg="Deleted successfully";
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                        msg = "Oops! delete unsuccessful";
                        return msg;
                    }


            }




            return msg;
        }

        protected void onPostExecute(String msg)
        {
            Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
        }

    }
}
