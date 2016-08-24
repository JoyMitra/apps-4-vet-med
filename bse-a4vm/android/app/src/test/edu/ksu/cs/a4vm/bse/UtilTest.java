/**
 * Created by Joydeep Mitra on 3/11/16.
 * Copyright (c) 2016, Kansas State University
 * Licensed under Eclipse Public License v1.0
 * http://www.eclipse.org/legal/epl-v10.html
 */
package edu.ksu.cs.a4vm.bse;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Random;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertTrue;

@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class UtilTest{

    private HashSet<String> sampleKeySet = null;
    private HashSet<String> bullKeys = null;
    @Mock
    private VetinfoActivity vetInfo;


    public void getKeyTest(HashSet<String> hset){
        if(hset!=null && !hset.isEmpty()){
            String key = Util.getKey(hset);
            assertThat("key is unique",hset.contains(key),is(false));
        }
        else{
            String key = Util.getKey(hset);
            assertThat("key is unique",key==null,is(false));
        }
    }



    public void getBullKeytest(HashSet<String> bullKeys, HashSet<String> sampleKeySet){

        if(sampleKeySet!=null && !sampleKeySet.isEmpty())
        {
            for(String grpKey : sampleKeySet){
                String key = Util.getBullKey(grpKey,bullKeys);
                assertThat("Bull Key is unique",bullKeys.contains(key),is(false));
            }
        }
        else if(bullKeys!=null && !bullKeys.isEmpty())
        {
            String key = Util.getBullKey(null,bullKeys);
            assertThat("Bull Key is unique",bullKeys.contains(key),is(false));
        }
        else
        {
            String key = Util.getBullKey(null,null);
            assertThat("Bull Key is unique", key != null, is(true));
        }

    }



    @Test
    public void uniqueKeyWithEmptyKeySetsTest(){
        getKeyTest(sampleKeySet);
        sampleKeySet = new HashSet<>();
        bullKeys = new HashSet<>();

        getBullKeytest(bullKeys,sampleKeySet);
    }

    @Test
    public void uniqueKeyWithEmptySampleKeySetTest(){
        getKeyTest(sampleKeySet);
        sampleKeySet = new HashSet<>();
        bullKeys = new HashSet<>();
        for(int i=0;i<3;i++)
        {
            String randNo = Integer.toString(Math.abs(new Random().nextInt()));
            bullKeys.add(randNo);
        }
        getBullKeytest(bullKeys,sampleKeySet);
    }

    @Test
    public void uniqueKeyWithNonEmptyKeySetsTest(){

        sampleKeySet = new HashSet<>();
        bullKeys = new HashSet<>();
        for(int i=0;i<5;i++)
        {
            String randNo = Integer.toString(Math.abs(new Random().nextInt()));
            String randNo1 = Integer.toString(Math.abs(new Random().nextInt()));
            bullKeys.add(randNo);
            sampleKeySet.add(randNo1);
        }
        getKeyTest(sampleKeySet);
        getBullKeytest(bullKeys,sampleKeySet);
    }

    @Test
    public void getDaysInAMonthTest(){
        int year = Math.abs(new Random().nextInt());
        if(year%4==0){
            assertTrue(Util.get_days_of_a_month(2,year)==29);
        }
        else if(year%4!=0){
            assertTrue(Util.get_days_of_a_month(2,year)==28);
        }
        assertTrue(Util.get_days_of_a_month(1,year)==31);
        assertTrue(Util.get_days_of_a_month(3,year)==31);
        assertTrue(Util.get_days_of_a_month(5,year)==31);
        assertTrue(Util.get_days_of_a_month(7,year)==31);
        assertTrue(Util.get_days_of_a_month(8,year)==31);
        assertTrue(Util.get_days_of_a_month(10,year)==31);
        assertTrue(Util.get_days_of_a_month(12,year)==31);

        assertTrue(Util.get_days_of_a_month(4,year)==30);
        assertTrue(Util.get_days_of_a_month(6,year)==30);
        assertTrue(Util.get_days_of_a_month(9, year) == 30);
        assertTrue(Util.get_days_of_a_month(11, year) == 30);
    }

    @Test
    public void EmailValidatorTest(){
        assertFalse(Util.isEmailValid("xhasg"));
        assertFalse(Util.isEmailValid("xh.@edu"));
        assertFalse(Util.isEmailValid("x@_.@edu"));
        assertFalse(Util.isEmailValid("xh@sg@com.in"));

        assertTrue(Util.isEmailValid("xhasg@com.in"));
        assertTrue(Util.isEmailValid(""));
        assertTrue(Util.isEmailValid("xyz@ksu.edu"));


    }

}
