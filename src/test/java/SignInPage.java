
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignInPage {
    private WebDriver driver;
    private By usernameInput = By.name("username");
    private By passwordInput = By.name("password");

    private By AccountName = By.className("oxd-userdropdown-name");
    private By loginButton = By.xpath("//button[@type = \"submit\"]");
    private By AdminButton = By.xpath("//*[@id=\"app\"]/div[1]/div[1]/aside/nav/div[2]/ul/li[1]/a");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getSignInPageTitle() {
        String pageTitle = driver.getTitle();
        return pageTitle;
    }

    public boolean verifySignInPageTitle(){
        String expectedTitle = "OrangeHRM";
        return getSignInPageTitle().equals(expectedTitle);
    }

    public void enterUsername(String username){
        WebElement usernameTxt = driver.findElement(usernameInput);
        if (usernameTxt.isDisplayed()) {
            usernameTxt.sendKeys(username);
        }
    }

    public  void enterPassword(String password) {
        WebElement passwordTxt = driver.findElement(passwordInput);
        if (passwordTxt.isDisplayed()) {
            passwordTxt.sendKeys(password);
        }
    }

    public void waitForPageLoaded() {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
                        .equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
            wait.until(expectation);
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for Page Load Request to complete.");
        }
    }

    public void signin(String username, String password) throws InterruptedException {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

    private void clickSignIn() {
        WebElement signin = driver.findElement(loginButton);
        if (signin.isDisplayed()) {
            signin.click();
        }
    }

    public void printAccountName() {
        WebElement accountName = driver.findElement(AccountName);
        System.out.println(accountName.getText());
    }
    public void clickAdmin() {
        WebElement admin = driver.findElement(AdminButton);
        if (admin.isDisplayed()) {
            admin.click();
        }
    }
}

