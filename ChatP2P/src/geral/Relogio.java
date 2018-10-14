package geral;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Relogio {
	
	public String getDate() {
		Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.getDayOfMonth() + "/" + localDate.getMonthValue() + "/" + localDate.getYear() + " - " + date.getHours() + ":" + date.getMinutes();
	}

}
