package enums.timeouts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PdfTimeouts {
    PDF_LIST_OF_CHAPTERS_APPEAR_TIMEOUT(120000, 1000);

    private long timeoutMillis;
    private long pollingMillis;

    PdfTimeouts(long timeoutMillis) {
        this.timeoutMillis = timeoutMillis;
    }
}