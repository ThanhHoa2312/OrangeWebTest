
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignInTest extends BaseSetUp {
    private WebDriver driver;
    public SignInPage SignInPage;

    @BeforeMethod
    public void setUp() {
        driver = getDriver();
    }


    @Test(dataProvider = "data_signin")
    public void signIn(String Username, String Password) throws Exception{
        System.out.println(driver);
        SignInPage = new SignInPage(driver);
        SignInPage.signin(Username,Password);
        Thread.sleep(5000);
        SignInPage.printAccountName();
        SignInPage.clickAdmin();
    }
}
