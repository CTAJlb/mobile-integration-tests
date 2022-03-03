package screens.libraries.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.swipe.SwipeElementUtils;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import screens.libraries.LibrariesScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosLibrariesScreen extends LibrariesScreen {
    private static final String LIBRARY_CELL_BY_LIBRARY_NAME_LOC =
            "//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeCell";

    private final IButton btnAddLibrary = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Add Library\"]"), "btnAddLibrary");
    private final IButton btnDeleteLibrary =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Delete\"]"), "btnDeleteLibrary");

    public IosLibrariesScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Libraries\"]"));
    }

    @Override
    public boolean isLibraryPresent(String libraryName) {
        return getLibraryCell(libraryName).state().isDisplayed();
    }

    private IButton getLibraryCell(String libraryName) {
        return getElementFactory().getButton(By.xpath(String.format(LIBRARY_CELL_BY_LIBRARY_NAME_LOC, libraryName)), "libraryCell");
    }

    @Override
    public void openLibrary(String libraryName) {
        Point point = getLibraryCell(libraryName).getElement().getCenter();
        TouchAction action = new TouchAction(AqualityServices.getApplication().getDriver());
        action.tap(PointOption.point(point)).perform();
    }

    @Override
    public void addLibrary() {
        btnAddLibrary.click();
    }

    @Override
    public void deleteLibrary(String libraryName) {
        IButton libraryToSwipeLeft = getLibraryCell(libraryName);
        SwipeElementUtils.swipeElementLeft(libraryToSwipeLeft);
        btnDeleteLibrary.click();
    }
}
