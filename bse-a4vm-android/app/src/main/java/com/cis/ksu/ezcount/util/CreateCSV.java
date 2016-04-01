package com.cis.ksu.ezcount.util;

import android.content.Context;

import com.cis.ksu.ezcount.Constants.Constant;
import com.cis.ksu.ezcount.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class CreateCSV {

    public static ArrayList<ArrayList> getData(Context ctx, String groupKey)
    {
        try{
            ArrayList<ArrayList> rows = null;
            ArrayList<String> row = null;
            final Set<String> bullKeys = SharedPrefUtil.getValue(ctx, Constant.PREFS_BULL_INFO,Constant.KEY_BULL);
            final Set<String> vetInfo = SharedPrefUtil.getValue(ctx, Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
            final Set<String> groupInfo = SharedPrefUtil.getValue(ctx,Constant.PREFS_GROUP_INFO,groupKey);



                if(bullKeys!=null && !bullKeys.isEmpty())
                {
                    rows = new ArrayList<ArrayList>();
                    for(String bullKey : bullKeys)
                    {
                        String[] split_bullKey = bullKey.split("_");
                        if(split_bullKey!=null && split_bullKey.length==2 && groupKey.equalsIgnoreCase(split_bullKey[0]))
                        {

                            String uuid = null;
                            final Set<String> morphKeys = SharedPrefUtil.getValue(ctx,Constant.PREFS_MORPHOLOGY_COUNT_KEYS,
                                    Constant.KEY_MORPHOLOGY_COUNT_KEY);
                            if(morphKeys!=null && !morphKeys.isEmpty())
                            {
                                for(String key: morphKeys)
                                {
                                    String[] split_key = key.split("_");
                                    String tmpBullKey = split_key[0]+"_"+split_key[1];
                                    try{
                                        if(split_key!=null && split_key.length==3
                                                && tmpBullKey.equalsIgnoreCase(bullKey))
                                        {
                                            row = new ArrayList(103);
                                            for(int i=0;i<103;i++)
                                            {
                                                row.add("");
                                            }
                                            final Set<String> LabelCounts = SharedPrefUtil.getValue(ctx,
                                                    Constant.PREFS_BULL_MORPHOLOGY_INFO, key);
                                            if(LabelCounts!=null && !LabelCounts.isEmpty())
                                            {
                                                int i=54;
                                                for(String item: LabelCounts)
                                                {
                                                    String[] arrItems = item.split("=");
                                                    if(!"Timestamp".equalsIgnoreCase(arrItems[0]))
                                                    {
                                                        row.set(i,arrItems[1]);
                                                        row.set(i+1,arrItems[0]);
                                                        i = i+2;
                                                    }
                                                }
                                            }
                                            if(groupInfo!=null && !groupInfo.isEmpty())
                                            {
                                                for(String item : groupInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 && "Ranch Name".equalsIgnoreCase(split_item[0]))
                                                        row.set(91,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Rancher Name".equalsIgnoreCase(split_item[0]))
                                                        row.set(89,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Email".equalsIgnoreCase(split_item[0]))
                                                        row.set(85,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address1".equalsIgnoreCase(split_item[0]))
                                                        row.set(82,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address2".equalsIgnoreCase(split_item[0]))
                                                        row.set(83,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "City".equalsIgnoreCase(split_item[0]))
                                                        row.set(84,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "State".equalsIgnoreCase(split_item[0]))
                                                        row.set(87,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Zip".equalsIgnoreCase(split_item[0]))
                                                        row.set(88,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Phone".equalsIgnoreCase(split_item[0]))
                                                        row.set(86,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Timestamp".equalsIgnoreCase(split_item[0])) {
                                                        uuid = split_item[1];
                                                    }
                                                    //rows.add(row);
                                                }
                                            }

                                            Set<String> bullInfo = SharedPrefUtil.getValue(ctx,Constant.PREFS_BULL_INFO,bullKey);
                                            if(bullInfo!=null && !bullInfo.isEmpty())
                                            {
                                                String id=null;
                                                String ageYr = null;
                                                String ageMth = null;
                                                String age = null;
                                                for(String item : bullInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 && "Tag".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(7,split_item[1]);
                                                        id = split_item[1];
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Tattoo".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(8,split_item[1]);
                                                        id = split_item[1];
                                                    }

                                                    else if(split_item!=null && split_item.length==2 && "RFID".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(6,split_item[1]);
                                                        id = split_item[1];
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Brand".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(2,split_item[1]);
                                                        id = split_item[1];
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "yyyy-mm-dd".equalsIgnoreCase(split_item[0]))
                                                        row.set(4,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Age(Years)".equalsIgnoreCase(split_item[0]))
                                                        ageYr = split_item[1];
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Age(Months)".equalsIgnoreCase(split_item[0]))
                                                        ageMth = split_item[1];
                                                    else if(split_item!=null && split_item.length==2 && "Lot".equalsIgnoreCase(split_item[0]))
                                                        row.set(5,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Breed".equalsIgnoreCase(split_item[0]))
                                                        row.set(3,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Comments and Other Info".equalsIgnoreCase(split_item[0]))
                                                        row.set(9,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Timestamp".equalsIgnoreCase(split_item[0]))
                                                        row.set(45,split_item[1]);
                                                }
                                                uuid = uuid + " " + id;
                                                age = ageYr + " Years " + ageMth + " Months";
                                                row.set(0,age);

                                            }

                                            if(vetInfo!=null && !vetInfo.isEmpty())
                                            {
                                                for(String item : vetInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 && "First Name".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(101,split_item[1]);
                                                        uuid = uuid + " " + split_item[1];
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Last Name".equalsIgnoreCase(split_item[0]))
                                                        row.set(102,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Clinic name".equalsIgnoreCase(split_item[0]))
                                                        row.set(100,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address1".equalsIgnoreCase(split_item[0]))
                                                        row.set(93,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address2".equalsIgnoreCase(split_item[0]))
                                                        row.set(94,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "City".equalsIgnoreCase(split_item[0]))
                                                        row.set(95,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "State".equalsIgnoreCase(split_item[0]))
                                                        row.set(98,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Zip".equalsIgnoreCase(split_item[0]))
                                                        row.set(99,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Email".equalsIgnoreCase(split_item[0]))
                                                        row.set(96,split_item[1]);
                                                }
                                            }


                                            Set<String> matingInfo = SharedPrefUtil.getValue(ctx, Constant.PREFS_MATING_INFO,bullKey);
                                            if(matingInfo!=null && !matingInfo.isEmpty())
                                            {
                                                for(String item : matingInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 &&
                                                            "Breeding Season Used".equalsIgnoreCase(split_item[0]))
                                                        row.set(51,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Performance Description".equalsIgnoreCase(split_item[0]))
                                                        row.set(50,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Other Comments".equalsIgnoreCase(split_item[0]))
                                                        row.set(48,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "GOOD".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(49,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "POOR".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(49,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "UNKNOWN".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(49,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "OTHER".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(49,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "MULTI-SIRE".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(52,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "SINGLE-SIRE".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(52,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }
                                                    }
                                                }
                                            }

                                            Set<String> phyParamsInfo = SharedPrefUtil.getValue(ctx, Constant.PREFS_PHY_PRAMS_INFO,bullKey);
                                            if(phyParamsInfo!=null && !phyParamsInfo.isEmpty())
                                            {
                                                for(String item : phyParamsInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2
                                                            && "Eyes Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(10,split_item[1]);
                                                        row.set(11,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(12)))
                                                            row.set(12,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Eyes Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(12,"True");
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Feet Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(13,split_item[1]);
                                                        row.set(14,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(15)))
                                                            row.set(15,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Feet Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(15,"True");



                                                    else if(split_item!=null && split_item.length==2
                                                            && "Legs Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(16,split_item[1]);
                                                        row.set(17,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(18)))
                                                            row.set(18,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Legs Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(18,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Testicles Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(19,split_item[1]);
                                                        row.set(20,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(21)))
                                                            row.set(21,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Testicles Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(21,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Accessory Sex glands description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(22,split_item[1]);
                                                        row.set(23,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(24)))
                                                            row.set(24,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Accessory Sex glands description".equalsIgnoreCase(split_item[1]))
                                                        row.set(24,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Inguinal Rings Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(25,split_item[1]);
                                                        row.set(26,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(27)))
                                                            row.set(27,"True");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Inguinal Rings Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(27,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Scrotal Shape description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(28,split_item[1]);
                                                        row.set(29,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(30)))
                                                            row.set(30,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Scrotal Shape description".equalsIgnoreCase(split_item[1]))
                                                        row.set(30,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Epidydimides description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(31,split_item[1]);
                                                        row.set(32,split_item[0]);
                                                        if(!"True".equalsIgnoreCase(row.get(33)))
                                                            row.set(33,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Epidydimides description".equalsIgnoreCase(split_item[1]))
                                                        row.set(33,"True");
                                                }
                                            }

                                            Set<String> measurementsInfo = SharedPrefUtil.getValue(ctx, Constant.PREFS_BULL_MEASUREMENT_INFO,
                                                    bullKey);
                                            if(measurementsInfo!=null && !measurementsInfo.isEmpty())
                                            {
                                                for(String item : measurementsInfo) {
                                                    String[] split_item = item.split("=");
                                                    if (split_item != null && split_item.length == 2
                                                            && "Scrotal circumference (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(81, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Body Condition(0-9)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(74, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Pelvic X measure (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(79, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Pelvic Y measure (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(80, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Frame score (1-20)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(76, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Hip Height (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(77, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Other measurement".equalsIgnoreCase(split_item[0])) {
                                                        row.set(75, split_item[1]);
                                                    }

                                                    row.set(78, "cm");

                                                }
                                            }

                                            Set<String> motilityInfo = SharedPrefUtil.getValue(ctx,Constant.PREFS_MOTILITY_INFO,bullKey);
                                            if(motilityInfo!=null && !motilityInfo.isEmpty())
                                            {
                                                for(String item:motilityInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 &&
                                                            "Individual Motility".equalsIgnoreCase(split_item[0]))
                                                        row.set(72,split_item[1]);

                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Motility %".equalsIgnoreCase(split_item[0]))
                                                        row.set(73,split_item[1]);

                                                    else if(split_item!=null && split_item.length==2 && "Poor".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(71,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Fair".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(71,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Good".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(71,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Very Good".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(71,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                }
                                            }

                                            Set<String> classificationInfo = SharedPrefUtil.getValue(ctx,Constant.PREFS_CLASSIFICATION_INFO,
                                                    bullKey);
                                            if(classificationInfo!=null && !classificationInfo.isEmpty())
                                            {
                                                for(String item:classificationInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 &&
                                                            "Comments and Other Info".equalsIgnoreCase(split_item[0]))
                                                        row.set(47,split_item[1]);


                                                    else if(split_item!=null && split_item.length==2
                                                            && "Satisfactory".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(46,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Deferred".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(46,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Questionable".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(46,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Unsatisfactory".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(46,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                }
                                            }
                                            row.set(92,uuid);
                                            rows.add(row);
                                        }
                                    }
                                    catch(Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                }
                            }


                        }
                        //rows.add(row);
                    }
                }



            return rows;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
