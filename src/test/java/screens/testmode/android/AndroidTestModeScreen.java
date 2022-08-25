package screens.testmode.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.testmode.TestModeScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTestModeScreen extends TestModeScreen {
    private final IButton btnHiddenLibraries = getElementFactory().getButton(
            By.xpath("//android.widget.Switch[@text=\"Enable Hidden Libraries\"]"), "Hidden libraries button");

    public AndroidTestModeScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"Debug options\"]"));
    }

    @Override
    public void enableHiddenLibraries() {
        SwipeElementUtils.swipeByCoordinatesOfWindow();
        btnHiddenLibraries.click();
    }
}
