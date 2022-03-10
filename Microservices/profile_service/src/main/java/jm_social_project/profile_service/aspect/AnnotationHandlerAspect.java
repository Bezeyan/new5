package jm_social_project.profile_service.aspect;

import jm_social_project.profile_service.annotation.CheckExists;
import jm_social_project.profile_service.exception.NoSuchException;
import jm_social_project.profile_service.model.Profile;
import jm_social_project.profile_service.repository.ProfileRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Parameter;
import java.util.Optional;

@Aspect
@Component
public class AnnotationHandlerAspect {

    @Autowired
    private ProfileRepository profileRepository;

    @Before("execution(public * jm_social_project.profile_service.service..*.*(..))")
    public void checkExists2(JoinPoint joinPoint) throws NoSuchException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Parameter[] parameters = signature.getMethod().getParameters();
        for (int i = 0; i < parameters.length; i++) {
            CheckExists annotation = parameters[i].getAnnotation(CheckExists.class);
            if (annotation != null && joinPoint.getArgs()[i].getClass().equals(String.class)) {
                Optional<Profile> byId = profileRepository.findById((String) joinPoint.getArgs()[i]);
                if (byId.isEmpty()) {
                    throw new NoSuchException("Профиль с id: " + joinPoint.getArgs()[i] + " не найден");
                }
            }
        }
    }
}
