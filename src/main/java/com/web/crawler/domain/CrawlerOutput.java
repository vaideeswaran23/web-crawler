package com.web.crawler.domain;
import java.io.Serializable;

public class CrawlerOutput {

    String url;

    Integer count;

    String message;

    public CrawlerOutput() {
        this.url = "";
        this.count = 0;
        this.message = "";
    }

    public CrawlerOutput(String url, Integer count, String message) {
        this.url = url;
        this.count = count;
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "CrawlerOutput{" +
                "url='" + url + '\'' +
                ", count=" + count +
                ", message='" + message + '\'' +
                '}';
    }
}
