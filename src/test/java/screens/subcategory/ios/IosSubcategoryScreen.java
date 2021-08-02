package screens.subcategory.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.interfaces.IElement;
import constants.localization.application.catalog.BookActionButtonKeys;
import models.android.CatalogBookModel;
import org.openqa.selenium.By;
import screens.subcategory.SubcategoryScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosSubcategoryScreen extends SubcategoryScreen {
    private static final String BOOKS_LOCATOR = "//XCUIElementTypeCell";
    private static final String BOOK_BUTTON_XPATH = BOOKS_LOCATOR + "//XCUIElementTypeButton";
    private static final String BOOK_COVER_XPATH_PATTERN = "//XCUIElementTypeStaticText[contains(@name,\"%1$s\")]";
    private static final String AUTHOR_INFO_XPATH = "//XCUIElementTypeStaticText[@name][2]";
    private static final String BOOK_NAME_XPATH =
            "//XCUIElementTypeStaticText[@name and not(.//ancestor::XCUIElementTypeButton)][1]";
    private static final String BOOK_NAME_LOCATOR_PATTERN = "//XCUIElementTypeStaticText[@name=\"%s\"]";
    public static final String BOOK_BUTTON_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_LOCATOR_PATTERN = "//XCUIElementTypeStaticText[@name=\"%s\"]/following-sibling::XCUIElementTypeOther//XCUIElementTypeButton//XCUIElementTypeStaticText[@name=\"%s\"]";
    public static final String BOOK_TITLE_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_ENDING_PART_LOCATOR_PATTERN = "/parent::XCUIElementTypeButton/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeStaticText[@name=\"%s\"]";
    private static final String AUTHOR_LABEL_LOCATOR_PATTERN = "/parent::XCUIElementTypeOther/XCUIElementTypeStaticText[2]";
    private static final int COUNT_OF_ITEMS_TO_WAIT_FOR = 3;
    private static final int MILLIS_TO_WAIT_FOR_SEARCH_LOADING = 40000;

    private final ILabel lblFirstBookName =
            getElementFactory().getLabel(By.xpath(BOOKS_LOCATOR + BOOK_NAME_XPATH), "First book name");
    private final ILabel lblFirstBookAuthor =
            getElementFactory().getLabel(By.xpath(BOOKS_LOCATOR + AUTHOR_INFO_XPATH), "First book author");

    public IosSubcategoryScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public List<String> getBooksInfo() {
        List<String> listOfNames = getElements(BOOKS_LOCATOR + BOOK_NAME_XPATH, ElementType.LABEL)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
        AqualityServices.getLogger().info("Found list of books - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining(", ")));
        return listOfNames;
    }

    @Override
    public List<String> getAllButtonsNames() {
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR + BOOK_BUTTON_XPATH);
        AqualityServices.getLogger().info("Found list of buttons names - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining("; ")));
        return listOfNames;
    }

    @Override
    public List<String> getTitlesInfo() {
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR + BOOK_NAME_XPATH);
        AqualityServices.getLogger().info("Found list of titles - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining("; ")));
        return listOfNames;
    }

    @Override
    public void openBook(CatalogBookModel bookInfo) {
        waitForPageLoading();
        String title = bookInfo.getTitle();
        By locator = By.xpath(String.format(BOOK_COVER_XPATH_PATTERN, title));
        AqualityServices.getLogger().info("Count of books to click - " + getElementFactory().findElements(locator, ElementType.LABEL).size());
        try {
            Thread.sleep(MILLIS_TO_WAIT_FOR_SEARCH_LOADING);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        getElementFactory().getButton(locator, title).click();
    }

    @Override
    public CatalogBookModel openBookWithDefiniteActionButtonAndDefiniteNameFromAPIAndGetBookInfo(String bookName, BookActionButtonKeys actionButtonKey, String bookType) {
        String titleForLocator = bookName;
        if (bookType.toLowerCase().equals("audiobook")) {
            titleForLocator = titleForLocator + ". Audiobook.";
        }

        String actionButton = "";
        if (actionButtonKey == BookActionButtonKeys.GET) {
            actionButton = "Get";
        } else if (actionButtonKey == BookActionButtonKeys.RESERVE) {
            actionButton = "Reserve";
        }
        try {
            Thread.sleep(MILLIS_TO_WAIT_FOR_SEARCH_LOADING);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String locator = String.format(BOOK_BUTTON_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_LOCATOR_PATTERN, titleForLocator, actionButton);

        ILabel lblAuthor =
                getElementFactory().getLabel(By.xpath(String.format(BOOK_NAME_LOCATOR_PATTERN, titleForLocator) + AUTHOR_LABEL_LOCATOR_PATTERN), bookName);
        CatalogBookModel bookInfo = new CatalogBookModel()
                .setTitle(bookName)
                .setAuthor(lblAuthor.getText());

        if (getElementFactory().getButton(By.xpath(locator), bookName).state().waitForDisplayed()) {
            getElementFactory().getButton(By.xpath(locator + String.format(BOOK_TITLE_WITH_DEFINITE_NAME_AND_DEFINITE_ACTION_BUTTON_ENDING_PART_LOCATOR_PATTERN, titleForLocator)), bookName).click();
        } else {
            throw new RuntimeException("There is not book with title-" + bookName + " and button-" + actionButton);
        }

        return bookInfo;
    }

    @Override
    public String getErrorMessage() {
        return null;
    }

    @Override
    public boolean isErrorButtonPresent() {
        return false;
    }

    @Override
    public List<String> getAuthorsInfo() {
        AqualityServices.getConditionalWait().waitFor(() -> getElements(BOOKS_LOCATOR + AUTHOR_INFO_XPATH, ElementType.LABEL).size() > COUNT_OF_ITEMS_TO_WAIT_FOR);
        List<String> listOfNames = getValuesFromListOfLabels(BOOKS_LOCATOR + AUTHOR_INFO_XPATH);
        AqualityServices.getLogger().info("Found list of authors - " + listOfNames.stream().map(Object::toString).collect(Collectors.joining("; ")));
        return listOfNames;
    }

    @Override
    public CatalogBookModel getFirstBookInfo() {
        return new CatalogBookModel()
                .setTitle(lblFirstBookName.getText())
                .setAuthor(lblFirstBookAuthor.getText());
    }

    @Override
    public void openFirstBook() {
        lblFirstBookName.click();
    }

    private List<String> getValuesFromListOfLabels(String xpath) {
        return getElements(xpath, ElementType.LABEL)
                .stream()
                .map(IElement::getText)
                .collect(Collectors.toList());
    }

    private List<aquality.appium.mobile.elements.interfaces.IElement> getElements(String xpath, ElementType label) {
        return getElementFactory().findElements(By.xpath(xpath), label);
    }

    private void waitForPageLoading() {
        AqualityServices.getConditionalWait().waitFor(() -> getElements(BOOK_BUTTON_XPATH, ElementType.BUTTON).size() >= COUNT_OF_ITEMS_TO_WAIT_FOR);
    }
}
