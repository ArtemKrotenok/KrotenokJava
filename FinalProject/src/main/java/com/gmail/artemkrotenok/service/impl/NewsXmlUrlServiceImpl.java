package com.gmail.artemkrotenok.service.impl;

import com.gmail.artemkrotenok.service.model.SummaryNews;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;

public class NewsXmlUrlServiceImpl extends NewsUrlServiceImpl {

    private static final Logger logger = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public SummaryNews getSummaryNewsFromText(String textInputInformation) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SummaryNews.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (SummaryNews) jaxbUnmarshaller.unmarshal(new StringReader(textInputInformation));
        } catch (JAXBException e) {
            logger.error("Error parsing XML document");
        }
        return null;
    }
}