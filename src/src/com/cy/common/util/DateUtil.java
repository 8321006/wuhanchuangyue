package com.cy.common.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * description : 时间工具类
 * 
 * @ClassName : DateUtil
 * @Package : com.cy.common.util
 * @author : charlex
 * @version : 2015年7月24日 上午10:29:46
 */
public class DateUtil {

	/** yyyy-MM-dd HH:mm:ss */
	public static final String F_DATETIME = "yyyy-MM-dd HH:mm:ss";
	/** yyyy-MM-dd */
	public static final String F_DATE = "yyyy-MM-dd";
	public static final String F_YEAR_MONTH = "yyyy-MM";
	/** yyyyMMdd */
	public static final String F_DATE_YYYYMMDD = "yyyyMMdd";
	public static final String F_DATE_MMDD = "MM-dd";
	/** HH:mm:ss */
	public static final String F_TIME = "HH:mm:ss";
	/** yyyy */
	public static final String F_YEAR = "yyyy";
	/** MM */
	public static final String F_MONTH = "MM";
	/** dd */
	public static final String F_DAY = "dd";
	/** EEEEEEE */
	public static final String F_WEEK = "EEEEEEE";

	public static final String F_DATETIME_1 = "yyyy-MM-ddTHH:mm:ssZ";
	public static final int C_ONE_SECOND = 1000;
	public static final int C_ONE_MINUTE = 60 * C_ONE_SECOND;
	public static final int C_ONE_HOUR = 60 * C_ONE_MINUTE;
	public static final long C_ONE_DAY = 24 * C_ONE_HOUR;

	private DateUtil() { // 构造函数私有，静态工具类，不允许外部创建
	}

	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	/**
	 * 获取现在时间
	 * 
	 * @return 返回短时间字符串格式yyyy-MM-dd
	 */
	public static String getStringDateShort() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * 获取转换后日期字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String getStringDateByDate(Date date) {
		String dateString = "";
		if (date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			dateString = formatter.format(date);
		}
		return dateString;
	}

