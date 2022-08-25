package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.When;
import screens.testmode.TestModeScreen;

public class TestModeSteps {
    private final ScenarioContext context;
    private final TestModeScreen testModeScreen;

    @Inject
    public TestModeSteps (ScenarioContext context) {
        this.context = context;
        testModeScreen = AqualityServices.getScreenFactory().getScreen(TestModeScreen.class);
    }

    @When("Enable hidden libraries")
    public void enableHiddenLibraries() {
        testModeScreen.enableHiddenLibraries();
    }
}
