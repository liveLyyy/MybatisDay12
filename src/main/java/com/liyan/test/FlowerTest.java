package com.liyan.test;

import com.liyan.pojo.Flower;
import com.liyan.pojo.People;
import com.liyan.service.Impl.PeopleServiceImpl;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;
import java.util.Map;


public class FlowerTest {
    @Test
    public void find() throws Exception {
        //使用工厂设计模式
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();
        List<Flower> list = sqlSession.selectList("a.b.findAll");

        for (Flower flower : list) {
            System.out.println(flower.toString());
        }

    }

    @Test
    public void findById() throws Exception {
        //使用工厂设计模式
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();
        int count = sqlSession.selectOne("a.b.findById");
        System.out.println(count);
        sqlSession.close();

    }

    @Test
    public void findMap() throws Exception {
        //使用工厂设计模式
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();
        Map<Object, Object> map = sqlSession.selectMap("a.b.c", "name");
        System.out.println(map);
        sqlSession.close();

    }

    @Test
    public void findPeopleAll() throws Exception {
        PeopleServiceImpl peopleService = new PeopleServiceImpl();
        List<People> list = peopleService.findAll();
        for (People people:list){
            System.out.println(people.toString());
        }
    }
}
