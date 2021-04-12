package hooks;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.context.ScenarioContextKey;
import framework.utilities.ScenarioContext;
import framework.utilities.apiUtil.APIUtil;
import io.cucumber.java.After;

import java.util.Map;

public class ApiHooks {
    private ScenarioContext context;

    @Inject
    public ApiHooks(ScenarioContext scenarioContext) {
        context = scenarioContext;
    }

    @After(value = "@returnBooks", order = 3)
    public void returnBooks() {
        AqualityServices.getLogger().info("Test finished - returning books");
        Map<String, String> map = context.get(ScenarioContextKey.lIST_OF_CREDENTIALS_KEY);
        for (Map.Entry<String, String> m : map.entrySet()) {
            APIUtil.returnBooks(m.getKey(), m.getValue());
            APIUtil.enterBooksAfterReturningBooks(m.getKey(), m.getValue());
        }
    }
}
