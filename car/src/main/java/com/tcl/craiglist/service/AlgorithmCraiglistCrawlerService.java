package com.tcl.craiglist.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.tcl.car.controller.PreOwnerCarController;

public class AlgorithmCraiglistCrawlerService {

	/**
	 * @param args
	 */	
     private static final Logger logger = LoggerFactory.getLogger(AlgorithmCraiglistCrawlerService.class);

    private @Inject AlgorithmCraiglistCrawler acc;
	
	@Scheduled(fixedRate=1000*60*60*12)
	public void crawler() throws InterruptedException
	{
		Boolean dataNotCrawled_d=true;
		int i=0;
		
		
		while(dataNotCrawled_d)
		{
			 String sf_url_prefix_d="http://sfbay.craigslist.org/search/ctd?"+"&s="+i;
			 String url_d=sf_url_prefix_d+"query="+"car"+"&minAsk="+"1000";
			 Boolean existData=acc.crawlData(url_d);
			 if(existData) break;
			 else
			i=i+100;
 
		}
		Boolean dataNotCrawled_o=true;
		i=0;
		
		while(dataNotCrawled_o)
		{
			 String sf_url_prefix_o="http://sfbay.craigslist.org/search/cto?"+"&s="+i;
			 String url_o=sf_url_prefix_o+"query="+"car"+"&minAsk="+"1000";
			 Boolean existData=acc.crawlData(url_o);
			 if(existData) break;
			 else
			i=i+100;
 
		}
		
	}
	}

