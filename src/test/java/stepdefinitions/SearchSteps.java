package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import framework.utilities.feedXMLUtil.GettingBookUtil;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import screens.catalog.form.MainCatalogToolbarForm;
import screens.catalog.screen.catalog.CatalogScreen;
import screens.search.modal.SearchModal;
import screens.subcategory.SubcategoryScreen;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchSteps {
    private final MainCatalogToolbarForm mainCatalogToolbarForm;
    private final SearchModal searchModal;
    private final SubcategoryScreen subcategoryScreen;
    private final CatalogScreen catalogScreen;
    private ScenarioContext context;

    @Inject
    public SearchSteps(ScenarioContext context) {
        this.context = context;
        mainCatalogToolbarForm = AqualityServices.getScreenFactory().getScreen(MainCatalogToolbarForm.class);
        searchModal = AqualityServices.getScreenFactory().getScreen(SearchModal.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
        catalogScreen = AqualityServices.getScreenFactory().getScreen(CatalogScreen.class);
    }

    @When("I open search modal")
    public void openSearchModal() {
        catalogScreen.state().waitForDisplayed();
        mainCatalogToolbarForm.openSearchModal();
        searchModal.state().waitForDisplayed();
    }

    @Then("Search modal is opened")
    public void checkSearchModalIsOpened() {
        Assert.assertTrue("Search modal is not loaded", searchModal.state().waitForDisplayed());
    }

    @When("I search for {string} and save bookName as {string}")
    public void searchFor(String searchedText, String bookNameInfoKey) {
        context.add(bookNameInfoKey, searchedText);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().isDisplayed());
        searchModal.setSearchedText(searchedText);
        searchModal.applySearch();
        Assert.assertTrue("Search modal is not disappear", searchModal.state().waitForNotDisplayed());
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", searchedText, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }

    @When("I search random pdf and save as {string}")
    public void searchForPdf(String bookNameInfoKey) {
        String pdfForSearching = getRandomPdfWithoutBadSymbols();
        AqualityServices.getLogger().info("randomPdf: " + pdfForSearching);
        context.add(bookNameInfoKey, pdfForSearching);

        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().waitForDisplayed());
        searchModal.setSearchedText(pdfForSearching);
        searchModal.applySearch();
        Assert.assertTrue("Search modal is not disappear", searchModal.state().waitForNotDisplayed());
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", pdfForSearching, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }


    private String getRandomPdfWithoutBadSymbols() {
        int amount = 0;
        String pdfName = null;

        while (amount < 15) {
            pdfName = GettingBookUtil.getRandomPdf();
            Pattern pattern = Pattern.compile("[^\\w :]");
            Matcher matcher = pattern.matcher(pdfName);
            amount++;
            if (!matcher.find()) {
                break;
            }
        }
        AqualityServices.getLogger().info("Count of attempts to get random pdf name without bad symbols-" + amount);

        return pdfName;
    }
}
