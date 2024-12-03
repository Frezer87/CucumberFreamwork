package stepDefinition;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import Utils.TestContextSetup;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;

/*
 * Hooks.java
 *
 * Purpose:
 * - Manages hooks for Cucumber scenarios, such as cleanup after each scenario and adding screenshots for failed steps.
 */
public class Hooks {
    // Shared context to manage test data and objects across steps
    private final TestContextSetup testContextSetup;

    /*
     * Constructor:
     * Initializes TestContextSetup for sharing test objects and data.
     *
     * @param testContextSetup - Shared context for managing WebDriver and utilities.
     */
    public Hooks(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup;
    }

    /*
     * After Scenario Hook:
     * - Executes cleanup logic after each scenario, such as closing the WebDriver.
     * - Adds a delay (optional) for debugging purposes.
     */
    @After
    public void AfterScenario() throws IOException, InterruptedException {
        // Optional: Add delay for debugging (can be removed in production)
        Thread.sleep(2000);

        // Quit the WebDriver instance to free resources
        testContextSetup.testBase.WebDriverManager().quit();
    }

    /*
     * After Step Hook:
     * - Captures and attaches a screenshot for any failed step in the scenario.
     *
     * @param scenario - The current Cucumber scenario instance.
     */
    @AfterStep
    public void AddScreenshot(Scenario scenario) throws IOException {
        // Get the WebDriver instance from TestContextSetup
        WebDriver driver = testContextSetup.testBase.WebDriverManager();

        // Check if the scenario has failed
        if (scenario.isFailed()) {
            // Capture the screenshot as a file
            File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Define the screenshot directory and file path
            String screenshotDir = "test-output/SparkReport/screenshots";
            Path destinationPath = Path.of(screenshotDir, sanitizeFileName(scenario.getName()) + ".png");

            // Ensure the directory exists
            Files.createDirectories(destinationPath.getParent());

            // Copy the screenshot to the destination
            Files.copy(sourcePath.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

            // Attach the screenshot to the Cucumber report
            scenario.attach(Files.readAllBytes(destinationPath), "image/png", "Screenshot");
        }
    }

    /*
     * Utility Method:
     * Sanitizes the scenario name to create a valid file name for screenshots.
     *
     * @param name - The original scenario name.
     * @return String - A sanitized version of the scenario name.
     */
    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
