package screens.audiobook.tocAudiobook.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.AndroidAttributes;
import enums.timeouts.BooksTimeouts;
import org.openqa.selenium.By;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocAudiobookScreen extends TocAudiobookScreen {
    private final ILabel lblTheFirstChapter = getElementFactory().getLabel(By.xpath("//androidx.recyclerview.widget.RecyclerView//android.widget.LinearLayout[1]"), "The first chapter");
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//android.widget.ImageButton[@content-desc=\"Back\"]"), "Back button");
    private final IButton btnChapters = getElementFactory().getButton(By.xpath("//android.widget.LinearLayout[@content-desc=\"Chapters\"]"), "Chapters tab");
    private final IButton btnBookmarks = getElementFactory().getButton(By.xpath("//android.widget.LinearLayout[@content-desc=\"Bookmarks\"]"), "Bookmarks tab");
    private static final String CHAPTERS_LOC = "//android.widget.LinearLayout//*[contains(@resource-id, \"player_toc_item_view_title\")]";
    private static final String DOWNLOADING_PROGRESS_LOC = "//androidx.recyclerview.widget.RecyclerView//android.widget.RelativeLayout[%d]//android.view.View";

    public AndroidTocAudiobookScreen() {
        super(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"list\")]"));
    }

    @Override
    public boolean isTheFirstChapterLoaded() {
        return lblTheFirstChapter.state().waitForEnabled();
    }

    @Override
    public String openChapterAndGetChapterName(int chapterNumber) {
        IElement lblChapterText = getChapters().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(AndroidAttributes.TEXT);
        ILabel downloadProgress = getElementFactory().getLabel(By.xpath(String.format(DOWNLOADING_PROGRESS_LOC, chapterNumber + 1)), "Downloading progress");
        AqualityServices.getConditionalWait().waitFor(() -> !downloadProgress.state().isDisplayed(), Duration.ofMillis(BooksTimeouts.TIMEOUT_BOOK_CHANGES_STATUS.getTimeoutMillis()));
        lblChapterText.click();
        return chapterText;
    }

    @Override
    public int getCountOfChapters() {
        return getChapters().size();
    }

    @Override
    public String getChapterName(int chapterNumber) {
        IElement lblChapterName = getChapters().get(chapterNumber);
        return lblChapterName.getAttribute(AndroidAttributes.TEXT);
    }

    @Override
    public String getTextFromBackBtn() {
        return null;
    }

    @Override
    public void clickBackBtn() {
        btnBack.click();
    }

    @Override
    public boolean isContentTabDisplayed() {
        return btnChapters.state().waitForDisplayed();
    }

    @Override
    public boolean isBookmarksTabDisplayed() {
        return btnBookmarks.state().waitForDisplayed();
    }

    @Override
    public void openBookmarks() {
        btnBookmarks.click();
    }

    @Override
    public void openChapters() {
        btnChapters.click();
    }

    @Override
    public boolean isChaptersSelected() {
        return btnChapters.getAttribute(AndroidAttributes.SELECTED).equals(Boolean.TRUE.toString());
    }

    private List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOC), ElementType.LABEL).stream().limit(10).collect(Collectors.toList());
    }
}
