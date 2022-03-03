package screens.epub.bookmarksEpub.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBookmarksEpubScreen extends BookmarksEpubScreen {
    private static final String BOOKMARK_TITLE_LOC = "//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"tocBookmarksList\")]/android.view.ViewGroup//android.widget.TextView[contains(@resource-id,\"bookmarkTitle\")]";
    private static final String BOOKMARK_DATE_TIME_LOC = "//android.widget.TextView[contains(@resource-id,\"bookmarkDate\")]";
    private static final String BTN_DELETE_LOC = "//android.widget.ImageView[contains(@resource-id,\"bookmarkDelete\")]";

    private final IButton btnDeleteFromAlert =
            getElementFactory().getButton(By.xpath("//android.widget.Button[contains(@resource-id,\"button1\")]"), "btnDeleteFromAlert");

    public AndroidBookmarksEpubScreen() {
        super(By.xpath("//android.view.ViewGroup[contains(@resource-id,\"tocBookmarksLastRead\")]"));
    }

    @Override
    public List<String> getListOfBookmarkTitles() {
        return getListOfILableOfBookmarkTitles().stream().map(label -> label.getText()).collect(Collectors.toList());
    }

    private List<ILabel> getListOfILableOfBookmarkTitles() {
        return getElementFactory().findElements(By.xpath(BOOKMARK_TITLE_LOC), ElementType.LABEL);
    }

    @Override
    public List<String> getListOfBookmarkTimeDates() {
        return getListOfILableOfBookmarkDates().stream().map(label -> label.getText()).collect(Collectors.toList());
    }

    private List<ILabel> getListOfILableOfBookmarkDates() {
        return getElementFactory().findElements(By.xpath(BOOKMARK_DATE_TIME_LOC), ElementType.LABEL);
    }

    @Override
    public void deleteBookmark(int bookmarkNumber) {
        getListOfDeleteBtns().get(bookmarkNumber).click();
        btnDeleteFromAlert.click();
    }

    private List<IButton> getListOfDeleteBtns() {
        return getElementFactory().findElements(By.xpath(BTN_DELETE_LOC), ElementType.BUTTON);
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
        AqualityServices.getLogger().info("expected year-" + expectedLocalDateTime.getYear());
        AqualityServices.getLogger().info("expected month-" + expectedLocalDateTime.getMonthValue());
        AqualityServices.getLogger().info("expected dayNumber-" + expectedLocalDateTime.getDayOfMonth());
        AqualityServices.getLogger().info("expected min-" + expectedLocalDateTime.getMinute());
        AqualityServices.getLogger().info("expected hour-" + expectedLocalDateTime.getHour());
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
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("y-M-dd HH:m:s");
        LocalDateTime actualLocalDateTime = LocalDateTime.parse(stringActualDateTime, dateTimeFormatter);
        return actualLocalDateTime;
    }

    private String deleteSomeCharactersForExpectedDateTime(String stringExpectedDateTime) {
        String expectedBookmarkDateTime = stringExpectedDateTime.split("\\+")[0].replace("T", " ");
        return expectedBookmarkDateTime;
    }
}
