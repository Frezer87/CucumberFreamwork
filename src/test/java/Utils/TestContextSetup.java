package Utils;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import pageObjects.PageObjectManager;

/*
 * TestContextSetup.java
 *
 * Purpose:
 * This class manages shared objects and utilities, enabling them to be reused across different step definitions.
 * It acts as a central repository for WebDriver, PageObjectManager, and custom utility classes.
 */
public class TestContextSetup {
    // WebDriver instance shared across steps
    public WebDriver driver;

    // Variable to store data between steps (e.g., the product name from the landing page)
    public String landingPageProductName;

    // Manages page object instances to implement the Page Object Model (POM)
    public PageObjectManager pageObjectManager;

    // Initializes and manages WebDriver instances
    public TestBase testBase;

    // Utility class for reusable helper methods
    public GenericUtils genericUtils;

    /*
     * Constructor:
     * Initializes all shared objects like WebDriver, PageObjectManager, and utilities.
     *
     * - Creates a WebDriver instance using TestBase.
     * - Initializes the PageObjectManager with the WebDriver instance.
     * - Creates an instance of GenericUtils for reusable utility methods.
     *
     * @throws IOException - Handles any file input/output errors during initialization.
     */
    public TestContextSetup() throws IOException {
        // Initialize TestBase to manage WebDriver
        testBase = new TestBase();

        // Create a WebDriver instance using TestBase's WebDriverManager method
        driver = testBase.WebDriverManager();

        // Initialize the PageObjectManager with the WebDriver instance
        pageObjectManager = new PageObjectManager(driver);

        // Initialize GenericUtils with the WebDriver instance
        genericUtils = new GenericUtils(driver);
    }
}

/*
 * Best Practices:
 *
 * 1. Centralize setup logic:
 *    - Managing WebDriver, PageObjectManager, and shared utilities in one place ensures consistency and reusability.
 *
 * 2. Use shared data variables:
 *    - `landingPageProductName` is an example of shared data that can be passed between steps.
 *
 * 3. Extend for scalability:
 *    - Add additional utilities or shared objects as your framework grows.
 *
 * Example Usage:
 * - Step definitions can access TestContextSetup to get WebDriver, page objects, or utilities without reinitializing them.
 *
 */
