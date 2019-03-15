package com.xebia.xebia.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xebia.xebia.entity.Barcode;

public interface StoreRepository  extends JpaRepository<Barcode, Long>{
	
	
	 @Query(value="select count(distinct(p.price)) from Barcode p where p.barrcode = ?1",nativeQuery=true)
	 public int getCountFromJPA(String barcode);
	
	@Query(value="select max(p.price) from Barcode p where p.barrcode = ?1",nativeQuery=true)
	public long getHighPriceFromJPA(String barcode);
	
	@Query(value="select min(p.price) from Barcode p where p.barrcode = ?1",nativeQuery=true)
	public long getLowpriceFromJPA(String barcode);
	
	@Query(value="select sum(p.price) from Barcode p where p.barrcode = ?1",nativeQuery=true)
	public long getAvgpriceFromJPA(String barcode);
	
	@Query(value="select sum(b.price) from barcode b where b.barrcode= ?1 and b.price not in(select b1.price from barcode b1 order by b1.price limit 2) and b.price not in (select b2.price from barcode b2 order by b2.price desc limit 2)",nativeQuery=true)
	public Double getIdealpriceFromJPA(String barcode);
	
	
	

}
