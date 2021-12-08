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
        //only for android
    }

    @Override
    public boolean isTutorialPageOpened(String pageName) {
        //only for android
        return false;
    }

    @Override
    public void goToNextPage() {
        //only for android
    }
}
