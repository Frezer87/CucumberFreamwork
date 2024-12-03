package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * OfferPage.java
 *
 * Purpose:
 * Encapsulates all elements (locators) and actions (methods) related to the offers page.
 * Implements the Page Object Model (POM) design pattern to separate UI interactions from test logic.
 */
public class OfferPage {

    // WebDriver instance for interacting with the browser
    public WebDriver driver;

    /*
     * Constructor:
     * Accepts the WebDriver instance from the calling class and initializes the driver.
     *
     * @param driver - The WebDriver instance used to interact with the browser.
     */
    public OfferPage(WebDriver driver) {
        this.driver = driver; // Initialize WebDriver
    }

    /*
     * Locators:
     * - Defined as private variables to encapsulate the structure of the offers page.
     * - Use descriptive names for better readability and maintainability.
     */

    // Locator for the search input field on the offers page
    By search = By.xpath("//input[@type='search']");

    // Locator for the product name displayed in the table
    By productName = By.cssSelector("tbody tr td:nth-child(1)");

    /*
     * Method: searchItem
     * Purpose:
     * Simulates typing a search term into the search input field.
     *
     * @param name - The search term to be entered.
     */
    public void searchItem(String name) {
        driver.findElement(search).sendKeys(name); // Enter the search term in the search field
    }

    /*
     * Method: getProductName
     * Purpose:
     * Retrieves the name of the first product displayed in the search results table.
     *
     * @return String - The name of the first product in the table.
     */
    public String getProductName() {
        return driver.findElement(productName).getText(); // Extract the product name from the table
    }
}

/*
 * Best Practices:
 *
 * 1. Encapsulation:
 *    - Keep locators private to restrict direct access and maintain control over page structure.
 *
 * 2. Reusability:
 *    - Define methods like `searchItem` and `getProductName` to encapsulate common user actions.
 *
 * 3. Naming Conventions:
 *    - Use clear and descriptive method names to indicate their functionality (e.g., `searchItem`, `getProductName`).
 *
 * 4. Scalability:
 *    - As the offers page evolves, new locators and actions can be added without affecting existing tests.
 *
 * 5. Single Responsibility Principle:
 *    - This page object focuses solely on actions and elements related to the offers page.
 *
 * Example Usage in Tests:
 * - Call `searchItem` to filter offers.
 * - Use `getProductName` to validate product details from the offers page.
 */
