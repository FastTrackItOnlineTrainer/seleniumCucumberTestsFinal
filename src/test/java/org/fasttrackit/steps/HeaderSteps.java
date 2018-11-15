package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import java.util.Arrays;

public class HeaderSteps extends TestBase {

    private Header header = PageFactory.initElements(driver, Header.class);

    @And("^I search products by \"(.+)\"$")
    public void iSearchAProductBy(String keyword) {
        header.search(keyword);
    }

    @And("^I open the sub-category \"([^\"]*)\" from the \"([^\"]*)\" category$")
    public void iOpenTheSubCategoryFromTheCategory(String subCategory, String category) {
        By womenCategoryLocator = By.linkText(category);
        By newArrivalsSubCategoryLocator = By.linkText(subCategory);
        mouseOverAndClickLast(Arrays.asList(womenCategoryLocator, newArrivalsSubCategoryLocator));
    }

    @And("^I go to the Register page$")
    public void iGoToTheRegisterPage() {
        header.getAccountLink().click();
        header.getRegisterLink().click();
    }
}
