/**
 * Created by Joydeep Mitra on 6/12/16.
 * Run these tests after CollectionsTest.
 *
 * * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */
package edu.ksu.cs.a4vm.bse;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;
import android.widget.EditText;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MorphologyActivityTest extends ActivityInstrumentationTestCase2<MorphologyActivity> {

    private MorphologyActivity morphologyActivity;
    private Instrumentation.ActivityMonitor activityMonitor;

    public MorphologyActivityTest(){
        super(MorphologyActivity.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        morphologyActivity = getActivity();
    }

    @Test
    public void saveMorphSettingsWithValidLimitPass(){
        final Button save = (Button) morphologyActivity.findViewById(R.id.saveMorphology);
        final EditText limit = (EditText) morphologyActivity.findViewById(R.id.morphLimit);
        final EditText field2 = (EditText) morphologyActivity.findViewById(R.id.morphfield2);
        final EditText field3 = (EditText) morphologyActivity.findViewById(R.id.morphField3);
        morphologyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                limit.setText("30");
                field2.setText("Abnormal");
                field3.setText("Fertile");
                save.performClick();
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.getValue(morphologyActivity.getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, Constant.KEY_MORPHOLOGY);
                assertNotNull(afterSave);
            }
        });
        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNotNull(settingsActivity);
    }

    @Test
    public void saveMorphSettingsWithInValidLimitFail(){
        final Button save = (Button) morphologyActivity.findViewById(R.id.saveMorphology);
        final EditText limit = (EditText) morphologyActivity.findViewById(R.id.morphLimit);
        final EditText field2 = (EditText) morphologyActivity.findViewById(R.id.morphfield2);
        final EditText field3 = (EditText) morphologyActivity.findViewById(R.id.morphField3);
        morphologyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                limit.setText("es30");
                field2.setText("Abnormal");
                field3.setText("Fertile");
                save.performClick();
                assertEquals(limit.getBackground().getConstantState(), ContextCompat.getDrawable(morphologyActivity.getApplicationContext(), R.drawable.highlight).getConstantState());
            }
        });
        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNull(settingsActivity);
    }

    @Test
    public void saveMorphSettingsWithInValidThenValidLimitPass(){
        final Button save = (Button) morphologyActivity.findViewById(R.id.saveMorphology);
        final EditText limit = (EditText) morphologyActivity.findViewById(R.id.morphLimit);
        final EditText field2 = (EditText) morphologyActivity.findViewById(R.id.morphfield2);
        final EditText field3 = (EditText) morphologyActivity.findViewById(R.id.morphField3);
        morphologyActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                limit.setText("-30");
                field2.setText("Abnormal");
                field3.setText("Fertile");
                limit.setText("30");
                save.performClick();
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.getValue(morphologyActivity.getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, Constant.KEY_MORPHOLOGY);
                assertNotNull(afterSave);
            }
        });
        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNotNull(settingsActivity);
    }
}
