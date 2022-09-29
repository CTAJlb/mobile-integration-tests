package enums.timeouts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CategoriesTimeouts {
    TIMEOUT_WAIT_UNTIL_CATEGORY_PAGE_LOAD(180000);

    private long timeoutMillis;
}