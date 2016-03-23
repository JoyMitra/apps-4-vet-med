package com.cis.ksu.ezcount;

import android.app.ActionBar;
import android.app.usage.ConfigurationStats;
import android.content.Intent;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.util.SharedPrefUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MorphologyCount extends AppCompatActivity {

    private String morphKey = null;
    private Set<String> labels = null;
    private Set<String> initVals = null;
    private Set<String> morphologyCounts = null;
    private Button btn1 = null;
    private Button btn2 = null;
    private Button btn3 = null;
    private Button btn4 = null;
    private Button btn5 = null;
    private Button btn6 = null;
    private Button btn7 = null;
    private Button btn8 = null;

    private RelativeLayout layout = null;
    private String currentButton = null;


    //final MediaPlayer limitRchdSound = MediaPlayer.create(getApplicationContext(),R.raw.limit_reached);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morphology_count);

        morphKey = getIntent().getStringExtra("morphKey");

    }

    @Override
    public void onResume()
    {
        super.onResume();
        final MediaPlayer btnChangeSound = MediaPlayer.create(getApplicationContext(),R.raw.button_changed);
        final MediaPlayer limitRchdSound = MediaPlayer.create(getApplicationContext(), R.raw.limit_reached);
        //Toast.makeText(getApplicationContext(),morphKey,Toast.LENGTH_SHORT).show();
        labels = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_FILE_MORPH_INFO,Constant.KEY_MORPHOLOGY);
        initVals = (HashSet<String>) SharedPrefUtil.getValue(getApplicationContext(),
                Constant.PREFS_BULL_MORPHOLOGY_INFO, morphKey);


        //create buttons
        if(labels != null)
        {
            Iterator<String> it = labels.iterator();
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn1 = new Button(this);
                btn1.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn1.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn1.setId(R.id.button1);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.ALIGN_LEFT);
                p.setMargins(0,20,0,0);
                btn1.setLayoutParams(p);
                btn1.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button));
                btn1.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));

            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn2 = new Button(this);
                btn2.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn2.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn2.setId(R.id.button2);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.RIGHT_OF,R.id.button1);
                p.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                p.setMargins(0,20,0,0);
                btn2.setLayoutParams(p);
                btn2.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button2));
                btn2.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));

            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn3 = new Button(this);
                btn3.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn3.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn3.setId(R.id.button3);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.BELOW, R.id.button1);
                p.setMargins(0,20,0,0);
                btn3.setLayoutParams(p);
                btn3.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button3));
                btn3.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn4 = new Button(this);
                btn4.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn4.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn4.setId(R.id.button4);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.RIGHT_OF, R.id.button3);
                p.addRule(RelativeLayout.BELOW,R.id.button1);
                p.setMargins(0,20,0,0);
                btn4.setLayoutParams(p);
                btn4.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button4));
                btn4.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn5 = new Button(this);
                btn5.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn5.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn5.setId(R.id.button5);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.BELOW, R.id.button3);
                p.setMargins(0, 20, 0, 0);
                btn5.setLayoutParams(p);
                btn5.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button5));
                btn5.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn6 = new Button(this);
                btn6.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn6.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn6.setId(R.id.button6);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.setMargins(0,20,0,0);
                p.addRule(RelativeLayout.RIGHT_OF, R.id.button5);
                p.addRule(RelativeLayout.BELOW, R.id.button3);
                btn6.setLayoutParams(p);
                btn6.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button6));
                btn6.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn7 = new Button(this);
                btn7.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn7.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn7.setId(R.id.button7);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.BELOW, R.id.button5);
                p.setMargins(0, 20, 0, 0);
                btn7.setLayoutParams(p);
                btn7.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button7));
                btn7.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
            if(it.hasNext())
            {
                String label = it.next().trim();
                /*String limit = SharedPrefUtil.getSingleValue(getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, label);*/
                btn8 = new Button(this);
                btn8.setText(label +":0");
                if(initVals!=null && !initVals.isEmpty())
                {
                    for(String initVal : initVals)
                    {
                        String[] entry = initVal.split("=");
                        if(entry!=null && entry.length==2 && label.equalsIgnoreCase(entry[0]))
                        {
                            btn8.setText(label +":"+entry[1].trim());
                        }
                    }
                }
                btn8.setId(R.id.button8);
                RelativeLayout.LayoutParams p = new RelativeLayout.LayoutParams(500,300);
                p.addRule(RelativeLayout.RIGHT_OF, R.id.button7);
                p.addRule(RelativeLayout.BELOW, R.id.button5);
                p.setMargins(0,20,0,0);
                btn8.setLayoutParams(p);
                btn8.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.button8));
                btn8.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
            }
        }

        layout = (RelativeLayout) findViewById(R.id.morphCount);
        if(layout != null)
        {
            if(btn1!=null)
                layout.addView(btn1);
            if(btn2!=null)
                layout.addView(btn2);
            if(btn3!=null)
                layout.addView(btn3);
            if(btn4!=null)
                layout.addView(btn4);
            if(btn5!=null)
                layout.addView(btn5);
            if(btn6!=null)
                layout.addView(btn6);
            if(btn7!=null)
                layout.addView(btn7);
            if(btn8!=null)
                layout.addView(btn8);
        }

        /*btnChangeSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                btnChangeSound.stop();
                btnChangeSound.release();
            }
        });

        limitRchdSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                limitRchdSound.stop();
                limitRchdSound.release();
            }
        });*/

        if(btn1!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn1, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if(btn2!=null && labels!=null)
        {
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn2, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn3!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn3, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn4!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn4, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn5!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn5, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn6!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn6, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn7!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn7, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
                        Toast.makeText(getApplicationContext(),"Error occured. Restart and try again",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        if(btn8!=null && labels!=null)
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
                    morphologyCounts = Util.editMorghologyCount(getApplicationContext(),btn8, labels, morphologyCounts,limitRchdSound);
                    if(morphologyCounts==null)
                    {
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

                morphologyCounts.add("TimeStamp="+fDate);
            }
            //saving count
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
