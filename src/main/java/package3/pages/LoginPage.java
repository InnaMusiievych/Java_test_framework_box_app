package package3.pages;

import ch.lambdaj.function.convert.Converter;
import jnr.ffi.StructLayout;
import net.thucydides.core.annotations.DefaultUrl;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import net.thucydides.core.pages.WebElementFacade;

import net.thucydides.core.annotations.findby.FindBy;

import net.thucydides.core.pages.PageObject;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

@DefaultUrl("https://app.box.com/")
public class LoginPage extends PageObject {

    @FindBy(id="login")
    public WebElementFacade emailInput;

    @FindBy(id="password")
    public WebElementFacade passwordInput;

    @FindBy(id="continue")
    public WebElementFacade loginButton;

    @FindBy(linkText = "Single Sign On")
    public WebElementFacade singleSignOnButton;

    @FindBy(linkText = "Reset Password")
    public WebElementFacade resetPasswordButton;

    @FindBy(linkText = "Create Account")
    public WebElementFacade createAccountButton;

    @FindBy(css = ".center_container.single-width")
    public WebElementFacade singleSignOnDiv;

    @FindBy(className = "title_text")
    public WebElementFacade singleSignOn_resetPassword_createAccountTextDiv;

    @FindBy(css = ".notification_inner")
    public WebElementFacade errorMessageForInvalidCredsDiv;

    @FindBy(css = "#recaptcha_challenge_image")
    public WebElementFacade captcha;

    public void enters_email(String keyword) {
        emailInput.type(keyword);
    }

    public void enters_password(String keyword) {
        passwordInput.type(keyword);
    }

    public void click_login() {
        loginButton.click();
    }

}



