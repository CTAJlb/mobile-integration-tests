package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.localization.italian.ItalianIos;
import constants.localization.spanish.SpanishIos;
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

    @When("Open Settings in Spanish")
    public void openSettingsInSpanish() {
        bottomMenuForm.open(BottomMenu.SETTINGS_ES);
        bottomMenuForm.open(BottomMenu.SETTINGS_ES);
    }

    @When("Open Settings in Italian")
    public void openSettingsInItalian() {
        bottomMenuForm.open(BottomMenu.SETTINGS_IT);
        bottomMenuForm.open(BottomMenu.SETTINGS_IT);
    }

    @Then("Elements on Settings screen are translated correctly")
    public void checkTranslationInSettingsScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(settingsScreen.getTextFromSettingsHeader()).as("Settings header is not translated").isEqualTo(SpanishIos.SETTINGS);
        softAssertions.assertThat(settingsScreen.getTextFromLibrariesBtnES()).as("Libraries button is not translated").isEqualTo(SpanishIos.LIBRARIES);
        softAssertions.assertThat(settingsScreen.getTextFromAboutAppBtnES()).as("About palace button is not translated").isEqualTo(SpanishIos.ABOUT_PALACE);
        softAssertions.assertThat(settingsScreen.getTextFromPrivacyPolicyBtnES()).as("Privacy policy button is not translated").isEqualTo(SpanishIos.PRIVACY_POLICY);
        softAssertions.assertThat(settingsScreen.getTextFromUserAgreementBtnES()).as("User agreement button is not translated").isEqualTo(SpanishIos.USER_AGREEMENT);
        softAssertions.assertThat(settingsScreen.getTextFromSoftwareLicensesBtnES()).as("Software licenses button is not translated").isEqualTo(SpanishIos.SOFTWARE_LICENSES);
        softAssertions.assertAll();
    }

    @Then("Elements on Settings screen are translated correctly in Italian")
    public void checkTranslationInSettingsScreenIT() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(settingsScreen.getTextFromSettingsHeader()).as("Settings header is not translated").isEqualTo(ItalianIos.SETTINGS);
        softAssertions.assertThat(settingsScreen.getTextFromLibrariesBtnIT()).as("Libraries button is not translated").isEqualTo(ItalianIos.LIBRARIES);
        softAssertions.assertThat(settingsScreen.getTextFromAboutAppBtnIT()).as("About palace button is not translated").isEqualTo(ItalianIos.ABOUT_PALACE);
        softAssertions.assertThat(settingsScreen.getTextFromPrivacyPolicyBtnIT()).as("Privacy policy button is not translated").isEqualTo(ItalianIos.PRIVACY_POLICY);
        softAssertions.assertThat(settingsScreen.getTextFromUserAgreementBtnIT()).as("User agreement button is not translated").isEqualTo(ItalianIos.USER_AGREEMENT);
        softAssertions.assertThat(settingsScreen.getTextFromSoftwareLicensesBtnIT()).as("Software licenses button is not translated").isEqualTo(ItalianIos.SOFTWARE_LICENSES);
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
