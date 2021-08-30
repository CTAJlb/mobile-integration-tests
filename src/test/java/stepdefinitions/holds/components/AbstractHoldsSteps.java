package stepdefinitions.holds.components;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import framework.utilities.ScenarioContext;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.alert.AlertScreen;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.holds.HoldsScreen;
import stepdefinitions.BaseSteps;

public abstract class AbstractHoldsSteps extends BaseSteps implements IHoldsSteps {
    protected final BottomMenuForm bottomMenuForm;
    protected final HoldsScreen holdsScreen;
    protected final ScenarioContext context;
    protected final AlertScreen alertScreen;

    public AbstractHoldsSteps(ScenarioContext context) {
        this.context = context;
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        holdsScreen = AqualityServices.getScreenFactory().getScreen(HoldsScreen.class);
        alertScreen = AqualityServices.getScreenFactory().getScreen(AlertScreen.class);
    }

    @Override
    public void openHolds() {
        bottomMenuForm.open(BottomMenu.HOLDS);
    }

    @Override
    public void checkHoldsFeedIsLoaded() {
        Assert.assertTrue("Holds feed is not loaded", holdsScreen.state().waitForDisplayed());
    }

    @Override
    public void checkNoBooksArePresentInHoldsList() {
        Assert.assertTrue("Books are present in Holds list", holdsScreen.isNoBooksMessagePresent());
    }

    public abstract void checkBookBookInfoIsPresentInHoldsList(String bookInfoKey);

    @Override
    public void performActionOnHoldsScreen(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey, String bookInfoKey) {
        performActionOnBookWithoutClickActionButtonOnAlert(bookInfoKey, actionButtonKey);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            alertScreen.performAlertActionIfDisplayed(actionButtonKey);
        }
    }

    @Override
    public void performActionOnBookWithoutClickActionButtonOnAlert(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys bookActionButtonKey) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        holdsScreen.performActionOnBook(catalogBookModel.getTitle(), bookActionButtonKey);
    }

    @Override
    public void checkThatSavedBookContainButtonAtHoldScreen(
            final String bookInfoKey, final EnumActionButtonsForBooksAndAlertsKeys key) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String title = catalogBookModel.getTitle();
        Assert.assertTrue(String.format("Book with title '%1$s' button does not contain text '%2$s'", title, key.i18n()), holdsScreen.isActionButtonPresentOnBook(title, key));
    }

    public abstract void checkBookBookInfoIsNotPresentInHoldsList(String bookInfoKey);
}
