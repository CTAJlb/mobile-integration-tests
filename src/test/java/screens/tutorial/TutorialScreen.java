package screens.tutorial;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class TutorialScreen extends Screen {
    public TutorialScreen(By locator) {
        super(locator, "Tutorial");
    }

    public abstract void closeTutorial();
}
