package com.zr.common.utils;

import com.zr.common.Utils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by Miste on 3/22/2018.
 *
 * 统计并打印函数执行时间
 *
 */
@Component
@Aspect
public class PrintRunTime {

    @Pointcut(value = "@annotation(com.zr.common.annotation.PrintRunTime)")
    private void pointcut() {}

    @Around(value = "pointcut() && @annotation(com.zr.common.annotation.PrintRunTime)")
    public Object around(ProceedingJoinPoint point) {
        Long time, time2;
        Object res;
        time = new Date().getTime();
        try {
            res = point.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return throwable.getMessage();
        }
        time2 = new Date().getTime();
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        System.out.println("[" + Utils.dateToStr(new Date()) + "] " + method + "- has spend " + ((time2 - time) / 1000) + "s");

        return  res;
    }

}
