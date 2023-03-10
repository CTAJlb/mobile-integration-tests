package enums.localization.catalog;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum EnumActionButtonsForBooksAndAlertsKeys implements LocalizedValue {
    GET("get"),
    GET_ES("get_es"),
    READ("read"),
    READ_ES("read_es"),
    RESERVE("reserve"),
    DELETE("delete"),
    RETURN("return"),
    RETURN_ES("return_es"),
    CANCEL("cancel"),
    DOWNLOAD("download"),
    LISTEN("listen"),
    LISTEN_ES("listen_es"),
    REMOVE("remove"),
    SIGN_OUT("signOut"),
    NOT_NOW("notNow"),
    OK("ok"),
    VIEW_SAMPLE("view_sample"),
    PLAY_SAMPLE("play_sample"),
    ALLOW("allow");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("catalog.ActionButtonsForBooksAndAlerts");

    private final String key;

    @Override
    public String getDefaultLocalizedValue() {
        return localizationProvider.getLocalization(key);
    }

    @Override
    public String getLocalizedValueOfSpecificLocale(@NonNull Locale locale) {
        return localizationProvider.getLocalization(key, locale);
    }
}