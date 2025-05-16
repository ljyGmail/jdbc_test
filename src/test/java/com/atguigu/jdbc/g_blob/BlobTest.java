package com.atguigu.jdbc.g_blob;

import com.atguigu.jdbc.d_util.JDBCUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * ClassName: BlobTest
 * Package: com.atguigu.jdbc.g_blob
 * Description:
 * 使用PreparedStatement操作Blob类型的数据
 *
 * @Author: ljy
 * @Create: 2025. 5. 16. 오후 4:05
 * @Version 1.0
 */
public class BlobTest {

    // 向数据表customers中插入Blob类型的字段
    @Test
    public void testInsert() throws Exception {
        Connection conn = JDBCUtils.getConnection();

        String sql = "insert into customers(name, email, birth, photo) values(?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setObject(1, "宋红康");
        ps.setObject(2, "shk@123.com");
        ps.setObject(3, "1973-04-12");
        FileInputStream is = new FileInputStream(new File("src/main/resources/images/shk.png"));
        ps.setBlob(4, is);

        ps.execute();

        JDBCUtils.closeResource(conn, ps);
    }
}
