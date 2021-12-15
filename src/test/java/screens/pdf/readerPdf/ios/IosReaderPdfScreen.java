package screens.pdf.readerPdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.RegEx;
import constants.application.attributes.IosAttributes;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.searchPdf.SearchPdfScreen;
import screens.pdf.tocBookmarksGalleryPdf.TocBookmarksGalleryPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosReaderPdfScreen extends ReaderPdfScreen {
    private final NavigationBarPdfScreen navigationBarPdfScreen;
    private final SearchPdfScreen searchPdfScreen;
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeToolbar/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther[2]/XCUIElementTypeStaticText"), "lblBookName");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@value,\"/\")]"), "lblPageNumber");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"), "lblPage");

    public IosReaderPdfScreen() {
        super(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"));
        navigationBarPdfScreen = AqualityServices.getScreenFactory().getScreen(NavigationBarPdfScreen.class);
        searchPdfScreen = AqualityServices.getScreenFactory().getScreen(SearchPdfScreen.class);
    }

    @Override
    public String getBookName() {
        openNavigationBar();
        return lblBookName.getAttribute(IosAttributes.NAME);
    }

    @Override
    public int getPageNumber() {
        openNavigationBar();
        openNavigationBar();
        return Integer.parseInt(RegExUtil.getStringFromFirstGroup(lblPageNumber.getAttribute(IosAttributes.NAME), RegEx.PDF_CURRENT_PAGE_REGEX));
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
    public void openToc() {
        openNavigationBar();
        navigationBarPdfScreen.openTocBookmarksGallery();
        AqualityServices.getScreenFactory().getScreen(TocBookmarksGalleryPdfScreen.class).tapTocButton();
    }

    @Override
    public void returnToPreviousScreen() {
        openNavigationBar();
        navigationBarPdfScreen.tapBackButton();
    }

    @Override
    public void openNavigationBar() {
        if (!navigationBarPdfScreen.state().waitForDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public NavigationBarPdfScreen getNavigationBarScreen() {
        return navigationBarPdfScreen;
    }

    @Override
    public SearchPdfScreen getSearchPdfScreen() {
        return searchPdfScreen;
    }

    @Override
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        // only for android
    }
}
