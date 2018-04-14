package com.siemens.allkindsoftest.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.crawler.WebCrawler;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Simon
 * Date:
 * Time:
 */
public class CsiCrawlerCrawlerControllerFactory implements CrawlController.WebCrawlerFactory{
   // Map<String, String> metadata;
//    SqlRepository repository;

    public CsiCrawlerCrawlerControllerFactory(Map<String, String> metadata) {
        //this.metadata = metadata;
        //this.repository = repository;
    }

    @Override
    public WebCrawler newInstance() {
        return new MyCrawler();
    }
}
