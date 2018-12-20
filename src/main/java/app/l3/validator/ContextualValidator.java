package app.l3.validator;

import java.lang.annotation.Annotation;

public interface ContextualValidator<A extends Annotation,V> extends Validator<V>{
    void processContext(A value);
}
