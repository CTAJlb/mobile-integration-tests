package screens.epub.bookmarksEpub.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.attributes.IosAttributes;
import framework.utilities.swipe.SwipeElementUtils;
import org.openqa.selenium.By;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosBookmarksEpubScreen extends BookmarksEpubScreen {
    private static final String BOOKMARK_TITLE_LOC = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]";
    private static final String BOOKMARK_DATE_TIME_LOC = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[2]";

    private final IButton btnDelete =
            getElementFactory().getButton(By.name("Delete"), "btnDelete");

    public IosBookmarksEpubScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    @Override
    public List<String> getListOfBookmarkTitles() {
        return getListOfILableOfBookmarkTitles().stream().map(label -> label.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
    }

    private List<ILabel> getListOfILableOfBookmarkTitles() {
        return getElementFactory().findElements(By.xpath(BOOKMARK_TITLE_LOC), ElementType.LABEL);
    }

    @Override
    public List<String> getListOfBookmarkTimeDates() {
        return getListOfILableOfBookmarkDates().stream().map(label -> label.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
    }

    private List<ILabel> getListOfILableOfBookmarkDates() {
        return getElementFactory().findElements(By.xpath(BOOKMARK_DATE_TIME_LOC), ElementType.LABEL);
    }

    @Override
    public void deleteBookmark(int bookmarkNumber) {
        ILabel lblBookmark = getListOfILableOfBookmarkTitles().get(bookmarkNumber);
        SwipeElementUtils.swipeElementLeft(lblBookmark);
        btnDelete.click();
    }

    @Override
    public void openBookmark(int bookmarkNumber) {
        getListOfILableOfBookmarkTitles().get(bookmarkNumber).click();
    }

    @Override
    public boolean isBookmarkPresent(String expectedBookmarkTitle, String bookmarkDateTime) {
        LocalDateTime expectedLocalDateTime = getExpectedLocalDateTime(bookmarkDateTime);
        AqualityServices.getLogger().info("expected bookmark info: ");
        AqualityServices.getLogger().info("expected bookmark title-" + expectedBookmarkTitle);
        AqualityServices.getLogger().info("actual year-" + expectedLocalDateTime.getYear());
        AqualityServices.getLogger().info("actual month-" + expectedLocalDateTime.getMonthValue());
        AqualityServices.getLogger().info("actual dayNumber-" + expectedLocalDateTime.getDayOfMonth());
        AqualityServices.getLogger().info("actual min-" + expectedLocalDateTime.getMinute());
        AqualityServices.getLogger().info("actual hour-" + expectedLocalDateTime.getHour());
        boolean isBookmarkPresent = false;
        for (int i = 0; i < getListOfBookmarkTitles().size(); i++) {
            String actualBookmarkTitle = getListOfBookmarkTitles().get(i);
            LocalDateTime actualLocalDateTime = getActualLocalDateTime(getListOfBookmarkTimeDates().get(i));
            AqualityServices.getLogger().info("bookmark number " + i + " info: ");
            AqualityServices.getLogger().info("actual bookmark title-" + actualBookmarkTitle);
            AqualityServices.getLogger().info("actual year-" + actualLocalDateTime.getYear());
            AqualityServices.getLogger().info("actual month-" + actualLocalDateTime.getMonth().getValue());
            AqualityServices.getLogger().info("actual dayNumber-" + actualLocalDateTime.getDayOfMonth());
            AqualityServices.getLogger().info("actual min-" + actualLocalDateTime.getMinute());
            AqualityServices.getLogger().info("actual hour-" + actualLocalDateTime.getHour());
            if (actualBookmarkTitle.toLowerCase().equals(expectedBookmarkTitle.toLowerCase()) && expectedLocalDateTime.getHour() == actualLocalDateTime.getHour()
                    && expectedLocalDateTime.getMonthValue() == actualLocalDateTime.getMonthValue() && expectedLocalDateTime.getDayOfMonth() == actualLocalDateTime.getDayOfMonth()
                    && expectedLocalDateTime.getYear() == actualLocalDateTime.getYear()
                    && (expectedLocalDateTime.getMinute() == actualLocalDateTime.getMinute() || expectedLocalDateTime.getMinute() == actualLocalDateTime.getMinute() + 1
                    || expectedLocalDateTime.getMinute() == actualLocalDateTime.getMinute() + 2)) {
                isBookmarkPresent = true;
                break;
            }
        }
        return isBookmarkPresent;
    }

    private LocalDateTime getExpectedLocalDateTime(String stringExpectedDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("y-M-dd HH:m:s");
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse(deleteSomeCharactersForExpectedDateTime(stringExpectedDateTime), dateTimeFormatter);
        return expectedLocalDateTime;
    }

    private LocalDateTime getActualLocalDateTime(String stringActualDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm");
        LocalDateTime actualLocalDateTime = LocalDateTime.parse(deleteSomeCharactersForActualDateTime(stringActualDateTime), dateTimeFormatter);
        return actualLocalDateTime;
    }

    private String deleteSomeCharactersForExpectedDateTime(String stringExpectedDateTime) {
        String expectedBookmarkDateTime = stringExpectedDateTime.split("\\+")[0].replace("T", " ");
        return expectedBookmarkDateTime;
    }

    private String deleteSomeCharactersForActualDateTime(String stringActualDateTime) {
        return stringActualDateTime.split(" - ")[0];
    }
}
