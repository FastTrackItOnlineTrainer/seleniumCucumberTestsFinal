package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.ProductsGrid;
import org.openqa.selenium.support.PageFactory;

public class ProductsGridSteps extends TestBase {

    private ProductsGrid productsGrid = PageFactory.initElements(driver, ProductsGrid.class);

    @When("^I add to cart the product \"([^\"]*)\"$")
    public void iAddToCartTheProduct(String productName) {
        productsGrid.getAddToCartButtonByProductName(productName).click();
    }

    @And("^I press the first View Details button$")
    public void iPressTheFirstViewDetailsButton() {
        productsGrid.getViewDetailsButton().click();
    }
}
