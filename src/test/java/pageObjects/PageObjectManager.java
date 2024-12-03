package pageObjects;

import org.openqa.selenium.WebDriver;

/*
 * PageObjectManager.java
 *
 * Purpose:
 * Manages the creation and lifecycle of page objects to ensure that only one instance
 * of each page object exists per test session (Singleton Design Pattern).
 * This approach centralizes page object management and prevents redundant object creation.
 */
public class PageObjectManager {
    // Instance variables for page objects
    private LandingPage landingPage; // For landing page interactions
    private OfferPage offerPage;     // For offers page interactions
    private CheckOutPage checkOutPage; // For checkout page interactions

    // WebDriver instance shared across all page objects
    private WebDriver driver;

    /*
     * Constructor:
     * Accepts the WebDriver instance and initializes the PageObjectManager.
     * The WebDriver instance is passed to each page object when it is created.
     *
     * @param driver - The WebDriver instance used for interacting with the browser.
     */
    public PageObjectManager(WebDriver driver) {
        this.driver = driver; // Initialize WebDriver
    }

    /*
     * Method: getLandingPage
     * Purpose:
     * Returns an instance of the LandingPage class.
     * Creates a new instance if it doesn't already exist, ensuring only one instance per test session.
     *
     * @return LandingPage - The instance of the LandingPage class.
     */
    public LandingPage getLandingPage() {
        if (landingPage == null) { // Check if the LandingPage object is already created
            landingPage = new LandingPage(driver); // Create a new instance if it doesn't exist
        }
        return landingPage; // Return the existing or newly created instance
    }

    /*
     * Method: getOfferPage
     * Purpose:
     * Returns an instance of the OfferPage class.
     * Creates a new instance if it doesn't already exist, ensuring only one instance per test session.
     *
     * @return OfferPage - The instance of the OfferPage class.
     */
    public OfferPage getOfferPage() {
        if (offerPage == null) { // Check if the OfferPage object is already created
            offerPage = new OfferPage(driver); // Create a new instance if it doesn't exist
        }
        return offerPage; // Return the existing or newly created instance
    }

    /*
     * Method: getCheckOutPage
     * Purpose:
     * Returns an instance of the CheckOutPage class.
     * Creates a new instance if it doesn't already exist, ensuring only one instance per test session.
     *
     * @return CheckOutPage - The instance of the CheckOutPage class.
     */
    public CheckOutPage getCheckOutPage() {
        if (checkOutPage == null) { // Check if the CheckOutPage object is already created
            checkOutPage = new CheckOutPage(driver); // Create a new instance if it doesn't exist
        }
        return checkOutPage; // Return the existing or newly created instance
    }
}

/*
 * Best Practices:
 *
 * 1. Singleton Pattern:
 *    - Ensures that each page object is instantiated only once during a test session.
 *    - Prevents redundant object creation, saving memory and improving performance.
 *
 * 2. Centralized Object Management:
 *    - By managing all page objects in one place, it simplifies object lifecycle management.
 *    - Makes the framework more modular and easier to maintain.
 *
 * 3. Reusability:
 *    - Tests can reuse the same page object instances, ensuring consistency across test steps.
 *
 * Example Usage:
 * - Use `PageObjectManager` in your test setup to get the required page objects.
 * - Example:
 *   PageObjectManager pageManager = new PageObjectManager(driver);
 *   LandingPage landingPage = pageManager.getLandingPage();
 *
 * Scalability:
 * - Add more methods to return additional page objects as your framework grows.
 */
