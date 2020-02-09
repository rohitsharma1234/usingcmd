/**********************************************************************
 * Description: Module for CRUD operation using Restful Services
 * Author: Romu Tiwari
 * Created on: 29/01/2020
 * Employee id: 844549
 * ********************************************************************/

package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	
	@Autowired
	ProductDao pDao;
	
	//Adding Product
	public int addProduct(Product product) {
		
		return pDao.addProduct(product);
	}
	
	//Get By ID
	public Product getById(int prodId) {
		
		return pDao.getById(prodId);
	}
	
	//Delete Product
	public int deleteProduct(int prodId) {
		
		return pDao.deleteProduct(prodId);
	}
	
	//Update Product
	public int updateProduct(Product product) {
		
		return pDao.updateProduct(product);
	}

}

