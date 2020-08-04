package com.gmail.artemkrotenok.service.impl;

import com.gmail.artemkrotenok.service.NewsGet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.invoke.MethodHandles;

public abstract class NewsGetImpl implements NewsGet {
    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public String getNewsFromSource(String sourceAddress) {
        String textInputInformation = null;
        try {
            textInputInformation = getTextInputInformationFromUrl(sourceAddress);
        } catch (IOException e) {
            logger.error("Error download data from URL");
        }
        return textInputInformation;
    }

    public abstract String getTextInputInformationFromUrl(String urlAddress) throws IOException;
}