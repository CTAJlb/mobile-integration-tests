package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import org.testng.Assert;
import screens.agegate.AgeGateScreen;
import screens.epubreader.EpubReaderScreen;
import screens.pdfreader.PdfReaderScreen;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.IOS)
public class IosApplicationSteps extends AbstractApplicationSteps {
    private final AgeGateScreen ageGateScreen;
    private final EpubReaderScreen epubReaderScreen;
    private final PdfReaderScreen pdfReaderScreen;

    public IosApplicationSteps() {
        super();
        ageGateScreen = screenFactory.getScreen(AgeGateScreen.class);
        epubReaderScreen = screenFactory.getScreen(EpubReaderScreen.class);
        pdfReaderScreen = screenFactory.getScreen(PdfReaderScreen.class);
    }

    public void openApplication() {
        welcomeScreen.addALibraryLater();
        if (ageGateScreen.state().waitForDisplayed()) {
            ageGateScreen.approveAge();
        }
    }

    @Override
    public void returnToPreviousPage() {
        if (epubReaderScreen.state().isDisplayed()) {
            epubReaderScreen.returnToPreviousScreen();
        } else if (pdfReaderScreen.state().isDisplayed()) {
            pdfReaderScreen.closeReader();
        }
    }

    @Override
    public void openApplicationVar2(String libraryName) {
        welcomeScreen.state().waitForDisplayed();
        welcomeScreen.findLibrary();
        //не подходит так как уник-й элемента нету
        /*Assert.assertTrue(addAccountScreen.state().waitForDisplayed(),
                "Checking that add accounts screen visible");*/
        addAccountScreen.selectLibraryWelcomeScreen(libraryName);
    }
}
