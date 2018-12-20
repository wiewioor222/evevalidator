package app.l3.validator;

import app.l3.validator.impl.MinOnceImpl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(impl = MinOnceImpl.class)
public @interface MinOnce {
    String value();
    String field();
}
