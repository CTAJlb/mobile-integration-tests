package hooks.logout.components;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import screens.account.AccountScreen;
import screens.accounts.AccountsScreen;
import screens.bottommenu.BottomMenuForm;
import screens.settings.SettingsScreen;
import stepdefinitions.BaseSteps;
import stepdefinitions.application.components.AbstractApplicationSteps;

public abstract class AbstractLogoutHooks extends BaseSteps implements ILogoutHooks {

    protected final AccountScreen accountScreen;
    protected final AccountsScreen accountsScreen;
    protected final BottomMenuForm bottomMenuForm;
    protected final SettingsScreen settingsScreen;
    protected final AbstractApplicationSteps applicationSteps;
    protected ScenarioContext context;

    public AbstractLogoutHooks(ScenarioContext context) {
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        accountsScreen = AqualityServices.getScreenFactory().getScreen(AccountsScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        applicationSteps = stepsFactory.getSteps(AbstractApplicationSteps.class);
        this.context = context;
    }

    public abstract void logout();

    protected void restartApp() {
        AqualityServices.getApplication().getDriver().closeApp();
        AqualityServices.getApplication().getDriver().launchApp();
    }
}
