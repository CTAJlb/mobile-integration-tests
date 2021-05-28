package stepdefinitions.credentials.components;

import aquality.appium.mobile.application.AqualityServices;
import constants.context.ScenarioContextKey;
import framework.configuration.Configuration;
import framework.configuration.Credentials;
import framework.utilities.ScenarioContext;
import framework.utilities.returningBooksUtil.APIUtil;
import org.junit.Assert;
import screens.account.AccountScreen;
import screens.accounts.AccountsScreen;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.settings.SettingsScreen;
import screens.subcategory.SubcategoryScreen;
import stepdefinitions.BaseSteps;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCredentialsSteps extends BaseSteps implements ICredentialsSteps {
    protected final AccountScreen accountScreen;
    private final AccountsScreen accountsScreen;
    protected final SubcategoryScreen subcategoryScreen;
    protected final CatalogScreen catalogScreen;
    protected final AlertScreen alertScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private ScenarioContext context;

    public AbstractCredentialsSteps(ScenarioContext context) {
        this.context = context;
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        accountsScreen = AqualityServices.getScreenFactory().getScreen(AccountsScreen.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
    }

    public void textOnLogoutButtonIsChangedToLogInOnAccountScreen() {
        Assert.assertTrue("Text on Login button is not changed to Log out on Account screen", accountScreen.isLogoutSuccessful());
    }

    public void clickLogOut() {
        accountScreen.logOut();
    }

    public void enterCredentialsForLibraryAccount(String libraryName) {
        openAccounts();
        accountsScreen.openAccount(libraryName);
        Credentials credentials = Configuration.getCredentials(libraryName);
        storeCredentials(credentials);
        accountScreen.enterCredentials(credentials);
    }

    private void openAccounts() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openAccounts();
    }

    private void storeCredentials(Credentials credentials) {
        String barcode = credentials.getBarcode();
        String pin = credentials.getPin();
        APIUtil.enterBookAfterOpeningAccount(barcode, pin);
        if (context.get(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY) == null) {
            Map<String, String> hashMap = new HashMap<>();
            context.add(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY, hashMap);
        }
        Map<String, String> map = context.get(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY);
        map.put(barcode, pin);
        context.add(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY, map);
    }
}
