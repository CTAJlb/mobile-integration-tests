package screens.pdf.settingspdf.android;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.Attributes;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import org.openqa.selenium.By;
import screens.pdf.settingspdf.SettingsPdfScreen;

@ScreenType(platform = PlatformName.ANDROID)
public class AndroidSettingsPdfScreen extends SettingsPdfScreen {
    private final IButton btnFirstPage =
            getElementFactory().getButton(By.xpath("//android.widget.Button[@resource-id=\"firstPage\"]"), "First page button");
    private final IButton btnLastPage =
            getElementFactory().getButton(By.xpath("//android.widget.Button[@resource-id=\"lastPage\"]"), "Last page button");
    private final IButton btnVerticalScrolling =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"scrollVertical\"]"), "Vertical scrolling");
    private final IButton btnHorizontalScrolling =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"scrollHorizontal\"]"), "Horizontal scrolling");
    private final IButton btnWrappedScrolling =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"scrollWrapped\"]"), "Wrapped scrolling");
    private final IButton btnNoSpreads =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"spreadNone\"]"), "No spreads");
    private final IButton btnOddSpreads =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"spreadOdd\"]"), "Odd spreads");
    private final IButton btnEvenSpreads =
            getElementFactory().getButton(By.xpath("//android.widget.RadioButton[@resource-id=\"spreadEven\"]"), "Even spreads");

    public AndroidSettingsPdfScreen() {
        super(By.xpath("//android.view.View[@resource-id=\"secondaryToolbar\"]"));
    }

    @Override
    public boolean isOpened() {
        return btnFirstPage.state().waitForDisplayed();
    }

    @Override
    public void tapGoToFirstPage() {
        btnFirstPage.click();
    }

    @Override
    public void tapGoToLastPage() {
        btnLastPage.click();
    }

    @Override
    public void tapVerticalScrolling() {
        btnVerticalScrolling.click();
    }

    @Override
    public boolean isVerticalScrollingChosen() {
        return btnVerticalScrolling.getAttribute(Attributes.VALUE).equals(Boolean.TRUE.toString());
    }

    @Override
    public boolean isNoSpreadsAvailable() {
        return btnNoSpreads.state().isEnabled();
    }

    @Override
    public boolean isOddSpreadsAvailable() {
        return btnOddSpreads.state().isEnabled();
    }

    @Override
    public boolean isEvenSpreadsAvailable() {
        return btnEvenSpreads.state().isEnabled();
    }

    @Override
    public void tapHorizontalScrolling() {
        btnHorizontalScrolling.click();
    }

    @Override
    public boolean isHorizontalScrollingChosen() {
        return btnHorizontalScrolling.getAttribute(Attributes.VALUE).equals(Boolean.TRUE.toString());
    }

    @Override
    public void tapWrappedScrolling() {
        btnWrappedScrolling.click();
    }

    @Override
    public boolean isWrappedScrollingChosen() {
        return btnWrappedScrolling.getAttribute(Attributes.VALUE).equals(Boolean.TRUE.toString());
    }
}