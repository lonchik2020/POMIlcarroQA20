package tests;

import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class LoginTests extends AppiumConfig {

    boolean flagUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

    @AfterMethod
    public void afterMethod(){
        if(flagUserLogin){
            flagUserLogin = false;
            new ContactListPage(driver).logout();
        }else if(flagIsPopUpErrorDisplays){
            flagIsPopUpErrorDisplays = false;
            new AuthenticationPage(driver).clickOkBtnAlert();
        }
    }

    @Test
    public void positiveLogin(){
        flagUserLogin = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage()
                .login(UserDTO.builder()
                        .email("krasleo@gmail.com")
                        .password("Cristiano7777$!")
                        .build())
                .validateContactListOpened());
    }

    @Test
    public void negativeLoginEmptyEmail(){
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("Cristiano7777$!")
                        .clickLoginBtnNegative().validateErrorTitleAlertCorrect());
    }
}
