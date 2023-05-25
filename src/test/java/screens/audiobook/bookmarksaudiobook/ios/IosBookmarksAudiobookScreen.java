package screens.audiobook.bookmarksaudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.IosAttributes;
import framework.utilities.DateUtils;
import org.openqa.selenium.By;
import screens.audiobook.bookmarksaudiobook.BookmarksAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosBookmarksAudiobookScreen extends BookmarksAudiobookScreen {

    private final ILabel lblNoBookmarks = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name=\"no bookmarks\")]"), "No bookmarks label");
    private final ILabel lblChapterName = getElementFactory().getLabel(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[1]"), "Chapter name");
    private final ILabel lblChapterTime = getElementFactory().getLabel(By.xpath("//XCUIElementTypeTable/XCUIElementTypeCell[1]/XCUIElementTypeStaticText[3]"), "Chapter time");

    public IosBookmarksAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name=\"Bookmarks\"]"));
    }

    @Override
    public boolean isBookmarksScreenSelected() {
        return lblNoBookmarks.state().waitForDisplayed();
    }

    @Override
    public boolean isNoBookmarksMessageDisplayed() {
        return lblNoBookmarks.state().waitForDisplayed();
    }

    @Override
    public String getChapterName() {
        return lblChapterName.getText();
    }

    @Override
    public String getChapterTime() {
        return DateUtils.getDuration(lblChapterTime.getAttribute(IosAttributes.VALUE)).toString();
    }
}
