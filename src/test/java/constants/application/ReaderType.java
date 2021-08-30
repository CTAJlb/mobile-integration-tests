package constants.application;

public enum ReaderType {
    EBOOK("Book"),
    AUDIOBOOK("Audio");

    private String bookType;

    public String getBookType() {
        return bookType;
    }

    ReaderType(String bookType) {
        this.bookType = bookType;
    }
}
