package com.gmail.artemkrotenok;

import com.gmail.artemkrotenok.service.NewsService;
import com.gmail.artemkrotenok.service.impl.NewsJsonUrlServiceImpl;
import com.gmail.artemkrotenok.service.impl.NewsXmlUrlServiceImpl;
import com.gmail.artemkrotenok.service.model.SummaryNews;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        NewsService newsService = initService();
        SummaryNews summaryNews = getSummaryNews(newsService);
        processInformation(summaryNews, newsService);
    }

    private static void processInformation(SummaryNews summaryNews, NewsService newsService) {
        List<String> finedInformation;
        int choseUser;
        do {
            choseUser = choseUser();
            switch (choseUser) {
                case 1: {
                    printAllNews(summaryNews, newsService);
                    break;
                }
                case 2: {
                    searchByTag(summaryNews, newsService);
                    break;
                }
                default:
                    System.out.println("Not correct input (need '1' or '2') exit - '3'");
            }
        } while (choseUser != 3);
    }

    private static SummaryNews getSummaryNews(NewsService newsService) {
        SummaryNews summaryNews = null;
        do {
            Scanner input = new Scanner(System.in);
            input.reset();
            System.out.println("Input URL address:");
            String addressSource = input.nextLine();
            summaryNews = newsService.getNewsFromSource(addressSource);
            if (summaryNews == null) {
                System.out.println("Cannot download data, check address !");
            }
        } while (summaryNews == null);
        return summaryNews;
    }

    private static NewsService initService() {
        NewsService newsService = null;
        do {
            switch (choseUserXmlOrJson()) {
                case 1: {
                    newsService = new NewsJsonUrlServiceImpl();
                    break;
                }
                case 2: {
                    newsService = new NewsXmlUrlServiceImpl();
                    break;
                }
                case 3: {
                    break;
                }
                default:
                    System.out.println("Not correct input (need '1' or '2')");
            }
        } while (newsService == null);
        return newsService;
    }

    private static void printAllNews(SummaryNews summaryNews, NewsService newsService) {
        List<String> finedInformation = newsService.getAllInformationSortByDate(summaryNews);
        for (String information : finedInformation) {
            System.out.println(information);
        }
    }

    private static void searchByTag(SummaryNews summaryNews, NewsService newsService) {
        System.out.println("Input tag for news:");
        Scanner input = new Scanner(System.in);
        String tagNews = input.nextLine();
        List<String> finedInformation = newsService.findInformationByKeyword(summaryNews, tagNews);
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