package com.xebia.xebia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;

import org.hibernate.annotations.NamedNativeQueries;

@Entity
@Table(name="product")
@NamedNativeQuery(name="updatePrices",query="update Product set idealprice=?,highprice=?,lowprice=?,avgprice=?,noofprices=? where id=?")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id;
	
	String store;
	
	
	String description;
	String barrcode;
	long price;
	double avgprice;
	long lowprice;
	long highprice;
	double idealprice;
	int noofprices;
	String notes;
	
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBarrcode() {
		return barrcode;
	}

	public void setBarrcode(String barrcode) {
		this.barrcode = barrcode;
	}

	
	public String getStore() {
		return store;
	}
	public void setStore(String store) {
		this.store = store;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public double getAvgprice() {
		return avgprice;
	}
	public void setAvgprice(double avgprice) {
		this.avgprice = avgprice;
	}
	public long getLowprice() {
		return lowprice;
	}
	public void setLowprice(long lowprice) {
		this.lowprice = lowprice;
	}
	public long getHighprice() {
		return highprice;
	}
	public void setHighprice(long highprice) {
		this.highprice = highprice;
	}
	public double getIdealprice() {
		return idealprice;
	}
	public void setIdealprice(double idealprice) {
		this.idealprice = idealprice;
	}
	public int getNoofprices() {
		return noofprices;
	}
	public void setNoofprices(int noofprices) {
		this.noofprices = noofprices;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
	
	

}
