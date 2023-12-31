package screens.welcome.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.welcome.WelcomeScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidWelcomeScreen extends WelcomeScreen {
    private final IButton btnFindLibrary = getElementFactory().getButton(By.xpath("//android.widget.Button"), "btnFindLibrary");

    public AndroidWelcomeScreen() {
        super(By.xpath("//android.widget.Button[contains(@resource-id,\"selectionButton\")]"));
    }

    @Override
    public void tapFindLibraryButton() {
        btnFindLibrary.click();
    }

    @Override
    public void tapFindLibraryButtonInSpanish() {
        //for ios
    }

    @Override
    public void tapFindLibraryButtonInItalian() {
        //for ios
    }

    @Override
    public void tapFindLibraryButtonInFrench() {
        //for ios
    }

    @Override
    public void tapFindLibraryButtonInGerman() {
        //for ios
    }

    @Override
    public String getTextFromButtonFindYourLibraryES() {
        return btnFindLibrary.getText();
    }

    @Override
    public String getTextFromButtonFindYourLibraryIT() {
        return btnFindLibrary.getText();
    }

    @Override
    public String getTextFromButtonFindYourLibraryFR() {
        //for ios
        return null;
    }

    @Override
    public boolean isOpenedInSpanish() {
        //for ios
        return false;
    }

    @Override
    public boolean isOpenedInItalian() {
        //for ios
        return false;
    }

    @Override
    public boolean isOpenedInFrench() {
        //for ios
        return false;
    }

    @Override
    public boolean isOpenedInGerman() {
        //for ios
        return false;
    }
}
