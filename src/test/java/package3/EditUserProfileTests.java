package package3;

import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import package3.requirements.Application;
import package3.steps.EndUserSteps;
import package3.steps.VerifySteps;

@Story(Application.Search.SearchByKeyword.class)
@RunWith(ThucydidesRunner.class)
public class EditUserProfileTests {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://app.box.com/")
    public Pages pages;

    @Steps
    public EndUserSteps endUser;
    @Steps
    public VerifySteps verify;

    @Before
    public void testInitialization() {
    //    webdriver.manage().window().maximize();
        endUser.is_login_page();
        endUser.logs_in("i.musiievych@gmail.com", "dolly1010vlad");
    }

    @After
    public void testCleanup()
    { }

    @Test
    public void fill_and_save_profile_info_test() {
        endUser.goToProfileAndBranding();
        endUser.emptiesProfileInfo();
        endUser.fillProfileInformation("Inna Musiievych", "cogniance", "www.cogniance.com", "SET", "0933501371", "str.Somestreet, 23");
        verify.verifyInformationSaved("Inna Musiievych", "cogniance","www.cogniance.com", "SET", "0933501371", "str.Somestreet, 23");
    }

    @Test
    public void save_empty_fields_test() {
        endUser.goToProfileAndBranding();
        endUser.emptiesProfileInfo();
        verify.verifyErrorMessageForUsername_isDisplayed();
    }
}
