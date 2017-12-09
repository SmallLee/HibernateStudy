import com.study.hibernate.News;
import org.hibernate.*;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.MetadataSourceType;
import org.hibernate.internal.SessionFactoryRegistry;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.metamodel.EntityType;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Main {

    private Session session;
    private ServiceRegistry registry;
    private SessionFactory factory;
    private Transaction transaction;
    @Before
    public void init(){
        //创建服务注册器
        registry = new StandardServiceRegistryBuilder().configure().build();
        //构建Meta，然后构建SessionFactory
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    @Test
    public void test() {
        News news = new News("新闻", "新闻内容", new Date());
        System.out.println(news);
        news.setId(2200);//无效
        session.save(news);
//        news.setId(222);抛异常
        System.out.println(news);
    }

    @Test
    public void testPersist() {
        News news = new News("新闻s", "新闻内容", new Date());
        news.setId(11);
        session.persist(news);
        System.out.println(news);
    }

    @Test
    public void testSessionCache(){
        News news = session.get(News.class, 5);
        news.setTitle("天猫");
        //查询所有的结果
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<News> query = criteriaBuilder.createQuery(News.class);
        query.from(News.class);
        Query<News> sessionQuery = session.createQuery(query);
        List<News> resultList = sessionQuery.getResultList();
        System.out.println(resultList);
    }
    @Test
    public void testSessionCache2(){
        News news = new News("支付宝","发红包",new Date());
        session.save(news);
    }

    @Test
    public void testRefresh(){
        News news = session.get(News.class,6);
        System.out.println(news);
        session.refresh(news);
        System.out.println(news);
    }
    @Test
    public void testSessionClear(){
        News news = session.get(News.class,6);
        System.out.println(news);
//        session.clear();
        News news2 = session.get(News.class,6);
        System.out.println(news2);
    }

    @Test
    public void testLoad(){
        News news = session.load(News.class, 10);
//        System.out.println(news);
//        session.close();
//        System.out.println(news);
    }
    @After
    public void destory(){
        transaction.commit();;
        factory.close();
        session.close();
    }
}