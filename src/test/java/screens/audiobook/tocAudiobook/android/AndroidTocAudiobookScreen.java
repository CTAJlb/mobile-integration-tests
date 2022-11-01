package screens.audiobook.tocAudiobook.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
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
    private static final String CHAPTERS_LOC = "//android.widget.LinearLayout//*[contains(@resource-id, \"player_toc_item_view_title\")]";
    private static final String DOWNLOADING_PROGRESS_LOC = "//androidx.recyclerview.widget.RecyclerView//android.widget.RelativeLayout[%d]//android.view.View";

    public AndroidTocAudiobookScreen() {
        super(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"list\")]"));
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

    private List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOC), ElementType.LABEL).stream().limit(10).collect(Collectors.toList());
    }
}
