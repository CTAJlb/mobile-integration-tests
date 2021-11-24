package screens.pdf.readerPdf.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidReaderPdfScreen extends ReaderPdfScreen {
    private final ILabel lblBookName = getElementFactory().getLabel(By.id("title_textView"), "Book Name");
    private final ILabel lblPageNumberSlider =
            getElementFactory().getLabel(By.xpath("//*[contains(@resource-id,\"pdfView\")]//android.widget.TextView"), "Book Page number");
    private final ILabel lblPage = getElementFactory().getLabel(By.xpath("//android.widget.FrameLayout"), "Book Page");
    private final IButton btnChapters = getElementFactory().getButton(By.id("reader_toc"), "Table of contents");

    public AndroidReaderPdfScreen() {
        super(By.id("pdf_reader_hud_container"));
    }

    @Override
    public String getBookName() {
        return lblBookName.getText();
    }

    @Override
    public int getPageNumber() {
        return Integer.parseInt(lblPageNumberSlider.getText());
    }

    @Override
    public void openMenu() {

    }

    @Override
    public void goToNextPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.RIGHT);
    }

    @Override
    public void goToPreviousPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.LEFT);
    }

    @Override
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        SwipeElementUtils.dragElementThroughEntireScreen(lblPageNumberSlider, entireScreenDragDirection);
    }

    @Override
    public TocPdfScreen openGallery() {
        throw new NotImplementedException();
    }

    @Override
    public void openSearchPdf() {
        throw new NotImplementedException();
    }

    @Override
    public void closeReader() {

    }

    @Override
    public void openTableOfContents() {
        btnChapters.click();
    }
}
