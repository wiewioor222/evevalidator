package app.l3.validator.ex;

import lombok.Builder;
import lombok.ToString;

@Builder
@ToString
public class FieldDtoException {
	private String fieldName;
	private String cause;
}
