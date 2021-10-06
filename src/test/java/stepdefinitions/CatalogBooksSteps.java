package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import screens.catalog.screen.books.CatalogBooksScreen;

public class CatalogBooksSteps {
    private final CatalogBooksScreen catalogBooksScreen;
    private ScenarioContext context;

    @Inject
    public CatalogBooksSteps(ScenarioContext context) {
        this.context = context;
        catalogBooksScreen = AqualityServices.getScreenFactory().getScreen(CatalogBooksScreen.class);
    }

    @When("Open {} book with {} action button and {string} bookName on catalog books screen and save book as {string}")
    public void openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndSaveBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        CatalogBookModel bookInfo = catalogBooksScreen.openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(bookType, bookName, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
    }

    @When("Click {} action button on {} book with {string} bookName on catalog books screen and save book as {string}")
    public void clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndSaveBookInfo(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, EnumBookType bookType, String bookNameKey, String bookInfoKey) {
        String bookName = context.get(bookNameKey);
        CatalogBookModel bookInfo = catalogBooksScreen.clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(bookType, bookName, actionButtonKey);
        context.add(bookInfoKey, bookInfo);
    }
}
