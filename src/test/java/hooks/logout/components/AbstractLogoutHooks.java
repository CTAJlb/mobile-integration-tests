package hooks.logout.components;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import screens.account.AccountScreen;
import screens.libraries.LibrariesScreen;
import screens.bottommenu.BottomMenuForm;
import screens.settings.SettingsScreen;
import stepdefinitions.BaseSteps;
import stepdefinitions.application.components.AbstractApplicationSteps;

public abstract class AbstractLogoutHooks extends BaseSteps implements ILogoutHooks {

    protected final AccountScreen accountScreen;
    protected final LibrariesScreen librariesScreen;
    protected final BottomMenuForm bottomMenuForm;
    protected final SettingsScreen settingsScreen;
    protected final AbstractApplicationSteps applicationSteps;
    protected ScenarioContext context;

    public AbstractLogoutHooks(ScenarioContext context) {
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        librariesScreen = AqualityServices.getScreenFactory().getScreen(LibrariesScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        applicationSteps = stepsFactory.getSteps(AbstractApplicationSteps.class);
        this.context = context;
    }

    public abstract void logout();

    public abstract void logoutES();

    public abstract void logoutIT();

    protected void restartApp() {
        AqualityServices.getApplication().getDriver().closeApp();
        AqualityServices.getApplication().getDriver().launchApp();
    }
}
