package screens.aboutpalace.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.aboutpalace.AboutPalaceScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidAbPalaceScreen extends AboutPalaceScreen {
    private final ILabel lblPalaceProject = getElementFactory().getLabel(By.xpath("//android.widget.Image[@text=\"The Palace Project\"]"), "The Palace Project");

    public AndroidAbPalaceScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"About Palace\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblPalaceProject.state().waitForDisplayed();
    }
}
