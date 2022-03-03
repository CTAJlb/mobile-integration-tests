package hooks.logout.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.keysForContext.ContextLibrariesKeys;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import hooks.logout.components.AbstractLogoutHooks;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;
import screens.libraries.LibrariesScreen;

import java.util.List;

@StepsType(platform = PlatformName.IOS)
public class IosLogoutHooks extends AbstractLogoutHooks {
    private final AlertScreen alertScreen;

    public IosLogoutHooks(ScenarioContext context) {
        super(context);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
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
            if(alertScreen.state().waitForDisplayed()){
                alertScreen.waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys.SIGN_OUT);
            }
        }
    }
}
