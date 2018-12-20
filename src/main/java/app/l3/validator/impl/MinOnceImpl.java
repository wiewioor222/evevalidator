package app.l3.validator.impl;

import app.l3.validator.ContextualValidator;
import app.l3.validator.MinOnce;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

import static app.commons.UtilException.uncheck;

public class MinOnceImpl implements ContextualValidator<MinOnce,List<?>> {
    private String value;
    private String field;

    @Override
    public void valid(final List<?> list) {
        if(CollectionUtils.isNotEmpty(list)) {
            list.stream().map(m -> uncheck(() -> PropertyUtils.getProperty(m, "name"))).filter(f -> value.equals(f)).findAny()
                  .orElseThrow(() -> new IllegalArgumentException(String.format("No %s found", value)));
        }
    }

    @Override
    public void processContext(MinOnce value) {
        this.value = value.value();
    }
}
