package com.yr.ioffice.utils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Title: 日期操作类
 * </p>
 * <p>
 * Description: 完成一些对日期的计算工作
 * </p>
 * 
 * @version 1.0
 */
public class DateUtils {
	public static final String YM_FORMAT = "yyyy-MM";
	// 默认显示日期的格式
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";
	// 默认显示日期时间毫秒格式
	public static final String MSEL_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
	// 默认显示简体中文日期的格式
	public static final String ZHCN_DATE_FORMAT = "yyyy年MM月dd日";
	// 默认显示简体中文日期时间的格式
	public static final String ZHCN_TIME_FORMAT = "yyyy年MM月dd日HH时mm分ss秒";
	// 默认显示简体中文日期时间毫秒格式
	public static final String ZHCN_MSEL_FORMAT = "yyyy年MM月dd日HH时mm分ss秒SSS毫秒";
	// 获取日期串格式
	public static final String DATE_STR_FORMAT = "yyyyMMdd";
	// 获取日期时间串格式
	public static final String TIME_STR_FORMAT = "yyyyMMddHHmmss";
	// 获取日期时间毫秒串格式
	public static final String MSEL_STR_FORMAT = "yyyyMMddHHmmssSSS";
	private static DateFormat dateFormat = null;
	private static DateFormat dateTimeFormat = null;
	private static DateFormat zhcnDateFormat = null;
	private static DateFormat zhcnDateTimeFormat = null;

	static {
		dateFormat = new SimpleDateFormat(DATE_FORMAT);
		dateTimeFormat = new SimpleDateFormat(TIMEF_FORMAT);
		zhcnDateFormat = new SimpleDateFormat(ZHCN_DATE_FORMAT);
		zhcnDateTimeFormat = new SimpleDateFormat(ZHCN_TIME_FORMAT);
	}

	/**
	 * 获取今天的日期，格式如：2006-11-09
	 * 
	 * @return String - 返回今天的日期 
	 */
	public static String getToday() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 获取今天的日期，格式自定
	 * 
	 * @param pattern
	 *            - 设定显示格式
	 * @return String - 返回今天的日期
	 */
	public static String getToday(String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}
	
