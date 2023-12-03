package tests;

import com.github.javafaker.Faker;
import config.AppiumConfig;
import dto.UserDTO;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.AuthenticationPage;
import pages.ContactListPage;
import pages.SplashPage;

public class SignUpTests extends AppiumConfig {

//    RandomUtils randomUtils = new RandomUtils();
//    String email = randomUtils.generateEmail(7)
//using faker instead of random utils to generate email address
    boolean flagUserLogin = false;
    boolean flagIsPopUpErrorDisplays = false;

    //Initialize faker
    Faker faker = new Faker();

    //Generate a random email address
    String randomEmail = faker.internet().emailAddress();


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
    public void positiveRegistration(){
        System.out.println("Random Email: " + randomEmail);
        flagUserLogin = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage()
                .registration(UserDTO.builder()
                        .email(randomEmail)
                        .password("Cristiano7777$!")
                        .build())
                .validateContactListOpened());
    }

    @Test
    public void negativeRegistrationEmptyEmail(){
        flagIsPopUpErrorDisplays = true;
        Assert.assertTrue(new SplashPage(driver).goToAuthPage().fillPassword("Cristiano7777$!")
                .clickRegBtnNegative().validateErrorTitleAlertCorrect());
    }


}
