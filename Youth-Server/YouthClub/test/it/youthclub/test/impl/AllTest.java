package it.youthclub.test.impl;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith (Suite.class)
@SuiteClasses ({
	ApiTest.class,
	BeanTest.class,
	DataModelTesting.class,
	ReviewControlTest.class,
	SearchTest.class })

public class AllTest {

}
