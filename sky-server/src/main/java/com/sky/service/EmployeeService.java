package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return  employee
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);
    /**
     * 新增员工
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);
    /**
     * 启用禁用员工账号
     * @param employeePageQueryDTO  员工分页查询
     * @return  PageResult
     */
    PageResult page(EmployeePageQueryDTO employeePageQueryDTO);
    /**
     * 编辑员工信息
     * @param status 状态
     * @param id 员工id
     */
    void startOrStop(Integer status, Long id);
    /**
     * 根据id查询员工信息
     * @param employeeDTO 员工查询信息
     * @return
     */
    void update(EmployeeDTO employeeDTO);
    /**
     * 根据id查询员工信息
     * @param id 员工id
     * @return 员工信息
     * */
    Employee getById(Long id);
}
