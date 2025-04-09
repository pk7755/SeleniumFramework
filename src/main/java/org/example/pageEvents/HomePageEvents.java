package main.java.org.example.pageEvents;

import main.java.org.example.pageObjects.HomePageElements;
import main.java.org.example.utils.ElementFetch;

public class HomePageEvents {

    public void clickOnSingInButton() {
        ElementFetch elementFetch = new ElementFetch();
        elementFetch.getWebElement("XPATH", HomePageElements.signInButton).click();
    }
}
