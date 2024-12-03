package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * LandingPage.java
 *
 * Purpose:
 * Encapsulates all elements (locators) and actions (methods) related to the landing page.
 * Implements the Page Object Model (POM) design pattern to separate page-specific functionality from test logic.
 */
public class LandingPage {
    // WebDriver instance for interacting with the browser
    public WebDriver driver;

    /*
     * Constructor:
     * Accepts the WebDriver instance from the calling class and initializes the driver.
     *
     * @param driver - The WebDriver instance used to interact with the browser.
     */
    public LandingPage(WebDriver driver) {
        this.driver = driver; // Initialize WebDriver
    }

    /*
     * Locators:
     * - Defined as private variables to encapsulate the structure of the web page.
     * - Use descriptive names for better readability and maintainability.
     */

    // Locator for the search input field
    By search = By.xpath("//input[@type='search']");

    // Locator for the product name displayed in search results
    By productName = By.cssSelector("h4.product-name");

    // Locator for the "Top Deals" link
    By topDeals = By.linkText("Top Deals");

    // Locator for the "Add to Cart" button
    By addToCart = By.xpath("//button[text()='ADD TO CART']");

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
     * Retrieves the name of the first product displayed in the search results.
     *
     * @return String - The name of the first product.
     */
    public String getProductName() {
        return driver.findElement(productName).getText(); // Extract the product name from the web element
    }

    /*
     * Method: selectTopDealsPages
     * Purpose:
     * Navigates to the "Top Deals" page by clicking on the "Top Deals" link.
     */
    public void selectTopDealsPages() {
        driver.findElement(topDeals).click(); // Click the "Top Deals" link
    }
    
    /*
     * Method: addToCartBtn
     * Purpose:
     * Adds the selected product to the cart by clicking the "Add to Cart" button.
     */
    public void addToCartBtn() {
        driver.findElement(addToCart).click(); // Click the "Add to Cart" button
    }
}

/*
 * Best Practices:
 *
 * 1. Encapsulation:
 *    - Keep locators private to restrict direct access and maintain control over page structure.
 *
 * 2. Reusability:
 *    - Define methods that represent common user actions on the page (e.g., searchItem, getProductName).
 *
 * 3. Naming Conventions:
 *    - Use descriptive method and variable names for clarity and maintainability.
 *
 * 4. Scalability:
 *    - Add more methods as the page functionality grows while keeping it modular and cohesive.
 *
 * 5. Single Responsibility Principle:
 *    - Each page object should represent a single page or module, focusing only on its elements and actions.
 */
