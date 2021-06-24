package constants.localization.application.reader;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.NonNull;

import java.util.Locale;

public enum ReaderSettingKeys implements LocalizedValue {
    FONT_SERIF("font_serif"),
    FONT_DYSLEXIC("font_dyslexic"),
    FONT_SANS("font_sans"),
    WHITE_TEXT_ON_BLACK("white_text_on_black"),
    BLACK_TEXT_ON_WHITE("black_text_on_white"),
    BLACK_TEXT_ON_SEPIA("black_text_on_sepia"),
    DECREASE_FONT("decrease_font"),
    INCREASE_FONT("increase_font"),
    RESET_FONT("reset_font");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("reader.ReaderSettingKeys");
    private String key;

    ReaderSettingKeys(String key) {
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
