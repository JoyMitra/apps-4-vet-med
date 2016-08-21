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

/**
 * Created by Joy on 6/12/16.
 * Run these tests after EditCollections.
 *
 * * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class BullInfoTest extends ActivityInstrumentationTestCase2<BullInfo>{

    private String BullId;
    private String groupId;
    private BullInfo bullInfo;
    private HashSet<String> keySet;
    private HashSet<String> bullKeys;
    private Instrumentation.ActivityMonitor activityMonitor;

    public BullInfoTest(){
        super(BullInfo.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());

        bullInfo = getActivity();
        keySet = (HashSet<String>) SharedPrefUtil.getValue(
                bullInfo.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
        assertNotNull(keySet);
        groupId = keySet.iterator().next().toString();
        activityMonitor = getInstrumentation().addMonitor(BullExam.class.getName(), null, false);
        bullKeys = (HashSet<String>)SharedPrefUtil.getValue(
                bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO,Constant.KEY_BULL);
    }

    @Test
    public void saveBullInfoWithAtleastOneIdPass(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final EditText idTag = (EditText) bullInfo.findViewById(R.id.IdTag);
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idTag.setText("234tag");
                save.performClick();
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(bullKeys.contains(BullId));
            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNotNull(bullExam);
    }

    @Test
    public void saveBullInfoWithNoIdFail(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        final EditText idTag = (EditText) bullInfo.findViewById(R.id.IdTag);
        final EditText idTattoo = (EditText) bullInfo.findViewById(R.id.IdTattoo);
        final EditText idBrand = (EditText) bullInfo.findViewById(R.id.IdBrand);
        final EditText idRfid = (EditText) bullInfo.findViewById(R.id.IdRfid);
        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                save.performClick();
                assertEquals(idTag.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                assertEquals(idTattoo.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                assertEquals(idBrand.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                assertEquals(idRfid.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(!bullKeys.contains(BullId));
            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNull(bullExam);
    }

    @Test
    public void saveInvalidBullInfowithInvalidDOBfail(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        final EditText idTattoo = (EditText) bullInfo.findViewById(R.id.IdTattoo);
        final EditText dob = (EditText) bullInfo.findViewById(R.id.date);
        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idTattoo.setText("007rfid");
                dob.setText("2012-02-30");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                dob.setText("201-04-30");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                dob.setText("2012-01-32");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                dob.setText("2012-11-31");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                save.performClick();
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(!bullKeys.contains(BullId));

            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(bullExam);
    }

    @Test
    public void saveBullInfoWithInvalidDOBThenValidDOBPass(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        final EditText idTattoo = (EditText) bullInfo.findViewById(R.id.IdTattoo);
        final EditText dob = (EditText) bullInfo.findViewById(R.id.date);
        final EditText ageYr = (EditText) bullInfo.findViewById(R.id.ageYrs);
        final EditText ageMth = (EditText) bullInfo.findViewById(R.id.ageMths);
        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idTattoo.setText("1001TaT");
                dob.setText("2012-11-31");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(),
                        R.drawable.highlight).getConstantState());
                dob.setText("xyzd-11-31");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(),
                        R.drawable.highlight).getConstantState());
                save.performClick();
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(!bullKeys.contains(BullId));
                dob.setText("2014-12-31");
                dob.getOnFocusChangeListener().onFocusChange(dob, false);
                assertEquals(dob.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(),
                        R.drawable.focus_color).getConstantState());
                assertEquals(ageYr.getText().toString().trim() + "," + ageMth.getText().toString().trim()
                        , Util.getAge(2014,12));
                save.performClick();
                final HashSet<String> bullKeys1 = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(bullKeys1.contains(BullId));

            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(bullExam);
    }

    @Test
    public void saveBullInfoWithInvalidAgeYearFail(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        final EditText idTattoo = (EditText) bullInfo.findViewById(R.id.IdTattoo);
        final EditText dob = (EditText) bullInfo.findViewById(R.id.date);
        final EditText ageYr = (EditText) bullInfo.findViewById(R.id.ageYrs);
        final EditText ageMth = (EditText) bullInfo.findViewById(R.id.ageMths);

        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idTattoo.setText("999tat");
                ageYr.setText("26");
                ageYr.getOnFocusChangeListener().onFocusChange(ageYr, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageYr.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                ageMth.setText("18");
                save.performClick();
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(!bullKeys.contains(BullId));
            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(bullExam);
    }

    @Test
    public void saveBullInfoWithInvalidAgeMonthFail(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        final EditText idTattoo = (EditText) bullInfo.findViewById(R.id.IdTattoo);
        final EditText dob = (EditText) bullInfo.findViewById(R.id.date);
        final EditText ageYr = (EditText) bullInfo.findViewById(R.id.ageYrs);
        final EditText ageMth = (EditText) bullInfo.findViewById(R.id.ageMths);

        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idTattoo.setText("999tat");
                ageYr.setText("26");
                ageYr.getOnFocusChangeListener().onFocusChange(ageYr, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageYr.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                ageYr.setText("25");
                ageYr.getOnFocusChangeListener().onFocusChange(ageYr, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageYr.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.focus_color).getConstantState());
                ageMth.setText("19");
                ageMth.getOnFocusChangeListener().onFocusChange(ageMth, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageMth.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                save.performClick();
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(!bullKeys.contains(BullId));
            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(bullExam);
    }

    @Test
    public void saveBullInfoWithValidFieldsPass(){
        BullId = Util.getBullKey(groupId, bullKeys);
        bullInfo.bullKey = BullId;
        final Button save = (Button) bullInfo.findViewById(R.id.saveBullInfo);
        final EditText idBrand = (EditText) bullInfo.findViewById(R.id.IdBrand);
        final EditText dob = (EditText) bullInfo.findViewById(R.id.date);
        final EditText ageYr = (EditText) bullInfo.findViewById(R.id.ageYrs);
        final EditText ageMth = (EditText) bullInfo.findViewById(R.id.ageMths);
        final EditText lot = (EditText) bullInfo.findViewById(R.id.lot);
        final EditText breed = (EditText) bullInfo.findViewById(R.id.breed);

        bullInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                idBrand.setText("999brand");
                ageYr.setText("26");
                ageYr.getOnFocusChangeListener().onFocusChange(ageYr, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageYr.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                ageYr.setText("12");
                ageYr.getOnFocusChangeListener().onFocusChange(ageYr, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageYr.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.focus_color).getConstantState());
                ageMth.setText("19");
                ageMth.getOnFocusChangeListener().onFocusChange(ageMth, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageMth.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.highlight).getConstantState());
                save.performClick();
                final HashSet<String> bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                if(bullKeys!=null)
                    assertTrue(!bullKeys.contains(BullId));
                ageMth.setText("18");
                ageMth.getOnFocusChangeListener().onFocusChange(ageMth, false);
                assertEquals(dob.getText().toString().trim(), "");
                assertEquals(ageMth.getBackground().getConstantState(), ContextCompat.getDrawable(bullInfo.getApplicationContext(), R.drawable.focus_color).getConstantState());
                lot.setText("T");
                breed.setText("rare");
                save.performClick();
                final HashSet<String> bullKeys1 = (HashSet<String>) SharedPrefUtil.getValue(
                        bullInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
                assertTrue(bullKeys1.contains(BullId));
            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(bullExam);
    }
}
