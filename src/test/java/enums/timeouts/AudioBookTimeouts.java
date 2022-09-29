package enums.timeouts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AudioBookTimeouts {
    TIMEOUT_AUDIO_BOOK_LOADER_DISAPPEAR(120000);

    private long timeoutMillis;

    private long pollingMillis;

    AudioBookTimeouts(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }
}