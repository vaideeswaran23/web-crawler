package com.web.crawler.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.crawler.Application;
import com.web.crawler.api.rest.CrawlerController;

import com.web.crawler.domain.CrawlerInput;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class CrawlerControllerTest {

    @InjectMocks
    CrawlerController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void shouldCreateAndUpdateAndDelete() throws Exception {
        CrawlerInput r1 = mockCrawl("shouldCreateAndUpdate");
        String r1Json = toJson(r1);
        //CREATE
        MvcResult result = mvc.perform(post("/api/crawl")
                .content(r1Json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String content = result.getResponse().getContentAsString();
        System.out.println(content.toString());
    }



    private CrawlerInput mockCrawl(String prefix) {
        CrawlerInput crawlerInput = new CrawlerInput();
        List<String> urlList = new ArrayList<>();
        urlList.add("https://martinfowler.com/architecture/");
        crawlerInput.setUrls(urlList);
        crawlerInput.setSearchText("architecture");
        return crawlerInput;
    }

    private String toJson(Object r) throws Exception {
        ObjectMapper map = new ObjectMapper();
        return map.writeValueAsString(r);
    }

}
