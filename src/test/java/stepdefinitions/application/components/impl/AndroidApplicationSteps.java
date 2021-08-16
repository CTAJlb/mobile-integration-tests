package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import org.junit.Assert;
import org.openqa.selenium.By;
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
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.findLibrary();
        addAccountScreen.selectLibrary(libraryName);
    }
}
