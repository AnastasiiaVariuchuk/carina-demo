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
        Assert.assertTrue(login.isPageOpened(), "Login page is not opened");
        return login;
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testUsername() {
        Login login = openLogin();

        //===================================================================//
        ButtonExamination buttonExamination = login.getButton();
        Assert.assertTrue(buttonExamination.isUIObjectPresent(10), "Button container wasn't found!");

        //===================================================================//
        buttonExamination.toUsername(""); buttonExamination.toPassword(""); buttonExamination.loginLinkClick();
        ExtendedWebElement buttonError = buttonExamination.getButtonError();
        Assert.assertTrue(buttonError.isElementPresent(10), "Something wrong with error button");

        //===================================================================//
        ExtendedWebElement messageError = buttonExamination.getMessageError();
        Assert.assertTrue(messageError.isElementPresent(10), "Something wrong with msg");
        Assert.assertEquals(messageError.getText(), "Epic sadface: Username is required");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "regression"})
    public void testPassword() {
        Login login = openLogin();

        //===================================================================//
        ButtonExamination buttonExamination = login.getButton();
        Assert.assertTrue(buttonExamination.isUIObjectPresent(10), "Button container wasn't found!");
        buttonExamination.toUsername(INCORRECT_USER);
        Assert.assertTrue(login.readUsernames().contains(buttonExamination.readGottenUsername()), "INCORRECT USERNAME");

        //===================================================================//
        buttonExamination.toPassword(""); buttonExamination.loginLinkClick();
        ExtendedWebElement buttonError = buttonExamination.getButtonError();
        Assert.assertTrue(buttonError.isElementPresent(10), "Something wrong with error button");

        //===================================================================//
        ExtendedWebElement errorMessage = buttonExamination.getMessageError();
        Assert.assertTrue(errorMessage.isElementPresent(10), "Something wrong with msg");
        Assert.assertEquals(errorMessage.getText(), "Epic sadface: Password is required");
    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testShoppingBasket() {
        Login login = openLogin();

        //===================================================================//
        ButtonExamination buttonContainer = login.getButton();
        Assert.assertTrue(buttonContainer.isUIObjectPresent(10), "Button container wasn't found!");
        buttonContainer.toUsername(CORRECT_USER);
        Assert.assertTrue(login.readUsernames().contains(buttonContainer.readGottenUsername()), "Wrong username was chosen");
        buttonContainer.toPassword(PASSWORD);
        Assert.assertEquals(login.readPassword(), buttonContainer.readGottenPassword(), "Wrong password was chosen");

        //===================================================================//
        Main main = buttonContainer.goToMain();
        Assert.assertTrue(main.isPageOpened(), "Main page is not opened");
        InventoryExamination inventoryExamination = main.getInventoryExamination();
        Assert.assertTrue(inventoryExamination.isUIObjectPresent(10), "Inventory container wasn't found!");

        //===================================================================//
        String product = "Sauce Labs Backpack";
        Assert.assertTrue(inventoryExamination.readProductNames().contains(product), "Wrong name");
        List<ExtendedWebElement> buttons = inventoryExamination.getCreateShoppingBasketButtons();

        //===================================================================//
        Assert.assertEquals(buttons.size(), 6, "Wrong size");
        List <ExtendedWebElement> list = inventoryExamination.getElementsInShoppingBasket();
        Assert.assertEquals(list.size(), 0, "Something wrong with cart");

        //===================================================================//
        inventoryExamination.createShoppingButtonClick(product);
        Assert.assertEquals(inventoryExamination.readElementsInShoppingBasket(), "1", "Something wrong with cart");
        List<String> mainPageProductsNames = inventoryExamination.readProductNames();
        List<String> mainPageProductsPrices = inventoryExamination.readProductPrices();
        ShoppingBasket shoppingBasketPage = inventoryExamination.openShoppingBasketPage();
        Assert.assertTrue(shoppingBasketPage.isPageOpened(), "Cart page is not opened!");
        ShoppingBasketExamination shoppingBasket =  shoppingBasketPage.getContainer();
        Assert.assertTrue(shoppingBasket.isUIObjectPresent(10), "Cart container was not found!");

        //===================================================================//
        List<String> shoppingBasketProductsPrices = shoppingBasket.readProductsPrices();
        List<String> shoppingBasketProductsNames = shoppingBasket.readProductsNames();
        Assert.assertEquals(shoppingBasketProductsNames.size(), 1, "Something went wrong with names");
        Assert.assertEquals(shoppingBasketProductsPrices.size(), 1, "Something went wrong with prices");
        Assert.assertTrue(shoppingBasketProductsNames.contains(product), "Something wrong with name");
        Assert.assertTrue(mainPageProductsNames.contains(product), "Something wrong with name");
        Assert.assertEquals(mainPageProductsPrices.get(mainPageProductsNames.indexOf(product)),
                shoppingBasketProductsPrices.get(shoppingBasketProductsNames.indexOf(product)),
                "Prices are not equals");

    }

    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testOpenProduct() { //Login Open Return Login out
        Login login = openLogin();

        //===================================================================//
        ButtonExamination button = login.getButton();
        Assert.assertTrue(button.isUIObjectPresent(2), "Button container wasn't found!");
        button.toUsername(CORRECT_USER);
        Assert.assertTrue(login.readUsernames().contains(button.readGottenUsername()), "Wrong username was chosen");
        button.toPassword(PASSWORD);
        Assert.assertEquals(login.readPassword(), button.readGottenPassword(), "Wrong password was chosen");

        //===================================================================//
        Main main = button.goToMain();
        Assert.assertTrue(main.isPageOpened(), "Main page is not opened");
        InventoryExamination inventoryExamination = main.getInventoryExamination();
        Assert.assertTrue(inventoryExamination.isUIObjectPresent(2), "Inventory container wasn't found!");

        //===================================================================//
        String product = "Test.allTheThings() T-Shirt (Red)";
        Assert.assertTrue(inventoryExamination.readProductNames().contains(product), "Wrong name");
        Product productPage = inventoryExamination.clickOnProduct(product,
                inventoryExamination.getIntFromIds().get(inventoryExamination.readProductNames().indexOf(product)));
        Assert.assertTrue(productPage.isPageOpened(), "Item page is not opened");

        //===================================================================//
        InventoryProductExamination inventoryProductExamination = productPage.getContainer();
        Assert.assertTrue(inventoryProductExamination .isUIObjectPresent(2), "Item container wasn't found!");
        String productName = inventoryProductExamination .readProductName();
        String productPrice = inventoryProductExamination .readProductPrice();
        Assert.assertEquals(productName, product, "Something wrong with name");
        Assert.assertEquals(productPrice, inventoryExamination.readProductPrices().get(inventoryExamination.readProductNames().indexOf(product)), "Something wrong with price");
        main = inventoryProductExamination.clickToBack(main);
        Assert.assertFalse(productPage.isPageOpened(5), "Your item page is still opened");
        Assert.assertTrue(main.isPageOpened(5), "Main page is not opened!");

        //===================================================================//
        Assert.assertTrue(main.getMenuExamination().isUIObjectPresent(5), "Menu container is not present");
        login = main.getMenuExamination().toLogout(login);
        Assert.assertFalse(main.isPageOpened(5), "Main page is still opened");
        Assert.assertTrue(login.isPageOpened(5), "Login page is not opened");
    }


    @Test()
    @MethodOwner(owner = "AnastasiiaV")
    @TestLabel(name = "feature", value = {"web", "acceptance"})
    public void testSort() {
        Login login = openLogin();

        //===================================================================//
        ButtonExamination button = login.getButton();
        Assert.assertTrue(button.isUIObjectPresent(2), "Button container wasn't found!");
        button.toUsername(CORRECT_USER);
        Assert.assertTrue(login.readUsernames().contains(button.readGottenUsername()), "Wrong username was chosen");
        button.toPassword(PASSWORD);
        Assert.assertEquals(login.readPassword(), button.readGottenPassword(), "Wrong password was chosen");
        Main main = button.goToMain();
        Assert.assertTrue(main.isPageOpened(), "Main page is not opened");
        InventoryExamination inventoryExamination = main.getInventoryExamination();
        Assert.assertTrue(inventoryExamination.isUIObjectPresent(2), "Inventory container wasn't found!");

        //===================================================================//
        SortExamination sortExamination = main.getSortExamination();
        Assert.assertTrue(sortExamination.isUIObjectPresent(5), "Sort container wasn't found!");
        Assert.assertEquals(sortExamination.readActiveOption(), "name (a to z)".toUpperCase(Locale.ROOT), sortExamination.readActiveOption());
        List<String> sortedProducts = new ArrayList<>(inventoryExamination.readProductNames());
        Collections.sort(sortedProducts);
        Assert.assertEquals(sortedProducts, inventoryExamination.readProductNames(), "Wrong word order");
        sortExamination.sortZtoA();
        sortedProducts = new ArrayList<>(inventoryExamination.readProductNames());
        Collections.sort(sortedProducts); Collections.reverse(sortedProducts);
        Assert.assertEquals(sortedProducts, inventoryExamination.readProductNames(), "Wrong word order");

        //===================================================================//
        sortExamination.sortLowToHigh();
        List<Double> doubleList = new ArrayList<>();
        for (String s : inventoryExamination.readProductPrices()) {
            doubleList.add(Double.parseDouble(s.substring(1)));
        }
        List<Double> sortedDouble = new ArrayList<>(doubleList);
        Collections.sort(sortedDouble);
        Assert.assertEquals(sortedDouble, doubleList, "Wrong price order");

        //===================================================================//
        sortExamination.sortHighToLow();
        doubleList = new ArrayList<>();
        for (String s : inventoryExamination.readProductPrices()) {
            doubleList.add(Double.parseDouble(s.substring(1)));
        }
        sortedDouble = new ArrayList<>(doubleList);
        Collections.sort(sortedDouble); Collections.reverse(sortedDouble);
        Assert.assertEquals(sortedDouble, doubleList, "Wrong price order");
    }

}
