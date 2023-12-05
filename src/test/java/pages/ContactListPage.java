package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListPage extends BasePage {
    public ContactListPage(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@text='Contact list']")
    MobileElement textTitle;

   // @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    @FindBy(xpath = "//android.widget.ImageView[@content-desc='More options']")
    MobileElement menuMoreOptions;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/title']")
    MobileElement btnLogout;

    @FindBy(xpath = "//*[@class='android.widget.ImageButton']")
    MobileElement btnAddNewContact;

    By contactPhoneNumberElement = By.xpath("//*[@resource-id='com.sheygam.contactapp:id/rowPhone']");



    public boolean validateContactListOpened() {
        return isTextEqual(textTitle, "Contact list");
    }

    public AuthenticationPage logout(){
        clickBase(menuMoreOptions);
        clickBase(btnLogout);
        return new AuthenticationPage(driver);
    }

    public AddNewContactPage clickBtnAddNewContact(){
        clickBase(btnAddNewContact);
        return new AddNewContactPage(driver);
    }

    public List<MobileElement>list(By locator){
        return driver.findElements(locator);
    }

    public boolean validateCurrentContactCreated(int i) {
        for ( MobileElement phoneElement: list(contactPhoneNumberElement)) {
           if (isTextEqual(phoneElement,"1234567"+i))
               return true;
            }
        return false;
   }
}
