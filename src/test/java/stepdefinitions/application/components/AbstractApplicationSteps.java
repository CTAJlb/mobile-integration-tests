package stepdefinitions.application.components;

import aquality.appium.mobile.application.AqualityServices;
import constants.localization.french.FrenchIos;
import constants.localization.italian.ItalianIos;
import constants.localization.spanish.SpanishIos;
import enums.timeouts.RestartAppTimeouts;
import org.assertj.core.api.SoftAssertions;
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

    public abstract void turnOnTestMode();

    public abstract void returnToPreviousScreenForEpubAndPdf();

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
        Assert.assertEquals("Find your library button is not translated", SpanishIos.FIND_YOUR_LIBRARY, welcomeScreen.getTextFromButtonFindYourLibraryES());
    }

    @Override
    public void checkTranslationOnWelcomeScreenItalian(){
        Assert.assertEquals("Find your library button is not translated", ItalianIos.FIND_YOUR_LIBRARY, welcomeScreen.getTextFromButtonFindYourLibraryIT());
    }

    @Override
    public void checkTranslationOnWelcomeScreenFrench(){
        Assert.assertEquals("Find your library button is not translated", FrenchIos.FIND_YOUR_LIBRARY, welcomeScreen.getTextFromButtonFindYourLibraryFR());
    }

    @Override
    public void checkWelcomeScreenIsOpened() {
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.state().waitForDisplayed());
    }

    @Override
    public void isWelcomeScreenOpenedInSpanish(){
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.isOpenedInSpanish());
    }

    @Override
    public void isWelcomeScreenOpenedInItalian(){
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.isOpenedInItalian());
    }

    @Override
    public void isWelcomeScreenOpenedInFrench(){
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.isOpenedInFrench());
    }

    @Override
    public void isWelcomeScreenOpenedInGerman(){
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.isOpenedInGerman());
    }

    @Override
    public void closeWelcomeScreenInSpanish() {
        welcomeScreen.tapFindLibraryButtonInSpanish();
    }

    @Override
    public void closeWelcomeScreenInItalian() {
        welcomeScreen.tapFindLibraryButtonInItalian();
    }

    @Override
    public void closeWelcomeScreenInFrench() {
        welcomeScreen.tapFindLibraryButtonInFrench();
    }

    @Override
    public void closeWelcomeScreenInGerman() {
        welcomeScreen.tapFindLibraryButtonInGerman();
    }

    @Override
    public void closeTutorialScreen() {
        tutorialScreen.closeTutorial();
    }

    @Override
    public void closeWelcomeScreen() {
        welcomeScreen.tapFindLibraryButton();
    }

    public void checkFindYourLibScreenTranslation() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findYourLibScreen.getTextFromFindYourLibraryLbl()).as("Find your library label is not translated").isEqualTo(SpanishIos.FIND_YOUR_LIBRARY);
        softAssertions.assertThat(findYourLibScreen.getTextFromAddLibraryBtn()).as("Add library button is not translated").isEqualTo(SpanishIos.ADD_ACCOUNT);
        softAssertions.assertThat(findYourLibScreen.getTextFromCancelBtn()).as("Cancel button is not translated").isEqualTo(SpanishIos.CANCEL);
        softAssertions.assertAll();
    }

    public void checkFindYourLibScreenTranslationItalian() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findYourLibScreen.getTextFromFindYourLibraryLblIT()).as("Find your library label is not translated").isEqualTo(ItalianIos.FIND_YOUR_LIBRARY);
        softAssertions.assertThat(findYourLibScreen.getTextFromAddLibraryBtnIT()).as("Add library button is not translated").isEqualTo(ItalianIos.ADD_ACCOUNT);
        softAssertions.assertThat(findYourLibScreen.getTextFromCancelBtnIT()).as("Cancel button is not translated").isEqualTo(ItalianIos.CANCEL);
        softAssertions.assertAll();
    }

    public void checkFindYourLibScreenTranslationFrench() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findYourLibScreen.getTextFromFindYourLibraryLblFR()).as("Find your library label is not translated").isEqualTo(FrenchIos.FIND_YOUR_LIBRARY);
        softAssertions.assertThat(findYourLibScreen.getTextFromAddLibraryBtnFR()).as("Add library button is not translated").isEqualTo(FrenchIos.ADD_ACCOUNT);
        softAssertions.assertThat(findYourLibScreen.getTextFromCancelBtnFR()).as("Cancel button is not translated").isEqualTo(FrenchIos.CANCEL);
        softAssertions.assertAll();
    }

    public void checkFindYourLibScreenTranslationGerman() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(findYourLibScreen.getTextFromFindYourLibraryLblIT()).as("Find your library label is not translated").isEqualTo(ItalianIos.FIND_YOUR_LIBRARY);
        softAssertions.assertThat(findYourLibScreen.getTextFromAddLibraryBtnIT()).as("Add library button is not translated").isEqualTo(ItalianIos.ADD_ACCOUNT);
        softAssertions.assertThat(findYourLibScreen.getTextFromCancelBtnIT()).as("Cancel button is not translated").isEqualTo(ItalianIos.CANCEL);
        softAssertions.assertAll();
    }
}
