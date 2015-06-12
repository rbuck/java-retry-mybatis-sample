package com.github.rbuck.retry.samples.aop;

import com.github.rbuck.retry.RetryPolicy;
import org.apache.ibatis.exceptions.PersistenceException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import java.util.concurrent.Callable;

@Aspect
public class MyBatisRetryAspect {

    private final RetryPolicy<Object> retryPolicy;

    public MyBatisRetryAspect(RetryPolicy<Object> retryPolicy) {
        this.retryPolicy = retryPolicy;
    }

    public Object invoke(final ProceedingJoinPoint pjp) throws Throwable {
        return retryPolicy.action(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                try {
                    return pjp.proceed();
                } catch (Throwable throwable) {
                    if (throwable instanceof PersistenceException) {
                        Exception cause = (Exception) throwable.getCause();
                        throw cause;
                    } else {
                        // unknown issue that is not a SQLException
                        throw new Exception(throwable);
                    }
                }
            }
        });
    }
}
