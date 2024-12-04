package Hooks;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.TestContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/*
 * Hooks.java
 *
 * Purpose:
 * - Manages hooks for Cucumber scenarios, including cleanup and screenshots.
 */
public class Hooks {
    private final TestContext testContext;

    /*
     * Constructor:
     * Initializes TestContext to share test objects and data across steps.
     */
    public Hooks(TestContext testContext) {
        this.testContext = testContext;
    }

    /*
     * After Scenario Hook:
     * - Ensures WebDriver instance is closed after each scenario.
     */
    @After
    public void afterScenario() throws InterruptedException {
    	
    	Thread.sleep(1000);
        try {
            WebDriver driver = testContext.getDriver(); // Get WebDriver instance from TestContext
            if (driver != null) {
                driver.quit(); // Close the browser
                testContext.getBaseTest().driver = null; // Reset driver in BaseTest to null
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print stack trace for any exception
        }
    }


    /*
     * After Step Hook:
     * - Captures and attaches screenshots for failed steps.
     */
    @AfterStep
    public void addScreenshot(Scenario scenario) {
        WebDriver driver = testContext.getDriver();
        if (scenario.isFailed() && driver != null) {
            try {
                File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                String screenshotDir = System.getProperty("user.dir") + "\\test-output\\screenshots\\";
                Path destinationPath = Path.of(screenshotDir, sanitizeFileName(scenario.getName()) + ".png");

                Files.createDirectories(destinationPath.getParent());
                Files.copy(sourcePath.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);

                scenario.attach(Files.readAllBytes(destinationPath), "image/png", "Failed Step Screenshot");
            } catch (IOException e) {
                System.err.println("Error capturing screenshot: " + e.getMessage());
            }
        }
    }

    /*
     * Utility Method:
     * Sanitizes the scenario name to create a valid file name for screenshots.
     */
    private String sanitizeFileName(String name) {
        return name.replaceAll("[^a-zA-Z0-9._-]", "_");
    }
}
