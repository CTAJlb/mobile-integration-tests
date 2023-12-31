package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.localization.french.FrenchIos;
import constants.localization.italian.ItalianIos;
import constants.localization.spanish.SpanishIos;
import enums.keysforcontext.ContextLibrariesKeys;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import framework.utilities.swipe.SwipeElementUtils;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.account.AccountScreen;
import screens.alert.AlertScreen;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.libraries.LibrariesScreen;
import screens.addaccount.AddAccountScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.settings.SettingsScreen;

import java.util.ArrayList;
import java.util.List;

public class AccountSteps {
    private final LibrariesScreen librariesScreen;
    private final BottomMenuForm bottomMenuForm;
    private final SettingsScreen settingsScreen;
    private final AddAccountScreen addAccountScreen;
    private final CatalogScreen catalogScreen;
    private final AccountScreen accountScreen;
    private final AlertScreen alertScreen;
    private final ScenarioContext context;

    @Inject
    public AccountSteps(ScenarioContext context) {
        this.context = context;
        librariesScreen = AqualityServices.getScreenFactory().getScreen(LibrariesScreen.class);
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        settingsScreen = AqualityServices.getScreenFactory().getScreen(SettingsScreen.class);
        addAccountScreen = AqualityServices.getScreenFactory().getScreen(AddAccountScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
        accountScreen = AqualityServices.getScreenFactory().getScreen(AccountScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @When("Add {string} account")
    public void addAccount(String libraryName) {
        openAccounts();
        librariesScreen.addLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);

        if(libraryName.equalsIgnoreCase("LYRASIS Reads")){
            saveLibraryInContext(ContextLibrariesKeys.LOG_OUT.getKey(), libraryName);
        }
        catalogScreen.state().waitForDisplayed();
    }

    @Then("Button Add Library is displayed on libraries screen")
    public void isBtnAddLibDisplayed() {
        Assert.assertTrue("Add library button is not displayed", librariesScreen.isAddLibraryBtnDisplayed());
    }

    @When("Click Add library button on libraries screen")
    public void clickAddLibrary() {
        librariesScreen.addLibrary();
    }

    @Then("Add library screen is opened")
    public void isAddLibraryScreenOpened() {
        Assert.assertTrue("Add Library screen is not opened", addAccountScreen.isAddLibScreenOpened());
    }

    @Then("Libraries are sorted in alphabetical order on add account screen")
    public void isSortingCorrect() {
        Assert.assertTrue("Libraries are not sorted in alphabetical order", addAccountScreen.isSortingOfLibrariesCorrect());
    }

    @When("Add libraries through settings:")
    public void addSeveralLibraries(List<String > libraries) {
        libraries.forEach(library -> {
            openAccounts();
            librariesScreen.addLibrary();
            addAccountScreen.selectLibraryViaSearch(library);
            catalogScreen.state().waitForDisplayed();
        });
    }

    @Then("Libraries are sorted in alphabetical order on libraries screen")
    public void isLibrariesSorted() {
        Assert.assertTrue("Libraries are not sorted in alphabetical order", librariesScreen.isLibrariesAreSorted());
    }

    @When("Click to {string} and save library name as {string} on libraries screen")
    public void clickToLibrary(String libraryName, String libraryNameKay) {
        context.add(libraryNameKay, libraryName);
        librariesScreen.openLibrary(libraryName);
    }

    @Then("The screen with settings for {string} library is opened")
    public void isLibraryScreenOpened(String libraryNameKey) {
        String libraryName = context.get(libraryNameKey);
        Assert.assertTrue("The screen with settings of library " + libraryName + " is not opened", librariesScreen.isLibrarySettingsOpened(libraryName));
    }

    @When("Add library {string} on Add library screen")
    public void addLibrary(String libraryName) {
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }

    @When("Enter {string} library and save name as {string} on add account screen")
    public void enterLibName(String libraryName, String libraryNameKey) {
        context.add(libraryNameKey, libraryName);
        addAccountScreen.enterLibraryName(libraryName);
    }

    @When("Enter word {} and save as {string} on add account screen")
    public void enterData(String word, String infoKey) {
        context.add(infoKey, word);
        addAccountScreen.enterLibraryName(word);
    }

    @When("Close account screen")
    public void closeAccount(){
        accountScreen.closeAccountScreen();
    }

    @Then("Elements on add account screen are translated correctly")
    public void checkTranslationOnAddAccountScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(addAccountScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(SpanishIos.BACK);
        softAssertions.assertThat(addAccountScreen.getTextFromAddLibraryLbl()).as("Add library label is not translated").isEqualTo(SpanishIos.ADD_ACCOUNT);
        softAssertions.assertAll();
    }

    @Then("Elements on add account screen are translated correctly in Italian")
    public void checkTranslationOnAddAccountScreenIT() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(addAccountScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(ItalianIos.BACK);
        softAssertions.assertThat(addAccountScreen.getTextFromAddLibraryLbl()).as("Add library label is not translated").isEqualTo(ItalianIos.ADD_ACCOUNT);
        softAssertions.assertAll();
    }

    @Then("Elements on add account screen are translated correctly in French")
    public void checkTranslationOnAddAccountScreenFR() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(addAccountScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(FrenchIos.BACK);
        softAssertions.assertThat(addAccountScreen.getTextFromAddLibraryLbl()).as("Add library label is not translated").isEqualTo(FrenchIos.ADD_ACCOUNT);
        softAssertions.assertAll();
    }

    @Then("Library {string} is present on add account screen")
    public void isLibraryPresent(String libraryNameKey) {
        String libraryName = context.get(libraryNameKey);
        Assert.assertTrue(libraryName + " not found", addAccountScreen.isLibraryPresent(libraryName));
    }

    @When("Clear search field on add account screen")
    public void clickDeleteBtn() {
        addAccountScreen.clearSearchField();
    }

    @Then("Search field is empty on add account screen")
    public void isSearchFieldEmpty() {
        Assert.assertTrue("Search field is not empty", addAccountScreen.isSearchFieldEmpty());
    }

    @Then("Libraries contain word {string} on add account screen")
    public void isLibraryContainWord(String info) {
        String word = context.get(info);
        Assert.assertTrue("Search result does not contain libraries with " + word, addAccountScreen.isLibraryContainWord(word));
    }

    @Then("Search result is empty on add account screen")
    public void isSearchResultEmpty() {
        Assert.assertTrue("Search result contains data", addAccountScreen.isSearchResultEmpty());
    }

    @Then("Account {string} is present on Accounts screen")
    public void checkAccountIsPresent(String libraryName) {
        openAccounts();
        Assert.assertTrue(libraryName + " is not present on Accounts screen", librariesScreen.isLibraryPresent(libraryName));
    }

    @Then("Account {string} is not present on Accounts screen")
    public void checkAccountIsNotPresent(String libraryName) {
        Assert.assertFalse(libraryName + " is present on Accounts screen", librariesScreen.isLibraryPresent(libraryName));
    }

    @When("Remove {string} account")
    public void removeAccount(String libraryName) {
        openAccounts();
        librariesScreen.deleteLibrary(libraryName);
    }

    @When("Open account {string}")
    public void openAccount(String libraryName) {
        openAccounts();
        librariesScreen.openLibrary(libraryName);
    }

    @Then("Elements on Account screen are translated correctly")
    public void checkTranslationOnAccountScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(accountScreen.getTextFromLibrariesBtn()).as("Libraries button is not translated").isEqualTo(SpanishIos.LIBRARIES);
        softAssertions.assertThat(accountScreen.getTextFromAccountHeader()).as("Account is not translated").isEqualTo(SpanishIos.ACCOUNT);
        softAssertions.assertThat(accountScreen.getTextFromCardTxb()).as("Library card is not translated").isEqualTo(SpanishIos.LIBRARY_CARD);
        softAssertions.assertThat(accountScreen.getTextFromPinTxb()).as("Password is not translated").isEqualTo(SpanishIos.PASSWORD);
        softAssertions.assertThat(accountScreen.getTextFromLogInButtonES()).as("Sign in button is not translated").isEqualTo(SpanishIos.SIGN_IN);
        softAssertions.assertThat(accountScreen.getTextFromForgetPasswordLbl()).as("Forget password label is not translated").isEqualTo(SpanishIos.FORGET_PASSWORD);
        softAssertions.assertThat(accountScreen.getTextFromLicenseAgreementLink()).as("License agreement link is not translated").isEqualTo(SpanishIos.USER_AGREEMENT_LINK);
        softAssertions.assertThat(accountScreen.getTextFromNoAccountLbl()).as("No account label is not translated").isEqualTo(SpanishIos.NO_ACCOUNT);
        softAssertions.assertThat(accountScreen.getTextFromReportAboutProblemBtn()).as("Report a problem button is not translated").isEqualTo(SpanishIos.REPORT_A_PROBLEM);
        softAssertions.assertThat(accountScreen.getTextFromContentLicensesLbl()).as("Content licenses label is not translated").isEqualTo(SpanishIos.CONTENT_LICENSES);
        softAssertions.assertAll();
    }

    @Then("Elements on Account screen are translated correctly in Italian")
    public void checkTranslationOnAccountScreenIT() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(accountScreen.getTextFromLibrariesBtn()).as("Libraries button is not translated").isEqualTo(ItalianIos.LIBRARIES);
        softAssertions.assertThat(accountScreen.getTextFromAccountHeader()).as("Account is not translated").isEqualTo(ItalianIos.ACCOUNT);
        softAssertions.assertThat(accountScreen.getTextFromCardTxb()).as("Library card is not translated").isEqualTo(ItalianIos.LIBRARY_CARD);
        softAssertions.assertThat(accountScreen.getTextFromPinTxb()).as("Password is not translated").isEqualTo(ItalianIos.PASSWORD);
        softAssertions.assertThat(accountScreen.getTextFromLogInButtonIT()).as("Sign in button is not translated").isEqualTo(ItalianIos.SIGN_IN);
        softAssertions.assertThat(accountScreen.getTextFromForgetPasswordLbl()).as("Forget password label is not translated").isEqualTo(ItalianIos.FORGET_PASSWORD);
        softAssertions.assertThat(accountScreen.getTextFromLicenseAgreementLink()).as("License agreement link is not translated").isEqualTo(ItalianIos.USER_AGREEMENT_LINK);
        softAssertions.assertThat(accountScreen.getTextFromReportAboutProblemBtn()).as("Report a problem button is not translated").isEqualTo(ItalianIos.REPORT_A_PROBLEM);
        softAssertions.assertThat(accountScreen.getTextFromContentLicensesLbl()).as("Content licenses label is not translated").isEqualTo(ItalianIos.CONTENT_LICENSES);
        softAssertions.assertAll();
    }

