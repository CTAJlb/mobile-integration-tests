package screens.tutorial.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.tutorial.TutorialScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosTutorialScreen extends TutorialScreen {
    private final IButton btnCloseTutorial =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Close\"]"), "btnCloseTutorial");

    public IosTutorialScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name=\"Close\"]"));
    }

    @Override
    public void closeTutorial() {
        btnCloseTutorial.click();
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

    @Override
    public List<String> getListOfContentDescOfTutorialTabs() {
        //only for android
        return null;
    }
}
