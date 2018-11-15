package org.fasttrackit.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.fasttrackit.TestBase;
import org.fasttrackit.pageobjects.CartPage;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class CartSteps extends TestBase {

    private CartPage cartPage = PageFactory.initElements(driver, CartPage.class);

    private HeaderSteps headerSteps = new HeaderSteps();
    private ProductsGridSteps productsGridSteps = new ProductsGridSteps();
    private CommonSteps commonSteps = new CommonSteps();

    private double initialSubtotal;

    @Then("^the product \"([^\"]*)\" is successfully added to the cart$")
    public void theProductIsSuccessfullyAddedToTheCart(String productName) {
        commonSteps.iReceiveTheFollowingConfirmationMessage(productName + " was added to your shopping cart.");

        CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
        assertThat("No product is present in cart.", cartPage.getProductNameContainer(), notNullValue());
        assertThat("Added product is not present in cart.",
                cartPage.getProductNameContainer().getText(), is(productName.toUpperCase()));
    }

    @Then("^the product is added to the cart$")
    public void theProductIsAddedToTheCart() {
        theProductIsSuccessfullyAddedToTheCart(storedProductName);
    }

    @And("^the color of the product in cart is \"([^\"]*)\"")
    public void theColorOfTheProductInCartIs(String color) {
        assertThat("Unexpected product color in cart.", cartPage.getProductColorContainer().getText(),
                containsString(color));
    }

    @And("^the size of the product in cart is \"([^\"]*)\"")
    public void theSizeOfTheProductInCartIs(String size) {
        assertThat("Unexpected product size in cart.", cartPage.getProductSizeContainer().getText().trim(),
                is(size));
    }

    @And("^I add a product to cart$")
    public void iAddAProductToCart() {
        headerSteps.iSearchAProductBy("vase");
        String productName = "Herald Glass Vase";
        productsGridSteps.iAddToCartTheProduct(productName);
        theProductIsSuccessfullyAddedToTheCart(productName);
    }

    @And("^I store the product subtotal displayed in cart$")
    public void iStoreTheProductSubtotalDisplayedInCart() {
        initialSubtotal = parseProductSubtotalValue();
    }

    private double parseProductSubtotalValue() {
        String subtotalText = cartPage.getProductSubtotalContainer().getText();
        String subtotalWithoutCurrency = subtotalText.split(" ")[0];
        return Double.parseDouble(subtotalWithoutCurrency.replace(",", "."));
    }

    @And("^I set the value of the quantity field to (\\d+)$")
    public void iSetTheValueOfTheQuantityFieldTo(int quantity) {
        cartPage.getQuantityField().clear();
        cartPage.getQuantityField().sendKeys(String.valueOf(quantity));
    }

    @When("^I press the Update quantity button in cart$")
    public void iPressTheUpdateQuantityButtonInCart() {
        cartPage.getUpdateQuantityButton().click();
    }

    @Then("^the initial product subtotal in cart is multiplied by (\\d+)$")
    public void theInitialProductSubtotalInCartIsMultipliedBy(int multiplier) {
        double updatedSubtotal = parseProductSubtotalValue();
        assertThat("Subtotal not updated.", updatedSubtotal, equalTo(initialSubtotal * multiplier));
    }

    @When("^I press the Remove product button from the cart page$")
    public void iPressTheRemoveProductButtonFromTheCartPage() {
        cartPage.getRemoveProductButton().click();
    }

    @When("^I press the Empty Cart button from the cart page$")
    public void iPressTheEmptyCartButtonFromTheCartPage() {
        cartPage.getEmptyCartButton().click();
    }
}
