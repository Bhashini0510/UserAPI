package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Feature", plugin = { "pretty",
		"html:target/cucumber-reports.html" }, glue = { "stepDefinations" })

public class TestRunner extends AbstractTestNGCucumberTests {

	// @DataProvider(parallel = true)

	// @Override public Object[][] scenarios() {
	// return super.scenarios();
	// }
}
