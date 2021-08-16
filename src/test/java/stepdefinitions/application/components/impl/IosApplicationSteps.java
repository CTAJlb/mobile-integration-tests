package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import screens.epubreader.EpubReaderScreen;
import screens.pdfreader.PdfReaderScreen;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.IOS)
public class IosApplicationSteps extends AbstractApplicationSteps {
    private final EpubReaderScreen epubReaderScreen;
    private final PdfReaderScreen pdfReaderScreen;

    public IosApplicationSteps() {
        super();
        epubReaderScreen = screenFactory.getScreen(EpubReaderScreen.class);
        pdfReaderScreen = screenFactory.getScreen(PdfReaderScreen.class);
    }

    @Override
    public void returnToPreviousScreenForEpubAndPdf() {
        if (epubReaderScreen.state().isDisplayed()) {
            epubReaderScreen.returnToPreviousScreen();
        } else if (pdfReaderScreen.state().isDisplayed()) {
            pdfReaderScreen.closeReader();
        }
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        welcomeScreen.findLibrary();
        addAccountScreen.selectLibrary(libraryName);
    }
}
