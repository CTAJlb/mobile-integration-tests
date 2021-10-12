package screens.welcome.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.welcome.WelcomeScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidWelcomeScreen extends WelcomeScreen {
    private static final By PAGE_LOCATOR = By.id("selectionAlternateButton");
    private final IButton findLibraryBtn = getElementFactory().getButton(By.xpath("//android.widget.Button"), "Find Your Library");

    public AndroidWelcomeScreen() {
        super(PAGE_LOCATOR);
    }

    @Override
    public void findLibrary() {
        findLibraryBtn.state().waitForDisplayed();
        findLibraryBtn.click();
    }
}
