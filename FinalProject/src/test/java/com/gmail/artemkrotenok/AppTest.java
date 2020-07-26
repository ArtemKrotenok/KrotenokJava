package com.gmail.artemkrotenok;

import com.gmail.artemkrotenok.service.NewsService;
import com.gmail.artemkrotenok.service.impl.NewsJsonUrlServiceImpl;
import com.gmail.artemkrotenok.service.impl.NewsXmlUrlServiceImpl;
import com.gmail.artemkrotenok.service.model.SummaryNews;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    private static final String EXAMPLE_URL_JSON = "https://fake-api.kiparo.by/json/it_news.json";
    private static final String EXAMPLE_URL_XML = "https://fake-api.kiparo.by/json/it_news.xml";
    private static final NewsService newsServiceJson = new NewsJsonUrlServiceImpl();
    private static final NewsService newsServiceXml = new NewsXmlUrlServiceImpl();


    @Test
    public void JSON_NewsFromSource_return_not_NULL() {
        SummaryNews summaryNews = newsServiceJson.getNewsFromSource(EXAMPLE_URL_JSON);
        assertNotNull(summaryNews);
    }

    @Test
    public void XML_NewsFromSource_return_not_NULL() {
        SummaryNews summaryNews = newsServiceXml.getNewsFromSource(EXAMPLE_URL_XML);
        assertNotNull(summaryNews);
    }

}