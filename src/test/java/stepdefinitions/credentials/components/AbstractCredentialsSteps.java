package stepdefinitions.credentials.components;

import aquality.appium.mobile.application.AqualityServices;
import constants.context.ScenarioContextKey;
import framework.configuration.Configuration;
import framework.configuration.Credentials;
import framework.utilities.ScenarioContext;
import framework.utilities.apiUtil.APIUtil;
import org.testng.Assert;
import screens.account.AccountScreen;
import screens.accounts.AccountsScreen;
import screens.subcategory.SubcategoryScreen;
import stepdefinitions.BaseSteps;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCredentialsSteps extends BaseSteps implements ICredentialsSteps {
    protected final AccountScreen accountScreen;
    private final AccountsScreen accountsScreen;
    protected final SubcategoryScreen subcategoryScreen;
    private ScenarioContext context;

    public AbstractCredentialsSteps(ScenarioContext context) {
        this.context = context;
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        accountsScreen = AqualityServices.getScreenFactory().getScreen(AccountsScreen.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
    }

    public void textOnLogoutButtonIsChangedToLogInOnAccountScreen() {
        Assert.assertTrue(accountScreen.isLogoutSuccessful(), "Text on Login button is not changed to Log out on Account screen");
    }

    public void clickLogOut() {
        accountScreen.logOut();
    }

    public void enterCredentialsForLibraryAccount(String libraryName) {
        accountsScreen.openAccount(libraryName);
        Credentials credentials = Configuration.getCredentials(libraryName);
        storeCredentials(credentials);
        accountScreen.enterCredentials(credentials);
    }

    private void storeCredentials(Credentials credentials) {
        String barcode = credentials.getBarcode();
        String pin = credentials.getPin();
        AqualityServices.getLogger().info("There are books on the account: ");
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
