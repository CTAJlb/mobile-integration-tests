package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import org.junit.Assert;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidApplicationSteps extends AbstractApplicationSteps {

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
    public void checkEachTutorialPageCanBeOpened() {
        tutorialScreen.getListOfPageNames().stream().forEach(pageName -> {
            Assert.assertTrue(String.format("Tutorial page '%s' is not opened", pageName), tutorialScreen.isTutorialPageOpened(pageName));
            tutorialScreen.goToNextPage();
        });
    }
}
