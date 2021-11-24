package screens.pdf.searchPdf;

import aquality.appium.mobile.screens.Screen;
import org.openqa.selenium.By;

import java.util.List;

public abstract class SearchPdfScreen extends Screen {
    protected SearchPdfScreen(By locator) {
        super(locator, "Pdf search");
    }

    public abstract void findTextInDocument(String textToBeFound);

    public abstract boolean isFoundItemsExist();

    public abstract List<String> getListOfFoundItems();

    public abstract void openSearchedItemByName(String itemName);

    public abstract int getSearchedItemPageNumber(int index);
}
