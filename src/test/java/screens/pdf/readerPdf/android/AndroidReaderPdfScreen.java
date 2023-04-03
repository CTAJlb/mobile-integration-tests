package screens.pdf.readerPdf.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import framework.utilities.swipe.directions.EntireElementSwipeDirection;
import framework.utilities.swipe.directions.EntireScreenDragDirection;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import screens.pdf.navigationBarPdf.NavigationBarPdfScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import screens.pdf.searchPdf.SearchPdfScreen;
import screens.pdf.settingspdf.SettingsPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidReaderPdfScreen extends ReaderPdfScreen {
    private final NavigationBarPdfScreen navigationBarPdfScreen;
    private final SettingsPdfScreen settingsPdfScreen;
    private final ILabel lblViewer = getElementFactory().getLabel(By.xpath("//android.view.View[@resource-id=\"viewerContainer\"]"), "Content viewer");
    private final ILabel lblBookName = getElementFactory().getLabel(By.xpath("//android.view.ViewGroup/android.widget.TextView"), "Book name");
    private final ILabel lblPageNumber = getElementFactory().getLabel(By.xpath("//android.widget.TextView[@resource-id=\"numPages\"]"), "Last page number");

    private final ILabel lblPage = getElementFactory().getLabel(By.xpath("//android.view.View[@resource-id=\"mainContainer\"]/android.view.View"), "lblPage");

    public AndroidReaderPdfScreen() {
        super(By.xpath("//android.view.View[@resource-id=\"viewerContainer\"]"));
        navigationBarPdfScreen = AqualityServices.getScreenFactory().getScreen(NavigationBarPdfScreen.class);
        settingsPdfScreen = AqualityServices.getScreenFactory().getScreen(SettingsPdfScreen.class);
    }

    @Override
    public NavigationBarPdfScreen getNavigationBarScreen() {
        return navigationBarPdfScreen;
    }

    @Override
    public boolean isReaderOpened() {
        return lblViewer.state().waitForDisplayed();
    }

    @Override
    public String getBookName() {
        return lblBookName.getText();
    }

    @Override
    public int getPageNumber() {
        return Integer.parseInt(StringUtils.substringBetween(lblPageNumber.getText(), "(", " of"));
    }

    @Override
    public int getLastPageNumber() {
        return Integer.parseInt(StringUtils.substringBetween(lblPageNumber.getText(), "of ", ")"));
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
    public void slidePageSlider(EntireScreenDragDirection entireScreenDragDirection) {
        //only for ios
    }

    @Override
    public void returnToPreviousScreen() {
        AqualityServices.getApplication().getDriver().navigate().back();
    }

    @Override
    public void openNavigationBar() {
        //only for ios
    }

    @Override
    public SearchPdfScreen getSearchPdfScreen() {
        //only for ios
        return null;
    }

    @Override
    public SettingsPdfScreen getSettingsPdfScreen() {
        return settingsPdfScreen;
    }
}
