package constants;

public class RegEx {
    public static final String PAGE_NUMBER_REGEX = "Page (\\d+) of \\d+";
    public static final String FONT_SIZE_REGEX = "--USER__fontSize:(\\d+)%;";
    public static final String FONT_NAME_REGEX = "--USER__fontFamily:(.+?);";
    public static final String FONT_COLOR_REGEX = " color: (\\S* \\S*);";
    public static final String FONT_AND_BACKGROUND = "--USER__appearance:(.+?);";
    public static final String AUDIO_BOOK_CURRENT_CHAPTER_TEXT_REGEX = "(.+)\\s\\(.+\\)";
    public static final String PDF_CURRENT_PAGE_REGEX = "(\\d+)\\/\\d+";
}
