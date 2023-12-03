package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pages.ContactListPage;
import pages.SplashPage;

public class AddNewContactTests extends AppiumConfig {

    @BeforeClass
    public void beforeClass(){//to login before creating a new contact
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





}
