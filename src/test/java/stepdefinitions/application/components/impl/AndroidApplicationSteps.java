package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import org.assertj.core.api.SoftAssertions;
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
        if (!eulaAgreementScreen.isVanillaApp()) {
            eulaAgreementScreen.clickAgree();
        }
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(tutorialScreen.isTutorialPageOpened("Step 1")).as("Step 1 page of the tutorial is not opened. ").isTrue();
        tutorialScreen.goToNextPage();
        softAssertions.assertThat(tutorialScreen.isTutorialPageOpened("Step 2")).as("Step 2 page of the tutorial is not opened. ").isTrue();
        tutorialScreen.goToNextPage();
        softAssertions.assertThat(tutorialScreen.isTutorialPageOpened("Step 3")).as("Step 1 page of the tutorial is not opened. ").isTrue();
        softAssertions.assertAll();
        tutorialScreen.closeTutorial();
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.findLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }
}
