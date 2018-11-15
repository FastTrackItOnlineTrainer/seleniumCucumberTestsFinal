package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.ProductDetailsPage;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsSteps extends TestBase {

    private ProductDetailsPage productDetailsPage = PageFactory.initElements(driver, ProductDetailsPage.class);


    @And("^I store the name of the product$")
    public void iStoreTheNameOfTheProduct() {
        storedProductName = productDetailsPage.getProductNameContainer().getText();
    }

    @And("^I select the color Pink$")
    public void iSelectTheColorPink() {
        productDetailsPage.getPinkColorSelector().click();
    }

    @And("^I select the size S")
    public void iSelectTheSizeS() {
        productDetailsPage.getSmallSizeSelector().click();
    }

    @When("^I press the Add to Cart button$")
    public void iPressTheAddToCartButton() {
        productDetailsPage.getAddToCartButton().click();
    }
}
