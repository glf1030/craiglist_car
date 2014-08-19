package com.tcl.craiglist.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.tcl.car.model.PrivatePreOwnerCar;
import com.tcl.car.repository.PrivatePreOwnerCarRepository;

public class AlgorithmCraiglistCrawler {

	/*
	 * SF
	 */



	
	private int WEB_CLIENT_TIMEOUT=10000;
   private @Inject PrivatePreOwnerCarRepository ppocr;
	
	//query=civic&minAsk=8000&maxAsk=12000
	
	
	public boolean crawlData(String url)
	{
	
		int count=0;
		Boolean existsData=false;
	String u="http://sfbay.craigslist.org";
	//   ArrayList itemList=new ArrayList();


		
		WebClient webClient=new WebClient(BrowserVersion.FIREFOX_24);
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setRedirectEnabled(true);
    	webClient.getOptions().setCssEnabled(false);
    	webClient.getOptions().setThrowExceptionOnScriptError(false);
    	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    	webClient.setRefreshHandler(new ThreadedRefreshHandler());
    	webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        webClient.getOptions().setTimeout(WEB_CLIENT_TIMEOUT);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);
		
		
		
         HtmlPage htmlPage=null;
		
/*
 * Step 1, try to crawl owners' car
 */
			
			try {
				htmlPage = webClient.getPage(url);
			} catch (FailingHttpStatusCodeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Document doc = Jsoup.parse(htmlPage.asXml());
			

						
			if(doc.select("div.content").size()!=0)
			{
				Elements eles=doc.select("div.content").select("p.row");
				
				for(int i=0;i<eles.size();i++)
				{
					String condition="default";
					String year_make_model="default";
					String mileage="default";
					String vin="default";
					String color="default";
					String autofueltype="default";
					String transmission="default";
					String titlestatus="default";
					String pnr="default";
					String postdate="default";
					String text="default";
					String sellprice="default";
					try
					{
						String sellurlpre=eles.get(i).select("a.i").attr("href");
						String sellurl=null;
						if(sellurlpre.contains("http")) break;
						else
							sellurl=u+eles.get(i).select("a.i").attr("href");

					sellprice=eles.get(i).select("span.price").text().split(" ")[0];
					
					postdate=eles.get(i).select("span.date").text();
					
					
					pnr=eles.get(i).select("span.pnr").text();

					try 
					{
						htmlPage=webClient.getPage(u+eles.get(i).select("a.i").attr("href"));
					} catch (FailingHttpStatusCodeException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					doc = Jsoup.parse(htmlPage.asXml());
					//System.out.println(doc.select("p.attrgroup").size());
					
					for(int j=0;j<doc.select("p.attrgroup").size();j++)
					{
						
						String label=doc.select("p.attrgroup").get(j).text();
				        if(label.contains("condition:"))
				        	condition=label.split("condition:")[1].trim();
				        
				        
				        if(label.contains("odometer"))
				        {
				        	
				        	year_make_model=label.split("odometer")[0];
				        	mileage=label.split("odometer: ")[1].split(" ")[0];
				        	
				        }
				        if(label.toLowerCase().contains("vin"))
				        {
				        
				        	vin=label.toLowerCase().split(" vin: ")[1].split(" ")[0];
				        }
				        
				        if(label.toLowerCase().contains("color"))
				        {
				        
				        	color=label.toLowerCase().split("color : ")[1].split(" ")[0];
				        }
						
				        if(label.toLowerCase().contains("title status"))
				        {
				        	titlestatus=label.toLowerCase().split("title status : ")[1].split(" ")[0];
				        }
				        
				        if(label.toLowerCase().contains("fuel "))
				        {
				        	autofueltype=label.toLowerCase().split("fuel : ")[1].split(" ")[0];
				        }
				        if(label.toLowerCase().contains("transmission "))
				        {
				        	transmission=label.toLowerCase().split("transmission : ")[1].split(" ")[0];
				        }
					}
					text=doc.select("section.userbody").get(0).text();
					
				
					
//					System.out.println(sellurl);
//					System.out.println(sellprice);
//					System.out.println(condition);
//					System.out.println(year_make_model);
//					System.out.println(mileage);
//					System.out.println(vin);
//					System.out.println(color);
//					System.out.println(titlestatus);
//					System.out.println(autofueltype);
//					System.out.println(transmission);
//			        System.out.println(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
//			        System.out.println(pnr);
//			        System.out.println(postdate);
//			        System.out.println(text);


			    	PrivatePreOwnerCar ppoc=new PrivatePreOwnerCar();

					if(ppocr.hasThisRecord(sellurl)==0)
					{
					ppoc.setUrl(sellurl);
					ppoc.setSellprice(sellprice);
					ppoc.setCarcondition(condition);
					ppoc.setModel(year_make_model);
					ppoc.setMileage(mileage);
					ppoc.setVin(vin);
					ppoc.setColor(color);
			     	ppoc.setSearchdate(new Date());
			     
			     	ppoc.setPnr(pnr);
			     	ppoc.setPostdate(postdate);
			     	ppoc.setAutofueltype(autofueltype);
			     	ppoc.setTitlestatus(titlestatus);
			     	ppoc.setTransmission(transmission);
			     	ppoc.setText(text);
			     	ppocr.saveAndFlush(ppoc);
                    count++;
					}
					else
					{
						//existsData=true;
					}
				
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				
				}
				
			}
	
			System.out.println("save "+count+" records");
			return existsData;
	}
	
	public static void main(String[] args)
	{
		
		Boolean dataNotCrawled_d=true;
		int i=0;
		AlgorithmCraiglistCrawler acc=new AlgorithmCraiglistCrawler();
		
		while(dataNotCrawled_d)
		{
			 String sf_url_prefix_d="http://sfbay.craigslist.org/search/ctd?"+"&s="+i;
			 String url_d=sf_url_prefix_d+"query="+"car"+"&minAsk="+"1000";
			 
			 if(acc.crawlData(url_d)) break;
			 else
			i=i+100;
 
		}
		Boolean dataNotCrawled_o=true;
		i=0;
		
		while(dataNotCrawled_o)
		{
			 String sf_url_prefix_o="http://sfbay.craigslist.org/search/cto?"+"&s="+i;
			 String url_o=sf_url_prefix_o+"query="+"car"+"&minAsk="+"1000";
			 
			 if(acc.crawlData(url_o)) break;
			 else
			i=i+100;
 
		}

	}

}
