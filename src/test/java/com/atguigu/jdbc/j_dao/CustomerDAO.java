package com.atguigu.jdbc.j_dao;

import com.atguigu.jdbc.e_bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * ClassName: CustomerDAO
 * Package: com.atguigu.jdbc.j_dao
 * Description:
 * 此接口用于规范针对于customers表的常用操作
 *
 * @Author: ljy
 * @Create: 2025. 5. 18. 오후 11:46
 * @Version 1.0
 */
public interface CustomerDAO {

    /**
     * 将cust对象添加到数据库中
     *
     * @param conn
     * @param cust
     */
    void insert(Connection conn, Customer cust);

    /**
     * 针对于指定的id，删除表中的一条记录
     *
     * @param conn
     * @param id
     */
    void deleteById(Connection conn, int id);

    /**
     * 针对内存中的cust对象，去修改数据表中指定的记录
     *
     * @param conn
     * @param cust
     */
    void updateById(Connection conn, Customer cust);

    /**
     * 针对于指定的id查询得到对应的Customer
     *
     * @param conn
     * @param id
     * @return
     */
    Customer getCustomerById(Connection conn, int id);

    /**
     * 查询表中的所有记录构成的集合
     *
     * @param conn
     * @return
     */
    List<Customer> getAll(Connection conn);

    /**
     * 返回数据表中数据的条目数
     *
     * @param conn
     * @return
     */
    Long getCount(Connection conn);

    /**
     * 返回数据表中最大的生日
     *
     * @param conn
     * @return
     */
    Date getMaxBirth(Connection conn);
}