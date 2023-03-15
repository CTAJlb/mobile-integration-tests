package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import screens.bottommenu.BottomMenu;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidApplicationSteps extends AbstractApplicationSteps {

    @Override
    public void turnOnTestMode() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openTestMode();
    }

    @Override
    public void returnToPreviousScreenForEpubAndPdf() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        tutorialScreen.closeTutorial();
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.tapFindLibraryButton();
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }

    @Override
    public void addAccountByTheLogo(String libraryName) {
        catalogScreen.tapTheLogo();
        findYourLibScreen.tapAddLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);
        catalogScreen.state().waitForDisplayed();
    }

    @Override
    public void tapTheLogo() {
        catalogScreen.tapTheLogo();
    }

    @Override
    public void tapToLibrary(String libName) {

    }

    @Override
    public boolean isSortingInAlphabetical(int amountOfLibraries) {
        return findYourLibScreen.isSortingAlphabetical(amountOfLibraries);
    }

    @Override
    public void tapCancelBtn() {
        findYourLibScreen.tapCancelBtn();
    }

    @Override
    public boolean isMenuBarDisplayed() {
        return bottomMenuForm.isMenuDisplayed();
    }

    @Override
    public String getTypeOfButton(String type) {
        return bottomMenuForm.getTypeOfTab(type);
    }
}
