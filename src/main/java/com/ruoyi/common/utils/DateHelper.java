/**
 * HS
 * 
 */
package com.ruoyi.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;


/**
 * 日期常用工具方法类
 */
public final class DateHelper {

	/**
	* "YYYY-MM-DD"日期格式
	*/
	public static final String DATE_YYYYMMMMDD = "yyyy-MM-dd";

	/**
	 * "HH:MM:SS"时间格式
	 */
	public static final String TIME_HHCMMCSS = "HH:mm:ss";
	
	/**
	 * "HH:MM"时间格式
	 */
	public static final String TIME_HHCMM = "HH:MM";
	
	/**
	 * "HH:MM:SS AMPM"时间格式
	 */
	public static final String TIME_HHCMMCSSAMPM = "HH:MM:SS AMPM";
	
	
	public static final String YYYY = "yyyy";  
    
    public static final String YYYYMM = "yyyy-MM";  
      
    public static final String YYYYMMDD = "yyyy-MM-dd";  
      
    public static final String YYYYMMDDHH= "yyyy-MM-dd HH";  
      
    public static final String YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";  
      
    public static final String YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat m_dtFormater = null;

	/**
	 * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
	 * @return		当前日期时间
	 */
	public static String getCurDateTimeStr() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String str = timestamp.toString();
		return new StringBuffer().append(datetime).toString();
	}
	
	
	
	public static Date formateDate(String newdate) {
		Date date=null;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat(YYYYMMDDHHMMSS);
			date= sdf.parse(newdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	
	
	
	
	
	
	
	/**
	 * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
	 * @return		当前日期时间
	 */
	public static Timestamp getNowTimestamp(){
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		return timestamp;
	}

	/**
	 * 获取当前日期时间。 YYYY-MM-DD HH:MM:SS
	 * @return		当前日期时间
	 */
	public static String getCurDateTime() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		return (timestamp.toString()).substring(0, 19);
	}

	/**
	 * 获取当前日期。YYYY-MM-DD
	 * @return		当前日期
	 */
	public static String getCurrentDate() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentdate = (timestamp.toString()).substring(0, 4) + "-" + (timestamp.toString()).substring(5, 7) + "-"
				+ (timestamp.toString()).substring(8, 10);
		return currentdate;
	}
	
	/**
	 * 将Date类型转换为YYYY-MM-DD格式字符串。
	 * @param newdate	Date类型日期
	 * @return			String类型日期
	 */
	public static String getDate(Date newdate){
		if (newdate== null)
			return "";
		long datetime = newdate.getTime() ;
		Timestamp timestamp = new Timestamp(datetime) ;
		String currentdate = (timestamp.toString()).substring(0,4) + "-" + (timestamp.toString()).substring(5,7) + "-" +(timestamp.toString()).substring(8,10);
		return currentdate;
	}
	/**
	 * 将YYYY-MM-DD格式字符串转换为Date类型。
	 * @param newdate	String类型日期
	 * @return			Date类型日期
	 */
	public static Date getDate(String newdate){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (newdate== null)
			newdate = DateHelper.getCurrentDate();
		Date date = new Date();
		try {
			date = sdf.parse(newdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	/**
	 * 获取当前日期yyyy-mm-dd date类型
	 * @return			Date类型日期
	 */
	public static Date getDate(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String newdate = DateHelper.getCurrentDate();
		Date date = new Date();
		try {
			date = sdf.parse(newdate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当前年份。YYYY
	 * @return		当前年份
	 */
	public static String getCurrentYear() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currentyear = (timestamp.toString()).substring(0, 4);
		return currentyear;
	}

	/**
	 * 获取开始时间和结束时间之间的天数。
	 * @param datebegin	开始日期
	 * @param dateend		结束日期
	 * @return				天数
	 */
	public static long getDisDays(String datebegin, String dateend) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateBegin = sdf.parse(datebegin);
			Date dateEnd = sdf.parse(dateend);
			return (dateEnd.getTime() - dateBegin.getTime()) / (3600 * 24 * 1000) + 1;
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 获取当前时间(HH:MM:SS)。
	 * @return		当前时间
	 */
	public static String getCurrentTime() {
		Date newdate = new Date();
		long datetime = newdate.getTime();
		Timestamp timestamp = new Timestamp(datetime);
		String currenttime = (timestamp.toString()).substring(11, 13) + ":" + (timestamp.toString()).substring(14, 16) + ":"
				+ (timestamp.toString()).substring(17, 19);
		return currenttime;
	}

	/**
	* 计算两个日期之间间隔天数方法。
	* @param d1    开始日期.
	* @param d2    结束日期
	* @return      间隔天数
	*/
	public static long getDaysBetween(java.util.Calendar d1, java.util.Calendar d2) {
		return (d1.getTime().getTime() - d2.getTime().getTime()) / (3600 * 24 * 1000);
	}

	/**
	* 计算两个日期之间间隔(d1-d2)天数。
	* @param d1	开始日期(yyyy-MM-dd)
	* @param d2   	结束日期(yyyy-MM-dd)
	* @return      间隔天数
	*/
	public static long getDaysBetween(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = sdf.parse(d1);
			Date dt2 = sdf.parse(d2);
			return (dt1.getTime() - dt2.getTime()) / (3600 * 24 * 1000);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 计算两个日期之间的时间间隔(d1-d2)天数，可选择是否计算工作日。
	 * @param d1			开始日期
	 * @param d2			结束日期
	 * @param onlyWorkDay 	是否只计算工作日
	 * @return 			间隔天数
	 */
	public static long getDaysBetween(String d1, String d2, boolean onlyWorkDay) {
		if (!onlyWorkDay) {
			return getDaysBetween(d1, d2);
		} else {
			long days = 0;
			Calendar calendar = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				Date dt1 = sdf.parse(d1);
				Date dt2 = sdf.parse(d2);
				days = (dt1.getTime() - dt2.getTime()) / (3600 * 24 * 1000);
				for (calendar.setTime(dt1); calendar.getTime().after(dt2); calendar.add(Calendar.DAY_OF_YEAR, -1)) {
					int week = calendar.get(Calendar.DAY_OF_WEEK);
					if (week == Calendar.SUNDAY || week == Calendar.SATURDAY) {
						days--;
					}
				}
				if (days < 0) {
					days = 0;
				}
			} catch (Exception e) {
			}
			return days;
		}
	}

	/**
	 * 判断日期是否为工作日(周六和周日为非工作日)。
	 * @param date		日期
	 * @return 		是否为工作日
	 */
	public static boolean isWorkDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int week = calendar.get(Calendar.DAY_OF_WEEK);
		if (week == Calendar.SUNDAY || week == Calendar.SATURDAY) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 计算两个时间之间的间隔。单位：分钟(minutes)。
	 * @param s1		开始日期(yyyy-MM-dd/HH:mm:ss)
	 * @param s2		结束日期(yyyy-MM-dd/HH:mm:ss)
	 * @return			间隔分钟
	 */
	public static long getMinutesBetween(String s1, String s2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		try {
			Date dt1 = sdf.parse(s1);
			Date dt2 = sdf.parse(s2);
			return (dt1.getTime() - dt2.getTime()) / (60 * 1000);
		} catch (Exception e) {
			return 0;
		}

	}

	/**
	 * 计算两个时间之间的间隔。返回格式：xx天xx小时xx分
	 * @param s1		开始日期(yyyy-MM-dd/HH:mm:ss)
	 * @param s2		结束日期(yyyy-MM-dd/HH:mm:ss)
	 * @return			间隔分钟
	 */
	public static String getConsumingBetween(String s1, String s2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
		try {
			Date dt1 = sdf.parse(s1);
			Date dt2 = sdf.parse(s2);
			long minutes = (dt1.getTime() - dt2.getTime()) / (60 * 1000);
			long consumingminutes = minutes%60;
			long hours = minutes/60;
			long consuminghours = hours%24;
			long days = hours/24;
			String consumingDate = days+"天"+consuminghours+"小时"+consumingminutes+"分";
			return consumingDate;
		} catch (Exception e) {
			return "";
		}

	}
	/**
	 * 返回两个日期间隔。
	 * <p>按月分隔的list，list里面每个月一个map,第一天begindate，最后一天enddate</p>
	 * @param d1		开始日期(yyyy-MM-dd)
	 * @param d2		结束日期
	 * @return			按月分隔的List
	 */
	public static List<HashMap> getDateBetween(String d1, String d2) {
		ArrayList<HashMap> list = new ArrayList<HashMap>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(sdf.parse(d1));
			cal2.setTime(sdf.parse(d2));

			int monthnum = (cal2.get(Calendar.YEAR) - cal1.get(Calendar.YEAR)) * 12 + cal2.get(Calendar.MONTH) - cal1.get(Calendar.MONTH);
			for (int i = 0; i < monthnum; i++) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("begindate", sdf.format(cal1.getTime()));
				cal1.add(Calendar.DATE, cal1.getActualMaximum(Calendar.DATE) - cal1.get(Calendar.DATE));
				map.put("enddate", sdf.format(cal1.getTime()));
				list.add(map);
				cal1.add(Calendar.MONTH, 1);
				cal1.add(Calendar.DATE, 1 - cal1.get(Calendar.DATE));
			}
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("begindate", sdf.format(cal1.getTime()));
			map.put("enddate", d2);
			list.add(map);
		} catch (Exception e) {
			return list;
		}
		return list;
	}

	/**
	 * 返回两个时间段相交的天数。 
	 * @param b1	开始时间段开始日期(yyyy-MM-dd)
	 * @param e1	开始时间段结束日期
	 * @param b2	结束时间段开始日期
	 * @param e2	结束时间段结束日期
	 * @return		间隔天数
	 */
	public static long getDays(String b1, String e1, String b2, String e2) {
		long ret = 0;
		String begindate = "";
		String enddate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar begin1 = Calendar.getInstance();
			Calendar end1 = Calendar.getInstance();
			Calendar begin2 = Calendar.getInstance();
			Calendar end2 = Calendar.getInstance();
			begin1.setTime(sdf.parse(b1));
			end1.setTime(sdf.parse(e1));
			begin2.setTime(sdf.parse(b2));
			end2.setTime(sdf.parse(e2));
			//时间段不相交 
			if ((begin2.getTime().getTime() > end1.getTime().getTime() && end2.getTime().getTime() > end1.getTime().getTime())
					|| (begin2.getTime().getTime() < begin1.getTime().getTime() && end2.getTime().getTime() < begin1.getTime().getTime())) {
				//System.out.println("b"+ret);
				return ret;
			}

			if (begin2.getTime().getTime() >= begin1.getTime().getTime()) {
				begindate = sdf.format(begin2.getTime());
			} else {
				begindate = sdf.format(begin1.getTime());
			}
			if (end2.getTime().getTime() >= end1.getTime().getTime()) {
				enddate = sdf.format(end1.getTime());
			} else {
				enddate = sdf.format(end2.getTime());
			}

			if (!begindate.equals("") && !enddate.equals("")) {
				ret = getDisDays(begindate, enddate);
			}
		} catch (Exception e) {

		}
		//System.out.println("e"+ret);
		return ret;
	}

	/**
	 * 比较两个日期d1<d2。
	 * @param d1		开始日期(yyyy-MM-dd)
	 * @param d2		结束日期
	 * @return			d1是否小于d2
	 */
	public static boolean after(String d1, String d2) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = sdf.parse(d1);
			Date dt2 = sdf.parse(d2);
			return dt1.getTime() < dt2.getTime();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 移动日期。
	 * @param date		原日期(yyyy-MM-dd)	
	 * @param len		移动天数
	 * @return			移动后日期
	 */
	public static String dayMove(String date, int len) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.DATE, len);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}
	
	

	/**
	 * 移动日期。
	 * @param date		原日期(yyyy-MM-dd)
	 * @param year		年
	 * @param month	月
	 * @param day		日
	 * @param honr		小时
	 * @param mintues	分钟
	 * @param second	秒
	 * @return			移动后日期
	 */
	public static String dayMoveDateTime(String date, int year, int month, int day, int honr, int mintues, int second) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.YEAR, year);
			cal.add(Calendar.MONTH, month);
			cal.add(Calendar.DATE, day);
			cal.add(Calendar.HOUR, honr);
			cal.add(Calendar.MINUTE, mintues);
			cal.add(Calendar.SECOND, second);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}
	
	/**
	 * 移动日期。
	 * @param date		原日期(yyyy-MM-dd)
	 * @param year		年
	 * @param month	月
	 * @param day		日
	 * @param honr		小时
	 * @param mintues	分钟
	 * @param second	秒
	 * @return			移动后日期
	 */
	public static String dayMoveDateTime(String date,String dateformat, int year, int month, int day, int honr, int mintues, int second) {
		SimpleDateFormat sdf = new SimpleDateFormat(dateformat);
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.YEAR, year);
			cal.add(Calendar.MONTH, month);
			cal.add(Calendar.DATE, day);
			cal.add(Calendar.HOUR, honr);
			cal.add(Calendar.MINUTE, mintues);
			cal.add(Calendar.SECOND, second);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}
	
	/**
	 * 获取日期控件中WdatePicker里配置的dateFmt中的值，即日期格式
	 * @param fieldcheck
	 * @return 
	 */
	public static String getWdatePickerDateFmt(String fieldcheck){
		String dateformat = "";
		if(StringUtils.isEmpty(fieldcheck)){
			dateformat = "yyyy-MM-dd HH:mm:ss";
		}
		//{dateFmt:'yyyy年MM月dd日 HH时mm分ss秒',alwaysUseStartDate:true}
		int dateFmtIndex = fieldcheck.indexOf("dateFmt");
		if(dateFmtIndex>-1){
			int datefmtStart = fieldcheck.indexOf("'", dateFmtIndex);
			int datefmtEnd = fieldcheck.indexOf("'",datefmtStart+1);
			dateformat = fieldcheck.substring(datefmtStart+1,datefmtEnd);
		}
		return dateformat;
	}
	

	/**
	 * 返回当前月份(yyyy-MM)。
	 * @return		当前月份
	 */
	public static String getCurrentMonth() {
		Calendar today = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年M月份");
		String curmonth = sdf.format(today.getTime());
		return curmonth;
	}
	
	/**
	 * 返回当前时间的月份(MM)。
	 * @return		当前月份
	 */
	public static String getDateMonth(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String month = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			
			month = StringHelper.null2String(cal.get(Calendar.MONTH) + 1);
		} catch (Exception e) {
			return date;
		}
		return month;
	}
	
	/**
	 * 返回当前时间的月份(MM)。
	 * @return		当前月份
	 */
	public static int getDateMonth() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String month = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			month = StringHelper.null2String(cal.get(Calendar.MONTH) + 1);
		} catch (Exception e) {
		}
		return Integer.parseInt(month);
	}
	
	/**
	 * 返回当前时间的年份(YYYY)。
	 * @return		当前月份
	 */
	public static String getDateYear(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String year = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			
			year = StringHelper.null2String(cal.get(Calendar.YEAR));
		} catch (Exception e) {
			return date;
		}
		return year;
	}
	
	/**
	 * 返回当前时间的月份(week)。
	 * @return		月份
	 */
	public static String getDateWeek(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String week = "";
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			
			week = StringHelper.null2String(cal.get(Calendar.WEEK_OF_YEAR) - 1);
		} catch (Exception e) {
			return date;
		}
		return week;
	}

	/**
	 * 移动月份。
	 * @param date		原日期
	 * @param len		移动月份
	 * @return			移动后日期
	 */
	public static String monthMove(String date, int len) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(date));
			cal.add(Calendar.MONTH, len);
			return sdf.format(cal.getTime());
		} catch (Exception e) {
			return date;
		}
	}
	
	
	/**
	 * 移动月份。
	 * @param date		原日期
	 * @param len		移动月份
	 * @return			移动后日期
	 */
	public static Date monthMove(Date date, int len) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH,len);
			return c.getTime();
		} catch (Exception e) {
			return date;
		}
	}

	/**
	 * 截取2个时间段相交的时间段。
	 * @param b1		开始时间段开始时间
	 * @param e1		开始时间段结束时间
	 * @param b2		结束时间段开始时间
	 * @param e2		结束时间段结束时间
	 * @return			相交时间段 String[] = {array[0]=begindate ,array[1]=enddate}，不相交array[0]=""
	 */
	public static String[] getBetweenDate(String b1, String e1, String b2, String e2) {
		String[] date = new String[2];
		String begindate = "";
		String enddate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Calendar begin1 = Calendar.getInstance();
			Calendar end1 = Calendar.getInstance();
			Calendar begin2 = Calendar.getInstance();
			Calendar end2 = Calendar.getInstance();
			begin1.setTime(sdf.parse(b1));
			end1.setTime(sdf.parse(e1));
			begin2.setTime(sdf.parse(b2));
			end2.setTime(sdf.parse(e2));
			if ((begin2.getTime().getTime() >= end1.getTime().getTime() && end2.getTime().getTime() >= end1.getTime().getTime())
					|| (begin2.getTime().getTime() <= begin1.getTime().getTime() && end2.getTime().getTime() <= begin1.getTime().getTime())) {
				date[0] = "";
				return date;
			}

			if (begin2.getTime().getTime() >= begin1.getTime().getTime()) {
				begindate = sdf.format(begin2.getTime());
			} else {
				begindate = sdf.format(begin1.getTime());
			}
			if (end2.getTime().getTime() >= end1.getTime().getTime()) {
				enddate = sdf.format(end1.getTime());
			} else {
				enddate = sdf.format(end2.getTime());
			}

			if (!begindate.equals("") && !enddate.equals("")) {
				date[0] = begindate;
				date[1] = enddate;
			}
		} catch (Exception e) {

		}
		return date;
	}

	/**
	 * 根据日期返回该月的第一天。
	 * @param date		日期
	 * @return			该月第一天
	 */
	public static String getFirstDayOfMonthWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, 1);//1st
		//int day = calendar.get(Calendar.DAY_OF_WEEK);
		//calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}
	public static String getFirstDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);//1st
		//int day = calendar.get(Calendar.DAY_OF_WEEK);
		//calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}
	/**
	 * 根据日期返回该月的第一天。
	 * @param date		日期
	 * @return			该月第一天
	 */
	public static Date getFirstDayOfMonthWeek() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.set(Calendar.DAY_OF_MONTH, 1);//1st
		//int day = calendar.get(Calendar.DAY_OF_WEEK);
		//calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);
		Date date1 = calendar.getTime();
		return date1;
	}
	/**
	 * 根据日期返回该月的最后一天。
	 * @param date		日期
	 * @return			该月最后一天
	 */
	public static String getLastDayOfMonthWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1); //last day
		//int day = calendar.get(Calendar.DAY_OF_WEEK);
		//calendar.add(Calendar.DATE, 7 - day);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}
	public static String getLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DATE, 1);
		calendar.add(Calendar.DATE, -1); //last day
		//int day = calendar.get(Calendar.DAY_OF_WEEK);
		//calendar.add(Calendar.DATE, 7 - day);
		Date date1 = calendar.getTime();
		return sdf.format(date1);
	}
	
	
	
	/** 
	 * 获取某季度的第一天和最后一天 
	 *  @param num第几季度 
	 */  
	public static String[] getCurrQuarter(int num) {  
	    String[] s = new String[2];  
	    String str = "";  
	    // 设置本年的季  
	    Calendar quarterCalendar = null;  
	    switch (num) {  
	        case 1: // 本年到现在经过了一个季度，在加上前4个季度  
	            quarterCalendar = Calendar.getInstance();  
	            quarterCalendar.set(Calendar.MONTH, 3);  
	            quarterCalendar.set(Calendar.DATE, 1);  
	            quarterCalendar.add(Calendar.DATE, -1);  
	            str = DateHelper.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");  
	            s[0] = str.substring(0, str.length() - 5) + "01-01";  
	            s[1] = str;  
	            break;  
	        case 2: // 本年到现在经过了二个季度，在加上前三个季度  
	            quarterCalendar = Calendar.getInstance();  
	            quarterCalendar.set(Calendar.MONTH, 6);  
	            quarterCalendar.set(Calendar.DATE, 1);  
	            quarterCalendar.add(Calendar.DATE, -1);  
	            str = DateHelper.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");  
	            s[0] = str.substring(0, str.length() - 5) + "04-01";  
	            s[1] = str;  
	            break;  
	        case 3:// 本年到现在经过了三个季度，在加上前二个季度  
	            quarterCalendar = Calendar.getInstance();  
	            quarterCalendar.set(Calendar.MONTH, 9);  
	            quarterCalendar.set(Calendar.DATE, 1);  
	            quarterCalendar.add(Calendar.DATE, -1);  
	            str = DateHelper.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");  
	            s[0] = str.substring(0, str.length() - 5) + "07-01";  
	            s[1] = str;  
	            break;  
	        case 4:// 本年到现在经过了四个季度，在加上前一个季度  
	            quarterCalendar = Calendar.getInstance();  
	            str = DateHelper.formatDate(quarterCalendar.getTime(), "yyyy-MM-dd");  
	            s[0] = str.substring(0, str.length() - 5) + "10-01";  
	            s[1] = str.substring(0, str.length() - 5) + "12-31";  
	            break;  
	    }  
	    return s;  
	} 
	
	
	
	
	
	
	
	

	/**
	 * 格式化Date类型日期为yyyy-MM-dd格式，如果date1为null,返回当天日期。
	 * @param date1 		Date类型日期
	 * @return String		String类型日期(yyyy-MM-dd)
	 */
	public static String getShortDate(Date date1) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = null;
		Date date;
		if (date1 == null) {
			date = Calendar.getInstance().getTime();
		} else {
			date = date1;
		}
		strDate = format.format(date);
		return strDate;
	}

	/**
	 * 日期格式化方法。
	 * @param date		Date类型日期
	 * @return			String类型日期(yyyy-MM-dd HH:MM:SS)
	 */
	public static String convertDateIntoYYYYMMDD_HHCMMCSSStr(Date date) {
		return convertDateIntoDisplayStr(date, DATE_YYYYMMMMDD + " " + TIME_HHCMMCSS);
	}

	/**
	 * 日期格式化方法。
	 * @param date		Date类型日期
	 * @return			String类型日期
	 */
	public static String convertDateIntoYYYYMMDDStr(Date date) {
		return convertDateIntoDisplayStr(date, null);
	}

	/**
	 * 日期格式化方法。
	 * @param date		Date类型日期
	 * @return			String类型日期(yyyy-MM-dd HH:MM)
	 */
	public static String convertDateIntoYYYYMMDD_HHMMStr(Date date) {
		return convertDateIntoDisplayStr(date, DATE_YYYYMMMMDD + " " + TIME_HHCMM);
	}

	/**
	 * 按指定格式格式化日期，如format为null默认按yyyy-MM-dd格式转换。
	 * @param date		Date类型日期
	 * @param format	格式
	 * @return			String类型日期
	 */
	public static String convertDateIntoDisplayStr(Date date, String format) {
		String dateStr = null;
		if (format == null)
			format = DATE_YYYYMMMMDD;
		if (m_dtFormater == null) {
			m_dtFormater = new SimpleDateFormat();
		}
		m_dtFormater.applyPattern(format);
		if (date != null) {
			dateStr = m_dtFormater.format(date);
		}
		return dateStr;
	}

	/**
	 * String格式日期转换Date类型。
	 * @param source		String类型日期(yyyy-MM-dd)
	 * @return				Date类型日期
	 */
	public static Date parseDate(String source) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_YYYYMMMMDD);
		try {
			return format.parse(source);
		} catch (Exception e) {
			return null;
		}
	}

	/**
  	* 取得当前星期的第一天
  	* 
  	* @return
  	*/
    public static String getNowWeekFirstDay(){
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	Date date = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
    	Date first = calendar.getTime();//getDay(calendar.getTime(), 1);
    	if (getDayOfWeek(date)==1){//如果今天是星期日
    		first = getDay(calendar.getTime(), -6);
    	}
    	else{
    		first = getDay(calendar.getTime(), 1);
    	}
    	return sdf.format(first);
    }
    
    
    /**
  	* 取得当前星期的第一天
  	* 
  	* @return
  	*/
    public static Date getCurrentWeekFirstDay(){
    	Date date = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
    	Date first = calendar.getTime();//getDay(calendar.getTime(), 1);
    	if (getDayOfWeek(date)==1){//如果今天是星期日
    		first = getDay(calendar.getTime(), -6);
    	}
    	else{
    		first = getDay(calendar.getTime(), 1);
    	}
    	return first;
    }
    
    /**
  	* 取得所给日期的星期的第一天
  	* 
  	* @return
  	*/
    public static Date getCurrentWeekFirstDay(Date date){
//    	Date date = new Date();
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.DAY_OF_WEEK, calendar.getActualMinimum(Calendar.DAY_OF_WEEK));
    	Date first = calendar.getTime();//getDay(calendar.getTime(), 1);
    	if (getDayOfWeek(date)==1){//如果今天是星期日
    		first = getDay(calendar.getTime(), -6);
    	}
    	else{
    		first = getDay(calendar.getTime(), 1);
    	}
    	return first;
    }
    
    /**
     * 取得当前日期为本周的第几天
     * 
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    
    /**
     * 返回指定日期的前后的i天
     * 
     * @param date
     * @param i
     * @return
     */
    public static Date getDay(Date date, int i){
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.add(Calendar.DAY_OF_YEAR, i);
    	return calendar.getTime();
    }
    
    /**
     * String 转为date型
     */

    public static Date stringtoDate(String stringDate ){
    	if (StringHelper.isEmpty(stringDate)){
    		return new Date();
    	}
    	DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");   
    	Date date = null;
    	try {    
             date = format1.parse(stringDate);               
    	} catch (Exception e) {    
            e.printStackTrace();    
    	}    
        return date;
    }

    //获得第几季度   
    public static int getThisSeasonTime(int month){         
        int season = 1;   
        if(month>=1&&month<=3){   
            season = 1;   
        }   
        if(month>=4&&month<=6){   
            season = 2;   
        }   
        if(month>=7&&month<=9){   
            season = 3;   
        }   
        if(month>=10&&month<=12){   
            season = 4;   
        }   
        return season;
    }
    
    public static String getSeasonStart(int season){
    	String array[][] = {{"01","02","03"},{"04","05","06"},{"07","08","09"},{"10","11","12"}};  
    	
    	String start_month = array[season-1][0];   
    	String year = getCurrentYear();
    	String date = year+"-"+start_month+"-01";
    	return date;
    	
    }
    
    public static String getSeasonend(int season){
    	String array[][] = {{"01","02","03"},{"04","05","06"},{"07","08","09"},{"10","11","12"}};      	
    	String end_month = array[season-1][2];    
    	String year = getCurrentYear();
    	String month = year+"-"+end_month;
    	String lastmonth = monthMove(month,1)+"-01";
    	String enddate = dayMove(lastmonth,-1);
    	return enddate;    	
    }
    
    
    
    
    /** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/** 时分秒HH:mm:ss */
	public static final String dtDay = "HHmmss";

	/**
	 * 返回系统当前时间(精确到毫秒),
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getData() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 返回系统当前时间的前半个小时(精确到毫秒),
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getDatab() {
		long currentTime = System.currentTimeMillis() - 30 * 60 * 1000;
		Date date = new Date(currentTime);
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 
	 * @return 以HHmmss为格式的当前系统时间
	 */
	public static String getImageName() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtDay);
		return df.format(date) + getThree(6);
	}
	
	/**
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getNo() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}

	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		return RandomUtil.createRandom(true, 3);
	}

	/**
	 * 产生随机数 num 为随机数位数
	 * 
	 * @return
	 */
	public static String getThree(int num) {
		if (num < 1) {
			num = 1;
		}
		if (num > 100) {
			num = 100;
		}
		return RandomUtil.createRandom(true, num);
	}

	
	
	
	
	public static final int YEAR_RETURN = 0;  
    
    public static final int MONTH_RETURN = 1;  
      
    public static final int DAY_RETURN = 2;  
      
    public static final int HOUR_RETURN= 3;  
      
    public static final int MINUTE_RETURN = 4;  
      
    public static final int SECOND_RETURN = 5;
	
	
	
	
	public static long getBetween(Date beginTime, Date endTime, String formatPattern, int returnPattern) throws ParseException{  
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatPattern); 
        
        String begin=simpleDateFormat.format(beginTime);
        String end=simpleDateFormat.format(endTime);
        
        Date beginDate = simpleDateFormat.parse(begin);  
        Date endDate = simpleDateFormat.parse(end);  
          
        Calendar beginCalendar = Calendar.getInstance();  
        Calendar endCalendar = Calendar.getInstance();  
        beginCalendar.setTime(beginDate);  
        endCalendar.setTime(endDate);  
        switch (returnPattern) {  
        case YEAR_RETURN:  
            return DateHelper.getByField(beginCalendar, endCalendar, Calendar.YEAR);  
        case MONTH_RETURN:  
            return DateHelper.getByField(beginCalendar, endCalendar, Calendar.YEAR)*12 + DateHelper.getByField(beginCalendar, endCalendar, Calendar.MONTH);  
        case DAY_RETURN:  
            return DateHelper.getTime(beginDate, endDate)/(24*60*60*1000);  
        case HOUR_RETURN:  
            return DateHelper.getTime(beginDate, endDate)/(60*60*1000);  
        case MINUTE_RETURN:  
            return DateHelper.getTime(beginDate, endDate)/(60*1000);  
        case SECOND_RETURN:  
            return DateHelper.getTime(beginDate, endDate)/1000;  
        default:  
            return 0;  
        }  
    } 
	
	private static long getByField(Calendar beginCalendar, Calendar endCalendar, int calendarField){  
        return endCalendar.get(calendarField) - beginCalendar.get(calendarField);  
    }  
      
    private static long getTime(Date beginDate, Date endDate){  
        return endDate.getTime() - beginDate.getTime();  
    }  
      
    /** 
     * 使用参数Format将字符串转为Date 
     */  
    public static Date parse(String strDate, String pattern)  
    {  
    	Date date = null;
    	try {
			if (StringUtils.isBlank(strDate)) {
				date = null;
			}else {
				date = new SimpleDateFormat(pattern).parse(strDate);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return date;  
    }  
	
    public static Date parse(String strDate)  
    {  
    	return parse(strDate, "yyyy-MM-dd HH:mm:ss")  ;  
    }  
    
    
    //获取当前月的天数
    public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}
    
    //获取当前时间是当前月第几周
    public static int getDayOfMonthWeek(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int week = aCalendar.get(Calendar.WEEK_OF_MONTH);
		return week;
	}
    
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(String date) {
    	String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
    	int w=0;
    	try{
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(sdf.parse(date));
	        w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0){
	            w = 0;
	        }
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return weekDays[w];
    }
    
    /** 
     * 用途：以指定的格式格式化日期字符串 
     * @param pattern 字符串的格式 
     * @param currentDate 被格式化日期 
     * @return String 已格式化的日期字符串 
     * @throws NullPointerException 如果参数为空 
     */  
    public static String formatDate(Date currentDate, String pattern){  
        if(currentDate == null || "".equals(pattern) || pattern == null){  
            return null;  
        }  
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);  
        return sdf.format(currentDate);  
    } 
    
    /** 
     * 获取当年的第一天 
     * @param year 
     * @return 
     */  
    public static String getCurrYearFirst(){  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return sdf.format(getYearFirst(currentYear));  
    }  
      
    /** 
     * 获取当年的最后一天 
     * @param year 
     * @return 
     */  
    public static String getCurrYearLast(){  
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar currCal=Calendar.getInstance();    
        int currentYear = currCal.get(Calendar.YEAR);  
        return sdf.format(getYearLast(currentYear));  
    }  
      
    /** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
    
    
    
    /**
     * 得到UTC时间，类型为字符串，格式为"yyyy-MM-dd HH:mm"<br />
     * 如果获取失败，返回null
     * @return
     */
    public static String getUTCTimeStr() {
      StringBuffer UTCTimeBuffer = new StringBuffer();
      // 1、取得本地时间：
      Calendar cal = Calendar.getInstance() ;
      // 2、取得时间偏移量：
      int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
      // 3、取得夏令时差：
      int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
      // 4、从本地时间里扣除这些差量，即可以取得UTC时间：
      cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
      int year = cal.get(Calendar.YEAR);
      int month = cal.get(Calendar.MONTH)+1;
      int day = cal.get(Calendar.DAY_OF_MONTH);
      int hour = cal.get(Calendar.HOUR_OF_DAY);
      int minute = cal.get(Calendar.MINUTE);
      UTCTimeBuffer.append(year).append("-").append(month).append("-").append(day) ;
      UTCTimeBuffer.append(" ").append(hour).append(":").append(minute) ;
      try{
        format.parse(UTCTimeBuffer.toString()) ;
        return UTCTimeBuffer.toString() ;
      }catch(ParseException e)
      {
        e.printStackTrace() ;
      }
      return null ;
    }
    public static String getUTCTime() {
    	// 1、取得本地时间：
    	Calendar cal = Calendar.getInstance() ;
    	// 2、取得时间偏移量：
    	int zoneOffset = cal.get(java.util.Calendar.ZONE_OFFSET);
    	// 3、取得夏令时差：
    	int dstOffset = cal.get(java.util.Calendar.DST_OFFSET);
    	// 4、从本地时间里扣除这些差量，即可以取得UTC时间：
    	cal.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
    	try{
    		return cal.getTimeInMillis()+"";
    	}catch(Exception e)
    	{
    		e.printStackTrace() ;
    	}
    	return null ;
    }
    
    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm") ;
    /**
     * 将UTC时间转换为东八区时间
     * @param UTCTime
     * @return
     */
    public static String getLocalTimeFromUTC(String UTCTime){
      java.util.Date UTCDate = null ;
      String localTimeStr = null ;
      try {
        UTCDate = format.parse(UTCTime);
        format.setTimeZone(TimeZone.getTimeZone("GMT-8")) ;
        localTimeStr = format.format(UTCDate) ;
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return localTimeStr ;
    }
    
    public static String getToday(String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(new Date());
	}
    
    public static String getYesterday(String pattern){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(cal.getTime());
	}
    
    public static Date getYesterday(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}
    

	/**
	 * 描述：获取本周开始时间
	 * @param pattern
	 * @return
	 * @author yanbs
	 * @Date : 2019-05-21
	 */
	public static String getBeginDayOfWeek(String pattern) {
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(new Date());
		  int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
		  if (dayofweek == 1) {
		      dayofweek += 7;
		  }
		  cal.add(Calendar.DATE, 2 - dayofweek);
		  SimpleDateFormat format = new SimpleDateFormat(pattern);
		  return format.format(cal.getTime());
	}
	

	/**
	 * 描述：获取本周结束时间
	 * @param pattern
	 * @return
	 * @author yanbs
	 * @Date : 2019-05-21
	 */
	public static String getEndDayOfWeek(String pattern){
	   Calendar cal = Calendar.getInstance();
	   cal.setTime(formateDate(getBeginDayOfWeek("yyyy-MM-dd HH:mm:ss")));
	   cal.add(Calendar.DAY_OF_WEEK, 6);
	   Date weekEndSta = cal.getTime();
	   SimpleDateFormat format = new SimpleDateFormat(pattern);
	   return format.format(weekEndSta);
	}
	
	public static String getAddDate(int add_num, String pattern) {  
	       Calendar calendar = Calendar.getInstance();  
	       calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + add_num);  
	       Date today = calendar.getTime();  
	       SimpleDateFormat format = new SimpleDateFormat(pattern);  
	       String result = format.format(today);  
	       return result;  
	}
	
	/**
	 * 描述：获取当前日期为本年的第几周
	 * @return
	 * @author yanbs
	 * @Date : 2019-05-22
	 */
	public static int getWeekNumber(){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(new Date());
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	/**
	 * 描述：获取昨天为当前第几周
	 * @return
	 * @author yanbs
	 * @Date : 2019-05-22
	 */
	public static int getWeekNumber_yesterday(){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(DateHelper.getYesterday());
		return calendar.get(Calendar.WEEK_OF_YEAR);
	}
	
	
	public static void main(String[] args) {
		System.out.println(DateHelper.getAddDate(-2, "yyyyMMdd"));
		System.out.println(DateHelper.getWeekNumber_yesterday());
	}
	

}
