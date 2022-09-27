package screens.pdf.bookmarksPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.bookmarksPdf.BookmarksPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosBookmarksPdfScreen extends BookmarksPdfScreen {
    private static final String BOOKMARK_LOC = "//XCUIElementTypeCell";

    public IosBookmarksPdfScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public int getCountOfBookmarks() {
        return getListOfBookmarks().size();
    }

    @Override
    public void openBookmark(int bookmarkNumber) {
        ILabel lblBookmarks = getListOfBookmarks().get(bookmarkNumber);
        lblBookmarks.click();
    }

    private List<ILabel> getListOfBookmarks() {
        return getElementFactory().findElements(By.xpath(BOOKMARK_LOC), ElementType.LABEL);
    }
}
