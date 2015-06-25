package package3;

import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import package3.pages.LoginPage;
import package3.requirements.Application;
import package3.steps.EndUserSteps;
import package3.steps.VerifySteps;

import java.util.UUID;

@Story(Application.Search.SearchByKeyword.class)
@RunWith(ThucydidesRunner.class)
public class LoginTests {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://app.box.com/")
    public Pages pages;

    @Before
    public void testInitialization() {
     //   webdriver.manage().window().maximize();

    }

    @Steps
    public EndUserSteps endUser;

    @Steps
    public VerifySteps verify;

    @Test
    public void a1_click_on_single_sign_on_button() {
        endUser.is_login_page();
        endUser.clickOnSingleSignOnButton();
        endUser.verifySingleSignOnPage();
    }

    @Test
    public void a2_click_on_reset_password_button() {
        endUser.is_login_page();
        endUser.clickOnResetPasswordButton();
        verify.verifyResetPasswordPage();
    }

    @Test
    public void a3_click_on_create_account_button() {
        endUser.is_login_page();
        endUser.clickOnCreateAccountButton();
        verify.verifyCreateAccountPage();
    }

    @Test
    public void logs_in_to_the_page_with_correct_creds() {
        endUser.is_login_page();
        endUser.logs_in("i.musiievych@gmail.com", "dolly1010vlad");
        verify.verifyUserNameOnHomePage();
    }

    @Test
    public void a4_logs_in_to_page_with_incorrect_creds() {
        endUser.is_login_page();
        String random_string = UUID.randomUUID().toString();
        endUser.logs_in("i.musiievych@gmail.com", random_string);
        verify.verifyErrorMessageForInvalidCreds(true);
        endUser.logs_in(random_string, "dolly1010vlad");
        verify.verifyErrorMessageForInvalidCreds(true);
        endUser.logs_in("", "");
        verify.verifyErrorMessageForInvalidCreds(false);
    }

}

