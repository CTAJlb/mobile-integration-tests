package framework.utilities.feedXMLUtil;

public class GettingBookUtil {
    private static XMLUtil xmlUtil;

    public static void setXmlUtil(XMLUtil xml){
        xmlUtil = xml;
    }

    public static synchronized String getRandomBook(String availabilityType, String bookType, String distributor){
        return xmlUtil.getRandomBook(availabilityType, bookType, distributor);
    }

    public static synchronized String getRandomPdf(){
        return xmlUtil.getRandomPdf();
    }

    public static int getRandomValue(){
        return xmlUtil.getRandomValue();
    }
}
