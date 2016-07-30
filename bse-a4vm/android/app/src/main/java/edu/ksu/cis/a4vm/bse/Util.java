package edu.ksu.cis.a4vm.bse;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;


/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class Util
{
    public static Boolean valueOfColor(Context ctx, String color) throws NumberFormatException
    {
        if(ctx!=null && color!=null)
        {
            if(Integer.valueOf(color) == ContextCompat.getColor(ctx, R.color.colorAccent))
                return true;
            else
                return false;
        }
        return false;
    }
    public static ToggleButton changeToggleColor(ToggleButton togBtn, Context ctx)
    {
        if(togBtn.getCurrentTextColor()== ContextCompat.getColor(ctx, R.color.lightBlue))
        {
            togBtn.setTextColor(ContextCompat.getColor(ctx, R.color.colorAccent));
        }
        else if(togBtn.getCurrentTextColor()==ContextCompat.getColor(ctx, R.color.colorAccent))
        {
            togBtn.setTextColor(ContextCompat.getColor(ctx, R.color.lightBlue));
        }
        return togBtn;
    }

    public static boolean setFields(Set<String> arg1, Set<EditText> arg2) {
        Set<String> set1 = arg1;
        Set<EditText> set2 = arg2;
        if (set1 != null && set2 != null) {
            Iterator<String> it1 = set1.iterator();
            while (it1.hasNext()) {
                String[] entry = it1.next().split("=");
                Iterator<EditText> it2 = set2.iterator();
                while (it2.hasNext()) {
                    EditText et = it2.next();
                    if (entry != null && entry.length == 2) {
                        String fieldLabel = et.getHint().toString().trim();
                        if (fieldLabel != null && fieldLabel.equalsIgnoreCase(entry[0].trim())) {
                            et.setText(entry[1].trim());
                            set2.remove(et);
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }


            }
            return true;
        }
        return false;
    }


    public static void setToggleButtons(Set<String> arg1, Set<ToggleButton> arg2)
    {
        Set<String> set1 = arg1;
        Set<ToggleButton> set2 = arg2;
        if (set1 != null && set2 != null) {
            Iterator<String> it1 = set1.iterator();
            while (it1.hasNext()) {
                String[] entry = it1.next().split("=");
                Iterator<ToggleButton> it2 = set2.iterator();
                while (it2.hasNext()) {
                    ToggleButton et = it2.next();
                    if (entry != null && entry.length == 2) {
                        String fieldLabel = et.getText().toString().trim();
                        if (fieldLabel != null && fieldLabel.equalsIgnoreCase(entry[0].trim())) {
                            et.setTextColor(Integer.parseInt(entry[1].trim()));
                            set2.remove(et);
                            break;
                        }
                    }
                else{
                        break;
                    }
                }


            }
        }
    }

    public static void setCheckBoxes(Set<String> arg1, Set<CheckBox> arg2)
    {
        Set<String> set1 = arg1;
        Set<CheckBox> set2 = arg2;
        if (set1 != null && set2 != null) {
            Iterator<String> it1 = set1.iterator();
            while (it1.hasNext()) {
                String[] entry = it1.next().split("=");
                Iterator<CheckBox> it2 = set2.iterator();
                while (it2.hasNext()) {
                    CheckBox et = it2.next();
                    if (entry != null && entry.length == 2) {
                        String fieldLabel = Integer.toString(et.getId()).trim();
                        if (fieldLabel != null && fieldLabel.equalsIgnoreCase(entry[0].trim())) {
                            et.setChecked(true);
                            //set2.remove(et);
                            break;
                        }
                    }
                    else{
                        break;
                    }
                }


            }
        }
    }

    public static void setAllCheckBoxes(Set<CheckBox> boxes, Boolean checked)
    {
        if(boxes != null)
        {
            for(CheckBox b: boxes)
            {
                b.setChecked(checked);
            }
        }

    }

    public static Set<String> loadBullIds(Set<String> s1, String grpId, String id1, String id2, String id3, String id4, String prefix, Context ctx)
    {
        Set<String> s2 =  null;
        if(s1 != null && (id1!=null || id2!=null || id3!=null || id4!=null))
        {
            s2 = new LinkedHashSet<>();
            for(String item : s1)
            {

                Set<String> bullInfo = null;
                bullInfo = SharedPrefUtil.getValue(ctx, Constant.PREFS_BULL_INFO, item);
                if(bullInfo != null)
                {
                    for(String group : bullInfo)
                    {
                        String[] entry = group.split("=");
                        if(entry != null && entry.length==2 &&
                                    (entry[0].equalsIgnoreCase(id1) || entry[0].equalsIgnoreCase(id2)
                                            || entry[0].equalsIgnoreCase(id3) || entry[0].equalsIgnoreCase(id4)))
                        {
                            s2.add(prefix + ":" + entry[1]);
                            break;
                        }
                    }
                }


            }
            return s2;
        }
        return s2;
    }

    public static String getKey(HashSet<String> keys)
    {
        Random key = new Random();
        String randNo = Integer.toString(Math.abs(key.nextInt()));
        if(keys!=null && keys.contains(randNo))
        {
            randNo = getKey(keys);
        }
        return randNo;
    }

    public static String getBullKey(String grpKey,Set<String> keys)
    {
        String randNo = Integer.toString(Math.abs(new Random().nextInt()));
        String bullKey = grpKey + "_" + randNo;
        if(keys!=null && keys.contains(bullKey)) {
            bullKey = getBullKey(grpKey, keys);
        }
        return bullKey;
    }




    public static int get_days_of_a_month(int month, int year)
    {
        if(month==1)
            return 31;
        else if(year%4==0 && month==2)
            return 29;
        else if(year%4!=0 && month==2)
            return 28;
        else if(month==3)
            return 31;
        else if(month==4)
            return 30;
        else if(month==5)
            return 31;
        else if(month==6)
            return 30;
        else if(month==7)
            return 31;
        else if(month==8)
            return 31;
        else if(month==9)
            return 30;
        else if(month==10)
            return 31;
        else if(month==11)
            return 30;
        else if(month==12)
            return 31;
        else
            return 0;
    }

    public static boolean isEmailValid(String email)
    {
        org.apache.commons.validator.routines.EmailValidator emv = org.apache.commons.validator.routines.EmailValidator.getInstance();
        if(email.length()==0 || emv.isValid(email))
        {
            return true;
        }
        else
            return false;
    }

    public static String getAge(int year, int month){
        int todayYr = Calendar.getInstance().get(Calendar.YEAR);
        int todayMth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int birthYr = year;
        int birthMth = month;
        int ageY = 0;
        int ageM = 0;


        if (todayYr >= birthYr) {
            ageY = todayYr - birthYr;
            if (todayMth >= birthMth)
                ageM = todayMth - birthMth;
            else {
                ageM = todayMth + (12 - birthMth);
                ageY = ageY - 1;
            }
        }
        return(ageY + "," + ageM);
    }

}
