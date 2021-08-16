package stepdefinitions.application;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.context.ContextLibrariesKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import stepdefinitions.BaseSteps;
import stepdefinitions.application.components.AbstractApplicationSteps;
import stepdefinitions.application.components.IApplicationSteps;

import java.util.ArrayList;
import java.util.List;

public class ApplicationSteps extends BaseSteps implements IApplicationSteps {
    public static final String IOS_APP_BUNDLE_ID = "org.nypl.labs.SimplyE";
    public static final String GET_CURRENT_PACKAGE_COMMAND = "getCurrentPackage";
    private AbstractApplicationSteps applicationSteps;
    private ScenarioContext context;

    @Inject
    public ApplicationSteps(ScenarioContext context) {
        this.context = context;
        this.applicationSteps = stepsFactory.getSteps(AbstractApplicationSteps.class);
    }

    @And("I return to previous screen for epub and pdf")
    public void returnToPreviousScreenForEpubAndPdf() {
        applicationSteps.returnToPreviousScreenForEpubAndPdf();
    }

    @When("I restart app")
    public void restartApp() {
        applicationSteps.restartApp();
    }

    @Given("I add {string} account from welcomeScreen")
    public void addAccountFromWelcomeScreen(String libraryName) {
        saveLibraryForLogOut(libraryName);
        applicationSteps.addAccountFromWelcomeScreen(libraryName);
    }

    private String getBundleId() {
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
            return (String) AqualityServices.getApplication().getDriver().execute(GET_CURRENT_PACKAGE_COMMAND).getValue();
        }
        return IOS_APP_BUNDLE_ID;
    }

    private void saveLibraryForLogOut(String libraryName){
        if(libraryName.toLowerCase().equals("LYRASIS".toLowerCase())){
            saveLibraryInContext(ContextLibrariesKeys.LOG_OUT.getKey(), libraryName);
        }
    }

    private void saveLibraryInContext(String key, String libraryName) {
        List<String> listOfLibraries = context.containsKey(key)
                ? context.get(key)
                : new ArrayList<>();

        listOfLibraries.add(libraryName);
        context.add(key, listOfLibraries);
    }
}
