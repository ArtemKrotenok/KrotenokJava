package com.gmail.artemkrotenok.service.impl;

import com.gmail.artemkrotenok.service.model.SummaryNews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.net.URLConnection;

public abstract class NewsUrlServiceImpl extends NewsServiceImpl {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public SummaryNews getNewsFromSource(String sourceAddress) {
        String textInputInformation = null;
        try {
            textInputInformation = getTextInputInformationFromUrl(sourceAddress);
        } catch (IOException e) {
            logger.error("Error download data from URL");
        }
        if (textInputInformation != null) {
            return getSummaryNewsFromText(textInputInformation);
        }
        return null;
    }

    public abstract SummaryNews getSummaryNewsFromText(String textInputInformation);

    private String getTextInputInformationFromUrl(String urlAddress) throws IOException {
        URL url = new URL(urlAddress);
        URLConnection urlConnection = url.openConnection();
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(urlConnection.getInputStream()));
        String inputLine;
        StringBuilder stringBuilder = new StringBuilder();
        while ((inputLine = bufferedReader.readLine()) != null) {
            stringBuilder.append(inputLine);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}