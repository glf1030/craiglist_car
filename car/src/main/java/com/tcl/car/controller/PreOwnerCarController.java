/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcl.car.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;


import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.tcl.car.model.PrivatePreOwnerCar;
import com.tcl.car.repository.PrivatePreOwnerCarRepository;




@Controller

public class PreOwnerCarController {

	private static final Logger logger = LoggerFactory.getLogger(PreOwnerCarController.class);
	private @Inject PrivatePreOwnerCarRepository pCarRepo;
  
	@RequestMapping(value="/preOwnerCar/listCars",method=RequestMethod.GET)
	public @ResponseBody List<PrivatePreOwnerCar> listTags()
	{
		List<PrivatePreOwnerCar> cars=null;
		logger.debug("list tags");
		cars=pCarRepo.findAll();
		return cars;
	}

	@RequestMapping(value="/preOwnerCar/listCars/{days}",method=RequestMethod.GET)
	public @ResponseBody List<PrivatePreOwnerCar> listTags(@PathVariable("days") String days)
	{
		List<PrivatePreOwnerCar> cars=null;
		logger.debug("list tags");
		cars=pCarRepo.findAll();
		DateTime today=new DateTime(new Date());
		
		List<PrivatePreOwnerCar> matchingCarList=new ArrayList<PrivatePreOwnerCar>();
		for(int i=0;i<cars.size();i++)
		{
			DateTime searchDate=new DateTime(cars.get(i).getSearchdate());
			if(Days.daysBetween(searchDate, today).getDays()<Integer.valueOf(days))
			{
				System.out.println(Days.daysBetween(searchDate, today).getDays());
				matchingCarList.add(cars.get(i));
			}
			;
		}

		return matchingCarList;
	}
	
	@RequestMapping(value="/preOwnerCar/listCars/{model}/{days}",method=RequestMethod.GET)
	public @ResponseBody List<PrivatePreOwnerCar> listTags(@PathVariable("model") String model,@PathVariable("days") String days)
	{
		List<PrivatePreOwnerCar> cars=null;
		logger.debug("list tags");
		cars=pCarRepo.findAll();
		DateTime today=new DateTime(new Date());
		
		List<PrivatePreOwnerCar> matchingCarList=new ArrayList<PrivatePreOwnerCar>();
		for(int i=0;i<cars.size();i++)
		{
			DateTime searchDate=new DateTime(cars.get(i).getSearchdate());
			String carmodel=cars.get(i).getModel();
			if(Days.daysBetween(searchDate, today).getDays()<Integer.valueOf(days)&&carmodel.toLowerCase().contains(model.toLowerCase()))
			{
				System.out.println(Days.daysBetween(searchDate, today).getDays());

				matchingCarList.add(cars.get(i));
			}
			;
		}

		return matchingCarList;
	}
	
	

   
   
//    @Consumes({"application/xml", "application/json"})
//    @RequestMapping(value = "/items/", method = RequestMethod.POST)
//    public void create(JpaItem entity) 
//    {
//        super.create(entity);
//    }

//    @PUT
//    @Path("{id}")
//    @Consumes({"application/xml", "application/json"})
//    @Produces({"application/xml", "application/json"})
//    public void editp(Item entity) {
//        super.edit(entity);
//    }
//
//    @DELETE
//    @Path("{id}")
//    public void remove(@PathParam("id") Integer id) {
//        super.remove(super.find(id));
//    }
//
//    @GET
//    @Path("{id}")
//    @Produces({"application/xml", "application/json"})
//    public Item find(@PathParam("id") Integer id) {
//        return super.find(id);
//    }
//
//    @GET
//    @Override
//    @Produces({"application/xml", "application/json"})
//    public List<Item> findAll() {
//        return super.findAll();
//    }
//
//    @GET
//    @Path("{from}/{to}")
//    @Produces({"application/xml", "application/json"})
//    public List<Item> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
//        return super.findRange(new int[]{from, to});
//    }
//
//    @GET
//    @Path("count")
//    @Produces("text/plain")
//    public String countREST() {
//        return String.valueOf(super.count());
//    }
//
//    @java.lang.Override
//    protected EntityManager getEntityManager() {
//        return em;
//    }
}
