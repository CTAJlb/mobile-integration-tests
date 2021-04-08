package hooks.logout.components;

import aquality.appium.mobile.application.AqualityServices;
import constants.context.ContextLibrariesKeys;
import constants.localization.application.catalog.BookActionButtonKeys;
import framework.utilities.ScenarioContext;
import io.appium.java_client.appmanagement.ApplicationState;
import screens.account.AccountScreen;
import screens.accounts.AccountsScreen;
import screens.alert.AlertScreen;
import screens.bookDetails.BookDetailsScreen;
import screens.books.BooksScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.holds.HoldsScreen;
import screens.notifications.NotificationModal;
import screens.pdfreader.PdfReaderScreen;
import screens.settings.SettingsScreen;
import stepdefinitions.BaseSteps;
import stepdefinitions.application.components.AbstractApplicationSteps;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public abstract class AbstractLogoutHooks extends BaseSteps implements ILogoutHooks {

    public static final int COUNT_OF_RETRIES = 5;
    protected final AccountScreen accountScreen;
    protected final AccountsScreen accountsScreen;
    protected final BottomMenuForm bottomMenuForm;
    protected final SettingsScreen settingsScreen;
    protected final AbstractApplicationSteps applicationSteps;
    private final AlertScreen alertScreen;

    protected ScenarioContext context;

    public AbstractLogoutHooks(ScenarioContext context) {
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        accountsScreen = AqualityServices.getScreenFactory().getScreen(AccountsScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        applicationSteps = stepsFactory.getSteps(AbstractApplicationSteps.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);

        this.context = context;
    }

    public abstract void logout();

    protected void startAppIfCrashed() {
        AqualityServices.getLogger().info("App crashed - restarting");
        AqualityServices.getApplication().getDriver().launchApp();
        alertScreen.closeModalIfPresent();
        alertScreen.closeNotNowModalIfPresent();
    }

    protected void navigateBackIfBottomMenuIsNotVisibleUntilItIs() {
        IntStream.range(0, COUNT_OF_RETRIES)
                .filter(i -> !bottomMenuForm.state().waitForDisplayed())
                .forEach(i -> applicationSteps.returnToPreviousPage());
    }
}
