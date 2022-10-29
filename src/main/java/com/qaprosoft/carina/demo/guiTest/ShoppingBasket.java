package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class ShoppingBasket extends AbstractPage {
    private final String cartPageUrl = "https://www.saucedemo.com/cart.html";
    @FindBy(xpath = "//div[@id=\"cart_contents_container\"]")
    private ShoppingBasketExamination container;

    public ShoppingBasket(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(cartPageUrl);
    }

    public ShoppingBasketExamination getContainer() {
        return container;
    }
}
