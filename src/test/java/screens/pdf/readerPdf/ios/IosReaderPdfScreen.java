package screens.pdf.readerPdf.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.RegEx;
import framework.utilities.CoordinatesClickUtils;
import framework.utilities.RegExUtil;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosReaderPdfScreen extends ReaderPdfScreen {
    private final NavigationBarPdfScreen navigationBarPdfScreen;
    private final ILabel lblBookName =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeToolbar/parent::XCUIElementTypeOther/preceding-sibling::XCUIElementTypeOther[2]"), "lblBookName");
    private final ILabel lblPageNumber =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeOther/XCUIElementTypeStaticText[contains(@value,\"/\")]"), "lblPageNumber");
    private final ILabel lblPage =
            getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"), "lblPage");

    public IosReaderPdfScreen() {
        super(By.xpath("//XCUIElementTypeScrollView/XCUIElementTypeTextView"));
        navigationBarPdfScreen = AqualityServices.getScreenFactory().getScreen(NavigationBarPdfScreen.class);
    }

    @Override
    public String getBookName() {
        return lblBookName.getAttribute(Attributes.VALUE);
    }

    @Override
    public NavigationBarPdfScreen getNavigationBarPdfScreen() {
        return navigationBarPdfScreen;
    }

    @Override
    public void openAdditionalButtonsAndLabels() {
        if (!navigationBarPdfScreen.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public void hideAdditionalButtonsAndLabels() {
        if (navigationBarPdfScreen.state().isDisplayed()) {
            CoordinatesClickUtils.clickAtCenterOfScreen();
        }
    }

    @Override
    public int getPageNumber() {
        return Integer.parseInt(RegExUtil.getStringFromFirstGroup(lblPageNumber.getText(), RegEx.PDF_CURRENT_PAGE_REGEX));
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
    public void clickToc() {
        //only for android, use getNavigationBarPdfScreen()
    }

    @Override
    public void returnToPreviousScreen() {
        //only for android, use getNavigationBarPdfScreen()
    }

    @Override
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        // only for android
    }
}
