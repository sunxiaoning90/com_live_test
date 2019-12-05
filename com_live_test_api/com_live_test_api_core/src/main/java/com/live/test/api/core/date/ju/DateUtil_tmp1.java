package com.live.test.api.core.date.ju;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;



public class DateUtil_tmp1 {
	private static Logger logger = LogManager.getLogger(DateUtil_tmp1.class);
	/** 格式：MM-dd HH:mm */
	public static final DateFormat FORMAT_MD_HM = new SimpleDateFormat("MM-dd HH:mm");
	/** 格式：yyyyMMdd */
	public static final DateFormat FORMAT_YMD = new SimpleDateFormat("yyyyMMdd");
	/** 格式：yyyy-MM-dd */
	public static final DateFormat FORMAT_Y_M_D = new SimpleDateFormat("yyyy-MM-dd");
	/** 格式：yyyyMMddHHmmss */
	public static final DateFormat FORMATER_YMDHMS = new SimpleDateFormat("yyyyMMddHHmmss");
	/** 格式：yyyy-MM-dd HH:mm:ss */
	public static final DateFormat FORMATER_YMD_HMS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/** 格式: EEEE*/
	public static final DateFormat EEEE = new SimpleDateFormat("EEEE");
	/** 格式: yyMMddHHmmsss*/
	public static final DateFormat FORMATER_YYMDHMSSS = new SimpleDateFormat("yyMMddHHmmsss");
	
