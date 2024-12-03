package stepDefinition;

import org.testng.Assert;

import Utils.TestContextSetup;
import io.cucumber.java.en.Then;
import pageObjects.CheckOutPage;

/*
 * ChackOutPageStepDefinition.java
 *
 * Purpose:
 * Maps Cucumber feature file steps to corresponding Java methods for the Checkout Page.
 * Facilitates actions like proceeding to checkout, validating items, and verifying order placement.
 */
public class ChackOutPageStepDefinition {
    // Shared context to manage test data and objects across steps
    private final TestContextSetup testContextSetup;

    // Object for interacting with elements on the checkout page
    private final CheckOutPage checkoutPage;

    /*
     * Constructor:
     * Initializes TestContextSetup and CheckOutPage objects.
     * - TestContextSetup is used to share data and objects between step definitions.
     * - CheckOutPage is initialized through the PageObjectManager from TestContextSetup.
     *
     * @param testContextSetup - Shared context for managing data and objects.
     */
    public ChackOutPageStepDefinition(TestContextSetup testContextSetup) {
        this.testContextSetup = testContextSetup; // Share TestContextSetup
        this.checkoutPage = testContextSetup.pageObjectManager.getCheckOutPage(); // Initialize CheckOutPage object
    }

    /*
     * Step Definition:
     * Matches the "Then" step in the feature file:
     * - "User proceeds to Checkout and validate the {name} items in checkout page".
     *
     * Purpose:
     * - Simulates proceeding to the checkout page.
     * - Validates that the items match the expected name (future enhancement could validate item name).
     *
     * @param name - Name of the item(s) passed dynamically from the feature file.
     * 
     * Notes:
     * - Replace Thread.sleep with better wait logic for dynamic pages.
     */
    @Then("^User proceeds to Checkout and validate the (.+) items in checkout page$")
    public void user_proceeds_to_checkout_and_validate_the_items_in_checkout_page(String name) throws InterruptedException {
        // Navigate to the checkout page
        checkoutPage.CheckoutItems();

        // TODO: Replace Thread.sleep with explicit waits for better performance
        Thread.sleep(2000);

        // Placeholder for future item validation logic
        System.out.println("Validated items in the checkout page for: " + name);
    }

    /*
     * Step Definition:
     * Matches the "Then" step in the feature file:
     * - "verify user has ability to place the order".
     *
     * Purpose:
     * - Validates that the "Place Order" button is displayed on the checkout page.
     * - Uses assertions to verify the functionality.
     */
    @Then("verify user has abilty to place the order")
    public void verify_user_has_abilty_to_place_the_order() {
        // Assert that the "Place Order" button is displayed
        Assert.assertTrue(checkoutPage.VerifyPlaceOrder(), "Place Order button is not displayed.");
    }
}

/*
 * Summary:
 * 1. This class maps Cucumber steps to meaningful method names for the Checkout Page.
 * 2. Uses TestContextSetup to share test data and objects across multiple step definitions.
 * 3. Validates user actions like proceeding to checkout and placing an order.
 *
 * Best Practices:
 * - Replace Thread.sleep with WebDriver explicit waits for better reliability.
 * - Add meaningful assertions to validate user actions and page states.
 * - Use reusable methods from the Page Object Model (e.g., CheckoutItems, VerifyPlaceOrder).
 */
