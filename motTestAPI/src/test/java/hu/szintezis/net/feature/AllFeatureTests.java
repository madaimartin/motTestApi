package hu.szintezis.net.feature;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({ 
	OwnerFeatureTest.class, 
	VehicleFeatureTest.class, 
	TesterPersonFeatureTest.class,
	MotTestCenterFeatureTest.class,
	MotTestFeatureTest.class
})
public class AllFeatureTests {

	/*
	 * With running this file as jUnit test
	 * it executes my every feature test classes
	 * 
	 * Additional info / source:
	 * https://enji.systems/2022/06/06/running-multiple-junit-classes.html
	 */
}
