package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import framework.utilities.ScenarioContext;
import framework.utilities.feedXMLUtil.GettingBookUtil;
import io.cucumber.java.en.And;
import org.junit.Assert;
import screens.search.modal.SearchModal;
import screens.subcategory.SubcategoryScreen;

import javax.inject.Inject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @And("Search {string} book of distributor {string} and bookType {string} from {string} and save as {string}")
    public void searchFor(String availabilityType, String distributor, String bookType, String library, String bookNameInfoKey) {
        String bookName = getRandomBookNameWithoutBadSymbols(availabilityType, distributor, bookType);
        AqualityServices.getLogger().info("randomBookName: " + bookName);
        context.add(bookNameInfoKey, bookName);
        Assert.assertTrue("Search modal is not present. Error (if present) - " + subcategoryScreen.getErrorMessage(), searchModal.state().waitForDisplayed());
        searchModal.setSearchedText(bookName);
        searchModal.applySearch();
        Assert.assertTrue(String.format("Search results page for value '%s' is not present. Error (if present) - %s", bookName, subcategoryScreen.getErrorMessage()), subcategoryScreen.state().waitForDisplayed());
    }

    private String getRandomBookNameWithoutBadSymbols(String availabilityType, String distributor, String bookType) {
        int amount = 0;
        String bookName = null;

        while (amount < 15) {
            bookName = GettingBookUtil.getRandomBook(availabilityType.toLowerCase(), bookType.toLowerCase(), distributor.toLowerCase());
            Pattern pattern = Pattern.compile("[^\\w :]");
            Matcher matcher = pattern.matcher(bookName);
            amount++;
            if (!matcher.find()) {
                break;
            }
        }
        AqualityServices.getLogger().info("Count of attempts to get random book name without bad symbols-" + amount);

        return bookName;
    }
}
