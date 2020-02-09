/**********************************************************************
 * Description: Module for CRUD operation using Restful Services
 * Author: Romu Tiwari
 * Created on: 29/01/2020
 * Employee id: 844549
 * ********************************************************************/

package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUI {

	@Autowired
	ProductService pServ;

	//Adding Product 1
	@RequestMapping(value="/admin/product",method=RequestMethod.POST,produces = "application/json")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		HttpHeaders headers = new HttpHeaders();
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		pServ.addProduct(product);		headers.add("Product Object Created -",String.valueOf(product.getProdId()));
		return new ResponseEntity<Product>(product,headers,HttpStatus.CREATED);
	}
	
	//Adding Product 2
	//	@RequestMapping(value="/product", method=RequestMethod.POST,produces="application/json")
	//	public int addProduct(@RequestBody Product product) {
	//		return pServ.addProduct(product);
	//	}
	

	//Get By ID
	@RequestMapping(value="/admin/getbyid/{prodId}",method=RequestMethod.GET)
	public ResponseEntity<Product> getById(@PathVariable("prodId") int prodId) {
		Product product = pServ.getById(prodId);
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	

	//Delete by ID
	@RequestMapping(value="/admin/delete/{prodId}",method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("prodId") int prodId){
		HttpHeaders headers = new HttpHeaders();
		Product product = pServ.getById(prodId);
		if(product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		pServ.deleteProduct(prodId);
		headers.add("Product Deleted - ", String.valueOf(prodId));
		return new ResponseEntity<Product>(product,headers,HttpStatus.NO_CONTENT);

	}
	

	//Update Product Details
	@RequestMapping(value="/admin/update/{prodId}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("prodId") int prodId, @RequestBody Product product){
		HttpHeaders headers = new HttpHeaders();
		Product isProduct = pServ.getById(prodId);
		if(isProduct == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		else if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		pServ.updateProduct(product);
		headers.add("Product Updated - ", String.valueOf(prodId));
		return new ResponseEntity<Product>(product,headers,HttpStatus.OK);
	}
	
	@RequestMapping(value="/admin",method=RequestMethod.GET)
	public String getIndex(Model model) {
		model.addAttribute("msg","Hey I am Admin");
		
		return "admin";
	}



}
