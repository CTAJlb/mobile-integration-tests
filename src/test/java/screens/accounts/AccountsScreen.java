package screens.accounts;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class AccountsScreen extends Screen {
    protected AccountsScreen(By locator) {
        super(locator, "Screen");
    }

    public abstract boolean isLibraryPresent(String libraryName);

    public abstract void openLibraryAccount(String libraryName);

    public abstract void addAccount();

    public abstract void deleteLibrary(String libraryName);
}
