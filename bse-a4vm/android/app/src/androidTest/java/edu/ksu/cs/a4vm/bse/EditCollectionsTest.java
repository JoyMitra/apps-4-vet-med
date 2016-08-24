
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
import android.content.Intent;
import android.os.Bundle;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashSet;
import java.util.LinkedHashSet;

import edu.ksu.cs.a4vm.bse.Constants.Constant;
import edu.ksu.cs.a4vm.bse.util.SharedPrefUtil;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditCollectionsTest extends ActivityInstrumentationTestCase2<EditCollections>{
    private Instrumentation.ActivityMonitor activityMonitor;
    private EditCollections editCollections;
    private HashSet<String> grpInfo;
    private String groupId;

    public EditCollectionsTest()
    {
        super(EditCollections.class);
    }

    @Before
    public void setup() throws Exception
    {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        Intent i = new Intent();
        Bundle extras = new Bundle();
        extras.putString("ranch", "<>:<>/<>");
        extras.putString("GrpId", groupId);
        i.putExtras(extras);
        setActivityIntent(i);
        editCollections = getActivity();
        activityMonitor = getInstrumentation().addMonitor(NewGroup.class.getName(), null, false);
        final HashSet<String> keySet = (HashSet<String>) SharedPrefUtil.getValue(
                editCollections.getApplicationContext(), Constant.PREFS_GROUP_INFO, Constant.KEY_GROUP);
        assertNotNull(keySet);
        groupId = keySet.iterator().next().toString();
        grpInfo = (HashSet<String>) SharedPrefUtil.getValue(
                editCollections.getApplicationContext(), Constant.PREFS_GROUP_INFO, groupId);


    }

    @Test
    public void OnClickEditGroupDisplayCurrentGroupInfo(){
        final TextView tvEditGrp = (TextView) editCollections.findViewById(R.id.editGrp);
        editCollections.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvEditGrp.performClick();
            }
        });
        NewGroup newGroup = (NewGroup)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(newGroup);
        Util.setFields(SharedPrefUtil.getValue(newGroup.getApplicationContext(),
                Constant.PREFS_GROUP_INFO, groupId), newGroup.fields);

        EditText ranchName = (EditText) newGroup.findViewById(R.id.rName);
        EditText rancherName = (EditText) newGroup.findViewById(R.id.rnName);
        EditText email = (EditText) newGroup.findViewById(R.id.rancherEmail);
        EditText address1 = (EditText) newGroup.findViewById(R.id.addr1);
        EditText address2 = (EditText) newGroup.findViewById(R.id.addr2);
        EditText city = (EditText) newGroup.findViewById(R.id.city);
        EditText state = (EditText) newGroup.findViewById(R.id.state);
        EditText zip = (EditText) newGroup.findViewById(R.id.zip);
        EditText phone = (EditText) newGroup.findViewById(R.id.phone);

        HashSet<String> data = new LinkedHashSet<String>();
        data.add(ranchName.getHint().toString().trim() + "=" + ranchName.getText().toString().trim().replace(",",";"));
        data.add(rancherName.getHint().toString().trim() + "=" + rancherName.getText().toString().trim().replace(",", ";"));
        data.add(email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";"));
        data.add(address1.getHint().toString().trim() + "=" + address1.getText().toString().trim().replace(",", ";"));
        data.add(address2.getHint().toString().trim() + "=" + address2.getText().toString().trim().replace(",", ";"));
        data.add(city.getHint().toString().trim() + "=" + city.getText().toString().trim().replace(",", ";"));
        data.add(state.getHint().toString().trim() + "=" + state.getText().toString().trim().replace(",", ";"));
        data.add(zip.getHint().toString().trim() + "=" + zip.getText().toString().trim().replace(",", ";"));
        data.add(phone.getHint().toString().trim() + "=" + phone.getText().toString().trim().replace(",",";"));

        assertTrue(grpInfo.containsAll(data));
    }
}
