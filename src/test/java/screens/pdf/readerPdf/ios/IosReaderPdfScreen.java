package screens.pdf.readerPdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.RegEx;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.tocPdf.TocPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosReaderPdfScreen extends ReaderPdfScreen {
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"), "Book Name");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@value,'/')]"), "Book Page number");
    private final ILabel lblPage = getElementFactory().getLabel(
            By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"),
            "Book Page");
    private final IButton btnChapters =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[2]"), "Table of contents");
    private final IButton btnSearch =
            getElementFactory().getButton(By.xpath("(//XCUIElementTypeNavigationBar/XCUIElementTypeButton)[3]"), "Search btn");
    private final IButton btnTableOfContents =
            getElementFactory().getButton(By.xpath("(//XCUIElementTypeNavigationBar/XCUIElementTypeButton)[2]"), "Table of contents");
    private final IButton btnGoBack =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton"), "Go back");

    public IosReaderPdfScreen() {
        super(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"));
    }

    @Override
    public String getBookName() {
        return lblBookName.getAttribute(Attributes.VALUE);
    }

    @Override
    public int getPageNumber() {
        openMenu();
        return Integer.parseInt(RegExUtil.getStringFromFirstGroup(lblPageNumber.getText(), RegEx.PDF_CURRENT_PAGE_REGEX));
    }

    @Override
    public void openMenu() {
        if (!lblPageNumber.state().waitForDisplayed()) {
            CoordinatesClickUtils.clickOutOfElement(lblPage); // needed to expand navigation and labels
        }
    }

    @Override
    public void goToNextPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.RIGHT);
        CoordinatesClickUtils.clickOutOfElement(lblPage); // needed to expand navigation and labels
    }

    @Override
    public void goToPreviousPage() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.LEFT);
        CoordinatesClickUtils.clickOutOfElement(lblPage); // needed to expand navigation and labels
    }

    @Override
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        // not implemented on iOS
    }

    @Override
    public TocPdfScreen openGallery() {
        btnChapters.click();
        TocPdfScreen tocPdfScreen =
                AqualityServices.getScreenFactory().getScreen(TocPdfScreen.class);
        tocPdfScreen.state().waitForExist();
        return tocPdfScreen;
    }

    @Override
    public void openSearchPdf() {
        btnSearch.click();
    }

    @Override
    public void closeReader() {
        btnGoBack.click();
    }

    @Override
    public void openTableOfContents() {
        btnTableOfContents.click();
    }
}
