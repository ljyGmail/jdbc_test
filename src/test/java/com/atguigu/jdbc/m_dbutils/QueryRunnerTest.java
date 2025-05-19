package com.atguigu.jdbc.m_dbutils;

import com.atguigu.jdbc.d_util.JDBCUtils;
import com.atguigu.jdbc.d_util.JDBCUtilsWithDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

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
}
