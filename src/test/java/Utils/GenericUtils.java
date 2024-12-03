package Utils;

import java.util.Iterator;
import java.util.Set;
import org.openqa.selenium.WebDriver;

/*
 * GenericUtils.java
 *
 * Purpose:
 * - Provides reusable utility methods for Selenium WebDriver.
 * - Includes functionality such as switching to child windows, capturing screenshots, scrolling, etc.
 */
public class GenericUtils {
    // WebDriver instance for interacting with the browser
    private final WebDriver driver;

    /*
     * Constructor:
     * Accepts the WebDriver instance and initializes the GenericUtils object.
     *
     * @param driver - The WebDriver instance used for browser interactions.
     */
    public GenericUtils(WebDriver driver) {
        this.driver = driver;
    }

    /*
     * Method: SwitchWindowToChild
     * Purpose:
     * - Switches the browser's focus from the parent window to the child window.
     *
     * Notes:
     * - Assumes there is exactly one child window and the method is called after the child window is opened.
     */
    public void SwitchWindowToChild() {
        // Get all window handles currently available to the driver
        Set<String> windowHandles = driver.getWindowHandles();

        // Verify that there are at least two windows (parent + child)
        if (windowHandles.size() < 2) {
            throw new IllegalStateException("No child window found to switch to.");
        }

        // Iterate over window handles
        Iterator<String> iterator = windowHandles.iterator();

        // Get parent and child window handles
        String parentWindow = iterator.next(); // Store the parent window handle
        String childWindow = iterator.next();  // Store the child window handle

        // Switch to the child window
        driver.switchTo().window(childWindow);

        // Log or print for debugging (optional)
        System.out.println("Switched to child window: " + childWindow);
    }
}

/*
 * Best Practices:
 *
 * 1. Reusability:
 *    - Add commonly used utility methods (e.g., scrolling, waiting, screenshot capture) here for better modularity.
 *
 * 2. Robustness:
 *    - Include error handling to manage edge cases (e.g., no child window found).
 *
 * 3. Logging:
 *    - Log important actions for traceability during test execution.
 *
 * 4. Scalability:
 *    - Extend this class as needed to include additional utilities, ensuring a single source of reusable methods.
 */
