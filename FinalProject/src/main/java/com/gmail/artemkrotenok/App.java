package com.gmail.artemkrotenok;

import com.gmail.artemkrotenok.service.NewsParsing;
import com.gmail.artemkrotenok.service.NewsService;
import com.gmail.artemkrotenok.service.impl.NewsDownloadFromUrl;
import com.gmail.artemkrotenok.service.impl.NewsParsingJsonImpl;
import com.gmail.artemkrotenok.service.impl.NewsParsingXmlImpl;
import com.gmail.artemkrotenok.service.impl.NewsServiceImpl;

import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        NewsParsing newsParsing = initParsingService();
        String addressSource = getAddressSource();
        processInformation(addressSource, newsParsing);
    }

    private static void processInformation(String addressSource, NewsParsing newsParsing) {
        List<String> finedInformation;
        int choseUser;
        do {
            choseUser = choseUser();
            switch (choseUser) {
                case 1: {
                    printAllNews(addressSource, newsParsing);
                    break;
                }
                case 2: {
                    searchByTag(addressSource, newsParsing);
                    break;
                }
                default:
                    System.out.println("Not correct input (need '1' or '2') exit - '3'");
            }
        } while (choseUser != 3);
    }

    private static String getAddressSource() {
        Scanner input = new Scanner(System.in);
        input.reset();
        System.out.println("Input URL address:");
        return input.nextLine();
    }

    private static NewsParsing initParsingService() {
        NewsParsing newsParsing = null;
        do {
            switch (choseUserXmlOrJson()) {
                case 1: {
                    newsParsing = new NewsParsingJsonImpl();
                    break;
                }
                case 2: {
                    newsParsing = new NewsParsingXmlImpl();
                    break;
                }
                case 3: {
                    break;
                }
                default:
                    System.out.println("Not correct input (need '1' or '2')");
            }
        } while (newsParsing == null);
        return newsParsing;
    }

    private static void printAllNews(String addressSource, NewsParsing newsParsing) {
        NewsService newsService = new NewsServiceImpl(new NewsDownloadFromUrl(), newsParsing, addressSource);
        List<String> finedInformation = newsService.getAllInformationSortByDate();
        for (String information : finedInformation) {
            System.out.println(information);
        }
    }

    private static void searchByTag(String addressSource, NewsParsing newsParsing) {
        System.out.println("Input tag for news:");
        Scanner input = new Scanner(System.in);
        String tagNews = input.nextLine();
        NewsService newsService = new NewsServiceImpl(new NewsDownloadFromUrl(), newsParsing, addressSource);
        List<String> finedInformation = newsService.findInformationByKeyword(tagNews);
        if (finedInformation == null || finedInformation.isEmpty()) {
            System.out.println("News with tag '" + tagNews + "' not found");
        } else {
            for (String information : finedInformation) {
                System.out.println(information);
            }
        }
    }

    private static int choseUser() {
        System.out.print("Print all news(1) or search by tag(2), exit (3): ");
        System.out.print("Input a number:");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }

    private static int choseUserXmlOrJson() {
        System.out.print("We will work with JSON(1) or XML(2): ");
        System.out.print("Input a number:");
        Scanner input = new Scanner(System.in);
        return input.nextInt();
    }
}