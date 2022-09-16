package com.web.crawler.service;

import com.web.crawler.domain.CrawlerInput;
import com.web.crawler.domain.CrawlerOutput;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class CrawlerService {

    public List<CrawlerOutput> crawl(CrawlerInput crawlerInput) {
        List<CrawlerOutput> crawlerOutputList = new ArrayList<>();
        List<String> urlList = crawlerInput.getUrls();
        String searchText = crawlerInput.getSearchText().toLowerCase();
        System.out.println("crawlerInput" + crawlerInput);
        for(String link : urlList) {
            CrawlerOutput crawlerOutput = new CrawlerOutput(link, 0, "");
            try {
                URL url = new URL(link);
                StringBuilder rawHTML = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String inputLine = bufferedReader.readLine();
                while(inputLine  != null){
                    rawHTML.append(inputLine);

                    inputLine = bufferedReader.readLine();
                }
                bufferedReader.close();
                parseHTML(rawHTML.toString(), crawlerOutput, searchText);
            } catch (MalformedURLException mue) {
                crawlerOutput.setMessage("Malformed url");
                mue.printStackTrace();
            } catch (IOException ioe) {
                crawlerOutput.setMessage("IO Exception");
                ioe.printStackTrace();
            } catch (Exception e) {
                crawlerOutput.setMessage(e.getMessage());
            } finally {
                crawlerOutputList.add(crawlerOutput);
            }
        }
        return crawlerOutputList;
    }

    void parseHTML(String rawHTML,CrawlerOutput crawlerOutput, String searchText) {
        String parsedHTML = rawHTML.toLowerCase();
        int index = 0;
        int count = 0;
        while(index != -1){
            index = parsedHTML.indexOf(searchText, index);
            if (index != -1) {
                index++;
                count++;
            }
        }
        crawlerOutput.setCount(count);
        crawlerOutput.setMessage("Successfully parsed");
    }
}
