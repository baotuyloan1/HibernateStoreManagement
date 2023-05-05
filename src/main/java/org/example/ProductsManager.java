/*
 * ProductsManager.java
 * Copyright by Nguyen Duc Bao
 * Created on 5 - 5 - 2023 (mm-dd-yyyy)
 */

package org.example;

import org.example.Entity.Product;
import org.example.Entity.ProductDetail;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProductsManager {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Product product = new Product();
        product.setName("Civic 1");
        product.setDescription("Comfortable, fuel-saving car 1");
        product.setPrice(20000);

        ProductDetail detail = new ProductDetail();
        detail.setPartNumber("ABCSDSFASF");
        detail.setDimension("2,5m x 1,4m x 1,2m");
        detail.setWeight(1000);
        detail.setManufacturer("Honda Automobile");
        detail.setOrigin("Japan");

        product.setProductDetail(detail);
        detail.setProduct(product);

//       lưu vào bộ nhớ đệm, chưa lưu vào database
        session.save(product);

        List<Product> productList = session.createQuery("from Product ").getResultList();
        productList.forEach(System.out::println);

//        session.close();
//        sessionFactory.close();
    }
}
