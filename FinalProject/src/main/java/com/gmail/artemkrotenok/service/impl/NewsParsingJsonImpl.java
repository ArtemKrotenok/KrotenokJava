package com.gmail.artemkrotenok.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmail.artemkrotenok.service.NewsParsing;
import com.gmail.artemkrotenok.service.model.SummaryNews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public class NewsParsingJsonImpl implements NewsParsing {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public SummaryNews getSummaryNewsFromText(String textInputInformation) {
        SummaryNews summaryNews;
        ObjectMapper mapper = new ObjectMapper();
        try {
            summaryNews = mapper.readValue(textInputInformation, SummaryNews.class);
            return summaryNews;
        } catch (IOException e) {
            logger.error("Error parsing JSON document");
        }
        return null;
    }
}