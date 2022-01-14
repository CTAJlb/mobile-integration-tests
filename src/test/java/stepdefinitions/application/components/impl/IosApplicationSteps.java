package stepdefinitions.application.components.impl;

import aquality.appium.mobile.application.AqualityServices;
import aquality.appium.mobile.application.PlatformName;
import factories.steps.StepsType;
import org.junit.Assert;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;
import stepdefinitions.application.components.AbstractApplicationSteps;

import java.util.List;

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
            if (AqualityServices.getApplication().getPlatformName() == PlatformName.ANDROID) {
                readerPdfScreen.returnToPreviousScreen();
            } else if (AqualityServices.getApplication().getPlatformName() == PlatformName.IOS) {
                readerPdfScreen.getNavigationBarScreen().tapBackButton();
            }
        }
    }

    @Override
    public void addAccountFromWelcomeScreen(String libraryName) {
        tutorialScreen.closeTutorial();
        welcomeScreen.tapFindLibraryButton();
        addAccountScreen.selectLibraryViaSearch(libraryName);
    }

    @Override
    public void checkEachTutorialPageCanBeOpened() {
        List<String> listOfPageNames = tutorialScreen.getListOfPageNames();
        while (tutorialScreen.getListOfPageNames().size() != 0) {
            tutorialScreen.getListOfPageNames().stream().forEach(pageName -> {
                Assert.assertTrue(String.format("Tutorial page '%s' is not opened", pageName), tutorialScreen.isTutorialPageOpened(pageName));
                tutorialScreen.goToNextPage();
            });
        }
    }
}
