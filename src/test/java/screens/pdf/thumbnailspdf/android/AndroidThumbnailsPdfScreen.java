package screens.pdf.thumbnailspdf.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import org.openqa.selenium.By;
import screens.pdf.thumbnailspdf.ThumbnailsPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidThumbnailsPdfScreen extends ThumbnailsPdfScreen {
    private static final String THUMBNAIL_LOC = "//android.view.View[contains(@content-desc, \"Thumbnail of Page\")]";
    private static final String THUMBNAIL_NUMBER_LOC = "//android.view.View[@content-desc=\"Thumbnail of Page %d\"]";

    public AndroidThumbnailsPdfScreen() {
        super(By.xpath("//android.view.View[@resource-id=\"thumbnailView\"]"));
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
