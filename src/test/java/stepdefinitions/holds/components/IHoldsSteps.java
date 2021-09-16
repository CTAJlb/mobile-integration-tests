package stepdefinitions.holds.components;

import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;

public interface IHoldsSteps {

    void openHolds();

    void checkHoldsFeedIsLoaded();

    void checkNoBooksArePresentInHoldsList();

    void checkBookBookInfoIsPresentInHoldsList(String bookInfoKey);

    void performActionOnHoldsScreen(EnumActionButtonsForBooksAndAlertsKeys bookActionButtonKey, String bookInfoKey);

    void checkThatSavedBookContainButtonAtHoldScreen(final String bookInfoKey, final EnumActionButtonsForBooksAndAlertsKeys key);

    void performActionOnBookWithoutClickActionButtonOnAlert(String bookInfoKey, EnumActionButtonsForBooksAndAlertsKeys bookActionButtonKey);
}
