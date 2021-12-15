package screens.tutorial;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class TutorialScreen extends Screen {
    public TutorialScreen(By locator) {
        super(locator, "Tutorial");
    }

    public abstract void closeTutorial();

    public abstract boolean isTutorialPageOpened(String pageName);

    public abstract void goToNextPage();

    public abstract List<String> getListOfContentDescOfTutorialTabs();
}
