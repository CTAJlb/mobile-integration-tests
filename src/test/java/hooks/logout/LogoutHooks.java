package hooks.logout;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import hooks.logout.components.AbstractLogoutHooks;
import hooks.logout.components.ILogoutHooks;
import io.cucumber.java.After;
import stepdefinitions.BaseSteps;

public class LogoutHooks extends BaseSteps implements ILogoutHooks {

    private final AbstractLogoutHooks abstractLogoutHooks;

    @Inject
    public LogoutHooks(ScenarioContext context) {
        abstractLogoutHooks = stepsFactory.getSteps(AbstractLogoutHooks.class, context);
    }

    @Override
    @After(value = "@logout", order = 3)
    public void logout() {
        AqualityServices.getLogger().info("Test finished - logging out");
        abstractLogoutHooks.logout();
    }

    @Override
    @After(value = "@logoutES", order = 3)
    public void logoutES() {
        AqualityServices.getLogger().info("Test finished - logging out");
        abstractLogoutHooks.logoutES();
    }

    @Override
    @After(value = "@logoutIT", order = 3)
    public void logoutIT() {
        AqualityServices.getLogger().info("Test finished - logging out");
        abstractLogoutHooks.logoutIT();
    }
}
