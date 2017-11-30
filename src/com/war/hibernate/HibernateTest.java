package com.war.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
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
        //创建一个Configuration对象，改对象保存了配置文件和对象映射文件,不推荐使用了
//        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .configure().build();
        SessionFactory sf = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
        News news = new News("bigNews2","today is my birthday",new Date(new java.util.Date().getTime()));
        session.save(news);
        News news2 = session.get(News.class, 1);
        System.out.println(news2);
        transaction.commit();
        session.close();
        sf.close();
    }
}
