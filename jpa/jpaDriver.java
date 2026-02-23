package com.training.jpa;

import java.time.LocalDateTime;

import com.training.jpa.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class jpaDriver {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myUnit");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
//		Product product = new Product();
//		product.setProdName("Laptop");
//		product.setPrice(80000.0);
//		product.setProdDesc("Electronic");
//		product.setCreatedAt(LocalDateTime.now());
//		em.persist(product);
//		tx.commit();
//		System.out.println("");
		em.close();
		emf.close();
		
		
	}
}
