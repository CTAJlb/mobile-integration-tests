package stepdefinitions.application.components;

import aquality.appium.mobile.application.AqualityServices;
import screens.addaccount.AddAccountScreen;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.eulaagreement.EulaAgreementScreen;
import screens.welcome.WelcomeScreen;
import stepdefinitions.BaseSteps;

public abstract class AbstractApplicationSteps extends BaseSteps implements IApplicationSteps {
    protected final EulaAgreementScreen eulaAgreementScreen;
    protected final WelcomeScreen welcomeScreen;
    protected final AddAccountScreen addAccountScreen;
    protected final CatalogScreen catalogScreen;

    public AbstractApplicationSteps() {
        eulaAgreementScreen = AqualityServices.getScreenFactory().getScreen(EulaAgreementScreen.class);
        welcomeScreen = AqualityServices.getScreenFactory().getScreen(WelcomeScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
    }

    public abstract void returnToPreviousScreenForEpubAndPdf();

    public abstract void addAccountFromWelcomeScreen(String libraryName);

    @Override
    public void restartApp() {
        AqualityServices.getApplication().getDriver().closeApp();
        AqualityServices.getApplication().getDriver().launchApp();
        catalogScreen.state().waitForDisplayed();
    }
}
