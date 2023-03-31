package screens.welcome;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class WelcomeScreen extends Screen {
    public WelcomeScreen(By locator) {
        super(locator, "Welcome");
    }

    public abstract void tapFindLibraryButton();

    public abstract void tapFindLibraryButtonInSpanish();
    public abstract void tapFindLibraryButtonInItalian();

    public abstract String getTextFromButtonFindYourLibraryES();
    public abstract String getTextFromButtonFindYourLibraryIT();

    public abstract boolean isOpenedInSpanish();
}
