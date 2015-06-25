package package3.steps;


import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.pages.WebElementFacade;
import net.thucydides.core.steps.ScenarioSteps;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import package3.HomePageTests;
import package3.pages.BoxHomePage;
import package3.pages.BoxSettingsPage;
import package3.pages.LoginPage;
import package3.pages.ProfileAndBrandingPage;
import package3.pages.Utils;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import package3.steps.VerifySteps;

public class EndUserSteps extends ScenarioSteps {

    LoginPage loginPage;
    BoxHomePage boxHomePage;
    BoxSettingsPage boxSettingsPage;
    ProfileAndBrandingPage pAndBPage;
    Utils utils;

    @Steps
    public VerifySteps verifysteps;

    @Step
    public void is_login_page ()
    {
        loginPage.open();
    }

    @Step
    public void logs_in(String email, String password){
        loginPage.emailInput.clear();
        loginPage.passwordInput.clear();
        loginPage.enters_email(email);
        loginPage.enters_password(password);
        loginPage.click_login();
    }

    @Step
    public void searches(String searchQuery)
    {
        boxHomePage.enters_search_text(searchQuery);
    }

    @Step
    public void goToProfileAndBranding() {
        boxHomePage.navigateToHomeDropdownPage("Account");
        boxSettingsPage.navigateToTabOnSettingsPage("Profile");
    }

    @Step
    public void fillProfileInformation(String username, String company, String website, String jobTitle, String phone, String address) {
        pAndBPage.fillProfileAndBrandingFields(username, company, website, jobTitle, phone, address);
        pAndBPage.saveButton.click();
    }

    @Step
    public void emptiesProfileInfo() {
        pAndBPage.emptiesUserProfileFields();
        pAndBPage.saveButton.click();
    }

    @Step
    public void clickOnSingleSignOnButton(){loginPage.singleSignOnButton.click();}

    @Step
    public void verifySingleSignOnPage(){
        assertThat(loginPage.singleSignOnDiv.isDisplayed(), is(true));
        assertThat(loginPage.singleSignOn_resetPassword_createAccountTextDiv.getText().equals("Single Sign On (SSO)"), is(true));
    }

    @Step
    public void clickOnResetPasswordButton(){loginPage.resetPasswordButton.click();}

    @Step
    public void clickOnCreateAccountButton(){loginPage.createAccountButton.click();}

    @Step
    public void add_new_note(String name, String text){
        boxHomePage.newBoxNoteButton.click();
        boxHomePage.newBoxNoteName.click();
        boxHomePage.newBoxNoteName.sendKeys(name);
        boxHomePage.newBoxNoteNameOKButton.click();

        boxHomePage.go_to_box_body();

        Actions action = new Actions(getDriver());
        action.moveToElement(boxHomePage.newBoxNoteBody).click().sendKeys("lalalalalal, this is box body and This is my first note!!!)))").build().perform();


//        boxHomePage.switchToWindow();






//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Step
    public void find_note_by_index(Integer index){
        boxHomePage.find_note_by_index(index);
    }

    @Step
    public void delete_note_by_index(Integer index){
        boxHomePage.check_note_by_name(boxHomePage.find_note_by_index(index).getText());
        boxHomePage.click_on_trash();
        boxHomePage.ensureDeletion();
    }

    @Step
    public void delete_note_by_name(String note_name){
        boxHomePage.check_note_by_name(note_name);
        boxHomePage.click_on_trash();
        boxHomePage.ensureDeletion();
    }

    @Step
    public void search_for_query( String query)
    {
        boxHomePage.enters_search_text(query);
    }

    @Step
    public void addNewFolder(String folderName){
        boxHomePage.clickOnNewFolderAction();
        verifysteps.verify_new_folder_popup_is_displayed();
        boxHomePage.enterNewFolderName(folderName);
    }

    @Step
    public void commentNotebyName(String notename){

 //       String notename = boxHomePage.fullnotename_from_partname(noteName);
  //      System.out.println("Notename from endusers to use in following methods IS: " + notename);

//        WebElement commentElement = getDriver().findElement(By.id("new_item_feed_object_textarea_cntr"));
//        JavascriptExecutor js = (JavascriptExecutor) getDriver();
//        js.executeScript("document.getElementById('new_item_feed_object_textarea').style.display='inline';");
//        boxHomePage.makeComment("This is inna's comment");

        boxHomePage.scrollToNote(notename);
        boxHomePage.clickOnCommentBtn(notename);
        boxHomePage.clickOnCommentInput(notename);

    }



}