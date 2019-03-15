package com.xebia.xebia.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xebia.xebia.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
	
	

}
