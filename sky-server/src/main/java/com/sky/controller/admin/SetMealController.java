package com.sky.controller.admin;


import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetMealService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Api("套餐管理")
@RequestMapping("/admin/setmeal")
public class SetMealController {
    @Autowired
    private SetMealService setMealService;
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public Result<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询{}", setmealPageQueryDTO);
        PageResult pageRequest = setMealService.page(setmealPageQueryDTO);
        return Result.success(pageRequest);

    }
    @PostMapping
    @ApiOperation("新增套餐")
    public Result save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐{}", setmealDTO);
        setMealService.save(setmealDTO);
        return Result.success();
    }
    @PutMapping
    @ApiOperation("修改套餐")
    public Result update(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐{}", setmealDTO);
        setMealService.update(setmealDTO);
        return Result.success();
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询套餐")
    public Result<SetmealDTO> getById(@PathVariable Long id){
        log.info("根据id查询套餐{}", id);
        SetmealDTO setmealDTO = setMealService.getById(id);
        return Result.success(setmealDTO);
    }
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public Result delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐{}", ids);
        setMealService.delete(ids);
        return Result.success();
    }
    @PostMapping("/status/{status}")
    @ApiOperation("起售、停售套餐")
    public Result startOrStop(@PathVariable Integer status, Long id){
        log.info("起售、停售套餐{}", id);
        setMealService.startOrStop(status, id);
        return Result.success();
    }



}
