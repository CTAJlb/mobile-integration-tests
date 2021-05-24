package stepdefinitions.credentials.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import org.junit.Assert;
import stepdefinitions.credentials.components.AbstractCredentialsSteps;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidCredentialsSteps extends AbstractCredentialsSteps {
    public AndroidCredentialsSteps(ScenarioContext context) {
        super(context);
    }

    @Override
    public void checkLoginIsPerformedSuccessfully() {
        Assert.assertTrue("Login failed. Message: " + accountScreen.getLoginFailedMessage(), accountScreen.isLoginSuccessful());
    }
}
