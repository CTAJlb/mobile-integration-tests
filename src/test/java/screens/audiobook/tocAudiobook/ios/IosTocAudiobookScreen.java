package screens.audiobook.tocAudiobook.ios;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.IosAttributes;
import org.openqa.selenium.By;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosTocAudiobookScreen extends TocAudiobookScreen {
    private final ILabel lblChapterName = getElementFactory().getLabel(By.xpath(CHAPTER_NAME_LOC), "Chapter name");
    private static final String CHAPTER_NAME_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[1]";

    public IosTocAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    @Override
    public boolean isTheFirstChapterLoaded() {
        return lblChapterName.state().waitForDisplayed();
    }

    @Override
    public String openChapterAndGetChapterName(int chapterNumber) {
        IElement lblChapterText = getChapters().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(IosAttributes.NAME);
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
        return lblChapterName.getAttribute(IosAttributes.NAME);
    }

    private List<IElement> getChapters() {
        return new ArrayList<>(getElementFactory().findElements(By.xpath(CHAPTER_NAME_LOC), ElementType.LABEL));
    }
}
