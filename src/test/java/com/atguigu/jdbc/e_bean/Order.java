package com.atguigu.jdbc.e_bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

/**
 * ClassName: Order
 * Package: com.atguigu.jdbc.e_bean
 * Description:
 *
 * @Author: ljy
 * @Create: 2025. 5. 15. 오후 10:14
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int orderId;
    private String orderName;
    private Date orderDate;
}
