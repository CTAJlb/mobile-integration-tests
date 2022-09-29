package screens.epub.tocBookmarksEpub.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.epub.EnumTabsTocAndBookmarksEpub;
import org.openqa.selenium.By;
import screens.epub.bookmarksEpub.BookmarksEpubScreen;
import screens.epub.tocBookmarksEpub.TocBookmarksEpubScreen;
import screens.epub.tocEpub.TocEpubScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocBookmarksEpubScreen extends TocBookmarksEpubScreen {
    public AndroidTocBookmarksEpubScreen() {
        super(By.xpath("//android.widget.HorizontalScrollView[contains(@resource-id,\"tocTabs\")]"));
        tocEpubScreen = AqualityServices.getScreenFactory().getScreen(TocEpubScreen.class);
        bookmarksEpubScreen = AqualityServices.getScreenFactory().getScreen(BookmarksEpubScreen.class);
    }

    @Override
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void openTab(EnumTabsTocAndBookmarksEpub tab) {
        getElementFactory().getLabel(By.xpath(String.format("//android.widget.TextView[@text=\"%s\"]", tab.getValue())), "tab").click();
    }

    @Override
    public TocEpubScreen getTocEpubScreen() {
        return tocEpubScreen;
    }

    @Override
    public BookmarksEpubScreen getBookmarksEpubScreen() {
        return bookmarksEpubScreen;
    }
}
