package automatedTest.login;

import annotations.RunThisTest;
import pageEvents.LoginPageEvents;
import org.testng.annotations.Test;
import automatedTest.BaseTest;
import utils.ConfigReader;


public class LoginTest extends BaseTest {

    @Test
    @RunThisTest("LoginTest")
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
        System.out.println("ReportPortal endpoint: " + com.epam.reportportal.utils.properties.PropertiesLoader.load().getProperty("rp.endpoint"));
    }

    @Test
    @RunThisTest("DashBoardTest")
    public void loginTest1() throws InterruptedException {
        System.out.println("env: " + System.getProperty("env"));
        System.out.println("runAnnotation: " + System.getProperty("runAnnotation"));

        String username = ConfigReader.get("username");
        String password = ConfigReader.get("password");

        Thread.sleep(5000);
        LoginPageEvents loginPageEvents = new LoginPageEvents();
        loginPageEvents.enterEmailId(username);
        loginPageEvents.clickOnContinueButton();
        loginPageEvents.enterPassword(password);
        loginPageEvents.clickOnContinueButton();
        Thread.sleep(5000);
        System.out.println("ReportPortal endpoint: " + com.epam.reportportal.utils.properties.PropertiesLoader.load().getProperty("rp.endpoint"));
    }

}
