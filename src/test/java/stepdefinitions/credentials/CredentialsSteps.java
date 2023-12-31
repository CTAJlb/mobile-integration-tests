package stepdefinitions.credentials;

import com.google.inject.Inject;
import enums.keysforcontext.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
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

    @When("Click the log out button on the account screen")
    public void clickLogOut() {
        abstractCredentialsSteps.clickLogOut();
    }

    @When("Enter credentials for {string} account")
    public void enterCredentialsForLibraryAccount(String libraryName) {
        saveLibraryForLogOut(libraryName);
        abstractCredentialsSteps.enterCredentialsForLibraryAccount(libraryName);
    }

    @When("Enter credentials for {string} account in Spanish")
    public void enterCredentialsInSpanish(String libraryName) {
        saveLibraryForLogOut(libraryName);
        abstractCredentialsSteps.enterCredentialsSpanish(libraryName);
    }

    @When("Enter credentials for {string} account in Italian")
    public void enterCredentialsInSpanishIT(String libraryName) {
        saveLibraryForLogOut(libraryName);
        abstractCredentialsSteps.enterCredentialsItalian(libraryName);
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
