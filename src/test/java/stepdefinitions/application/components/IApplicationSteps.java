package stepdefinitions.application.components;

public interface IApplicationSteps {

    void returnToPreviousScreenForEpubAndPdf();

    void restartApp();

    void addAccountFromWelcomeScreen(String libraryName);

    void checkThatTutorialScreenIsOpened();

    void checkWelcomeScreenIsOpened();

    void waitSeveralSeconds(Integer secondsCount);

    void checkEachTutorialPageCanBeOpened();
}
