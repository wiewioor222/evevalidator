package app.l3;

import app.l3.validator.ReflectValidator;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

@Slf4j
public class ChristmasEve {

	@SneakyThrows
	public static void init(Object obj) {
		process(obj);
		ReflectValidator.valid(obj);
	}

	private static void process(Object obj) throws IllegalAccessException {
		Class<?> clazz = obj.getClass();

		Field[] declaredFields = clazz.getDeclaredFields();

		for (Field declaredField : declaredFields) {
			declaredField.setAccessible(true);

			Object value = declaredField.get(obj);

			Optional<Object> optValue = Optional.ofNullable(value);
			if (optValue.isPresent()){
				Object currentValue = optValue.get();
				String simpleName = declaredField.getType().getSimpleName();
				if(currentValue instanceof Collection){
					for (Object o : (Collection)currentValue) {
						log.debug("-----LIST------");
						process(o);
					}
				} else {
					log.debug("Field name: {}, type: {}, value {}",declaredField.getName(),simpleName,currentValue);
				}
			}
		}
	}
}
