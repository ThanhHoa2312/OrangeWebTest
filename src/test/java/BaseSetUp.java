
import org.openqa.selenium.WebDriver;
import  org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseSetUp extends SignInData {
    private WebDriver driver;
    static String driverPath = "C:\\Users\\ADMIN\\Desktop\\test selenium\\chromedriver_win32\\";
    public WebDriver getDriver(){
        return driver;
    }

    private void setDriver(String browserType, String appURL){
        switch (browserType) {
            case "chrome" :
                driver = initChromeDriver(appURL);
                break;
            default:
                System.out.println("Browser: " + browserType + " is invalid, Launching Chrome as browser of choice ...");
                driver = initChromeDriver(appURL);
        }
    }

    private static WebDriver initChromeDriver(String appURL) {
        System.out.println("Launching Chrome browser...");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to(appURL);
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        return driver;
    }

    @Parameters({"browserType","appURL"})
    @BeforeMethod
    public void initializeTestBaseSetup(String browserType, String appURL){
        try {
            setDriver(browserType, appURL);
        }catch (Exception e){
            System.out.println("Error..." + e.getStackTrace());
        }
    }

    @AfterMethod
    public void tearDown() throws Exception {
        Thread.sleep(2000);
        driver.quit();
    }
}
