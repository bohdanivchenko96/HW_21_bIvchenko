import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class BaseUITest {

    public WebDriver driver;
    public WebDriverWait wait;
    @BeforeClass
    public void initialChromeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
    }

    @AfterClass
    public void closeWebDriver() {
        driver.quit();
    }
}
