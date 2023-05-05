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

public class ParentChildMapping {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
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

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
