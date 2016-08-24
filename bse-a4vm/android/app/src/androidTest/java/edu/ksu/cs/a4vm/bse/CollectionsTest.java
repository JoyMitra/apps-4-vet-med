/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
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
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CollectionsTest extends ActivityInstrumentationTestCase2<Collections> {

    private Instrumentation.ActivityMonitor activityMonitor;
    private Instrumentation.ActivityMonitor activityMonitor1;
    private Instrumentation.ActivityMonitor activityMonitor2;
    private Collections collections;

    public CollectionsTest()
    {
        super(Collections.class);
    }

    @Before
    public void setup() throws Exception
    {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        collections = getActivity();
        activityMonitor = getInstrumentation().addMonitor(NewGroup.class.getName(), null, false);
        activityMonitor1 = getInstrumentation().addMonitor(Collections.class.getName(),null,false);
        activityMonitor2 = getInstrumentation().addMonitor(EditCollections.class.getName(),null,false);
    }

    @Test
    public void displayAllSavedGroups(){
        ListView lv = (ListView)collections.findViewById(R.id.ranchId);
        ListAdapter ldap = lv.getAdapter();
        final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                collections.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
        assertTrue(ldap.getCount()==keySet.size());
    }

    @Test
    public void CollectionsChangeAfterCreateNewGroup(){
        final ListView lv = (ListView)collections.findViewById(R.id.ranchId);
        final ListAdapter ldap = lv.getAdapter();
        final Button newGrpBtn = (Button) collections.findViewById(R.id.addGrpBtn);
        collections.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                newGrpBtn.performClick();
            }
        });
        NewGroup newGroup = (NewGroup)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        final Button save = (Button)newGroup.findViewById(R.id.save);
        final EditText ranchName = (EditText)newGroup.findViewById((R.id.rName));
        newGroup.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ranchName.setText("Sunflower Bull Co.");
                save.performClick();
            }
        });
        final Collections collections1 = (Collections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor1, 5000);
        assertNotNull(collections1);
        final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                collections.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
        assertTrue(ldap.getCount()+1 == keySet.size());

    }

    /*
    Test needs at least 3 groups
     */
    @Test
    public void navigateToEditCollectionsOnCollectionItemClicked(){
        final ListView lv = (ListView)collections.findViewById(R.id.ranchId);
        assertNotNull(lv);
        String selectedItem = lv.getItemAtPosition(0).toString();
        collections.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lv.getOnItemClickListener().onItemClick(lv,lv,0,R.id.ranchId);
            }
        });
        EditCollections editCollections = (EditCollections)getInstrumentation().waitForMonitorWithTimeout(activityMonitor2, 5000);
        assertNotNull(editCollections);
        TextView ranch = (TextView) editCollections.findViewById(R.id.ranchName);
        TextView ranchDt = (TextView) editCollections.findViewById(R.id.ranchDate);
        TextView tvBull = (TextView) editCollections.findViewById(R.id.editBulls);
        TextView tvExport = (TextView) editCollections.findViewById(R.id.export);
        TextView tvEditGrp = (TextView) editCollections.findViewById(R.id.editGrp);
        String r = ranch.getText().toString();
        String d = ranchDt.getText().toString();
        assertEquals(selectedItem,d+":"+r);
        assertNotNull(tvBull);
        assertNotNull(tvExport);
        assertNotNull(tvEditGrp);
    }


}
