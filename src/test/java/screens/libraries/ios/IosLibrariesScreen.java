package screens.libraries.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import com.google.common.collect.Ordering;
import framework.utilities.swipe.SwipeElementUtils;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import screens.libraries.LibrariesScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosLibrariesScreen extends LibrariesScreen {
    private static final String LIBRARY_CELL_BY_LIBRARY_NAME_LOC = "//XCUIElementTypeStaticText[@name=\"%s\"]/parent::XCUIElementTypeCell";
    private static final String LIB_NAME_LOCATOR = "//XCUIElementTypeTable//XCUIElementTypeCell//XCUIElementTypeStaticText[2]";
    private static final String LIB_NAME_ON_LIB_SETTINGS = "//XCUIElementTypeStaticText[@name=\"%s\"]";

    private final IButton btnAddLibrary = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Add Library\"]"), "btnAddLibrary");
    private final IButton btnDeleteLibrary = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@label=\"Delete\"]"), "btnDeleteLibrary");

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
    public boolean isAddLibraryBtnDisplayed() {
        return btnAddLibrary.state().isDisplayed();
    }

    @Override
    public void deleteLibrary(String libraryName) {
        IButton libraryToSwipeLeft = getLibraryCell(libraryName);
        SwipeElementUtils.swipeElementLeft(libraryToSwipeLeft);
        btnDeleteLibrary.click();
    }

    @Override
    public boolean isLibrariesAreSorted() {
        List<String> libraries = getLibrariesNames();
        libraries.remove(0);
        return Ordering.natural().isOrdered(libraries);
    }

    @Override
    public boolean isLibrarySettingsOpened(String libraryName) {
        return getElementFactory().getLabel(By.xpath(String.format(LIB_NAME_ON_LIB_SETTINGS, libraryName)), "Library name on library settings screen").state().isDisplayed();
    }

    private List<String> getLibrariesNames() {
        List<ILabel> libraries = getElementFactory().findElements(By.xpath(LIB_NAME_LOCATOR), ElementType.LABEL);
        List<String> names = new ArrayList<>();
        libraries.forEach(library -> names.add(library.getText().toLowerCase()));
        return names;
    }
}
