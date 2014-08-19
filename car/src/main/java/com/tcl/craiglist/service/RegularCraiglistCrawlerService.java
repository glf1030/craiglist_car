//package com.tcl.craiglist.service;
//
//import javax.inject.Inject;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//
//import com.tcl.car.controller.PreOwnerCarController;
//
//public class RegularCraiglistCrawlerService {
//
//	/**
//	 * @param args
//	 */	
//     private static final Logger logger = LoggerFactory.getLogger(RegularCraiglistCrawlerService.class);
//
//    private @Inject CraiglistCrawler cc;
//	
//	@Scheduled(fixedRate=1000*60*60*12)
//	public void crawler() throws InterruptedException
//	{
//		logger.debug("begin running crawler");
//		cc.crawlData("civic", "6000", "12000");
//		cc.crawlData("accord", "6000", "12000");
//		cc.crawlData("corolla", "6000", "12000");
//		cc.crawlData("camry", "6000", "12000");
//		logger.debug("finish running crawler, waiting for next time");
//	}
//}
