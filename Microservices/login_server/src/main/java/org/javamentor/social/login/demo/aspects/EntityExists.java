package org.javamentor.social.login.demo.aspects;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.javamentor.social.login.demo.exceptions.NoSuchUserException;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Field;


@Component
@Aspect
public class EntityExists {

    @PersistenceContext
    private EntityManager entityManager;


    @Pointcut(value = "within(org.javamentor..*) && execution(public * org.javamentor..*.*(..))")
    public void publicMethodsInAppForDto() {
    }

    /**
     *
     * @param proceedingJoinPoint - параметр для считывания сущности, точки входа в метод
     * @param checkExist - параметр для считывания поля object(), объявленный над классом в @CheckExist
     * @throws Throwable
     */

    @Around("(publicMethodsInAppForDto() && @args(checkExist))")
    public Object existIdDtoInTheDatabase(ProceedingJoinPoint proceedingJoinPoint, CheckExist checkExist) throws Throwable {
        Object[] invObj = proceedingJoinPoint.getArgs();
        for (Object obj : invObj) {
            Field idField = obj.getClass().getDeclaredField("id"); // в классе ищем поле id
            idField.setAccessible(true);                                 //отмена проверки доступа
            Long idDto = (Long) idField.get(obj);
            if (entityManager.find(checkExist.object(), idDto) == null) {
                throw new NoSuchUserException("ID: " + idDto + " отсутствует в базе!");
            }
        }
        return proceedingJoinPoint.proceed();
    }
}




