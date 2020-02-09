/**********************************************************************
 * Description: Module for CRUD operation using Restful Services
 * Author: Romu Tiwari
 * Created on: 29/01/2020
 * Employee id: 844549
 * ********************************************************************/

package com.cts.controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {

	@Autowired
	private DataSource ds;
	private JdbcTemplate jdbc;

	//Setter for JDBC Template
	public void setJdbc(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	//Adding Product To Database
	public int addProduct(Product product){
		jdbc = new JdbcTemplate(ds);
		int storedStatus = jdbc.update("INSERT INTO product VALUES(?,?,?,?)", new Object[] {product.getProdId(),product.getProdName(),product.getProdQuantity(),product.getProdPrice()});
		System.out.println(storedStatus);
		return product.getProdId();
	}
	

	//Getting Product Details by ID
	public Product getById(int prodId) {
		jdbc = new JdbcTemplate(ds);
		String sql = "SELECT * FROM product WHERE prod_id=?";
		Product product = (Product)jdbc.queryForObject(sql, new Object[] {prodId},

				new RowMapper<Product>() {
			
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

				Product product = new Product();

				product.setProdId(rs.getInt(1));
				product.setProdName(rs.getString(2));
				product.setProdQuantity(rs.getInt(3));
				product.setProdPrice(rs.getFloat(4));

				return product;
			}

		});
		return product;
	}
	

	//Delete Product by ID
	public int deleteProduct(int prodId) {
		jdbc = new JdbcTemplate(ds);
		int count = jdbc.update("DELETE FROM product WHERE prod_id=?", new Object[] {prodId});
		return count;
	}
	
	
	//Update Product Details by ID
	public int updateProduct(Product product) {
		jdbc = new JdbcTemplate(ds);
		String sql = "UPDATE product SET prod_name=?, prod_quantity=?, prod_price=? WHERE prod_id =?";
		int count = jdbc.update(sql,new Object[] 
				{product.getProdName(),product.getProdQuantity(),product.getProdPrice(),product.getProdId()});
		return count;
	}



}

