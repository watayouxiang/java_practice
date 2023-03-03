package com.watayouxiang.myjava.clazz;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Demo_Date {

	public static void main(String[] args) {
		test_Date();
		test_SimpleDateFormat();
		test_Calendar();
	}

	private static void test_Calendar() {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar);
		printCalendar(calendar);

		// 月份和星期都是从0开始的，所以8代表9月。
		calendar.set(2010, 8, 1);
		// 2010年9月1日星期三
		printCalendar(calendar);

		// 9月加4个月，所以是1月。
		calendar.add(Calendar.MONTH, 4);
		// 2011年1月1日星期六
		printCalendar(calendar);

		// 1日加32天，所以是2月2日
		calendar.add(Calendar.DAY_OF_MONTH, 32);
		// 2011年2月2日星期三
		printCalendar(calendar);
	}

	private static void printCalendar(Calendar calendar) {
		int year = calendar.get(Calendar.YEAR);
		int month = (calendar.get(Calendar.MONTH) + 1);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		String[] weeks = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		// 有些地区以星期日作为一周的第一天，需要根据本地化设置的不同而确定是否需要 “-1”
		String week = weeks[calendar.get(Calendar.DAY_OF_WEEK) - 1];

		System.out.println(year + "年" + month + "月" + day + "日" + week);
	}

	private static void test_SimpleDateFormat() {
		// 日期格式参阅 java.text.SimpleDateFormat 中的表格
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 E HH:mm:ss", Locale.getDefault());
		String time = sdf.format(new Date());
		System.out.println(time);
	}

	private static void test_Date() {
		Date date = new Date();
		// Fri Mar 29 16:41:12 CST 2019
		System.out.println(date);
	}

}
