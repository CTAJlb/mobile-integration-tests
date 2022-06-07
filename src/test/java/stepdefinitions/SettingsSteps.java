package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.aboutpalace.AboutPalaceScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.privacypolicy.PrivacyPolicyScreen;
import screens.settings.SettingsScreen;
import screens.softwarelicenses.SoftwareLicScreen;
import screens.useragreement.UserAgreementScreen;

public class SettingsSteps {

    private ScenarioContext context;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private final AboutPalaceScreen aboutPalaceScreen;
    private final PrivacyPolicyScreen privacyPolicyScreen;
    private final UserAgreementScreen userAgreementScreen;
    private final SoftwareLicScreen softwareLicScreen;

    @Inject
    public SettingsSteps (ScenarioContext context) {
        this.context = context;
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        aboutPalaceScreen = AqualityServices.getScreenFactory().getScreen(AboutPalaceScreen.class);
        privacyPolicyScreen = AqualityServices.getScreenFactory().getScreen(PrivacyPolicyScreen.class);
        userAgreementScreen = AqualityServices.getScreenFactory().getScreen(UserAgreementScreen.class);
        softwareLicScreen = AqualityServices.getScreenFactory().getScreen(SoftwareLicScreen.class);
    }

    @When("I open Settings")
    public void openSettings() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        bottomMenuForm.open(BottomMenu.SETTINGS);
    }

    @Then("Settings screen is opened")
    public void isSettingsScreenOpened(){
        Assert.assertTrue("Settings screen is not opened", settingsScreen.isSettingsScreenOpened());
    }

    @When("I open Libraries on settings screen")
    public void openLibraries() {
        settingsScreen.openLibraries();
    }

    @When("I open {string} library on setting screen")
    public void openLibrary(String libraryName) {
        settingsScreen.openLibrary(libraryName);
    }


    @When("I open About Palace on settings screen")
    public void openAboutPalace() {
        settingsScreen.openAboutPalace();
    }

    @Then("About Palace screen is opened")
    public void aboutPalaceIsPresent() {
        aboutPalaceScreen.isOpened();
    }

    @When("I open Privacy Policy on settings screen")
    public void openPrivacyPolicy() {
        settingsScreen.openPrivacyPolicy();
    }

    @Then("Privacy Policy screen is opened")
    public void privacyPolicyIsOpened() {
        privacyPolicyScreen.isOpened();
    }

    @When("I open User Agreement on settings screen")
    public void openUserAgreement () {
        settingsScreen.openUserAgreement();
    }

    @Then("User Agreement screen is opened")
    public void userAgreementIsOpened() {
        userAgreementScreen.isOpened();
    }

    @When("I scroll page to link {string} on user agreement screen")
    public void scrollToLinkOnAgreement(String link) {
        userAgreementScreen.scrollThePage(link);
    }

    @Then("Link {string} is available on user agreement screen")
    public void isLinkAvailable(String link) {
        Assert.assertTrue("Link is not available", userAgreementScreen.isLinkAvailable(link));
    }

    @When("I open Software Licenses on settings screen")
    public void openSoftwareLic() {
        settingsScreen.openSoftwareLic();
    }

    @Then("Software Licenses screen is opened")
    public void softwareLicIsOpened() {
        softwareLicScreen.isOpened();
    }

    @When("I scroll page to link {string} on software licenses screen")
    public void scrollToLinkOnLic(String link) {
        softwareLicScreen.scrollThePage(link);
    }

    @Then("Link {string} is available on software licenses screen")
    public void isLinkClickable(String link) {
        Assert.assertTrue("Link is not available", softwareLicScreen.isLinkAvailable(link));
    }
}
