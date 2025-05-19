package com.atguigu.jdbc.m_dbutils;

import com.atguigu.jdbc.d_util.JDBCUtils;
import com.atguigu.jdbc.d_util.JDBCUtilsWithDataSource;
import com.atguigu.jdbc.e_bean.Customer;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * ClassName: QueryRunnerTest
 * Package: com.atguigu.jdbc.m_dbutils
 * Description:
 * commons-dbutils是Apache组织提供的一个开源JDBC工具类库，封装了针对于数据库的增删改查操作。
 *
 * @Author ljy
 * @Create 2025. 5. 19. 오후 2:06
 * @Version 1.0
 */
public class QueryRunnerTest {

    // 测试插入
    @Test
    public void testInsert() throws SQLException {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();

            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "insert into customers(name, email, birth) values(?, ?, ?)";
            int insertCount = runner.update(conn, sql, "周阳", "zhouyang@126.com", "1973-05-19");
            System.out.println("添加了" + insertCount + "条记录");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    // 测试查询

    /*
     BeanHandler: 是ResultSetHandler接口的实现类，用于封装表中的一条记录。
     */
    @Test
    public void testQuery1() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select id, name, email, birth from customers where id = ?";
            BeanHandler<Customer> handler = new BeanHandler<>(Customer.class);
            Customer customer = runner.query(conn, sql, handler, 21);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
     BeanListHandler: 是ResultSetHandler接口的实现类，用于封装表中的多条记录构成的集合。
     */
    @Test
    public void testQuery2() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select id, name, email, birth from customers where id < ?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list = runner.query(conn, sql, handler, 21);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
     MapHandler: 是ResultSetHandler接口的实现类，对应表中的一条记录。
     将字段及相应字段的值作为map中的key和value。
     */
    @Test
    public void testQuery3() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select id, name, email, birth from customers where id = ?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, handler, 21);
            System.out.println(map);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
     MapListHandler: 是ResultSetHandler接口的实现类，对应表中的多条记录。
     将字段及相应字段的值作为map中的key和value，并将这些map添加到List中。
     */
    @Test
    public void testQuery4() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select id, name, email, birth from customers where id < ?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(conn, sql, handler, 20);
            list.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
    ScalarHandler: 用于查询特殊值。
     */
    @Test
    public void testQuery5() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            Long count = (Long) runner.query(conn, sql, handler);
            System.out.println(count);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testQuery6() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select max(birth) from customers";
            ScalarHandler handler = new ScalarHandler();
            Date maxBirth = (Date) runner.query(conn, sql, handler);
            System.out.println(maxBirth);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    /*
    自定义ResultSetHandler的实现类
     */
    @Test
    public void testQuery7() {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtilsWithDataSource.getConnection3();
            String sql = "select id,name, email,birth from customers where id = ?";
            ResultSetHandler<Customer> handler = new ResultSetHandler<>() {

                @Override
                public Customer handle(ResultSet rs) throws SQLException {
                    // System.out.println("handler");
                    // return null;
                    // return new Customer(12, "成龙", "jacky@126.com", new Date(1234567890L));
                    // 下面的逻辑就相当于BeanHandler的处理过程
                    if (rs.next()) {
                        int id = rs.getInt("id");
                        String name = rs.getString("name");
                        String email = rs.getString("email");
                        Date birth = rs.getDate("birth");
                        Customer customer = new Customer(id, name, email, birth);
                        return customer;
                    }
                    return null;
                }
            };
            Customer customer = runner.query(conn, sql, handler, 20);
            System.out.println(customer);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}
