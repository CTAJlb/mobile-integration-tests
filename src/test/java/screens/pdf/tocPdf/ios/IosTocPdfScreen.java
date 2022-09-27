package screens.pdf.tocPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import org.openqa.selenium.By;
import screens.pdf.tocPdf.TocPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosTocPdfScreen extends TocPdfScreen {
    private static final String CHAPTER_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[1]";
    private static final String CHAPTER_NUMBER_LOC = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"%d\"]";

    public IosTocPdfScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    @Override
    public int  openRandomChapter() {
        int chapterNumber = (int) (Math.random() * (getChapters().size()) + 1);
        ILabel chapter = getElementFactory().getLabel(By.xpath(String.format(CHAPTER_NUMBER_LOC, chapterNumber)), "Chapter");
        chapter.click();
        return chapterNumber;
    }

    @Override
    public boolean areChaptersDisplayed() {
        return getChapters().size() == 0 || getChapters().size() != 0;
    }

    private List<ILabel> getChapters() {
        return getElementFactory().findElements(By.xpath(CHAPTER_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
