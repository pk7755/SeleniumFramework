package automatedTest.demo;

import pageEvents.HomePageEvents;
import pageEvents.LoginPageEvents;
import org.testng.annotations.Test;
import automatedTest.BaseTest;

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
