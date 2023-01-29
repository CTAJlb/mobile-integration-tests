package enums.localization.facetedsearch;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum FacetSortByKeys implements LocalizedValue {
    TITLE("title"),
    AUTHOR("author"),
    RANDOM("random"),
    RECENTLY_ADDED("recently_added");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("facetedSearch.FacetSortByKeys");

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