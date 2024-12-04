package stepDefinition;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import utilities.TestContext;

public class HomePageStepDefinition {
	 TestContext testContext;
	 HomePage homePage;
	WebDriverWait wait;
	WebDriver driver;

	/*
	 * Constructor: Injects TestContext to share WebDriver and utilities.
	 *
	 * @param testContext - Shared context for managing test objects and data.
	 */
	public HomePageStepDefinition(TestContext testContext) {
		this.testContext = testContext;
		this.homePage = testContext.getPageManager().getHomePage();
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	}

	/*
	 * Scenario: Verify the presence of security information
	 */

	@Given("User is on the {string} landing page")
	public void user_is_on_the_landing_page(String expectedTitle) {
		// Verify the page title
		homePage.pageTitleVerify(expectedTitle);
	}

	@Then("The security information section is displayed when User clicks on Here’s how you know")
	public void the_security_information_section_is_displayed_when_user_clicks_on_here_s_how_you_know() {
		// Verify "The site is secure." message is displayed
		homePage.verifyTheSiteIsSecure();
	}

//*******************************************************************************
	/*
	 * S Scenario: validate the "I Want a Contract" navigation functionality
	 */

	@When("the user clicks on the {string} menu and selects {string}")
	public void the_user_clicks_on_the_menu_and_selects(String menu, String dropdown) throws InterruptedException {
		if (menu.equals("I Want a Contract")) {
			homePage.clickIWantAContractMenu();
		}

		homePage.selectDropdownItem(dropdown);

	}

	@Then("the user should be redirected to the {string} section")
	public void the_user_should_be_redirected_to_the_section(String sectionTitle) throws InterruptedException {
	    assert homePage.doesSectionExist(sectionTitle) : "Section does not exist: " + sectionTitle;
	    System.out.println("Successfully found the section: " + sectionTitle);
	}


	// *********************************************************
	 /**
     * Scenario Outline: Validate search functionality
     */

	@When("the user searches for {string} in the search field")
	public void the_user_searches_for_in_the_search_field(String string) {
	    homePage.searchItem(string);
	}

	@Then("verify that the search results for {string} are displayed")
	public void verify_that_the_search_results_for_are_displayed(String result) {
	    assert homePage.doesSearchResultExist(result): "Search result does not exist:" + result;
	    System.out.println("Successfully search result display " + result);
	}
	
	// *********************************************************
/*
 *  Scenario: Check Vendor File Status
 */
	
	@When("User clicks on {string} button and then clicks on the login button")
	public void user_clicks_on_button_and_then_clicks_on_the_login_button(String status) throws InterruptedException {
	    homePage.navigateToLoginFromCheckFileStatus(status);
	}

	@Then("Verify the user is redirected to the {string} page")
	public void verify_the_user_is_redirected_to_the_page(String expectedTitle) {
	    homePage.pageTitleVerify(expectedTitle);
	}
	// *********************************************************
/*
 *   Scenario Outline: Access eTools links
 */
	
	@When("the user clicks on the resource {string}")
	public void the_user_clicks_on_the_resource(String link) {
	    homePage.clickOnLinkSource(link);
	}

	@Then("verify the link redirects to the correct {string}")
	public void verify_the_link_redirects_to_the_correct(String expectedPageTitle) {
	    boolean isPageValid = homePage.validateLinkPage(expectedPageTitle);
	    if (!isPageValid) {
	        throw new AssertionError("The redirected page title does not match the expected title: " + expectedPageTitle);
	    }
	}


}
