package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;

public class DeleteContactTests extends AppiumConfig {
    @BeforeClass
    public void beforeClass() {//to login before creating a new contact
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("krasleo@gmail.com")
                        .password("Cristiano7777$!")
                        .build());
    }

    @AfterClass
    public void afterClass(){
        //need to open contact page before logout
        new ContactListPage(driver).logout();
    }

    @Test
    public void deleteOneContactPositive() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        Assert.assertFalse(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("lN"+i)
                        .email("test" + i + "@gmail.af")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .moveContactByPhoneNumberToTheRight("1234567" + i)
                .clickYesBtnPopUpForContactDelete()
                .isPhoneNumberOnThePage("1234567" + i));
    }

    @Test
    public void deleteAllContacts() {
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("LN"+i)
                        .email("test" + i + "@gmail.af")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact: " + i)
                        .build())
                .deleteAllContacts()
                .validateContactListEmpty());
    }

}
