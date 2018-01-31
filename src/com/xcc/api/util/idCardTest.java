/**
 * 
 */
package com.xcc.api.util;

/**
 *  Administrator
 *
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class idCardTest {

	public static boolean testID_Card(String str1){
		//测试是否为合法的身份证号码
		/* String[] str={
                "130681198712092019",
                "13068119871209201x",
                "130681198711209201",
        };*/
		//准备正则表达式（身份证有15位和18位两种），最后一位可能为字母
		String regex="(\\d{14}\\w)|\\d{17}\\w";
		//准备开始匹配，判断所有输入是否正确
		Pattern regular=Pattern.compile(regex);//创建匹配的规则Pattern
		StringBuilder sb=new StringBuilder();
		//遍历所有要匹配的字符串
		Matcher matcher=regular.matcher(str1);
		sb.append("身份证：");
		sb.append(str1);
		System.out.println(sb.toString());
		sb.delete(0, sb.length());
		getBirthDay(str1);
		return matcher.matches();

	}
	private static void getBirthDay(String str){
		System.out.println("准备开始获取出生日期：");
		//准备验证规则
		Pattern BirthDayRegular=Pattern.compile("(\\d{6})(\\d{8})(.*)");
		//.*连在一起的意思  任意数量的不包含换行的字符
		Pattern YearMonthDayRegular=Pattern.compile("(\\d{4})(\\d{2})(\\d{2})");

		Matcher matcher=BirthDayRegular.matcher(str);
		if(matcher.matches()){
			Matcher matcher2=YearMonthDayRegular.matcher(matcher.group(2));
			if(matcher2.matches()){
				System.out.println(str+"中的出生年份分解为"+matcher2.group(1)
				+"年"+matcher2.group(2)+"月"+matcher2.group(3));
			}
		}
	}
	public static boolean isMobileNO(String mobiles){  

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  

		Matcher m = p.matcher(mobiles);  

	//	System.out.println(m.matches()+"---");  

		return m.matches();  

	}  

}