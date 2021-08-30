package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.keysForContext.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.accounts.AccountsScreen;
import screens.addaccount.AddAccountScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.settings.SettingsScreen;

import java.util.ArrayList;
import java.util.List;

public class AccountSteps {
    private final AccountsScreen accountsScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private final AddAccountScreen addAccountScreen;
    private ScenarioContext context;

    @Inject
    public AccountSteps(ScenarioContext context) {
        this.context = context;
        accountsScreen = AqualityServices.getScreenFactory().getScreen(AccountsScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
    }

    @When("I add {string} account")
    public void addAccount(String libraryName) {
        openAccounts();
        accountsScreen.addAccount();
        addAccountScreen.selectLibraryViaSearch(libraryName);

        if(libraryName.toLowerCase().equals("LYRASIS".toLowerCase())){
            saveLibraryInContext(ContextLibrariesKeys.LOG_OUT.getKey(), libraryName);
        }
    }

    @Then("Account {string} is present on Accounts screen")
    public void checkAccountIsPresent(String libraryName) {
        openAccounts();
        Assert.assertTrue(libraryName + " is not present on Accounts screen", accountsScreen.isLibraryPresent(libraryName));
    }

    @Then("Account {string} is not present on Accounts screen")
    public void checkAccountIsNotPresent(String libraryName) {
        Assert.assertFalse(libraryName + " is present on Accounts screen", accountsScreen.isLibraryPresent(libraryName));
    }

    @When("I remove {string} account")
    public void removeAccount(String libraryName) {
        openAccounts();
        accountsScreen.deleteLibrary(libraryName);
    }

    @When("I open account {string}")
    public void openAccount(String libraryName) {
        openAccounts();
        accountsScreen.openLibraryAccount(libraryName);
    }

    private void saveLibraryInContext(String key, String libraryName) {
        List<String> listOfLibraries = context.containsKey(key)
                ? context.get(key)
                : new ArrayList<>();

        listOfLibraries.add(libraryName);
        context.add(key, listOfLibraries);
    }

    private void openAccounts() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openAccounts();
    }
}
