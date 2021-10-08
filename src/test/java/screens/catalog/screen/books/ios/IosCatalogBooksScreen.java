package screens.catalog.screen.books.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.EnumBookType;
import constants.application.attributes.IosAttributes;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.IWorkingWithListOfBooks;
import screens.catalog.screen.books.CatalogBooksScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosCatalogBooksScreen extends CatalogBooksScreen implements IWorkingWithListOfBooks {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeCollectionView";
    private static final String SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC = "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeButton";
    private static final String SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC = SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC + "/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
    private static final String AUTHOR_ON_BOOK_WITH_SPECIFIC_NAME_AND_SPECIFIC_ACTION_BUTTON_LOC = SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC + "/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[2]";

    private static final String SPECIFIC_ACTION_BUTTON_ON_THE_FIRST_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC = "//XCUIElementTypeStaticText/following-sibling::XCUIElementTypeOther//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeButton";
    private static final String THE_FIRST_BOOK_NAME_WITH_SPECIFIC_ACTION_BUTTON_LOC = SPECIFIC_ACTION_BUTTON_ON_THE_FIRST_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC + "/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[1]";
    private static final String AUTHOR_ON_THE_FIRST_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC = SPECIFIC_ACTION_BUTTON_ON_THE_FIRST_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC + "/parent::XCUIElementTypeOther/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[2]";

    public IosCatalogBooksScreen() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public CatalogBookModel clickSpecificActionButtonOnBookWithSpecificTypeAndSpecificNameAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookName = bookName + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString);
        IButton bookNameButton = getActionButtonFromListOfBooks(actionButtonLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_ON_BOOK_WITH_SPECIFIC_NAME_AND_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString)), "lblAuthor");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getText());
        bookNameButton.click();
        return bookInfo;
    }

    @Override
    public CatalogBookModel openBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonAndGetBookInfo(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookName = bookName + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString);
        String bookNameLoc = String.format(SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString);
        IButton bookNameButton = getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(actionButtonLoc, bookNameLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_ON_BOOK_WITH_SPECIFIC_NAME_AND_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString)), "lblAuthor");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getText());
        bookNameButton.click();
        return bookInfo;
    }

    @Override
    public boolean isBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonPresent(EnumBookType bookType, String bookName, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        if (EnumBookType.AUDIOBOOK == bookType) {
            bookName = bookName + ". Audiobook.";
        }
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(SPECIFIC_ACTION_BUTTON_ON_BOOK_WITH_SPECIFIC_NAME_LOC, bookName, actionButtonString);
        String bookNameLoc = String.format(SPECIFIC_BOOK_NAME_ON_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, bookName, actionButtonString);
        return getBookNameButtonForBookWithSpecificTypeAndSpecificNameAndSpecificActionButtonFromListOfBooks(actionButtonLoc, bookNameLoc).state().waitForDisplayed();
    }

    @Override
    public CatalogBookModel clickSpecificActionButtonOnTheFirstBookWithSpecificActionButtonAndGetBookInfo(EnumBookType bookType, EnumActionButtonsForBooksAndAlertsKeys actionButtonKey) {
        String actionButtonString = actionButtonKey.i18n();
        String actionButtonLoc = String.format(SPECIFIC_ACTION_BUTTON_ON_THE_FIRST_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, actionButtonString);
        IButton bookNameButton = getActionButtonFromListOfBooks(actionButtonLoc);
        ILabel lblAuthor = getElementFactory().getLabel(By.xpath(String.format(AUTHOR_ON_THE_FIRST_BOOK_WITH_SPECIFIC_ACTION_BUTTON_LOC, actionButtonString)), "lblAuthor");
        ILabel lblBookName = getElementFactory().getLabel(By.xpath(String.format(THE_FIRST_BOOK_NAME_WITH_SPECIFIC_ACTION_BUTTON_LOC, actionButtonString)), "lblBookName");
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(lblBookName.getAttribute(IosAttributes.NAME))
                .setAuthor(lblAuthor.getAttribute(IosAttributes.NAME));
        bookNameButton.click();
        return bookInfo;
    }
}
