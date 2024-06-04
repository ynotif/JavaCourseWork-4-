package student.course.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
@Aspect
public class ControllerLogAspect {

    @Pointcut("execution(public * student.course.controller..*(..))") // Когда вызываем и где
    public void callControllerLog() {
    }

    @Before("callControllerLog()")
    public void beforeControllerLog(JoinPoint joinPoint) {
        List<String> args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .toList();

        log.info("Call {} with args: {}", joinPoint.getSignature().getName(), args);
    }

    @AfterReturning(value = "callControllerLog()", returning = "object")
    public void afterControllerLog(JoinPoint joinPoint, ResponseEntity<?> object) {
        log.info("Call {} with object: {}", joinPoint.getSignature().getName(), object.getBody());
    }
}
