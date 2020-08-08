package com.gmail.artemkrotenok.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NewsDownloadFromUrl extends NewsGetImpl {
    @Override
    public String getTextInputInformationFromUrl(String urlAddress) throws IOException {
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