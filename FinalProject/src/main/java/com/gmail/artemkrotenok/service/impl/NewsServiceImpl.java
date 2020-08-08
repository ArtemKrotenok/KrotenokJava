package com.gmail.artemkrotenok.service.impl;

import com.gmail.artemkrotenok.service.NewsGet;
import com.gmail.artemkrotenok.service.NewsParsing;
import com.gmail.artemkrotenok.service.NewsService;
import com.gmail.artemkrotenok.service.model.Information;
import com.gmail.artemkrotenok.service.model.SummaryNews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.invoke.MethodHandles;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class NewsServiceImpl implements NewsService {
    private static final String PARSING_PATTERN_DATE_TAME = "yyyy-MM-dd HH:mm:ss Z";
    private static final String PRINT_FORMAT_PATTERN_DATE_TAME = "HH:mm:ss dd LLLL yyyy";
    private final NewsGet newsGet;
    private final NewsParsing newsParsing;
    private final String addressSource;
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    public NewsServiceImpl(NewsGet newsGet, NewsParsing newsParsing, String addressSource) {
        this.newsGet = newsGet;
        this.newsParsing = newsParsing;
        this.addressSource = addressSource;
    }

    @Override
    public List<String> getAllInformationSortByDate() {
        String downloadData = newsGet.getNewsFromSource(addressSource);
        if (downloadData == null) {
            logger.error("Cannot download data, check address !");
            return Collections.emptyList();
        }
        SummaryNews summaryNews = newsParsing.getSummaryNewsFromText(downloadData);
        List<Information> outInformationList = summaryNews.getNews();
        outInformationList = sortByDate(outInformationList);
        List<String> resultList = new ArrayList<>();
        for (Information information : outInformationList) {
            resultList.add(formattedInformation(information));
        }
        return resultList;
    }

    private List<Information> sortByDate(List<Information> outInformationList) {
        outInformationList.sort(new Comparator<Information>() {
            public int compare(Information articleA, Information articleB) {
                return articleA.getDate().compareTo(articleB.getDate());
            }
        });
        return outInformationList;
    }

    @Override
    public List<String> findInformationByKeyword(String keyword) {
        String downloadData = newsGet.getNewsFromSource(addressSource);
        if (downloadData == null) {
            logger.error("Cannot download data, check address !");
            return Collections.emptyList();
        }
        SummaryNews summaryNews = newsParsing.getSummaryNewsFromText(downloadData);
        List<String> finedInformationList = new ArrayList<>();
        List<Information> news = summaryNews.getNews();
        for (Information information : news) {
            List<String> keywords = information.getKeywords();
            for (String keywordInInformation : keywords) {
                if (keywordInInformation.equals(keyword)) {
                    finedInformationList.add(formattedInformation(information));
                }
            }
        }
        return finedInformationList;
    }

    private String formattedInformation(Information information) {
        DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern(PARSING_PATTERN_DATE_TAME);
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern(PRINT_FORMAT_PATTERN_DATE_TAME);
        LocalDateTime localDateTime = LocalDateTime.parse(information.getDate(), inFormatter);
        String formattedDate = localDateTime.format(outFormatter);
        return "Date: " + formattedDate + "\n" +
                "Id: " + information.getId() + "\n" +
                "Description: " + information.getDescription() + "\n" +
                "Title: " + information.getTitle() + "\n" +
                "Keywords: " + information.getKeywords() + "\n" +
                "Visible: " + information.isVisible() + "\n";
    }
}