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
    private final IButton btnAddLibItalian = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Aggiungi biblioteca\"]"), "Add library btn in Italian");
    private final IButton btnAddLibFrench = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Ajouter une bibliothèque\"]"), "Add library btn in French");
    private final CreatingLibraryLocator libraryLocator = (index ->
            getElementFactory().getLabel(By.xpath(String.format("//XCUIElementTypeSheet//XCUIElementTypeScrollView[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[%d]/XCUIElementTypeButton", index)), "Library"));
    private final IButton btnCancel = getElementFactory().getButton(By.xpath("//XCUIElementTypeSheet//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Cancel\"]"), "Close button");
    private final IButton btnCancelSpanish = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Cancelar\"]"), "Cancel btn in Spanish");
    private final IButton btnCancelItalian = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Annulla\"]"), "Cancel btn in Italian");
    private final IButton btnCancelFrench = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Annuler\"]"), "Cancel btn in French");
    private final ILabel lblFindYourLibSpanish = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Encuentra tu biblioteca\"]"), "Find your library lbl in Spanish");
    private final ILabel lblFindYourLibItalian = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Trova la tua biblioteca\"]"), "Find your library lbl in Italian");
    private final ILabel lblFindYourLibFrench = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Trouvez votre bibliothèque\"]"), "Find your library lbl in French");
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
    public String getTextFromFindYourLibraryLblIT() {
        return lblFindYourLibItalian.getText();
    }

    @Override
    public String getTextFromFindYourLibraryLblFR() {
        return lblFindYourLibFrench.getText();
    }

    @Override
    public String getTextFromFindYourLibraryLblGR() {
        return null;
    }

    @Override
    public String getTextFromAddLibraryBtn() {
        return btnAddLibSpanish.getText();
    }

    @Override
    public String getTextFromAddLibraryBtnIT() {
        return btnAddLibItalian.getText();
    }

    @Override
    public String getTextFromAddLibraryBtnFR() {
        return btnAddLibFrench.getText();
    }

    @Override
    public String getTextFromAddLibraryBtnGR() {
        return null;
    }

    @Override
    public String getTextFromCancelBtn() {
        return btnCancelSpanish.getText();
    }

    @Override
    public String getTextFromCancelBtnIT() {
        return btnCancelItalian.getText();
    }

    @Override
    public String getTextFromCancelBtnFR() {
        return btnCancelFrench.getText();
    }

    @Override
    public String getTextFromCancelBtnGR() {
        return null;
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
