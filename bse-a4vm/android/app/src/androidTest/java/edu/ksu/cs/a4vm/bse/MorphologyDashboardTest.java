package edu.ksu.cs.a4vm.bse;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.Set;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

/**
 * Created by Joy on 6/13/16.
 * Run these tests after BullInfoTest.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MorphologyDashboardTest extends ActivityInstrumentationTestCase2<MorphologyDashboard>{

    private MorphologyDashboard morphologyDashboard;
    private HashSet<String> bullKeys;
    private String bullKey;

    public MorphologyDashboardTest(){
        super(MorphologyDashboard.class);
    }

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        morphologyDashboard = getActivity();
        bullKeys = (HashSet<String>) SharedPrefUtil.getValue(
                morphologyDashboard.getApplicationContext(), Constant.PREFS_BULL_INFO, Constant.KEY_BULL);
        for(String key: bullKeys){
            if(SharedPrefUtil.getValue(morphologyDashboard.getApplicationContext(), Constant.PREFS_BULL_INFO, key)!=null){
                this.bullKey = key;
                morphologyDashboard.bullKey = key;
                break;
            }
        }
        morphologyDashboard.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                morphologyDashboard.onResume();
            }
        });

    }

    @Test
    public void verifyGrpKey(){
        assertEquals(morphologyDashboard.bullKey,this.bullKey);
        assertNotNull(morphologyDashboard.morphKey);
    }

    @Test
    public void AddMorphologyCountToDashboard(){
        ListView lv = (ListView)morphologyDashboard.findViewById(R.id.morphCollection);
        ListAdapter ladp = lv.getAdapter();
        if(ladp!=null)
        {
            final Set<String> morphKeys = SharedPrefUtil.getValue(morphologyDashboard.getApplicationContext(),
                    Constant.PREFS_MORPHOLOGY_COUNT_KEYS, Constant.KEY_MORPHOLOGY_COUNT_KEY);
            assertEquals(ladp.getCount(), morphKeys.size());
        }
        else
            assertTrue(true);

    }

    @Test
    public void NewMorphologyCountTest(){
        final Button addMorph = (Button)morphologyDashboard.findViewById(R.id.addMorphology);
        morphologyDashboard.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                addMorph.performClick();
            }
        });
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(MorphologyCount.class.getName(), null, false);
        final MorphologyCount morphologyCount = (MorphologyCount)getInstrumentation().waitForMonitorWithTimeout(activityMonitor,5000);
        assertNotNull(morphologyCount);
        assertNotNull(morphologyCount.grpKey);
        assertNotNull(morphologyCount.morphKey);
        final HashSet<String> currentSettings = (HashSet<String>) SharedPrefUtil.getValue(morphologyCount.getApplicationContext(),
                Constant.PREFS_GRP_MORPH_CONFIG,morphologyCount.grpKey);
        morphologyCount.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                morphologyCount.onResume();
                assertEquals(morphologyCount.test, "Resume called...");
                TextView tvLimit = (TextView) morphologyCount.findViewById(R.id.totals);
                assertNotNull(tvLimit);
                Button editMorph = (Button) morphologyCount.findViewById(R.id.button);
                assertNotNull(editMorph);
                if (currentSettings == null) {
                    Button edit = (Button) morphologyCount.findViewById(R.id.button);
                    assertNotNull(edit);
                    Button normal = (Button) morphologyCount.findViewById(R.id.button1);
                    assertNotNull(normal);
                    String text = normal.getText().toString().trim();
                    int countBeforeClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                    normal.performClick();
                    text = normal.getText().toString().trim();
                    int countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                    assertEquals(countBeforeClick + 1, countAfterClick);
                } else {
                    Button edit = (Button) morphologyCount.findViewById(R.id.button);
                    assertNotNull(edit);
                    Button normal = (Button) morphologyCount.findViewById(R.id.button1);
                    assertNotNull(normal);
                    String text = normal.getText().toString().trim();
                    String count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                    int countBeforeClick = Integer.parseInt(count);
                    normal.performClick();
                    text = normal.getText().toString().trim();
                    int countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                    assertEquals(countBeforeClick + 1, countAfterClick);
                    for (String item : currentSettings) {
                        String splitItem[] = item.split("=");
                        if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 2")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button2);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        } else if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 3")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button3);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        } else if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 4")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button4);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        } else if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 5")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button5);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        } else if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 6")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button6);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        } else if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 7")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button7);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        } else if (splitItem != null && splitItem.length == 2 && splitItem[0].equalsIgnoreCase("Morphology Field 8")) {
                            String btnText = splitItem[1];
                            Button btn2 = (Button) morphologyCount.findViewById(R.id.button8);
                            assertNotNull(btn2);
                            assertEquals(btn2.getText().toString().trim(), btnText + ":0(0.00%)");
                            text = btn2.getText().toString().trim();
                            count = text.substring(text.indexOf(":") + 1, text.indexOf("("));
                            countBeforeClick = Integer.parseInt(count);
                            btn2.performClick();
                            text = normal.getText().toString().trim();
                            countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                            assertEquals(countBeforeClick + 1, countAfterClick);

                        }

                    }
                }
                morphologyCount.onBackPressed();
            }
        });

        Instrumentation.ActivityMonitor activityMonitor1;
        activityMonitor1 = getInstrumentation().addMonitor(MorphologyDashboard.class.getName(), null, false);
        final MorphologyDashboard morphDashboard = (MorphologyDashboard)getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 5000);
        assertNotNull(morphDashboard);

    }

    @Test
    public void modifySavedMorphologyCountTest(){

        final Set<String> keys = SharedPrefUtil.getValue(morphologyDashboard.getApplicationContext(),
                Constant.PREFS_MORPHOLOGY_COUNT_KEYS, Constant.KEY_MORPHOLOGY_COUNT_KEY);
        if(keys!=null && !keys.isEmpty()){
            //assertTrue(keys.contains(morphologyDashboard.bullKey));
            for(String k : keys){
                Intent goToMorphCount = new Intent(morphologyDashboard.getApplicationContext(), MorphologyCount.class);
                goToMorphCount.putExtra("morphKey", k);
                morphologyDashboard.startActivity(goToMorphCount);
                break;
            }
            Instrumentation.ActivityMonitor activityMonitor;
            activityMonitor = getInstrumentation().addMonitor(MorphologyCount.class.getName(), null, false);
            final MorphologyCount morphologyCount = (MorphologyCount)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
            assertNotNull(morphologyCount);
            assertNotNull(morphologyCount.grpKey);
            assertNotNull(morphologyCount.morphKey);
            final HashSet<String> currentSettings = (HashSet<String>) SharedPrefUtil.getValue(morphologyCount.getApplicationContext(),
                    Constant.PREFS_GRP_MORPH_CONFIG, morphologyCount.grpKey);
            morphologyCount.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    morphologyCount.onResume();
                    assertEquals(morphologyCount.test, "Resume called...");
                    TextView tvLimit = (TextView) morphologyCount.findViewById(R.id.totals);
                    assertNotNull(tvLimit);
                    Button editMorph = (Button) morphologyCount.findViewById(R.id.button);
                    assertNotNull(editMorph);
                    Button normal = (Button) morphologyCount.findViewById(R.id.button1);
                    assertNotNull(normal);
                    String normalTxt = normal.getText().toString().trim().replace(":", "=");
                    final Set<String> morphData = SharedPrefUtil.getValue(morphologyDashboard.getApplicationContext(),
                            Constant.PREFS_BULL_MORPHOLOGY_INFO, morphologyCount.morphKey);
                    assertTrue(morphData.contains(normalTxt));
                    String text = normal.getText().toString().trim();
                    int countBeforeClick = Integer.parseInt(text.substring(text.indexOf(":")+1,text.indexOf("(")));
                    normal.performClick();
                    text = normal.getText().toString().trim();
                    int countAfterClick = Integer.parseInt(text.substring(text.indexOf(":") + 1, text.indexOf("(")));
                    assertEquals(countBeforeClick + 1, countAfterClick);
                    editMorph.performClick();

                }
            });
            Instrumentation.ActivityMonitor activityMonitor1;
            activityMonitor1 = getInstrumentation().addMonitor(EditMorphologyCounts.class.getName(), null, false);
            final EditMorphologyCounts editMorphologyCounts = (EditMorphologyCounts)getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 5000);
            assertNotNull(editMorphologyCounts);
            editMorphologyCounts.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    editMorphologyCounts.onResume();
                    final Set<String> morphData = SharedPrefUtil.getValue(morphologyDashboard.getApplicationContext(),
                            Constant.PREFS_BULL_MORPHOLOGY_INFO, morphologyCount.morphKey);
                    String savedNormal = "";
                    for(String s : morphData){
                        if(s.contains("Normal")){
                            savedNormal = savedNormal + s.substring(s.indexOf("=")+1,s.indexOf("("));
                        }
                    }
                    String displayedNormal = editMorphologyCounts.et1.getText().toString().trim();
                    assertEquals(displayedNormal,savedNormal);

                }
            });
        }
        else
        {
            assertTrue(true);
        }
    }

}
