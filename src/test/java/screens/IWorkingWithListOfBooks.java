package screens;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElementFactory;
import constants.application.EnumBookType;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import org.openqa.selenium.By;

public interface IWorkingWithListOfBooks {

    default IButton getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(EnumBookType bookType, String bookName,
                                                                                                                  EnumActionButtonsForBooksAndAlertsKeys actionButtonKey,
                                                                                                                  String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC,
                                                                                                                  String SPECIFIC_BOOK_NAME_LOC) {
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookName = bookName + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        IButton actionButton = elementFactory.getButton(By.xpath(String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString)), "Action Button");
        if (!actionButton.state().waitForDisplayed()) {
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        actionButton.state().waitForDisplayed();
        return elementFactory.getButton(By.xpath(String.format(SPECIFIC_BOOK_NAME_LOC, bookName)), bookName);
    }

    default IButton getSpecificActionButtonForBookWithSpecificTypeAndSpecificNameFromListOfBooks(EnumBookType bookType, String bookName,
                                                                                                 EnumActionButtonsForBooksAndAlertsKeys actionButtonKey,
                                                                                                 String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC) {
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookName = bookName + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        IElementFactory elementFactory = AqualityServices.getElementFactory();
        IButton actionButton = elementFactory.getButton(By.xpath(String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString)), "Action Button");
        if (!actionButton.state().waitForDisplayed()) {
            actionButton.getTouchActions().scrollToElement(SwipeDirection.DOWN);
        }
        actionButton.state().waitForDisplayed();
        return actionButton;
    }
}
