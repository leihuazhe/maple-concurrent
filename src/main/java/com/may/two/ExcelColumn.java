package com.may.two;

import java.lang.annotation.*;

/**
 * @author maple 2018.09.10 下午9:38
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface ExcelColumn {
    String value();

}
