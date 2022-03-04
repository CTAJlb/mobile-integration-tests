package constants.localization.application.catalog;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.NonNull;

import java.util.Locale;

public enum EnumActionButtonsForBooksAndAlertsKeys implements LocalizedValue {
    GET("get"),
    READ("read"),
    RESERVE("reserve"),
    DELETE("delete"),
    RETURN("return"),
    DOWNLOAD("download"),
    LISTEN("listen"),
    REMOVE("remove"),
    SIGN_OUT("signOut"),
    NOT_NOW("notNow"),
    CANCEL("cancel"),
    OK("ok");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("catalog.ActionButtonsForBooksAndAlerts");

    private final String key;


    EnumActionButtonsForBooksAndAlertsKeys(String key) {
        this.key = key;
    }

    @Override
    public String i18n() {
        return localizationProvider.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return localizationProvider.getLocalization(key, locale);
    }
}