package screens.search.modal.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.search.modal.SearchModal;

@ScreenType(platform = PlatformName.IOS)
public class IosSearchModal extends SearchModal {
    private static final String MAIN_ELEMENT = "//XCUIElementTypeButton[@name=\"Search\"]";

    private final ITextBox txbSearch = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSearchField[contains(@name, \"Search\")]"), "Search value input");
    private final IButton btnSearch = getElementFactory().getButton(By.xpath(MAIN_ELEMENT), "Search");
    private final IButton btnClearSearch = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Clear text\"]"), "Clear text button");
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Back\"]"), "Back button");

    public IosSearchModal() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void setSearchedText(String text) {
        txbSearch.sendKeys(text);
    }

    @Override
    public void applySearch() {
        btnSearch.click();
    }

    @Override
    public void clearSearchField() {
        btnClearSearch.click();
    }

    @Override
    public boolean isSearchFieldEmpty() {
        return txbSearch.getText().isEmpty();
    }

    @Override
    public void closeSearchScreen() {
        btnBack.click();
    }
}
