package org.javamentor.social.login.demo.aspects;

import java.lang.annotation.*;


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckExist {
    Class object();
}
