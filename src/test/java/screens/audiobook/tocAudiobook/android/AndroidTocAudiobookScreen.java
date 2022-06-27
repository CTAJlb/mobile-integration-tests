package screens.audiobook.tocAudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.attributes.AndroidAttributes;
import org.openqa.selenium.By;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocAudiobookScreen extends TocAudiobookScreen {
    private static final String CHAPTERS_LOC = "//android.widget.RelativeLayout//*[contains(@resource-id, \"player_toc_item_view_title\")]";

    public AndroidTocAudiobookScreen() {
        super(By.xpath("//androidx.recyclerview.widget.RecyclerView[contains(@resource-id,\"list\")]"));
    }

    @Override
    public String openChapterAndGetChapterName(int chapterNumber) {
        IElement lblChapterText = getChapters().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(AndroidAttributes.TEXT);
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
    public void swipeToTheEndOfTOC() {

    }

    private List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOC), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }
}
