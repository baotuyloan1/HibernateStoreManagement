/*
 * ParentChildMapping.java
 * Copyright by Nguyen Duc Bao
 * Created on 5 - 5 - 2023 (mm-dd-yyyy)
 */

package org.example;

import org.example.Entity.Category;
import org.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Set;

public class ParentChildMapping {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
//        save(session);

        listCategories(session);

        transaction.commit();
        session.close();
        sessionFactory.close();
    }

    private static void listCategories(Session session) {
        Category category = session.get(Category.class, 79l);

        Set<Category> childrens = category.getChildren();
        System.out.println(category.getName());
        for (Category child : childrens) {
            System.out.println("--" + child.getName());
            printChildren(child,1);
        }

    }

    private static void printChildren(Category parent, int subLevel) {
        Set<Category> children = parent.getChildren();
        for (Category child : children) {
            for (int i = 0; i <= subLevel; i++)
                System.out.print("--");
            System.out.println(child.getName());
            printChildren(child, subLevel + 1);
        }
    }


    private static void save(Session session) {
        Category electronis = new Category("Electronics");
        Category mobilePhones = new Category("MObile Phones", electronis);
        Category washingMachines = new Category("Washing machines", electronis);

        electronis.addChild(mobilePhones);
        electronis.addChild(washingMachines);

        Category iPhone = new Category("iPhone", mobilePhones);
        Category samsung = new Category("Samsung", mobilePhones);


        mobilePhones.addChild(iPhone);
        mobilePhones.addChild(samsung);

        Category galaxy = new Category("Galaxy", samsung);
        samsung.addChild(galaxy);

        session.save(electronis);
    }
}
