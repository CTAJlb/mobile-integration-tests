package screens.tutorial.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.tutorial.TutorialScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTutorialScreen extends TutorialScreen {
    public IosTutorialScreen() {
        super(By.xpath(""));
    }

    @Override
    public void closeTutorial() {

    }
}
