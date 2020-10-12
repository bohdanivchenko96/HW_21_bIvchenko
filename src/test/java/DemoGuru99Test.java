import org.openqa.selenium.*;


import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class DemoGuru99Test extends BaseUITest {
    private String loginUrl = "http://demo.guru99.com/Agile_Project/Agi_V1/index.php";

    @BeforeMethod
    public void getUrlPath() {
        driver.get(loginUrl);
    }

    public Alert switchToAllert() {
        Alert alert = driver.switchTo().alert();
        return alert;
    }

    @Test
    public void positiveLoginTest() {
        driver.findElement(By.name("uid")).sendKeys("1303");
        driver.findElement(By.name("password")).sendKeys("Guru99");
        driver.findElement(By.name("btnLogin")).click();
        WebElement logoutButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[3]/div/ul/li[3]/a")));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("window.scrollBy(0,250)", "");
        logoutButton.click();
        Assert.assertEquals(switchToAllert().getText(), "You Have Succesfully Logged Out!!");
        switchToAllert().accept();
    }

    @Test
    public void negativeTestEmptyLoginFields() {
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertEquals(switchToAllert().getText(), "User or Password is not valid");
        switchToAllert().accept();
    }

    @Test
    public void negativeTestEmptyPasswordField() {
        driver.findElement(By.name("uid")).sendKeys("1303");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertTrue(driver.findElement(By.id("message18")).isDisplayed());
    }

    @Test
    public void negativeTestEmptyLoginField() {
        driver.findElement(By.name("password")).sendKeys("Guru99");
        driver.findElement(By.name("uid")).sendKeys("");
        driver.findElement(By.name("btnLogin")).click();
        WebElement emptyLoginFieldErrorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("message23")));
        Assert.assertTrue(emptyLoginFieldErrorMessage.isDisplayed());
    }

    @Test
    public void failedLoginField() {
        driver.findElement(By.name("uid")).sendKeys("1303");
        driver.findElement(By.name("password")).sendKeys("failed");
        driver.findElement(By.name("btnLogin")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(switchToAllert().getText(), "User or Password is not valid");
        switchToAllert().accept();
    }

    @Test
    public void failedPasswordField() {
        driver.findElement(By.name("uid")).sendKeys("failed");
        driver.findElement(By.name("password")).sendKeys("Guru99");
        driver.findElement(By.name("btnLogin")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(switchToAllert().getText(), "User or Password is not valid");
        switchToAllert().accept();
    }

    @Test
    public void failedLoginAfterResetingFields() {
        driver.findElement(By.name("uid")).sendKeys("1303");
        driver.findElement(By.name("password")).sendKeys("Guru99");
        driver.findElement(By.name("btnReset")).click();
        driver.findElement(By.name("btnLogin")).click();
        Assert.assertEquals(switchToAllert().getText(), "User or Password is not valid");
        switchToAllert().accept();
    }
}

