package com.gd.data.mapper;

import com.gd.data.entity.TVipFlow;

import java.util.Date;
import java.util.List;

public interface TVipFlowMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_vip_flow
     *
     * @mbggenerated
     */
    int insert(TVipFlow record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_vip_flow
     *
     * @mbggenerated
     */
    int insertSelective(TVipFlow record);

    List<TVipFlow> selectCarPayList(Date date);
}