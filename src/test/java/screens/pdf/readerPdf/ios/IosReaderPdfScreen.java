package screens.pdf.readerPdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.RegEx;
import constants.applicationattributes.IosAttributes;
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
import screens.pdf.settingspdf.SettingsPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosReaderPdfScreen extends ReaderPdfScreen {
    private final NavigationBarPdfScreen navigationBarPdfScreen;
    private final SearchPdfScreen searchPdfScreen;
    private final ILabel lblPageNumber = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[contains(@value,\"/\")]"), "lblPageNumber");
    private final ILabel lblBookName = getElementFactory().getLabel(By.xpath("//XCUIElementTypeToolbar/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther[2]/XCUIElementTypeStaticText"), "lblBookName");
    private final ILabel lblPageNumberSlider = getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther[contains(@value,\"Page\")]"), "lblPageNumberSlider");
    private final ILabel lblPage = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"), "lblPage");

    public IosReaderPdfScreen() {
        super(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"));
        navigationBarPdfScreen = AqualityServices.getScreenFactory().getScreen(NavigationBarPdfScreen.class);
        searchPdfScreen = AqualityServices.getScreenFactory().getScreen(SearchPdfScreen.class);
    }

    @Override
    public NavigationBarPdfScreen getNavigationBarScreen() {
        return navigationBarPdfScreen;
    }

    @Override
    public boolean isReaderOpened() {
        return lblPage.state().waitForDisplayed();
    }


    @Override
    public String getBookName() {
        openNavigationBar();
        return lblBookName.getAttribute(IosAttributes.NAME);
    }

    @Override
    public int getPageNumber() {
        openNavigationBar();
        return Integer.parseInt(RegExUtil.getStringFromFirstGroup(lblPageNumber.getAttribute(IosAttributes.NAME), RegEx.PDF_CURRENT_PAGE_REGEX));
    }

    @Override
    public int getLastPageNumber() {
        return 0;
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
    public void swipePageDown() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.DOWN);
    }

    @Override
    public void swipePageUp() {
        SwipeElementUtils.swipeThroughEntireElement(lblPage, EntireElementSwipeDirection.UP);
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
    public SearchPdfScreen getSearchPdfScreen() {
        return searchPdfScreen;
    }

    @Override
    public SettingsPdfScreen getSettingsPdfScreen() {
        //only for android
        return null;
    }

    @Override
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        openNavigationBar();
        openNavigationBar();
        if (entireScreenDragDirection == EntireScreenDragDirection.RIGHT) {
            SwipeElementUtils.swipeThroughEntireElement(lblPageNumberSlider, EntireElementSwipeDirection.RIGHT);
        } else {
            SwipeElementUtils.swipeThroughEntireElement(lblPageNumberSlider, EntireElementSwipeDirection.LEFT);
        }
    }
}
