package com.war.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.spi.SessionFactoryServiceRegistryBuilder;
import org.junit.Test;

import java.sql.Date;


public class HibernateTest {
    @Test
    public void test(){
        Configuration configuration = new Configuration().configure();
        SessionFactory sf = configuration.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        News news = new News("bigNews","today is my birthday",new Date(new java.util.Date().getTime()));
        session.save(news);
        transaction.commit();
        session.close();
        sf.close();
    }
}
