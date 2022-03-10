package jm_social_project.profile_service.config_annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Component
@Aspect
public class AuthPrincipalAspect {

    private final AuthPrincipalFilter authPrincipalFilter;

    @Autowired
    public AuthPrincipalAspect(AuthPrincipalFilter authPrincipalFilter) {
        this.authPrincipalFilter = authPrincipalFilter;
    }

    //В execution должен быть путь до пакета или класса, в котором
    //будет поиск аннотации. Для каждого микросервиса будет свой, соответственно
    @Around("execution(* jm_social_project.profile_service.controller.*.*(..))")
    public Object idTransfer(ProceedingJoinPoint joinPoint) throws Throwable {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        Object[] args = joinPoint.getArgs();
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (Annotation[] annotation : parameterAnnotations) {
            for (Annotation annotation1 : annotation) {
                if (annotation1 instanceof AuthPrincipal) {
                    for (int i = 0; i < args.length; i++) {
                        if (args[i] == null) {
                            args[i] = authPrincipalFilter.getAuthHeader();
                        }
                    }
                }
            }
        }
        return joinPoint.proceed(args);
    }
}

