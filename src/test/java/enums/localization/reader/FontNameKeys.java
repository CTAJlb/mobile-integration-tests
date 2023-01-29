package enums.localization.reader;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum FontNameKeys implements LocalizedValue {
    FONT_SERIF("font_serif"),
    FONT_DYSLEXIC("font_dyslexic"),
    FONT_SANS("font_sans");

    private String fontName;
    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("reader.FontNameKeys");

    @Override
    public String getDefaultLocalizedValue() {
        return localizationProvider.getLocalization(fontName);
    }

    @Override
    public String getLocalizedValueOfSpecificLocale(@NonNull Locale locale) {
        return localizationProvider.getLocalization(fontName, locale);
    }
}
