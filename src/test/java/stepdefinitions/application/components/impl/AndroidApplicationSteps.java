package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidApplicationSteps extends AbstractApplicationSteps {

    @Override
    public void returnToPreviousPage(String bookName) {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    public void openApplication() {
        if (!eulaAgreementScreen.isVanillaApp()) {
            eulaAgreementScreen.clickAgree();
        }
        welcomeScreen.addALibraryLater();
    }
}
