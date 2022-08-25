package screens.testmode;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class TestModeScreen extends Screen {

    protected TestModeScreen(By locator) {
        super(locator, "Test Mode");
    }

    public abstract void enableHiddenLibraries();
}
