package screens.welcome.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.welcome.WelcomeScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosWelcomeScreen extends WelcomeScreen {
    private final IButton btnFindLibrary = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton"), "Find your library button");

    public IosWelcomeScreen() {
        super(By.xpath("//XCUIElementTypeButton"));
    }

    @Override
    public void tapFindLibraryButton() {
        btnFindLibrary.click();
    }

    @Override
    public String getTextFromButtonFindYourLibrary() {
        return btnFindLibrary.getText();
    }
}
