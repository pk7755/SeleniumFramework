package test.java.Demo;

import main.java.org.example.pageEvents.HomePageEvents;
import main.java.org.example.pageEvents.LoginPageEvents;
import org.testng.annotations.Test;
import test.java.BaseTest;

public class SampleTest extends BaseTest {

    @Test
    public void sampleMethodForEmailEntering() throws InterruptedException {
        Thread.sleep(5000);
        HomePageEvents homePageEvents = new HomePageEvents();
        homePageEvents.clickOnSingInButton();

        LoginPageEvents loginPageEvents = new LoginPageEvents();
//        loginPageEvents.verifyLoginPageIsOpenOrNot();
        loginPageEvents.enterEmailId("pradyumna@gmail.com");
    }
}
