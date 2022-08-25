package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.findyourlibraryscreen.FindYourLibScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.settings.SettingsScreen;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.IOS)
public class IosApplicationSteps extends AbstractApplicationSteps {
    private final ReaderEpubScreen readerEpubScreen;
    private final ReaderPdfScreen readerPdfScreen;
    private final CatalogScreen catalogScreen;
    private final FindYourLibScreen findYourLibScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;

    public IosApplicationSteps() {
        super();
        readerEpubScreen = screenFactory.getScreen(ReaderEpubScreen.class);
        readerPdfScreen = screenFactory.getScreen(ReaderPdfScreen.class);
        catalogScreen = screenFactory.getScreen(CatalogScreen.class);
        findYourLibScreen = screenFactory.getScreen(FindYourLibScreen.class);
        bottomMenuForm = screenFactory.getScreen(BottomMenuForm.class);
        settingsScreen = screenFactory.getScreen(SettingsScreen.class);
    }

    @Override
    public void turnOnTestMode(String libraryName) {
        tutorialScreen.closeTutorial();
        welcomeScreen.tapFindLibraryButton();
        addAccountScreen.selectLibraryViaSearch(libraryName);
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openTestMode();
    }

    @Override
    public void returnToPreviousScreenForEpubAndPdf() {
        if (readerEpubScreen.state().isDisplayed()) {
            readerEpubScreen.openNavigationBar();
            readerEpubScreen.getNavigationBarEpubScreen().returnToPreviousScreen();
        } else if (readerPdfScreen.state().isDisplayed()) {
            if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
                readerPdfScreen.returnToPreviousScreen();
            } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
                readerPdfScreen.getNavigationBarScreen().tapBackButton();
            }
        }
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        tutorialScreen.closeTutorial();
        welcomeScreen.tapFindLibraryButton();
        addAccountScreen.selectLibraryViaSearch(libraryName);
        catalogScreen.state().waitForDisplayed();
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
        findYourLibScreen.tapToLibrary(libName);
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
