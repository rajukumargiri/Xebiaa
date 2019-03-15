package com.xebia.xebia.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xebia.xebia.DAO.ProductRepository;
import com.xebia.xebia.DAO.StoreRepository;
import com.xebia.xebia.entity.Barcode;
import com.xebia.xebia.entity.Product;

@Service
public class ProductService {
	
	
	@PersistenceContext
	EntityManager em;
	
	@Autowired
	ProductRepository prorespos;
	
	@Autowired
	StoreRepository storerepos;
	
	public List<Product> getproductList()
	{
		 List<Product> list=prorespos.findAll();
		 
		 return list;
		
	}
   
	@Transactional
	public void savepro(Product pro) {
		// TODO Auto-generated method stub
		
		savestore(pro);
		
		
		long id = 0;
		try
		{
		
		id=(long) em.createQuery("select p.id from Product p where p.barrcode = :barcode")
				  .setParameter("barcode", pro.getBarrcode())
				  .getSingleResult();
		}
		catch(NoResultException ex)
		{
			System.out.println("New Product with "+pro.getBarrcode());
		}
		
		if(id>0)		   
		{
			
		  long max=storerepos.getHighPriceFromJPA(pro.getBarrcode());		  
		  	if(pro.getPrice()>max)
		  		pro.setHighprice(pro.getPrice());
		   else
			  pro.setHighprice(max);
		  
		  long min=storerepos.getLowpriceFromJPA(pro.getBarrcode());		   
			    if(pro.getPrice()<min)
				     pro.setLowprice(pro.getPrice());
				else
					pro.setLowprice(min);
				  	 
				 	
				  int count=storerepos.getCountFromJPA(pro.getBarrcode());	
				  
				  pro.setNoofprices(count);		
							
				  long sum=storerepos.getAvgpriceFromJPA(pro.getBarrcode()); 
                  pro.setAvgprice(sum/count);			  
					
					if(count>4)
					{
					  double idealprice=storerepos.getIdealpriceFromJPA(pro.getBarrcode());	
					  double avgidealprice=idealprice/(count-4);
					  pro.setIdealprice(avgidealprice+((avgidealprice)*20)/100);
					}
			 
		      Query q=em.createNamedQuery("updatePrices")
		    		  .setParameter(1, pro.getIdealprice())
		    		  .setParameter(2, pro.getHighprice())
		    		  .setParameter(3, pro.getLowprice())
		    		  .setParameter(4, pro.getAvgprice())
		    		  .setParameter(5, pro.getNoofprices())
		    		  .setParameter(6, id);
		               
		      q.executeUpdate();
		    		  
		    		  
		}
		else
		{
		   pro.setHighprice(pro.getPrice());
		   pro.setLowprice(pro.getPrice());
		   pro.setAvgprice(pro.getPrice());
		   pro.setIdealprice(0);
		   pro.setNoofprices(1);
		     
			
		   prorespos.save(pro);
		}
		
	}
	
	
	public void savestore(Product pro)
	{
		
		Barcode b=new Barcode();
		b.setStore(pro.getStore());
		b.setDescription(pro.getDescription());
		b.setBarrcode(pro.getBarrcode());
		b.setPrice(pro.getPrice());
		b.setNotes(pro.getNotes());
		storerepos.save(b);
	}

	public void deleteproduct(long id) {
		// TODO Auto-generated method stub
		prorespos.deleteById(id);
	}

	public Product getproductById(long id) {
		Product p=prorespos.findById(id).orElse(null);
		return p;
	}

	public List<Barcode> getStoreList() {
		return storerepos.findAll();
	}

	@Transactional
	public void deletestore(long id) {
		// TODO Auto-generated method stub
		Product p=prorespos.findById(id).orElse(null);
		
		Query q=em.createNamedQuery("deleteStore").setParameter("barcode", p.getBarrcode());
		q.executeUpdate();
		
	}
	

}
