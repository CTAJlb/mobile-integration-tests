package screens.audiobook.tocAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IElement;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.application.attributes.IosAttributes;
import org.openqa.selenium.By;
import screens.audiobook.tocAudiobook.TocAudiobookScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosTocAudiobookScreen extends TocAudiobookScreen {
    private static final String CHAPTERS_TEXT_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[1]";
    private static final String CHAPTERS_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell";


    public IosTocAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    @Override
    public String openChapterAndGetChapterName(int chapterNumber) {
        IElement lblChapterText = getChaptersText().get(chapterNumber);
        String chapterText = lblChapterText.getAttribute(IosAttributes.NAME);
        lblChapterText.click();
        return chapterText;
    }

    private List<IElement> getChaptersText() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_TEXT_LOC), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }

    @Override
    public int getCountOfChapters() {
        return getChapters().size();
    }

    @Override
    public String getChapterName(int chapterNumber) {
        IElement lblChapterName = getChaptersText().get(chapterNumber);
        return lblChapterName.getAttribute(IosAttributes.NAME);
    }

    private List<IElement> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTERS_LOC), ElementType.LABEL).stream().limit(5).collect(Collectors.toList());
    }
}
