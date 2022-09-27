package screens.pdf.thumbnailspdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import org.openqa.selenium.By;
import screens.pdf.thumbnailspdf.ThumbnailsPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosThumbnailsPdfScreen extends ThumbnailsPdfScreen {
    private static final String THUMBNAIL_LOC = "//XCUIElementTypeCollectionView/XCUIElementTypeCell";
    private static final String THUMBNAIL_NUMBER_LOC = THUMBNAIL_LOC + "/XCUIElementTypeStaticText[@name=\"%d\"]";

    public IosThumbnailsPdfScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public boolean areThumbnailsDisplayed() {
        return getThumbnails().size() != 0;
    }

    @Override
    public int openRandomThumbnail() {
        int thumbnailNumber = (int) (Math.random() * (getThumbnails().size()) + 1);
        ILabel thumbnail = getElementFactory().getLabel(By.xpath(String.format(THUMBNAIL_NUMBER_LOC, thumbnailNumber)), "Thumbnail");
        thumbnail.click();
        return thumbnailNumber;
    }

    private List<ILabel> getThumbnails(){
        return getElementFactory().findElements(By.xpath(THUMBNAIL_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
