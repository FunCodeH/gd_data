package com.gd.data.mapper;

import com.gd.data.entity.TDataPayment;

import java.util.List;

public interface TDataPaymentMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_payment
     *
     * @mbggenerated
     */
    int insert(TDataPayment record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_data_payment
     *
     * @mbggenerated
     */
    int insertSelective(TDataPayment record);

    List<TDataPayment> selectPayments();
}