package com.gmail.artemkrotenok;

import com.gmail.artemkrotenok.service.NewsService;
import com.gmail.artemkrotenok.service.impl.NewsDownloadFromUrl;
import com.gmail.artemkrotenok.service.impl.NewsParsingJsonImpl;
import com.gmail.artemkrotenok.service.impl.NewsParsingXmlImpl;
import com.gmail.artemkrotenok.service.impl.NewsServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {
    private static final String EXAMPLE_URL_JSON = "https://fake-api.kiparo.by/json/it_news.json";
    private static final String EXAMPLE_URL_XML = "https://fake-api.kiparo.by/json/it_news.xml";
    private static final NewsService newsServiceJson = new NewsServiceImpl(
            new NewsDownloadFromUrl(),
            new NewsParsingJsonImpl(),
            EXAMPLE_URL_JSON);
    private static final NewsService newsServiceXml = new NewsServiceImpl(
            new NewsDownloadFromUrl(),
            new NewsParsingXmlImpl(),
            EXAMPLE_URL_XML);

    @Test
    public void JSON_NewsFromSource_return_not_NULL() {
        assertNotNull(newsServiceJson.getAllInformationSortByDate());
    }

    @Test
    public void XML_NewsFromSource_return_not_NULL() {
        assertNotNull(newsServiceXml.getAllInformationSortByDate());
    }

}