package com.web.crawler.domain;
import java.io.Serializable;
import java.util.List;

public class CrawlerInput {

    List<String> urls;

    String searchText;

    public CrawlerInput(List<String> urls, String searchText) {
        this.urls = urls;
        this.searchText = searchText;
    }

    public CrawlerInput() {
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public String toString() {
        return "CrawlerInput{" +
                "urls=" + urls +
                ", searchText='" + searchText + '\'' +
                '}';
    }

}
