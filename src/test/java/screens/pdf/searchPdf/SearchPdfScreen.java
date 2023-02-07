package screens.pdf.searchPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class SearchPdfScreen extends Screen {
    public SearchPdfScreen(By locator) {
        super(locator, "Pdf search");
    }

    public abstract boolean isSearchPdfScreenOpened();

    public abstract void closeSearchScreen();

    public abstract void enterText(String text);

    public abstract void deleteText();

    public abstract boolean isSearchFieldEmpty();

    public abstract List<String> getListOfFoundTexts();

    public abstract int openRandomFoundText();

    public abstract boolean isSearchResultEmpty();

    public abstract String getTextFromDoneBtn();

    public abstract String getTextFromSearchTxb();
}
