//package com.tcl.craiglist.service;
//
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//
//import javax.inject.Inject;
//
//
//import org.jsoup.Jsoup;
//import org.jsoup.nodes.Document;
//import org.jsoup.select.Elements;
//
//import com.gargoylesoftware.htmlunit.BrowserVersion;
//import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
//import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
//import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
//import com.gargoylesoftware.htmlunit.WebClient;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//import com.tcl.car.model.PrivatePreOwnerCar;
//import com.tcl.car.repository.PrivatePreOwnerCarRepository;
//
//public class CraiglistCrawler {
//
//	private String url_prefix="http://sfbay.craigslist.org/search/cto?";
//	private String url_postfix="&auto_fuel_type=1&auto_transmission=2&auto_title_status=1";
//	private int WEB_CLIENT_TIMEOUT=10000;
//	private @Inject PrivatePreOwnerCarRepository ppocr;
//	
//	//query=civic&minAsk=8000&maxAsk=12000
//	public void crawlData(String keyword,String min, String max)
//	{
//	
//		String u="http://sfbay.craigslist.org";
//	//   ArrayList itemList=new ArrayList();
//
//		String url=url_prefix+"query="+keyword+"&minAsk="+min+"&maxAsk="+max+url_postfix;
//
//		System.out.println(url);
//		WebClient webClient=new WebClient(BrowserVersion.FIREFOX_24);
//		webClient.getOptions().setJavaScriptEnabled(true);
//		webClient.getOptions().setRedirectEnabled(true);
//    	webClient.getOptions().setCssEnabled(false);
//    	webClient.getOptions().setThrowExceptionOnScriptError(false);
//    	webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//    	webClient.setRefreshHandler(new ThreadedRefreshHandler());
//    	webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//        webClient.getOptions().setTimeout(WEB_CLIENT_TIMEOUT);
//        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//        webClient.getOptions().setThrowExceptionOnScriptError(false);
//		
//		
//		
//         HtmlPage htmlPage=null;
//		
//    	//	System.out.println(url);
//			//url="http://sfbay.craigslist.org/search/cto?query=civic&minAsk=8000&maxAsk=15000&auto_bodytype=8&auto_drivetrain=3&auto_fuel_type=1&auto_transmission=2&auto_title_status=1";
//			//URL u=new URL(url);
//			
//			try {
//				htmlPage = webClient.getPage(url);
//			} catch (FailingHttpStatusCodeException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MalformedURLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			Document doc = Jsoup.parse(htmlPage.asXml());
//			
//			//System.out.println(doc.select("div.content").size());
//						
//			if(doc.select("div.content").size()!=0)
//			{
//				Elements eles=doc.select("div.content").select("p.row");
//				
//				for(int i=0;i<eles.size();i++)
//				{
//					String condition="default";
//					String year_make_model="default";
//					String mileage="default";
//					String vin="default";
//					String color="default";
//					
//					try
//					{
//						String sellurlpre=eles.get(i).select("a.i").attr("href");
//						String sellurl=null;
//						if(sellurlpre.contains("http")) break;
//						else
//							sellurl=u+eles.get(i).select("a.i").attr("href");
//					
//					
//					//System.out.println(u+eles.get(i).select("a.i").attr("href"));
//					
//					
//				//	System.out.println("*"+eles.get(i).select("span.price").text()+"*");
//					String sellprice=eles.get(i).select("span.price").text().split(" ")[0];
//					
//
//					try 
//					{
//						htmlPage=webClient.getPage(u+eles.get(i).select("a.i").attr("href"));
//					} catch (FailingHttpStatusCodeException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (MalformedURLException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					doc = Jsoup.parse(htmlPage.asXml());
//					//System.out.println(doc.select("p.attrgroup").size());
//					
//					for(int j=0;j<doc.select("p.attrgroup").size();j++)
//					{
//						
//						String label=doc.select("p.attrgroup").get(j).text();
//				        if(label.contains("condition:"))
//				        	condition=label.split("condition:")[1].trim();
//				        
//				        
//				        if(label.contains("odometer"))
//				        {
//				        	
//				        	year_make_model=label.split("odometer")[0];
//				        	mileage=label.split("odometer: ")[1].split(" ")[0];
//				        	
//				        }
//				        if(label.toLowerCase().contains("vin"))
//				        {
//				        
//				        	vin=label.toLowerCase().split(" vin: ")[1].split(" ")[0];
//				        }
//				        
//				        if(label.toLowerCase().contains("color"))
//				        {
//				        	color=label.toLowerCase().split("color : ")[1].split(" ")[0];
//				        }
//						
//					}
//					
//					System.out.println(sellurl);
//					System.out.println(sellprice);
//					System.out.println(condition);
//					System.out.println(year_make_model);
//					System.out.println(mileage);
//					System.out.println(vin);
//					System.out.println(color);
//			        System.out.println(new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
//					PrivatePreOwnerCar ppoc=new PrivatePreOwnerCar();
//
//					System.out.println(ppocr);
//					if(ppocr.hasThisRecord(sellurl)==0)
//					{
//					ppoc.setUrl(sellurl);
//					ppoc.setSellprice(sellprice);
//					ppoc.setCarcondition(condition);
//					ppoc.setModel(year_make_model);
//					ppoc.setMileage(mileage);
//					ppoc.setVin(vin);
//					ppoc.setColor(color);
//			     	ppoc.setSearchdate(new Date());
//			     	ppocr.saveAndFlush(ppoc);
//					}
//				   // ppocr.save(ppoc);
//					}
//					catch(Exception e)
//					{
//						e.printStackTrace();
//					}
//				
//				}
//			}
//			
//			
//		
//				
//				
//			
//		
//			//System.out.println(doc.html());
//			
//			
//		
//		
//		
//	}
//	/*
//	 * Test pass
//	 */
//	public static void main(String[] args)
//	{
//		CraiglistCrawler cc=new CraiglistCrawler();
//		cc.crawlData("corolla", "8000", "20000");
//	}
//
//}