	/**
	 * 将String转换成Date,支持格式：yyyyMMdd，yyyy-MM-dd，yyyyMMddHHmmss，yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static Date parse(String date) {
		Date result = null;
		if (StringUtils.isNotBlank(date)) {
			date = date.trim();
			try {
				switch (date.length()) {
					case 8:
						result = FORMAT_YMD.parse(date);
						break;
					case 10:
						result = FORMAT_Y_M_D.parse(date);
						break;
					case 14:
						result = FORMATER_YMDHMS.parse(date);
						break;
					default:
						result = FORMATER_YMD_HMS.parse(date);
						break;
				}
			} catch (ParseException e) {
				logger.error(MarkerManager.getMarker("core.platform"),"DateUtil.parse","错误信息:{}",e);
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 将String转换成Date：格式：yyyyMMdd，yyyy-MM-dd
	 * 
	 * @return
	 */
	public static Date parseYMDHMS(String date) {
		Date result = null;
		if (StringUtils.isNotBlank(date)) {
			date = date.trim();
			try {
				if (date.contains("-")) {
					result = FORMATER_YMD_HMS.parse(date);
				} else {
					result = FORMATER_YMDHMS.parse(date);
				}
			} catch (ParseException e) {
				logger.error(MarkerManager.getMarker("core.platform"),"DateUtil.parseYMDHMS","错误信息:{}",e);
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 格式化日期到月日时分
	 * 
	 * @param time
	 * @return
	 */
	public static String format_MDHm(Date date) {
		return date == null ? null : FORMAT_MD_HM.format(date);
	}

	/**
	 * 格式化日期到年月日:yyyyMMdd
	 * 
	 * @param date
	 * @return
	 */
	public static String formatYMD(Date date) {
		return date == null ? null : FORMAT_YMD.format(date);
	}

	/**
	 * 时间转换成yyyy-MM-dd
	 * 
	 * @param date
	 * @return
	 */
	public static String format_YMD(Date date) {
		return date == null ? null : FORMAT_Y_M_D.format(date);
	}

	/**
	 * 格式化日期到年月日时分
	 * 
	 * @param time
	 * @return
	 */
	public static String formatYMDHms(Date date) {
		return date == null ? null : FORMATER_YMDHMS.format(date);
	}
	
	/**
	 * 格式化日期到年月日时分毫秒
	 * @param data
	 * @return
	 */
	public static String formatYMDHMss(Date date){
		return date == null ? null: FORMATER_YYMDHMSSS.format(date);
	}

	/**
	 * 格式化日期到年月日时分秒
	 * 
	 * @param time
	 * @return
	 */
	public static String format_YMDHms(Date date) {
		return date == null ? null : FORMATER_YMD_HMS.format(date);
	}

	/**
	 * 相差天数
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	public static int compareDate(Date fDate, Date oDate) {
		// 计算
		long time = Math.abs(fDate.getTime() - oDate.getTime());
		int date = (int) Math.floor(time / (24 * 3600 * 1000));
		if (fDate.after(oDate))
			return -date;
		return date;
	}
	
	/**
	 * 相差分钟
	 * 
	 * @param fDate
	 * @param oDate
	 * @return
	 */
	@SuppressWarnings("null")
	public static long compareDate(Object  fDate, Object  oDate) {
		Date date1 = null;
		Date date2 = null;
		if(fDate.getClass()==String.class){
			 try {
				date1 = FORMATER_YMD_HMS.parse(String.valueOf(fDate));
			} catch (ParseException e) {
				logger.error(MarkerManager.getMarker("core.platform"),"DateUtil.compareDate","错误信息:{}",e);
				e.printStackTrace();
			}
		}
		if(oDate.getClass() == String.class){
			 try {
				date2 = FORMATER_YMD_HMS.parse(String.valueOf(oDate));
			} catch (ParseException e) {
				logger.error(MarkerManager.getMarker("core.platform"),"DateUtil.compareDate","错误信息:{}",e);
				e.printStackTrace();
			}
		}
		if(fDate.getClass()==Date.class){
			date1 = (Date)fDate;
		}
		if(oDate.getClass()==Date.class){
			date2 = (Date)oDate;
		}
		if(date1!=null&&date2!=null){
		    long l = date1.getTime() - date2.getTime(); 
		    long day = l / (24 * 60 * 60 * 1000);  
	        long hour = (l / (60 * 60 * 1000) - day * 24);  
	        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60); 
	        if(day>0){
	        	return day*60+hour*60+min;
	        }else if(hour>0){
	        	return hour*60+min;
	        }else{
	        	return min;
	        }
	        
		}
		return (Long) null;
	}
	
	/** 
	 * 取得指定日期所在周的第一天 
	 * 
	 * @param date 
	 * @return 
	 */ 
	 public static Date getFirstDayOfWeek(Date date) { 
	 Calendar c = new GregorianCalendar(); 
	 c.setFirstDayOfWeek(Calendar.MONDAY); 
	 c.setTime(date); 
	 c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday 
	 return c.getTime (); 
	 }
	 /** 
	  * 取得指定日期所在周的最后一天 
	  * 
	  * @param date 
	  * @return 
	  */ 
	  public static Date getLastDayOfWeek(Date date) { 
	  Calendar c = new GregorianCalendar(); 
	  c.setFirstDayOfWeek(Calendar.MONDAY); 
	  c.setTime(date); 
	  c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday 
	  return c.getTime(); 
	  } 

	
	  /**
	   * 取得指定日期是周几
	   * @param date
	   * @return String
	   */
	  public static String getWeek(Date date){
		  return EEEE.format(date);
	  }
	  
	  
	  /**
	   * 取得指定日期是周几
	   * @param date
	   * @return String
	   */
	  public static String getWeekDate(Date date){
		    String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	        if (w < 0)
	            w = 0;
	        return weekDays[w];
	  }
	  
	  /**
	   * 取得当前时间的前几天
	   * @param day
	   * @return
	   */
	  public static Date inWeekData(int day){
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(new Date());
		  cal.add(Calendar.DAY_OF_WEEK_IN_MONTH, -day);
		  return  cal.getTime();
	  }
	  
	  public static Date getTodayStart() {
		  Calendar cal = Calendar.getInstance();
		  cal.set(Calendar.HOUR_OF_DAY,0);
		  cal.set(Calendar.MINUTE,0);
		  cal.set(Calendar.SECOND,0);
		  return cal.getTime();
		  
	  }
	  
	  
	  public static Date getTodayEnd() {
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(getTodayStart());
		  cal.add(Calendar.DAY_OF_MONTH, 1);
		  cal.add(Calendar.SECOND, -1);
		  return cal.getTime();
	  }
	  
	  // 获得本月第一天0点时间
	    public static Date getTimesMonthmorning() {
	        Calendar cal = Calendar.getInstance();
	        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	        return cal.getTime();
	    }
	    // 获得本月最后一天24点时间
	    public static Date getTimesMonthnight() {
	        Calendar cal = Calendar.getInstance();
	        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
	        cal.set(Calendar.HOUR_OF_DAY, 24);
	        return cal.getTime();
	    }
	    
	    // 获得当天0点时间
	    public static Date getTimesmorning() {
	        Calendar cal = Calendar.getInstance();
	        cal.set(Calendar.HOUR_OF_DAY, 0);
	        cal.set(Calendar.SECOND, 0);
	        cal.set(Calendar.MINUTE, 0);
	        cal.set(Calendar.MILLISECOND, 0);
	        return cal.getTime();
	    }
	    // 获得明天0点时间
	    public static Date getTimesTomorrow() {
	    	Calendar cal=Calendar.getInstance();
	    	cal.add(Calendar.DATE,1);//这里改为1
	        return cal.getTime();
	    }
	  
	  public static void main(String [] args) {
		  
		 System.out.println(getTimesMonthmorning());
		 System.out.println(getTimesMonthnight());
	  }
	  
}
