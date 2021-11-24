package screens.pdf.searchPdf.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.apache.commons.lang3.NotImplementedException;
import org.openqa.selenium.By;
import screens.pdf.searchPdf.SearchPdfScreen;

import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSearchPdfScreen extends SearchPdfScreen {

    public AndroidSearchPdfScreen() {
        super(By.xpath(""));
    }

    @Override
    public void findTextInDocument(String textToBeFound) {
        throw new NotImplementedException();
    }

    @Override
    public boolean isFoundItemsExist() {
        throw new NotImplementedException();
    }

    @Override
    public List<String> getListOfFoundItems() {
        throw new NotImplementedException();
    }

    @Override
    public void openSearchedItemByName(String itemName) {
        throw new NotImplementedException();
    }

    @Override
    public int getSearchedItemPageNumber(int index) {
        throw new NotImplementedException();
    }
}
