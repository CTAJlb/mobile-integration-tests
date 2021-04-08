package framework.utilities.apiUtil;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "feed")
public class APIPageXMLModel {
    private List<Entry> entries;

    public List<Entry> getEntries() {
        return entries;
    }

    @XmlElement(name = "entry")
    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}

class Entry {
    private List<Link> links;

    public List<Link> getLinks() {
        return links;
    }

    @XmlElement(name = "link")
    public void setLinks(List<Link> links) {
        this.links = links;
    }
}

class Link {
    private String href;

    public String getHref() {
        return href;
    }

    @XmlAttribute(name = "href")
    public void setHref(String href) {
        this.href = href;
    }
}
