package monopoly.util;

import java.util.Calendar;

public class DateUtil {
	
	private final static String dateFormat = "%d-%02d-%02d";  // 可自定义的时间格式，用于前台显示
	private int year, month, day;
	// 当前对象是否被初始化。若为false，则该对象没有保存有效的时间信息。这在诸如order的checkDate属性上很有必要，因为订单并不一定被check
	private boolean initialized;
	private volatile static DateUtil dateUtil;
	private final String[] weekdays = {StrSrc.sunday.getValue(),StrSrc.monday.getValue(),StrSrc.tuesday.getValue(),
			StrSrc.wensday.getValue(), StrSrc.thursday.getValue(), StrSrc.friday.getValue(), StrSrc.saturday.getValue()};
 	
	/** 获得一个表示当前时间的DateUtil对象
	 *  2015/07/21
	 */
	private DateUtil() {
		Calendar now = Calendar.getInstance();
		this.year = now.get(Calendar.YEAR);
		this.month = now.get(Calendar.MONTH) + 1;
		this.day = now.get(Calendar.DATE);
		initialized = true;
	}
	
	/**
	 * get the unique instance
	 * @return
	 * 2016年4月30日 下午2:28:09
	 */
	public static DateUtil getInstance() {
		if (dateUtil == null) {
			synchronized (DateUtil.class) {
				if (dateUtil == null) {
					dateUtil = new DateUtil();
				}
			}
		}
		
		return dateUtil;
	}
	
	/**
	 * set year, month and day
	 * @param year
	 * @param month
	 * @param day
	 * 2016年4月30日 下午2:27:49
	 */
	public void set(int year, int month, int day){
		if (initialized) {
			this.year = year;
			this.month = month;
			this.day = day;
		}
	}
	
	/**
	 * pass n days
	 * 2016/04/20
	 * @param n
	 */
	public void passNDays(int n) {
		Calendar time = this.getCalendar();
		time.add(Calendar.DATE, n);
		this.year = time.get(Calendar.YEAR);
		this.month = time.get(Calendar.MONTH) + 1;
		this.day = time.get(Calendar.DATE);
	}
	
	/** 根据给定的时间格式输出时间字符串 （用于前台显示）
	 *  输出：String；null：当前对象未被初始化
	 *  2015/07/21
	 */
	public String toString() {
		if (initialized) {
			return String.format(dateFormat, year, month, day);
		} else {
			return null;
		}
	}
	
	/** 得到表示当前时间的Calendar对象 （用于时间的比较）
	 *  输出：Calendar
	 *  2015/07/20
	 */
	private Calendar getCalendar() {
		Calendar c = Calendar.getInstance();  // 得到Calendar
		c.clear();  // 清除当前的时间信息
		c.set(year, month - 1, day);  // 赋值为设定的时间
		return c;
	}

	public boolean isInitialized() {
		return initialized;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getDayOfWeek() {
		Calendar c = this.getCalendar();
		return c.get(Calendar.DAY_OF_WEEK);
	}
	
	public String getWeekDayString() {
		int dayOfWeek = getDayOfWeek();
		return weekdays[dayOfWeek-1];
	}
}
