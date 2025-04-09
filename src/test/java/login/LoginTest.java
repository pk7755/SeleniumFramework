package test.java.login;

import main.java.org.example.pageEvents.LoginPageEvents;
import org.testng.annotations.Test;
import test.java.BaseTest;
import test.java.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void loginTest() throws InterruptedException {

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        Thread.sleep(5000);
        LoginPageEvents loginPageEvents = new LoginPageEvents();
        loginPageEvents.enterEmailId(username);
        loginPageEvents.clickOnContinueButton();
        loginPageEvents.enterPassword(password);
        loginPageEvents.clickOnContinueButton();
        Thread.sleep(5000);
    }

}
