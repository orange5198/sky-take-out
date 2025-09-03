package com.sky.mapper;


import com.sky.annotation.AutoFill;
import com.sky.entity.SetmealDish;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据菜品id查询菜品数量
     * @param dishIds
     * @return
     */
    List<Long> countByDishIds(List<Long> dishIds);
    /**
     * 批量插入套餐和菜品的关联关系
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
    /**
     * 根据套餐id查询套餐和菜品的关联数据
     *
     * @param id
     * @return
     * */

    List<SetmealDish> getBySetmealId(Long id);

    /**
     * 根据套餐id删除套餐和菜品的关联数据
     * @param setmealId
     */
    void deleteBySetmealId(Long setmealId);
}