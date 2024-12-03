package Runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/feature", // Corrected path to the folder containing feature files
    glue = "stepDefinition", // Path to the folder containing step definitions
    plugin = { 
        "pretty", // Outputs readable logs for executed steps in the console
        "json:target/cucumber-reports/Cucumber.json", // Generates JSON report for analysis
        "html:target/cucumber-reports/Cucumber.html", // Generates HTML report for test execution
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // ExtentReports for advanced reporting
        "rerun:target/failed_scenarios.txt" // Captures failed scenarios to rerun later
    },
    monochrome = true, // Ensures console output is readable by removing unnecessary characters
     tags = " @offersPage or @placeOrder" //
)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true) // Enables parallel execution of scenarios
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
