package screens.findyourlibraryscreen;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class FindYourLibScreen extends Screen {

    protected FindYourLibScreen(By locator) {
        super(locator, "Find your library");
    }

    public abstract void tapAddLibrary();
}
