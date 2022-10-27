package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class Main extends AbstractPage {
    private final String mainPageUrl = "https://www.saucedemo.com/inventory.html";

    @FindBy(id = "inventory_container")
    private InventoryExamination inventoryContainer;

    @FindBy(className = "bm-menu")
    private MenuExamination menuExamination;

    @FindBy(xpath = "//span[@class='select_container']")
    private SortExamination sortExamination;

    public Main(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(mainPageUrl);
    }

    public InventoryExamination getInventoryContainer() {
        return inventoryContainer;
    }
    public MenuExamination getMenuExamination() {
        return menuExamination;
    }
    public SortExamination getSortExamination() {
        return sortExamination;
    }
    public String getMainPageUrl() {
        return mainPageUrl;
    }
}
