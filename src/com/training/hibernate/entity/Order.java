package com.training.hibernate.entity;

import java.time.LocalDate;

public class Order {
	private Long orderId;
	private LocalDate orderDate;
	private String orderType;
	private Product product;
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Order(Long orderId, LocalDate orderDate, String orderType, Product product) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.orderType = orderType;
		this.product = product;
	}
	public Order() {
		super();
	}
	
	
}
