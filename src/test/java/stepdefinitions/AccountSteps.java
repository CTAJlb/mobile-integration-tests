package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import enums.keysforcontext.ContextLibrariesKeys;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import enums.localization.translation.Spanish;
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

    @When("I add {string} account")
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

    @When("I click Add library button on libraries screen")
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

    @When("I add libraries through settings:")
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

    @When("I click to {string} and save library name as {string} on libraries screen")
    public void clickToLibrary(String libraryName, String libraryNameKay) {
        context.add(libraryNameKay, libraryName);
        librariesScreen.openLibrary(libraryName);
    }

    @Then("The screen with settings for {string} library is opened")
    public void isLibraryScreenOpened(String libraryNameKey) {
        String libraryName = context.get(libraryNameKey);
        Assert.assertTrue("The screen with settings of library " + libraryName + " is not opened", librariesScreen.isLibrarySettingsOpened(libraryName));
    }

    @When("I enter {string} library and save name as {string} on add account screen")
    public void enterLibName(String libraryName, String libraryNameKey) {
        context.add(libraryNameKey, libraryName);
        addAccountScreen.enterLibraryName(libraryName);
    }

    @When("I enter word {} and save as {string} on add account screen")
    public void enterData(String word, String infoKey) {
        context.add(infoKey, word);
        addAccountScreen.enterLibraryName(word);
    }

    @When("I close account screen")
    public void closeAccount(){
        accountScreen.closeAccountScreen();
    }

    @Then("Elements on add account screen are translated correctly")
    public void checkTranslationOnAddAccountScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(addAccountScreen.getTextFromBackBtn()).as("Back button is not translated").isEqualTo(Spanish.BACK.getDefaultLocalizedValue());
        softAssertions.assertThat(addAccountScreen.getTextFromAddLibraryLbl()).as("Add library label is not translated").isEqualTo(Spanish.ADD_LIBRARY.getDefaultLocalizedValue());
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

    @When("I remove {string} account")
    public void removeAccount(String libraryName) {
        openAccounts();
        librariesScreen.deleteLibrary(libraryName);
    }

    @When("I open account {string}")
    public void openAccount(String libraryName) {
        openAccounts();
        librariesScreen.openLibrary(libraryName);
    }

    @Then("Elements on Account screen are translated correctly")
    public void checkTranslationOnAccountScreen() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(accountScreen.getTextFromLibrariesBtn()).as("Libraries button is not translated").isEqualTo(Spanish.LIBRARIES.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromAccountHeader()).as("Account is not translated").isEqualTo(Spanish.ACCOUNT.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromCardTxb()).as("Library card is not translated").isEqualTo(Spanish.LIBRARY_CARD.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromPinTxb()).as("Password is not translated").isEqualTo(Spanish.PASSWORD.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromLogInButton()).as("Sign in button is not translated").isEqualTo(Spanish.LOG_IN.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromForgetPasswordLbl()).as("Forget password label is not translated").isEqualTo(Spanish.FORGET_PASSWORD.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromLicenseAgreementLink()).as("License agreement link is not translated").isEqualTo(Spanish.USER_AGREEMENT_LINK.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromNoAccountLbl()).as("No account label is not translated").isEqualTo(Spanish.NO_ACCOUNT.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromReportAboutProblemBtn()).as("Report a problem button is not translated").isEqualTo(Spanish.REPORT_A_PROBLEM.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromCreateCardBtn()).as("Create card button is not translated").isEqualTo(Spanish.CREATE_CARD.getDefaultLocalizedValue());
        softAssertions.assertThat(accountScreen.getTextFromContentLicensesLbl()).as("Content licenses label is not translated").isEqualTo(Spanish.CONTENT_LICENSES.getDefaultLocalizedValue());
        softAssertions.assertAll();
    }

    @When("I open User license agreement on account screen")
    public void openLicAgreement(){
        accountScreen.openLicenseAgreement();
    }

    @Then("User License Agreement link is opened")
    public void isUserLicAgreementOpened() {
        Assert.assertTrue("User License Agreement is not opened", accountScreen.isLinkOpened());
    }

    @When("I open Content Licenses on account screen")
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

    @When("I open Advanced on account screen")
    public void openAdvanced(){
        accountScreen.openAdvanced();
    }

    @Then("Advanced screen contains {string} button")
    public void isAdvancedContainsButton(String buttonName) {
        Assert.assertTrue("Advanced screen does not contain " + buttonName + " button", accountScreen.isButtonDisplayed(buttonName));
    }

    @When("I click {string} button and cancel it on Advanced screen")
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
