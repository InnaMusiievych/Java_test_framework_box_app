package package3.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

/**
 * Created by imusiievych on 3/26/15.
 */
public class ProfileAndBrandingPage extends PageObject {

    //input fields
    @FindBy(id="settings_public_name")
    private WebElementFacade userNameInput;

    @FindBy(id="settings_company_name")
    private WebElementFacade companyNameInput;

    @FindBy(id="settings_website")
    private WebElementFacade websiteInput;

    @FindBy(id="settings_job_title")
    private WebElementFacade titleInput;

    @FindBy(id="settings_phone")
    private WebElementFacade phoneInput;

    @FindBy(id="settings_address")
    private WebElementFacade addressInput;

    //buttons
    //TODO
    @FindBy(id="settings_user_profile_save_button")
    public WebElementFacade saveButton;

    public void fillProfileAndBrandingFields(String username, String company, String website, String jobTitle, String phone, String address) {
        userNameInput.sendKeys(username);
        companyNameInput.sendKeys(company);
        websiteInput.sendKeys(website);
        titleInput.sendKeys(jobTitle);
        phoneInput.sendKeys(phone);
        addressInput.sendKeys(address);
        saveButton.click();
    }

    public void emptiesUserProfileFields() {
        userNameInput.clear();
        companyNameInput.clear();
        websiteInput.clear();
        titleInput.clear();
        phoneInput.clear();
        addressInput.clear();
    }

    public void verifyEnteredInformationIsSaved(String username, String company, String website, String jobTitle, String phone, String address) {
        assertThat(userNameInput.getValue().equals(username), is(true));
        assertThat(companyNameInput.getValue().equals(company), is(true));
        assertThat(websiteInput.getValue().equals(website), is(true));
        assertThat(titleInput.getValue().equals(jobTitle), is(true));
        assertThat(phoneInput.getValue().equals(phone), is(true));
        assertThat(addressInput.getValue().equals(address), is(true));
    }

}
