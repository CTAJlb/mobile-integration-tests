package enums.localization.account;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum AccountScreenLoginStatus implements LocalizedValue {
    SIGN_IN("sign_in"),
    SIGN_OUT("sign_out");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("account.AccountScreenLoginStatus");

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