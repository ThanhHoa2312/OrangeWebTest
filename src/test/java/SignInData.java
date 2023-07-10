
import org.testng.annotations.DataProvider;

public class SignInData {
    @DataProvider(name ="data_signin")
    public Object[][] dpMethod() {
        return new Object[][]{
                {"Admin","admin123"}
        };
    }
}
