package io.codehunters.measure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation for measure methods
 *
 * @since 1.0.0
 * @author anresmontoyat@gmail.com
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Measured {
    String name() default "";

    String[] tags() default {};

    boolean recordExceptions() default true;

    boolean longTask() default false;

    /**
     * Expresiones SpEL para extraer atributos dinámicos de parámetros.
     * Formato: {"key1=#param1.prop", "key2=#param2.other"}
     */
    String[] expressions() default {};
}
