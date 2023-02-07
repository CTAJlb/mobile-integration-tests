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
    RESERVE("reserve"),
    BOOK_AVAILABILITY("book_availability"),
    DESCRIPTION("description"),
    INFORMATION("information"),
    PUBLISHED("published"),
    PUBLISHER("publisher"),
    PAGE("page"),
    TOC("toc"),
    CONTENTS("contents"),
    BOOKMARKS("bookmarks"),
    INFO_BOOKMARKS("info_bookmarks"),
    LIBRARIES("libraries"),
    ABOUT_PALACE("about_palace"),
    PRIVACY_POLICY("privacy_policy"),
    USER_AGREEMENT("user_agreement"),
    SOFTWARE_LICENSES("software_licenses"),
    RESERVE_INFO("reserve_info"),
    ACCOUNT("account"),
    LIBRARY_CARD("library_card"),
    LOG_IN("log_in"),
    PASSWORD("password"),
    FORGET_PASSWORD("forget_password"),
    USER_AGREEMENT_LINK("user_agreement_link"),
    NO_ACCOUNT("no_account"),
    CREATE_CARD("create_card"),
    REPORT_A_PROBLEM("report_a_problem"),
    CONTENT_LICENSES("content_licenses"),
    DONE("done"),
    SEARCH_PDF("search_pdf"),
    RESUME("resume"),
    NO_BOOKMARKS("no_bookmarks"),
    LINE_REMAINING("line_remaining"),
    PLAYBACK_SPEED("playback_speed"),
    SLEEP_TIMER("sleep_timer"),
    OFF("off"),
    END_OF_CHAPTER("end_of_chapter");

    private static final AbstractILocalizationProvider localizationProvider =
            LocalizationProviderFactory.getProvider("translation.Spanish");

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