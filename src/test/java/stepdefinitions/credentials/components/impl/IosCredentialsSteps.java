package stepdefinitions.credentials.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import org.junit.Assert;
import stepdefinitions.credentials.components.AbstractCredentialsSteps;

@StepsType(platform = PlatformName.IOS)
public class IosCredentialsSteps extends AbstractCredentialsSteps {
    public IosCredentialsSteps(ScenarioContext context) {
        super(context);
    }

    @Override
    public void checkLoginIsPerformedSuccessfully() {
        alertScreen.closeNotNowModalIfPresent();
        Assert.assertTrue("Log in is not completed", catalogScreen.state().waitForDisplayed() || accountScreen.isLoginSuccessful());
    }
}
