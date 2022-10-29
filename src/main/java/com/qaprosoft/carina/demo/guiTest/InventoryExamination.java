package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class InventoryExamination extends AbstractUIObject {
    @FindBy(xpath = "//div[@class=\"inventory_item\"]//div[@class=\"inventory_item_name\"]")
    private List<ExtendedWebElement> productNames;

    @FindBy(xpath = "//div[@class=\"inventory_item\"]//button")
    private List<ExtendedWebElement> createShoppingBasketButtons;

    @FindBy(xpath = "//div[@class=\"pricebar\"]//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> productPrices;

    @FindBy(xpath = "//div[@class='inventory_item']//div[@class='inventory_item_label']//a")
    private List<ExtendedWebElement> ids;

    @FindBy(xpath = "//div[@id=\"shopping_cart_container\"]//span[@class=\"shopping_cart_badge\"]")
    private List<ExtendedWebElement> elementsInShoppingBasket;

    @FindBy(xpath = "//a[@class=\"shopping_cart_link\"]")
    private ExtendedWebElement shoppingBasketLink;

    public InventoryExamination(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public ShoppingBasket openShoppingBasketPage() {
        shoppingBasketLink.click();
        return new ShoppingBasket(driver);
    }

    public List<String> readProductNames() {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < productNames.size(); i++) {
            result.add(productNames.get(i).getText());
        }
        return result;
    }


    public List<String> readProductPrices() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < productPrices.size(); i++) {
            result.add(productPrices.get(i).getText());
        }
        return result;
    }

    public List<String> readIds() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            result.add(ids.get(i).getAttribute("id"));
        }
        return result;
    }

    public List<Integer> getIntFromIds() {
        List<String> arr = readIds();
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++) {
            String[] p = arr.get(i).split("_");
            result.add(Integer.parseInt(p[1]));
        }
        return result;
    }

    public String readElementsInShoppingBasket() {
        return elementsInShoppingBasket.get(0).getText();
    }

    public int createShoppingButtonClick(String name) {
        if (readProductNames().indexOf(name) < 0) {
            throw new RuntimeException();
        } else {
            assertElementPresent(createShoppingBasketButtons.get(readProductNames().indexOf(name)));
            createShoppingBasketButtons.get(readProductNames().indexOf(name)).click();
            return readProductNames().indexOf(name);
        }
    }

    public Product clickOnProduct(String name, int x) {
        if (readProductNames().indexOf(name) < 0) {
            throw new RuntimeException();
        } else {
            assertElementPresent(productNames.get(readProductNames().indexOf(name)));
            productNames.get(readProductNames().indexOf(name)).click(3);
            return new Product(driver, x);
        }
    }

    public List<ExtendedWebElement> getProductNames() {
        return productNames;
    }

    public List<ExtendedWebElement> getCreateShoppingBasketButtons() {
        return createShoppingBasketButtons;
    }

    public List<ExtendedWebElement> getElementsInShoppingBasket() {
        return elementsInShoppingBasket;
    }
}
