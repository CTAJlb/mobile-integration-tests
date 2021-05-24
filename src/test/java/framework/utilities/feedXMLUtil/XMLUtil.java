package framework.utilities.feedXMLUtil;

import aquality.appium.mobile.application.AqualityServices;
import constants.util.UtilConstants;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.RandomUtils;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jaxb.JaxbConverterFactory;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class XMLUtil {
    private static final String BASE_URL = "https://demo.lyrasistechnology.org";
    private static final String partOfURL = "lyrasis/crawlable";
    private HashMap<String, List<BookModel>> hashMapAvailableEbooks;
    private HashMap<String, List<BookModel>> hashMapAvailableAudiobooks;
    private HashMap<String, List<BookModel>> hashMapUnavailableEbooks;
    private HashMap<String, List<BookModel>> hashMapUnavailableAudiobooks;
    private ArrayList<BookModel> availableBooksAnyType;
    private ArrayList<BookModel> unavailableBooksAnyType;
    private ArrayList<BookModel> availablePdf;
    //private static XMLUtil xmlUtil;
    private final int randomValue;
    private final int connectTimeout = 120;
    private final int readTimeout = 120;
    private final int writeTimeout = 120;
    private final int threadSleepTime = 3000;

    public XMLUtil() {
        randomValue = RandomUtils.nextInt(0, 10);
        setHashMapsForEBooksAndAudioBooks();
    }

    public int getRandomValue(){
        return randomValue;
    }

    /*public static void setXmlUtil(XMLUtil xml){
        xmlUtil = xml;
    }

    public static synchronized XMLUtil getInstance() {
        if (xmlUtil == null) {
            xmlUtil = new XMLUtil();
        }
        return xmlUtil;
    }

    public static synchronized void instanceInitialization() {
        if (xmlUtil == null) {
            xmlUtil = new XMLUtil();
        }
    }*/

    //todo method does not use
    public void getStatistics() {
        HashMap<String, HashMap<String, List<BookModel>>> hashMap = new HashMap<>();
        hashMap.put("AvailableEbooks".toLowerCase(), hashMapAvailableEbooks);
        hashMap.put("AvailableAudiobooks".toLowerCase(), hashMapAvailableAudiobooks);
        hashMap.put("UnavailableEbooks".toLowerCase(), hashMapUnavailableEbooks);
        hashMap.put("UnavailableAudiobooks".toLowerCase(), hashMapUnavailableAudiobooks);
        for (Map.Entry<String, HashMap<String, List<BookModel>>> mapOfSpecificType : hashMap.entrySet()) {
            String typeAndAvailabilityOfBooks = mapOfSpecificType.getKey();
            AqualityServices.getLogger().info("typeAndAvailabilityOfBooks: " + typeAndAvailabilityOfBooks);
            for (Map.Entry<String, List<BookModel>> map : mapOfSpecificType.getValue().entrySet()) {
                AqualityServices.getLogger().info("Statistics: ");
                AqualityServices.getLogger().info("Distributor: " + map.getKey());
                AqualityServices.getLogger().info("CountOfBooks: " + map.getValue().size());
                AqualityServices.getLogger().info("+++++++++++++++++++++++++++++++++++++++++++++++");
            }
        }
    }

    private void setListAvailableAndUnavailableBooksAnyTypeMayBeWithRepeat() {
        String url = partOfURL;
        ArrayList<BookModel> listAvailableBooksAnyType = new ArrayList<>();
        ArrayList<BookModel> listUnavailableBooksAnyType = new ArrayList<>();
        ArrayList<BookModel> listAvailablePdf = new ArrayList<>();

        while (true) {
            FeedModel feedModel = supportMethod(url);
            boolean isNextXMLPresent = feedModel.getLinksFromFeed().stream().anyMatch(link -> link.getConditionForNextXML().equals(UtilConstants.NEXT.toLowerCase()));
            if (!isNextXMLPresent) {
                break;
            }

            for (EntryXML entry : feedModel.getEntries()) {
                boolean isEnLanguagePresent = entry.getLanguage().toLowerCase().equals("en");
                if (!isEnLanguagePresent) {
                    continue;
                }

                boolean isPdfTypePresent = entry.getLinksFromEntry().stream().anyMatch(link -> link.getIndirectAcquisition() != null);
                boolean isPdfAvailabilityPresent = entry.getLinksFromEntry().stream().anyMatch(link -> link.getAvailabilityPDF() != null);

                if (isPdfTypePresent && isPdfAvailabilityPresent) {
                    String pdfType = entry.getLinksFromEntry().stream().filter(link -> link.getAvailabilityPDF() != null).findFirst().get().getIndirectAcquisition().getType();
                    String pdfAvailability = entry.getLinksFromEntry().stream().filter(link -> link.getAvailabilityPDF() != null).findFirst().get().getAvailabilityPDF().getStatus();

                    if (pdfType.toLowerCase().equals("application/pdf".toLowerCase()) && pdfAvailability.toLowerCase().equals("available".toLowerCase())) {
                        String[] arrayBookType = entry.getBookType().split("/");
                        String bookType = arrayBookType[arrayBookType.length - 1];
                        BookModel bookModel = new BookModel(entry.getDistributor().getDistributorName().toLowerCase(), bookType.toLowerCase(), entry.getBookName(), 0);//todo countAvailableCopies is not zero status == available
                        listAvailablePdf.add(bookModel);
                        continue;
                    }
                }

                boolean isCopiesPresent = entry.getLinksFromEntry().stream().anyMatch(link -> link.getCopies() != null);

                if (!isCopiesPresent) {
                    continue;
                }

                boolean isPdfAndVndAdobeAdeptPresent = false;
                boolean isIndirectAcquisitionTagPresent = entry.getLinksFromEntry().stream().anyMatch(link -> link.getIndirectAcquisition() != null);
                if (isIndirectAcquisitionTagPresent) {
                    LinkFromEntry linkFromEntry = entry.getLinksFromEntry().stream().filter(link -> link.getIndirectAcquisition() != null).findFirst().get();
                    if (linkFromEntry.getIndirectAcquisition().getType().toLowerCase().contains("vnd.adobe.adept+xml".toLowerCase())) {
                        boolean isPdfPresent = linkFromEntry.getIndirectAcquisition().getInternalIndirectAcquisition().getType().toLowerCase().contains("pdf".toLowerCase());
                        if (isPdfPresent) {
                            isPdfAndVndAdobeAdeptPresent = true;
                        }
                    }
                }

                boolean isLibrarySimplifiedPresent = false;
                if (isIndirectAcquisitionTagPresent) {
                    LinkFromEntry linkFromEntry = entry.getLinksFromEntry().stream().filter(link -> link.getIndirectAcquisition() != null).findFirst().get();
                    isLibrarySimplifiedPresent = linkFromEntry.getIndirectAcquisition().getType().toLowerCase().contains("vnd.librarysimplified.axisnow+json".toLowerCase());
                }

                if (isPdfAndVndAdobeAdeptPresent || isLibrarySimplifiedPresent) {
                    continue;
                }

                int countAvailableCopies = entry.getLinksFromEntry().stream().filter(link -> link.getCopies() != null).findFirst().get().getCopies().getCountAvailableCopies();

                if (countAvailableCopies > 0) {
                    String[] arrayBookType = entry.getBookType().split("/");
                    String bookType = arrayBookType[arrayBookType.length - 1];
                    listAvailableBooksAnyType.add(new BookModel(entry.getDistributor().getDistributorName().toLowerCase(), bookType.toLowerCase(), entry.getBookName(), countAvailableCopies));
                } else if (countAvailableCopies == 0) {
                    String[] arrayBookType = entry.getBookType().split("/");
                    String bookType = arrayBookType[arrayBookType.length - 1];
                    listUnavailableBooksAnyType.add(new BookModel(entry.getDistributor().getDistributorName().toLowerCase(), bookType.toLowerCase(), entry.getBookName(), countAvailableCopies));
                }
            }

            String nextUrl = feedModel.getLinksFromFeed().stream().filter(link -> link.getConditionForNextXML().equals("next")).findFirst().get().getNextURLForXML();
            url = nextUrl.replace(BASE_URL + "/", "");
        }

        availableBooksAnyType = listAvailableBooksAnyType;
        unavailableBooksAnyType = listUnavailableBooksAnyType;
        availablePdf = getListAvailablePdfWithoutRepetitions(listAvailablePdf);
    }

    private ArrayList<BookModel> getListAvailablePdfWithoutRepetitions(ArrayList<BookModel> arrayList) {
        Set<BookModel> setAvailablePdf = arrayList.stream().collect(Collectors.toSet());
        ArrayList<BookModel> arrayListAvailablePdf = new ArrayList<>(setAvailablePdf);

        return arrayListAvailablePdf;
    }

    public synchronized String getRandomPdf() {
        if (availablePdf.size() == 0) {
            throw new RuntimeException("availablePdf.size() == 0, There is not available pdf");
        }

        BookModel randomBookModel = availablePdf.get(RandomUtils.nextInt(0, availablePdf.size()));
        String pdf = randomBookModel.getBookName();

        availablePdf.remove(randomBookModel);

        return pdf;
    }

    public synchronized String getRandomBook(String availabilityType, String bookType, String distributor) {
        HashMap<String, List<BookModel>> hashMap = null;
        if (availabilityType.toLowerCase().equals(UtilConstants.AVAILABLE.toLowerCase())) {
            if (bookType.toLowerCase().equals(UtilConstants.EBOOK.toLowerCase())) {
                hashMap = hashMapAvailableEbooks;
            } else if (bookType.toLowerCase().equals(UtilConstants.AUDIOBOOK.toLowerCase())) {
                hashMap = hashMapAvailableAudiobooks;
            }
        } else if (availabilityType.toLowerCase().equals(UtilConstants.UNAVAILABLE.toLowerCase())) {
            if (bookType.toLowerCase().equals(UtilConstants.EBOOK.toLowerCase())) {
                hashMap = hashMapUnavailableEbooks;
            } else if (bookType.toLowerCase().equals(UtilConstants.AUDIOBOOK.toLowerCase())) {
                hashMap = hashMapUnavailableAudiobooks;
            }
        }
        if (!hashMap.containsKey(distributor.toLowerCase())) {
            throw new RuntimeException("There are not any type books for distributor: " + distributor);
        }

        if (hashMap.get(distributor.toLowerCase()).size() == 0) {
            throw new RuntimeException("Count of  " + availabilityType + " books == 0 for distributor: " + distributor);
        }

        String bookName = hashMap.get(distributor.toLowerCase()).get(RandomUtils.nextInt(0, hashMap.get(distributor.toLowerCase()).size())).getBookName();

        List<BookModel> list = hashMap.get(distributor.toLowerCase());
        for (int i = 0; i < list.size(); i++) {
            BookModel bookModel = list.get(i);
            if (bookModel.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                list.remove(bookModel);
                hashMap.put(distributor.toLowerCase(), list);
                break;
            }
        }

        return bookName;
    }

    private FeedModel supportMethod(String url) {
        FeedModel feedModel = null;
        int sch = 1;

        while (sch < 4) {
            feedModel = getFeedModel(url);
            if (feedModel == null) {
                try {
                    Thread.sleep(threadSleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                break;
            }
            sch++;
        }

        if (sch == 3) {
            throw new RuntimeException("Bad Response, problem with server, Count of attempts: " + sch);
        }

        if (feedModel == null) {
            throw new RuntimeException("Bad Response, problem with server, feedModel == null");
        }

        return feedModel;
    }

    private void setHashMapsForEBooksAndAudioBooks() {
        setListAvailableAndUnavailableBooksAnyTypeMayBeWithRepeat();
        hashMapAvailableEbooks = getHashMapForAvailableAndUnavailableBooksWithSpecificType(availableBooksAnyType, UtilConstants.EBOOK.toLowerCase());
        hashMapAvailableAudiobooks = getHashMapForAvailableAndUnavailableBooksWithSpecificType(availableBooksAnyType, UtilConstants.AUDIOBOOK.toLowerCase());
        hashMapUnavailableEbooks = getHashMapForAvailableAndUnavailableBooksWithSpecificType(unavailableBooksAnyType, UtilConstants.EBOOK.toLowerCase());
        hashMapUnavailableAudiobooks = getHashMapForAvailableAndUnavailableBooksWithSpecificType(unavailableBooksAnyType, UtilConstants.AUDIOBOOK.toLowerCase());
    }

    private HashMap<String, List<BookModel>> getHashMapForAvailableAndUnavailableBooksWithSpecificType(ArrayList<BookModel> arrayList, String bookType) {

        Set<String> setDistributors = arrayList.stream().map(book -> book.getDistributorName()).collect(Collectors.toSet());

        HashMap<String, List<BookModel>> hashMap = new HashMap<>();

        for (String distributor : setDistributors) {
            Set<BookModel> setBooks = arrayList.stream().filter(book -> book.getDistributorName().toLowerCase().equals(distributor.toLowerCase())).collect(Collectors.toSet());
            Set<BookModel> setBooksSpecificBookType = setBooks.stream().filter(book -> book.getBookType().toLowerCase().equals(bookType.toLowerCase())).collect(Collectors.toSet());

            hashMap.put(distributor.toLowerCase(), new ArrayList<>(setBooksSpecificBookType));
        }

        return hashMap;
    }

    private FeedModel getFeedModel(String url) {
        OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).writeTimeout(writeTimeout, TimeUnit.SECONDS).build();
        XMLAPIMethods xmlapiMethods = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JaxbConverterFactory.create()).
                client(client).build().create(XMLAPIMethods.class);

        Response<FeedModel> response = null;
        try {
            response = xmlapiMethods.getFeed(url).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (response.body() == null) {
            AqualityServices.getLogger().info("ResponseCode: " + response.code());
            AqualityServices.getLogger().info("ResponseToString: " + response.toString());
        }

        return response.body();
    }
}
