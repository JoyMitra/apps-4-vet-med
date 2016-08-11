package edu.ksu.cs.a4vm.bse.util;

import android.content.Context;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.Util;

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
            boolean hasBulMorpholgy = false;
            final Set<String> bullKeys = SharedPrefUtil.getValue(ctx, Constant.PREFS_BULL_INFO,Constant.KEY_BULL);
            final Set<String> vetInfo = SharedPrefUtil.getValue(ctx, Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
            final Set<String> groupInfo = SharedPrefUtil.getValue(ctx,Constant.PREFS_GROUP_INFO,groupKey);


                //Constant.headers = Constant.CSV_HEADING.split(",");
                Constant.morphHeaders = new ArrayList(100);
                for(int i=0;i<100;i++)
                {
                    Constant.morphHeaders.add("");
                }
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
                                            hasBulMorpholgy = true;
                                            row = new ArrayList(103);
                                            for(int i=0;i<103;i++)
                                            {
                                                row.add("");
                                            }
                                            final Set<String> LabelCounts = SharedPrefUtil.getValue(ctx,
                                                    Constant.PREFS_BULL_MORPHOLOGY_INFO, key);
                                            ArrayList<String> morphHeaders = new ArrayList<String>(LabelCounts);
                                            Collections.sort(morphHeaders);
                                            if(morphHeaders!=null && !morphHeaders.isEmpty())
                                            {
                                                int i=77;
                                                for(String item: morphHeaders)
                                                {
                                                    String[] arrItems = item.split("=");
                                                    if(!"Timestamp".equalsIgnoreCase(arrItems[0]) && !"Threshold".equalsIgnoreCase(arrItems[0]))
                                                    {
                                                        //Constant.headers[i+1] = arrItems[0];

                                                        if(!Constant.morphHeaders.contains(arrItems[0]))
                                                        {
                                                            String lb = arrItems[0].substring(0,1).toUpperCase() + arrItems[0].substring(1);
                                                            Constant.morphHeaders.set(i-77,"morphology_" + lb + "_count");
                                                            row.set(i, arrItems[1].substring(0,arrItems[1].trim().indexOf("(")));
                                                        }
                                                        else
                                                        {
                                                            String lb = arrItems[0].substring(0,1).toUpperCase() + arrItems[0].substring(1);
                                                            int k = Constant.morphHeaders.indexOf("morphology_" + lb + "_count");
                                                            row.set(77+k, arrItems[1].substring(0,arrItems[1].trim().indexOf("(")));

                                                        }

                                                        i = i+1;
                                                    }
                                                    else if("Threshold".equalsIgnoreCase(arrItems[0]))
                                                    {
                                                        row.set(76,arrItems[1]);
                                                    }
                                                }
                                            }
                                            if(groupInfo!=null && !groupInfo.isEmpty())
                                            {
                                                for(String item : groupInfo)
                                                {
                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2 && "Ranch Name".equalsIgnoreCase(split_item[0]))
                                                        row.set(64,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Rancher Name".equalsIgnoreCase(split_item[0]))
                                                        row.set(62,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Email".equalsIgnoreCase(split_item[0]))
                                                        row.set(58,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address1".equalsIgnoreCase(split_item[0]))
                                                        row.set(55,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address2".equalsIgnoreCase(split_item[0]))
                                                        row.set(56,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "City".equalsIgnoreCase(split_item[0]))
                                                        row.set(57,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "State".equalsIgnoreCase(split_item[0]))
                                                        row.set(60,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Zip".equalsIgnoreCase(split_item[0]))
                                                        row.set(61,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Phone".equalsIgnoreCase(split_item[0]))
                                                        row.set(59,split_item[1]);
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
                                                        row.set(34,split_item[1]);
                                                }
                                                uuid = uuid + "-" + id;
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
                                                        row.set(74,split_item[1]);
                                                        uuid = uuid + "-" + split_item[1];
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Last Name".equalsIgnoreCase(split_item[0]))
                                                        row.set(75,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Clinic name".equalsIgnoreCase(split_item[0]))
                                                        row.set(73,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address1".equalsIgnoreCase(split_item[0]))
                                                        row.set(66,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Address2".equalsIgnoreCase(split_item[0]))
                                                        row.set(67,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "City".equalsIgnoreCase(split_item[0]))
                                                        row.set(68,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "State".equalsIgnoreCase(split_item[0]))
                                                        row.set(71,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Zip".equalsIgnoreCase(split_item[0]))
                                                        row.set(72,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "Email".equalsIgnoreCase(split_item[0]))
                                                        row.set(69,split_item[1]);
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
                                                        row.set(40,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Performance Description".equalsIgnoreCase(split_item[0]))
                                                        row.set(39,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Other Comments".equalsIgnoreCase(split_item[0]))
                                                        row.set(37,split_item[1]);
                                                    else if(split_item!=null && split_item.length==2 && "GOOD".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx, split_item[1]))
                                                                row.set(38,split_item[0]);
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
                                                                row.set(38,split_item[0]);
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
                                                                row.set(38,split_item[0]);
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
                                                                row.set(38,split_item[0]);
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
                                                                row.set(41,split_item[0]);
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
                                                                row.set(41,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }
                                                    }
                                                    else if(split_item!=null && split_item.length==2 && "Not Used".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(41,split_item[0]);
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
                                                    if(!"True".equalsIgnoreCase(row.get(11)))
                                                        row.set(11,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(13)))
                                                        row.set(13,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(15)))
                                                        row.set(15,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(17)))
                                                        row.set(17,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(19)))
                                                        row.set(19,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(21)))
                                                        row.set(21,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(23)))
                                                        row.set(23,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(25)))
                                                        row.set(25,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(27)))
                                                        row.set(27,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(29)))
                                                        row.set(29,"False");
                                                    if(!"True".equalsIgnoreCase(row.get(31)))
                                                        row.set(31,"False");


                                                    String[] split_item = item.split("=");
                                                    if(split_item!=null && split_item.length==2
                                                            && "Eyes Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(10,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(11)))
                                                            row.set(11,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Eyes Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(11,"True");
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Feet Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(14,split_item[1]);
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
                                                        if(!"True".equalsIgnoreCase(row.get(17)))
                                                            row.set(17,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Legs Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(17,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Testicles Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(18,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(19)))
                                                            row.set(19,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Testicles Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(19,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Accessory Sex glands description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(20,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(21)))
                                                            row.set(21,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Accessory Sex glands description".equalsIgnoreCase(split_item[1]))
                                                        row.set(21,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Inguinal Rings Description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(22,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(23)))
                                                            row.set(23,"True");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Inguinal Rings Description".equalsIgnoreCase(split_item[1]))
                                                        row.set(23,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Scrotal Shape description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(24,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(25)))
                                                            row.set(25,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Scrotal Shape description".equalsIgnoreCase(split_item[1]))
                                                        row.set(25,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Epidydimides description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(26,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(27)))
                                                            row.set(27,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Epidydimides description".equalsIgnoreCase(split_item[1]))
                                                        row.set(27,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Penis description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(28,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(29)))
                                                            row.set(29,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Penis description".equalsIgnoreCase(split_item[1]))
                                                        row.set(29,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Prepuce description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(30,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(31)))
                                                            row.set(31,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Prepuce description".equalsIgnoreCase(split_item[1]))
                                                        row.set(31,"True");

                                                    else if(split_item!=null && split_item.length==2
                                                            && "Scrotum description".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        row.set(12,split_item[1]);
                                                        if(!"True".equalsIgnoreCase(row.get(13)))
                                                            row.set(13,"False");
                                                    }
                                                    else if(split_item!=null && split_item.length==2
                                                            && "Scrotum description".equalsIgnoreCase(split_item[1]))
                                                        row.set(13,"True");
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
                                                        row.set(54, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Body Condition(0-9)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(47, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Pelvic X measure (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(52, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Pelvic Y measure (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(53, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Frame score (1-20)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(49, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Hip Height (cm)".equalsIgnoreCase(split_item[0])) {
                                                        row.set(50, split_item[1]);
                                                    }
                                                    else if (split_item != null && split_item.length == 2
                                                            && "Other measurement".equalsIgnoreCase(split_item[0])) {
                                                        row.set(48, split_item[1]);
                                                    }

                                                    row.set(51, "cm");

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
                                                        row.set(45,split_item[1]);

                                                    else if(split_item!=null && split_item.length==2 &&
                                                            "Motility %".equalsIgnoreCase(split_item[0]))
                                                        row.set(46,split_item[1]);

                                                    else if(split_item!=null && split_item.length==2 && "Poor".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(44,split_item[0]);
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
                                                                row.set(44,split_item[0]);
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
                                                                row.set(44,split_item[0]);
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
                                                                row.set(44,split_item[0]);
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
                                                        row.set(36,split_item[1]);


                                                    else if(split_item!=null && split_item.length==2
                                                            && "Satisfactory".equalsIgnoreCase(split_item[0]))
                                                    {
                                                        try{
                                                            if(Util.valueOfColor(ctx,split_item[1]))
                                                                row.set(35,split_item[0]);
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
                                                                row.set(35,split_item[0]);
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
                                                                row.set(35,split_item[0]);
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
                                                                row.set(35,split_item[0]);
                                                        }
                                                        catch(NumberFormatException ne)
                                                        {
                                                            ne.printStackTrace();
                                                            return null;
                                                        }

                                                    }
                                                }
                                            }
                                            row.set(65,uuid.replace(" ","-"));
                                            rows.add(row);
                                        }

                                    }
                                    catch(Exception e)
                                    {
                                        e.printStackTrace();
                                    }

                                }
                            }

                            if(!hasBulMorpholgy){


                                        row = new ArrayList(103);
                                        for(int i=0;i<103;i++)
                                        {
                                            row.add("");
                                        }
                                        if(groupInfo!=null && !groupInfo.isEmpty())
                                        {
                                            for(String item : groupInfo)
                                            {
                                                String[] split_item = item.split("=");
                                                if(split_item!=null && split_item.length==2 && "Ranch Name".equalsIgnoreCase(split_item[0]))
                                                    row.set(64,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Rancher Name".equalsIgnoreCase(split_item[0]))
                                                    row.set(62,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Email".equalsIgnoreCase(split_item[0]))
                                                    row.set(58,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Address1".equalsIgnoreCase(split_item[0]))
                                                    row.set(55,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Address2".equalsIgnoreCase(split_item[0]))
                                                    row.set(56,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "City".equalsIgnoreCase(split_item[0]))
                                                    row.set(57,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "State".equalsIgnoreCase(split_item[0]))
                                                    row.set(60,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Zip".equalsIgnoreCase(split_item[0]))
                                                    row.set(61,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Phone".equalsIgnoreCase(split_item[0]))
                                                    row.set(59,split_item[1]);
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
                                                    row.set(34,split_item[1]);
                                            }
                                            uuid = uuid + "-" + id;
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
                                                    row.set(74,split_item[1]);
                                                    uuid = uuid + "-" + split_item[1];
                                                }
                                                else if(split_item!=null && split_item.length==2 && "Last Name".equalsIgnoreCase(split_item[0]))
                                                    row.set(75,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Clinic name".equalsIgnoreCase(split_item[0]))
                                                    row.set(73,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Address1".equalsIgnoreCase(split_item[0]))
                                                    row.set(66,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Address2".equalsIgnoreCase(split_item[0]))
                                                    row.set(67,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "City".equalsIgnoreCase(split_item[0]))
                                                    row.set(68,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "State".equalsIgnoreCase(split_item[0]))
                                                    row.set(71,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Zip".equalsIgnoreCase(split_item[0]))
                                                    row.set(72,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "Email".equalsIgnoreCase(split_item[0]))
                                                    row.set(69,split_item[1]);
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
                                                    row.set(40,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 &&
                                                        "Performance Description".equalsIgnoreCase(split_item[0]))
                                                    row.set(39,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 &&
                                                        "Other Comments".equalsIgnoreCase(split_item[0]))
                                                    row.set(37,split_item[1]);
                                                else if(split_item!=null && split_item.length==2 && "GOOD".equalsIgnoreCase(split_item[0]))
                                                {
                                                    try{
                                                        if(Util.valueOfColor(ctx, split_item[1]))
                                                            row.set(38,split_item[0]);
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
                                                            row.set(38,split_item[0]);
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
                                                            row.set(38,split_item[0]);
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
                                                            row.set(38,split_item[0]);
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
                                                            row.set(41,split_item[0]);
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
                                                            row.set(41,split_item[0]);
                                                    }
                                                    catch(NumberFormatException ne)
                                                    {
                                                        ne.printStackTrace();
                                                        return null;
                                                    }
                                                }
                                                else if(split_item!=null && split_item.length==2 && "Not Used".equalsIgnoreCase(split_item[0]))
                                                {
                                                    try{
                                                        if(Util.valueOfColor(ctx,split_item[1]))
                                                            row.set(41,split_item[0]);
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
                                                if(!"True".equalsIgnoreCase(row.get(11)))
                                                    row.set(11,"False");
                                                if(!"True".equalsIgnoreCase(row.get(13)))
                                                    row.set(13,"False");
                                                if(!"True".equalsIgnoreCase(row.get(15)))
                                                    row.set(15,"False");
                                                if(!"True".equalsIgnoreCase(row.get(17)))
                                                    row.set(17,"False");
                                                if(!"True".equalsIgnoreCase(row.get(19)))
                                                    row.set(19,"False");
                                                if(!"True".equalsIgnoreCase(row.get(21)))
                                                    row.set(21,"False");
                                                if(!"True".equalsIgnoreCase(row.get(23)))
                                                    row.set(23,"False");
                                                if(!"True".equalsIgnoreCase(row.get(25)))
                                                    row.set(25,"False");
                                                if(!"True".equalsIgnoreCase(row.get(27)))
                                                    row.set(27,"False");
                                                if(!"True".equalsIgnoreCase(row.get(29)))
                                                    row.set(29,"False");
                                                if(!"True".equalsIgnoreCase(row.get(31)))
                                                    row.set(31,"False");


                                                String[] split_item = item.split("=");
                                                if(split_item!=null && split_item.length==2
                                                        && "Eyes Description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(10,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(11)))
                                                        row.set(11,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Eyes Description".equalsIgnoreCase(split_item[1]))
                                                    row.set(11,"True");
                                                else if(split_item!=null && split_item.length==2
                                                        && "Feet Description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(14,split_item[1]);
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
                                                    if(!"True".equalsIgnoreCase(row.get(17)))
                                                        row.set(17,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Legs Description".equalsIgnoreCase(split_item[1]))
                                                    row.set(17,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Testicles Description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(18,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(19)))
                                                        row.set(19,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Testicles Description".equalsIgnoreCase(split_item[1]))
                                                    row.set(19,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Accessory Sex glands description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(20,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(21)))
                                                        row.set(21,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Accessory Sex glands description".equalsIgnoreCase(split_item[1]))
                                                    row.set(21,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Inguinal Rings Description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(22,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(23)))
                                                        row.set(23,"True");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Inguinal Rings Description".equalsIgnoreCase(split_item[1]))
                                                    row.set(23,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Scrotal Shape description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(24,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(25)))
                                                        row.set(25,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Scrotal Shape description".equalsIgnoreCase(split_item[1]))
                                                    row.set(25,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Epidydimides description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(26,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(27)))
                                                        row.set(27,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Epidydimides description".equalsIgnoreCase(split_item[1]))
                                                    row.set(27,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Penis description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(28,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(29)))
                                                        row.set(29,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Penis description".equalsIgnoreCase(split_item[1]))
                                                    row.set(29,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Prepuce description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(30,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(31)))
                                                        row.set(31,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Prepuce description".equalsIgnoreCase(split_item[1]))
                                                    row.set(31,"True");

                                                else if(split_item!=null && split_item.length==2
                                                        && "Scrotum description".equalsIgnoreCase(split_item[0]))
                                                {
                                                    row.set(12,split_item[1]);
                                                    if(!"True".equalsIgnoreCase(row.get(13)))
                                                        row.set(13,"False");
                                                }
                                                else if(split_item!=null && split_item.length==2
                                                        && "Scrotum description".equalsIgnoreCase(split_item[1]))
                                                    row.set(13,"True");
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
                                                    row.set(54, split_item[1]);
                                                }
                                                else if (split_item != null && split_item.length == 2
                                                        && "Body Condition(0-9)".equalsIgnoreCase(split_item[0])) {
                                                    row.set(47, split_item[1]);
                                                }
                                                else if (split_item != null && split_item.length == 2
                                                        && "Pelvic X measure (cm)".equalsIgnoreCase(split_item[0])) {
                                                    row.set(52, split_item[1]);
                                                }
                                                else if (split_item != null && split_item.length == 2
                                                        && "Pelvic Y measure (cm)".equalsIgnoreCase(split_item[0])) {
                                                    row.set(53, split_item[1]);
                                                }
                                                else if (split_item != null && split_item.length == 2
                                                        && "Frame score (1-20)".equalsIgnoreCase(split_item[0])) {
                                                    row.set(49, split_item[1]);
                                                }
                                                else if (split_item != null && split_item.length == 2
                                                        && "Hip Height (cm)".equalsIgnoreCase(split_item[0])) {
                                                    row.set(50, split_item[1]);
                                                }
                                                else if (split_item != null && split_item.length == 2
                                                        && "Other measurement".equalsIgnoreCase(split_item[0])) {
                                                    row.set(48, split_item[1]);
                                                }

                                                row.set(51, "cm");

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
                                                    row.set(45,split_item[1]);

                                                else if(split_item!=null && split_item.length==2 &&
                                                        "Motility %".equalsIgnoreCase(split_item[0]))
                                                    row.set(46,split_item[1]);

                                                else if(split_item!=null && split_item.length==2 && "Poor".equalsIgnoreCase(split_item[0]))
                                                {
                                                    try{
                                                        if(Util.valueOfColor(ctx,split_item[1]))
                                                            row.set(44,split_item[0]);
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
                                                            row.set(44,split_item[0]);
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
                                                            row.set(44,split_item[0]);
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
                                                            row.set(44,split_item[0]);
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
                                                    row.set(36,split_item[1]);


                                                else if(split_item!=null && split_item.length==2
                                                        && "Satisfactory".equalsIgnoreCase(split_item[0]))
                                                {
                                                    try{
                                                        if(Util.valueOfColor(ctx,split_item[1]))
                                                            row.set(35,split_item[0]);
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
                                                            row.set(35,split_item[0]);
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
                                                            row.set(35,split_item[0]);
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
                                                            row.set(35,split_item[0]);
                                                    }
                                                    catch(NumberFormatException ne)
                                                    {
                                                        ne.printStackTrace();
                                                        return null;
                                                    }

                                                }
                                            }
                                        }
                                        row.set(65,uuid.replace(" ","-"));
                                        rows.add(row);


                            }
                            hasBulMorpholgy = false;
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
