package com.cncounter.mysqldemo.dao.mapper;

import com.cncounter.mysqldemo.model.TBizOrder;

public interface TBizOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_biz_order
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_biz_order
     *
     * @mbg.generated
     */
    int insert(TBizOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_biz_order
     *
     * @mbg.generated
     */
    int insertSelective(TBizOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_biz_order
     *
     * @mbg.generated
     */
    TBizOrder selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_biz_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(TBizOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_biz_order
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(TBizOrder record);
}