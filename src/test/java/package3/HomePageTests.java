package package3;

import net.thucydides.core.annotations.Managed;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Story;
import net.thucydides.core.pages.Pages;
import net.thucydides.junit.runners.ThucydidesRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import package3.requirements.Application;
import package3.steps.EndUserSteps;
import package3.steps.VerifySteps;

import java.util.UUID;

/**
 * Created by root on 4/1/15.
 */

@Story(Application.Search.SearchByKeyword.class)
@RunWith(ThucydidesRunner.class)
public class HomePageTests
{
    @Managed(uniqueSession = true)
    public WebDriver webdriver;

    @ManagedPages(defaultUrl = "https://app.box.com/")
    public Pages pages;

    @Before
    public void testInitialization()
    {
    //    webdriver.manage().window().maximize();
        endUser.is_login_page();
        endUser.logs_in("i.musiievych@gmail.com", "dolly1010vlad");
    }

    @Steps
    public EndUserSteps endUser;
    @Steps
    public VerifySteps verify;


    @Test
    public void create_new_note(){
        String note_name = UUID.randomUUID().toString();
        endUser.add_new_note(note_name, "Body for fourth note is here");
//        verify.verify_new_note_is_displayed(1);
//        endUser.delete_note_by_name(note_name);
}

    @Test
    public void delete_notes(){
        endUser.delete_note_by_index(6);
    }

    @Test
    public void delete_note(){
        endUser.delete_note_by_name("vffd");
    }

    @Test
    public void search_for_nonexisting_file() {
        String query = UUID.randomUUID().toString();
        endUser.search_for_query(query);
        verify.verify_no_results_for_search();
    }

    @Test
    public void validateUserCanAddNewFolder(){
        String foldername = "Inna" + UUID.randomUUID().toString();
        endUser.addNewFolder(foldername);

        verify.verify_new_folder_is_added(foldername);
    }

    @Test
    public void CommentNote(){
        endUser.commentNotebyName("2.jpg");
    }




}
