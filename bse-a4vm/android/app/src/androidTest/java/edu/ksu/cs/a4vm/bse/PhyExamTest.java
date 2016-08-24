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
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PhyExamTest extends ActivityInstrumentationTestCase2<PhysicalExam>{

    public PhyExamTest(){
        super(PhysicalExam.class);
    }
    private PhysicalExam physicalExam;
    private HashSet<String> bullKeys;

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        physicalExam = getActivity();
        bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                physicalExam.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
        for(String key: bullKeys){
            if(SharedPrefUtil.getValue(physicalExam.getApplicationContext(), Constant.PREFS_BULL_INFO, key)!=null){
                physicalExam.bullKey = key;
                break;
            }
        }
    }

    @Test
    public void saveEmptyTest(){
        final Button saveBtn = (Button) physicalExam.findViewById(R.id.savePhyExam);
        physicalExam.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                saveBtn.performClick();
                final HashSet<String> phyExamAfterSave = (HashSet<String>)SharedPrefUtil.getValue(physicalExam.getApplicationContext(),
                        Constant.PREFS_PHY_PRAMS_INFO, physicalExam.bullKey);
                assertNotNull(phyExamAfterSave);

            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(physicalParameter);
    }
}
