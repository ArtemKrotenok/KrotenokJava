package com.gmail.artemkrotenok.service;

import com.gmail.artemkrotenok.service.model.SummaryNews;

public interface NewsParsing {
    SummaryNews getSummaryNewsFromText(String textInputNews);
}