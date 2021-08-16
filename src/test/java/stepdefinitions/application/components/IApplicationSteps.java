package stepdefinitions.application.components;

public interface IApplicationSteps {

    void returnToPreviousScreenForEpubAndPdf();

    void restartApp();

    void addAccountFromWelcomeScreen(String libraryName);
}
