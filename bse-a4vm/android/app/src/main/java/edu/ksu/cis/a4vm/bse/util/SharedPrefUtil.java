package edu.ksu.cis.a4vm.bse.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Set;

import edu.ksu.cis.a4vm.bse.Constants.Constant;

/**
 * Created by Joydeep Mitra on 2/22/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */

public class SharedPrefUtil {


    public static void saveVetInfo(Context ctx, Set<String> data)
    {
        SharedPreferences settings=null;
        SharedPreferences.Editor editor=null;
        settings = ctx.getSharedPreferences(Constant.PREFS_FILE_VET_INFO, ctx.MODE_PRIVATE);
        editor = settings.edit();
        editor.putStringSet(Constant.KEY_VET,data);
        editor.commit();
    }

    public static void saveMorphologyKeys(Context ctx, Set<String> data)
    {
        SharedPreferences settings=null;
        SharedPreferences.Editor editor=null;
        settings = ctx.getSharedPreferences(Constant.PREFS_FILE_MORPH_INFO, ctx.MODE_PRIVATE);
        editor = settings.edit();
        editor.putStringSet(Constant.KEY_MORPHOLOGY,data);
        editor.commit();
    }


    public static void saveGroup(Context ctx, String fileName, String key, Set<String> data)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = ctx.getSharedPreferences(fileName, ctx.MODE_PRIVATE);
        editor = settings.edit();
        editor.putStringSet(key, data);
        editor.commit();

    }

    public static Set<String> getValue(Context ctx, String prefs_file,String key){
        SharedPreferences settings;
        settings = ctx.getSharedPreferences(prefs_file, ctx.MODE_PRIVATE);
        return settings.getStringSet(key, null);
    }

    public static String getSingleValue(Context ctx, String prefs_file,String key){
        SharedPreferences settings;
        settings = ctx.getSharedPreferences(prefs_file, ctx.MODE_PRIVATE);
        return settings.getString(key, null);
    }

    public static void removeKey(Context ctx, String prefs_file, String key)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = ctx.getSharedPreferences(prefs_file, ctx.MODE_PRIVATE);
        editor = settings.edit();
        editor.remove(key);
        editor.commit();
    }

    public static void clear_pref(Context ctx, String prefs_file)
    {
        SharedPreferences settings;
        SharedPreferences.Editor editor;
        settings = ctx.getSharedPreferences(prefs_file, ctx.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear();
        editor.commit();
    }

}
