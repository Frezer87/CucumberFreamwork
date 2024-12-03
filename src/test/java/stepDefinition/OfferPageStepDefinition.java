package stepDefinition;

import Utils.TestContextSetup;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pageObjects.LandingPage;
import pageObjects.OfferPage;

/*
 * OfferPageStepDefinition.java
 *
 * Purpose:
 * Maps Cucumber feature file steps to corresponding Java methods for the Offers Page.
 * Facilitates interactions between the Offers Page and other pages, such as the Landing Page.
 */
public class OfferPageStepDefinition {
	// Shared data variable to store product name from the Offers Page
	public String offerPageProductName;

	// Shared context to manage test data and objects across steps
	TestContextSetup testContextSetup;

	/*
	 * Constructor: Initializes the shared TestContextSetup object.
	 *
	 * @param testContextSetup - Shared context for managing data and objects.
	 */
	public OfferPageStepDefinition(TestContextSetup testContextSetup) {
		this.testContextSetup = testContextSetup;
	}

	/*
	 * Step Definition: Matches the "Then" step in the feature file: -
	 * "user searched for {shortName} shortname in offers page".
	 *
	 * Purpose: - Switches to the Offers Page. - Searches for a product using the
	 * provided short name. - Stores the product name found on the Offers Page for
	 * validation.
	 *
	 * @param shortName - The short name of the product passed dynamically from the
	 * feature file.
	 */

	@Then("^user searched for (.+) shorname in offers page$")
	public void user_searched_for_beet_shorname_in_offers_page(String shortName) {
		// Switch to the Offers Page
		switchToOffersPage();

		// Use the OfferPage object to perform the search
		OfferPage offerPage = testContextSetup.pageObjectManager.getOfferPage();
		offerPage.searchItem(shortName);

		// Store the product name retrieved from the Offers Page
		offerPageProductName = offerPage.getProductName();
	}

	/*
	 * Helper Method: Switches the browser context to the Offers Page.
	 * 
	 * Purpose: - Uses the LandingPage object to navigate to the "Top Deals"
	 * section. - Switches the browser window context to the child window (Offers
	 * Page).
	 */
	public void switchToOffersPage() {
		// Use LandingPage object to click the "Top Deals" link
		LandingPage landingPage = testContextSetup.pageObjectManager.getLandingPage();
		landingPage.selectTopDealsPages();

		// Use GenericUtils to switch the browser context to the child window
		testContextSetup.genericUtils.SwitchWindowToChild();
	}

	/*
	 * Step Definition: Matches the "Then" step in the feature file: -
	 * "validate product name in offers page matches with Landing Page".
	 *
	 * Purpose: - Compares the product name retrieved from the Offers Page with the
	 * one from the Landing Page. - Asserts that the names are equal.
	 */
	@Then("validate product name in offers page matches with Landing Page")
	public void validate_product_name_in_offers_page_matches_with_landing_page() {
		// Assert that the product names match
		Assert.assertEquals(offerPageProductName, testContextSetup.landingPageProductName,
				"Product names do not match between Offers Page and Landing Page.");
	}
}

/*
 * Summary: 1. Maps Cucumber feature steps to meaningful Java methods. 2.
 * Ensures proper flow between the Landing Page and Offers Page using helper
 * methods. 3. Uses shared context (TestContextSetup) for data and object
 * management across steps.
 *
 * Best Practices: - Centralize window-switching logic in a utility class (e.g.,
 * GenericUtils). - Validate steps using assertions and meaningful error
 * messages. - Reuse existing page objects via the PageObjectManager for
 * consistency and modularity.
 */
