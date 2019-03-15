package com.xebia.xebia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.xebia.xebia.entity.*;
import com.xebia.xebia.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService proservice;
	
	
	@RequestMapping("/saveproductform")
    public String saveproductform(Product product) {
        return "save-product";
    }
	
	
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getProductList(Model model)
	{
		 model.addAttribute("products",proservice.getproductList());
	     return "index";
	}
	
	@RequestMapping(value="/saveproduct",method=RequestMethod.POST)
	public String saveProduct(Product product, BindingResult result, Model model)
	{
	     proservice.savepro(product);
	     model.addAttribute("products",proservice.getproductList());
	     return "index";
	    
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable long id, Model model)
	{
		
		 proservice.deletestore(id);
	     proservice.deleteproduct(id);     
	     
	     
	     model.addAttribute("products",proservice.getproductList());
	     return "index";
	    
	}
	
	@RequestMapping(value="/getStoreDetails",method=RequestMethod.GET)
	public String getStoreDetails(Model model)
	{
	  
	     model.addAttribute("stores",proservice.getStoreList());
	     return "store-details";
	    
	}
	
}
