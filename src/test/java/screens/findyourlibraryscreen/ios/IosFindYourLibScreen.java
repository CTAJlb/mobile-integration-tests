package screens.findyourlibraryscreen.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import screens.findyourlibraryscreen.FindYourLibScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.IOS)
public class IosFindYourLibScreen extends FindYourLibScreen {

    private final IButton btnAddLib = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Add Library\"]"), "Add library btn");
    private final IButton btnAddLibSpanish = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Añadir biblioteca\"]"), "Add library btn in Spanish");
    private final CreatingLibraryLocator libraryLocator = (index ->
            getElementFactory().getLabel(By.xpath(String.format("//XCUIElementTypeSheet//XCUIElementTypeScrollView[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[%d]/XCUIElementTypeButton", index)), "Library"));
    private final IButton btnCancel = getElementFactory().getButton(By.xpath("//XCUIElementTypeSheet//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Cancel\"]"), "Close button");
    private final IButton btnCancelSpanish = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Cancelar\"]"), "Cancel btn in Spanish");
    private final ILabel lblFindYourLibSpanish = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Encuentra tu biblioteca\"]"), "Find your library lbl in Spanish");
    private static final String LIBRARY_NAME = "//XCUIElementTypeSheet//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"%s\"]";

    public IosFindYourLibScreen() {
        super(By.xpath("//XCUIElementTypeSheet[@name=\"Find Your Library\"]"));
    }

    @Override
    public void tapAddLibrary() {
        btnAddLib.click();
    }

    @Override
    public void tapToLibrary(String libName) {
        IButton btnLibrary = getElementFactory().getButton(By.xpath(String.format(LIBRARY_NAME, libName)), "Library button");
        btnLibrary.click();
    }

    @Override
    public boolean isSortingAlphabetical(int amountOfLibraries) {
        List<String > libraries = getListOfLibraries(amountOfLibraries);
        return Ordering.natural().isOrdered(libraries);
    }

    @Override
    public void tapCancelBtn() {
        btnCancel.click();
    }

    @Override
    public String getTextFromFindYourLibraryLbl() {
        return lblFindYourLibSpanish.getText();
    }

    @Override
    public String getTextFromAddLibraryBtn() {
        return btnAddLibSpanish.getText();
    }

    @Override
    public String getTextFromCancelBtn() {
        return btnCancelSpanish.getText();
    }

    private List<String > getListOfLibraries(int listSize) {
        List<String > libraries = new ArrayList<>();
        int index = 1;
        int end = 0;
        while (end <= listSize) {
            ILabel lblLibrary = libraryLocator.createLbl(index);
            libraries.add(lblLibrary.getText());
            index+=2;
            end++;
        }
        return libraries;
    }

    @FunctionalInterface
    interface CreatingLibraryLocator {
        ILabel createLbl(int index);
    }
}
