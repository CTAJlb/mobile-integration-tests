package stepdefinitions.application.components;

import aquality.appium.mobile.application.AqualityServices;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.junit.Assert;
import screens.addaccount.AddAccountScreen;
import screens.alert.AlertScreen;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.tutorial.TutorialScreen;
import screens.welcome.WelcomeScreen;
import stepdefinitions.BaseSteps;

public abstract class AbstractApplicationSteps extends BaseSteps implements IApplicationSteps {
    protected final WelcomeScreen welcomeScreen;
    protected final AddAccountScreen addAccountScreen;
    protected final CatalogScreen catalogScreen;
    protected final AlertScreen alertScreen;
    protected final TutorialScreen tutorialScreen;

    public AbstractApplicationSteps() {
        welcomeScreen = AqualityServices.getScreenFactory().getScreen(WelcomeScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
        tutorialScreen = AqualityServices.getScreenFactory().getScreen(TutorialScreen.class);
    }

    public abstract void returnToPreviousScreenForEpubAndPdf();

    public abstract void addAccountFromWelcomeScreen(String libraryName);

    @Override
    public void performActionOnAlert(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        alertScreen.waitAndPerformAlertActionIfDisplayed(actionButtonKey);
    }

    @Override
    public void checkThatTutorialScreenIsOpened(){
        Assert.assertTrue("Tutorial screen is not opened", tutorialScreen.state().isDisplayed());
    }

    @Override
    public void restartApp() {
        AqualityServices.getApplication().getDriver().closeApp();
        AqualityServices.getApplication().getDriver().launchApp();
        catalogScreen.state().waitForDisplayed();
    }

    @Override
    public void checkEachTutorialPageCanBeOpened(){
        tutorialScreen.getListOfContentDescOfTutorialTabs().stream().forEach(contentDesc -> {
            Assert.assertTrue(String.format("Tab with '%s' contentDesc is not opened", contentDesc), tutorialScreen.isTutorialPageOpened(contentDesc));
            tutorialScreen.goToNextPage();
        });
    }

    @Override
    public void checkWelcomeScreenIsOpened(){
        Assert.assertTrue("Welcome screen is not opened", welcomeScreen.state().isDisplayed());
    }
}
