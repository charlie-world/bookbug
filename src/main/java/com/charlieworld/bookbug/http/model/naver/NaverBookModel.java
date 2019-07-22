package com.charlieworld.bookbug.http.model.naver;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement(name = "rss")
public class NaverBookModel {

    private int total;
    private int start;
    private int display;

    private List<Item> items;

    @XmlElementWrapper(name="channel")
    @XmlElement(name = "item")
    public List<Item> getItems() {
        return this.items;
    }

    @XmlElement(name = "channel.total")
    public int getTotal() {
        return this.total;
    }

    @XmlElement(name = "channel.total")
    public int getStart() {
        return this.start;
    }

    @XmlElement(name = "channel.total")
    public int getDisplay() {
        return this.display;
    }
}
