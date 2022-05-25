package screens.findyourlibraryscreen.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.findyourlibraryscreen.FindYourLibScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosFindYourLibScreen extends FindYourLibScreen {

    private final IButton btnAddLib =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSheet[@name=\"Find Your Library\"]//XCUIElementTypeButton[@name=\"Add Library\"]"), "Add library btn");

    public IosFindYourLibScreen() {
        super(By.xpath("//XCUIElementTypeSheet[@name=\"Find Your Library\"]"));
    }

    @Override
    public void tapAddLibrary() {
        btnAddLib.click();
    }
}
