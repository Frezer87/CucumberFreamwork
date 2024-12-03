package stepDefinition;

import Utils.TestContextSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.LandingPage;

/*
 * LandingPageStepDefinition.java
 *
 * Purpose:
 * This class maps Cucumber feature file steps to corresponding Java methods for the Landing Page.
 * It facilitates interaction with the landing page elements using the LandingPage object.
 * Shared data and objects are managed using TestContextSetup.
 */
public class LandingPageStepDefinition {
	// Shared context to manage test data and objects across steps
	private final TestContextSetup testContextSetup;

	// Object for interacting with elements on the landing page
	private final LandingPage landingPage;

	/*
	 * Constructor: Initializes TestContextSetup and LandingPage objects. -
	 * TestContextSetup is used to share data and objects between step definitions.
	 * - LandingPage is initialized through the PageObjectManager from
	 * TestContextSetup.
	 *
	 * @param testContextSetup - Shared context for managing data and objects.
	 */
	public LandingPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup; // Share the test context setup
		this.landingPage = testContextSetup.pageObjectManager.getLandingPage(); // Initialize the LandingPage object
	}

	/*
	 * Step Definition: Matches the "Given" step in the feature file: -
	 * "User is on GreenCart Landing page". Purpose: - Navigates the user to the
	 * GreenCart landing page.
	 */
	@Given("User is on GreenCart Landing page")
	public void user_is_on_green_cart_landing_page() {
		System.out.println("Navigated to GreenCart Landing page.");
		// You can add code to validate the page load, like checking the page title or
		// specific element visibility.
	}

	/*
	 * Step Definition: Matches the "When" step in the feature file: -
	 * "User searched with shortname {shortName} and extracted actual name of product"
	 * . Purpose: - Searches for a product using a short name. - Extracts and stores
	 * the actual product name in the shared context.
	 *
	 * @param shortName - The short name of the product passed dynamically from the
	 * feature file.
	 * 
	 * Notes: - Replaces Thread.sleep with better wait logic (like WebDriverWait)
	 * for improved stability.
	 */
	@When("^User searched with shortname (.+) and extracted actual name of product$")
	public void user_searched_with_shortname_and_extracted_actual_name_of_product(String shortName)
			throws InterruptedException {
		landingPage.searchItem(shortName); // Perform the product search using the provided short name

		// TODO: Replace Thread.sleep with explicit wait for better performance
		Thread.sleep(2000); // Wait for search results to load

		// Extract and store the actual product name displayed on the website
		testContextSetup.landingPageProductName = landingPage.getProductName().split("-")[0].trim();
		System.out.println("Extracted product name: " + testContextSetup.landingPageProductName);
	}

	/*
	 * Step Definition: Matches the "When" step in the feature file: -
	 * "Added items of the selected product to cart". Purpose: - Adds the selected
	 * product to the cart by clicking the "Add to Cart" button.
	 */
	@When("Added iterms of the selected product to cart")
	public void added_iterms_of_the_selected_product_to_cart() {
		landingPage.addToCartBtn(); // Interact with the landing page to add the product to the cart
	}
}

/*
 * Summary: 1. Maps Cucumber feature file steps to meaningful Java methods for
 * better readability. 2. Uses the TestContextSetup object to share test data
 * and objects between step definitions. 3. Adheres to the Page Object Model
 * (POM) by using the LandingPage class for UI interactions.
 * 
 * Best Practices: - Replace Thread.sleep with WebDriver explicit waits (e.g.,
 * WebDriverWait). - Validate actions, like checking if the product was
 * successfully added to the cart. - Add exception handling for robust test
 * execution.
 */
