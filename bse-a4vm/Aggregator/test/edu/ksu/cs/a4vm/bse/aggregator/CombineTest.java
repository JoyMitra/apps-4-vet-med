/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ksu.cs.a4vm.bse.aggregator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Shubh Chopra
 */
public class CombineTest {
    String testpath = System.getProperty("user.dir") + "/AggregatorTest";
   // String testpath = "C:/Users/Shubh Chopra/Desktop/AggregatorTest";
    public CombineTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of combineandsave method, of class Combine.
     */
   

    /**
     * Test of combineandsavetest method, of class Combine.
     */
    @Test
    public void testDifferentBullId() throws FileNotFoundException, IOException {
        System.out.println("combineandsave");
        
       
        Combine instance = new Combine();
        
         String s1 = testpath + "/Test1";
        String s2 = "";
        instance.combineandsavetest(s1, s2);
        // TODO review the generated test code and remove the default call to fail.
        BufferedReader reader1 = new BufferedReader(new FileReader (s1+"/actual_result.csv"));
        BufferedReader reader2 = new BufferedReader(new FileReader (testpath+"/Test1_result/result.csv"));
        
        String         line1 = null;
        String line2 = null;
   // StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try {
        while((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
            assertTrue(line1.equals(line2));
        }

      
    } finally {
        reader1.close();
        reader2.close();
    }
        
        //fail("The test case is a prototype.");
    }
 @Test
    public void testSameBullIdDisjointInformation() throws FileNotFoundException, IOException {
        System.out.println("combineandsave");
        
        String s1 = testpath + "/Test2";
        String s2 = "";
        Combine instance = new Combine();
        instance.combineandsavetest(s1, s2);
        // TODO review the generated test code and remove the default call to fail.
        BufferedReader reader1 = new BufferedReader(new FileReader (s1+"/actual_result.csv"));
        BufferedReader reader2 = new BufferedReader(new FileReader (testpath+"/Test2_result/result.csv"));
        
        String         line1 = null;
        String line2 = null;
   // StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try {
        while((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
            assertTrue(line1.equals(line2));
        }

      
    } finally {
        reader1.close();
        reader2.close();
    }
        
        //fail("The test case is a prototype.");
    }
    @Test
    public void testSameBullIdContradictoryInformation() throws FileNotFoundException, IOException {
        System.out.println("combineandsave");
        
        String s1 = testpath + "/Test3";
        String s2 = "";
        Combine instance = new Combine();
        instance.combineandsavetest(s1, s2);
        // TODO review the generated test code and remove the default call to fail.
        BufferedReader reader1 = new BufferedReader(new FileReader (s1+"/actual_result.csv"));
        BufferedReader reader2 = new BufferedReader(new FileReader (testpath+"/Test3_result/result.csv"));
        
        String         line1 = null;
        String line2 = null;
   // StringBuilder  stringBuilder = new StringBuilder();
    String         ls = System.getProperty("line.separator");

    try {
        while((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
            assertTrue(line1.equals(line2));
        }

      
    } finally {
        reader1.close();
        reader2.close();
    }
        
        //fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Combine.
     */
  
}
