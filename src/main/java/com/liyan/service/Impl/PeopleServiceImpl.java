package com.liyan.service.Impl;

import com.liyan.file.PageInfo;
import com.liyan.pojo.People;
import com.liyan.service.PeopleService;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo findPage(int pagesize, int pagenumber) throws Exception {
        PageInfo pageInfo = new PageInfo();
        pageInfo.setPageNumber(pagenumber);
        pageInfo.setPageSize(pagesize);
        InputStream config = Resources.getResourceAsStream("mybatis.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
        SqlSession sqlSession = factory.openSession();
        Map<String, Object> map = new HashMap<>();
        map.put("pageStart", pagesize * (pagenumber - 1));
        map.put("pageSize", pagesize);
        pageInfo.setList(sqlSession.selectList("mapper.PeopleMapper.findpage", map));
        //总条数
        Long count = sqlSession.selectOne("mapper.PeopleMapper.findCount");
        pageInfo.setTotal(count%pagesize==0?count/pagesize:count/pagesize+1);
        return pageInfo;
    }


}
