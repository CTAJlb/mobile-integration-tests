package enums.localization.bookdetails;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum BookDetailsScreenInformationBlockKeys implements LocalizedValue {
    PUBLISHED("published"),
    PUBLISHER("publisher"),
    DISTRIBUTOR("distributor"),
    CATEGORIES("categories"),
    UPDATED("updated");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("bookdetals.BookDetailsScreenInformationBlockKeys");

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
