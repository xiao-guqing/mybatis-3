import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.submitted.permissions.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author xiaoguqing
 * @date 2021/9/17 11:28
 */
public class MybatisTest {
  /**
   * 传统方式
   */
  public void test1() throws IOException {
    //1。读取配置文件 ，读取字节输入流，注意：现在还没解析
    InputStream resourceAsStream = Resources.getResourceAsStream("");

    //2.解析配置文件 ，封装Configuration对象    创建DefaultSqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    //3.生产了DefaultSqlsession实例对象  设置了事务不自动提交   完成了 executor对象的创建
    SqlSession sqlSession = sqlSessionFactory.openSession();

    //4. (1)根据statementid来从Configuration中map集合获取到了指定的MappedStatement对象
    //   (2)将查询任务委派了executor执行器
    List<Object> objects = sqlSession.selectList("namespace.id");
    sqlSession.update("");

  }

  /**
   * mapper代理方式
   */
  public void test2() throws IOException {
    //1。读取配置文件 ，读取字节输入流，注意：现在还没解析
    InputStream resourceAsStream = Resources.getResourceAsStream("");

    //2.解析配置文件 ，封装Configuration对象    创建DefaultSqlSessionFactory对象
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

    //3.生产了DefaultSqlsession实例对象  设置了事务不自动提交   完成了 executor对象的创建
    SqlSession sqlSession = sqlSessionFactory.openSession();

    //JDK 动态代理对mapper接口产生代理对象
    IUserMapper mapper = sqlSession.getMapper(IUserMapper.class);

    //代理对象调用接口中的任意方法，执行的都是动态代理中的invoke方法
    List<Object> allUser = mapper.findAllUser();
  }

}
