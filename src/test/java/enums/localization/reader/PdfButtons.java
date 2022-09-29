package enums.localization.reader;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum PdfButtons implements LocalizedValue {
    CHAPTERS("chapters"),
    SEARCH("search");

    private String button;
    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("reader.PdfButtons");

    @Override
    public String i18n() {
        return localizationProvider.getLocalization(button);
    }

    @Override
    public String i18n(@NonNull Locale locale) {
        return localizationProvider.getLocalization(button, locale);
    }
}
