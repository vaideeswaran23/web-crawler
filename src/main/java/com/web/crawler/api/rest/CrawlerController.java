package com.web.crawler.api.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.crawler.domain.CrawlerInput;
import com.web.crawler.domain.CrawlerOutput;
import com.web.crawler.service.CrawlerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/*
 * Demonstrates how to set up RESTful API endpoints using Spring MVC
 */
@RestController
@RequestMapping(value = "/api/crawl")
@Api(tags = {"webcrawler"})
public class CrawlerController extends AbstractRestHandler {

    @Autowired
    private CrawlerService crawlerService;

    @RequestMapping(
            method = RequestMethod.POST,
            consumes = {"application/json"},
            produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Crawl the given url", notes = "Returns the count of search text in each urls")
    public List<CrawlerOutput> crawlWeb(@RequestBody CrawlerInput crawlerInput,
                                 HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        String mapJson = objectMapper.writeValueAsString(map);
//        CrawlerInput crawlerInput = objectMapper.readValue(mapJson, CrawlerInput.class);
        List<CrawlerOutput> crawlerOutputList = this.crawlerService.crawl(crawlerInput);
        return crawlerOutputList;
    }
}
