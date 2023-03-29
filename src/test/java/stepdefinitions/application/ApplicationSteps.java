package stepdefinitions.application;

import com.google.inject.Inject;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import stepdefinitions.BaseSteps;
import stepdefinitions.application.components.AbstractApplicationSteps;
import stepdefinitions.application.components.IApplicationSteps;

import java.util.List;

public class ApplicationSteps extends BaseSteps implements IApplicationSteps {
    private final AbstractApplicationSteps abstractApplicationSteps;
    private final ScenarioContext context;

    @Inject
    public ApplicationSteps(ScenarioContext context) {
        this.context = context;
        this.abstractApplicationSteps = stepsFactory.getSteps(AbstractApplicationSteps.class);
    }

    @When("Turn on test mode")
    public void turnOnTestMode() {
        abstractApplicationSteps.turnOnTestMode();
    }

    @And("Return to previous screen for epub and pdf")
    public void returnToPreviousScreenForEpubAndPdf() {
        abstractApplicationSteps.returnToPreviousScreenForEpubAndPdf();
    }

    @When("Restart app")
    public void restartApp() {
        abstractApplicationSteps.restartApp();
    }

    @When("Add {string} account by the logo")
    public void addAccountByTheLogo(String libraryName) {
        abstractApplicationSteps.addAccountByTheLogo(libraryName);
    }

    @When("Tap the logo on catalog screen")
    public void tapTheLogo() {
        abstractApplicationSteps.tapTheLogo();
    }

    @Then("Elements on Find your library screen are translated correctly")
    @Override
    public void checkFindYourLibScreenTranslation() {
        abstractApplicationSteps.checkFindYourLibScreenTranslation();
    }

    @When("Add libraries by the logo:")
    public void addSevLibraries(List<String> libraries){
        libraries.forEach(abstractApplicationSteps::addAccountByTheLogo
        );
    }

    @When("Save {int} amount as {string}")
    public void addAmountOfLibraries(int listSize, String sizeKey) {
        context.add(sizeKey, listSize);
    }

    @Then("The sorting of {string} libraries is alphabetical on find your library screen")
    public void isSortingCorrect(String amountKey) {
        int amount = context.get(amountKey);
        Assert.assertTrue("The list of libraries is not in alphabetical ored", abstractApplicationSteps.isSortingInAlphabetical(amount));
    }

    @When("Tap cancel button on find your library screen")
    public void tapCloseBtn() {
        abstractApplicationSteps.tapCancelBtn();
    }

    @When("Choose {string} library on find your library screen")
    public void chooseLibrary(String libName) {
        abstractApplicationSteps.tapToLibrary(libName);
    }

    @Then("Tutorial screen is opened")
    @Override
    public void checkThatTutorialScreenIsOpened() {
        abstractApplicationSteps.checkThatTutorialScreenIsOpened();
    }

    @When("Close tutorial screen")
    @Override
    public void closeTutorialScreen() {
        abstractApplicationSteps.closeTutorialScreen();
    }

    @When("Close welcome screen")
    @Override
    public void closeWelcomeScreen() {
        abstractApplicationSteps.closeWelcomeScreen();
    }

    @When("Close welcome screen in Spanish")
    @Override
    public void closeWelcomeScreenInSpanish() {
        abstractApplicationSteps.closeWelcomeScreenInSpanish();
    }

    @Then("Welcome screen is opened")
    @Override
    public void checkWelcomeScreenIsOpened() {
        abstractApplicationSteps.checkWelcomeScreenIsOpened();
    }

    @Then("Welcome screen is opened in Spanish")
    @Override
    public void isWelcomeScreenOpenedInSpanish() {
        abstractApplicationSteps.isWelcomeScreenOpenedInSpanish();
    }

    @Then("Elements on welcome screen are translated correctly")
    @Override
    public void checkTranslationOnWelcomeScreen(){
        abstractApplicationSteps.checkTranslationOnWelcomeScreen();
    }

    @And("Wait for {int} seconds")
    @Override
    public void waitSeveralSeconds(Integer secondsCount) {
        abstractApplicationSteps.waitSeveralSeconds(secondsCount);
    }

    @Then("Each tutorial page can be opened on tutorial screen for android and close tutorial screen")
    @Override
    public void checkEachTutorialPageCanBeOpened() {
        abstractApplicationSteps.checkEachTutorialPageCanBeOpened();
    }

    @Then("There is a menu bar at the bottom of the screen")
    public void isBottomMenuDisplayed() {
        Assert.assertTrue("Bottom menu is not displayed", abstractApplicationSteps.isMenuBarDisplayed());
    }

    @Then("There are tabs {string}, {string} and {string} on application screen")
    public void areTabsExists(String tab1, String tab2, String tab3) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab1)).as("There is no " + tab1 + " tab").isEqualTo(tab1);
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab2)).as("There is no " + tab2 + " tab").isEqualTo(tab2);
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab3)).as("There is no " + tab3 + " tab").isEqualTo(tab3);
    }

    @Then("There are tabs {string}, {string}, {string} and {string} on application screen")
    public void areTabInLyrExists(String tab1, String tab2, String tab3, String tab4) {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab1)).as("There is no " + tab1 + " tab").isEqualTo(tab1);
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab2)).as("There is no " + tab2 + " tab").isEqualTo(tab2);
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab3)).as("There is no " + tab3 + " tab").isEqualTo(tab3);
        softAssertions.assertThat(abstractApplicationSteps.getTypeOfButton(tab4)).as("There is no " + tab4 + " tab").isEqualTo(tab4);
    }
}
