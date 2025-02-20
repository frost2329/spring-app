package by.frost.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Slf4j
public class FirstAspect {
    @Pointcut("execution(public * by.frost.service.*Service.findById(*))")
    public void anyServiceFindByIdMethod() {
    }

    @Around(value = "anyServiceFindByIdMethod() && target(service) && args(id)", argNames = "joinPoint,service,id")
    public Object addLoggingAround(ProceedingJoinPoint joinPoint, Object service, Object id) throws Throwable {
        log.info("AROUND before - invoked method in class {}, with id {}", service, id);
        try {
            Object result = joinPoint.proceed();
            log.info("AROUND after returning - invoked method in class {}, with result {}", service, result);
            return result;
        } catch (Throwable ex) {
            log.info("AROUND after trowing - invoked method in class {}, with ex {}", service, ex.getClass());
            throw ex;
        } finally {
            log.info("AROUND after (finally) - invoked method in class {}, ", service);
        }
    }
}
