package com.qaprosoft.carina.demo.guiTest;

/*
 * Copyright 2013-2021 QAPROSOFT (http://qaprosoft.com/).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractUIObject;

public class ButtonExamination extends AbstractUIObject {
    private String username;
    private String password;

    @FindBy(xpath = "//div[@class='login-box']")
    private ExtendedWebElement login;

    @FindBy(xpath = "//div[@class='form_group']//input[@placeholder='Username']")
    private ExtendedWebElement gottenUsername;

    @FindBy(xpath = "//div[@class='form_group']//input[@placeholder='Password']")
    private ExtendedWebElement gottenPassword;

    @FindBy(xpath = "//input[@type=\"submit\"]")
    private ExtendedWebElement loginLink;

    @FindBy(xpath = "//div[@class='error-message-container error']//button")
    private ExtendedWebElement buttonError;

    @FindBy(xpath = "//div[@class='error-message-container error']")
    private ExtendedWebElement messageError;

    public ButtonExamination(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void loginLinkClick() {
        loginLink.click();
    }

    public Main goToMain(){
        loginLink.click(3);
        return new Main(driver);
    }

    public void toUsername(String s) {
        this.username = s;
        gottenUsername.click();
        gottenUsername.type(s);

    }

    public void toPassword(String s) {
        this.password = s;
        gottenPassword.click();
        gottenPassword.type(s);
    }

    public String readGottenUsername() {
        return username;
    }

    public String readGottenPassword() {
        return password;
    }

    public ExtendedWebElement getButtonError() {
        return buttonError;
    }

    public ExtendedWebElement getMessageError() {
        return messageError;
    }
}

