package stepdefinitions.credentials.components;

public interface ICredentialsSteps {
    void checkLoginIsPerformedSuccessfully();

    void isLogoutSuccessfully();

    void clickLogOut();

    void enterCredentialsForLibraryAccount(String libraryName);
}
