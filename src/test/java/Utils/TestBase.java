package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/*
 * TestBase.java
 * 
 * Purpose:
 * Handles WebDriver initialization for different browsers and configurations specified in the global.properties file.
 */
public class TestBase {
    public WebDriver driver;

    /*
     * WebDriverManager Method:
     * Reads the browser and URL configuration from global.properties.
     * Initializes WebDriver for the specified browser using the Singleton pattern.
     *
     * @return WebDriver - The initialized WebDriver instance
     */
    public WebDriver WebDriverManager() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\resources\\global.properties");
        prop.load(fis);

        String browser = prop.getProperty("browser").toLowerCase(); // Browser type
        String url = prop.getProperty("QAUrl"); // Application URL

        if (driver == null) {
            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\resources\\chromedriver.exe");
                    driver = new ChromeDriver();
                    break;

                case "firefox":
                    System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\resources\\geckodriver.exe");
                    driver = new FirefoxDriver();
                    break;

                case "edge":
                    System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\resources\\msedgedriver.exe");
                    driver = new EdgeDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            driver.manage().window().maximize();
            driver.get(url);
        }

        return driver;
    }
}
