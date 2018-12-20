package app.l3;

import app.l3.table.Guest;
import app.l3.table.Table;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

@Slf4j
public class Main {
	public static void main(String[] args) {
		Table table = getTable();
		ChristmasEve.init(table);
	}

	private static Table getTable() {
		Table table = new Table();
		table.setOrderDate(new Date());
		table.setGuests(getGuests());

		return table;
	}

	private static List<Guest> getGuests() {
		List<Guest> items = Lists.newArrayList();

		for (int i = 0; i < 10; i++) {
			items.add(Guest.builder().name("Guest " + i).build());
		}

		return items;
	}
}
