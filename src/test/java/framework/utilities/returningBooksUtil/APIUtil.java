package framework.utilities.returningBooksUtil;

import aquality.appium.mobile.application.AqualityServices;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

public class APIUtil {
    private static final String BASE_URL = "https://demo.lyrasistechnology.org";
    private static final int connectTimeout = 120;
    private static final int readTimeout = 120;
    private static final int writeTimeout = 120;

    public static void returnBooks(String barcode, String pin) {
        String authHeader = getAuthHeader(barcode, pin);
        AqualityServices.getLogger().info("There are books on the account for returning: ");
        ArrayList<String> booksForReturning = getListOfBooksInAccount(authHeader);
        AqualityServices.getLogger().info("Count of books on the account for returning: " + booksForReturning.size());
        sendRequestsForReturningBooks(authHeader, booksForReturning);
    }

    private static ArrayList<String> getListOfBooksInAccount(String authHeader) {
        ArrayList<String> booksForReturning = new ArrayList<>();
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).writeTimeout(writeTimeout, TimeUnit.SECONDS).build();
        GetBooksAPIMethods getBooksAPIMethods = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JaxbConverterFactory.create()).
                client(client).build().create(GetBooksAPIMethods.class);
        Response<APIPageXMLModel> response = null;

        try {
            response = getBooksAPIMethods.getBooks(authHeader).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        APIPageXMLModel apiPageXMLModel = response.body();

        if (response.body() == null){
            AqualityServices.getLogger().info("APIUtilResponseCode: " + response.code());
            AqualityServices.getLogger().info("APIUtilResponseToString: " + response.toString());
            throw new RuntimeException("Bad Response, problem with server");
        }

        boolean isEntryPresentInXml = response.body().getEntries() == null;

        if (!isEntryPresentInXml) {
            for (Entry entry : apiPageXMLModel.getEntries()) {
                boolean isRevokePresentInLink = entry.getLinks().stream().anyMatch(link -> link.getHref().toLowerCase().contains("revoke"));
                if(!isRevokePresentInLink){
                    AqualityServices.getLogger().info("Books without revoke in link. BookName: " + entry.getTitle() + " Distributor: " + entry.getDistributor());
                    continue;
                }
                AqualityServices.getLogger().info("bookName: " + entry.getTitle() + " Distributor: " + entry.getDistributor().getDistributorName());
                String link = entry.getLinks().stream().filter(ref -> ref.getHref().toLowerCase().contains("revoke")).findFirst().get().getHref();
                booksForReturning.add(link);
            }
        } else {
            AqualityServices.getLogger().info("There is not books on the account.");
        }

        return booksForReturning;
    }

    public static void enterBookAfterOpeningAccount(String barcode, String pin) {
        String authHeader = getAuthHeader(barcode, pin);
        AqualityServices.getLogger().info("There are books on the account after opening account: ");
        ArrayList<String> listOfBooksAfterOpeningAccount = getListOfBooksInAccount(authHeader);
        AqualityServices.getLogger().info("Count of books on the account after opening account: " + listOfBooksAfterOpeningAccount.size());
    }

    public static void enterBooksAfterReturningBooks(String barcode, String pin) {
        String authHeader = getAuthHeader(barcode, pin);
        AqualityServices.getLogger().info("There are books on the account after returning books: ");
        ArrayList<String> listOfBooksAfterReturningBooks = getListOfBooksInAccount(authHeader);
        AqualityServices.getLogger().info("Count of books on the account after returning books: " + listOfBooksAfterReturningBooks.size());
    }

    private static void sendRequestsForReturningBooks(String authHeader, ArrayList<String> booksForReturning) {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).writeTimeout(writeTimeout, TimeUnit.SECONDS).build();
        ReturnBooksAPIMethods getBooksAPIMethods = new Retrofit.Builder().baseUrl(BASE_URL).client(client).build().
                create(ReturnBooksAPIMethods.class);

        if (booksForReturning.size() != 0) {
            for (String bookUrl : booksForReturning) {
                String path = bookUrl.replace(BASE_URL + "/", "");
                try {
                    getBooksAPIMethods.returnBooks(authHeader, path).execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static String getAuthHeader(String barcode, String pin) {
        String base = barcode + ":" + pin;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(base.getBytes());
        return authHeader;
    }
}
