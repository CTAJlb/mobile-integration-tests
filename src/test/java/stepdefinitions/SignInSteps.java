package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import org.assertj.core.api.SoftAssertions;
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
}
