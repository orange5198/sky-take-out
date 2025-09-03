package com.sky.mapper;

import com.sky.annotation.AutoFill;
import com.sky.entity.ShoppingCart;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShoppingCartMapper {
    /**
     * 根据用户id查询购物车数据
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart>  list(ShoppingCart shoppingCart);
    /**
     * 更新购物车数据
     * @param shoppingCart
     */
    void update(ShoppingCart shoppingCart);

    /**
     *
     * @param shoppingCart
     */
    void insert(ShoppingCart shoppingCart);
    /**
     * 删除购物车数据
     * @param userId
     */
    void deleteByUserId(Long userId);
    /**
     * 批量删除购物车数据
     * @param shoppingCart
     */
    void deleteByIds(ShoppingCart shoppingCart);
}