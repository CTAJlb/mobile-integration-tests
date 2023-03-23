package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.signIn.SignInScreen;

public class SignInSteps {
    private final SignInScreen signInScreen;
    private final ScenarioContext context;

    @Inject
    public SignInSteps(ScenarioContext context) {
        signInScreen = AqualityServices.getScreenFactory().getScreen(SignInScreen.class);
        this.context = context;
    }

    @Then("Sing in screen is opened")
    public void isSignInScreenOpened() {
        signInScreen.state().waitForDisplayed();
    }

    @Then("All fields and links are displayed on Sign in Screen")
    public void checkTheVisibilityOfFieldsAndLinks() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(signInScreen.isLibraryCardDisplayed()).as("Library card textbox is not displayed").isTrue();
        softAssertions.assertThat(signInScreen.isPasswordDisplayed()).as("Password textbox is not displayed").isTrue();
        softAssertions.assertThat(signInScreen.isSignInBtnDisplayed()).as("Sign in is not displayed").isTrue();
        softAssertions.assertThat(signInScreen.isLinkToTheLicenseAgreementDisplayed()).as("License agreement link is not displayed").isTrue();
        softAssertions.assertAll();
    }

    @Then("There is a placeholder Library Card in the Library Card field on Sign in screen")
    public void isPlaceholderInLibCardDisplayed() {
        Assert.assertEquals("A placeholder Library Card is not displayed", "Library Card", signInScreen.getTextFromLibraryCard());
    }

    @Then("There is a placeholder {string} in the Password field on Sign in screen")
    public void isPlaceholderInPasswordDisplayed(String placholderName) {
        Assert.assertEquals("A placeholder " + placholderName + " is not displayed", placholderName, signInScreen.getTextFromPassword());
    }

    @When("Enter a Password {string} on Sign in screen")
    public void enterPassword(String password) {
        signInScreen.enterPassword(password);
    }

    @Then("Sign in button is disabled on Sign in screen")
    public void isSignInButtonDisabled() {
        signInScreen.tapSignInBtn();
        Assert.assertFalse("Sign in screen not displayed", signInScreen.state().waitForNotDisplayed());
    }

    @When("Enter a Library card {string} on Sign in screen")
    public void enterLibCard(String libraryCard) {
        signInScreen.enterLibraryCard(libraryCard);
    }

    @When("Enter a Library card {string} and save it as {string} on Sign in screen")
    public void enterLibCardAndSave(String libraryCard, String libraryCardKey) {
        context.add(libraryCardKey, libraryCard);
        signInScreen.enterLibraryCard(libraryCard);
    }

    @When("Edit data by adding {string} in Library card field and save it as {string} on sign in screen")
    public void editLibraryCard(String addedData, String newLibCardKey) {
        signInScreen.deleteSomeDataInLibCard();
        signInScreen.deleteSomeDataInLibCard();
        signInScreen.setTextInLibCard(addedData);
        context.add(newLibCardKey, signInScreen.getTextFromLibraryCard());
    }

    @Then("There is a placeholder {string} in the Library Card field on Sign in screen")
    public void isNewPlaceholderDisplayed(String placeholderKey) {
        Assert.assertEquals("New placeholder is not displayed", context.get(placeholderKey), signInScreen.getTextFromLibraryCard());
    }
}
