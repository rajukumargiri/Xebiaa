package com.xebia.xebia.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Barcode")
@NamedNativeQuery(name="deleteStore",query="delete from Barcode where barrcode= :barcode")
public class Barcode {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long barid;
	
	String store;
	String description;
	String barrcode;
	long price;
	String notes;
	
	
	
	public String getBarrcode() {
		return barrcode;
	}
	public void setBarrcode(String barrcode) {
		this.barrcode = barrcode;
	}
	public long getBarid() {
		return barid;
	}
	public void setBarid(long barid) {
		this.barid = barid;
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
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
}
