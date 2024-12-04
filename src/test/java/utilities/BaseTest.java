package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

/*
 * BaseTest
 * Purpose:
 * - Manages WebDriver setup and configuration based on global properties.
 */
public class BaseTest {
    public WebDriver driver; // Make this public for shared context
    private final Properties prop;

    /*
     * Constructor:
     * Loads configuration properties from the global.properties file.
     */
    public BaseTest() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\global.properties");
        prop.load(fis);
    }

    /*
     * WebDriverManager:
     * Initializes and returns a singleton WebDriver instance.
     */
    public WebDriver WebDriverManager() {
        if (driver == null) {
            String browser = prop.getProperty("browser").toLowerCase();
            String url = prop.getProperty("QAUrl");

            switch (browser) {
                case "chrome":
                    System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\chromedriver.exe");
                    driver = new ChromeDriver();
                    break;

                case "edge":
                    System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "\\src\\test\\resources\\msedgedriver.exe");
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
