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
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity>{
    public MainActivityTest(){
        super(MainActivity.class);
    }
    private MainActivity mainActivity;

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        mainActivity = getActivity();
    }

    @Test
    public void NavigateToCollectionsOnlyAfterInitialSetupPass(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(Collections.class.getName(), null, false);
        final TextView collections = (TextView) mainActivity.findViewById(R.id.collections);
        mainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                collections.performClick();
            }
        });
        Collections collections1 = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        if (SharedPrefUtil.getValue(mainActivity.getApplicationContext(),
                Constant.PREFS_FILE_VET_INFO, Constant.KEY_VET) != null){
            assertNotNull(collections1);
        }
        else{
            assertNull(collections1);
        }
    }

}
