package enums.timeouts;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Duration;

@AllArgsConstructor
public enum CategoriesTimeouts {
    TIMEOUT_WAIT_UNTIL_CATEGORY_PAGE_LOAD(Duration.ofMillis(180000));

    private Duration timeoutMillis;

    public Duration getTimeoutMillis() {
        return timeoutMillis;
    }
}