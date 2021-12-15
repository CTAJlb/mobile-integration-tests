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
    private static final String BOOKMARK_TITLE_LOC = "//android.widget.TextView[contains(@resource-id,\"bookmarkTitle\")]";
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
    public boolean isBookmarkPresent(String bookmarkTitle, String bookmarkDateTime) {
        LocalDateTime expectedLocalDateTime = getExpectedLocalDateTime(bookmarkDateTime);
        boolean bookmarkIsPresent = false;
        for (int i = 0; i < getListOfBookmarkTitles().size(); i++) {
            LocalDateTime actualLocalDateTime = getActualLocalDateTime(getListOfBookmarkTimeDates().get(i));
            if (getListOfBookmarkTitles().get(i).toLowerCase().equals(bookmarkTitle.toLowerCase()) && expectedLocalDateTime.getHour() == actualLocalDateTime.getHour()
                    && (expectedLocalDateTime.getMinute() == actualLocalDateTime.getMinute() || expectedLocalDateTime.getMinute() == actualLocalDateTime.getMinute() + 1)) {
                bookmarkIsPresent = true;
                break;
            }
        }
        return bookmarkIsPresent;
    }

    private LocalDateTime getExpectedLocalDateTime(String stringExpectedDateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("y-M-dd HH:m:s");
        LocalDateTime expectedLocalDateTime = LocalDateTime.parse(deleteSomeCharactersForExpectedDateTime(stringExpectedDateTime), dateTimeFormatter);
        return expectedLocalDateTime;
    }

    private LocalDateTime getActualLocalDateTime(String stringActualDateTime){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("y-M-dd HH:m:s");
        LocalDateTime actualLocalDateTime = LocalDateTime.parse(stringActualDateTime, dateTimeFormatter);
        return actualLocalDateTime;
    }

    private String deleteSomeCharactersForExpectedDateTime(String stringExpectedDateTime) {
        String expectedBookmarkDateTime = stringExpectedDateTime.split("\\+")[0].replace("T", " ");
        return expectedBookmarkDateTime;
    }
}
