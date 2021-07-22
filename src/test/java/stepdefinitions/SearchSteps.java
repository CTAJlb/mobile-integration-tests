package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import framework.utilities.feedXMLUtil.GettingBookUtil;
import framework.utilities.feedXMLUtil.XMLUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.search.modal.SearchModal;
import screens.subcategory.SubcategoryScreen;

import javax.inject.Inject;

public class SearchSteps {
    private final MainCatalogToolbarForm mainCatalogToolbarForm;
    private final SearchModal searchModal;
    private final SubcategoryScreen subcategoryScreen;
    private ScenarioContext context;

    @Inject
    public SearchSteps(ScenarioContext context) {
        this.context = context;
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        searchModal = AqualityServices.getScreenFactory().getScreen(SearchModal.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
    }

    @When("I open search modal")
    public void openSearchModal() {
        mainCatalogToolbarForm.openSearchModal();
        searchModal.state().waitForDisplayed();
    }

    @Then("Search modal is opened")
    public void checkSearchModalIsOpened() {
        Assert.assertTrue("Search modal is not loaded", searchModal.state().waitForDisplayed());
    }

    @When("I search for {string}")
    public void searchFor(String searchedText) {
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().waitForDisplayed());
        searchModal.setSearchedText(searchedText);
        searchModal.applySearch();
        Assert.assertTrue("Search modal is not disappear", searchModal.state().waitForNotDisplayed());
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", searchedText, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }

    @When("I search random pdf and save as {string}")
    public void searchForPdf(String bookNameInfoKey) {
        String pdfForSearching = GettingBookUtil.getRandomPdf();
        AqualityServices.getLogger().info("randomPdf: " + pdfForSearching);
        context.add(bookNameInfoKey, pdfForSearching);

        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().waitForDisplayed());
        searchModal.setSearchedText(pdfForSearching);
        searchModal.applySearch();
        Assert.assertTrue("Search modal is not disappear", searchModal.state().waitForNotDisplayed());
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", pdfForSearching, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }
}
