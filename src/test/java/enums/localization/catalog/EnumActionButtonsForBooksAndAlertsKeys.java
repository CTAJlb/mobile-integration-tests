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
    READ("read"),
    RESERVE("reserve"),
    DELETE("delete"),
    RETURN("return"),
    CANCEL("cancel"),
    DOWNLOAD("download"),
    LISTEN("listen"),
    REMOVE("remove"),
    SIGN_OUT("signOut"),
    NOT_NOW("notNow"),
    OK("ok"),
    VIEW_SAMPLE("view_sample"),
    ALLOW("allow");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("catalog.ActionButtonsForBooksAndAlerts");

    private final String key;

    @Override
    public String i18n() {
        return localizationProvider.getLocalization(key);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return localizationProvider.getLocalization(key, locale);
    }
}