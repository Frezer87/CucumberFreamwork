package pages;

import org.openqa.selenium.WebDriver;
import utilities.TestContext;

public class PageManager {
    private WebDriver driver;
    private TestContext testContext;
    private HomePage homePage;

    // Constructor accepting WebDriver and TestContext
    public PageManager(WebDriver driver, TestContext testContext) {
        this.driver = driver;
        this.testContext = testContext;
    }

    public HomePage getHomePage() {
        if (homePage == null) {
            homePage = new HomePage(driver, testContext);
        }
        return homePage;
    }
}
