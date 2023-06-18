package com.snva.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.snva.demo.dto.ProductRequest;
import com.snva.demo.dto.ProductResponse;
import com.snva.demo.entity.Product;
import com.snva.demo.exception.ProductNotFoundException;
import com.snva.demo.repository.ProductRepository;
import java.util.*;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import static org.springframework.beans.BeanUtils.copyProperties;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Log4j2
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Product addProduct(ProductRequest productRequest) {
		
		log.info("ProductService | addProduct is called");
		
		Product product = Product.builder()
				.name(productRequest.getName())
				.description(productRequest.getDescription())
				.price(productRequest.getPrice())
				.category(productRequest.getCategory())
				.image(productRequest.getImage())
						
						.build();
		 log.info("ProductService | addProduct | Product Created");
	        log.info("ProductService | addProduct | Product Id : " + product.getId());
return productRepository.save(product);
	}
	
	public ProductResponse getProductById(int productId) {
		log.info("ProductService | getProductById is called");
		log.info("ProductService | getById | Get the Product for productId: {}", productId);
		
		Product product
		        = productRepository.findById(productId)
		        .orElseThrow(()-> new ProductNotFoundException("Product with given with Id: " + productId + " not found:",
	                    "PRODUCT_NOT_FOUND"));
		
		ProductResponse productResponse
        = new ProductResponse();

copyProperties(product, productResponse);

log.info("ProductService | getProductById | productResponse :" + productResponse.toString());

return productResponse;
	}
	
	public List<Product> getAllProducts(){
		
	 return productRepository.findAll();
	}
	
	
	public Product updateProduct(int productId, ProductRequest productRequest) {
		Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("Product with given with Id: " + productId + " not found:",
                "PRODUCT_NOT_FOUND"));
	product.setName(productRequest.getName());
	product.setPrice(productRequest.getPrice());
	product.setDescription(productRequest.getDescription());
	product.setCategory(productRequest.getCategory());
	product.setImage(productRequest.getImage());
	
	return productRepository.save(product);
	}
	
	public void deleteProductById(int productId) {
		log.info("Product id: {}", productId);
		if(!productRepository.existsById(productId)) {
			throw new ProductNotFoundException(
                    "Product with given Id: " + productId + " not found:",
                    "PRODUCT_NOT_FOUND");
			
		}else {
			productRepository.deleteById(productId);
		}
	}
}
