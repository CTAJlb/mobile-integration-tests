package hooks.logout.components.impl;

import aquality.appium.mobile.application.PlatformName;
import constants.context.ContextLibrariesKeys;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import hooks.logout.components.AbstractLogoutHooks;
import screens.bottommenu.BottomMenu;

import java.util.List;

@StepsType(platform = PlatformName.ANDROID)
public class AndroidLogoutHooks extends AbstractLogoutHooks {

    public AndroidLogoutHooks(ScenarioContext context) {
        super(context);
    }

    @Override
    public void logout() {
        restartApp();
        List<String> listOfLibraries = context.get(ContextLibrariesKeys.LOG_OUT.getKey());
        for (String library : listOfLibraries) {
            if (!accountScreen.state().waitForDisplayed()) {
                bottomMenuForm.open(BottomMenu.SETTINGS);
                settingsScreen.openAccounts();
                accountsScreen.openLibraryAccount(library);
            }
            if (accountScreen.isLogoutRequired()) {
                accountScreen.logOut();
            }
        }
    }
}
