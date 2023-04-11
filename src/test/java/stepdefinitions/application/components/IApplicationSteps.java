package stepdefinitions.application.components;

public interface IApplicationSteps {

    void turnOnTestMode();

    void returnToPreviousScreenForEpubAndPdf();

    void restartApp();

    void checkThatTutorialScreenIsOpened();

    void checkWelcomeScreenIsOpened();

    void isWelcomeScreenOpenedInSpanish();

    void isWelcomeScreenOpenedInItalian();

    void isWelcomeScreenOpenedInFrench();

    void isWelcomeScreenOpenedInGerman();

    void checkTranslationOnWelcomeScreen();

    void checkTranslationOnWelcomeScreenItalian();

    void checkTranslationOnWelcomeScreenFrench();

    void waitSeveralSeconds(Integer secondsCount);

    void checkEachTutorialPageCanBeOpened();

    void closeTutorialScreen();

    void closeWelcomeScreen();

    void closeWelcomeScreenInSpanish();

    void closeWelcomeScreenInItalian();

    void closeWelcomeScreenInFrench();

    void closeWelcomeScreenInGerman();

    void checkFindYourLibScreenTranslation();

    void checkFindYourLibScreenTranslationItalian();

    void checkFindYourLibScreenTranslationFrench();

    void checkFindYourLibScreenTranslationGerman();
}
