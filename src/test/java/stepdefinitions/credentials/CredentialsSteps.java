package stepdefinitions.credentials;

import com.google.inject.Inject;
import enums.keysforcontext.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import stepdefinitions.BaseSteps;
import stepdefinitions.credentials.components.AbstractCredentialsSteps;
import stepdefinitions.credentials.components.ICredentialsSteps;

import java.util.ArrayList;
import java.util.List;

public class CredentialsSteps extends BaseSteps implements ICredentialsSteps {
    private final AbstractCredentialsSteps abstractCredentialsSteps;
    private final ScenarioContext context;

    @Inject
    public CredentialsSteps(ScenarioContext context) {
        this.context = context;
        this.abstractCredentialsSteps = stepsFactory.getSteps(AbstractCredentialsSteps.class, context);
    }

    @Then("Login is performed successfully")
    public void checkLoginIsPerformedSuccessfully() {
        abstractCredentialsSteps.checkLoginIsPerformedSuccessfully();
    }

    @Then("Logout is performed successfully")
    public void isLogoutSuccessfully() {
        abstractCredentialsSteps.isLogoutSuccessfully();
    }

    @When("I click the log out button on the account screen")
    public void clickLogOut() {
        abstractCredentialsSteps.clickLogOut();
    }

    @And("Enter credentials for {string} account")
    public void enterCredentialsForLibraryAccount(String libraryName) {
        saveLibraryForLogOut(libraryName);
        abstractCredentialsSteps.enterCredentialsForLibraryAccount(libraryName);
    }

    private void saveLibraryForLogOut(String libraryName) {
        saveLibraryInContext(ContextLibrariesKeys.LOG_OUT.getKey(), libraryName);
    }

    private void saveLibraryInContext(String key, String libraryName) {
        List<String> listOfLibraries = context.containsKey(key)
                ? context.get(key)
                : new ArrayList<>();

        listOfLibraries.add(libraryName);
        context.add(key, listOfLibraries);
    }
}
