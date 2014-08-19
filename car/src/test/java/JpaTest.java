

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.tcl.car.config.MainConfig;
import com.tcl.car.model.PrivatePreOwnerCar;
import com.tcl.car.repository.PrivatePreOwnerCarRepository;
import com.tcl.craiglist.service.AlgorithmCraiglistCrawler;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainConfig.class}, loader = AnnotationConfigContextLoader.class)
public class JpaTest {

	private @Inject AlgorithmCraiglistCrawler acc;
	private @Inject PrivatePreOwnerCarRepository ppcRepo;
	
	private static final Logger logger = LoggerFactory.getLogger(JpaTest.class);

	//@Test
	public void testPrivatePreOwnerCarRepository() 
	{
		List<PrivatePreOwnerCar> itemList =ppcRepo.findAll();
		logger.debug("item size:" + itemList.size());
		DateTime today=new DateTime(new Date());

		for(int i=0;i<itemList.size();i++)
		{
			
				DateTime searchDate=new DateTime(itemList.get(i).getSearchdate());
				System.out.println(searchDate);
				System.out.println(Days.daysBetween(searchDate, today).getDays());
		}
		String url="http://sfbay.craigslist.org/sfc/cto/4559893477.html";
	    System.out.println(ppcRepo.hasThisRecord(url));
		
		
	}

	@Test
	public void testCraiglistCrawler()
	{
		Boolean dataNotCrawled_d=true;
		int i=0;
		
		
		while(dataNotCrawled_d)
		{
			 String sf_url_prefix_d="http://sfbay.craigslist.org/search/ctd?"+"&s="+i;
			 String url_d=sf_url_prefix_d+"query="+"car"+"&minAsk="+"1000";
			 System.out.println(url_d);
			 Boolean existData=acc.crawlData(url_d);
			 if(existData||i>300) break;
			 else
			i=i+100;
 
		}
		Boolean dataNotCrawled_o=true;
		i=0;
		
		while(dataNotCrawled_o)
		{
			 String sf_url_prefix_o="http://sfbay.craigslist.org/search/cto?"+"&s="+i;
			 String url_o=sf_url_prefix_o+"query="+"car"+"&minAsk="+"1000";
			 System.out.println(url_o);
			 Boolean existData=acc.crawlData(url_o);
			 if(existData||i>300) break;
			 else
			i=i+100;
 
		}
		
	}
	
}
