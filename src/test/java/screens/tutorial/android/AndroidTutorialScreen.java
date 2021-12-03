package screens.tutorial.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.tutorial.TutorialScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTutorialScreen extends TutorialScreen {
    private final IButton btnCloseTutorial =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[contains(@resource-id,\"skip_button\")]"), "btnCloseTutorial");

    public AndroidTutorialScreen() {
        super(By.xpath("//android.widget.ImageView[@content-desc=\"Tutorial page\"]"));
    }

    @Override
    public void closeTutorial() {
        btnCloseTutorial.click();
    }
}
