package com.qaprosoft.carina.demo;

import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.demo.guiTest.*;
import com.zebrunner.agent.core.annotation.TestLabel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.*;

public class WebTest implements IAbstractTest {
    private static final String CORRECT_USER = "standard_user";
    private static final String INCORRECT_USER = "problem_user";

    private static final String PASSWORD = "secret_sauce";

    {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\carina-demo\\src\\main\\resources\\chromedriver.exe");
    }

    public WebDriver webDriver = new ChromeDriver();

    @BeforeTest
    public WebDriver getDriver() {
        return webDriver;
    }

    public Login openLogin() {
        Login login = new Login(getDriver()); login.open();
        Assert.assertTrue(login.isPageOpened(), "PAGE ISN`T OPENED");
        return login;
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testUsername() {
        Login login = openLogin();

        //===================================================================//
        ButtonExamination buttonExamination = login.getButtonContainer();
        Assert.assertTrue(buttonExamination.isUIObjectPresent(2), "BUTTON ISN`T FOUNDED");

        //===================================================================//
        buttonExamination.toUsername(""); buttonExamination.toPassword(""); buttonExamination.loginLinkClick();
        ExtendedWebElement buttonError = buttonExamination.getButtonError();
        Assert.assertTrue(buttonError.isElementPresent(2), "ERROR BUTTON");

        //===================================================================//
        ExtendedWebElement messageError = buttonExamination.getMessageError();
        Assert.assertTrue(messageError.isElementPresent(2), "ERROR MASSAGE");
        Assert.assertEquals(messageError.getText(), "Epic sadface: Username is required");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testPassword() {
        Login login = openLogin();

        //===================================================================//
        ButtonExamination buttonExamination = login.getButtonContainer();
        Assert.assertTrue(buttonExamination.isUIObjectPresent(2), "BUTTON ISN`T FOUNDED");
        buttonExamination.toUsername(INCORRECT_USER);
        Assert.assertTrue(login.readUsernames().contains(buttonExamination.readGottenUsername()), "INCORRECT USERNAME");

        //===================================================================//
        buttonExamination.toPassword(""); buttonExamination.loginLinkClick();
        ExtendedWebElement buttonError = buttonExamination.getButtonError();
        Assert.assertTrue(buttonError.isElementPresent(2), "ERROR BUTTON");

        //===================================================================//
        ExtendedWebElement errorMessage = buttonExamination.getMessageError();
        Assert.assertTrue(errorMessage.isElementPresent(2), "ERROR MASSAGE");
        Assert.assertEquals(errorMessage.getText(), "Epic sadface: Password is required");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testLoginOpenReturnUnlogin() {
        Login login = openLogin();

        ButtonExamination buttonContainer = login.getButtonContainer();
        Assert.assertTrue(buttonContainer.isUIObjectPresent(2), "Button container wasn't found!");
        buttonContainer.toUsername(CORRECT_USER);
        Assert.assertTrue(login.readUsernames().contains(buttonContainer.readGottenUsername()), "Wrong username was chosen");
        buttonContainer.toPassword(PASSWORD);
        Assert.assertEquals(login.readPassword(), buttonContainer.readGottenPassword(), "Wrong password was chosen");

        Main mainPage = buttonContainer.goToMain();
        Assert.assertTrue(mainPage.isPageOpened(), "Main page is not opened");

        InventoryExamination inventoryContainer = mainPage.getInventoryContainer();
        Assert.assertTrue(inventoryContainer.isUIObjectPresent(2), "Inventory container wasn't found!");

        String fname = "Sauce Labs Fleece Jacket";
        Assert.assertTrue(inventoryContainer.readItemNames().contains(fname), "Wrong name");

        List<String> mainPageItemsNames = inventoryContainer.readItemNames();
        List<String> mainPageItemsPrices = inventoryContainer.readItemsPrices();

        int idxForCartItemMainPage = inventoryContainer.readItemNames().indexOf(fname);
        int perexidIndex = inventoryContainer.readIntegerFromIds().get(idxForCartItemMainPage);

        Product itemPage = inventoryContainer.clickOnProduct(fname, perexidIndex);
        Assert.assertTrue(itemPage.isPageOpened(), "Item page is not opened");

        InventoryProductExamination inventoryItemContainer = itemPage.getContainer();
        Assert.assertTrue(inventoryItemContainer.isUIObjectPresent(2), "Item container wasn't found!");

        String itemName = inventoryItemContainer.readItemName();
        String itemPrice = inventoryItemContainer.readItemPrice();

        Assert.assertEquals(itemName, fname, "Something wrong with name");
        Assert.assertEquals(itemPrice, mainPageItemsPrices.get(idxForCartItemMainPage), "Something wrong with price");

        mainPage = inventoryItemContainer.clickToBack(mainPage);
        Assert.assertFalse(itemPage.isPageOpened(2), "Your item page is still opened");
        Assert.assertTrue(mainPage.isPageOpened(2), "Main page is not opened!");

        MenuExamination menuContainer = mainPage.getMenuExamination();
        Assert.assertTrue(menuContainer.isUIObjectPresent(2), "Menu container is not present");
        login = menuContainer.clickToLogout(login);
        Assert.assertFalse(mainPage.isPageOpened(2), "Main page is still opened");
        Assert.assertTrue(login.isPageOpened(2), "Login page is not opened");

    }
}
