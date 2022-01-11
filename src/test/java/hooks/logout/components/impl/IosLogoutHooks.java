package hooks.logout.components.impl;

import aquality.appium.mobile.application.PlatformName;
import constants.keysForContext.ContextLibrariesKeys;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import hooks.logout.components.AbstractLogoutHooks;
import screens.bottommenu.BottomMenu;

import java.util.List;

@StepsType(platform = PlatformName.IOS)
public class IosLogoutHooks extends AbstractLogoutHooks {

    public IosLogoutHooks(ScenarioContext context) {
        super(context);
    }

    @Override
    public void logout() {
        restartApp();
        List<String> listOfLibraries = context.get(ContextLibrariesKeys.LOG_OUT.getKey());
        if (listOfLibraries.size() == 0) {
            throw new RuntimeException("There are not libraries for logout");
        }
        for (String library : listOfLibraries) {
            bottomMenuForm.open(BottomMenu.SETTINGS);
            bottomMenuForm.open(BottomMenu.SETTINGS);
            settingsScreen.openLibraries();
            librariesScreen.openLibrary(library);
            if (accountScreen.isLogoutRequired()) {
                accountScreen.logOut();
            }
        }
    }
}
