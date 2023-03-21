package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import enums.localization.translation.Spanish;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
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

    @When("Open Settings")
    public void openSettings() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        bottomMenuForm.open(BottomMenu.SETTINGS);
    }

    @When("I open Settings in Spanish")
    public void openSettingsInSpanish() {
        bottomMenuForm.open(BottomMenu.SETTINGS_ES);
        bottomMenuForm.open(BottomMenu.SETTINGS_ES);
    }

    @Then("Elements on Settings screen are translated correctly")
    public void checkTranslationInSettingsScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(settingsScreen.getTextFromSettingsHeader()).as("Settings header is not translated").isEqualTo(Spanish.SETTINGS.getDefaultLocalizedValue());
        softAssertions.assertThat(settingsScreen.getTextFromLibrariesBtn()).as("Libraries button is not translated").isEqualTo(Spanish.LIBRARIES.getDefaultLocalizedValue());
        softAssertions.assertThat(settingsScreen.getTextFromAboutAppBtn()).as("About palace button is not translated").isEqualTo(Spanish.ABOUT_PALACE.getDefaultLocalizedValue());
        softAssertions.assertThat(settingsScreen.getTextFromPrivacyPolicyBtn()).as("Privacy policy button is not translated").isEqualTo(Spanish.PRIVACY_POLICY.getDefaultLocalizedValue());
        softAssertions.assertThat(settingsScreen.getTextFromUserAgreementBtn()).as("User agreement button is not translated").isEqualTo(Spanish.USER_AGREEMENT.getDefaultLocalizedValue());
        softAssertions.assertThat(settingsScreen.getTextFromSoftwareLicensesBtn()).as("Software licenses button is not translated").isEqualTo(Spanish.SOFTWARE_LICENSES.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @Then("Settings screen is opened")
    public void isSettingsScreenOpened(){
        Assert.assertTrue("Settings screen is not opened", settingsScreen.isSettingsScreenOpened());
    }

    @When("Open Libraries on settings screen")
    public void openLibraries() {
        settingsScreen.openLibraries();
    }

    @When("Open {string} library on setting screen")
    public void openLibrary(String libraryName) {
        settingsScreen.openLibrary(libraryName);
    }


    @When("Open About Palace on settings screen")
    public void openAboutPalace() {
        settingsScreen.openAboutPalace();
    }

    @Then("About Palace screen is opened")
    public void aboutPalaceIsPresent() {
        aboutPalaceScreen.isOpened();
    }

    @When("Open Privacy Policy on settings screen")
    public void openPrivacyPolicy() {
        settingsScreen.openPrivacyPolicy();
    }

    @Then("Privacy Policy screen is opened")
    public void privacyPolicyIsOpened() {
        privacyPolicyScreen.isOpened();
    }

    @When("Open User Agreement on settings screen")
    public void openUserAgreement () {
        settingsScreen.openUserAgreement();
    }

    @Then("User Agreement screen is opened")
    public void userAgreementIsOpened() {
        userAgreementScreen.isOpened();
    }

    @When("Scroll page to link {string} on user agreement screen")
    public void scrollToLinkOnAgreement(String link) {
        userAgreementScreen.scrollThePage(link);
    }

    @Then("Link {string} is available on user agreement screen")
    public void isLinkAvailable(String link) {
        Assert.assertTrue("Link is not available", userAgreementScreen.isLinkAvailable(link));
    }

    @When("Open Software Licenses on settings screen")
    public void openSoftwareLic() {
        settingsScreen.openSoftwareLic();
    }

    @Then("Software Licenses screen is opened")
    public void softwareLicIsOpened() {
        softwareLicScreen.isOpened();
    }

    @When("Scroll page to link {string} on software licenses screen")
    public void scrollToLinkOnLic(String link) {
        softwareLicScreen.scrollThePage(link);
    }

    @Then("Link {string} is available on software licenses screen")
    public void isLinkClickable(String link) {
        Assert.assertTrue("Link is not available", softwareLicScreen.isLinkAvailable(link));
    }
}
