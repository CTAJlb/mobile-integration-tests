package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import stepdefinitions.application.components.AbstractApplicationSteps;

@StepsType(platform = PlatformName.IOS)
public class IosApplicationSteps extends AbstractApplicationSteps {
    private final ReaderEpubScreen readerEpubScreen;
    private final ReaderPdfScreen readerPdfScreen;

    public IosApplicationSteps() {
        super();
        readerEpubScreen = screenFactory.getScreen(ReaderEpubScreen.class);
        readerPdfScreen = screenFactory.getScreen(ReaderPdfScreen.class);
    }

    @Override
    public void returnToPreviousScreenForEpubAndPdf() {
        if (readerEpubScreen.state().isDisplayed()) {
            readerEpubScreen.openNavigationBar();
            readerEpubScreen.getNavigationBarEpubScreen().returnToPreviousScreen();
        } else if (readerPdfScreen.state().isDisplayed()) {
            readerPdfScreen.closeReader();
        }
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        welcomeScreen.findLibrary();
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }
}
