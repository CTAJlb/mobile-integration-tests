package screens.pdf.searchPdf.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.ElementType;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import aquality.selenium.core.elements.ElementState;
import aquality.selenium.core.elements.ElementsCount;
import constants.application.attributes.IosAttributes;
import org.openqa.selenium.By;
import screens.pdf.searchPdf.SearchPdfScreen;

import java.util.List;
import java.util.stream.Collectors;

@ScreenType(platform = PlatformName.IOS)
public class IosSearchPdfScreen extends SearchPdfScreen {
    private static final String FOUND_TEXT_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText[2]";
    private static final String NUMBER_OF_FOUND_TEXT_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText[3]";

    private final ITextBox txbSearch =
            getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSearchField"), "txbSearch");
    private final IButton btnApplySearch =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Search\"]"), "btnApplySearch");
    private final IButton btnClose =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Cancel\"]"), "Close button");
    private final IButton btnDelete =
            getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Clear text\"]"), "Delete button");

    public IosSearchPdfScreen() {
        super(By.xpath("//XCUIElementTypeSearchField"));
    }

    @Override
    public void findTextInDocument(String text) {
        txbSearch.sendKeys(text);
        btnApplySearch.click();
    }

    @Override
    public void enterText(String text) {
        txbSearch.sendKeys(text);
    }

    @Override
    public void deleteText() {
        btnDelete.click();
    }

    @Override
    public List<String> getListOfFoundTexts() {
        return getFoundTexts().stream().limit(5).map(lblText -> lblText.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
    }

    @Override
    public void openTheFirstFoundText() {
        getFoundTexts().get(0).click();
    }

    @Override
    public int getNumberOfTheFirstFoundText() {
        ILabel lblNumberOfFoundText = getNumbersOfFoundTexts().get(0);
        return Integer.parseInt(lblNumberOfFoundText.getAttribute(IosAttributes.NAME));
    }

    @Override
    public void closeSearchScreen() {
        btnClose.click();
    }

    @Override
    public boolean isSearchFieldEmpty() {
        return txbSearch.getText().isEmpty();
    }

    @Override
    public boolean isSearchResultsEmpty() {
        List<ILabel> resultsList = getElementFactory().findElements(By.xpath(FOUND_TEXT_LOC), ElementType.LABEL);
        return resultsList.size() == 0;
    }

    private List<ILabel> getFoundTexts() {
        return getElementFactory().findElements(By.xpath(FOUND_TEXT_LOC), ElementType.LABEL);
    }

    private List<ILabel> getNumbersOfFoundTexts() {
        return getElementFactory().findElements(By.xpath(NUMBER_OF_FOUND_TEXT_LOC), ElementType.LABEL);
    }
}
