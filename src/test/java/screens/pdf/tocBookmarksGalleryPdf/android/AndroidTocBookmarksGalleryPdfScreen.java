package screens.pdf.tocBookmarksGalleryPdf.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.galleryPdf.GalleryPdfScreen;
import screens.pdf.tocBookmarksGalleryPdf.TocBookmarksGalleryPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidTocBookmarksGalleryPdfScreen extends TocBookmarksGalleryPdfScreen {
    public AndroidTocBookmarksGalleryPdfScreen() {
        super(By.xpath(""));
    }

    @Override
    public void tapBackButton() {
        //only for ios
    }

    @Override
    public void tapResumeButton() {
        //only for ios
    }

    @Override
    public void tapGalleryButton() {
        //only for ios
    }

    @Override
    public void tapBookmarksButton() {
        //only for ios
    }

    @Override
    public TocPdfScreen getTocPdfScreen() {
        //only for ios
        return null;
    }

    @Override
    public GalleryPdfScreen getGalleryPdfScreen() {
        //only for ios
        return null;
    }

    @Override
    public void tapTocButton() {
        //only for ios
    }
}
