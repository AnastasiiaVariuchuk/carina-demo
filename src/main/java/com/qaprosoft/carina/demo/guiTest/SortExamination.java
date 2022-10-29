package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class SortExamination extends AbstractUIObject {
    @FindBy(xpath = "//select[@class='product_sort_container']")
    private ExtendedWebElement sort;

    @FindBy(xpath = "//span[@class='active_option']")
    private ExtendedWebElement activeOption;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"za\"]")
    private ExtendedWebElement za;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"lohi\"]")
    private ExtendedWebElement lh;

    @FindBy(xpath = "//select[@class='product_sort_container']//option[@value=\"hilo\"]")
    private ExtendedWebElement hl;

    public SortExamination(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void sortZtoA() {
        sort.click();
        za.click();
    }

    public void sortLowToHigh() {
        sort.click();
        lh.click();
    }

    public void sortHighToLow() {
        sort.click();
        hl.click();
    }

    public String readActiveOption() {
        return activeOption.getText();
    }
}
