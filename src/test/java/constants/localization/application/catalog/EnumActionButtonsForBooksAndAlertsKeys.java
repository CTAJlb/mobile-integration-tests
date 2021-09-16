package constants.localization.application.catalog;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.NonNull;

import java.util.Locale;

public enum EnumActionButtonsForBooksAndAlertsKeys implements LocalizedValue {
    GET("get"),
    DOWNLOAD("download"),
    READ("read"),
    RESERVE("reserve"),
    DELETE("delete"),
    RETURN("return"),
    LISTEN("listen"),
    NOT_NOW("notNow"),
    DO_NOT_ALLOW("doNotAllow"),
    CANCEL_RESERVATION("cancelReservation"),
    CANCEL_POPUP("cancelPopup");

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