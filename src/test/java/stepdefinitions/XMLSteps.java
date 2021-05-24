package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import framework.utilities.feedXMLUtil.XMLUtil;
import io.cucumber.java.en.And;
import org.junit.Assert;
import screens.search.modal.SearchModal;
import screens.subcategory.SubcategoryScreen;

import javax.inject.Inject;

public class XMLSteps {
    private final SearchModal searchModal;
    private final SubcategoryScreen subcategoryScreen;
    private ScenarioContext context;

    @Inject
    public XMLSteps(ScenarioContext context) {
        this.context = context;
        searchModal = AqualityServices.getScreenFactory().getScreen(SearchModal.class);
        subcategoryScreen = AqualityServices.getScreenFactory().getScreen(SubcategoryScreen.class);
    }

    @And("I search {string} book of distributor {string} and bookType {string} and save as {string}")
    public void searchFor(String availabilityType, String distributor, String bookType, String bookNameInfoKey) {
        String bookName = XMLUtil.getInstance().getRandomBook(availabilityType.toLowerCase(), bookType.toLowerCase(), distributor.toLowerCase());
        AqualityServices.getLogger().info("randomBookName: " + bookName);
        context.add(bookNameInfoKey, bookName);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().waitForDisplayed());
        searchModal.setSearchedText(bookName);
        searchModal.applySearch();
        Assert.assertTrue("Search modal is not disappear", searchModal.state().waitForNotDisplayed());
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", bookName, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }
}
