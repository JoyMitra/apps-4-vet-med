package edu.ksu.cs.a4vm.bse;

import android.app.Instrumentation;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.content.ContextCompat;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.widget.EditText;

import java.util.HashSet;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Joy on 6/10/16.
 * Run these tests after MorphologyActivityTest.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class NewGroupTest extends ActivityInstrumentationTestCase2<NewGroup>{
    private String groupId;
    private Instrumentation.ActivityMonitor activityMonitor;
    private NewGroup newGroup;

    public NewGroupTest() {
        super(NewGroup.class);
    }


    @Before
    public void setup() throws Exception
    {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        newGroup = getActivity();
        activityMonitor = getInstrumentation().addMonitor(Collections.class.getName(), null, false);
        //newGroup = mActivityTestRule.getActivity();
        final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
        groupId = Util.getKey(keySet);
    }
    @Test
    public void NavigateToCollectionsFromEmptyGroupOnSaveWithoutSavingPass(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // click button and open next activity.
                save.performClick();
                final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                assertTrue(!keySet.contains(groupId));
            }
        });


        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull("Collections activity is not launched",collections);
        collections.finish();

    }

    @Test
    public void saveGroupWithOnlyRanchNameAndNavigateToCollectionsPass(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText)newGroup.findViewById((R.id.rName));

        newGroup.key = groupId;

        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ranchName.setText("Ithaca Farms");
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                assertTrue(keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() + 1 == keySetAfterSave.size());
            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull("Collections activity is not launched",collections);
        collections.finish();
    }

    @Test
    public void saveGroupWithOnlyRancherNameAndNavigateToCollectionsPass(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText rancherName = (EditText)newGroup.findViewById((R.id.rnName));

        newGroup.key = groupId;

        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rancherName.setText("Kate Cameron");
                final HashSet<String> currMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(newGroup.getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, Constant.KEY_MORPHOLOGY);
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() + 1 == keySetAfterSave.size());
                if(groupMorphConfig != null && groupMorphConfig != null)
                    assertTrue(currMorphConfig.containsAll(groupMorphConfig));
                else
                    assertTrue(true);
            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull("Collections activity is not launched",collections);
        collections.finish();
    }

    @Test
    public void saveGroupWithAllValidFieldsAndNavigateToCollectionsPass(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText) newGroup.findViewById(R.id.rName);
        final EditText rancherName = (EditText) newGroup.findViewById(R.id.rnName);
        final EditText email = (EditText) newGroup.findViewById(R.id.rancherEmail);
        final EditText address1 = (EditText) newGroup.findViewById(R.id.addr1);
        final EditText city = (EditText) newGroup.findViewById(R.id.city);
        final EditText state = (EditText) newGroup.findViewById(R.id.state);
        final EditText zip = (EditText) newGroup.findViewById(R.id.zip);
        final EditText phone = (EditText) newGroup.findViewById(R.id.phone);

        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rancherName.setText("Kate Cameron");
                ranchName.setText("KatiC Farms");
                email.setText("kat@test.edu");
                email.getOnFocusChangeListener().onFocusChange(email, false);
                address1.setText("1213 Carems St.");
                address1.getOnFocusChangeListener().onFocusChange(address1,false);
                city.setText("Atchinson");
                city.getOnFocusChangeListener().onFocusChange(city,false);
                zip.setText("66402");
                zip.getOnFocusChangeListener().onFocusChange(zip,false);
                state.setText("MA");
                state.getOnFocusChangeListener().onFocusChange(state,false);
                phone.setText("7857726121");
                phone.getOnFocusChangeListener().onFocusChange(state,false);

                final HashSet<String> currMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(newGroup.getApplicationContext(),
                        Constant.PREFS_FILE_MORPH_INFO, Constant.KEY_MORPHOLOGY);
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() + 1 == keySetAfterSave.size());
                if(groupMorphConfig!=null && currMorphConfig!=null)
                    assertTrue(currMorphConfig.containsAll(groupMorphConfig));
                else
                    assertTrue(true);
            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull("Collections activity is not launched",collections);
        collections.finish();
    }

    @Test
    public void saveGroupWithoutRanchAndRancherNameFails(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText email = (EditText)newGroup.findViewById((R.id.rancherEmail));
        final EditText city = (EditText) newGroup.findViewById(R.id.city);
        final EditText state = (EditText) newGroup.findViewById(R.id.state);
        final EditText zip = (EditText) newGroup.findViewById(R.id.zip);

        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                email.setText("xyz@ksu.edu");
                city.setText("Manhattan");
                state.setText("KS");
                zip.setText("66502");
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(!keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() == keySetAfterSave.size());
                assertTrue(groupMorphConfig == null || groupMorphConfig.isEmpty());
            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull("Collections activity is not launched",collections);
        collections.finish();
    }

    @Test
    public void saveNewGroupWithInvalidEmailFails(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText)newGroup.findViewById((R.id.rName));
        final EditText rancherName = (EditText)newGroup.findViewById((R.id.rnName));
        final EditText email = (EditText)newGroup.findViewById((R.id.rancherEmail));
        final EditText city = (EditText) newGroup.findViewById(R.id.city);
        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ranchName.setText("Manhattan Bull Company");
                rancherName.setText("Kambiz Kotton");
                email.setText("kk@mbc@com");
                email.getOnFocusChangeListener().onFocusChange(email, false);
                city.setText("Manhattan");
                assertTrue(email.getBackground().getConstantState().equals(ContextCompat.getDrawable(newGroup.getApplicationContext(), R.drawable.highlight).getConstantState()));
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(!keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() == keySetAfterSave.size());
                assertTrue(keySetBeforeSave.containsAll(keySetAfterSave));
                assertTrue(groupMorphConfig == null || groupMorphConfig.isEmpty());

            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull("Collections activity is launched", collections);
    }

    @Test
    public void saveNewGroupWithInvalidStateFails(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText)newGroup.findViewById((R.id.rName));
        final EditText rancherName = (EditText)newGroup.findViewById((R.id.rnName));
        final EditText email = (EditText)newGroup.findViewById((R.id.rancherEmail));
        final EditText city = (EditText) newGroup.findViewById(R.id.city);
        final EditText state = (EditText) newGroup.findViewById(R.id.state);
        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ranchName.setText("Manhattan Bull Company");
                rancherName.setText("Kambiz Kotton");
                email.setText("kk@mbc.com");
                email.getOnFocusChangeListener().onFocusChange(email, false);
                city.setText("Manhattan");
                state.setText("Kansas");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                assertTrue(state.getBackground().getConstantState().equals(ContextCompat.getDrawable(newGroup.getApplicationContext(), R.drawable.highlight).getConstantState()));
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(!keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() == keySetAfterSave.size());
                assertTrue(keySetBeforeSave.containsAll(keySetAfterSave));
                assertTrue(groupMorphConfig == null || groupMorphConfig.isEmpty());

            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull("Collections activity is launched", collections);
    }

    @Test
     public void saveNewGroupWithInvalidZipFails(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText)newGroup.findViewById((R.id.rName));
        final EditText rancherName = (EditText)newGroup.findViewById((R.id.rnName));
        final EditText email = (EditText)newGroup.findViewById((R.id.rancherEmail));
        final EditText city = (EditText) newGroup.findViewById(R.id.city);
        final EditText state = (EditText) newGroup.findViewById(R.id.state);
        final EditText zip = (EditText) newGroup.findViewById(R.id.zip);
        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ranchName.setText("Manhattan Bull Company");
                rancherName.setText("Kambiz Kotton");
                email.setText("kk@mbc.com");
                email.getOnFocusChangeListener().onFocusChange(email, false);
                city.setText("Manhattan");
                state.setText("KS");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("665023");
                zip.getOnFocusChangeListener().onFocusChange(zip, false);
                assertTrue(zip.getBackground().getConstantState().equals(ContextCompat.getDrawable(newGroup.getApplicationContext(), R.drawable.highlight).getConstantState()));
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(!keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() == keySetAfterSave.size());
                assertTrue(keySetBeforeSave.containsAll(keySetAfterSave));
                assertTrue(groupMorphConfig == null || groupMorphConfig.isEmpty());

            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull("Collections activity is launched", collections);
    }

    @Test
    public void saveNewGroupWithInvalidPhoneFails(){
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText)newGroup.findViewById((R.id.rName));
        final EditText rancherName = (EditText)newGroup.findViewById((R.id.rnName));
        final EditText email = (EditText)newGroup.findViewById((R.id.rancherEmail));
        final EditText city = (EditText) newGroup.findViewById(R.id.city);
        final EditText state = (EditText) newGroup.findViewById(R.id.state);
        final EditText zip = (EditText) newGroup.findViewById(R.id.zip);
        final EditText phone = (EditText) newGroup.findViewById(R.id.phone);
        newGroup.key = groupId;
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ranchName.setText("Manhattan Bull Company");
                rancherName.setText("Kambiz Kotton");
                email.setText("kk@mbc.com");
                email.getOnFocusChangeListener().onFocusChange(email, false);
                city.setText("Manhattan");
                state.setText("KS");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("66502");
                zip.getOnFocusChangeListener().onFocusChange(zip, false);
                phone.setText("7889281");
                phone.getOnFocusChangeListener().onFocusChange(phone, false);
                assertTrue(phone.getBackground().getConstantState().equals(ContextCompat.getDrawable(newGroup.getApplicationContext(), R.drawable.highlight).getConstantState()));
                HashSet<String> keySetBeforeSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                if(keySetBeforeSave==null)
                    keySetBeforeSave = new HashSet<>();
                save.performClick();
                final HashSet<String> keySetAfterSave = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
                final HashSet<String> groupMorphConfig = (HashSet<String>) SharedPrefUtil.getValue(
                        newGroup.getApplicationContext(), Constant.PREFS_GRP_MORPH_CONFIG, groupId);
                assertTrue(!keySetAfterSave.contains(groupId));
                assertTrue(keySetBeforeSave.size() == keySetAfterSave.size());
                assertTrue(keySetBeforeSave.containsAll(keySetAfterSave));
                assertTrue(groupMorphConfig == null || groupMorphConfig.isEmpty());

            }
        });

        Collections collections = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull("Collections activity is launched", collections);
    }

}
