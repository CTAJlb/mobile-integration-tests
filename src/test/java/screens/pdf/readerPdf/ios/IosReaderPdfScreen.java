package screens.pdf.readerPdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.RegEx;
import constants.application.attributes.IosAttributes;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.Direction;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
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
    private final ILabel lblPageNumberSlider =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther[contains(@value,\"Page\")]"), "lblPageNumberSlider");
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
        openNavigationBar();
        openNavigationBar();
        if (entireScreenDragDirection == EntireScreenDragDirection.RIGHT) {
            Point upperLeft = lblPageNumberSlider.getElement().getLocation();
            Dimension dimensions = lblPageNumberSlider.getElement().getSize();
            int y = upperLeft.y + dimensions.height / 2;
            Direction direction = new Direction().setFrom(new Point(upperLeft.x + dimensions.width + 1, y)).setTo(new Point(upperLeft.x + dimensions.width - 1, y));
            AqualityServices.getTouchActions().swipe(direction.getFrom(), direction.getTo());
        } else {
            SwipeElementUtils.swipeThroughEntireElement(lblPageNumberSlider, EntireElementSwipeDirection.RIGHT);
        }
    }
}
