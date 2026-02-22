package com.training.hibernate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.training.hibernate.entity.Order;
import com.training.hibernate.entity.Product;
import com.training.hibernate.util.HibernateUtil;

public class HibernateDriver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Session session = HibernateUtil.getSessionFactory().openSession();

        while (true) {
            System.out.println("\n1. Add Product");
            System.out.println("2. Fetch All Products");
            System.out.println("3. Fetch Product By ID");
            System.out.println("4. Fetch Product By Name");
            System.out.println("5. Update Product");
            System.out.println("6. Delete Product");
            System.out.println("7. Place Order");
            System.out.println("8. Exit");

            int choice = sc.nextInt();

            switch (choice) {

            // ADD PRODUCT
            case 1:
                Transaction tx1 = session.beginTransaction();
                Product p = new Product();

                System.out.println("Enter Product Name:");
                p.setProdName(sc.next());

                System.out.println("Enter Description:");
                p.setProdDesc(sc.next());

                System.out.println("Enter Price:");
                p.setPrice(sc.nextDouble());

                session.persist(p);
                tx1.commit();
                System.out.println("Product Added");
                break;

            // FETCH ALL
            case 2:
                List<Product> products = session.createQuery("from Product", Product.class).list();
                products.forEach(prod ->
                        System.out.println(prod.getProdId() + " " + prod.getProdName() + " " + prod.getPrice()));
                break;

            // FETCH BY ID
            case 3:
                System.out.println("Enter Product ID:");
                long id = sc.nextLong();
                Product product = session.get(Product.class, id);

                if (product != null) {
                    System.out.println(product.getProdName() + " " + product.getPrice());
                } else {
                    System.out.println("Product not found");
                }
                break;

            // FETCH BY NAME
            case 4:
                System.out.println("Enter Product Name:");
                String name = sc.next();

                Query<Product> q = session.createQuery(
                        "from Product where prodName = :name", Product.class);
                q.setParameter("name", name);

                List<Product> list = q.list();
                list.forEach(prod ->
                        System.out.println(prod.getProdId() + " " + prod.getProdName()));
                break;

            // UPDATE
            case 5:
                Transaction tx2 = session.beginTransaction();

                System.out.println("Enter Product ID to update:");
                long updateId = sc.nextLong();

                Product updateProduct = session.get(Product.class, updateId);

                if (updateProduct != null) {
                    System.out.println("Enter new price:");
                    updateProduct.setPrice(sc.nextDouble());

                    session.update(updateProduct);
                    tx2.commit();
                    System.out.println("Product Updated");
                } else {
                    System.out.println("Product not found");
                }
                break;

            // DELETE
            case 6:
                Transaction tx3 = session.beginTransaction();

                System.out.println("Enter Product ID to delete:");
                long deleteId = sc.nextLong();

                Product deleteProduct = session.get(Product.class, deleteId);

                if (deleteProduct != null) {
                    session.delete(deleteProduct);
                    tx3.commit();
                    System.out.println("Product Deleted");
                } else {
                    System.out.println("Product not found");
                }
                break;

            // PLACE ORDER (with duplicate check)
            case 7:
                Transaction tx4 = session.beginTransaction();

                System.out.println("Enter Product ID:");
                long prodId = sc.nextLong();

                Product orderProduct = session.get(Product.class, prodId);

                if (orderProduct == null) {
                    System.out.println("Product does not exist");
                    tx4.rollback();
                    break;
                }

                // Check duplicate order within 5 minutes
                LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);

                Query<Order> orderQuery = session.createQuery(
                        "from Order where product.prodId = :pid and orderDate >= :time",
                        Order.class);

                orderQuery.setParameter("pid", prodId);
                orderQuery.setParameter("time", fiveMinutesAgo.toLocalDate());

                List<Order> existingOrders = orderQuery.list();

                if (!existingOrders.isEmpty()) {
                    System.out.println("Duplicate order within 5 minutes not allowed");
                    tx4.rollback();
                    break;
                }

                Order order = new Order();
                order.setOrderDate(LocalDate.now());
                order.setOrderType("NEW");
                order.setProduct(orderProduct);

                session.persist(order);

                tx4.commit();
                System.out.println("Order placed successfully");
                break;

            case 8:
                session.close();
                System.exit(0);
            }
        }
    }
}