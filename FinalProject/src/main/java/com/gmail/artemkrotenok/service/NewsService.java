package com.gmail.artemkrotenok.service;

import com.gmail.artemkrotenok.service.model.SummaryNews;

import java.util.List;

public interface NewsService {

    SummaryNews getNewsFromSource(String sourceAddress);

    List<String> getAllInformationSortByDate(SummaryNews news);

    List<String> findInformationByKeyword(SummaryNews summaryNews, String keyword);
}