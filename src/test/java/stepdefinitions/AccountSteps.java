package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.keysForContext.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.libraries.LibrariesScreen;
import screens.addaccount.AddAccountScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.settings.SettingsScreen;

import java.util.ArrayList;
import java.util.List;

public class AccountSteps {
    private final LibrariesScreen librariesScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private final AddAccountScreen addAccountScreen;
    private final CatalogScreen catalogScreen;
    private ScenarioContext context;

    @Inject
    public AccountSteps(ScenarioContext context) {
        this.context = context;
        librariesScreen = AqualityServices.getScreenFactory().getScreen(LibrariesScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
    }

    @When("I add {string} account")
    public void addAccount(String libraryName) {
        openAccounts();
        librariesScreen.addLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);

        if(libraryName.toLowerCase().equals("LYRASIS Reads".toLowerCase())){
            saveLibraryInContext(ContextLibrariesKeys.LOG_OUT.getKey(), libraryName);
        }
        catalogScreen.state().waitForDisplayed();
    }

    @When("I enter {string} library and save name as {string} on add account screen")
    public void enterLibName(String libraryName, String libraryNameKey) {
        context.add(libraryNameKey, libraryName);
        addAccountScreen.enterLibraryName(libraryName);
    }

    @When("I enter word {} and save as {string} on add account screen")
    public void enterData(String word, String infoKey) {
        context.add(infoKey, word);
        addAccountScreen.enterLibraryName(word);
    }

    @Then("Library {string} is present on add account screen")
    public void isLibraryPresent(String libraryNameKey) {
        String libraryName = context.get(libraryNameKey);
        Assert.assertTrue(libraryName + " not found", addAccountScreen.isLibraryPresent(libraryName));
    }

    @When("Clear search field on add account screen")
    public void clickDeleteBtn() {
        addAccountScreen.clearSearchField();
    }

    @Then("Search field is empty on add account screen")
    public void isSearchFieldEmpty() {
        Assert.assertTrue("Search field is not empty", addAccountScreen.isSearchFieldEmpty());
    }

    @Then("Libraries contain word {string} on add account screen")
    public void isLibraryContainWord(String info) {
        String word = context.get(info);
        Assert.assertTrue("Search result does not contain libraries with " + word, addAccountScreen.isLibraryContainWord(word));
    }

    @Then("Search result is empty on add account screen")
    public void isSearchResultEmpty() {
        Assert.assertTrue("Search result contains data", addAccountScreen.isSearchResultEmpty());
    }

    @Then("Account {string} is present on Accounts screen")
    public void checkAccountIsPresent(String libraryName) {
        openAccounts();
        Assert.assertTrue(libraryName + " is not present on Accounts screen", librariesScreen.isLibraryPresent(libraryName));
    }

    @Then("Account {string} is not present on Accounts screen")
    public void checkAccountIsNotPresent(String libraryName) {
        Assert.assertFalse(libraryName + " is present on Accounts screen", librariesScreen.isLibraryPresent(libraryName));
    }

    @When("I remove {string} account")
    public void removeAccount(String libraryName) {
        openAccounts();
        librariesScreen.deleteLibrary(libraryName);
    }

    @When("I open account {string}")
    public void openAccount(String libraryName) {
        openAccounts();
        librariesScreen.openLibrary(libraryName);
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
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openLibraries();
    }
}
