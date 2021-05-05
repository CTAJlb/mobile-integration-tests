package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import org.openqa.selenium.By;
import org.testng.Assert;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidApplicationSteps extends AbstractApplicationSteps {

    @Override
    public void returnToPreviousPage() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void openApplicationVar2(String libraryName) {
        if (!eulaAgreementScreen.isVanillaApp()) {
            eulaAgreementScreen.clickAgree();
        }
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.findLibrary();
        if (AqualityServices.getElementFactory().getButton(By.xpath("//android.widget.Button[@text = \"Deny\"]"), "DENYButton").state().waitForDisplayed()) {
            AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
        }
        Assert.assertTrue(addAccountScreen.state().waitForDisplayed(),
                "Checking that add accounts screen visible");
        addAccountScreen.selectLibrary(libraryName);
    }

    public void openApplication() {
        if (!eulaAgreementScreen.isVanillaApp()) {
            eulaAgreementScreen.clickAgree();
        }
        welcomeScreen.addALibraryLater();
        if (ageGateScreen.state().waitForDisplayed()) {
            ageGateScreen.approveAge();
        }
    }
}
