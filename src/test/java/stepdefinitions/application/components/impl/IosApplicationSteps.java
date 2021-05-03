package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
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
        if (epubReaderScreen.state().isDisplayed()){
            epubReaderScreen.returnToPreviousScreen();
        }else if (pdfReaderScreen.state().isDisplayed()){
            pdfReaderScreen.closeReader();
        }
    }
}
