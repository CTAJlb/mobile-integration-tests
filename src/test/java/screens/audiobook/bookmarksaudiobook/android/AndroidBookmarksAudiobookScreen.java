package screens.audiobook.bookmarksaudiobook.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.AndroidAttributes;
import org.openqa.selenium.By;
import screens.audiobook.bookmarksaudiobook.BookmarksAudiobookScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBookmarksAudiobookScreen extends BookmarksAudiobookScreen {

    private final IButton btnBookmarks = getElementFactory().getButton(By.xpath("//android.widget.LinearLayout[@content-desc=\"Bookmarks\"]"), "Bookmarks tab");

    public AndroidBookmarksAudiobookScreen() {
        super(By.xpath("//android.widget.LinearLayout[@content-desc=\"Bookmarks\"]"));
    }

    @Override
    public boolean isBookmarksScreenSelected() {
        return btnBookmarks.getAttribute(AndroidAttributes.SELECTED).equals(Boolean.TRUE.toString());
    }
}
