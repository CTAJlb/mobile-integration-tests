package screens.testmode.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.testmode.TestModeScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTestModeScreen extends TestModeScreen {
    private final IButton btnHiddenLibraries = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeSwitch[name=\"Enable Hidden Libraries\"]"), "Hidden libraries button");

    public IosTestModeScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[name=\"Testing\"]"));
    }

    @Override
    public void enableHiddenLibraries() {
        btnHiddenLibraries.click();
    }
}
