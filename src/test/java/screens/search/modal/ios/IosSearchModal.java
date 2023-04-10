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

    private final ITextBox txbSearch = getElementFactory().getTextBox(By.xpath("//XCUIElementTypeSearchField"), "Search value input");
    private final IButton btnSearch = getElementFactory().getButton(By.xpath(MAIN_ELEMENT), "Search");
    private final IButton btnClearSearch = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"clear.button.text\"]"), "Clear text button");
    private final IButton btnBack = getElementFactory().getButton(By.xpath("//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]"), "Back button");
    private final IButton btnDelete = getElementFactory().getButton(By.xpath("//XCUIElementTypeKey[@name=\"delete\"]"), "Delete button");
    private final IButton btnSpace = getElementFactory().getButton(By.xpath("//XCUIElementTypeKey[@name=\"space\"]"), "Space button");
    private final IButton btnK = getElementFactory().getButton(By.xpath("//XCUIElementTypeKey[@name=\"K\"]"), "K button");

    public IosSearchModal() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void setSearchedText(String text) {
        txbSearch.sendKeys(text);
    }

    @Override
    public void inputCharacterK() {
        btnK.click();
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

    @Override
    public String getTextFromBackButton() {
        return btnBack.getText();
    }

    @Override
    public String getTextFromSearchField() {
        return txbSearch.getText();
    }

    @Override
    public void deleteSomeData() {
        btnDelete.click();
    }

    @Override
    public boolean isSearchButtonClickable() {
        return btnSearch.state().isClickable();
    }

    @Override
    public boolean isSearchLineDisplayed() {
        return txbSearch.state().waitForDisplayed();
    }

    @Override
    public void inputSpace() {
        btnSpace.click();
    }
}
