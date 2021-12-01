package screens.pdf.galleryPdf.ios;

import aquality.appium.mobile.actions.SwipeDirection;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import org.openqa.selenium.By;
import screens.pdf.galleryPdf.GalleryPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosGalleryPdfScreen extends GalleryPdfScreen {
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeCollectionView"), "lblPage");
    private static final String PAGE_LOC = "//XCUIElementTypeOther/XCUIElementTypeImage";

    public IosGalleryPdfScreen() {
        super(By.xpath("//XCUIElementTypeCollectionView"));
    }

    @Override
    public int getCountOfBookPages() {
        return (int) getPages().stream().limit(10).count();
    }

    @Override
    public void scrollGallery(EntireElementSwipeDirection entireElementSwipeDirection) {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, entireElementSwipeDirection);
    }

    @Override
    public void openGalleryPage(int pageNumber) {
        ILabel lblPage = getPages().get(pageNumber);
        lblPage.click();
    }

    private List<ILabel> getPages() {
        return getElementFactory().findElements(By.xpath(PAGE_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
