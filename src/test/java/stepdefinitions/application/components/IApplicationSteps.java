package stepdefinitions.application.components;

public interface IApplicationSteps {

    void openApplication();

    void returnToPreviousScreen();

    void restartApp();

    void addAccountFromWelcomeScreen(String libraryName);
}
