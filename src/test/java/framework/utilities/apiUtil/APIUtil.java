package framework.utilities.apiUtil;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

public class APIUtil {
    private static final String BASE_URL = "https://demo.lyrasistechnology.org";

    public static void returnBooks(String barcode, String pin) {
        String authHeader = getAuthHeader(barcode, pin);
        ArrayList<String> booksForReturning = getBooksForReturning(authHeader);
        sendRequestsForReturningBooks(authHeader, booksForReturning);
    }

    private static ArrayList<String> getBooksForReturning(String authHeader) {
        ArrayList<String> booksForReturning = new ArrayList<>();
        GetBooksAPIMethods getBooksAPIMethods = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JaxbConverterFactory.create()).
                client(new OkHttpClient()).build().create(GetBooksAPIMethods.class);
        Response<APIPageXMLModel> response = null;

        try {
            response = getBooksAPIMethods.getBooks(authHeader).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        APIPageXMLModel apiPageXMLModel = response.body();

        for (Entry entry2 : apiPageXMLModel.getEntries()) {
            boolean isLinkRevokePresent = entry2.getLinks().stream().anyMatch(entry -> entry.getHref().toLowerCase().contains("revoke"));

            if (!isLinkRevokePresent) {
                throw new RuntimeException("Entry does not have href with revoke");
            }

            String link = entry2.getLinks().stream().filter(entry -> entry.getHref().toLowerCase().contains("revoke")).findFirst().get().getHref();
            booksForReturning.add(link);
        }

        return booksForReturning;
    }

    private static void sendRequestsForReturningBooks(String authHeader, ArrayList<String> booksForReturning) {
        ReturnBooksAPIMethods getBooksAPIMethods = new Retrofit.Builder().baseUrl(BASE_URL).client(new OkHttpClient()).build().
                create(ReturnBooksAPIMethods.class);

        for (String bookUrl : booksForReturning) {
            String path = bookUrl.replace("https://demo.lyrasistechnology.org/", "");
            try {
                getBooksAPIMethods.returnBooks(authHeader, path).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getAuthHeader(String barcode, String pin) {
        String base = barcode + ":" + pin;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(base.getBytes());
        return authHeader;
    }
}
