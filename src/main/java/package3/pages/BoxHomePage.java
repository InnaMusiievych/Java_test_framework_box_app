package package3.pages;

import jnr.ffi.Struct;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.PageObject;
import net.thucydides.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;


@DefaultUrl("https://app.box.com/files")
public class BoxHomePage extends PageObject {

    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @FindBy(xpath = "//*[@id='empty_files']")
    public WebElementFacade mainEmptyFilesDiv;

    //main tab elements
    @FindBy(id="search")
    private WebElementFacade searchInputField;

    @FindBy(className="search_button")
    private WebElementFacade searchButton;

    @FindBy(id="new_box_note_button")
    public WebElementFacade newBoxNoteButton;

    @FindBy(id="popup-input-name")
    public WebElementFacade newBoxNoteName;

    @FindBy(xpath = "//*[@data-type='okay-btn']")
    public WebElementFacade newBoxNoteNameOKButton;

    @FindBy(css = ".ace-line")
    public WebElementFacade newBoxNoteBody;

    @FindBy(xpath = "//*[@data-type='item-row']")
    private List<WebElementFacade> notesList;

    @FindBy(css = ".action.select")
    private List<WebElementFacade> noteCheckboxList;

    @FindBy(css="#item_menu_new_folder a")
    private WebElementFacade addFolder;

    @FindBy(id="view_options_button")
    private WebElementFacade viewOptionsDropdown;

    //dropdown elements for User
    @FindBy(className="user_name")
    public WebElementFacade userName;

    @FindBy(partialLinkText="View Profile")
    private WebElementFacade viewProfileDDLink;

    @FindBy(linkText="Account Settings")
    private WebElementFacade accountSettingsDDLink;

    @FindBy(xpath = "//*[@id='current_user_tab']/menu")
    public WebElementFacade dropdownDiv;

    @FindBy(xpath = "//*[@id='multi_options_delete_me']/button")
    public WebElementFacade trashButton;

    @FindBy(xpath = "//*[@data-type='confirm-btn']")
    public WebElementFacade deleteButton;

    @FindBy(xpath = "//*[@data-type='confirm-btn']")
    public WebElementFacade cancelDeletionButton;

    //New Folder creation
    @FindBy(css="#box_popup")
    public WebElementFacade newFolderPopup;

    @FindBy(css="#new_folder_popup_name")
    public WebElementFacade newFolderPopupName;

    @FindBy(css="#popup_button_okay")
    public WebElementFacade newFolderPopupOkay;

    @FindBy(css="#assign_new_comment_button")
    public WebElementFacade newCommentInputFieldButton;

    @FindBy(css="#new_item_feed_object_textarea")
    public WebElementFacade newCommentInputField;


    WebElement tableItem(String filename){
        return getDriver().findElement(By.xpath("//a[.='"+ filename + "']/ancestor::li"));
    }

    WebElement commentsDiv(String filename){
        return getDriver().findElement(By.xpath("//a[.='"+ filename + "']/ancestor::li/following-sibling::div"));
    }

    public void enters_search_text(String searchQuery){
        searchInputField.type(searchQuery);
        searchButton.click();
    }

    public String userName()
    {
       return userName.getText();
    }

    public WebElementFacade find_note_by_index(Integer index) {
        List<WebElementFacade> notesList = findAll(By.xpath("//*[@data-type='item-row']"));
        WebElementFacade result = null;
        for (WebElementFacade note : notesList) {
            Integer ind = index - 1;
            if (ind.equals(notesList.indexOf(note))) {
                result = note;
                break;
            }
        }
        return result;
    }

    public void check_note_by_name(String name) {
        List<WebElementFacade> notesList = findAll(By.cssSelector(".file"));
        for (WebElementFacade note : notesList) {
            if (note.getText().contains(name)) {
                List<WebElementFacade> noteCheckboxList = findAll(By.cssSelector(".action.select"));
                int index = notesList.indexOf(note);
                noteCheckboxList.get(index);
                return;
            }
        }
    }

    public String fullnotename_from_partname(String name) {
        String n_name = null;
        List<WebElementFacade> notesList = findAll(By.cssSelector(".file"));
        for (WebElement note : notesList) {
            String notetext = note.getText();
            System.out.println("notetext name is :" + notetext);
            if (notetext.contains(name)) {
                n_name = note.findElement(By.xpath("//a[@data-field='name']")).getText();
                System.out.println("note name is :" + n_name);
            }
        }
        return n_name;
    }

    public void click_on_trash(){
        trashButton.click();
    }

    public void navigateToHomeDropdownPage(String dropdownItem) {
        userName.click();
        List<WebElementFacade> dropdownItems = dropdownDiv.thenFindAll(By.className("menu-option"));
        for (WebElementFacade item : dropdownItems)
        {
            if (item.getText().contains(dropdownItem)) {
                item.click();
                break;
            }
        }
    }

    public void switchToWindow() {
        String windowBefore = webdriver.getWindowHandle();
        for (String windowHandle : webdriver.getWindowHandles()) {
            if (!windowHandle.equals(windowBefore)) {
                webdriver.switchTo().window(windowHandle);
            }
        }
    }

    public void ensureDeletion(){
        deleteButton.click();
    }

    public void clickOnNewFolderAction(){
        Actions action = new Actions(getDriver());
        action.moveToElement(viewOptionsDropdown).contextClick().moveToElement(addFolder).click().build().perform();
    }

    public void enterNewFolderName(String folderName){
        newFolderPopupName.sendKeys(folderName);
        newFolderPopupOkay.click();
    }

/*    public void makeComment(String comment){
//        newCommentInputFieldButton.click();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("document.getElementById('new_item_feed_object_textarea').style.display='block';");
        newCommentInputField.sendKeys(comment);
    } */

    public void scrollToNote(String filename){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].scrollIntoView(true);",tableItem(filename));
    }

    public void makeVisible(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].setAttribute(\"style\", \"display:block\")", element);
    }

    public void clickOnCommentBtn(String filename){
        tableItem(filename).findElement(By.xpath("//a[@data-type='item-feed-btn']")).click();
    }

    WebElement unvisible_field() {
        return getDriver().findElement(By.className("author_name"));
   }

    public void clickOnCommentInput(String filename){
        Actions action = new Actions(getDriver());
        Actions action2 = new Actions(getDriver());
//        makeVisible(getDriver().findElement(By.className("author_name")));
        action.sendKeys(unvisible_field().findElement(By.id("new_comment_textarea")), "feferf").build().perform();
        action2.click(commentsDiv(filename).findElement(By.id("btn_submit"))).build().perform();



//        commentsDiv(filename).findElement(By.id("new_comment_textarea")).click();
        // findElement(By.id("new_item_feed_object_textarea"))
    }

    public String go_to_box_body(){
        WebDriver driver = getDriver();
        String current = driver.getWindowHandle();
        driver.getWindowHandles();
        for (String windowHandle: driver.getWindowHandles()){
            driver.switchTo().window(windowHandle);
        }

        return current;
    }

    public void type_into_note(){}

}
