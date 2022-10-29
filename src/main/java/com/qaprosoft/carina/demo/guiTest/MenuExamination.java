package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public class MenuExamination extends AbstractUIObject {
    @FindBy(xpath = "//div[@class='bm-burger-button']")
    private ExtendedWebElement burgerButton;

    @FindBy(xpath = "//a[@id='logout_sidebar_link']")
    private ExtendedWebElement logoutLink;

    public MenuExamination(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public Login toLogout(Login login) {
        burgerButton.click(2);
        logoutLink.click(10);
        setPageAbsoluteURL(login.getLoginPageUrl());
        return login;
    }
}
