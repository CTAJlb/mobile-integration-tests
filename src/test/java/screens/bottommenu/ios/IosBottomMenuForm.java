package screens.bottommenu.ios;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import constants.applicationattributes.IosAttributes;
import org.openqa.selenium.By;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;

@ScreenType(platform = PlatformName.IOS)
public class IosBottomMenuForm extends BottomMenuForm {
    private final IButton btnCatalog = getElementFactory().getButton(By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[1]"), "Catalog btn");
    private final IButton btnMyBooks = getElementFactory().getButton(By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[2]"), "My Books btn");
    private final IButton btnReservations = getElementFactory().getButton(By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[3]"), "Reservations btn");
    private final IButton btnSettings = getElementFactory().getButton(By.xpath("//XCUIElementTypeTabBar/XCUIElementTypeButton[4]"), "Settings btn");
    private static final String BOTTOM_MENU_ELEMENT_PATTERN_LOC = "//XCUIElementTypeButton[@name=\"%1$s\"]";
    private static final String MAIN_ELEMENT = String.format(BOTTOM_MENU_ELEMENT_PATTERN_LOC, BottomMenu.CATALOG.getItemName())
            + "/parent::XCUIElementTypeTabBar";
    private static CreatingTypeOfButton typeOfButton = (button ->
            AqualityServices.getElementFactory().getButton(By.xpath(String.format("//XCUIElementTypeTabBar/XCUIElementTypeButton[@name=\"%s\"]", button)), String.format("%s", button)));

    public IosBottomMenuForm() {
        super(By.xpath(MAIN_ELEMENT));
    }

    @Override
    public void open(BottomMenu bottomMenuItem) {
        getButton(bottomMenuItem).click();
    }

    @Override
    public boolean isMenuDisplayed() {
        return getElementFactory().getButton(By.xpath(MAIN_ELEMENT), "Bottom menu").state().isDisplayed();
    }

    @Override
    public String getTypeOfTab(String type) {
        return typeOfButton.createBtn(type).getText();
    }

    @Override
    public String getTextFromCatalogBtn() {
        return btnCatalog.getAttribute(IosAttributes.NAME);
    }

    @Override
    public String getTextFromMyBooksBtn() {
        return btnMyBooks.getText();
    }

    @Override
    public String getTextFromReservationsBtn() {
        return btnReservations.getText();
    }

    @Override
    public String getTextFromSettingsBtn() {
        return btnSettings.getText();
    }

    private IButton getButton(BottomMenu bottomMenuItem) {
        String itemName = bottomMenuItem.getItemName();
        return getElementFactory().getButton(By.xpath(String.format(BOTTOM_MENU_ELEMENT_PATTERN_LOC, itemName)), itemName);
    }

    @FunctionalInterface
    interface CreatingTypeOfButton {
        IButton createBtn(String button);
    }
}
