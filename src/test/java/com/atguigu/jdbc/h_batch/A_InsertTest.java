package com.atguigu.jdbc.h_batch;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * ClassName: A_InsertTest
 * Package: com.atguigu.jdbc.h_batch
 * Description:
 * 使用PreparedStatement实现批量数据的操作
 * <p>
 * update、delete本身就具有批量操作的效果。
 * 此时的批量操作，主要指的是批量插入。使用PreparedStatement如何实现更高效的批量插入？
 * <p>
 * 题目: 向goods表中插入20000条数据
 * CREATE TABLE goods
 * (
 * id   INT PRIMARY KEY AUTO_INCREMENT,
 * name VARCHAR(25)
 * );
 * <p>
 * 方式1: 使用Statement
 * Connection conn = JDBCUtils.getConnection();
 * Statement st = conn.createStatement();
 * for (int i = 1; i <= 20000; i++) {
 * String sql = "insert into goods(name) values('name_" + i + "')";
 * st.execute(sql);
 * }
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오후 5:21
 * @Version 1.0
 */
public class A_InsertTest {

    // 批量插入的方式2: 使用PreparedStatement
    @Test
    public void testInsert1() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            long start = System.currentTimeMillis();
            conn = JDBCUtils.getConnection();
            String sql = "insert into goods(name) values(?)";
            ps = conn.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setObject(1, "name_" + i);

                ps.execute();
            }
            long end = System.currentTimeMillis();
            System.out.println("话费的时间为: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(conn, ps);
        }
    }
}
