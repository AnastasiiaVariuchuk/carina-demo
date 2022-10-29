package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ShoppingBasketExamination extends AbstractUIObject {

    @FindBy(xpath = "//div[@class=\"cart_item\"]")
    private List<ExtendedWebElement> shoppingBasketProducts;

    @FindBy(xpath = "//div[@class=\"inventory_item_name\"]")
    private List<ExtendedWebElement> productsNames;

    @FindBy(xpath = "//div[@class=\"inventory_item_price\"]")
    private List<ExtendedWebElement> productsPrices;

    public ShoppingBasketExamination(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public List<String> readProductsNames() {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < productsNames.size(); i++) {
            result.add(productsNames.get(i).getText());
        }
        return result;
    }

    public List<String> readProductsPrices() {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < productsPrices.size(); i++) {
            result.add(productsPrices.get(i).getText());
        }
        return result;
    }

}
