package app.l3.table;

import app.commons.AbstractNameEntity;
import app.l3.validator.MinOnce;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Table extends AbstractNameEntity{
	private Date orderDate;
	@MinOnce(value = "Andrzej Kozlowski", field = "name")
	private List<Guest> guests = Lists.newArrayList();
}
