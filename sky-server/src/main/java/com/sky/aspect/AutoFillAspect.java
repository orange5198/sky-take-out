package com.sky.aspect;

import com.sky.annotation.AutoFill;
import com.sky.constant.AutoFillConstant;
import com.sky.context.BaseContext;
import com.sky.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面类
 * 实现公共字段自动填充
 */
@Aspect
@Component
@Slf4j
public class AutoFillAspect {
    @Pointcut("execution(* com.sky.mapper.*.*(..)) && @annotation(com.sky.annotation.AutoFill) ")
    public void autoFillPointCut(){
    }
    /**
     * 逻辑：在通知方法中获取参数，根据对应的参数创建时间进行填充
     * @param joinPoint
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {
        log.info("开始进行公共字段填充");
        //获取当前方法数据库操作类型
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);
        OperationType operationType = autoFill.value();//获得操作数据库操作类型
        //获取当前被拦截的方法参数--实体对象
        Object[] args = joinPoint.getArgs();
        if(args == null || args.length == 0){
            return;
        }
        Object entity = args[0];
        //准备赋值数据
        LocalDateTime now = LocalDateTime.now();
        Long empId = BaseContext.getCurrentId();
        //根据当前不同的操作类型，为对应的属性赋值
        if(operationType == OperationType.INSERT){
            //为四个公共字段赋值
            Method setCreateTime = null;
            try {
                setCreateTime = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                Method setCreateUser = entity.getClass().getMethod(AutoFillConstant.SET_CREATE_USER,Long.class);
                Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);
                setCreateTime.invoke(entity,now);
                setUpdateTime.invoke(entity,now);
                setCreateUser.invoke(entity,BaseContext.getCurrentId());
                setUpdateUser.invoke(entity,BaseContext.getCurrentId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else if(operationType == OperationType.UPDATE){
            //为update_time和update_user赋值
            try {
                Method setUpdateTime = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_TIME,LocalDateTime.class);
                setUpdateTime.invoke(entity,LocalDateTime.now());
                Method setUpdateUser = entity.getClass().getMethod(AutoFillConstant.SET_UPDATE_USER,Long.class);
                setUpdateUser.invoke(entity,BaseContext.getCurrentId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }


    }

}
