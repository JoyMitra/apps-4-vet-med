package edu.ksu.cis.a4vm.bse;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;
import android.widget.ToggleButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import edu.ksu.cis.a4vm.bse.Constants.Constant;
import edu.ksu.cis.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joy on 6/12/16.
 * Run these tests BullInfoTest
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MatingInfoTest extends ActivityInstrumentationTestCase2<MatingInfo> {

    private MatingInfo matingInfo;
    private Instrumentation.ActivityMonitor activityMonitor;
    private HashSet<String> bullKeys;
    private String bullKey;

    public MatingInfoTest(){
        super(MatingInfo.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        activityMonitor = getInstrumentation().addMonitor(BullExam.class.getName(), null, false);
        matingInfo = getActivity();
        bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                matingInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
        for(String key: bullKeys){
            if(SharedPrefUtil.getValue(matingInfo.getApplicationContext(), Constant.PREFS_BULL_INFO, key)!=null){
                this.bullKey = key;
                matingInfo.bullKey = key;
                break;
            }
        }
    }

    @Test
    public void saveMatingInfoWithBullPerformanceNotSelectedFail(){
        final ToggleButton tgbtn = (ToggleButton)matingInfo.findViewById(R.id.perfGood);
        final ToggleButton tgbtn1 = (ToggleButton)matingInfo.findViewById(R.id.perfBad);
        final ToggleButton tgbtn2 = (ToggleButton)matingInfo.findViewById(R.id.perfOther);
        final ToggleButton tgbtn3 = (ToggleButton)matingInfo.findViewById(R.id.perfUnkown);
        final Button save = (Button) matingInfo.findViewById(R.id.saveMatingInfo);
        matingInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final HashSet<String> beforeSave = (HashSet<String>)SharedPrefUtil.getValue(matingInfo.getApplicationContext(),
                        Constant.PREFS_MATING_INFO, bullKey);
                tgbtn.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                tgbtn1.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                tgbtn2.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                tgbtn3.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                save.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.getValue(matingInfo.getApplicationContext(),
                        Constant.PREFS_MATING_INFO, bullKey);
                if(beforeSave!=null){
                    assertNotNull(afterSave);
                    assertTrue(beforeSave.containsAll(afterSave) && afterSave.containsAll(beforeSave));
                }
                else{
                    assertNull(afterSave);
                }
            }
        });
        Instrumentation.ActivityMonitor activityMonitor1;
        activityMonitor1 = getInstrumentation().addMonitor(BullExam.class.getName(), null, false);
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor1,5000);
        assertNull(bullExam);
    }

    @Test
    public void saveMatingInfoWithOnlyBullperformanceSelectedPass(){
        final ToggleButton tgbtn = (ToggleButton)matingInfo.findViewById(R.id.perfGood);
        final ToggleButton tgbtn1 = (ToggleButton)matingInfo.findViewById(R.id.perfBad);
        final ToggleButton tgbtn2 = (ToggleButton)matingInfo.findViewById(R.id.perfOther);
        final ToggleButton tgbtn3 = (ToggleButton)matingInfo.findViewById(R.id.perfUnkown);
        final Button save = (Button) matingInfo.findViewById(R.id.saveMatingInfo);
        matingInfo.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(tgbtn.getCurrentTextColor()== ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue)){
                    //tgbtn.callOnClick();
                    tgbtn.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.colorAccent));
                    tgbtn1.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn2.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn3.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                }
                else if(tgbtn1.getCurrentTextColor()== ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue)){
                    //tgbtn1.callOnClick();
                    tgbtn.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn1.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.colorAccent));
                    tgbtn2.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn3.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                }
                else if(tgbtn2.getCurrentTextColor()== ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue)){
                    //tgbtn2.callOnClick();
                    tgbtn.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn1.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn2.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.colorAccent));
                    tgbtn3.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                }
                else if(tgbtn3.getCurrentTextColor()== ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue)){
                    //tgbtn3.callOnClick();
                    tgbtn.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn1.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn2.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.lightBlue));
                    tgbtn3.setTextColor(ContextCompat.getColor(matingInfo.getApplicationContext(), R.color.colorAccent));
                }
                final HashSet<String> beforeSave = (HashSet<String>)SharedPrefUtil.getValue(matingInfo.getApplicationContext(),
                        Constant.PREFS_MATING_INFO, bullKey);
                save.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.getValue(matingInfo.getApplicationContext(),
                        Constant.PREFS_MATING_INFO, bullKey);
                if(beforeSave!=null){
                    assertEquals(beforeSave.size(),afterSave.size());
                    assertFalse(beforeSave.containsAll(afterSave));
                }
                else
                {
                    assertNotNull(afterSave);
                }

            }
        });
        BullExam bullExam = (BullExam)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNotNull(bullExam);
    }
}
