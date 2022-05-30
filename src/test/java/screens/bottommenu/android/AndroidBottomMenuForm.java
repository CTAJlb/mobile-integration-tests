package screens.bottommenu.android;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.bottommenu.BottomMenu;
import screens.bottommenu.BottomMenuForm;
@ScreenType(platform = PlatformName.ANDROID)
public class AndroidBottomMenuForm extends BottomMenuForm {

    private static CreatingTypeOfButton typeOfButton = (button ->
            AqualityServices.getElementFactory().getButton(By.xpath(
                    String.format("//android.view.ViewGroup//android.widget.FrameLayout//android.widget.TextView[@text=\"%s\"]", button)), String.format("%s", button)));

    public AndroidBottomMenuForm() {
        super(By.id("bottomNavigator"));
    }

    @Override
    public void open(BottomMenu bottomMenuItem) {
        getButton(bottomMenuItem).click();
    }

    @Override
    public boolean isMenuDisplayed() {
        return getElementFactory().getButton(By.id("bottomNavigator"), "Bottom menu").state().isDisplayed();
    }

    @Override
    public String getTypeOfTab(String type) {
        return typeOfButton.createBtn(type).getText();
    }

    private IButton getButton(BottomMenu bottomMenuItem) {
        String itemName = bottomMenuItem.getItemName();
        return getElementFactory().getButton(By.id(itemName), itemName);
    }

    @FunctionalInterface
    interface CreatingTypeOfButton {
        IButton createBtn(String button);
    }
}
