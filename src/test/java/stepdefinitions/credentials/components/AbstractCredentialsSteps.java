package stepdefinitions.credentials.components;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.keysForContext.ScenarioContextKey;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.configuration.Configuration;
import framework.configuration.Credentials;
import framework.utilities.ScenarioContext;
import framework.utilities.returningBooksUtil.APIUtil;
import org.junit.Assert;
import screens.account.AccountScreen;
import screens.libraries.LibrariesScreen;
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
    private final LibrariesScreen librariesScreen;
    protected final SubcategoryScreen subcategoryScreen;
    protected final CatalogScreen catalogScreen;
    protected final AlertScreen alertScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private ScenarioContext context;

    public AbstractCredentialsSteps(ScenarioContext context) {
        this.context = context;
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        librariesScreen = AqualityServices.getScreenFactory().getScreen(LibrariesScreen.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
    }

    public void isLogoutSuccessfully() {
        Assert.assertTrue("Text on Login button is not changed to Log out on Account screen", accountScreen.isLogoutSuccessful());
    }

    public void clickLogOut() {
        accountScreen.tapLogOut();
        accountScreen.tapApproveSignOut();
        if(alertScreen.state().waitForDisplayed()){
            alertScreen.waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys.SIGN_OUT);
        }
    }

    public void enterCredentialsForLibraryAccount(String libraryName) {
        openAccounts();
        librariesScreen.openLibrary(libraryName);
        Credentials credentials = Configuration.getCredentials(libraryName);
        storeCredentials(credentials);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
            AqualityServices.getApplication().getDriver().switchTo().alert().dismiss();
            AqualityServices.getLogger().info("Alert appears and dismiss alert");
        }
        accountScreen.enterCredentialsAndLogin(credentials);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && alertScreen.state().waitForDisplayed()) {
           alertScreen.waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys.NOT_NOW);
        }
        boolean isLoginPerformedSuccessfully = AqualityServices.getConditionalWait().waitFor(() -> accountScreen.isLoginSuccessful() || catalogScreen.state().isDisplayed());
        if (!isLoginPerformedSuccessfully) {
            throw new RuntimeException("Login is not completed");
        }
    }

    private void openAccounts() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openLibraries();
    }

    private void storeCredentials(Credentials credentials) {
        String barcode = credentials.getBarcode();
        String pin = credentials.getPin();
        APIUtil.enterBookAfterOpeningAccount(credentials);
        if (context.get(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY) == null) {
            Map<String, String> hashMap = new HashMap<>();
            context.add(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY, hashMap);
        }
        Map<String, String> map = context.get(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY);
        map.put(barcode, pin);
        context.add(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY, map);
    }
}
