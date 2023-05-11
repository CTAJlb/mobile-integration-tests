package screens.search.modal.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ITextBox;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import framework.utilities.keyboard.KeyboardUtils;
import io.appium.java_client.android.nativekey.AndroidKey;
import org.openqa.selenium.By;
import screens.search.modal.SearchModal;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSearchModal extends SearchModal {
    private final ITextBox txbSearch = getElementFactory().getTextBox(By.xpath("//android.widget.AutoCompleteTextView[contains(@resource-id,\"search_src_text\")]"), "Search value input");
    private final IButton btnClearSearchField = getElementFactory().getButton(By.xpath("//android.widget.ImageView[contains(@resource-id,\"search_close_btn\")]"), "Clear search field btn");

    public AndroidSearchModal() {
        super(By.xpath("//*[contains(@resource-id,\"search_src_text\")]"));
    }

    @Override
    public void setSearchedText(String text) {
        txbSearch.sendKeys(text);
    }

    @Override
    public void inputCharacterK() {
        KeyboardUtils.pressKey(AndroidKey.K);
    }

    @Override
    public void applySearch() {
        KeyboardUtils.pressKey(AndroidKey.ENTER);
        KeyboardUtils.hideKeyboard();
    }

    @Override
    public void clearSearchField() {
        btnClearSearchField.click();
    }

    @Override
    public boolean isSearchFieldEmpty() {
        return txbSearch.getText().isEmpty();
    }

    @Override
    public void closeSearchScreen() {
        btnClearSearchField.click();
        if (txbSearch.state().isDisplayed())
            btnClearSearchField.click();
    }

    @Override
    public String getTextFromBackButton() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromSearchField() {
        return txbSearch.getText();
    }

    @Override
    public void deleteSomeData() {
        KeyboardUtils.pressKey(AndroidKey.DEL);
    }

    @Override
    public boolean isSearchButtonClickable() {
        //for iOS
        return false;
    }

    @Override
    public boolean isSearchLineDisplayed() {
        return txbSearch.state().waitForDisplayed();
    }

    @Override
    public void inputSpace() {
        KeyboardUtils.pressKey(AndroidKey.SPACE);
    }
}