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
        eulaAgreementScreen.clickAgree();
        tutorialScreen.closeTutorial();
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.findLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }
}
