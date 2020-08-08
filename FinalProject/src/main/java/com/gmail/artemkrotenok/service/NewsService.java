package com.gmail.artemkrotenok.service;

import java.util.List;

public interface NewsService {
    List<String> getAllInformationSortByDate();

    List<String> findInformationByKeyword(String keyword);
}