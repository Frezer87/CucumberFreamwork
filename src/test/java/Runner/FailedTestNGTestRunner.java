package Runner;

import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/*
 * FailedTestNGTestRunner.java
 *
 * Purpose:
 * - Re-runs failed Cucumber scenarios captured in `target/failed_scenarios.txt`.
 * - Supports parallel execution and generates detailed reports.
 */
@CucumberOptions(
    features = "@target/failed_scenarios.txt", // Reads the list of failed scenarios from the specified file
    glue = "stepDefinition", // Path to the folder containing step definitions
    plugin = { 
        "pretty", // Outputs readable logs for executed steps in the console
        "json:target/cucumber-reports/FailedCucumber.json", // Generates JSON report for failed test cases
        "html:target/cucumber-reports/FailedCucumber.html", // Generates HTML report for failed scenarios
        "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", // ExtentReports for detailed reporting
        "rerun:target/failed_scenarios.txt" // Captures failed scenarios for further reruns
    },
    monochrome = true // Ensures console output is clean and readable
)
public class FailedTestNGTestRunner extends AbstractTestNGCucumberTests {

    /*
     * DataProvider for Parallel Execution:
     * - Enables parallel execution of failed scenarios.
     * - Improves execution efficiency for large suites.
     */
    @Override
    @DataProvider(parallel = false) // Set to 'true' for parallel execution, 'false' for sequential execution
    public Object[][] scenarios() {
        // Retrieves scenarios from the parent class (AbstractTestNGCucumberTests)
        return super.scenarios();
    }
}
