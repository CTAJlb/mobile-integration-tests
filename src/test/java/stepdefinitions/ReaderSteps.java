package stepdefinitions;

import aquality.appium.mobile.application.AqualityServices;
import com.google.inject.Inject;
import constants.application.EnumBookType;
import framework.utilities.ScenarioContext;
import io.cucumber.java.en.Then;
import models.android.CatalogBookModel;
import org.junit.Assert;
import screens.audioplayer.AudioPlayerScreen;
import screens.epub.readerEpub.ReaderEpubScreen;
import screens.pdf.readerPdf.ReaderPdfScreen;

import java.util.Locale;

public class ReaderSteps {
    private final ReaderEpubScreen readerEpubScreen;
    private final ReaderPdfScreen readerPdfScreen;
    private final ScenarioContext context;
    private final AudioPlayerScreen audioPlayerScreen;

    @Inject
    public ReaderSteps(ScenarioContext context) {
        readerEpubScreen = AqualityServices.getScreenFactory().getScreen(ReaderEpubScreen.class);
        readerPdfScreen = AqualityServices.getScreenFactory().getScreen(ReaderPdfScreen.class);
        audioPlayerScreen = AqualityServices.getScreenFactory().getScreen(AudioPlayerScreen.class);
        this.context = context;
    }


    private void assertBookNameForPdf(CatalogBookModel catalogBookModel) {
        String expectedBookName = catalogBookModel.getTitle().toLowerCase();
        String actualBookName = readerPdfScreen.getBookName().toLowerCase();
        Assert.assertTrue(String.format("BookName(pdf) is not correct. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }

    @Then("Book {string} with {} type is present on epub or pdf or audiobook screen")
    public void readerScreenForEbookTypeIsPresent(String bookInfoKey, EnumBookType bookType) {
        CatalogBookModel catalogBookModel = context.get(bookInfoKey);
        String bookName = catalogBookModel.getTitle();
        switch (bookType) {
            case EBOOK:
                if (readerEpubScreen.state().waitForDisplayed()) {
                    assertBookNameForEpub(catalogBookModel);
                } else {
                    assertBookNameForPdf(catalogBookModel);
                }
                break;
            case AUDIOBOOK:
                Assert.assertTrue("AudiobookName is not present on audiobook screen", audioPlayerScreen.isAudiobookNamePresent(bookName));
                break;
        }
    }

    private void assertBookNameForEpub(CatalogBookModel catalogBookModel) {
        String expectedBookName = catalogBookModel.getTitle().toLowerCase();
        String actualBookName = readerEpubScreen.getBookName().toLowerCase();
        Assert.assertTrue(String.format("BookName(epub) is not correct. Expected bookName - '%1$s', actualName - '%2$s'", expectedBookName, actualBookName), actualBookName.contains(expectedBookName));
    }
}
