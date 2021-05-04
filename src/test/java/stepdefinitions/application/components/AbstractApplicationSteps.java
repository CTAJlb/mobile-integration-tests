package stepdefinitions.application.components;

import aquality.appium.mobile.application.AqualityServices;
import screens.addaccount.AddAccountScreen;
import screens.eulaagreement.EulaAgreementScreen;
import screens.welcome.WelcomeScreen;
import stepdefinitions.BaseSteps;

public abstract class AbstractApplicationSteps extends BaseSteps implements IApplicationSteps {
    protected final EulaAgreementScreen eulaAgreementScreen;
    protected final WelcomeScreen welcomeScreen;
    protected final AddAccountScreen addAccountScreen;

    public AbstractApplicationSteps() {
        eulaAgreementScreen = AqualityServices.getScreenFactory().getScreen(EulaAgreementScreen.class);
        welcomeScreen = AqualityServices.getScreenFactory().getScreen(WelcomeScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
    }

    public abstract void openApplication();

    public abstract void returnToPreviousPage();

    public abstract void openApplicationVar2(String libraryName);

    @Override
    public void restartApp() {
        AqualityServices.getApplication().getDriver().closeApp();
        AqualityServices.getApplication().getDriver().launchApp();
    }
}
