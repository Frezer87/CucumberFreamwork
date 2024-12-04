package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.TestContext;

import java.time.Duration;

public class HomePage {
	public WebDriver driver;
	public TestContext testContext; // Ensure this is public or accessible

	// Constructor: Initialize both driver and testContext
	public HomePage(WebDriver driver, TestContext testContext) {
		this.driver = driver;
		this.testContext = testContext;
	}

	// Locators
	By hereIsHowYouKnow = By.xpath("//span[contains(text(), \"Here’s how you know\")]");
	By theSiteIsSecure = By.xpath("//strong[text()=\"The site is secure.\"]");
	By iWantAContractButton = By.xpath("//strong[contains(text(),'I Want a Contract')]");
	By globalSearchInput = By.xpath("//input[@id='globalSearch']");

	/**
	 * Scenario: Verify the presence of security information
	 */
	public void pageTitleVerify(String expectedTitle) {

		testContext.genericUtils.waitForPageToLoad(); // Access

		// Verify if the page title matches the expected title
		String actualTitle = driver.getTitle();
		if (!actualTitle.equals(expectedTitle)) {
			throw new AssertionError(
					"Page title mismatch: Expected '" + expectedTitle + "', but found '" + actualTitle + "'");
		}
		System.out.println("Page title verified successfully: " + actualTitle);
	}

	public void verifyTheSiteIsSecure() {
		// Click on "Here’s how you know"
		driver.findElement(hereIsHowYouKnow).click();

		// Verify if "The site is secure." is displayed
		boolean isDisplayed = driver.findElement(theSiteIsSecure).isDisplayed();
		if (!isDisplayed) {
			throw new AssertionError("'The site is secure.' message is not displayed.");
		}
		System.out.println("'The site is secure.' message is displayed successfully.");
	}
	// ***********************************************************************************************

	/**
	 * Scenario: Validate the "I Want a Contract" navigation functionality
	 * 
	 * @throws InterruptedException
	 */
	public void clickIWantAContractMenu() throws InterruptedException {
		driver.findElement(iWantAContractButton).click();
		Thread.sleep(2000);

	}

	public void selectDropdownItem(String dropdown) {
		By dropdownLocator = By.xpath("//div[@class='dropdown-menu show']//span[normalize-space()='" + dropdown + "']");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
		wait.until(ExpectedConditions.elementToBeClickable(dropdownLocator)).click(); // Wait until the dropdown item is
																						// clickable and click it
	}

	public boolean doesSectionExist(String sectionTitle) {
		By sectionLocator = By.xpath("//span[@class='font-weight-bold' and contains(text(),'" + sectionTitle + "')]");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(sectionLocator));
			return true; // Element is visible
		} catch (Exception e) {
			return false; // Element not found within the timeout
		}
	}

	// ***********************************************************************************************

	/**
	 * Scenario Outline: Validate search functionality
	 */

	public void searchItem(String item) {
		driver.findElement(globalSearchInput).sendKeys(item, Keys.ENTER);
	}

	public boolean doesSearchResultExist(String results) {
		// Define the locator dynamically using the results string
		By resultLocator = By.xpath("//*[contains(@class,'white') and contains(text(),'" + results + "')]");

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait for up to 10 seconds
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(resultLocator));
			return true; // Element is visible
		} catch (Exception e) {
			return false; // Element not found within the timeout
		}
	}
	// ***********************************************************************************************

	/**
	 * Scenario Outline: Validate search functionality
	 * 
	 * @throws InterruptedException
	 */

	public void navigateToLoginFromCheckFileStatus(String status) throws InterruptedException {
		// Locators
		By checkFileStatusButton = By
				.xpath("//a[@class='btn btn-primary my-lg-3 float-end' and contains(text(),'" + status + "')]");
		By loginButton = By.xpath("//button[@class='btn btn-primary']");

		driver.findElement(checkFileStatusButton).click();

		testContext.genericUtils.waitForPageToLoad(); // wait page to lood

		driver.findElement(loginButton).click();

	}

	// *********************************************************
	/*
	 * Scenario Outline: Access eTools links
	 */
	
	
	public void clickOnLinkSource(String link) {
	    try {
	        // Define the locator for the link
	        By listLinks = By.xpath("//a[text()= '" + link + "']");

	        // Wait for the element to be visible and interactable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        WebElement linkElement = wait.until(ExpectedConditions.elementToBeClickable(listLinks));

	        // Scroll into view using JavaScript if necessary
	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", linkElement);

	        // Attempt to click on the link
	        linkElement.click();
	        System.out.println("Successfully clicked on the link: " + link);

	        // Wait for the page to load after the click
	        testContext.genericUtils.waitForPageToLoad();
	    } catch (ElementClickInterceptedException e) {
	        System.err.println("Element click intercepted. Retrying using JavaScript...");
	        WebElement linkElement = driver.findElement(By.xpath("//a[text()= '" + link + "']"));
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", linkElement);
	    } catch (Exception e) {
	        throw new AssertionError("Failed to click on the link: " + link + ". Error: " + e.getMessage());
	    }
	}

	
	public boolean validateLinkPage(String expectedTitle) {
	    try {
	        // Switch to the child window
	        testContext.genericUtils.SwitchWindowToChild();

	        // Wait for the page to load
	        testContext.genericUtils.waitForPageToLoad();

	        // Get the current page title
	        String currentPageTitle = driver.getTitle();
	        System.out.println("Current Page Title: " + currentPageTitle);

	        // Validate the title
	        if (currentPageTitle.contains(expectedTitle)) {
	            System.out.println("Page title matches the expected title: " + expectedTitle);
	            return true;
	        } else {
	            System.err.println("Page title does not match the expected title.");
	            return false;
	        }
	    } catch (Exception e) {
	        throw new AssertionError("Failed to validate the page title. Error: " + e.getMessage());
	    }
	}


}
