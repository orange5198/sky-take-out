package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface SetMealService {
    PageResult page(SetmealPageQueryDTO setmealPageQueryDTO);

    void save(SetmealDTO setmealDTO);

    void update(SetmealDTO setmealDTO);

    SetmealDTO getById(Long id);

    void delete(List<Long> ids);

    void startOrStop(Integer status, Long id);
}
