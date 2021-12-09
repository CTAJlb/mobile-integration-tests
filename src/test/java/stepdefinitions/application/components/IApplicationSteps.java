package stepdefinitions.application.components;
import constants.localization.application.catalog.EnumActionButtonsForBooksAndAlertsKeys;

public interface IApplicationSteps {

    void returnToPreviousScreenForEpubAndPdf();

    void restartApp();

    void performActionOnAlert(EnumActionButtonsForBooksAndAlertsKeys actionButtonKey);

    void addAccountFromWelcomeScreen(String libraryName);

    void approveAgreement();

    void closeTutorial();

    void checkWelcomeScreenIsOpened();

    void checkEachTutorialPageCanBeOpened();
}
