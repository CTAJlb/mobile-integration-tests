package screens.findyourlibraryscreen.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import screens.findyourlibraryscreen.FindYourLibScreen;

import java.util.ArrayList;
import java.util.List;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidFindYourLibScreen extends FindYourLibScreen {

    private final IButton btnAddLib = getElementFactory().getButton(By.xpath("//android.widget.LinearLayout//android.widget.TextView[@text=\"Add Library\"]"), "Add library btn");
    private final IButton btnCancel = getElementFactory().getButton(By.xpath("//android.widget.TextView[@text=\"Cancel\"]"), "Close button");
    private final String CURRENT_LIBRARY_LOC = "//android.widget.LinearLayout/android.widget.TextView[@text=\"%s\"]";
    private final CreatingLibraryLocator libraryLocator = (index ->
            getElementFactory().getLabel(By.xpath(String.format(
                    "//android.widget.LinearLayout//androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[%d]/android.widget.LinearLayout/android.widget.TextView[1]", index)), "Library")
    );

    public AndroidFindYourLibScreen() {
        super(By.xpath(""));
    }

    @Override
    public void tapAddLibrary() {
        btnAddLib.click();
    }

    @Override
    public void tapToLibrary(String libName) {
        getElementFactory().getButton(By.xpath(String.format(CURRENT_LIBRARY_LOC, libName)), "Current library").click();
    }

    @Override
    public boolean isSortingAlphabetical(int amountOfLibraries) {
        List<String > libraries = getListOfLibraries(amountOfLibraries);
        return Ordering.natural().isOrdered(libraries);
    }

    @Override
    public void tapCancelBtn() {
        btnCancel.click();
    }

    @Override
    public String getTextFromFindYourLibraryLbl() {
        // for ios
        return null;
    }

    @Override
    public String getTextFromFindYourLibraryLblIT() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromFindYourLibraryLblFR() {
        return null;
    }

    @Override
    public String getTextFromFindYourLibraryLblGR() {
        return null;
    }

    @Override
    public String getTextFromAddLibraryBtn() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromAddLibraryBtnIT() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromAddLibraryBtnFR() {
        return null;
    }

    @Override
    public String getTextFromAddLibraryBtnGR() {
        return null;
    }

    @Override
    public String getTextFromCancelBtn() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromCancelBtnIT() {
        //for ios
        return null;
    }

    @Override
    public String getTextFromCancelBtnFR() {
        return null;
    }

    @Override
    public String getTextFromCancelBtnGR() {
        return null;
    }

    private List<String > getListOfLibraries(int listSize) {
        List<String > libraries = new ArrayList<>();
        int index = 1;
        int end = 0;
        while (end < listSize + 1) {
            ILabel lblLibrary = libraryLocator.createLbl(index);
            libraries.add(lblLibrary.getText());
            index++;
            end++;
        }
        return libraries;
    }

    @FunctionalInterface
    interface CreatingLibraryLocator {
        ILabel createLbl(int index);
    }
}
