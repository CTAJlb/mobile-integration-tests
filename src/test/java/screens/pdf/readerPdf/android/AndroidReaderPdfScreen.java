package screens.pdf.readerPdf.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidReaderPdfScreen extends ReaderPdfScreen {
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//android.widget.TextView[contains(@resource-id,\"title_textView\")]"), "lblBookName");
    private final ILabel lblPageNumberSlider =
            getElementFactory().getLabel(By.xpath("//*[contains(@resource-id,\"pdfView\")]//android.widget.TextView"), "lblPageNumberSlider");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//android.widget.FrameLayout"), "lblPage");
    private final IButton btnToc =
            getElementFactory().getButton(By.xpath("//android.widget.ImageView[contains(@resource-id,\"reader_toc\")]"), "btnToc");

    public AndroidReaderPdfScreen() {
        super(By.xpath("//*[contains(@resource-id,\"pdfView\")]"));
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
    public void goToNextPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.RIGHT);
    }

    @Override
    public void openAdditionalButtonsAndLabels() {
        if (!lblPageNumberSlider.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public void hideAdditionalButtonsAndLabels() {
        if (lblPageNumberSlider.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public void clickToc(){
        btnToc.click();
    }

    @Override
    public void goToPreviousPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.LEFT);
    }

    @Override
    public NavigationBarPdfScreen getNavigationBarPdfScreen() {
        //only for ios
        return null;
    }

    @Override
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        SwipeElementUtils.dragElementThroughEntireScreen(lblPageNumberSlider, entireScreenDragDirection);
    }

    @Override
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }
}
