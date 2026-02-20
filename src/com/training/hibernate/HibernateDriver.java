package com.training.hibernate;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.training.hibernate.entity.Order;
import com.training.hibernate.entity.Product;
import com.training.hibernate.util.HibernateUtil;

public class HibernateDriver {
	public static void main(String[] args) {
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Transaction transaction = session.beginTransaction();
//		Product product = new Product();
//		product.setProdName("laptop");
//		product.setProdDesc("electornics");
//		product.setPrice(2100);
//		session.persist(product);
//		
//		Order order = new Order();
//		order.setOrderDate(LocalDate.now());
//		order.setOrderType("new");
//		order.setProduct(product);
//		
//		session.persist(order);
//		
//		transaction.commit();
//		session.close();
		
		
		Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        
        List<Product> products = session.createQuery("from Product", Product.class).list();

        for (Product p : products) {
            System.out.println(p.getProdId() + " " + p.getProdName() + " " + p.getPrice());
        }

//        transaction.commit();
        session.close();
		
	}
}
