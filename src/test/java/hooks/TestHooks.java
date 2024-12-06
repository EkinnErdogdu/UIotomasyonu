package hooks;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class TestHooks {

    public static WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
    }


    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.getStatus().toString().equalsIgnoreCase("FAILED")) {
                System.out.println("Test failed: " + scenario.getName());
            } else if (scenario.getStatus().toString().equalsIgnoreCase("SKIPPED")) {
                System.out.println("Test skipped: " + scenario.getName());
            }


            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("WebDriver kapatma sırasında hata: " + e.getMessage());
        }
    }
}

