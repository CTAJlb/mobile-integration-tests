package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.holds.HoldsScreen;

public class HoldsSteps {
    protected final BottomMenuForm bottomMenuForm;
    protected final HoldsScreen holdsScreen;
    protected final ScenarioContext context;
    protected final AlertScreen alertScreen;

    @Inject
    public HoldsSteps(ScenarioContext context) {
        this.context = context;
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        holdsScreen = AqualityServices.getScreenFactory().getScreen(HoldsScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @When("I open Holds")
    public void openHolds() {
        bottomMenuForm.open(BottomMenu.HOLDS);
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
        Assert.assertTrue(String.format("'%s' book with specific action button is not present on holds screen", bookName),
                holdsScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @And("{} book with {} action button and {string} bookInfo is not present on holds screen")
    public void isBookNotPresent(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        Assert.assertFalse(String.format("'%s' book with specific action button is present on holds screen", bookName),
                holdsScreen.isBookDisplayed(bookType, bookName, actionButtonKey));
    }

    @When("Open {} book with {} action button and {string} bookInfo on holds screen")
    public void openBook(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        CatalogBookModel bookInfo = context.get(bookInfoKey);
        String bookName = bookInfo.getTitle();
        holdsScreen.openBook(bookType, bookName, actionButtonKey);
    }
}