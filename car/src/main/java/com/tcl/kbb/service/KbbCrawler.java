package com.tcl.kbb.service;

import java.io.IOException;
import java.net.MalformedURLException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class KbbCrawler {


	//http://www.kbb.com/honda/accord/2001-honda-accord/lx-sedan-4d/?condition=excellent&vehicleid=4393&intent=trade-in-sell&mileage=108000&category=sedan&pricetype=private-party	
	//http://www.kbb.com/Honda/Civic/year-Honda-Civic/lx-sedan-4d/?condition=excellent&intent=trade-in-sell&mileage=108000&category=sedan+&pricetype=private-party
	//http://www.kbb.com/honda/accord/year-honda-accord/lx-sedan-4d/?condition=excellent&intent=trade-in-sell&mileage=108000&category=sedan+&pricetype=private-party

	private String prefix="http://www.kbb.com/";
	private int WEB_CLIENT_TIMEOUT=5000;
	
	private String ppe="privatepartyexcellent";
	private String ppf="privatepartyfair";
	private String ppg="privatepartygood";
	private String ppv="privatepartyverygood";
	
	private String te="tradeinexcellent";
	private String tf="tradeinfair";
	private String tg="tradeingood";
	private String tvg="tradeinverygood";
	
	/*
	 * Accord:DX,LX,EX,EX-L
	 */
	/*
	 * CAMRY:LE,SE,XLE
	 */
	/*
	 * Civic:DX,LX,EX,EX-L
	 */
	/*
	 * Corrola:	ce,s,le
	 */
	public String[][] queryPreOwnerCar(String year,String make, String model,String style,String mileage,String tradetype,String status)
	{
		String type="";
		
	             type=style+"-sedan-4d";
		String url=prefix+make+"/"+model+"/"+year+"-"+make+"-"+model+"/"+type+"/?condition="+status+"&intent="+tradetype+"&mileage="+mileage+"&category=sedan+"+"&pricetype="+"private-party";
		
		System.out.println(url);
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
        
        try {
			HtmlPage htmlPage=webClient.getPage(url);
			
			//System.out.println(htmlPage.asXml());
			
			
			if(htmlPage.asXml().contains("KBB.Vehicle.Pages.PricingOverview.Owners.setup"))
				System.out.println("true");
			
			Document doc=Jsoup.parse(htmlPage.asXml());
			String values=htmlPage.asXml().split("data: ")[1].split("\\);")[0];
			System.out.println(values.split(this.ppe+"\":")[1].split(",")[0]);
			System.out.println(values);
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
		return null;
	}
	
	public static void main(String args[])
	{
		KbbCrawler kc=new KbbCrawler();
		kc.queryPreOwnerCar("2009", "toyota", "corolla","LE","58000", "trade-in-sell", "excellent");
	}
	

}
