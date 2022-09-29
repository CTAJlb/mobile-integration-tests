package screens.catalog.form.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.catalog.form.MainCatalogToolbarForm;

@ScreenType(platform = PlatformName.IOS)
public class IosMainCatalogToolbarForm extends MainCatalogToolbarForm {
    private static final String MAIN_ELEMENT_LOC = "//XCUIElementTypeNavigationBar";

    private final IButton btnChooseAnotherLibrary = getElementFactory().getButton(By.xpath(MAIN_ELEMENT_LOC + "//XCUIElementTypeButton[@name=\"Change Library Account\"]"), "Change library account");
    private final ILabel lblCategoryAndCatalogName = getElementFactory().getLabel(By.xpath(MAIN_ELEMENT_LOC + "/XCUIElementTypeButton[1]/following-sibling::*"), "Catalog name");
    private final IButton btnSearch = getElementFactory().getButton(By.xpath(MAIN_ELEMENT_LOC + "//XCUIElementTypeButton[@name=\"Search\"]"), "Search");

    public IosMainCatalogToolbarForm() {
        super(By.xpath(MAIN_ELEMENT_LOC));
    }

    @Override
    public void chooseAnotherLibrary() {
        btnChooseAnotherLibrary.click();
    }

    @Override
    public String getCategoryName() {
        return lblCategoryAndCatalogName.getText();
    }

    @Override
    public void openSearchModal() {
        btnSearch.click();
    }
}
