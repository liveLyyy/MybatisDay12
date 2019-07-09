[全局配置文件详解](https://www.w3cschool.cn/mybatis/)
===
数据库连接池:
===
>1、在内存中开辟一个空间，存放多个数据库连接对象<br>
>2、JDBC Tomcat Pool，直接由Tomcat产生连接池<br>
>3、使用数据库连接池的目的：<br>
* >>在高频率访问数据库时，使用数据库连接池可以降低服务器系统压力<br>
* >>小型项目不适用数据库连接池<br>
>4、实现JDBC Tomcat Pool的步骤：<br>
>>在web项目的META-INF中存放context.xml<br>
* >>>context.xml详解：<br>
*   >>>>context.xml的三个作用范围：<br>
*   >>>>>tomcat server级别：在/conf/context.xml里配置。<br>
*   >>>>>Host级别：在/conf/Catalina/${hostName}里添加context.xml，继而进行配置。<br> 
*   >>>>>web app 级别：在/conf/Catalina/${hostName}里添加${webAppName}.xml，继而进行配置。<br>
* >>Tomcat5.5之前<br>
*   >>>><Context>元素：Context体现在/conf/server.xml中的Host里的<Context>元素，它由Context接口定义。每个<Context>元素代表了运行在虚拟主机上的单个Web应用。<br>
```
    <Context path="/kaka" docBase="kaka" debug="0" reloadbale="true"> <br>
```
*   >>>>>path：即要建立的虚拟目录，,注意是/kaka，它指定访问Web应用的URL入口<br>
*   >>>>>docBase：为实际目录在硬盘上的位置（应用程序的路径或者是WAR文件存放的路径）<br>
*   >>>>>reloadable：如果这个属性设为true，Tomcat服务器在运行状态下会监视在WEB-INF/classes和Web-INF/lib目录CLASS文件的改变，如果监视到有class文件被更新，服务器自动重新加载Web应用，这样我们可以在不重起tomcat的情况下改变应用程序<br>
*   >>注：一个Host元素中嵌套任意多的Context元素。每个Context的路径必须是惟一的，由path属性定义。另外，你必须定义一个path=“”的context，这个Context称为该虚拟主机的缺省web应用，用来处理那些不能匹配任何Context的Context路径的请求。<br>
* >>Tomcat5.5之后<br>
>>不推荐在server.xml中进行配置，而是在/conf/context.xml中进行独立的配置。因为server.xml是不可动态重加载的资源，服务器一旦启动了以后，要修改这个文件，就得重启服务器才能重新加载。而context.xml文件则不然，tomcat服务器会定时去扫描这个文件。一旦发现文件被修改（时间戳改变了），就会自动重新加载这个文件，而不需要重启服务器。<br>
```
    <Context path="/kaka" docBase="kaka" debug="0" reloadbale="true" privileged="true">  
        <WatchedResource>WEB-INF/web.xml</WatchedResource>    
        <WatchedResource>WEB-INF/kaka.xml</WatchedResource> 监控资源文件，如果web.xml || kaka.xml改变了，则自动重新加载改应用。    
        <Resource name="jdbc/testSiteds" 表示指定的jndi名称  
           auth="Container" 表示认证方式，一般为Container  
           type="javax.sql.DataSource"  
          maxActive="100" 连接池支持的最大连接数  
          maxIdle="30" 连接池中最多可空闲maxIdle个连接  
          maxWait="10000" 连接池中连接用完时,新的请求等待时间,毫秒  
          username="root" 表示数据库用户名  
          password="root" 表示数据库用户的密码  
          driverClassName="com.mysql.jdbc.Driver" 表示JDBC DRIVER  
          url="jdbc:mysql://localhost:3306/testSite" /> 表示数据库URL地址   
    </Context>
```
三种查询方式
===
>1、selectList：返回值List<resultType属性控制>,适用于查询结果都需要遍历。<br>
```
      List<Flower> list = sqlSession.selectList("a.b.findAll");
           for (Flower flower : list) {
            System.out.println(flower.toString());
      }
```      
>2、selectOne:返回值魏Object，适用于返回结果只是变量或一行数据。<br>
```
         int count = sqlSession.selectOne("a.b.findById");
         System.out.println(count)
```
>3、selectMap:返回值Map，适用于需求需要在查询结果中通过某列的值取到这行的需求,Map<Key,resultType控制>。<br>
```
       Map<Object, Object> map = sqlSession.selectMap("a.b.c", "name");
        System.out.println(map);
```
[Log4j](https://blog.csdn.net/u013870094/article/details/79518028)
===
<<<<<<< HEAD
parameterType
===
*   >1、在XXMapper.xm;中<select><delect>等标签的parameterType可以控制参数类型。 <br>
*   >2、SqlSessiond的selectList()和selectOne()的第二个参数和selectMap()的第三个参数都表示方法的参数。 <br>
```
    int count = sqlSession.selectOne("a.b.findById",1);
           System.out.println(count);
```
*   >>在Mapper.xml文件中可以通过#{}来获取参数：#{}使用索引，从0开始#{0}表示第一个参数，也可以使用#{param}的一个参数，如果只有一个参数（基本数据类型或String）mybatis对#{}里面内容要求只要写内容就行,如果参数是对象#{属性名}，如果参数是map写成#{map}。 <br>
```
<select id="findById" resultType="com.liyan.pojo.People" parameterType="int">
        select * from people where id = #{id}
    </select>
```
*   >3、#{}和$()区别 <br>
*   >>1、#{}获取参数的内容支持，索引获取param获取指定位置参数并且sql使用?占位符。 <br>
*   >>2、${}字符串拼接不使用?,默认找${内容}内容的get/set方法,如果写数字就是指数字的值。 <br>

Mybatis分页
===
>  1、？不允许在关键字前后进行数学运算，需要要代码中进行。 <br>
```
        //显示几个数据
        int pageSize=2;
        //第几页
        int pageNumber=2;
        Map<String,Object> map=new HashMap<>();

        map.put("pageSize",pageSize);
        map.put("pageStart",pageSize*(pageNumber-1));
        InputStream config = null;
        try {
            config = Resources.getResourceAsStream("mybatis.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(config);
            SqlSession sqlSession = factory.openSession();
           List<People> p=sqlSession.selectList("mapper.PeopleMapper.findpage",map);
            System.out.println(p);
        } catch (IOException e) {
            e.printStackTrace();
        }
```

