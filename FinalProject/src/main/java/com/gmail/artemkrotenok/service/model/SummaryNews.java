package com.gmail.artemkrotenok.service.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)

public class SummaryNews implements Serializable {

    private String name;
    private String location;
    @XmlElementWrapper(name = "news")
    @XmlElement(name = "element")
    private List<Information> news;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Information> getNews() {
        return news;
    }

    public void setNews(List<Information> news) {
        this.news = news;
    }
}