package com.qaprosoft.carina.demo.guiTest;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.utils.R;
import com.qaprosoft.carina.core.foundation.webdriver.decorator.ExtendedWebElement;
import com.qaprosoft.carina.core.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Login extends AbstractPage {
    private final String loginPageUrl = "https://www.saucedemo.com/";

    @FindBy(id = "login_button_container")
    private ButtonExamination button;

    @FindBy(xpath = "//div[@id=\"login_credentials\"]")
    private List<ExtendedWebElement> usernames;

    @FindBy(xpath = "//div[@class=\"login_password\"]")
    private ExtendedWebElement password;

    public Login(WebDriver driver) {
        super(driver);
        setPageAbsoluteURL(R.CONFIG.get(Configuration.Parameter.URL.getKey()));
    }

    public ButtonExamination getButtonContainer() {
        return button;
    }

    public String readPassword() {
        String[] arr = password.getText().split("\n");
        return arr[1];
    }

    public List<String> readUsernames() {
        List<String> arr = Arrays.asList(usernames.get(0).getText().split("\n")).stream()
                .filter(x -> !x.contains(":")).collect(Collectors.toList());
        return arr;
    }

    public String getLoginPageUrl() {
        return loginPageUrl;
    }
}
