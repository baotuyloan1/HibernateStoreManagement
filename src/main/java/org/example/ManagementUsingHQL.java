/*
 * ManagementUsingHQL.java
 * Copyright by Nguyen Duc Bao
 * Created on 5 - 5 - 2023 (mm-dd-yyyy)
 */

package org.example;

import org.example.Entity.Category;
import org.example.Entity.Order;
import org.example.Entity.Product;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

public class ManagementUsingHQL {
    public static void main(String[] args) throws ParseException {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

//        listQuery(session);
        session.beginTransaction();

//        save(session);
//            listQuery(session);
//        searchQuery(session);
//        updateHQL(session);
//        deleteHQL(session);
//        joinQuery(session);
//        sortQuery(session);
//        groupByQuery(session);
//        paginationQuery(session);
//        addOrder(session);
//        dateRangeQuery(session);
//        aggregate(session);
//        namedParameters(session);


//        implicitJoin(session);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();


    }

    private static void implicitJoin(Session session) {
        String hql = "from Product where category.name = 'Mobile 1'";
        Query query = session.createQuery(hql);
        List<Product> objects = query.getResultList();
        objects.forEach(System.out::println);
    }


    private static void namedParameters(Session session) {
        String hql = "from Order where  customerName like :keyword";
        String keyword = "Bao 2";
        Query query = session.createQuery(hql);
        query.setParameter("keyword", "%" + keyword + "%");
        List<Order> orderList = query.getResultList();
        orderList.forEach(System.out::println);
    }

    private static void searchQuery(Session session) {
        String hql = "from Product where category.name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", "Mobile");
        List<Product> productList = query.getResultList();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void aggregate(Session session) {
        String hql = "select count(name) from Category ";
        Query query = session.createQuery(hql);
        List<Number> countName = query.getResultList();
        countName.forEach(System.out::println);

        Query query1 = session.createQuery(hql);
        Number list = (Number) query.getSingleResult();
        System.out.println(list);

    }

    private static void dateRangeQuery(Session session) throws ParseException {
        String hql = "from Order where purchaseDate >= :beginDate and purchaseDate <= :endDate";
        Query query = session.createQuery(hql);
        query.setParameter("beginDate", new SimpleDateFormat("yyyy-MM-dd").parse("2023-5-1"));
        query.setParameter("endDate", new SimpleDateFormat("yyyy-MM-dd").parse("2023-5-30"));

        List<Order> orderList = query.getResultList();
        orderList.forEach(System.out::println);
    }

    private static void addOrder(Session session) throws ParseException {
        Product product = session.get(Product.class, 21l);


        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        Order order = new Order();
        order.setAmount(3);
        order.setCustomerName("Nguyen Duc Bao");
        order.setPurchaseDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-5-4"));
        order.setProduct(product);

        Order order1 = new Order();
        order1.setAmount(7);
        order1.setCustomerName("Nguyen Duc Bao 2");
        order1.setPurchaseDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-9-4"));
        order1.setProduct(product);


        Order order2 = new Order();
        order2.setAmount(5);
        order2.setCustomerName("Nguyen Duc Bao 1");
        order2.setPurchaseDate(new SimpleDateFormat("yyyy-MM-dd").parse("2023-1-1"));
        order2.setProduct(product);

        session.save(order2);
        session.save(order);
        session.save(order1);
    }

    private static void paginationQuery(Session session) {
        String hql = "from Category ";
        Query query = session.createQuery(hql);
        query.setFirstResult(2);
        query.setMaxResults(2);

        List<Category> categoryList = query.getResultList();
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }

    private static void groupByQuery(Session session) {
        String hql = "select sum(p.price), p.category.id from Product  p group by p.category.id";
        Query query = session.createQuery(hql);
        List<Object[]> listResult = query.getResultList();
//        System.out.println(listResult);
        for (Object[] aRow : listResult) {
            Double sum = (Double) aRow[0];
            Long category = (Long) aRow[1];
            System.out.println(category + " - " + sum);
        }
    }

    private static void sortQuery(Session session) {
        String hql = "from Product order by price DESC ";
        Query query = session.createQuery(hql);
        List<Product> productList = query.getResultList();
        for (Product product : productList) {
            System.out.println(product);
        }
    }

    private static void joinQuery(Session session) {
        String hql = "from Product p inner join p.category";
        Query query = session.createQuery(hql);
        List<Object[]> list = query.getResultList();
        for (Object[] aRow : list) {
            Product product = (Product) aRow[0];
            Category category = (Category) aRow[1];
            System.out.println(product.getName() + " - " + category.getName());
        }

        String hql1 = "from Product";
        Query query1 = session.createQuery(hql1);
        List<Product> listProduct = query1.getResultList();
        for (Product product : listProduct) {
            System.out.println(product.getName() + " - " + product.getCategory().getName());

        }

        String hql2 = "from Product p inner join p.category where p.price > 500";
        Query query2 = session.createQuery(hql2);
        List<Object[]> objects = query2.getResultList();
        for (Object[] object : objects) {
            Product product = (Product) object[0];
            Category category = (Category) object[1];
            System.out.println(product + " - " + category);
        }

        String hql3 = "from Product where category.id = :id";
        Query query3 = session.createQuery(hql3);
        query3.setParameter("id", 20l);
        List<Product> products = query3.getResultList();
        for (Product product : products) {
            System.out.println("Products with category id = 20 == " + product);
        }
    }

    private static void deleteHQL(Session session) {
        String hql = "delete from Category where id = :catId";
        Query query = session.createQuery(hql);
        query.setParameter("catId", 10L);
        int rowAffected = query.executeUpdate();
        if (rowAffected > 0) {
            System.out.println("Delete" + rowAffected);
        }
    }

    private static void updateHQL(Session session) {
        String hql = "update Product set price = :price where id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("price", 488.08f);
        query.setParameter("id", 21l);
        int rowAffected = query.executeUpdate();
        if (rowAffected > 0) {
            System.out.println("Updated " + rowAffected);
        }
    }

    private static void save(Session session) {
//       HQL không hỗ trợ INSERT statement vì session.save() method does it perfectly
        Category category = new Category();
        category.setName("Mobile");
        session.save(category);
        Product product1 = new Product();
        product1.setName("Iphone13");
        product1.setPrice(1000L);
        product1.setDescription("New iphone description");
        product1.setCategory(category);
        Product product2 = new Product();
        product2.setName("Iphone14");
        product2.setPrice(1001L);
        product2.setDescription("New iphone description 1");
        product2.setCategory(category);

        session.save(product1);
        session.save(product2);
    }

    private static void listQuery(Session session) {
        String hql = "from Category ";
        Query query = session.createQuery(hql);
        List<Category> categoryList = query.getResultList();
        for (Category category : categoryList) {
            System.out.println(category);
        }
    }
}