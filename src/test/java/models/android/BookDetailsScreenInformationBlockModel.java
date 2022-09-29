package models.android;

import enums.localization.bookdetails.BookDetailsScreenInformationBlockKeys;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class BookDetailsScreenInformationBlockModel {
    private BookDetailsScreenInformationBlockKeys key;
    private String value;

    public static BookDetailsScreenInformationBlockModel createBookDetailsScreenInformationBlockModel(
            Map<String, String> entry) {
        return new BookDetailsScreenInformationBlockModel()
                .setKey(BookDetailsScreenInformationBlockKeys.valueOf(entry.get("key")))
                .setValue(entry.get("value"));
    }
}
