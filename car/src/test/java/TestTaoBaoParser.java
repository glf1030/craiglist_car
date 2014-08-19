

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.ThreadedRefreshHandler;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestTaoBaoParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	//	String url="http://ai.taobao.com/auction/edetail.htm?e=zDDfaF8FhBcjmraEDZVrLsmMNkdZQSCYTCbL1Om%2BR1KLltG5xFicOdXrTUTgh9sMDPIwxrc30rg3avkGnQR%2BPiEFUhVMIwRUQaAdrwrBO6%2Farqjq1DkBcuIZWR1bMnHu";
		
		String url="http://redirect.simba.taobao.com/rd?w=unionnojs&f=http%3A%2F%2Fai.taobao.com%2Fauction%2Fedetail.htm%3Fe%3D3a4YU4jY%252FVIjmraEDZVrLvtiWRh2L6cvWDbyJyU0aqSLltG5xFicOdXrTUTgh9sMDPIwxrc30rg3avkGnQR%252BPiEFUhVMIwRUQaAdrwrBO6%252Farqjq1DkBcuIZWR1bMnHu%26unid%3D30138255%26ptype%3D100010%26from%3Dbasic&k=5ccfdb950740ca16&c=un&b=alimm_0&p=mm_30138255_6878168_23334840";
		WebClient webClient = new WebClient(BrowserVersion.FIREFOX_24);
		
	//	webClient.getOptions().setJavaScriptEnabled(true);
	//	webClient.getOptions().setRedirectEnabled(true);
    //	webClient.getOptions().setCssEnabled(false);
    	//webClient.getOptions().setThrowExceptionOnScriptError(false);
    	//webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
    	//webClient.setRefreshHandler(new ThreadedRefreshHandler());
    //	webClient.addRequestHeader("Referer", url);
    //	webClient.setAjaxController(new NicelyResynchronizingAjaxController());
     //   webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
     //   webClient.getOptions().setThrowExceptionOnScriptError(false);
    	HtmlPage htmlPage;
    	try
    	{
	         htmlPage = webClient.getPage(url);
			
			Document doc = Jsoup.parse(htmlPage.asXml());
			
		//	System.out.println(doc.html().split("dib similar-like")[0]);
			
			System.out.println(doc.select("a.dib.product-title").get(0).html());

    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}

	}

}
