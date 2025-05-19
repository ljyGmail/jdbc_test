package com.atguigu.jdbc.j_dao;

import com.atguigu.jdbc.d_util.JDBCUtils;
import com.atguigu.jdbc.e_bean.Customer;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * ClassName: CustomDAOImplTest
 * Package: com.atguigu.jdbc.j_dao
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 19. 오전 9:32
 * @Version 1.0
 */
public class CustomDAOImplTest {

    private CustomDAOImpl dao = new CustomDAOImpl();

    @Test
    public void testInsert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Customer cust = new Customer(1, "封捷", "fengjie@126.com", new Date(43534646435L));
            dao.insert(conn, cust);
            System.out.println("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testDeleteById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            dao.deleteById(conn, 13);
            System.out.println("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testUpdateById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Customer cust = new Customer(18, "贝多芬", "beiduofeng@126.com", new Date(453465656L));
            dao.updateById(conn, cust);
            System.out.println("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testGetCustomerById() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Customer cust = dao.getCustomerById(conn, 19);
            System.out.println(cust);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testGetAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            List<Customer> list = dao.getAll(conn);
            list.forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testGetCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Long count = dao.getCount(conn);
            System.out.println("表中的记录数为: " + count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }

    @Test
    public void testGetMaxBirth() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            Date maxBirth = dao.getMaxBirth(conn);
            System.out.println("最大生日为: " + maxBirth);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, null);
        }
    }
}