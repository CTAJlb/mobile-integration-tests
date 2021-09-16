package constants.keysForContext;

public enum ContextLibrariesKeys {
    LOG_OUT("librariesForLogOut");

    private final String key;

    ContextLibrariesKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
