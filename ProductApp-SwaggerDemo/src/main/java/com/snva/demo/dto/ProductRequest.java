package com.snva.demo.dto;


import javax.validation.constraints.*;

import org.hibernate.validator.constraints.URL;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
	
    @NotNull(message = " name is required")
    @Size(max = 100, message = "name has to be 100 or less characters")
    private String name;

  
    @Size(max = 500, message = "Product description 500 characters")
    private String description;

    
    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.0", inclusive = false)
    private Double price;

   
    @NotEmpty(message = "Product category is required")
    @Size(max = 50)
    private String category;

    @URL(message = "valid URL Required")
    private String image;

}
