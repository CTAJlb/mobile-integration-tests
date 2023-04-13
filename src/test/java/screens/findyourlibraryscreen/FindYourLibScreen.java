package screens.findyourlibraryscreen;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

public abstract class FindYourLibScreen extends Screen {

    protected FindYourLibScreen(By locator) {
        super(locator, "Find your library");
    }

    public abstract void tapAddLibrary();

    public abstract void tapToLibrary(String libName);

    public abstract boolean isSortingAlphabetical(int amountOfLibraries);

    public abstract void tapCancelBtn();

    public abstract String getTextFromFindYourLibraryLbl();

    public abstract String getTextFromFindYourLibraryLblIT();

    public abstract String getTextFromFindYourLibraryLblFR();

    public abstract String getTextFromFindYourLibraryLblGR();

    public abstract String getTextFromAddLibraryBtn();

    public abstract String getTextFromAddLibraryBtnIT();

    public abstract String getTextFromAddLibraryBtnFR();

    public abstract String getTextFromAddLibraryBtnGR();

    public abstract String getTextFromCancelBtn();

    public abstract String getTextFromCancelBtnIT();

    public abstract String getTextFromCancelBtnFR();

    public abstract String getTextFromCancelBtnGR();
}
