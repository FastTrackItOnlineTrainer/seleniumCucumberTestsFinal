package org.fasttrackit.steps;

import cucumber.api.java.en.Then;
import org.fasttrackit.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class CommonSteps extends TestBase {

    @Then("^I am redirected to a page with title \"([^\"]*)\"$")
    public void iAmRedirectedToAPageWithTitle(String expectedTitle) {
        WebElement pageTitleContainer = driver.findElement(By.className("page-title"));
        String pageTitle;
        if (pageTitleContainer == null) {
            pageTitle = null;
        } else {
            pageTitle = pageTitleContainer.getText();
        }
        assertThat("Unexpected page title.", pageTitle, is(expectedTitle));
    }

    @Then("^I receive the following confirmation message: \"([^\"]*)\"$")
    public void iReceiveTheFollowingConfirmationMessage(String expectedMessage) {
        WebElement successMessageContainer = driver.findElement(By.cssSelector(".success-msg"));

        assertThat("Confirmation message not displayed.", successMessageContainer, notNullValue());
        assertThat("Unexpected confirmation message.", successMessageContainer.getText(), equalToIgnoringCase(expectedMessage));
    }
}
