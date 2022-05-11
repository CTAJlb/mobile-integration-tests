package screens.softwarelicenses.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.softwarelicenses.SoftwareLicScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSoftwareLicScreen extends SoftwareLicScreen {

    private final ILabel lblLicense = getElementFactory().getLabel(By.xpath("//android.widget.TextView[@text=\"Palace License\"]"), "Palace License");

    public AndroidSoftwareLicScreen() {
        super(By.xpath("//android.widget.TextView[@text=\"Software Licenses\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblLicense.state().isDisplayed();
    }
}
