package main.java.org.example.pageEvents;

import main.java.org.example.pageObjects.LoginPageElements;
import main.java.org.example.utils.ElementFetch;
import org.testng.Assert;

public class LoginPageEvents {
    ElementFetch elementFetch = new ElementFetch();

    public void enterEmailId(String email) {
        elementFetch.getWebElement("ID", LoginPageElements.emailAddress).sendKeys(email);
    }

    public void enterPassword(String password) {
        elementFetch.getWebElement("ID", LoginPageElements.password).sendKeys(password);
    }

    public void clickOnContinueButton() {
        elementFetch.getWebElement("XPATH", LoginPageElements.continueButton).click();
    }
}
