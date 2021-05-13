package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.context.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.testng.Assert;
import screens.account.AccountScreen;
import screens.accounts.AccountsScreen;
import screens.addaccount.AddAccountScreen;
import screens.agegate.AgeGateScreen;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.notifications.NotificationModal;
import screens.settings.SettingsScreen;

import java.util.ArrayList;
import java.util.List;

public class AccountSteps {
    private final AccountsScreen accountsScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private final AddAccountScreen addAccountScreen;
    private final AlertScreen alertScreen;
    private final AccountScreen accountScreen;
    private final AgeGateScreen ageGateScreen;
    private final MainCatalogToolbarForm mainCatalogToolbarForm;
    private final CatalogScreen catalogScreen;
    private final NotificationModal notificationModal;
    private ScenarioContext context;

    @Inject
    public AccountSteps(ScenarioContext context) {
        this.context = context;
        accountsScreen = AqualityServices.getScreenFactory().getScreen(AccountsScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        ageGateScreen = AqualityServices.getScreenFactory().getScreen(AgeGateScreen.class);
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        notificationModal = AqualityServices.getScreenFactory().getScreen(NotificationModal.class);
    }

    @When("I add {string} account")
    public void addAccount(String libraryName) {
        openAccounts();
        accountsScreen.addAccount();
        if (AqualityServices.getElementFactory().getButton(By.xpath("//android.widget.Button[@text = \"Deny\"]"), "DENYButton").state().waitForDisplayed()) {
            AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
        }
        Assert.assertTrue(addAccountScreen.state().waitForDisplayed(),
                "Checking that add accounts screen visible");
        addAccountScreen.selectLibrary(libraryName);

        /*saveLibraryInContext(ContextLibrariesKeys.CANCEL_GET.getKey(), libraryName);
        saveLibraryInContext(ContextLibrariesKeys.CANCEL_HOLD.getKey(), libraryName);*/
        if(libraryName.toLowerCase().equals("LYRASIS".toLowerCase())){
            saveLibraryInContext(ContextLibrariesKeys.LOG_OUT.getKey(), libraryName);
        }
    }

    @Then("Account {string} is present on Accounts screen")
    public void checkAccountIsPresent(String libraryName) {
        openAccounts();
        Assert.assertTrue(accountsScreen.isLibraryPresent(libraryName), libraryName + " is not present on Accounts screen");
    }

    @Then("Account {string} is not present on Accounts screen")
    public void checkAccountIsNotPresent(String libraryName) {
        Assert.assertFalse(accountsScreen.isLibraryPresent(libraryName), libraryName + " is present on Accounts screen");
    }

    @When("I remove {string} account")
    public void removeAccount(String libraryName) {
        openAccounts();
        accountsScreen.deleteLibrary(libraryName);
    }

    @When("I open account {string}")
    public void openAccount(String libraryName) {
        openAccounts();
        accountsScreen.openAccount(libraryName);
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
