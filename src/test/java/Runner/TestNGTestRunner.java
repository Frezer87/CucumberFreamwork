package Runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features", // Path to feature files
    glue = {"stepDefinition", "Hooks"}, // Include step definitions and hooks
    plugin = { 
        "pretty", 
        "json:target/cucumber-reports/Cucumber.json",
        "html:target/cucumber-reports/Cucumber.html",
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
        "rerun:target/failed_scenarios.txt"
    },
    monochrome = true, 
    tags = " @smokeTest" // Execute scenarios with this tag
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

//    @Override
//    @DataProvider(parallel = true) // Uncomment to enable parallel execution
//    public Object[][] scenarios() {
//        return super.scenarios();
//    }
}
