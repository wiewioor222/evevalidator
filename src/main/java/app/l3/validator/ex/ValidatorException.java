package app.l3.validator.ex;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

@AllArgsConstructor
@Getter
public class ValidatorException extends Exception {
	private List<FieldDtoException> exceptions;

	public static void throwIfNotNull(List<FieldDtoException> exceptions) throws ValidatorException {
		if(CollectionUtils.isNotEmpty(exceptions)){
			throw new ValidatorException(exceptions);
		}
	}
}

