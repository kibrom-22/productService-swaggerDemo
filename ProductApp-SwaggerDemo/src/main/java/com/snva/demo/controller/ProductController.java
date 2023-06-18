package com.snva.demo.controller;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.snva.demo.dto.ProductRequest;
import com.snva.demo.dto.ProductResponse;
import com.snva.demo.entity.Product;
import com.snva.demo.service.ProductService;


import org.modelmapper.ModelMapper;
@RestController
@RequestMapping("/api/products")
public class ProductController {
	
    @Autowired
	private  ProductService productService;
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping("/addProduct")//creates new product and store it in to MySQL database
	public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequest productRequest){
		
		return new ResponseEntity<Product>(productService.addProduct(productRequest), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}") //find product ById
	public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") int productId){
	ProductResponse productResponse =	productService.getProductById(productId);
	return new ResponseEntity<>(productResponse, HttpStatus.OK);
	}
	

	
	@GetMapping   // Fetch all Products
	public List<ProductResponse> getAllproducts(){
		return productService.getAllProducts()
				.stream()
				.map(product->modelMapper.map(product, ProductResponse.class))
				.collect(Collectors.toList());
	}
	
	@PutMapping("/{id}")  // Update Product ById
	public ResponseEntity<ProductRequest> updateProduct(@PathVariable("id") int productId, @RequestBody ProductRequest productRequest){
		//convert DTO Rquest to Entity
		Product request = modelMapper.map(productRequest, Product.class);
		Product product = productService.updateProduct(productId, productRequest);
		//convert Entity to DTO Response
		ProductResponse response = modelMapper.map(product, ProductResponse.class);
		
		return new ResponseEntity(response, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")  //Remove Product ById
	public ResponseEntity<String> deleteProductById(@PathVariable("id") int productId){
		productService.deleteProductById(productId);
		return new ResponseEntity<String>("Product with ProductId: "+productId+" Removed Succussfully", HttpStatus.OK);
	}
	

}
