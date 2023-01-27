package enums.localization.translation;

import constants.localization.providers.AbstractILocalizationProvider;
import constants.localization.providers.LocalizationProviderFactory;
import constants.localization.providers.LocalizedValue;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.Locale;

@AllArgsConstructor
public enum Spanish implements LocalizedValue {

    FIND_YOUR_LIBRARY("find_your_library"),
    ADD_ACCOUNT("add_account"),
    BACK("back"),
    CATALOG("catalog"),
    MY_BOOKS("my_books"),
    RESERVATIONS("reservations"),
    SETTINGS("settings"),
    MORE("more"),
    ALL("all"),
    EBOOKS("ebooks"),
    AUDIOBOOKS("audiobooks"),
    SEARCH("search"),
    ADD_LIBRARY("add_library"),
    CANCEL("cancel"),
    AVAILABILITY("availability"),
    SORT_BY("sort_by"),
    COLLECTION("collection"),
    TITLE("title"),
    RECENTLY_ADDED("recently_added"),
    AUTHOR("author"),
    AVAILABLE_NOW("available_now"),
    YOURS_TO_KEEP("yours_to_keep"),
    POPULAR_BOOKS("popular_books"),
    EVERYTHING("everything"),
    GET("get"),
    RESERVE("reserve");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("translation.Spanish");

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