package stepdefinitions.holds.components;

import constants.localization.application.catalog.BookActionButtonKeys;

public interface IHoldsSteps {

    void openHolds();

    void checkHoldsFeedIsLoaded();

    void checkNoBooksArePresentInHoldsList();

    void checkBookBookInfoIsPresentInHoldsList(String bookInfoKey);

    void performActionOnHoldsScreen(BookActionButtonKeys key, String bookInfoKey);

    void checkThatSavedBookContainButtonAtHoldScreen(final String bookInfoKey, final BookActionButtonKeys key);

    void performActionOnAlert(BookActionButtonKeys key);
}
