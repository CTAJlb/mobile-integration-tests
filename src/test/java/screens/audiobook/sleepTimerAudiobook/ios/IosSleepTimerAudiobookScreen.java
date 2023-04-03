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
    private final IButton btnCancelES = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Cancelar\"]"), "Cancel button in Spanish");
    private final IButton btnCancelIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Annulla\"]"), "Cancel button in Italian");
    private final ILabel lblSleepTimerES = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Temporizador de reposo\""), "Sleep timer label in Spanish");
    private final ILabel lblSleepTimerIT = getElementFactory().getLabel(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeStaticText[@name=\"Timer di sospensione\""), "Sleep timer label in Italian");
    private final IButton btnOffES = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Apagado\"]"), "Off button in Spanish");
    private final IButton btnOffIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Off\"]"), "Off button in Italian");
    private final IButton btnEndOfChapterES = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Fin del capítulo\"]"), "End of chapter button in Spanish");
    private final IButton btnEndOfChapterIT = getElementFactory().getButton(By.xpath("//XCUIElementTypeScrollView//XCUIElementTypeButton[@name=\"Fine del capitolo\"]"), "End of chapter button in Italian");

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
    public String getTextFromSleepTimerLabelES() {
        return lblSleepTimerES.getText();
    }

    @Override
    public String getTextFromSleepTimerLabelIT() {
        return lblSleepTimerIT.getText();
    }

    @Override
    public String getTextFromOffBtnES() {
        return btnOffES.getText();
    }

    @Override
    public String getTextFromOffBtnIT() {
        return btnOffIT.getText();
    }

    @Override
    public String getTextFromEndOfChapterBtnES() {
        return btnEndOfChapterES.getText();
    }

    @Override
    public String getTextFromEndOfChapterBtnIT() {
        return btnEndOfChapterIT.getText();
    }

    @Override
    public String getTextFromCancelBtn() {
        return btnCancel.getText();
    }

    @Override
    public String getTextFromCancelBtnES() {
        return btnCancelES.getText();
    }

    @Override
    public String getTextFromCancelBtnIT() {
        return btnCancelIT.getText();
    }
}
