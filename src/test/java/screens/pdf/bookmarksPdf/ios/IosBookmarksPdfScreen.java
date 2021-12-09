package screens.pdf.bookmarksPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import org.openqa.selenium.By;
import screens.pdf.bookmarksPdf.BookmarksPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosBookmarksPdfScreen extends BookmarksPdfScreen {
    private static final String PAGE_LOC = "//XCUIElementTypeOther/XCUIElementTypeImage";

    public IosBookmarksPdfScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public int getCountOfBookmarks() {
        return getListOfILableOfBookmarks().size();
    }

    @Override
    public void openBookmarks(int bookmarksNumber) {
        ILabel lblBookmarks = getListOfILableOfBookmarks().get(bookmarksNumber);
        lblBookmarks.click();
    }

    private List<ILabel> getListOfILableOfBookmarks() {
        return getElementFactory().findElements(By.xpath(PAGE_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
