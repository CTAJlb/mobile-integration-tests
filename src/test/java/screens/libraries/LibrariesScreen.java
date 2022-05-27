package screens.libraries;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class LibrariesScreen extends Screen {
    protected LibrariesScreen(By locator) {
        super(locator, "LibrariesScreen");
    }

    public abstract boolean isLibraryPresent(String libraryName);

    public abstract void openLibrary(String libraryName);

    public abstract void addLibrary();

    public abstract boolean isAddLibraryBtnDisplayed();

    public abstract void deleteLibrary(String libraryName);

    public abstract boolean isLibrariesAreSorted();

    public abstract boolean isLibrarySettingsOpened(String libraryName);
}
