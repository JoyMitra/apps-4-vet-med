package edu.ksu.cs.a4vm.bse;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
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
 * Created by Joy on 7/4/16.
 * Run these tests after BullInfoTest.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MeasurementsTableTest extends ActivityInstrumentationTestCase2<MeasurementsTable>{

    public MeasurementsTableTest(){
        super(MeasurementsTable.class);
    }
    private MeasurementsTable measurementsTable;
    private HashSet<String> bullKeys;
    private String bullKey;

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        measurementsTable = getActivity();
        bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                measurementsTable.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
        for(String key: bullKeys){
            if(SharedPrefUtil.getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_INFO, key)!=null){
                this.bullKey = key;
                measurementsTable.bullKey = key;
                break;
            }
        }
    }

    @Test
    public void saveAllFieldsTestPass1(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("60.5");
                field2.setText("8.5");
                field3.setText("30");
                field4.setText("8");
                field5.setText("20");
                field6.setText("23");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field1.getHint().toString().trim() + "=" + "60.5";
                assertTrue(afterSave.contains(data));
                data = field2.getHint().toString().trim() + "=" + "8.5";
                assertTrue(afterSave.contains(data));
                data = field3.getHint().toString().trim() + "=" + "30";
                assertTrue(afterSave.contains(data));
                data = field4.getHint().toString().trim() + "=" + "8";
                assertTrue(afterSave.contains(data));
                data = field5.getHint().toString().trim() + "=" + "20";
                assertTrue(afterSave.contains(data));
                data = field6.getHint().toString().trim() + "=" + "23";
                assertTrue(afterSave.contains(data));
                data = field7.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));
                data = field8.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(physicalParameter);

    }

    @Test
    public void saveAllFieldsTestPass2(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("60.5");
                field2.setText("");
                field3.setText("30");
                field4.setText("");
                field5.setText("20");
                field6.setText("");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field1.getHint().toString().trim() + "=" + "60.5";
                assertTrue(afterSave.contains(data));
                data = field2.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));
                data = field3.getHint().toString().trim() + "=" + "30";
                assertTrue(afterSave.contains(data));
                data = field4.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));
                data = field5.getHint().toString().trim() + "=" + "20";
                assertTrue(afterSave.contains(data));
                data = field6.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));
                data = field7.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));
                data = field8.getHint().toString().trim() + "=" + "";
                assertTrue(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(physicalParameter);

    }

    @Test
    public void saveInvalidField1TestFail(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("62");
                field1.getOnFocusChangeListener().onFocusChange(field1,false);
                field2.setText("");
                field3.setText("30");
                field4.setText("");
                field5.setText("20");
                field6.setText("");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field1.getHint().toString().trim() + "=" + "62";
                assertFalse(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(physicalParameter);

    }

    @Test
     public void saveInvalidField2TestFail(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("58");
                field1.getOnFocusChangeListener().onFocusChange(field1, false);
                field2.setText("12");
                field2.getOnFocusChangeListener().onFocusChange(field2, false);
                field3.setText("30");
                field4.setText("");
                field5.setText("20");
                field6.setText("");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field2.getHint().toString().trim() + "=" + "12";
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(physicalParameter);

    }

    @Test
    public void saveInvalidField3TestFail(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("58");
                field1.getOnFocusChangeListener().onFocusChange(field1, false);
                field2.setText("");
                field2.getOnFocusChangeListener().onFocusChange(field2, false);
                field3.setText("7");
                field3.getOnFocusChangeListener().onFocusChange(field3, false);
                field4.setText("");
                field5.setText("20");
                field6.setText("");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field3.getHint().toString().trim() + "=" + "7";
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(physicalParameter);

    }
    @Test
    public void saveInvalidField4TestFail(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("58");
                field1.getOnFocusChangeListener().onFocusChange(field1, false);
                field2.setText("");
                field2.getOnFocusChangeListener().onFocusChange(field2, false);
                field3.setText("12");
                field3.getOnFocusChangeListener().onFocusChange(field3, false);
                field4.setText("31");
                field4.getOnFocusChangeListener().onFocusChange(field4, false);
                field5.setText("20");
                field6.setText("");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field4.getHint().toString().trim() + "=" + "31";
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(physicalParameter);

    }

    @Test
    public void saveInvalidField5TestFail(){
        final EditText field1 = (EditText) measurementsTable.findViewById(R.id.field1);
        final EditText field2 = (EditText) measurementsTable.findViewById(R.id.field2);
        final EditText field3 = (EditText) measurementsTable.findViewById(R.id.field3);
        final EditText field4 = (EditText) measurementsTable.findViewById(R.id.field4);
        final EditText field5 = (EditText) measurementsTable.findViewById(R.id.field5);
        final EditText field6 = (EditText) measurementsTable.findViewById(R.id.field6);
        final EditText field7 = (EditText) measurementsTable.findViewById(R.id.field7);
        final EditText field8 = (EditText) measurementsTable.findViewById(R.id.field8);
        final Button saveBtn = (Button) measurementsTable.findViewById(R.id.saveMeasurement);
        measurementsTable.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                field1.setText("58");
                field1.getOnFocusChangeListener().onFocusChange(field1, false);
                field2.setText("");
                field2.getOnFocusChangeListener().onFocusChange(field2, false);
                field3.setText("12");
                field3.getOnFocusChangeListener().onFocusChange(field3, false);
                field4.setText("31");
                field4.getOnFocusChangeListener().onFocusChange(field4, false);
                field5.setText("22");
                field5.getOnFocusChangeListener().onFocusChange(field5, false);
                field6.setText("");
                saveBtn.performClick();
                final HashSet<String> afterSave = (HashSet<String>)SharedPrefUtil.
                        getValue(measurementsTable.getApplicationContext(), Constant.PREFS_BULL_MEASUREMENT_INFO, bullKey);
                String data = field5.getHint().toString().trim() + "=" + "22";
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));


            }
        });
        Instrumentation.ActivityMonitor activityMonitor = getInstrumentation().addMonitor(PhysicalParameter.class.getName(), null, false);
        PhysicalParameter physicalParameter = (PhysicalParameter)getInstrumentation().
                waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(physicalParameter);

    }
}
