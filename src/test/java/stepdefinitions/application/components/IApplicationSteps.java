package stepdefinitions.application.components;

public interface IApplicationSteps {

    void turnOnTestMode(String libraryName);

    void returnToPreviousScreenForEpubAndPdf();

    void restartApp();

    void addAccountFromWelcomeScreen(String libraryName);

    void checkThatTutorialScreenIsOpened();

    void checkWelcomeScreenIsOpened();

    void checkTranslationOnWelcomeScreen();

    void waitSeveralSeconds(Integer secondsCount);

    void checkEachTutorialPageCanBeOpened();

    void closeTutorialScreen();

    void closeWelcomeScreen();
}
