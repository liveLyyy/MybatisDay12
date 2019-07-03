package com.liyan.test;

import com.liyan.pojo.Flower;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;


public class FlowerTest {
    @Test
    public void find() throws Exception {
        //使用工厂设计模式
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();
        List<Flower> list = sqlSession.selectList("a.b.findAll");

        for (Flower flower:list){
            System.out.println(flower.toString());
        }

    }
}
