package screens.audiobook.sleepTimerAudiobook.ios;

import aquality.appium.mobile.application.PlatformName;
import aquality.appium.mobile.elements.interfaces.IButton;
import aquality.appium.mobile.elements.interfaces.ILabel;
import aquality.appium.mobile.screens.screenfactory.ScreenType;
import enums.localization.catalog.TimerKeys;
import org.openqa.selenium.By;
import screens.audiobook.sleepTimerAudiobook.SleepTimerAudiobookScreen;

@ScreenType(platform = PlatformName.IOS)
public class IosSleepTimerAudiobookScreen extends SleepTimerAudiobookScreen {
    private final IButton btnCancel = getElementFactory().getButton(By.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]/XCUIElementTypeOther/XCUIElementTypeOther[2]//XCUIElementTypeButton"), "Cancel button");
    private final IButton btnCancelES = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Cancelar\"]"), "Cancel button");
    private final ILabel lblSleepTimer = getElementFactory().getLabel(By.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]//XCUIElementTypeScrollView//XCUIElementTypeStaticText"), "Sleep timer label");
    private final ILabel lblSleepTimerES = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Temporizador de reposo\""), "Sleep timer label");
    private final IButton btnOff = getElementFactory().getButton(By.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeButton"), "Off button");
    private final IButton btnOffES = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Apagado\"]"), "Off button");
    private final IButton btnEndOfChapter = getElementFactory().getButton(By.xpath("//XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther[3]//XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[9]/XCUIElementTypeButton"), "End of chapter button");
    private final IButton btnEndOfChapterES = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Fin del capítulo\"]"), "End of chapter button");

    public IosSleepTimerAudiobookScreen() {
        super(By.xpath("//XCUIElementTypeStaticText[@name=\"Sleep Timer\"]"));
    }

    @Override
    public void setTimer(TimerKeys timerSetting) {
        String buttonName = timerSetting.getDefaultLocalizedValue();
        getElementFactory().getButton(By.xpath("//XCUIElementTypeButton[@name=\"" + buttonName + "\"]"), buttonName).click();
    }

    @Override
    public void closeSleepTimer() {
        btnCancel.click();
    }

    @Override
    public String getTextFromSleepTimerLabel() {
        return lblSleepTimer.getText();
    }

    @Override
    public String getTextFromSleepTimerLabelES() {
        return lblSleepTimerES.getText();
    }

    @Override
    public String getTextFromOffBtn() {
        return btnOff.getText();
    }

    @Override
    public String getTextFromOffBtnES() {
        return btnOffES.getText();
    }

    @Override
    public String getTextFromEndOfChapterBtn() {
        return btnEndOfChapter.getText();
    }

    @Override
    public String getTextFromEndOfChapterBtnES() {
        return btnEndOfChapterES.getText();
    }

    @Override
    public String getTextFromCancelBtn() {
        return btnCancel.getText();
    }

    @Override
    public String getTextFromCancelBtnES() {
        return btnCancelES.getText();
    }
}
