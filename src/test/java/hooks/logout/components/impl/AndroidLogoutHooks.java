package hooks.logout.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import enums.timeouts.AuthorizationTimeouts;
import enums.keysforcontext.ContextLibrariesKeys;
import enums.localization.account.AccountScreenLoginStatus;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import hooks.logout.components.AbstractLogoutHooks;
import screens.bottommenu.BottomMenu;

import java.time.Duration;
import java.util.Collections;
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
        if (listOfLibraries.size() == 0) {
            throw new RuntimeException("There are not libraries for logout");
        }
        for (String library : listOfLibraries) {
            bottomMenuForm.open(BottomMenu.SETTINGS);
            bottomMenuForm.open(BottomMenu.SETTINGS);
            settingsScreen.openLibraries();
            librariesScreen.openLibrary(library);
            if (accountScreen.isLogoutRequired()) {
                final String cardTextBeforeLogout = accountScreen.getTextFromCardTxb();
                final String pinTextBeforeLogout = accountScreen.getTextFromPinTxb();
                accountScreen.tapLogOut();
                AqualityServices.getConditionalWait().waitFor(() ->
                        accountScreen.getTextFromLogInButton().equals(AccountScreenLoginStatus.SIGN_IN.i18n())
                            && !accountScreen.getTextFromCardTxb().equals(cardTextBeforeLogout)
                            && !accountScreen.getTextFromPinTxb().equals(pinTextBeforeLogout),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getTimeoutMillis()),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getPollingMillis()),
                        Collections.singletonList(NoSuchMethodException.class));
            }
        }
    }
}
