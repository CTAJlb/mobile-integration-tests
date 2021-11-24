package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.pdfreader.PdfReaderScreen;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.IOS)
public class IosApplicationSteps extends AbstractApplicationSteps {
    private final ReaderEpubScreen readerEpubScreen;
    private final PdfReaderScreen pdfReaderScreen;

    public IosApplicationSteps() {
        super();
        readerEpubScreen = screenFactory.getScreen(ReaderEpubScreen.class);
        pdfReaderScreen = screenFactory.getScreen(PdfReaderScreen.class);
    }

    @Override
    public void returnToPreviousScreenForEpubAndPdf() {
        if (readerEpubScreen.state().isDisplayed()) {
            readerEpubScreen.openNavigationBar();
            readerEpubScreen.getNavigationBarEpubScreen().returnToPreviousScreen();
        } else if (pdfReaderScreen.state().isDisplayed()) {
            pdfReaderScreen.closeReader();
        }
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        welcomeScreen.findLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }
}
