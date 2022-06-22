package constants;

public class RegEx {
    public static final String PAGE_NUMBER_AND_CHAPTER_NAME_REGEX_FOR_IOS = "Page (\\d+) of (\\d+) (\\w.+)";
    public static final String PAGE_NUMBER_REGEX_FOR_ANDROID = "Page (\\d+) of (\\d+)";
    public static final String FONT_SIZE_REGEX_ANDROID = "--USER__fontSize:(\\d+)%;";
    public static final String FONT_NAME_REGEX_ANDROID = "--USER__fontFamily:(.+?);";
    public static final String BACKGROUND_COLOR_REGEX_ANDROID = "--USER__appearance:(.+?);";
    public static final String AUDIO_BOOK_CURRENT_CHAPTER_TEXT_REGEX = "(.+)\\s\\(.+\\)";
    public static final String AUDIOBOOK_CURRENT_FILE_NUMBER = "\\d+";
    public static final String PDF_CURRENT_PAGE_REGEX = "(\\d+)\\/\\d+";
    public static final String FONT_SIZE_REGEX_IOS = "--USER__fontSize: (\\d+.\\d+)%;";
    public static final String FONT_NAME_REGEX_IOS = "--USER__fontFamily: (.+?);";
    public static final String BACKGROUND_COLOR_REGEX_IOS = "--USER__appearance: (.+?);";
    public static final String VALID_SYMBOLS_IN_NAMES = "^[a-zA-Z0-9\\s]*$";
    public static final String UNNECESSARY_SYMBOLS = "[&/.,;:'\\s]";
    public static final String VALID_AUTHOR_OR_TITLE_OR_PUBLISHER = "([a-zA-Z-]*)([&,;\\s]*)([a-zA-Z-]*)";
    public static final String VALID_CATEGORY_NAME = "([a-zA-Z0-9\\s]*)([&\\s]*)([a-zA-Z0-9\\s]*)";
}
