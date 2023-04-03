package stepdefinitions.application.components;

public interface IApplicationSteps {

    void turnOnTestMode();

    void returnToPreviousScreenForEpubAndPdf();

    void restartApp();

    void checkThatTutorialScreenIsOpened();

    void checkWelcomeScreenIsOpened();

    void isWelcomeScreenOpenedInSpanish();

    void checkTranslationOnWelcomeScreen();
    void checkTranslationOnWelcomeScreenItalian();

    void waitSeveralSeconds(Integer secondsCount);

    void checkEachTutorialPageCanBeOpened();

    void closeTutorialScreen();

    void closeWelcomeScreen();

    void closeWelcomeScreenInSpanish();

    void closeWelcomeScreenInItalian();

    void checkFindYourLibScreenTranslation();
    void checkFindYourLibScreenTranslationItalian();
}
