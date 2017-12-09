import com.study.hibernate.News;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.jdbc.Work;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

public class Main {

    private ServiceRegistry registry;
    private SessionFactory factory;
    private Session session;
    private Transaction transaction;

    @Before
    public void init(){
        registry = new StandardServiceRegistryBuilder().configure().build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }
   @Test
    public void testUpdate(){
       News news = session.get(News.class,2);
       transaction.commit();
       session.close();
       session = factory.openSession();
       transaction = session.beginTransaction();
       news.setContent("孙悟空");
       News news2 = session.get(News.class,2);
       session.update(news);
   }
   @Test
   public void testSaveAndUpdate(){
        News news = new News("比克2","那美克星2",new Date());
        news.setId(11);
        session.saveOrUpdate(news);
   }

   @Test
   public void testDelete(){
       //游离对象
//       News news = new News();
//       news.setId(1);
       News news = session.get(News.class,4);
       session.delete(news);
       System.out.println(news);
   }
   @Test
   public void TestEvict(){
        News news1  =session.get(News.class,5);
        News news2 = session.get(News.class,6);
        news1.setTitle("起源站");
        news2.setTitle("龟派气功");
        session.evict(news2);
   }

   @Test
   public void testDoWork(){
       session.doWork(new Work() {
           @Override
           public void execute(Connection connection) throws SQLException {
               System.out.println(connection);
               //调用存储过程
           }
       });
   }
    @After
    public void destory(){
        transaction.commit();
        factory.close();
        session.close();
    }
}