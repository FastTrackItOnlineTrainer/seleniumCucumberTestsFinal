package org.fasttrackit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class TestBase {

    protected static String storedProductName;

    protected WebDriver driver = DriverFactory.getDriver();

    protected void mouseOverAndClickLast(List<By> locators) {
        Actions actions = new Actions(driver);

        for (By locator : locators) {
            actions.moveToElement(driver.findElement(locator))
                    .perform();
        }

        actions.click().perform();
    }

    // useful when running tests in Firefox
    protected void waitForPageToLoad(long timeout) {
        long waited = 0;
        long pauseMillis = 500;
        do {
            try {
                Thread.sleep(pauseMillis);
            } catch (InterruptedException e) {
                System.out.println("Wait process interrupted.");
            }
            waited += pauseMillis;
        }
        while (waited <= timeout && !((JavascriptExecutor) driver)
                .executeScript("return document.readyState")
                .equals("complete"));
    }

}
