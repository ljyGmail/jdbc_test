package com.atguigu.jdbc.a_connection;

import com.mysql.cj.jdbc.Driver;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ClassName: ConnectionTest
 * Package: com.atguigu.jdbc.a_connection
 * Description:
 *
 * @Author ljy
 * @Create 2025. 5. 14. 오후 5:41
 * @Version 1.0
 */
public class ConnectionTest {
    // 方式一
    @Test
    public void testConnection1() throws SQLException {
        // 获取Driver的实现类对象
        Driver driver = new Driver();

        // jdbc:mysql协议
        // localhost: ip地址
        // 3306: 默认mysql的端口号
        // test: test数据库
        String url = "jdbc:mysql://localhost:3306/test";

        // 将用户名和密码封装在Properties中
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "445566hh");

        Connection conn = driver.connect(url, info);
        System.out.println("方式一: " + conn);
    }

    // 方式二: 对方式一对迭代
    @Test
    public void testConnection2() throws Exception {
        // 1. 获取Driver实现类的对象，使用反射
        Class<?> clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        // 2. 提供要连接的数据库
        String url = "jdbc:mysql://localhost:3306/test";

        // 3. 提供连接需要的用户名和密码
        Properties info = new Properties();
        info.setProperty("user", "root");
        info.setProperty("password", "445566hh");

        // 4. 获取连接
        Connection conn = driver.connect(url, info);
        System.out.println("方式二: " + conn);
    }

    // 方式三: 使用DriverManager替换Driver
    @Test
    public void testConnection3() throws Exception {
        // 1. 获取Driver实现类的对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        // 2. 提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "445566hh";

        // 注册驱动
        DriverManager.registerDriver(driver);

        // 3. 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    // 方式四: 只要加载驱动就可以了，不用显式地注册驱动了。
    @Test
    public void testConnection4() throws Exception {
        // 1. 提供另外三个连接的基本信息
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "445566hh";

        // 2. 加载Driver
        // 对于mysql数据库，甚至连这一行的代码也可以省略。在mysql驱动的jar包中的service文件夹中,可以看到该类被自动加载了。
        // 但是建议不要省略，因为这个自动加载可能只限于mysql，如果换成其它数据库，可能就没有自动加载的操作了。
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 相较于方式三，可以省略如下的操作:
        // Driver driver = (Driver) clazz.newInstance();
        // 注册驱动
        // DriverManager.registerDriver(driver);
        // 为什么可以省略上述操作呢?
        /*
        // 在mysql的Driver实现类中，声明了如下的操作:
        // Register ourselves with the DriverManager.
        static {
            try {
                java.sql.DriverManager.registerDriver(new Driver());
            } catch (SQLException E) {
                throw new RuntimeException("Can't register driver!");
            }
        }
         */


        // 3. 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }

    // 方式五(final版): 将数据库连接需要的4个基本信息声明在配置文件中，通过读取配置文件的方式获取连接
    /*
    此种方式的好处？
    1. 实现了数据与代码的分离，实现了解藕
    2. 如果需要修改配置文件的信息，可以避免程序重新打包。
     */
    @Test
    public void getConnection5() throws Exception {
        // 1. 读取配置文件中的4个基本信息
        InputStream is = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");

        Properties props = new Properties();
        props.load(is);

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String url = props.getProperty("url");
        String driverClass = props.getProperty("driverClass");

        // 2. 加载驱动
        Class.forName(driverClass);

        // 3. 获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);
    }
}
