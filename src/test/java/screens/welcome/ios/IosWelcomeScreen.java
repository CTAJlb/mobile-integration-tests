package screens.welcome.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.welcome.WelcomeScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosWelcomeScreen extends WelcomeScreen {
    private final IButton btnFindLibrary = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Find Your Library\"]"), "Find your library button");
    private final IButton btnFindLibrarySpanish = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Encuentra tu biblioteca\"]"), "Find your library button in Spanish");
    private final IButton btnFindLibraryItalian = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Trova la tua biblioteca\"]"), "Find your library button in Spanish");
    private final IButton btnFindLibraryFrench = getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"Trouvez votre bibliothèque\"]"), "Find your library button in French");

    public IosWelcomeScreen() {
        super(By.xpath("//XCUIElementTypeButton[@name=\"Find Your Library\"]"));
    }

    @Override
    public void tapFindLibraryButton() {
        btnFindLibrary.click();
    }

    @Override
    public void tapFindLibraryButtonInSpanish() {
        btnFindLibrarySpanish.click();
    }

    @Override
    public void tapFindLibraryButtonInItalian() {
        btnFindLibraryItalian.click();
    }

    @Override
    public void tapFindLibraryButtonInFrench() {
        btnFindLibraryFrench.click();
    }

    @Override
    public void tapFindLibraryButtonInGerman() {

    }

    @Override
    public String getTextFromButtonFindYourLibraryES() {
        return btnFindLibrarySpanish.getText();
    }

    @Override
    public String getTextFromButtonFindYourLibraryIT() {
        return btnFindLibraryItalian.getText();
    }

    @Override
    public String getTextFromButtonFindYourLibraryFR() {
        return btnFindLibraryFrench.getText();
    }

    @Override
    public boolean isOpenedInSpanish() {
        return btnFindLibrarySpanish.state().waitForDisplayed();
    }

    @Override
    public boolean isOpenedInItalian() {
        return btnFindLibraryItalian.state().waitForDisplayed();
    }

    @Override
    public boolean isOpenedInFrench() {
        return btnFindLibraryFrench.state().waitForDisplayed();
    }

    @Override
    public boolean isOpenedInGerman() {
        return false;
    }
}
