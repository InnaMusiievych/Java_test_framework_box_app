package package3.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by imusiievych on 3/26/15.
 */



public class BoxSettingsPage extends PageObject
{
    @FindBy(className="user_info_menu")
    private WebElementFacade accountTab;

    @FindBy(id="user_profile_branding_menu")
    private WebElementFacade profileAndBrandingTab;

    @FindBy(id="security_settings_menu")
    private WebElementFacade securityTab;

    @FindBy(id="collaboration_preferences_menu")
    private WebElementFacade contentAndSharingTab;

    @FindBy(id="notification_preferences_menu")
    private WebElementFacade notificationsTab;

    @FindBy(id="account_preferences_menu")
    private WebElementFacade generalTab;

    @FindBy(id="openbox_settings_menu")
    private WebElementFacade appsTab;

    @FindBy(id="mobile_sync_menu")
    private WebElementFacade mobileAndSyncTab;

    @FindBy(className="triangle_tabs")
    public WebElementFacade tabsDiv;

    @FindBy(css = ".notification_inner")
    public WebElementFacade errorForUsernameEmptyField;

    public void goToProfileAndBranding() {
//        WebElementFacade profileAndBrandTab = (new WebDriverWait(webdriver, 10)).until(ExpectedConditions.presenceOfElementLocated(By.id("user_profile_branding_name")));
         profileAndBrandingTab.click();
    }

    public void navigateToTabOnSettingsPage(String tabName) {
        List<WebElementFacade> tabs = tabsDiv.thenFindAll(By.tagName("a"));
        for (WebElementFacade item : tabs)
        {
            if (item.getText().contains(tabName)) {
                item.click();
                break;
            }
        }
    }
}
