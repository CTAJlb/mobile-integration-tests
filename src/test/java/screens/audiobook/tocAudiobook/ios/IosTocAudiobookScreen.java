package screens.audiobook.tocAudiobook.ios;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
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
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton"), "Back button");
    private final IButton btnChapters = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Chapters\"]"), "Chapters tab");
    private final IButton btnBookmarks = getElementFactory().getButton(By.xpath("//android.widget.LinearLayout[@name=\"Bookmarks\"]"), "Bookmarks tab");

    private static final String CHAPTER_NAME_LOC = "//XCUIElementTypeTable/XCUIElementTypeCell/XCUIElementTypeStaticText[1]";
    private static final String CURRENT_CHAPTER_NAME = "//XCUIElementTypeTable/XCUIElementTypeCell[%d]/XCUIElementTypeStaticText[1]";

    public IosTocAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeTable"));
    }

    @Override
    public boolean isTheFirstChapterLoaded() {
        return lblChapterName.state().waitForDisplayed();
    }

    @Override
    public String openChapterAndGetChapterName(int chapterNumber) {
        ILabel lblChapter = getElementFactory().getLabel(By.xpath(String.format(CURRENT_CHAPTER_NAME, chapterNumber)), "Chapter");
        String chapterText = lblChapter.getAttribute(IosAttributes.NAME);
        lblChapter.click();
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

    @Override
    public String getTextFromBackBtn() {
        return btnBack.getText();
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
        return lblChapterName.state().waitForDisplayed();
    }

    private List<ILabel> getChapters() {
        return new ArrayList<>(getElementFactory().findElements(By.xpath(CHAPTER_NAME_LOC), ElementType.LABEL));
    }
}
