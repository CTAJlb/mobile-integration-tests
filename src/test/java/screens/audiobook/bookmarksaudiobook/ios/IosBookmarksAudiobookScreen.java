package screens.audiobook.bookmarksaudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.audiobook.bookmarksaudiobook.BookmarksAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosBookmarksAudiobookScreen extends BookmarksAudiobookScreen {

    private final ILabel lblNoBookmarks = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@name=\"no bookmarks\")]"), "No bookmarks label");

    public IosBookmarksAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name=\"Bookmarks\"]"));
    }

    @Override
    public boolean isBookmarksScreenSelected() {
        return lblNoBookmarks.state().waitForDisplayed();
    }
}
