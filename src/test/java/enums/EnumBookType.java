package enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumBookType {
    EBOOK("ebook"),
    AUDIOBOOK("audiobook"),
    PDF("pdf");

    private String bookType;

    public String getBookType() {
        return bookType;
    }
}
