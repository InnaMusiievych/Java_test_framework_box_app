package package3.pages;

import net.thucydides.core.annotations.findby.FindBy;
import net.thucydides.core.pages.WebElementFacade;

import java.util.List;

/**
 * Created by imusiievych on 3/4/15.
 */
public class BoxNotePage {

    @FindBy(id="popup-input-name")
    private WebElementFacade boxNoteNameInput;

    @FindBy(css="span[data-type='okay-btn']")
    private WebElementFacade boxNameSubmitButton;

    @FindBy(css="span[data-type='cancel-btn']")
    private WebElementFacade boxNameCancelButton;

    @FindBy(css="span[data-type='add-description-link']")
    private WebElementFacade addDescriptionLink;

    @FindBy(id="popup-textarea-description")
    private WebElementFacade descriptionInput;


    public void enters_box_name_and_submit(String box_name){
        boxNoteNameInput.type(box_name);
        boxNameSubmitButton.click();
    }

    public void enters_box_name_and_cancel(String box_name){
        boxNoteNameInput.type(box_name);
        boxNameCancelButton.click();
    }

    public void adds_description_and_submit(String description) {
        addDescriptionLink.click();
        descriptionInput.type(description);
        boxNameSubmitButton.click();
    }

    public void adds_description_and_cancel(String description){
        addDescriptionLink.click();
        descriptionInput.type(description);
        boxNameCancelButton.click();
    }

}
