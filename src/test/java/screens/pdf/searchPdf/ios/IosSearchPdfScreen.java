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
    private static final String NUMBER_OF_FOUND_TEXT_LOC = "//XCUIElementTypeCell/XCUIElementTypeStaticText[2]/following-sibling::XCUIElementTypeStaticText";

    private final ITextBox txbSearch = getElementFactory().getTextBox(
            By.xpath("//XCUIElementTypeSearchField"), "txbSearch");
    private final IButton btnApplySearch = getElementFactory().getButton(
            By.xpath("//XCUIElementTypeButton[@name=\"Search\"]"), "btnApplySearch");

    public IosSearchPdfScreen() {
        super(By.xpath("//XCUIElementTypeSearchField"));
    }

    @Override
    public void findTextInDocument(String text) {
        txbSearch.sendKeys(text);
        btnApplySearch.click();
    }

    @Override
    public List<String> getListOfFoundTexts() {
        return getFoundTexts().stream().map(lblText -> lblText.getAttribute(IosAttributes.NAME)).collect(Collectors.toList());
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

    private List<ILabel> getFoundTexts() {
        return getElementFactory().findElements(By.xpath(FOUND_TEXT_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }

    private List<ILabel> getNumbersOfFoundTexts() {
        return getElementFactory().findElements(By.xpath(NUMBER_OF_FOUND_TEXT_LOC), ElementType.LABEL, ElementsCount.ANY, ElementState.EXISTS_IN_ANY_STATE);
    }
}
