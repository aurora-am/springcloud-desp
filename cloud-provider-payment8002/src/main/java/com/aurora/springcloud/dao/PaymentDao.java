package com.aurora.springcloud.dao;

import com.aurora.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper     //ibatis下的注解
public interface PaymentDao {   //Dao 跟数据库打交道
    public int create(Payment payment);    //写操作

    public  Payment getPaymentById(@Param("id") Long id);   //读操作
    // 加上@Param注解，mapper中就可以采用#{}的方式把@Param注解括号内的参数进行引用

    //这里用读和写进行演示，有兴趣的可以自己加其他的方法
}