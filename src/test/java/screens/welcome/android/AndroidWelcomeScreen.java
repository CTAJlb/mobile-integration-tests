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
}
