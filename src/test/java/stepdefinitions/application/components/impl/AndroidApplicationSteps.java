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
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        if (!eulaAgreementScreen.isVanillaApp()) {
            eulaAgreementScreen.clickAgree();
        }
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.findLibrary();
        if (AqualityServices.getElementFactory().getButton(By.xpath("//android.widget.Button[@text = \"Deny\"]"), "DENYButton").state().waitForDisplayed()) {
            AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
        }
        Assert.assertTrue("Checking that add accounts screen visible", addAccountScreen.state().waitForDisplayed());
        addAccountScreen.selectLibrary(libraryName);
    }
}
