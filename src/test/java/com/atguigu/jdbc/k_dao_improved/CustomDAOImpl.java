package com.atguigu.jdbc.k_dao_improved;

import com.atguigu.jdbc.e_bean.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * ClassName: CustomDaoImpl
 * Package: com.atguigu.jdbc.k_dao_improved
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 19. 오전 10:10
 * @Version 1.0
 */
public class CustomDAOImpl extends BaseDAO<Customer> implements CustomerDAO {
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql = "insert into customers(name, email, birth) values(?, ?, ?)";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth());
    }

    @Override
    public void deleteById(Connection conn, int id) {
        String sql = "delete from customers where id = ?";
        update(conn, sql, id);
    }

    @Override
    public void updateById(Connection conn, Customer cust) {
        String sql = "update customers set name = ?, email = ?, birth = ? where id = ?";
        update(conn, sql, cust.getName(), cust.getEmail(), cust.getBirth(), cust.getId());
    }

    @Override
    public Customer getCustomerById(Connection conn, int id) {
        String sql = "select id, name, email, birth from customers where id = ?";
        Customer customer = getInstance(conn,  sql, id);
        return customer;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql = "select id, name, birth from customers";
        List<Customer> list = getForList(conn,  sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql = "select count(*) from customers";
        return getValue(conn, sql);
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql = "select max(birth) from customers";
        return getValue(conn, sql);
    }
}
