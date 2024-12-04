package utilities;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import pages.PageManager;

public class TestContext {
    private BaseTest baseTest;
    private WebDriver driver;
    private PageManager pageManager;
    public GenericUtils genericUtils;

    public TestContext() {
        try {
            baseTest = new BaseTest();
            driver = baseTest.WebDriverManager();
            
            // Use the appropriate PageManager constructor
            pageManager = new PageManager(driver, this);
            
            genericUtils = new GenericUtils(driver);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize TestContext: " + e.getMessage(), e);
        }
    }

    public BaseTest getBaseTest() {
        return baseTest;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public PageManager getPageManager() {
        return pageManager;
    }

    public GenericUtils getGenericUtils() {
        return genericUtils;
    }
}
