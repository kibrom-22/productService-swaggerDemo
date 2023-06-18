package com.snva.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.snva.demo.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
