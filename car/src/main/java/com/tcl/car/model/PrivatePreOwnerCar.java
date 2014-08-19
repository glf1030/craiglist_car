package com.tcl.car.model;


import java.util.Date;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "privatepreownercar")
public class PrivatePreOwnerCar 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name = "ID")
	private int id;
	@Column(name = "SELLPRICE")
	private String sellprice;	
	@Column(name = "MILEAGE")
	private String mileage;
	@Column(name = "COLOR")
	private String color;
	@Column(name = "VIN")
	private String vin;
	@Column(name="URL")
	private String url;
	@Column(name="CARCONDITION")
	private String carcondition;
	@Column(name="SEARCHDATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date searchdate;
	@Column(name="BUYSTATUS")
	private String buystatus;  
	@Column(name="MAKEYEAR")
    private String makeyear;
    @Column(name="MAKE")
    private String make;
    @Column(name="MODEL")
    private String model;
    @Column(name="selldate")
	@Temporal(TemporalType.TIMESTAMP)
	private Date selldate;
	@Column(name="TEXT")
    private String text;
	@Column(name="transmission")
    private String transmission;
	@Column(name="titlestatus")
    private String titlestatus;
    @Column(name="autofueltype")
    private String autofueltype;
    @Column(name="pnr")
    private String pnr;
    @Column(name="postDate")
    private String postdate;
	public String getPostdate() {
		return postdate;
	}
	public void setPostdate(String postdate) {
		this.postdate = postdate;
	}

    public String getAutofueltype() {
		return autofueltype;
	}
	public void setAutofueltype(String autofueltype) {
		this.autofueltype = autofueltype;
	}
	public String getPnr() {
		return pnr;
	}
	public void setPnr(String pnr) {
		this.pnr = pnr;
	}

    
    public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getTitlestatus() {
		return titlestatus;
	}
	public void setTitlestatus(String titlestatus) {
		this.titlestatus = titlestatus;
	}

    public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getVin() {
		return vin;
	}
	public void setVin(String vin) {
		this.vin = vin;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCarcondition() {
		return carcondition;
	}
	public void setCarcondition(String carcondition) {
		this.carcondition = carcondition;
	}
	public Date getSearchdate() {
		return searchdate;
	}
	public void setSearchdate(Date searchdate) {
		this.searchdate = searchdate;
	}
	public String getBuystatus() {
		return buystatus;
	}
	public void setBuystatus(String buystatus) {
		this.buystatus = buystatus;
	}
	public String getMakeyear() {
		return makeyear;
	}
	public void setMakeyear(String makeyear) {
		this.makeyear = makeyear;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}

	public Date getSelldate() {
		return selldate;
	}
	public void setSelldate(Date selldate) {
		this.selldate = selldate;
	}

}
