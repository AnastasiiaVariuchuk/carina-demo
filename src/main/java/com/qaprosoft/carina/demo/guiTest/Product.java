package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Product extends AbstractPage {
    private String itemPageUrl;
    private Main mainPage;

    @FindBy(id = "inventory_item_container")
    private InventoryProductExamination container;

    public Product(WebDriver driver, int x) {
        super(driver);
        itemPageUrl = String.format("https://www.saucedemo.com/inventory-item.html?id=%d", x);
        setPageAbsoluteURL(itemPageUrl);
    }

    public InventoryProductExamination getContainer() {
        return container;
    }
}
