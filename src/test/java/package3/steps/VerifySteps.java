package package3.steps;

/**
 * Created by imusiievych on 4/7/15.
 */

import net.thucydides.core.steps.ScenarioSteps;

import net.thucydides.core.annotations.Step;
import org.jruby.RubyProcess;
import org.openqa.selenium.By;
import package3.pages.*;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VerifySteps extends ScenarioSteps {

    BoxHomePage boxHomePage;
    ProfileAndBrandingPage pAndBPage;
    BoxSettingsPage boxSettingsPage;
    LoginPage loginPage;
    Utils util;


    @Step
    public void verifyUserNameOnHomePage()
    {
        assertThat(boxHomePage.userName().equals("Inna Musiievych"), is(true));
    }

    @Step
    public void verify_new_note_is_displayed(Integer index){
        assertThat(boxHomePage.find_note_by_index(index).isDisplayed(), is(true));
    }

    @Step
    public void verifyInformationSaved(String username,String company, String website, String jobTitle, String phone, String address) {
        pAndBPage.verifyEnteredInformationIsSaved(username, company, website, jobTitle, phone, address);
    }

    @Step
    public void verifyErrorMessageForUsername_isDisplayed() {
        assertThat(boxSettingsPage.errorForUsernameEmptyField.getText().equals("Please enter a valid name."), is(true));
    }

    @Step
    public void verifyResetPasswordPage() {
        assertThat(loginPage.singleSignOn_resetPassword_createAccountTextDiv.getText().equals("Forgot your password?"), is(true));
    }

    @Step
    public void verifyCreateAccountPage() {
        assertThat(loginPage.singleSignOn_resetPassword_createAccountTextDiv.getText().equals("Set up your account"), is(true));
    }

    @Step
    public void verifyErrorMessageForInvalidCreds(Boolean displayed) {
        if (displayed){
            assertThat(loginPage.errorMessageForInvalidCredsDiv.getText().equals("Invalid login credentials"), is(true));
        }
        else {
            assertThat(loginPage.errorMessageForInvalidCredsDiv.isDisplayed(),is(false));
        }
    }

    @Step
    public void verify_no_results_for_search() {
        assertThat(boxHomePage.find(By.xpath("//*[@id='empty_files']//h2")).getText().equals("Sorry, there are no files or folders that match your search"), is(true));
    }

    @Step
    public void verify_new_folder_popup_is_displayed(){
        assertThat(boxHomePage.newFolderPopup.isDisplayed(), is(true));
    }

    @Step
    public void verify_new_folder_is_added(String f_name){
        assertThat(boxHomePage.find_note_by_index(1).getText().contains(f_name), is(true));
    }
}
