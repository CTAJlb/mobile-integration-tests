package stepdefinitions.holds.components;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import constants.localization.application.catalog.BookActionButtonKeys;
import framework.utilities.ScenarioContext;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
import screens.holds.HoldsScreen;
import screens.notifications.NotificationModal;
import stepdefinitions.BaseSteps;

public abstract class AbstractHoldsSteps extends BaseSteps implements IHoldsSteps {
    protected final BottomMenuForm bottomMenuForm;
    protected final HoldsScreen holdsScreen;
    protected final NotificationModal notificationModal;
    protected final ScenarioContext context;

    public AbstractHoldsSteps(ScenarioContext context) {
        this.context = context;
        bottomMenuForm = AqualityServices.getScreenFactory().getScreen(BottomMenuForm.class);
        holdsScreen = AqualityServices.getScreenFactory().getScreen(HoldsScreen.class);
        notificationModal = AqualityServices.getScreenFactory().getScreen(NotificationModal.class);
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
    public void performActionOnHoldsScreen(BookActionButtonKeys key, String bookInfoKey) {
        clickOnBookAddButtonOnHoldsScreenWithoutPopupHandling(bookInfoKey, key);
        if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
            notificationModal.performActionOnAlert(key);
        }
    }

    public void clickOnBookAddButtonOnHoldsScreenWithoutPopupHandling(String bookInfoKey, BookActionButtonKeys key) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        holdsScreen.performActionOnBook(catalogBookModel.getTitle(), key);
    }

    @Override
    public void performActionOnAlert(BookActionButtonKeys bookActionButtonKeys) {
        notificationModal.performActionOnAlert(bookActionButtonKeys);
    }

    @Override
    public void checkThatSavedBookContainButtonAtHoldScreen(
            final String bookInfoKey, final BookActionButtonKeys key) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String title = catalogBookModel.getTitle();
        Assert.assertTrue(String.format("Book with title '%1$s' button does not contain text '%2$s'", title, key.i18n()), holdsScreen.isActionButtonPresentOnBook(title, key));
    }

    public abstract void checkBookBookInfoIsNotPresentInHoldsList(String bookInfoKey);
}
