package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;
import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);


    /**
    * 插入菜品数据
    * @param dish 菜品数据
    * @return Dish
    */
    @AutoFill(value = OperationType.INSERT)
    Integer Insert(Dish dish);

    /**
    * 菜品分页查询
    * @param dishPageQueryDTO 分页参数
    * @return Page<DishVO>
    */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);
    /**
    * 根据id查询菜品和对应的口味数据
    * @param id
    * @return
    */
    Dish getById(Long id);
    /**
    * 删除菜品
    * @param id
    */
    void deleteById(Long id);
    /**
    * 批量删除菜品
    * @param ids
    */
    void deleteByIds(List<Long> ids);
    /**
    * 修改菜品
    * @param dish
    */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);
    /**
    * 根据分类id查询菜品
    * @param categoryId
    * @return
    */
    List<DishVO> listByCategoryId(Integer categoryId);
    /**
    * 根据套餐id查询菜品选项
    * @param id 套餐id
    * @return
    */
    @Select("select a.* from dish a left join setmeal_dish b on a.id = b.dish_id where b.setmeal_id = #{setmealId}")
    List<Dish> getBySetmealId(Long id);
    /**
    * 根据条件查询菜品和口味
    * @param dish 查询条件
    * @return
    */
    List<Dish> list(Dish dish);
}
