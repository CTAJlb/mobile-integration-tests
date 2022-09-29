package screens.pdf.searchPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.IosAttributes;
import org.openqa.selenium.By;
import screens.pdf.searchPdf.SearchPdfScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosSearchPdfScreen extends SearchPdfScreen {
    private final IButton btnDone = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Done\"]"), "Apply search");
    private final ITextBox txbSearchLine = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeTextField[@value=\"Search\"]"), "Search line");

    private static final String FOUND_TEXT_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText[1]";
    private static final String FOUND_TEXT_NUMBER_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText[@name=\"%d\"]";
    private static final String NUMBER_OF_FOUND_TEXT_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText[2]";

    public IosSearchPdfScreen() {
        super(By.xpath("//XCUIElementTypeSearchField"));
    }

    @Override
    public boolean isSearchPdfScreenOpened() {
        return txbSearchLine.state().waitForDisplayed();
    }

    @Override
    public void closeSearchScreen() {
        btnDone.click();
    }

    @Override
    public void enterText(String text) {
        txbSearchLine.sendKeys(text);
    }

    @Override
    public void deleteText() {
        txbSearchLine.clear();
    }

    @Override
    public boolean isSearchFieldEmpty() {
        return txbSearchLine.getText().isEmpty();
    }

    @Override
    public List<String> getListOfFoundTexts() {
        return getFoundTexts().stream().map(lblText -> lblText.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
    }

    @Override
    public int openRandomFoundText() {
        int pageNumber = (int) (Math.random() * (getNumbersOfFoundTexts().size()) + 1);
        ILabel foundText = getElementFactory().getLabel(By.xpath(String.format(FOUND_TEXT_NUMBER_LOC, pageNumber)), "Found text");
        foundText.click();
        return pageNumber;
    }

    @Override
    public boolean isSearchResultEmpty() {
        return getFoundTexts().size() == 0;
    }

    private List<ILabel> getNumbersOfFoundTexts() {
        return getElementFactory().findElements(By.xpath(NUMBER_OF_FOUND_TEXT_LOC), ElementType.LABEL);
    }

    private List<ILabel> getFoundTexts() {
        return getElementFactory().findElements(By.xpath(FOUND_TEXT_LOC), ElementType.LABEL);
    }
}
