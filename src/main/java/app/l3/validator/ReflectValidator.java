package app.l3.validator;

import app.l3.validator.ex.FieldDtoException;
import app.l3.validator.ex.ValidatorException;
import com.google.common.collect.Lists;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.ArrayUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ReflectValidator {

	public static void valid(Object obj) throws ValidatorException {
		List<FieldDtoException> exceptions = Lists.newArrayList();
		process(obj, exceptions);
		ValidatorException.throwIfNotNull(exceptions);
	}

	@SneakyThrows
	private static void process(Object obj, List<FieldDtoException> exceptions) {
		Class<?> clazz = obj.getClass();

		Field[] declaredFields = clazz.getDeclaredFields();

		log.debug("Class name: {}", clazz.getSimpleName());
		for (Field declaredField : declaredFields) {
			declaredField.setAccessible(true);

			Object value = declaredField.get(obj);
			processValid(declaredField, value);
			try {
				if (value instanceof Collection) {
					for (Object o : (Collection) value) {
						log.debug("-----LIST------");
						process(o, exceptions);
					}
				}
			} catch (Exception e){
				exceptions.add(FieldDtoException.builder().fieldName(declaredField.getName()).cause(e.getMessage()).build());
			}
		}
	}

	private static void processValid(Field declaredField, Object value) throws InstantiationException, IllegalAccessException, InvocationTargetException {
		List<Annotation> annotations = getAnnotations(declaredField);

		for (Annotation annotation : annotations) {
			Constraint constraint = annotation.annotationType().getAnnotation(Constraint.class);

			Class<? extends Validator> impl = constraint.impl();

			Validator validator = impl.newInstance();

			if(ContextualValidator.class.isAssignableFrom(impl)){
				((ContextualValidator)validator).processContext(annotation);
			}

			validator.valid(value);
		}
	}

	private static List<Annotation> getAnnotations(Field declaredField) {
		Annotation[] annotations = declaredField.getDeclaredAnnotations();

		if(ArrayUtils.isEmpty(annotations)){
			return Lists.newArrayList();
		}

		return Arrays.stream(annotations).filter(f -> f.annotationType().isAnnotationPresent(Constraint.class)).collect(Collectors.toList());
	}
}
