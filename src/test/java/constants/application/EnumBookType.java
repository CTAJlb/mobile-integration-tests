package constants.application;

public enum EnumBookType {
    EBOOK("ebook"),
    AUDIOBOOK("audiobook"),
    PDF("pdf");

    private String bookType;

    public String getBookType() {
        return bookType;
    }

    EnumBookType(String bookType) {
        this.bookType = bookType;
    }
}
