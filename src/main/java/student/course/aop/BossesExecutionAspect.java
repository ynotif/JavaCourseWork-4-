package student.course.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class BossesExecutionAspect {

    @Pointcut("execution(public * student.course.controller.BossesController.getAllBosses(..))")
    public void callGetAllBosses() {
    }

    @Around("callGetAllBosses()")
    public Object callGetAllBossesAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        log.info("User {}. Execution {} time: {} ms",
                SecurityContextHolder.getContext().getAuthentication().getName(),
                proceedingJoinPoint.getSignature().getName(),
                endTime - startTime); // Странно, в конец если добавить авторизацию, то ошибка
        return result;
    }
}
