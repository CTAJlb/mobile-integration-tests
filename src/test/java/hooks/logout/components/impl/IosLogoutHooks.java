package hooks.logout.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.localization.italian.ItalianIos;
import constants.localization.spanish.SpanishIos;
import enums.timeouts.AuthorizationTimeouts;
import enums.keysforcontext.ContextLibrariesKeys;
import enums.localization.account.AccountScreenLoginStatus;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import factories.steps.StepsType;
import framework.utilities.ScenarioContext;
import hooks.logout.components.AbstractLogoutHooks;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;

import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
                final String cardTextBeforeLogout = accountScreen.getTextFromCardTxb();
                final String pinTextBeforeLogout = accountScreen.getTextFromPinTxb();
                accountScreen.tapLogOut();
                accountScreen.tapApproveSignOut();
                if(alertScreen.state().waitForDisplayed()){
                    alertScreen.waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys.SIGN_OUT);
                }
                AqualityServices.getConditionalWait().waitFor(() ->
                        accountScreen.getTextFromLogInButton().equals(AccountScreenLoginStatus.SIGN_IN.getDefaultLocalizedValue())
                            && !accountScreen.getTextFromCardTxb().equals(cardTextBeforeLogout)
                            && !accountScreen.getTextFromPinTxb().equals(pinTextBeforeLogout),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getTimeoutMillis()),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getPollingMillis()),
                        Collections.singletonList(NoSuchElementException.class));
            }
        }
    }

    @Override
    public void logoutES() {
        restartApp();
        List<String> listOfLibraries = context.get(ContextLibrariesKeys.LOG_OUT.getKey());
        if (listOfLibraries.size() == 0) {
            throw new RuntimeException("There are not libraries for logout");
        }
        for (String library: listOfLibraries) {
            bottomMenuForm.open(BottomMenu.SETTINGS_ES);
            bottomMenuForm.open(BottomMenu.SETTINGS_ES);
            settingsScreen.openLibrariesES();
            librariesScreen.openLibrary(library);
            if (accountScreen.isLogoutRequiredES()) {
                final String cardTextBeforeLogout = accountScreen.getTextFromCardTxb();
                final String pinTextBeforeLogout = accountScreen.getTextFromPinTxb();
                accountScreen.tapLogOutES();
                accountScreen.tapApproveSignOutES();
                if(alertScreen.state().waitForDisplayed()){
                    alertScreen.performAlertActionIfDisplayedInSpanish(SpanishIos.SIGN_OUT);
                }
                AqualityServices.getConditionalWait().waitFor(() ->
                                accountScreen.getTextFromLogInButtonES().equals(SpanishIos.SIGN_IN)
                                        && !accountScreen.getTextFromCardTxb().equals(cardTextBeforeLogout)
                                        && !accountScreen.getTextFromPinTxb().equals(pinTextBeforeLogout),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getTimeoutMillis()),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getPollingMillis()),
                        Collections.singletonList(NoSuchElementException.class));
            }
        }
    }

    @Override
    public void logoutIT() {
        restartApp();
        List<String> listOfLibraries = context.get(ContextLibrariesKeys.LOG_OUT.getKey());
        if (listOfLibraries.size() == 0) {
            throw new RuntimeException("There are not libraries for logout");
        }
        for (String library: listOfLibraries) {
            bottomMenuForm.open(BottomMenu.SETTINGS_IT);
            bottomMenuForm.open(BottomMenu.SETTINGS_IT);
            settingsScreen.openLibrariesIT();
            librariesScreen.openLibrary(library);
            if (accountScreen.isLogoutRequiredIT()) {
                final String cardTextBeforeLogout = accountScreen.getTextFromCardTxb();
                final String pinTextBeforeLogout = accountScreen.getTextFromPinTxb();
                accountScreen.tapLogOutIT();
                accountScreen.tapApproveSignOutIT();
                if(alertScreen.state().waitForDisplayed()){
                    alertScreen.performAlertActionIfDisplayedInItalian(ItalianIos.SIGN_OUT);
                }
                AqualityServices.getConditionalWait().waitFor(() ->
                                accountScreen.getTextFromLogInButtonIT().equals(ItalianIos.SIGN_IN)
                                        && !accountScreen.getTextFromCardTxb().equals(cardTextBeforeLogout)
                                        && !accountScreen.getTextFromPinTxb().equals(pinTextBeforeLogout),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getTimeoutMillis()),
                        Duration.ofMillis(AuthorizationTimeouts.USER_LOGGED_OUT.getPollingMillis()),
                        Collections.singletonList(NoSuchElementException.class));
            }
        }
    }
}