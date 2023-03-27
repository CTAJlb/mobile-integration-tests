package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import com.google.inject.Inject;
import constants.localization.spanish.SpanishIos;
import enums.EnumBookType;
import enums.localization.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.facetedSearch.FacetedSearchScreen;
import screens.holds.HoldsScreen;

import java.util.List;
import java.util.stream.Collectors;

public class HoldsSteps {
    protected final BottomMenuForm bottomMenuForm;
    protected final HoldsScreen holdsScreen;
    protected final ScenarioContext context;
    protected final AlertScreen alertScreen;
    protected final FacetedSearchScreen facetedSearchScreen;

    @Inject
    public HoldsSteps(ScenarioContext context) {
        this.context = context;
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        holdsScreen = AqualityServices.getScreenFactory().getScreen(HoldsScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
        facetedSearchScreen = AqualityServices.getScreenFactory().getScreen(FacetedSearchScreen.class);
    }

    @When("Open Holds")
    public void openHolds() {
        bottomMenuForm.open(BottomMenu.HOLDS);
    }

    @When("I open Holds in Spanish")
    public void openHoldsInSpanish(){
        bottomMenuForm.open(BottomMenu.HOLD_ES);
    }

    @Then("Elements on Holds screen are translated correctly")
    public void checkTranslationOnHoldsScreen(){
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(holdsScreen.getTextFromHoldsHeader()).as("Holds header is not translated").isEqualTo(SpanishIos.RESERVATIONS);
        softAssertions.assertThat(holdsScreen.getTextFromInformationLbl()).as("Information label is not translated").isEqualTo(SpanishIos.RESERVE_INFO);
        softAssertions.assertAll();
    }

    @Then("Holds screen is loaded")
    public void isHoldsOpened() {
        Assert.assertTrue("Reservations screen is not opened", holdsScreen.isHoldsScreenOpened());
    }

    @And("There are not books on holds screen")
    public void areBooksNotPresent() {
        Assert.assertTrue("Books are present on holds screen", holdsScreen.isNoBooksMessagePresent());
    }

    @And("{} book with {} action button and {string} bookInfo is present on holds screen")
    public void isBookPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && bookType == EnumBookType.AUDIOBOOK) {
            bookName = bookName + ". Audiobook.";
        }
        Assert.assertTrue(String.format("'%s' book with specific action button is not present on holds screen", bookName),
                holdsScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @And("{} book with {} action button and {string} bookInfo is not present on holds screen")
    public void isBookNotPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && bookType == EnumBookType.AUDIOBOOK) {
            bookName = bookName + ". Audiobook.";
        }
        Assert.assertFalse(String.format("'%s' book with specific action button is present on holds screen", bookName),
                holdsScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @When("Open {} book with {} action button and {string} bookInfo on holds screen")
    public void openBook(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS && bookType == EnumBookType.AUDIOBOOK) {
            bookName = bookName + ". Audiobook.";
        }
        holdsScreen.openBook(bookType, bookName, actionButtonKey);
    }

    @Then("Books are sorted by Title by default on holds screen")
    public void checkDefaultSorting() {
        Assert.assertEquals("Book are not sorting by default", "Title", holdsScreen.getNameOfSorting());
    }

    @Then("Books are sorted by Title ascending on holds screen")
    public void areBooksSortedByTitleOnHolds() {
        List<String> titlesList = holdsScreen.getListOfTitles();
        Assert.assertEquals("Books are not sorted by title", titlesList.stream().sorted().collect(Collectors.toList()), titlesList);
    }

    @Then("Books are sorted by Author ascending on holds screen")
    public void areBooksSortedByAuthorOnHolds() {
        List<String> authorsList = holdsScreen.getListOfAuthors();
        Assert.assertEquals("Books are not sorted by author", authorsList.stream().sorted().collect(Collectors.toList()), authorsList);
    }

    @Then("There are sorting by {string} and {string} in {string} on holds screen")
    public void checkTypeOfSorting(String title, String author, String library) {
        holdsScreen.sortBy();
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(title)).as("There is no sorting by " + title).isEqualTo(title);
        softAssertions.assertThat(facetedSearchScreen.getTypeVariantsOfBtn(author)).as("There is no sorting by " + author).isEqualTo(author);
    }
}
