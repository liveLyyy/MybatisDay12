package com.liyan.service.Impl;

import com.liyan.pojo.People;
import com.liyan.service.PeopleService;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 在数据访问层处理异常和在控制器中处理异常，service中只抛出异常
 **/
public class PeopleServiceImpl implements PeopleService {

    @Override
    public List<People> findAll() throws Exception {
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        //前面是工厂 实例化工厂对象时使用的时构建者设计模式  名称标志后面有Builder
        //构建者设计模式的意义；简化对象实例化过程
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
//        XMLConfigBuilder configBuilder=new XMLConfigBuilder(resourceAsStream);
//        Configuration configuration=configBuilder.parse();
//        DefaultSqlSessionFactory defaultSqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = factory.openSession();
        List<People> list = sqlSession.selectList("mapper.PeopleMapper.findAll");
        sqlSession.close();
        return list;
    }
}
