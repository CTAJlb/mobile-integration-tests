package screens.softwarelicenses.ios;

import aquality.appium.mobile.elements.interfaces.ILabel;
import org.openqa.selenium.By;
import screens.softwarelicenses.SoftwareLicScreen;

public class IosSoftwareLicScreen extends SoftwareLicScreen {

    private final ILabel lblLicense = getElementFactory().getLabel(By.xpath("//XCUIElementTypeStaticText[@name=\"Palace License\"]"), "Palace License");

    public IosSoftwareLicScreen() {
        super(By.xpath("//XCUIElementTypeNavigationBar[@name=\"Software Licenses\"]"));
    }

    @Override
    public boolean isOpened() {
        return lblLicense.state().isDisplayed();
    }
}
