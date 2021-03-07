package com.adkun.seckill.dao;

import com.adkun.seckill.entity.Promotion;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PromotionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promotion
     *
     * @mbg.generated Sun Mar 07 22:26:42 CST 2021
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promotion
     *
     * @mbg.generated Sun Mar 07 22:26:42 CST 2021
     */
    int insert(Promotion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promotion
     *
     * @mbg.generated Sun Mar 07 22:26:42 CST 2021
     */
    Promotion selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promotion
     *
     * @mbg.generated Sun Mar 07 22:26:42 CST 2021
     */
    List<Promotion> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table promotion
     *
     * @mbg.generated Sun Mar 07 22:26:42 CST 2021
     */
    int updateByPrimaryKey(Promotion record);
}