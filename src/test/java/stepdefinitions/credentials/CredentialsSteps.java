package stepdefinitions.credentials;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import screens.alert.AlertScreen;
import stepdefinitions.BaseSteps;
import stepdefinitions.credentials.components.AbstractCredentialsSteps;
import stepdefinitions.credentials.components.ICredentialsSteps;

public class CredentialsSteps extends BaseSteps implements ICredentialsSteps {
    private final AbstractCredentialsSteps abstractCredentialsSteps;
    private final AlertScreen alertScreen;

    @Inject
    public CredentialsSteps(ScenarioContext context) {
        this.abstractCredentialsSteps = stepsFactory.getSteps(AbstractCredentialsSteps.class, context);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @Then("Login is performed successfully")
    public void checkLoginIsPerformedSuccessfully() {
        alertScreen.closeNotNowModalIfPresent();
        abstractCredentialsSteps.checkLoginIsPerformedSuccessfully();
    }

    @Then("Text on Logout button is changed to Log in on Account screen")
    public void textOnLogoutButtonIsChangedToLogInOnAccountScreen() {
        abstractCredentialsSteps.textOnLogoutButtonIsChangedToLogInOnAccountScreen();
    }

    @When("I click the log out button on the account screen")
    public void clickLogOut() {
        abstractCredentialsSteps.clickLogOut();
    }

    @And("I enter credentials for {string} account")
    public void enterCredentialsForLibraryAccount(String libraryName) {
        abstractCredentialsSteps.enterCredentialsForLibraryAccount(libraryName);
    }
}
