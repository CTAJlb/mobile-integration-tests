package screens.pdf.tocBookmarksGalleryPdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.galleryPdf.GalleryPdfScreen;
import screens.pdf.tocBookmarksGalleryPdf.TocBookmarksGalleryPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosTocBookmarksGalleryPdfScreen extends TocBookmarksGalleryPdfScreen {
    private final TocPdfScreen tocPdfScreen;
    private final GalleryPdfScreen galleryPdfScreen;
    private final IButton btnResume =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name = \"Resume\"]"), "btnResume");
    private final IButton btnBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]"), "btnBack");
    private final IButton btnGallery =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name = \"Grid\"]"), "btnGallery");
    private final IButton btnToc =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name = \"List\"]"), "btnToc");
    private final IButton btnBookmarks =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeSegmentedControl/XCUIElementTypeButton[@name = \"Bookmark N\"]"), "btnBookmarks");

    public IosTocBookmarksGalleryPdfScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name = \"Resume\"]"));
        tocPdfScreen = AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        galleryPdfScreen = AqualityServices.getScreenFactory().getScreen(GalleryPdfScreen.class);
    }

    @Override
    public void tapBackButton() {
        btnBack.click();
    }

    @Override
    public void tapResumeButton() {
        btnResume.click();
    }

    @Override
    public void tapGalleryButton() {
        btnGallery.click();
    }

    @Override
    public void tapBookmarksButton() {
        btnBookmarks.click();
    }

    @Override
    public TocPdfScreen getTocPdfScreen() {
        return tocPdfScreen;
    }

    @Override
    public GalleryPdfScreen getGalleryPdfScreen() {
        return galleryPdfScreen;
    }

    @Override
    public void tapTocButton() {
        btnToc.click();
    }
}