	/**
	 * 得到当前时间的前/后若干天的时间 例如当前时间2006-05-16 间隔天数30天，则返回2006-04-16
	 * 
	 * @param days
	 *            - 间隔天数
	 * @return String - 返回当时的时间
	 */
	public static String getInternalTimeByDay(int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.DATE, days);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前时间的前/后若干天的时间 例如当前时间2006-05-16 间隔天数30天，则返回2006-04-16
	 * 
	 * @param days
	 *            - 间隔天数
	 * @param pattern
	 *            - 设定显示格式
	 * @return String - 根据显示格式返回当时的时间
	 */
	public static String getInternalTimeByDay(int days, String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.DATE, days);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前时间的前/后若干月的时间 例如当前时间2006-05-16 间隔月数3月，则返回2006-02-16
	 * 
	 * @param months
	 * @return - 返回当时的时间
	 */
	public static String getInternalTimeByMonth(int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.MONTH, months);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前时间的前/后若干月的时间 例如当前时间2006-05-16 间隔月数3月，则返回2006-02-16
	 * 
	 * @param months
	 *            - 间隔月数
	 * @param pattern
	 *            - 设定显示格式
	 * @return - 根据显示格式返回当时的时间
	 */
	public static String getInternalTimeByMonth(int months, String pattern) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.MONTH, months);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到中文日期
	 * 
	 * @param dateStr
	 *            - 日期串，格式为“yyyy-MM-dd”
	 * @return String - 返回中文日期，格式为“yyyy年MM月dd日”
	 */
	public static String chinaDate(String dateStr) {
		if (dateStr == null || dateStr.equals("null") || dateStr.equals("")) {
			return "";
		}
		Date d = getDate(dateStr, DATE_FORMAT);
		SimpleDateFormat sdf = new SimpleDateFormat(ZHCN_DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(d));
	}

	/**
	 * 得到中文日期,自定设置格式
	 * 
	 * @param dateStr
	 *            - 需要改变格式的时间串
	 * @param inPattern
	 *            - 时间串的格式
	 * @param outPattern
	 *            - 改为时间串的格式
	 * @return String - 根据outPattern格式返回时间
	 */
	public static String alterDateByDynamic(String dateStr, String inPattern, String outPattern) {
		if (dateStr == null || dateStr.equals("null") || dateStr.equals("")) {
			return "";
		}
		Date d = getDate(dateStr, inPattern);
		SimpleDateFormat sdf = new SimpleDateFormat(outPattern);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(d));
	}

	/**
	 * 比较当前日期和指定日期 return boolean 如果当前日期在指定日期之后返回true否则返回flase
	 * 
	 * @param dateStr
	 *            指定日期
	 * @param pattern
	 *            指定日期的格式
	 * @return boolean
	 */
	public static boolean dateCompare(String dateStr, String pattern) {
		boolean bea = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		String isDate = dateFormat.format(new java.util.Date());
		Date date1;
		Date date0;
		try {
			date1 = dateFormat.parse(dateStr);
			date0 = dateFormat.parse(isDate);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}

	/**
	 * 比较指定两日期,如果dateStr早于dateStr2则return true;
	 * 
	 * @param dateStr1
	 *            指定日期
	 * @param dateStr2
	 *            指定日期
	 * @param pattern
	 *            指定日期的格式
	 * @return boolean
	 */
	public static boolean dateCompare(String dateStr1, String dateStr2, String pattern) {
		boolean bea = false;
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		Date date1;
		Date date0;
		try {
			date1 = df.parse(dateStr1);
			date0 = df.parse(dateStr2);
			if (date0.after(date1)) {
				bea = true;
			}
		} catch (ParseException e) {
			bea = false;
		}
		return bea;
	}
	
	/**
	 * 比较指定两日期,如果date1晚于date2则return true;
	 * 
	 * @param dateStr1
	 *            指定日期
	 * @param dateStr2
	 *            指定日期
	 * @param pattern
	 *            指定日期的格式
	 * @return boolean
	 */   
	public static boolean dateCompare(Date date1, Date date2) {
		boolean bea = false;
		try {
			if (date1.after(date2)) {
				bea = true;
			}
		} catch (Exception e) {
			bea = false;
		}
		return bea;
	}


	/**
	 * 设置间隔数后返回时间
	 * 
	 * @param type
	 *            间隔类型 秒或者天 秒的类型为s,天的类型为d
	 * @param 间隔数字
	 *            比如1秒或者一天
	 * @return String 返回时间格式为“yyyy-MM-dd HH:mm:ss”
	 */
	public static String dateAdd(String type, int i) {
		SimpleDateFormat df = new SimpleDateFormat(TIMEF_FORMAT);
		String str = getToday(TIMEF_FORMAT);
		Calendar c = Calendar.getInstance(); // 当时的日期和时间
		if (type.equals("s")) {
			int s = c.get(Calendar.SECOND);
			s = s + i;
			c.set(Calendar.SECOND, s);
			str = df.format(c.getTime());
		} else if (type.equals("d")) {
			int d = c.get(Calendar.DAY_OF_MONTH); // 取出“日”数
			d = d + i;
			c.set(Calendar.DAY_OF_MONTH, d); // 将“日”数设置回去
			str = df.format(c.getTime());
		}
		return str;
	}

	/**
	 * 得到当前日期，如"2001-03-16".
	 *
	 * @version 1.0
	 * @author wanghaibo.
	 */
	public static String curDate() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String format = "yyyy-MM-dd";
		// String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		// String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前详细日期、时间，如"2001-03-16 20:34:20".
	 *
	 */
	public static String curTime() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		// String DATE_FORMAT = "yyyy-MM-dd";
		String format = "yyyy-MM-dd HH:mm:ss";
		// String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前详细日期、时间，如"2001-03-16 20:34:20".
	 *
	 * @version 1.0
	 */
	public static String getTimeAfter(int n) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MINUTE, n);
		// String DATE_FORMAT = "yyyy-MM-dd";
		// String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		// String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(TIMEF_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前时间的前/后若干天的时间
	 * 
	 * @param day
	 *            - 间隔时间
	 * @return - 返回当时的时间 例如当前时间2003-05-16 间隔天数30天，则返回2003-04-16
	 */
	public static String getInternalTime(int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		// String DATE_FORMAT = "yyyy-MM-dd";
		// String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		// String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(TIMEF_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		now.add(Calendar.DATE, days);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到当前时间的前/后若干天的时间
	 * 
	 * @param currentTime
	 *            - 当前时间
	 * @param iHour
	 *            - 间隔时间
	 * @return - 返回当时的时间 例如当前时间2003-05-16 08:10:10 间隔时间3小时，则返回2003-05-16
	 *         05:10:10
	 */
	public static String getTimeOut(String currentTime, int iHour) {
		String time = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(TIMEF_FORMAT);
			java.util.Date result = sdf.parse(currentTime);
			Calendar cal = Calendar.getInstance();
			cal.setTime(result);
			cal.add(Calendar.HOUR, iHour);
			time = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return time;
	}

	/**
	 * 得到当前月底的前/后若干天的时间
	 * 
	 * @param day
	 *            - 间隔时间
	 * @return - 返回当时的时间
	 */
	public static String getInternalTimeByLastDay(int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		// String DATE_FORMAT = "yyyy-MM-dd";
		// String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		// String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getDefault());
		int maxDay = now.getActualMaximum(Calendar.DAY_OF_MONTH);
		now.set(Calendar.DATE, maxDay);
		now.add(Calendar.DATE, days);
		return (sdf.format(now.getTime()));
	}

	/**
	 * 得到时间串
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param fmt
	 *            String 时间格式
	 * @return String 返回值
	 */
	public static String getDateStr(String dateStr, String fmt) {
		try {
			if (dateStr == null || dateStr.equals("")) {
				return "";
			}
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			// java.text.DateFormat df =
			// java.text.DateFormat.getDateTimeInstance();
			Date d = sdf.parse(dateStr);
			String newDate = sdf.format(d);
			return newDate;
		} catch (Exception e) {
			e.printStackTrace();
			// log.debug("\n" + e.getMessage());
		}
		return "";
	}

	/**
	 * 得到时间串
	 * 
	 * @param dateStr
	 *            String 时间字符串
	 * @param fmt
	 *            String 时间格式
	 * @return String 返回值
	 */
	public static Date getDate(String dateStr) {
		try {
			if (dateStr == null || dateStr.equals("")) {
				return null;
			}
			// Calendar now = Calendar.getInstance(TimeZone.getDefault());
			// String DATE_FORMAT = "yyyyMMddHHmmss";
			// java.text.SimpleDateFormat sdf = new
			// java.text.SimpleDateFormat(DATE_FORMAT);
			SimpleDateFormat sdf = new SimpleDateFormat(TIME_STR_FORMAT);
			// sdf.setTimeZone(TimeZone.getDefault());
			Date d = sdf.parse(dateStr);
			// java.text.DateFormat df =
			// java.text.DateFormat.getDateTimeInstance();
			// java.util.Date d= df.parse(dateStr);
			return d;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 *
	 * @return 得到当前时间目录例如 030524
	 */
	public static String getCurrTimeDir() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String format = "yyMMdd";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 *
	 * @return 得到上个月月份 如200505
	 */
	public static String getYesterM() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MONTH, -2);
		String format = "yyyyMM";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 *
	 * @return 得到本年度年份 如2005
	 */
	public static String getYear() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		// now.add(Calendar.MONTH,-1);
		String format = "yyyy";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 *
	 * @return 得到本月月份 如09
	 */
	public static String getMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MONTH, -1);
		String format = "MM";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}
	
	/**
	 *
	 * @return 得到本月月份 如09
	 */
	public static String getDay() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.add(Calendar.MONTH, -1);
		String format = "DD";
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}
	
	/**
	 * 得到下一个月分，包括年，例如： 2003－1 月份的上一个月份是2002－12
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String[] getBeforeMonth(String year, String month) {
		String[] time = new String[2];
		if (month.equals("12")) {
			time[1] = "1";
			time[0] = String.valueOf(Integer.parseInt(year) + 1);
		} else {
			time[1] = String.valueOf(Integer.parseInt(month) + 1);
			time[0] = year;
		}
		return time;
	}

	/**
	 * 得到上一个月
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return String[] 0为年,1为月 TODO
	 */
	public static String[] beforeMonth(String year, String month) {
		String[] time = new String[2];
		if (month.equals("1")) {
			time[1] = "12";
			time[0] = String.valueOf(Integer.parseInt(year) - 1);
		} else {
			time[1] = String.valueOf(Integer.parseInt(month) - 1);
			time[0] = year;
		}
		return time;
	}

	/**
	 * 得到当前日期，按照页面日期控件格式，如"2001-3-16".
	 * 
	 * @return String TODO
	 */
	public static String curSingleNumDate() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String format = "yyyy-M-d";
		// String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
		// String DATE_FORMAT = "yyyyMMdd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * 取自当前日期后的第n天的日期
	 * 
	 * @param day
	 *            int 之后n天
	 * @return String
	 */
	public static String getDateAfter(int n) {
		String time = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
			Calendar cal = Calendar.getInstance(TimeZone.getDefault());
			cal.add(Calendar.DAY_OF_MONTH, n);
			time = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return time;
	}

	/**
	 * 得到半年前的日期
	 * 
	 * @return String
	 */
	public static String getHalfYearBeforeStr() {
		GregorianCalendar cal = new GregorianCalendar();
		/** @todo 取当前日期 */
		String month = "";
		int tMonth = cal.get(GregorianCalendar.MONTH) + 1;
		if (tMonth < 10) {
			month = "0" + tMonth;
		} else {
			month = "" + tMonth;
		}
		int tDay = cal.get(GregorianCalendar.DATE);
		String day = "";
		if (tDay < 10) {
			day = "0" + tDay;
		} else {
			day = "" + tDay;
		}
		// String endDate = "" + cal.get(GregorianCalendar.YEAR) + month + day;
		/** @todo 取半年前日期 */
		cal.add(GregorianCalendar.MONTH, -6);
		tMonth = cal.get(GregorianCalendar.MONTH) + 1;
		if (tMonth < 10) {
			month = "0" + tMonth;
		} else {
			month = "" + tMonth;
		}
		tDay = cal.get(GregorianCalendar.DATE);
		day = "";
		if (tDay < 10) {
			day = "0" + tDay;
		} else {
			day = "" + tDay;
		}
		String beginDate = "" + cal.get(GregorianCalendar.YEAR) + month + day;
		return beginDate;
	}

	/**
	 * 返回比当前日期晚几分钟的一个yyyy-MM-dd HH:mm:ss的日期串晚的分钟数可由输入参数minute控制
	 * 
	 * @param minute
	 * @return 返回延迟N分钟后的时间串
	 */
	public static String getCurrentNextMinute(int minute) {
		String chargeStartTime = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new java.util.Date());
			cal.add(Calendar.MINUTE, minute);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			chargeStartTime = sdf.format(cal.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chargeStartTime;
	}

	/**
	 * 得到当前分钟
	 * 
	 * @return int
	 */
	public static int getCurMin() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
		int currentTime = Integer.parseInt(simpleDateFormat.format(date));
		return currentTime;
	}

	/**
	 *
	 * @param formatStr
	 * @return
	 */
	private static DateFormat getDateFormat(String formatStr) {
		if (formatStr.equalsIgnoreCase(DATE_FORMAT)) {
			return dateFormat;
		} else if (formatStr.equalsIgnoreCase(TIMEF_FORMAT)) {
			return dateTimeFormat;
		} else if (formatStr.equalsIgnoreCase(ZHCN_DATE_FORMAT)) {
			return zhcnDateFormat;
		} else if (formatStr.equalsIgnoreCase(ZHCN_TIME_FORMAT)) {
			return zhcnDateTimeFormat;
		} else {
			return new SimpleDateFormat(formatStr);
		}
	}

	// public static Date getDate(String dateTimeStr)
	// {
	// return getDate(dateTimeStr,DATATIMEF_STR);
	// }
	/**
	 * 按照默认formatStr的格式，转化dateTimeStr为Date类型 dateTimeStr必须是formatStr的形式
	 * 
	 * @param dateTimeStr
	 * @param formatStr
	 * @return
	 */
	public static Date getDate(String dateTimeStr, String formatStr) {
		try {
			if (dateTimeStr == null || dateTimeStr.equals("")) {
				return null;
			}
			DateFormat sdf = getDateFormat(formatStr);
			Date d = sdf.parse(dateTimeStr);
			return d;
		} catch (ParseException e) {
			e.printStackTrace();
			// throw new LangException(e);
		}
		return null;
	}

	/**
	 * 将Date转换成字符串“yyyy-mm-dd hh:mm:ss”的字符串
	 * 
	 * @param date
	 *            日期
	 * @return String 字符串 TODO
	 */
	public static String dateToDateString(Date date) {
		return dateToDateString(date, TIMEF_FORMAT);
	}

	/**
	 * 将Date转换成formatStr格式的字符串
	 * 
	 * @param date
	 * @param formatStr
	 * @return
	 */
	public static String dateToDateString(Date date, String formatStr) {
		DateFormat df = getDateFormat(formatStr);
		return df.format(date);
	}

	/**
	 * 返回一个yyyy-MM-dd HH:mm:ss 形式的日期时间字符串中的HH:mm:ss
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String getTimeString(String dateTime) {
		return getTimeString(dateTime, TIMEF_FORMAT);
	}

	/**
	 * 返回一个formatStr格式的日期时间字符串中的HH:mm:ss
	 * 
	 * @param dateTime
	 * @param formatStr
	 * @return
	 */
	public static String getTimeString(String dateTime, String formatStr) {
		Date d = getDate(dateTime, formatStr);
		String s = dateToDateString(d);
		return s.substring(TIMEF_FORMAT.indexOf('H'));
	}

	/**
	 * 获取当前日期yyyy-MM-dd的形式
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return dateToDateString(new Date(), DATE_FORMAT);
	}

	/**
	 * 获取当前日期yyyy年MM月dd日的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDate() {
		return dateToDateString(new Date(), ZHCN_DATE_FORMAT);
	}

	/**
	 * 获取当前日期时间yyyy-MM-dd HH:mm:ss的形式
	 * 
	 * @return
	 */
	public static String getCurDateTime() {
		return dateToDateString(new Date(), TIMEF_FORMAT);
	}

	/**
	 * 获取当前日期时间yyyy年MM月dd日HH时mm分ss秒的形式
	 * 
	 * @return
	 */
	public static String getCurZhCNDateTime() {
		return dateToDateString(new Date(), ZHCN_TIME_FORMAT);
	}

	/**
	 * 获取日期d的days天后的一个Date
	 * 
	 * @param d
	 * @param days
	 * @return Date
	 */
	public static Date getInternalDateByDay(Date d, int days) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.DATE, days);
		return now.getTime();
	}

	/**
	 * 获取日期d的months月后的一个Date
	 * 
	 * @param d
	 * @param months
	 * @return Date
	 */
	public static Date getInternalDateByMon(Date d, int months) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MONTH, months);
		return now.getTime();
	}

	/**
	 * 获取日期d的years年后的一个Date
	 * 
	 * @param d
	 * @param years
	 * @return Date
	 */
	public static Date getInternalDateByYear(Date d, int years) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.YEAR, years);
		return now.getTime();
	}

	/**
	 * 获取日期d的sec秒后的一个Date
	 * 
	 * @param d
	 * @param sec
	 * @return Date
	 */
	public static Date getInternalDateBySec(Date d, int sec) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.SECOND, sec);
		return now.getTime();
	}

	/**
	 * 获取日期d的min分后的一个Date
	 * 
	 * @param d
	 * @param min
	 * @return Date
	 */
	public static Date getInternalDateByMin(Date d, int min) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.MINUTE, min);
		return now.getTime();
	}

	/**
	 * 获取日期d的hours小时后的一个Date
	 * 
	 * @param d
	 * @param hours
	 * @return Date
	 */
	public static Date getInternalDateByHour(Date d, int hours) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		now.add(Calendar.HOUR_OF_DAY, hours);
		return now.getTime();
	}

	/**
	 * 根据一个日期字符串，返回日期格式，目前支持4种 如果都不是，则返回null
	 * 
	 * @param DateString
	 * @return 返回日期格式，目前支持4种
	 */
	public static String getFormateStr(String dateString) {
		String patternStr1 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}"; // "yyyy-MM-dd"
		String patternStr2 = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}\\s[0-9]{1,2}:[0-9]{1,2}:[0-9]{1,2}"; // "yyyy-MM-dd
																									// HH:mm:ss";
		String patternStr3 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日"; // "yyyy年MM月dd日"
		String patternStr4 = "[0-9]{4}年[0-9]{1,2}月[0-9]{1,2}日[0-9]{1,2}时[0-9]{1,2}分[0-9]{1,2}秒"; // "yyyy年MM月dd日HH时mm分ss秒"
		Pattern p = Pattern.compile(patternStr1);
		Matcher m = p.matcher(dateString);
		boolean b = m.matches();
		if (b) {
			return DATE_FORMAT;
		}
		p = Pattern.compile(patternStr2);
		m = p.matcher(dateString);
		b = m.matches();
		if (b) {
			return TIMEF_FORMAT;
		}
		p = Pattern.compile(patternStr3);
		m = p.matcher(dateString);
		b = m.matches();
		if (b) {
			return ZHCN_DATE_FORMAT;
		}
		p = Pattern.compile(patternStr4);
		m = p.matcher(dateString);
		b = m.matches();
		if (b) {
			return ZHCN_TIME_FORMAT;
		}
		return null;
	}

	/**
	 * 将一个"yyyy-MM-dd HH:mm:ss"字符串，转换成"yyyy年MM月dd日HH时mm分ss秒"的字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getZhCNDateTime(String dateStr) {
		Date d = getDate(dateStr);
		return dateToDateString(d, ZHCN_TIME_FORMAT);
	}

	/**
	 * 将一个"yyyy-MM-dd"字符串，转换成"yyyy年MM月dd日"的字符串
	 * 
	 * @param dateStr
	 * @return
	 */
	public static String getZhCNDate(String dateStr) {
		Date d = getDate(dateStr, DATE_FORMAT);
		return dateToDateString(d, ZHCN_DATE_FORMAT);
	}

	/**
	 * 将dateStr从fmtFrom转换到fmtTo的格式
	 * 
	 * @param dateStr
	 * @param fmtFrom
	 * @param fmtTo
	 * @return
	 */
	public static String getDateStr(String dateStr, String fmtFrom, String fmtTo) {
		Date d = getDate(dateStr, fmtFrom);
		return dateToDateString(d, fmtTo);
	}

	/**
	 * 将小时数换算成返回以毫秒为单位的时间
	 * 
	 * @param hours
	 * @return
	 */
	public static long getMicroSec(BigDecimal hours) {
		BigDecimal bd;
		bd = hours.multiply(new BigDecimal(3600 * 1000));
		return bd.longValue();
	}

	/**
	 * 获取Date中的分钟
	 * 
	 * @param d
	 * @return
	 */
	public static int getMin(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MINUTE);
	}

	/**
	 * 获取xxxx-xx-xx的日
	 * 
	 * @param d
	 * @return
	 */
	public static int getDay(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取月份，1-12月
	 * 
	 * @param d
	 * @return
	 */
	public static int getMonth(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.MONTH) + 1;
	}

	/**
	 * 获取19xx,20xx形式的年
	 * 
	 * @param d
	 * @return
	 */
	public static int getYear(Date d) {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		now.setTime(d);
		return now.get(Calendar.YEAR);
	}

	/**
	 * 得到d的上个月的年份+月份,如200505
	 * 
	 * @return
	 */
	public static String getYearMonthOfLastMon(Date d) {
		Date newdate = getInternalDateByMon(d, -1);
		String year = String.valueOf(getYear(newdate));
		String month = String.valueOf(getMonth(newdate));
		return year + month;
	}

	/**
	 * 得到当前日期的年和月如200509
	 * 
	 * @return String
	 */
	public static String getCurYearMonth() {
		Calendar now = Calendar.getInstance(TimeZone.getDefault());
		String pattern = "yyyyMM";
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		sdf.setTimeZone(TimeZone.getDefault());
		return (sdf.format(now.getTime()));
	}

	/**
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getNextMonth(String year, String month) {
		String datestr = year + "-" + month + "-01";
		Date date = getDate(datestr, DATE_FORMAT);
		return getInternalDateByMon(date, 1);
	}

	/**
	 * @param year
	 * @param month
	 * @return
	 */
	public static Date getLastMonth(String year, String month) {
		String datestr = year + "-" + month + "-01";
		Date date = getDate(datestr, DATE_FORMAT);
		return getInternalDateByMon(date, -1);
	}

	/**
	 * 得到日期d，按照页面日期控件格式，如"2001-3-16"
	 * 
	 * @param d
	 * @return
	 */
	public static String getSingleNumDate(Date d) {
		return dateToDateString(d, DATE_FORMAT);
	}

	/**
	 * 得到d半年前的日期,"yyyy-MM-dd"
	 * 
	 * @param d
	 * @return
	 */
	public static String getHalfYearBeforeStr(Date d) {
		return dateToDateString(getInternalDateByMon(d, -6), DATE_FORMAT);
	}

	/**
	 * 得到当前日期D的月底的前/后若干天的时间,<0表示之前，>0表示之后
	 * 
	 * @param d
	 * @param days
	 * @return
	 */
	public static String getInternalDateByLastDay(Date d, int days) {
		return dateToDateString(getInternalDateByDay(d, days), DATE_FORMAT);
	}

	public static java.sql.Date getSqlDate(String dateTimeStr) {
		// DateUtils.getTIME_STR_FORMAT
		java.sql.Date d = new java.sql.Date(DateUtils.getDate(dateTimeStr, DateUtils.TIME_STR_FORMAT).getTime());
		// d.setHours(Integer.parseInt(dateTimeStr.substring(8,10)));
		// d.setMinutes(Integer.parseInt(dateTimeStr.substring(10,12)));
		// d.setSeconds(Integer.parseInt(dateTimeStr.substring(12,14)));
		return d;
	}

	/**
	 * 补充日期的时间 (00:00:00)
	 * 
	 * @param dateStr
	 *            日期格式字符串 格式: yyyy-MM-dd
	 * @return 时间格式字符串 格式 yyyy-MM-dd hh:mm:ss
	 * @throws Exception
	 */
	public static String getFullTime(String dateStr) throws Exception {
		if (dateStr.indexOf("-") == -1) {
			throw new Exception("日期格式异常.要求yyyy-MM-dd");
		}
		String str = dateStr + " 00:00:00";
		return str;
	}

	/**
	 * 根据String类型时间得到Timestamp
	 * 
	 * @param dateTime
	 *            String类型时间 格式:yyyy-MM-dd
	 * @return Timestamp
	 * @throws Exception
	 */
	public static Timestamp getTimestampByDate(String dateTime) throws Exception {
		Timestamp s = Timestamp.valueOf(getFullTime(dateTime));
		return s;
	}

	/**
	 * 根据String类型时间得到Timestamp
	 * 
	 * @param dateTime
	 *            String类型时间 格式:yyyy-MM-dd hh:mm:ss
	 * @return Timestamp
	 * @throws Exception
	 */
	public static Timestamp getTimestamp(String dateTime) throws Exception {
		Timestamp s = Timestamp.valueOf(dateTime);
		return s;
	}

	/**
	 * Timestamp转String
	 * @param ts Timestamp格式日期
	 * @return yyyy-MM-dd HH:mm:ss 格式的时间字符串
	 */
	public static String getStringDatetime(Timestamp ts) {
		DateFormat sdf = new SimpleDateFormat(TIMEF_FORMAT);
		String tsStr = sdf.format(ts);
		return tsStr;
	}
	
	/**
	 * Timestamp转String,指定格式
	 * @param ts Timestamp格式日期
	 * @param pattern 时间格式
	 * @return yyyy-MM-dd HH:mm:ss 格式的时间字符串
	 */
	public static String getStringDatetime(Timestamp ts,String pattern) {
		DateFormat sdf = new SimpleDateFormat(pattern);
		String tsStr = sdf.format(ts);
		return tsStr;
	}
	
	/**
	 * 获得某年的某月总天数
	 * format 时间格式
	 * @param source xx年xx月
	 * @return
	 */
	 public static Integer getManyDaysAMonth(String source){
		  SimpleDateFormat format = new SimpleDateFormat("yyyy:MM");
		  try {
		      Date date = format.parse(source);
		      Calendar calendar = new GregorianCalendar();
		      calendar.setTime(date);
		      calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		      return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		    } catch (ParseException e) {
		      e.printStackTrace();
		    }
		  return 0;
	  }
}
