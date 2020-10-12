import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class FindStylusLinkTest extends BaseUITest {
    private String loginUrl = "http://google.com";
    String searchUrl = "stylus.ua";

    @BeforeMethod
    public void getUrlPath() {
        driver.get(loginUrl);
    }

    @Test
    public void findStylusLink() {
        enterValuetoSearchGoogleString();
        List<String> searchElementsList = new ArrayList<String>();
        int count = 1;
        while (true) {
            addLinksUrlToList(searchElementsList);
            if (searchElementsList.contains(searchUrl)) {
                System.out.println("STYLUS.UA found on " + count + " page");
                break;
            } else {
                if (count > 5) {
                    System.out.println("STYLUS.UA not found on first 5 pages");
                    break;
                } else {
                    navigateToNextGoogleSearchPage();
                    count++;
                }
            }
        }
        Assert.assertTrue(searchElementsList.contains(searchUrl));
    }

    public List<String> addLinksUrlToList(List<String> searchElementsList) {
        List<WebElement> googleLinkList = driver.findElements(By.tagName("cite"));
        for (WebElement webElement : googleLinkList) {
            String[] words = webElement.getText().split(" ");
            searchElementsList.add(words[0]);
        }
        return searchElementsList;
    }

    public void navigateToNextGoogleSearchPage() {
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)", "");
        driver.findElement(By.id("pnnext")).click();
    }
    public void enterValuetoSearchGoogleString(){
        WebElement googleSearchField = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
        googleSearchField.sendKeys("iphone kyiv buy");
        googleSearchField.sendKeys(Keys.ENTER);
    }
}


