package constants.application;

public enum EnumBookType {
    EBOOK("ebook"),
    AUDIOBOOK("audiobook");

    private String bookType;

    public String getBookType() {
        return bookType;
    }

    EnumBookType(String bookType) {
        this.bookType = bookType;
    }
}
