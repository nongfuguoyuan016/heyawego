package com.weixin.heyawego.app.common.utils;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * 
 * @author ganjinhua
 * @version 2014-4-15
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
			"yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM", "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss",
			"yyyy.MM.dd HH:mm", "yyyy.MM" };

	private static String startTimes = " 00:00:00";
	private static String endTimes = " 23:59:59";
	public static String FULL_TIME = "yyyy-MM-dd HH:mm:ss";
	public static String FULL_DAY_TIME = "yyyy-MM-dd";

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd）
	 */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/**
	 * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/**
	 * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
	 */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		} else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/**
	 * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前时间字符串 格式（HH:mm:ss）
	 */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/**
	 * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
	 */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 得到当前年份字符串 格式（yyyy）
	 */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/**
	 * 得到当前月份字符串 格式（MM）
	 */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/**
	 * 得到当天字符串 格式（dd）
	 */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/**
	 * 得到当前星期字符串 格式（E）星期几
	 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/**
	 * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy.MM.dd",
	 * "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取过去的天数
	 * 
	 * @param date
	 * @return
	 */
	public static long pastDays(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (24 * 60 * 60 * 1000);
	}

	/**
	 * 获取过去的小时
	 * 
	 * @param date
	 * @return
	 */
	public static long pastHour(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 60 * 1000);
	}

	/**
	 * 获取过去的分钟
	 * 
	 * @param date
	 * @return
	 */
	public static long pastMinutes(Date date) {
		long t = new Date().getTime() - date.getTime();
		return t / (60 * 1000);
	}

	/**
	 * 转换为时间（天,时:分:秒.毫秒）
	 * 
	 * @param timeMillis
	 * @return
	 */
	public static String formatDateTime(long timeMillis) {
		long day = timeMillis / (24 * 60 * 60 * 1000);
		long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
		long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
		long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
		long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000 - s * 1000);
		return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
	}

	/**
	 * 获取两个日期之间的天数
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static double getDistanceOfTwoDate(Date before, Date after) {
		long beforeTime = before.getTime();
		long afterTime = after.getTime();
		return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
	}

	/**
	 * 得到几天前的时间
	 * 
	 * @param dateTime(yyyy-MM-dd
	 *            HH:mm:ss)
	 * @param day
	 * @return
	 */
	public static String getDateBefore(String dateTime, int day) {
		Calendar now = Calendar.getInstance();
		try {
			Date d = DateUtils.parseDate(dateTime, "yyyy-MM-dd HH:mm:ss");
			now.setTime(d);
			now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DateUtils.formatDate(now.getTime(), "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 
	 * @param dateTime
	 *            "yyyy-MM-dd HH:mm:ss"
	 */
	public static void getDay(String dateTime, int day) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateTime);
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		date = calendar.getTime();
		System.out.println(sdf.format(date));
	}

	/**
	 * 返回最近3天的时间
	 * 
	 * @param dateTime
	 *            2017-09-12
	 * @param day
	 *            2
	 * @return [2017-09-09,2017-09-10, 2017-09-11]
	 */
	public static List<String> getDateBefores(String dateTime, int day) {
		List<String> dayList = Lists.newArrayList();
		for (int i = day; i >= 0; i--) {
			Calendar now = Calendar.getInstance();
			try {
				Date d = DateUtils.parseDate(dateTime, "yyyy-MM-dd");
				now.setTime(d);
				now.set(Calendar.DATE, now.get(Calendar.DATE) - i);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String date = DateUtils.formatDate(now.getTime(), "yyyy-MM-dd");
			dayList.add(date);
		}
		return dayList;
	}

	/**
	 * 返回开始时间 ,在传入的时间后加上" 00:00:00"
	 * 
	 * @param startTime
	 *            2017-09-12
	 * @return 2017-09-12 00:00:00
	 */
	public static String getDayStart(String startTime) {
		String time = startTime.trim() + startTimes;
		return time;
	}

	/**
	 * 返回当天的结束时间 加上 " 23:59:59"
	 * 
	 * @param endTime
	 *            2017-09-12
	 * @return 2017-09-12 23:59:59
	 */
	public static String getDayEnd(String endTime) {
		String time = endTime.trim() + endTimes;
		return time;
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 返回开始时间,在开始时间后加上" 00:00:00"
	 * @param startTime
	 * @return
	 */
	public static Date getDayStart(Date startTime) {
		String time = formatDate(startTime, "yyyy-MM-dd") + startTimes;
		return parseDate(time);
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 返回结束时间,在开始时间后加上" 23:59:59"
	 * @param startTime
	 * @return
	 */
	public static Date getDayEnd(Date endTime) {
		String time = formatDate(endTime, "yyyy-MM-dd") + endTimes;
		return parseDate(time);
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取指定时间前/后几分钟的时间,(minute:前为负数,后为正数)
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date getMinuteBefore(Date date, int minute) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		return cal.getTime();
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取指定时间前/后几分钟的时间,(minute:前为负数,后为正数)
	 * @param date_string
	 * @param minute
	 * @return
	 */
	public static String getMinuteBefore(String date_string, int minute) {
		Date date = parseDate(date_string);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minute);
		date = cal.getTime();
		date_string = formatDateTime(date);
		return date_string;
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取指定日期前/后几天的日期,(day:前为负数,后为正数)
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date getDayBefore(Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_YEAR, day);
		return cal.getTime();
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 比较两个日期是否一致
	 * @param date
	 * @param dat
	 * @return
	 */
	public static boolean sameDate(Date date, Date dat) {
		if (null != date && null != dat) {
			String date_str = formatDateTime(date);
			String date_src = formatDateTime(dat);
			return date_str.equals(date_src);
		}
		return false;
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取距离指定日期前/后的某个月
	 * @param date
	 *            指定日期
	 * @param month
	 *            前/后几个月 (偏移量 : 后为正,前为负)
	 * @return
	 */
	public static Date getMonthBefore(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取指定日期当月的第一天的第一秒(2017-10-1 00:00:00)
	 * @param date
	 * @return
	 */
	public static Date getMonthFirstDay(Date date) {
		Calendar cal = Calendar.getInstance();
		String dateStr = formatDate(date, "yyyy-MM-dd");
		String[] array = dateStr.split("-");
		int year = Integer.parseInt(array[0]);
		int month = Integer.parseInt(array[1]);
		cal.set(year, month - 1, 1, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取指定日期当月的最后一天的最后一秒(2017-10-31 23:59:59)
	 * @param date
	 * @return
	 */
	public static Date getMonthLastDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maxDay = cal.getActualMaximum(cal.DAY_OF_MONTH);
		String dateStr = formatDate(date, "yyyy-MM-dd");
		String[] array = dateStr.split("-");
		int year = Integer.parseInt(array[0]);
		int month = Integer.parseInt(array[1]);
		cal.set(year, month - 1, maxDay, 23, 59, 59);
		return cal.getTime();
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取两个日期之间相差的月数
	 * @param date
	 * @param dat
	 * @return
	 */
	public static int getDistanceMonthBetweenDate(Date date, Date dat) {
		Calendar cal = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		cal.setTime(date);
		c.setTime(dat);
		int year = (cal.get(Calendar.YEAR) - c.get(Calendar.YEAR)) * 12;
		int month = cal.get(Calendar.MONTH) - c.get(Calendar.MONTH);
		int result = year + month;
		return Math.abs(result);
	}

	/**
	 * 
	 * @author xuchang
	 * @Description 获取指定日期当月的星期列表,从每周的第一个周一起到下个月的第一个周一止
	 * @param date
	 * @return 返回字符串集合,每个元素按日期顺序排序,由周一到周日组成,以逗号分隔 例:2017-09-04,2017-09-10
	 *         2017-09-11,2017-09-17 2017-09-18,2017-09-24 2017-09-25,2017-10-01
	 */
	public static List<String> getEveryWeekOfMonth(Date date) {
		List<String> list = Lists.newArrayList();
		date = getMonthFirstDay(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int days = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		String month = formatDate(date, "MM");
		for (int i = 0; i < days; i++) {
			String weekDay = formatDate(date, "E");
			String mon = formatDate(date, "MM");
			// System.out.println(formatDate(date)+" "+formatDate(date, "E"));
			if ("星期一".equals(weekDay) || "Mon".equals(weekDay)) {
				if (!month.equals(mon)) {
					break;
				}
				Date newDate = getDayBefore(date, 6);
				// list.add("{"+formatDate(date)+formatDate(date,
				// "E")+","+formatDate(getDayBefore(date,
				// 6))+formatDate(getDayBefore(date, 6), "E")+"}");
				list.add(formatDate(date) + "," + formatDate(newDate));
				date = newDate;
			} else {
				date = getDayBefore(date, 1);
			}
		}
		// System.out.println(Arrays.toString(list.toArray()));
		return list;

	}

	/**
	 * 获取2个时间段之间每隔5分钟的时间 2014-07-01 12:00:00 2014-07-01 12:05:00 *** 2014-07-01
	 * 12:55:00
	 * 
	 * @param startTime
	 *            开始时间 2014-07-01 12:00:00
	 * @param endTime
	 *            结束时间 2014-07-01 13:00:00
	 * @param day
	 * @return
	 */
	public static List<Date> getTimeSegmentByHour(String startTime, String endTime) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = parseDate(startTime, FULL_TIME);
			endDate = parseDate(endTime, FULL_TIME);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.MILLISECOND, 0);
		long startTimes = cal.getTimeInMillis();
		cal.setTime(endDate);
		long endTimes = cal.getTimeInMillis();
		final int seg = 5 * 60 * 1000;// 五分钟
		ArrayList<Date> result = new ArrayList<Date>((int) ((endTimes - startTimes) / seg + 1));
		for (long time = startTimes; time <= endTimes; time += seg) {
			result.add(new Date(time));
		}
		return result;
	}

	/**
	 * 获取当前时间小时的开始 2018-07-01 12:02:30 返回 2018-07-01 12:00:00
	 * 
	 * @param date
	 *            当前时间
	 * @return
	 */
	public static Date getCurrentHourBegin(Date date) throws ParseException {
		String begin = DateUtils.formatDate(date, "yyyy-MM-dd HH:00:00");
		return DateUtils.parseDate(begin, DateUtils.FULL_TIME);
	}

	public static void main(String[] args) throws ParseException {
		//Date date = parseDate("2017-9-3", "yyyy-MM-dd");
		//Date dat = parseDate("2016-3-29", "yyyy-MM-dd");
		//System.out.println(getTimeSegmentByHourWithOneSecondLess("2014-07-01 12:00:00","2014-07-02 12:00:00",60));
		/*
		 * date = getMonthBefore(date,1); System.out.println(formatDate(date,
		 * "yyyy-MM-dd HH:mm:ss"));
		 * System.out.println(getDistanceMonthBetweenDate(date,dat)); date =
		 * getMonthLastDay(date); System.out.println(formatDate(date,
		 * "yyyy-MM-dd HH:mm:ss"));
		 */
		/*
		 * String month = formatDate(dat, "MM"); System.out.println(month);
		 */
		//getEveryWeekOfMonth(date);
		String fileName = DateUtils.formatDate(new Date(),"yyyyMMddHHmmss") ;
		System.out.println(fileName);
	}

	/**
	 * 
	 * @author liurenqun
	 * @Description 获取指定时间前/后几秒的时间,(minute:前为负数,后为正数)
	 * @param date
	 * @param second
	 * @return
	 */
	public static Date getSecondBefore(Date date, int second) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.SECOND, second);
		return cal.getTime();
	}

	/**
	 * 获取2个时间段之间每隔5分钟的时间 2014-07-01 11:59:59 2014-07-01 12:04:59 *** 2014-07-01
	 * 12:55:59
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param day
	 * @return
	 */
	public static List<Date> getTimeSegmentByHourWithOneSecondLess(String startTime, String endTime) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = parseDate(startTime, FULL_TIME);
			endDate = parseDate(endTime, FULL_TIME);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.MILLISECOND, 0);
		long startTimes = cal.getTimeInMillis();
		cal.setTime(endDate);
		long endTimes = cal.getTimeInMillis();
		final int seg = 5 * 60 * 1000;// 五分钟
		ArrayList<Date> result = new ArrayList<Date>((int) ((endTimes - startTimes) / seg + 1));
		for (long time = startTimes; time <= endTimes; time += seg) {
			Date date = new Date(time);
			date = getSecondBefore(date, -1);
			result.add(date);
		}
		return result;
	}
	
	
	/**
	 * 获取2个时间段之间每隔5分钟的时间 2014-07-01 11:59:59 2014-07-01 12:04:59 *** 2014-07-01
	 * 12:55:59
	 * 
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @param segment
	 * 			  时间间隔
	 * @param day
	 * @return
	 */
	public static List<Date> getTimeSegmentByHourWithOneSecondLess(String startTime, String endTime,int segment) {
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = parseDate(startTime, FULL_TIME);
			endDate = parseDate(endTime, FULL_TIME);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.MILLISECOND, 0);
		long startTimes = cal.getTimeInMillis();
		cal.setTime(endDate);
		long endTimes = cal.getTimeInMillis();
		final int seg = segment * 60 * 1000;// 五分钟
		ArrayList<Date> result = new ArrayList<Date>((int) ((endTimes - startTimes) / seg + 1));
		for (long time = startTimes; time <= endTimes; time += seg) {
			Date date = new Date(time);
			date = getSecondBefore(date, -1);
			result.add(date);
		}
		return result;
	}

	/**
	 * 返回最近几天的时间
	 * 
	 * @param dateTime
	 *            2017-09-12
	 * @param day
	 *            2
	 * @return [2018-03-14, 2018-03-15, 2018-03-16, 2018-03-17, 2018-03-18,
	 *         2018-03-19, 2018-03-20]
	 */
	public static List<String> getTodayBefores(Date d, int day) {
		List<String> dayList = Lists.newArrayList();
		for (int i = day; i >= 0; i--) {
			Calendar now = Calendar.getInstance();
			try {

				now.setTime(d);
				now.set(Calendar.DATE, now.get(Calendar.DATE) - i);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String date = DateUtils.formatDate(now.getTime(), "yyyy-MM-dd");
			dayList.add(date);
		}
		return dayList;
	}

}
