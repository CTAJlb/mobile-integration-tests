package screens.aboutpalace.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.aboutpalace.AboutPalaceScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosAbPalaceScreen extends AboutPalaceScreen {

    private final ILabel lblAboutPalace = getElementFactory().getLabel(By.xpath("//XCUIElementTypeImage[@name=\"\tThe Palace Project\"]"), "About Palace label");

    public IosAbPalaceScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"About Palace\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblAboutPalace.state().waitForDisplayed();
    }
}
