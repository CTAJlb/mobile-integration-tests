package stepdefinitions.application.components;

import aquality.appium.mobile.application.AqualityServices;
import enums.localization.translation.Spanish;
import enums.timeouts.RestartAppTimeouts;
import org.junit.Assert;
import screens.addaccount.AddAccountScreen;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.findyourlibraryscreen.FindYourLibScreen;
import screens.settings.SettingsScreen;
import screens.tutorial.TutorialScreen;
import screens.welcome.WelcomeScreen;
import stepdefinitions.BaseSteps;

import java.time.Duration;

public abstract class AbstractApplicationSteps extends BaseSteps implements IApplicationSteps {
    protected final WelcomeScreen welcomeScreen;
    protected final AddAccountScreen addAccountScreen;
    protected final CatalogScreen catalogScreen;
    protected final TutorialScreen tutorialScreen;
    protected final FindYourLibScreen findYourLibScreen;
    protected final BottomMenuForm bottomMenuForm;
    protected final SettingsScreen settingsScreen;

    public AbstractApplicationSteps() {
        welcomeScreen = AqualityServices.getScreenFactory().getScreen(WelcomeScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        tutorialScreen = AqualityServices.getScreenFactory().getScreen(TutorialScreen.class);
        findYourLibScreen = AqualityServices.getScreenFactory().getScreen(FindYourLibScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
    }

    public abstract void turnOnTestMode(String libraryName);

    public abstract void returnToPreviousScreenForEpubAndPdf();

    public abstract void addAccountFromWelcomeScreen(String libraryName);

    public abstract void addAccountByTheLogo(String libraryName);

    public abstract void tapTheLogo();

    public abstract void tapToLibrary(String libName);

    public abstract boolean isSortingInAlphabetical(int amountOfLibraries);

    public abstract void tapCancelBtn();

    public abstract boolean isMenuBarDisplayed();

    public abstract String getTypeOfButton(String type);

    @Override
    public void waitSeveralSeconds(Integer secondsCount) {
        if (secondsCount > 10) {
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount / 3));
            AqualityServices.getApplication().getDriver().getContext();
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount / 3));
            AqualityServices.getApplication().getDriver().getContext();
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount / 3));
        } else {
            AqualityServices.getConditionalWait().waitFor(() -> false, Duration.ofSeconds(secondsCount));
        }
    }

    @Override
    public void checkEachTutorialPageCanBeOpened() {
        tutorialScreen.getListOfPageNames().forEach(pageName -> {
            Assert.assertTrue(String.format("Tutorial page '%s' is not opened", pageName), tutorialScreen.isTutorialPageOpened(pageName));
            tutorialScreen.goToNextPage();
        });
    }

    @Override
    public void checkThatTutorialScreenIsOpened() {
        Assert.assertTrue("Tutorial screen is not opened", tutorialScreen.state().waitForDisplayed());
    }

    @Override
    public void restartApp() {
        AqualityServices.getApplication().getDriver().runAppInBackground(RestartAppTimeouts.TIMEOUT_RESTART_APPLICATION.getTimeoutRestart());
        AqualityServices.getApplication().getDriver().closeApp();
        AqualityServices.getApplication().getDriver().launchApp();
        catalogScreen.state().waitForDisplayed();
    }

    @Override
    public void checkTranslationOnWelcomeScreen(){
        Assert.assertEquals("Find your library button is not translated", Spanish.FIND_YOUR_LIBRARY.i18n(), welcomeScreen.getTextFromButtonFindYourLibrary());
    }

    @Override
    public void checkWelcomeScreenIsOpened() {
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.state().isDisplayed());
    }

    @Override
    public void closeTutorialScreen() {
        tutorialScreen.closeTutorial();
    }

    @Override
    public void closeWelcomeScreen() {
        welcomeScreen.tapFindLibraryButton();
    }
}
