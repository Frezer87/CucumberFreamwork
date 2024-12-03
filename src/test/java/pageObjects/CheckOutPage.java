package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/*
 * CheckOutPage.java
 *
 * Purpose:
 * Encapsulates all elements (locators) and actions (methods) related to the checkout page.
 * Implements the Page Object Model (POM) design pattern to separate UI interactions from test logic.
 */
public class CheckOutPage {
    // WebDriver instance for interacting with the browser
    public WebDriver driver;

    /*
     * Constructor:
     * Accepts the WebDriver instance from the calling class and initializes the driver.
     *
     * @param driver - The WebDriver instance used to interact with the browser.
     */
    public CheckOutPage(WebDriver driver) {
        this.driver = driver; // Initialize WebDriver
    }

    /*
     * Locators:
     * Defined as private variables to encapsulate the structure of the web page.
     * Each locator corresponds to a specific element on the checkout page.
     */

    // Locator for the cart bag icon
    By cartBag = By.cssSelector("img[alt='Cart']");

    // Locator for the "Proceed to Checkout" button
    By checkOutButton = By.xpath("//button[contains(text(), 'PROCEED TO CHECKOUT')]");

    // Locator for the promo code button
    By promoBtn = By.cssSelector(".promoBtn");

    // Locator for the "Place Order" button
    By placeOrder = By.xpath("//button[contains(text(), 'Place Order')]");

    /*
     * Method: CheckoutItems
     * Purpose:
     * Simulates clicking on the cart bag icon and proceeding to the checkout page.
     * Combines multiple actions into a single reusable method.
     */
    public void CheckoutItems() {
        driver.findElement(cartBag).click(); // Click the cart bag icon to view items in the cart
        driver.findElement(checkOutButton).click(); // Click the "Proceed to Checkout" button
    }

    /*
     * Method: VerifyPromoBtn
     * Purpose:
     * Checks if the promo code button is displayed on the checkout page.
     *
     * @return Boolean - True if the promo button is displayed, false otherwise.
     */
    public Boolean VerifyPromoBtn() {
        return driver.findElement(promoBtn).isDisplayed(); // Check visibility of the promo button
    }

    /*
     * Method: VerifyPlaceOrder
     * Purpose:
     * Checks if the "Place Order" button is displayed on the checkout page.
     *
     * @return Boolean - True if the "Place Order" button is displayed, false otherwise.
     */
    public Boolean VerifyPlaceOrder() {
        return driver.findElement(placeOrder).isDisplayed(); // Check visibility of the "Place Order" button
    }
}

/*
 * Best Practices:
 *
 * 1. Encapsulation:
 *    - Keep locators private to restrict direct access and maintain control over page structure.
 *
 * 2. Reusability:
 *    - Define methods like `CheckoutItems`, `VerifyPromoBtn`, and `VerifyPlaceOrder` to encapsulate common user actions.
 *
 * 3. Naming Conventions:
 *    - Use clear and descriptive method names to indicate their functionality (e.g., `CheckoutItems`, `VerifyPromoBtn`).
 *
 * 4. Scalability:
 *    - As the checkout page evolves, new locators and actions can be added without affecting existing tests.
 *
 * 5. Single Responsibility Principle:
 *    - This page object focuses solely on actions and elements related to the checkout page.
 *
 * Example Usage in Tests:
 * - Call `CheckoutItems` to proceed to checkout.
 * - Use `VerifyPromoBtn` or `VerifyPlaceOrder` to validate UI elements.
 */