    @When("Open User license agreement on account screen")
    public void openLicAgreement(){
        accountScreen.openLicenseAgreement();
    }

    @Then("User License Agreement link is opened")
    public void isUserLicAgreementOpened() {
        Assert.assertTrue("User License Agreement is not opened", accountScreen.isLinkOpened());
    }

    @When("Open Content Licenses on account screen")
    public void openAccountLicenses() {
        if(AqualityServices.getApplication().getPlatformName()== PlatformName.ANDROID) {
            SwipeElementUtils.swipeByCoordinatesOfWindow();
        }
        accountScreen.openContentLicenses();
    }

    @Then("Content Licenses screen is opened")
    public void isContentLicensesOpened() {
        Assert.assertTrue("Content Licenses is not opened", accountScreen.isContentLicOpened());
    }

    @When("Open Advanced on account screen")
    public void openAdvanced(){
        accountScreen.openAdvanced();
    }

    @Then("Advanced screen contains {string} button")
    public void isAdvancedContainsButton(String buttonName) {
        Assert.assertTrue("Advanced screen does not contain " + buttonName + " button", accountScreen.isButtonDisplayed(buttonName));
    }

    @When("Click {string} button and cancel it on Advanced screen")
    public void clickDelete(String buttonName) {
        accountScreen.clickDelete(buttonName);
        if(alertScreen.state().waitForDisplayed()) {
            alertScreen.waitAndPerformAlertActionIfDisplayed(EnumActionButtonsForBooksAndAlertsKeys.CANCEL);
        }
    }

    private void saveLibraryInContext(String key, String libraryName) {
        List<String> listOfLibraries = context.containsKey(key)
                ? context.get(key)
                : new ArrayList<>();

        listOfLibraries.add(libraryName);
        context.add(key, listOfLibraries);
    }

    private void openAccounts() {
        bottomMenuForm.open(BottomMenu.SETTINGS);
        bottomMenuForm.open(BottomMenu.SETTINGS);
        settingsScreen.openLibraries();
    }
}
