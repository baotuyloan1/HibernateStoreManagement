/*
 * StockManager.java
 * Copyright by Nguyen Duc Bao
 * Created on 5 - 5 - 2023 (mm-dd-yyyy)
 */

package org.example;

import org.example.Entity.Category;
import org.example.Entity.Product;
import org.example.Entity.ProductDetail;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashSet;
import java.util.Set;

public class StockManager {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Category category = new Category("Computer");

        Product pc = new Product("DELL PC", "Quad-core PC", 1200, category);


        Product laptop = new Product("MacBook", "Apple High-end laptop", 2100, category);

        Product phone = new Product("iPhone 5", "Apple Best-selling smartphone", 499, category);

        Product tablet = new Product("iPad 3", "Apple Best-selling tablet", 1099, category);

        Set<Product> products = new HashSet<>();
        products.add(pc);
        products.add(laptop);
        products.add(phone);
        products.add(tablet);
        category.setProducts(products);

        session.save(category);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }
}
