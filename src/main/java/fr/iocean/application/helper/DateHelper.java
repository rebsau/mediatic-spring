package fr.iocean.application.helper;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;


public class DateHelper {

	
	
	public static Date createDate(int year, Month month, int day) {
		LocalDate localDate = LocalDate.of(year, month, day);
		return localDateToDate(localDate);
	}
	
	public static Date plusDays(Date date, int days) {
		return localDateToDate(dateToLocalDate(date).plusDays(days));
	}
	
	public static Date minusDays(Date date, int days) {
		return localDateToDate(dateToLocalDate(date).minusDays(days));
	}
	
	public static Date plusMonths(Date date, int months) {
		return localDateToDate(dateToLocalDate(date).plusMonths(months));
	}
	
	public static Date minusMonths(Date date, int months) {
		return localDateToDate(dateToLocalDate(date).minusMonths(months));
	}
	
	public static Date plusYears(Date date, int years) {
		return localDateToDate(dateToLocalDate(date).plusYears(years));
	}
	
	public static Date minusYears(Date date, int years) {
		return localDateToDate(dateToLocalDate(date).minusYears(years));
	}
	
	
	
	
	
	public static LocalDate dateToLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static Date localDateToDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	
	public static Date now() {
		return localDateToDate(LocalDate.now());
	}
	

}