	/**
	 * 获得距离当天指定天数的日期
	 * 
	 * @param day
	 *            (间隔天数(负数提前,正数滞后))
	 * @return [参数说明]
	 * @see [类、类#方法、类#成员]
	 */
	public static Date getDateFromToday(int day) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) + day);
		return c.getTime();
	}

	/**
	 * 获取年月日
	 */
	public static String getDateByCalendar(Calendar cal) {
		return DATE_FORMAT.format(cal.getTime());
	}

	/**
	 * 获取开始 、结束日期 flag : 0 当天； 1 近一周；2 当月 ； 3 近一月
	 */

	public static Map<String, Object> getDateMap(String flag) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		Calendar ca = Calendar.getInstance();
		// 判断
		if ("0".equals(flag)) {
			ca.setTime(new Date());
			paraMap.put("endDate", ca.getTime());
			ca.set(Calendar.HOUR_OF_DAY, 0);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			paraMap.put("stratDate", ca.getTime());
			paraMap.put("startDateStr", getDateByCalendar(ca));
		} else if ("1".equals(flag)) {
			ca.setTime(new Date());
			paraMap.put("endDate", ca.getTime());
			paraMap.put("endDateStr", getDateByCalendar(ca));
			ca.add(Calendar.DAY_OF_YEAR, -6);
			ca.set(Calendar.HOUR_OF_DAY, 0);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			paraMap.put("stratDate", ca.getTime());
			paraMap.put("stratDateStr", getDateByCalendar(ca));
		} else if ("2".equals(flag)) {
			ca.setTime(new Date());
			paraMap.put("endDate", ca.getTime());
			paraMap.put("endDateStr", getDateByCalendar(ca));
			ca.set(Calendar.DAY_OF_MONTH, 1);
			ca.set(Calendar.HOUR_OF_DAY, 0);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			paraMap.put("stratDate", ca.getTime());
			paraMap.put("stratDateStr", getDateByCalendar(ca));
		} else if ("3".equals(flag)) {
			ca.setTime(new Date());
			paraMap.put("endDate", ca.getTime());
			paraMap.put("endDateStr", getDateByCalendar(ca));
			ca.add(Calendar.MONTH, -1);
			ca.set(Calendar.HOUR_OF_DAY, 24);
			ca.set(Calendar.MINUTE, 0);
			ca.set(Calendar.SECOND, 0);
			paraMap.put("stratDate", ca.getTime());
			paraMap.put("stratDateStr", getDateByCalendar(ca));
		}
		return paraMap;
	}

	/**
	 * 获取当月时间参数
	 * 
	 * @param flag
	 *            true:月初时间 false:月末时间
	 * @return
	 */
	public static Date getNowMonthDate(boolean flag) {
		Calendar rightNow = Calendar.getInstance();
		if (flag) {
			// 当前月的第一天
			rightNow.set(GregorianCalendar.DAY_OF_MONTH, 1);
			rightNow.set(Calendar.HOUR_OF_DAY, 0);
			rightNow.set(Calendar.MINUTE, 0);
			rightNow.set(Calendar.SECOND, 0);
		} else {
			// 当前月的最后一天
			rightNow.set(Calendar.DATE, 1);
			rightNow.roll(Calendar.DATE, -1);
			rightNow.set(Calendar.HOUR_OF_DAY, 23);
			rightNow.set(Calendar.MINUTE, 59);
			rightNow.set(Calendar.SECOND, 59);
		}
		return rightNow.getTime();
	}

	/**
	 * 得到当前时间
	 * 
	 * @return
	 */
	public static Date getNowDate() {
		Calendar cl = Calendar.getInstance();
		return cl.getTime();
	}

	public static String getFormattedDateUtil(java.util.Date dtDate,
			String strFormatTo) {
		if (dtDate == null) {
			return "";
		}
		strFormatTo = strFormatTo.replace('/', '-');
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(strFormatTo);
			return formatter.format(dtDate);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 格式化时间
	 * 
	 * @return
	 */
	public static String getFullTime() {
		Calendar cl = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(cl.getTime());
	}

	/**
	 * 取得给定字符串描述的日期对象，描述模式采用 format 指定的格式.
	 * 
	 * @param dateStr
	 *            日期描述
	 * @param format
	 *            日期模式
	 * @return 给定字符串描述的日期对象。
	 */
	public static Date parseDate(String dateStr, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date resDate = null;
		try {
			resDate = sdf.parse(dateStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resDate;
	}

	/**
	 * 格式化日期显示格式
	 * 
	 * @param dateStr
	 *            原始日期格式
	 * @param format
	 *            格式化后日期格式
	 * @return 格式化后的日期显示
	 */
	public static String dateFormat(String dateStr, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		java.sql.Date date = java.sql.Date.valueOf(dateStr);
		return formatter.format(date);
	}

	/**
	 * 格式化日期显示格式
	 * 
	 * @param date
	 *            日期
	 * @param format
	 *            日期格式
	 * @return 格式化后的日期显示
	 */
	public static String dateFormat(Date date, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		return formatter.format(date);
	}

	/**
	 * 格式化日期显示格式yyyy-MM-dd
	 * 
	 * @param dateStr
	 *            日期格式
	 * @return yyyy-MM-dd 格式化后的日期显示
	 */
	public static String dateFormat(String dateStr) {
		return dateFormat(dateStr, F_DATE);
	}

	/**
	 * 根据给定的格式，返回时间字符串。
	 * 
	 * @param format
	 *            日期格式字符串
	 * @return String 指定格式的日期字符串.
	 */
	public static String getFormatCurrentTime(String format) {
		return dateFormat(new Date(), format);
	}

	/**
	 * 取得当前日期的年份，以yyyy格式返回
	 * 
	 * @return 当年 yyyy
	 */
	public static String getCurrentYear() {
		return getFormatCurrentTime(F_YEAR);
	}
	public static String getCurrentYearAndMonth() {
		return getFormatCurrentTime(F_YEAR_MONTH);
	}
	public static String getYearAndMonth(Date date) {
		return dateFormat(dateFormat(date, F_DATE), F_YEAR_MONTH);
	}

	public static String getYear(Date date) {
		return dateFormat(dateFormat(date, F_DATE), F_YEAR);
	}

	public static String getMonth(Date date) {
		return dateFormat(dateFormat(date, F_DATE), F_MONTH);
	}

	public static String getMonthAndDay(Date date) {
		return dateFormat(dateFormat(date, F_DATE), F_DATE_MMDD);
	}

	/**
	 * 返回上一年 。例如当前年份是2007年，那么就自动返回2006
	 * 
	 * @return 返回结果的格式为 yyyy
	 */
	public static String getPreviousYear() {
		String currentYear = getFormatCurrentTime(F_YEAR);
		int previousYear = Integer.parseInt(currentYear) - 1;
		return "" + previousYear;
	}

	/**
	 * 取得当前日期的月份，以MM格式返回.
	 * 
	 * @return 当前月份 MM
	 */
	public static String getCurrentMonth() {
		return getFormatCurrentTime(F_MONTH);
	}

	/**
	 * 取得当前日期字符串 yyyy-MM-dd
	 */
	public static String getCurDate() {
		return getFormatCurrentTime(F_DATE);
	}

	/**
	 * 取得当前时间 HH:mm:ss
	 */
	public static String getCurTime() {
		return getFormatCurrentTime(F_TIME);
	}

	/**
	 * 取得当前日期(含时间 yyyy-MM-dd HH:mm:ss)
	 */
	public static String getCurDateTime() {
		return getFormatCurrentTime(F_DATETIME);
	}

	/**
	 * 求两个日期相差月数
	 * 
	 * @param sd
	 *            起始日期
	 * @param ed
	 *            终止日期
	 * @return 两个日期相差月数
	 */
	public static long getIntervalMonths(Date sd, Date ed) {
		long iStartYear = Long.parseLong(dateFormat(sd, F_YEAR));
		long iStartMonth = Long.parseLong(dateFormat(ed, F_YEAR));
		long iEndYear = Long.parseLong(dateFormat(sd, F_MONTH));
		long iEndMonth = Long.parseLong(dateFormat(sd, F_MONTH));
		return ((iEndYear - iStartYear) * 12) + (iEndMonth - iStartMonth);
	}

	public static Date max(Date d1, Date d2) {
		return (d2.getTime() - d1.getTime()) > 0 ? d2 : d1;
	}

	/**
	 * 求两个日期相差天数，
	 * 
	 * @param sd
	 *            起始日期
	 * @param ed
	 *            终止日期
	 * @return 两个日期相差天数
	 */
	public static long getIntervalDays(Date sd, Date ed) {
		return (long) (ed.getTime() - sd.getTime()) / C_ONE_DAY;
	}

	/**
	 * 求两个日期相差小时数
	 * 
	 * @param sd
	 *            起始日期
	 * @param ed
	 *            终止日期
	 * @return 两个日期相差小时数
	 */
	public static double getIntervalHours(Date sd, Date ed) {
		return (double) (ed.getTime() - sd.getTime()) / C_ONE_HOUR;
	}

	/**
	 * 求两个日期相差分钟数
	 * 
	 * @param sd
	 *            起始日期
	 * @param ed
	 *            终止日期
	 * @return 两个日期相差分钟数
	 */
	public static double getIntervalMinutes(Date sd, Date ed) {
		return (double) (ed.getTime() - sd.getTime()) / C_ONE_MINUTE;
	}

	/**
	 * 求两个日期相差秒数
	 * 
	 * @param sd
	 *            起始日期
	 * @param ed
	 *            终止日期
	 * @return 两个日期相差秒数
	 */
	public static double getIntervalSeconds(Date sd, Date ed) {
		return (double) (ed.getTime() - sd.getTime()) / C_ONE_SECOND;
	}

	/**
	 * @return 当前月份有多少天
	 */
	public static int getDaysOfCurMonth() {
		int curyear = Integer.parseInt(getCurrentYear()); // 当前年份
		int curMonth = Integer.parseInt(getCurrentMonth());// 当前月份
		int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		// 判断闰年的情况 ，2月份有29天；
		if ((curyear % 400 == 0)
				|| ((curyear % 100 != 0) && (curyear % 4 == 0))) {
			mArray[1] = 29;
		}
		return mArray[curMonth - 1];
		// 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
		// 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
	}

	public static int getDaysOfMonth(Date date) {
		int year = Integer.parseInt(getYear(date)); // 年份
		int month = Integer.parseInt(getMonth(date));// 月份
		int mArray[] = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,
				31 };
		// 判断闰年的情况 ，2月份有29天；
		if ((year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0))) {
			mArray[1] = 29;
		}
		return mArray[month - 1];
		// 如果要返回下个月的天数，注意处理月份12的情况，防止数组越界；
		// 如果要返回上个月的天数，注意处理月份1的情况，防止数组越界；
	}

	/**
	 * 根据指定的年月日小时分秒，返回一个 Date 对象。
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月 0-11
	 * @param date
	 *            日
	 * @param hourOfDay
	 *            小时 0-23
	 * @param minute
	 *            分 0-59
	 * @param second
	 *            秒 0-59
	 * @return 一个 Date 对象。
	 */
	public static Date getDate(int year, int month, int date, int hourOfDay,
			int minute, int second) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, hourOfDay, minute, second);
		return cal.getTime();
	}

	/**
	 * 获取某天开始的那一刻
	 * 
	 * @param year
	 * @param month
	 * @param date
	 */
	public static Date getDate(int year, int month, int date) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, date, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
	 * 
	 * @param year
	 * @param month
	 *            是从1开始的12结束
	 * @param day
	 * @return 返回一个代表当期日期是星期几的数字。
	 */
	public static int getDayOfWeek(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
	 * 
	 * @param year
	 * @param month
	 *            是从1开始的12结束
	 * @param day
	 * @return 返回一个代表当期日期是星期几的数字。
	 */
	public static int getDayOfWeek(String year, String month, String day) {
		int iYear = new Integer(year).intValue();
		int iMonth = new Integer(month).intValue();
		int iDay = new Integer(day).intValue();
		return getDayOfWeek(iYear, iMonth, iDay);
	}

	/**
	 * 根据指定的年、月、日返回当前是星期几。1表示星期天、2表示星期一、7表示星期六。
	 * 
	 * @param date
	 *            "yyyy/MM/dd",或者"yyyy-MM-dd"
	 * @return 返回一个代表当期日期是星期几的数字。
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 获取日期中的星期
	 * 
	 * @param date
	 *            日期
	 * @return 星期
	 */
	public static String getWeek(Date date) {
		DateFormat f_week = new SimpleDateFormat("EEEEEEE");
		return f_week.format(date).toString();
	}

	/**
	 * 取得给定日期加上一定毫秒数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的毫秒数，如果是向前的，使用负数就可以.
	 * @return Date 加上一定毫秒数以后的Date对象.
	 */
	public static Date addMilliseconds(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.MILLISECOND, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定秒数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的秒数，如果是向前的，使用负数就可以.
	 * @return Date 加上一定秒数以后的Date对象.
	 */
	public static Date addSeconds(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.SECOND, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定分钟数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的分钟数，如果是向前的，使用负数就可以.
	 * @return Date 加上一定分钟数以后的Date对象.
	 */
	public static Date addMinutes(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.MINUTE, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定小时数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的小时数，如果是向前的，使用负数就可以.
	 * @return Date 加上一定小时数以后的Date对象.
	 */
	public static Date addHours(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.HOUR, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，如果是向前的天数，使用负数就可以.
	 * @return Date 加上一定天数以后的Date对象.
	 */
	public static Date addDays(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定月数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的月数，如果是向前的月数，使用负数就可以.
	 * @return Date 加上一定月数以后的Date对象.
	 */
	public static Date addMonths(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.MONTH, amount);
		return cal.getTime();
	}

	/**
	 * 取得给定日期加上一定年数后的日期对象.
	 * 
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的年数，如果是向前的年数，使用负数就可以.
	 * @return Date 加上一定年数以后的Date对象.
	 */
	public static Date addYears(Date date, int amount) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(GregorianCalendar.YEAR, amount);
		return cal.getTime();
	}

	/**
	 * 合并日期和时间
	 * 
	 * @param date
	 *            日期
	 * @param time
	 *            时间
	 */
	public static Calendar mergeDateTime(Date date, Time time) {
		Calendar cal = Calendar.getInstance();
		if (date != null)
			cal.setTime(date);
		if (time != null) {
			Calendar temp = Calendar.getInstance();
			temp.setTime(time);
			cal.set(Calendar.HOUR_OF_DAY, temp.get(Calendar.HOUR_OF_DAY));
			cal.set(Calendar.MINUTE, temp.get(Calendar.MINUTE));
			cal.set(Calendar.SECOND, temp.get(Calendar.SECOND));
			cal.set(Calendar.MILLISECOND, temp.get(Calendar.MILLISECOND));
		}
		return cal;
	}

	/**
	 * 得到当前时间的时间戳
	 * 
	 * @return 当前时间戳 Timestamp
	 */
	public static Timestamp getNowTimestamp() {
		long curTime = System.currentTimeMillis();
		return new Timestamp(curTime);
	}

	/**
	 * 根据给定的格式，返回时间字符串。
	 * 
	 * @param format
	 *            日期时间格式串
	 * @return 返回一个时间字符串
	 */
	public static String getNowByFormat(String format) {
		Date now = new Date(System.currentTimeMillis());
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(now);
	}

	/**
	 * 返回两个日期之间的详细日期数组(包括开始日期和结束日期)。 例如：2007-07-01 到2007-07-03 ,那么返回数组
	 * {"2007-07-01","2007-07-02","2007-07-03"}
	 * 
	 * @param startDate
	 *            格式"yyyy-MM-dd"
	 * @param endDate
	 *            格式"yyyy-MM-dd"
	 * @return 返回一个字符串数组对象
	 */
	public static String[] getArrayBetweenDays(String startDate, String endDate) {
		if (startDate.equals(endDate)) {
			return new String[] { startDate };
		}
		Date sdate = parseDate(startDate, "yyyy-MM-dd"); // 开始日期
		Date edate = parseDate(endDate, "yyyy-MM-dd"); // 开始日期
		double len = getIntervalDays(sdate, edate);
		int l = (int) len;
		String[] dateResult = new String[l + 1];
		for (int i = 0; i < l + 1; i++) {
			dateResult[i] = dateFormat(addDays(sdate, i), "yyyy-MM-dd");
		}
		return dateResult;
	}

	/**
	 * 计算两天之间有多少个周末(周末指星期六和星期天)
	 * 
	 * @param startDate
	 *            开始日期 ，格式"yyyy-MM-dd"
	 * @param endDate
	 *            结束日期 ，格式"yyyy-MM-dd"
	 * @return int 天数
	 */
	public static int countWeekend(String startDate, String endDate) {
		int result = 0;
		Date sdate = parseDate(startDate, "yyyy-MM-dd"); // 开始日期
		Date edate = parseDate(endDate, "yyyy-MM-dd");// 结束日期
		// 首先计算出都有那些日期，然后找出星期六星期天的日期
		double len = getIntervalDays(sdate, edate);
		int l = (int) len;
		int sumDays = Math.abs(l);
		int dayOfWeek = 0;
		for (int i = 0; i <= sumDays; i++) {
			dayOfWeek = getDayOfWeek(addDays(sdate, i)); // 计算每过一天的日期
			if (dayOfWeek == 1 || dayOfWeek == 7) { // 1 星期天 7星期六
				result++;
			}
		}
		return result;
	}

	/**
	 * 返回指定日期是该年中的第几周
	 * 
	 * @param date
	 * @return int 第几周
	 */
	public static int getWeeksNoInYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.WEEK_OF_YEAR);
	}

	/**
	 * 比较时间 当time1<time2 返回 true
	 * 
	 * @param time1
	 * @param time2
	 * @return
	 */
	public static boolean comPareTime(String time1, String time2) {
		// 比较时间 当time1<time2 返回 true
		boolean sj = false;
		java.text.DateFormat df = new java.text.SimpleDateFormat("HH:mm:ss");
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(time1));
			c2.setTime(df.parse(time2));
		} catch (java.text.ParseException e) {
			System.err.println("格式不正确");
		}
		int result = c1.compareTo(c2);
		if (result >= 0)
			return sj;
		else if (result < 0)
			sj = true;
		return sj;

	}

	/**
	 * 获取 UTC 秒数, 1970年1月1日0时0分0秒到当前时间点所经过的秒数
	 * 
	 * @return
	 */
	public static int getUTCSeconds() {
		Date date = new Date();
		int second = (int) Math.abs(date.getTime() / 1000);
		return second;
	}

	/**
	 * 获取 UTC 秒数, 1970年1月1日0时0分0秒到当前时间点所经过的秒数
	 * 
	 * @return
	 */
	public static long getUTCSeconds(Date date) {
		int second = (int) Math.abs(date.getTime() / 1000);
		return second;
	}

	/**
	 * 将字符串解析为时间，字符串格式为yyyy-MM-ddThh:mm:ss 字符串时区为格林尼治时间，所以需要转化为东八区的时间
	 * 
	 * @param strTime
	 * @return
	 */
	public static Date parseToDate(String strTime) {
		String[] arr = strTime.split("T");
		String[] arr1 = null;
		String[] arr2 = null;
		if (arr.length >= 2) {
			arr1 = arr[0].split("-");
			arr2 = arr[1].split(":");
		} else {
			return null;
		}
		if (arr1.length >= 3 && arr2.length >= 2) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.valueOf(arr1[0]),
					Integer.valueOf(arr1[1]) - 1, Integer.valueOf(arr1[2]),
					Integer.valueOf(arr2[0]), Integer.valueOf(arr2[1]));
			// 将时间转化为东八区的时间
			calendar.setTimeInMillis(calendar.getTimeInMillis() + 3600000 * 8);
			return calendar.getTime();
		} else
			return null;
	}

	/**
	 * 将字符串解析为时间，字符串格式为yyyy-MM-ddThh:mm 字符串时区为格林尼治时间，所以需要转化为东八区的时间
	 * 
	 * @param strTime
	 * @return
	 */
	public static Date parseToDate2(String strTime) {
		String[] arr = strTime.split("T");
		String[] arr1 = null;
		String[] arr2 = null;
		if (arr.length >= 2) {
			arr1 = arr[0].split("-");
			arr2 = arr[1].split(":");
		} else {
			return null;
		}
		if (arr1.length >= 3 && arr2.length >= 3) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(Integer.valueOf(arr1[0]),
					Integer.valueOf(arr1[1]) - 1, Integer.valueOf(arr1[2]),
					Integer.valueOf(arr2[0]), Integer.valueOf(arr2[1]),
					Integer.valueOf(arr2[2].substring(0, 2)));
			// 将时间转化为东八区的时间
			calendar.setTimeInMillis(calendar.getTimeInMillis() + 3600000 * 8);
			return calendar.getTime();
		} else
			return null;
	}

	public static Map<String, String> getDatas(Date startTime, Date endTime) {
		Map<String, String> mon = new LinkedHashMap<String, String>();
		if (startTime != null && endTime != null) {
			String startMonth = getMonth(startTime);
			String endMonth = getMonth(endTime);
			String startYear = getYear(startTime);
			String endYear = getYear(endTime);
			int number = 0;
			if (startYear.equals(endYear)) {
				number = Integer.parseInt(endMonth)
						- Integer.parseInt(startMonth) + 1;
			} else {
				number = 12 - Integer.parseInt(startMonth) + 1
						+ Integer.parseInt(endMonth);
			}
			int tMonth = Integer.parseInt(startMonth);
			int tYear = Integer.parseInt(startYear);
			for (int i = 0; i < number; i++) {
				if (tMonth == 13) {
					tYear = Integer.parseInt(endYear);
					tMonth = 1;
				}
				String m = tMonth < 10 ? "0" + tMonth : tMonth + "";
				mon.put(tYear + "年" + m + "月", tYear + "-" + m);
				tMonth++;
			}
			for (Entry<String, String> item : mon.entrySet()) {
				System.out.println(item.getKey() + " * " + item.getValue());
			}
		}
		return mon;
	}

	public static String getTimebucket(Date date) {
		StringBuffer sb = new StringBuffer();
		String year = getYear(date);
		String month = getMonth(date);
		int das = getDaysOfMonth(date);
		sb.append(year).append("-").append(month).append("-").append("01")
				.append("*").append(year).append("-").append(month).append("-")
				.append(das);
		return sb.toString();
	}

	public static List<String> getDaylist(Date date) {
		List<String> days = new ArrayList<String>();
		int ds = getDaysOfMonth(date);
		String month = getMonth(date);
		for (int i = 1; i <= ds; i++) {
			if (i < 10) {
				days.add(month + "/0" + i);
			} else {
				days.add(month + "/" + i);
			}
		}
		return days;
	}
}
