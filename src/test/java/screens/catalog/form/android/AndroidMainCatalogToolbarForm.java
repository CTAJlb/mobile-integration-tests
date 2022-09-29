package screens.catalog.form.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.catalog.form.MainCatalogToolbarForm;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidMainCatalogToolbarForm extends MainCatalogToolbarForm {
    private static final String MAIN_ELEMENT_LOC = "//*[contains(@resource-id,\"mainToolbar\")]";
    private static final String CATEGORY_INFO_LOCATOR_PART = MAIN_ELEMENT_LOC + "/android.widget.TextView";

    private final IButton btnChooseAnotherLibrary = getElementFactory().getButton(By.xpath(MAIN_ELEMENT_LOC + "/android.widget.ImageView"), "Menu");
    private final ILabel lblCategoryName = getElementFactory().getLabel(By.xpath(CATEGORY_INFO_LOCATOR_PART), "Category name");
    private final IButton btnSearch = getElementFactory().getButton(By.xpath(MAIN_ELEMENT_LOC + "//*[contains(@resource-id,\"catalogMenuActionSearch\")]"), "Search");

    public AndroidMainCatalogToolbarForm() {
        super(By.xpath(MAIN_ELEMENT_LOC));
    }

    @Override
    public void chooseAnotherLibrary() {
        btnChooseAnotherLibrary.click();
    }

    @Override
    public String getCategoryName() {
        return lblCategoryName.getText();
    }

    @Override
    public void openSearchModal() {
        btnSearch.click();
    }
}
