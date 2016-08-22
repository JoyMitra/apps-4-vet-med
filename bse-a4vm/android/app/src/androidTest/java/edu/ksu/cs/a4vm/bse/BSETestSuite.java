package edu.ksu.cs.a4vm.bse;

import junit.framework.TestSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Joy on 8/22/16.
 */
// Runs all unit tests.
@RunWith(Suite.class)
@Suite.SuiteClasses({edu.ksu.cs.a4vm.bse.VetInfoActivityTest.class,edu.ksu.cs.a4vm.bse.MorphologyActivityTest.class,
        edu.ksu.cs.a4vm.bse.NewGroupTest.class,edu.ksu.cs.a4vm.bse.CollectionsTest.class,edu.ksu.cs.a4vm.bse.EditCollectionsTest.class,
        edu.ksu.cs.a4vm.bse.BullInfoTest.class,edu.ksu.cs.a4vm.bse.MatingInfoTest.class,edu.ksu.cs.a4vm.bse.MeasurementsTableTest.class,
        edu.ksu.cs.a4vm.bse.PhyExamTest.class,edu.ksu.cs.a4vm.bse.MorphologyDashboardTest.class})
public class BSETestSuite {}
