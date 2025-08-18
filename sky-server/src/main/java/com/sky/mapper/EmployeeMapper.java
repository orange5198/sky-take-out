package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
    /**
     * 根据用户名查询员工
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);
    /**
     * 添加新员工
     * @param employee
     */
    @AutoFill(value = OperationType.INSERT)
    void addNewEmployee(Employee employee);
    /**
     * 分页查询员工数据
     * @param employeePageQueryDTO
     * @return
     */
    Page<Employee> page(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 更新员工
     * @param employee  员工信息
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Employee employee);
    /**
     * 根据员工id查询员工信息
     * @param id
     * @return
     */
    Employee getById(Long id);
}
