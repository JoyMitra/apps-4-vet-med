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
public class VetInfoActivityTest extends ActivityInstrumentationTestCase2<VetinfoActivity>{

    private VetinfoActivity vetinfoActivity;

    public VetInfoActivityTest(){super(VetinfoActivity.class);}

    @Before
    public void setup() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
        vetinfoActivity = getActivity();
    }

    @Test
    public void saveVetInfoWithAllValidValuesPass(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                lastName.setText("Doe");
                clinic.setText("Doe's Clinic;");
                addr1.setText("1211, Mosier Hall");
                addr2.setText("");
                city.setText("Manhattan");
                state.setText("KS");
                zip.setText("66504");
                email.setText("doe.Jane@ksu.edu");
                saveBtn.performClick();
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = firstName.getHint().toString().trim() + "=" + firstName.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = lastName.getHint().toString().trim() + "=" + lastName.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = clinic.getHint().toString().trim() + "=" + clinic.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = addr1.getHint().toString().trim() + "=" + addr1.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = addr2.getHint().toString().trim() + "=" + addr2.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = city.getHint().toString().trim() + "=" + city.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = state.getHint().toString().trim() + "=" + state.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = zip.getHint().toString().trim() + "=" + zip.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithAllValidValues1Pass(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                lastName.setText("Doe");
                clinic.setText("Doe's Clinic;");
                addr1.setText("1211, Mosier Hall");
                addr2.setText("");
                city.setText("Manhattan");
                state.setText("KS");
                zip.setText("66504");
                email.setText("");
                saveBtn.performClick();
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = firstName.getHint().toString().trim() + "=" + firstName.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = lastName.getHint().toString().trim() + "=" + lastName.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = clinic.getHint().toString().trim() + "=" + clinic.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = addr1.getHint().toString().trim() + "=" + addr1.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = addr2.getHint().toString().trim() + "=" + addr2.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = city.getHint().toString().trim() + "=" + city.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = state.getHint().toString().trim() + "=" + state.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = zip.getHint().toString().trim() + "=" + zip.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
                data = email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";");
                assertTrue(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithInvalidFirstNameFail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                clinic.setText("Doe's Clinic");
                addr1.setText("1211 Mosier Hall");
                addr2.setText("");
                city.setText("Manhattan");
                state.setText("KS");
                zip.setText("66504");
                email.setText("doe.Jane@ksu.edu");
                saveBtn.performClick();
                assertEquals(firstName.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = firstName.getHint().toString().trim() + "=" + firstName.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithInvalidLastNameFail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("Doe's Clinic");
                addr1.setText("1211 Mosier Hall");
                addr2.setText("");
                city.setText("Manhattan");
                state.setText("KS");
                zip.setText("66504");
                email.setText("doe.Jane@ksu.edu");
                saveBtn.performClick();
                assertEquals(lastName.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = lastName.getHint().toString().trim() + "=" + lastName.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithOnlyNamePass(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("");
                clinic.getOnFocusChangeListener().onFocusChange(clinic, false);
                addr1.setText("");
                addr2.setText("");
                city.setText("");
                state.setText("");
                zip.setText("");
                email.setText(" ");
                saveBtn.performClick();
                assertEquals(clinic.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.focus_color).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = clinic.getHint().toString().trim() + "=" + clinic.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertTrue(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNotNull(settingsActivity);
    }


    @Test
    public void saveVetInfoWithInvalidStateFail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("Doe's Clinic");
                clinic.getOnFocusChangeListener().onFocusChange(clinic, false);
                addr1.setText("1211 Mosier Hall");
                addr1.getOnFocusChangeListener().onFocusChange(addr1, false);
                addr2.setText("");
                city.setText(" ");
                city.getOnFocusChangeListener().onFocusChange(city, false);
                state.setText("Kansas");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("66504");
                email.setText("doe.Jane@ksu.edu");
                saveBtn.performClick();
                assertEquals(state.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = state.getHint().toString().trim() + "=" + state.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithInvalidZipFail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("Doe's Clinic");
                clinic.getOnFocusChangeListener().onFocusChange(clinic, false);
                addr1.setText("1211 Mosier Hall");
                addr1.getOnFocusChangeListener().onFocusChange(addr1, false);
                addr2.setText("");
                city.setText(" ");
                city.getOnFocusChangeListener().onFocusChange(city, false);
                state.setText("Kansas");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("3");
                zip.getOnFocusChangeListener().onFocusChange(zip, false);
                email.setText("doe.Jane@ksu.edu");
                saveBtn.performClick();
                assertEquals(zip.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = zip.getHint().toString().trim() + "=" + zip.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }


    @Test
    public void saveVetInfoWithInvalidEmail1Fail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("Doe's Clinic");
                clinic.getOnFocusChangeListener().onFocusChange(clinic, false);
                addr1.setText("1211 Mosier Hall");
                addr1.getOnFocusChangeListener().onFocusChange(addr1, false);
                addr2.setText("");
                city.setText(" ");
                city.getOnFocusChangeListener().onFocusChange(city, false);
                state.setText("Kansas");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("");
                zip.getOnFocusChangeListener().onFocusChange(zip, false);
                email.setText("janedoe.com");
                email.getOnFocusChangeListener().onFocusChange(email,false);
                saveBtn.performClick();
                assertEquals(email.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithInvalidEmail2Fail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("Doe's Clinic");
                clinic.getOnFocusChangeListener().onFocusChange(clinic, false);
                addr1.setText("1211 Mosier Hall");
                addr1.getOnFocusChangeListener().onFocusChange(addr1, false);
                addr2.setText("");
                city.setText(" ");
                city.getOnFocusChangeListener().onFocusChange(city, false);
                state.setText("Kansas");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("");
                zip.getOnFocusChangeListener().onFocusChange(zip,false);
                email.setText("jane.doe@gmail..com");
                email.getOnFocusChangeListener().onFocusChange(email,false);
                saveBtn.performClick();
                assertEquals(email.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }

    @Test
    public void saveVetInfoWithInvalidEmail3Fail(){
        Instrumentation.ActivityMonitor activityMonitor;
        activityMonitor = getInstrumentation().addMonitor(SettingsActivity.class.getName(), null, false);
        final Button saveBtn = (Button)vetinfoActivity.findViewById(R.id.vetInfoSave);
        final EditText firstName = (EditText)vetinfoActivity.findViewById(R.id.firstName);
        final EditText lastName = (EditText)vetinfoActivity.findViewById(R.id.lastName);
        final EditText clinic = (EditText)vetinfoActivity.findViewById(R.id.clinic);
        final EditText addr1 = (EditText)vetinfoActivity.findViewById(R.id.addr1);
        final EditText addr2 = (EditText)vetinfoActivity.findViewById(R.id.addr2);
        final EditText city = (EditText)vetinfoActivity.findViewById(R.id.city);
        final EditText state = (EditText)vetinfoActivity.findViewById(R.id.state);
        final EditText zip = (EditText)vetinfoActivity.findViewById(R.id.zip);
        final EditText email = (EditText)vetinfoActivity.findViewById(R.id.email);
        vetinfoActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                firstName.setText("Jane");
                firstName.getOnFocusChangeListener().onFocusChange(firstName, false);
                lastName.setText("Doe");
                lastName.getOnFocusChangeListener().onFocusChange(lastName, false);
                clinic.setText("Doe's Clinic");
                clinic.getOnFocusChangeListener().onFocusChange(clinic, false);
                addr1.setText("1211 Mosier Hall");
                addr1.getOnFocusChangeListener().onFocusChange(addr1, false);
                addr2.setText("");
                city.setText(" ");
                city.getOnFocusChangeListener().onFocusChange(city, false);
                state.setText("Kansas");
                state.getOnFocusChangeListener().onFocusChange(state, false);
                zip.setText("");
                zip.getOnFocusChangeListener().onFocusChange(zip,false);
                email.setText("jane.ksu@edu");
                email.getOnFocusChangeListener().onFocusChange(email,false);
                saveBtn.performClick();
                assertEquals(email.getBackground().getConstantState(),
                        ContextCompat.getDrawable(vetinfoActivity.getApplicationContext(), R.drawable.highlight).
                                getConstantState());
                HashSet<String> afterSave = (HashSet<String>) SharedPrefUtil.
                        getValue(vetinfoActivity.getApplicationContext(), Constant.PREFS_FILE_VET_INFO,Constant.KEY_VET);
                String data = email.getHint().toString().trim() + "=" + email.getText().toString().trim().replace(",", ";");
                if(afterSave!=null)
                    assertFalse(afterSave.contains(data));
            }
        });

        SettingsActivity settingsActivity = (SettingsActivity)getInstrumentation().waitForMonitorWithTimeout(activityMonitor, 5000);
        assertNull(settingsActivity);
    }

}
