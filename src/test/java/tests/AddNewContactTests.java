package tests;

import config.AppiumConfig;
import dto.ContactDTO;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.AddNewContactPage;
import pages.ContactListPage;
import pages.SplashPage;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class AddNewContactTests extends AppiumConfig {
    boolean flagToCloseAlert = false;

    @BeforeClass
    public void beforeClass(){//to login before creating a new contact
        new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("krasleo@gmail.com")
                        .password("Cristiano7777$!")
                        .build());
        //driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
    }
    @AfterMethod
    public void afterMethod(){
        if(flagToCloseAlert){
            flagToCloseAlert = false;
            new AddNewContactPage(driver).clickOkCloseAlert();
                    //backBtnOnEmulator();
            //driver.hideKeyboard();
            //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            //new ContactListPage(driver).pause(10000);
            driver.navigate().back();
        }
    }

    @AfterClass
    public void afterClass(){
       //need to open contact page before logout
        new ContactListPage(driver).logout();
    }


    @Test
    public void positiveAddNewContact(){
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        //contact page - click +
        new ContactListPage(driver).pause(10);
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContact(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("LN" + i)
                        .email("test" + i + "@gmail.af")
                        .phone("1234567" + i)
                        .address("Haifa")
                        .description("contact" + i)
                        .build())
                .validateCurrentContactCreated(i));
        //fill the fields on the add new contact page and click create
        //some validation

    }

    @Test
    public void negativeTestEmptyPhone(){
        int i;
        i = new Random().nextInt(1000) + 1000;
        System.out.println(i);
        //contact page - click +
        flagToCloseAlert = true;
        Assert.assertTrue(new ContactListPage(driver).clickBtnAddNewContact()
                .addNewContactNegative(ContactDTO.builder()
                        .name("testQA20" + i)
                        .lastName("LN" + i)
                        .email("test" + i + "@gmail.af")
                        .phone("")
                        .address("Haifa")
                        .description("contact" + i)
                        .build())
                .validateErrorMessage());
    }
}
