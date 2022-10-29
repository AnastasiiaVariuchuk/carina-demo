package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class InventoryProductExamination extends AbstractUIObject {
    @FindBy(xpath = "//div[@class=\"inventory_details_name large_size\"]")
    private ExtendedWebElement productName;

    @FindBy(xpath = "//div[@class=\"inventory_details_price\"]")
    private ExtendedWebElement productPrice;

    @FindBy(xpath = "//button[@id='back-to-products']")
    private ExtendedWebElement backToProductsButton;

    public InventoryProductExamination(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public Main clickToBack(Main mainPage) {
        backToProductsButton.click(3000);
        setPageAbsoluteURL(mainPage.getMainUrl());
        return mainPage;
    }

    public String readProductName() {
        return productName.getText();
    }

    public String readProductPrice() {
        return productPrice.getText();
    }

    public String backToProductsButton() {
        return backToProductsButton.getText();
    }

}
