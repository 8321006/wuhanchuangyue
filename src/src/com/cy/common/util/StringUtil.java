package com.cy.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONException;

/**
 * 字符串工具类
 * 
 * @author add by peter_li 20131210
 * 
 */

public class StringUtil
{
	
	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	
	public StringUtil()
	{
	}
	
	public static final String escapeForIntro(String string)
	{
		// String str = escapeHTMLTags(string);
		String str = string;
		str = replace(str, "\r\n", "<br>");
		str = replace(str, "\n", "<br>");
		str = replace(str, "'", "\\'");
		return replace(str, "\r", "");
		
	}
	
	/**
	 * 得到非空的字符串，若字符串对象为null，则返回""。
	 * 
	 * @param objValue Object待转换的原字符串对象
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(Object objValue)
	{
		return (objValue == null ? "" : objValue.toString());
	}
	
	public static String getNotZeroStr(Object objValue)
	{
		return (objValue == null ? "0" : objValue.toString());
	}
	
	/**
	 * 得到非空的字符串，若字符串为null，则返回""。
	 * 
	 * @param strValue String待转换的原字符串
	 * @return String 转换后的字符串
	 * */
	public static String getNotNullStr(String strValue)
	{
		return (strValue == null ? "" : strValue.trim());
	}
	
	/**
	 * 将中文转化成AscII码以便存入数据库
	 * 
	 * @param s 中文字符串
	 * @return 16进制字符串
	 */
	// public static String ChineseStringToAscii(String s)
	// {
	// try
	// {
	// CharToByteConverter toByte = CharToByteConverter.getConverter("GBK");
	// byte[] orig = toByte.convertAll(s.toCharArray());
	// char[] dest = new char[orig.length];
	// for (int i = 0; i < orig.length; i++)
	// dest[i] = (char) (orig[i] & 0xFF);
	// return new String(dest);
	// }
	// catch (Exception e)
	// {
	// System.out.println(e);
	// return s;
	// }
	// }
	// /**
	// * 将UTF-8转化成AscII码以便存入数据库
	// * @param s 中文字符串
	// * @return 16进制字符串
	// */
	// public static String ChineseStringToUTF(String s)
	// {
	// try
	// {
	// CharToByteConverter toByte = CharToByteConverter.getConverter("UTF-8");
	// byte[] orig = toByte.convertAll(s.toCharArray());
	// char[] dest = new char[orig.length];
	// for (int i = 0; i < orig.length; i++)
	// dest[i] = (char) (orig[i] & 0xFF);
	// return new String(dest);
	// }
	// catch (Exception e)
	// {
	// System.out.println(e);
	// return s;
	// }
	// }
	//
	// /**
	// * 将AscII字符转换成汉字
	// * @param s - ASCII字符串
	// * @return 汉字
	// */
	// public static String AsciiToChineseString(String s)
	// {
	// char[] orig = s.toCharArray();
	// byte[] dest = new byte[orig.length];
	// for (int i = 0; i < orig.length; i++)
	// dest[i] = (byte) (orig[i] & 0xFF);
	// try
	// {
	// ByteToCharConverter toChar = ByteToCharConverter.getConverter("GBK");
	// return new String(toChar.convertAll(dest));
	// }
	// catch (Exception e)
	// {
	// System.out.println(e);
	// return s;
	// }
	// }
	
	// /**
	// * 使用正则表达式进行字符串内容替换
	// * @param regularExpression 正则表达式
	// * @param sub 替换的字符串
	// * @param input 要替换的字符串
	// * @return String 替换后的字符串
	// */
	// public static synchronized String regexReplace(String regularExpression,
	// String sub, String input)
	// {
	// Pattern pattern = PatternFactory.getPattern(regularExpression);
	// Matcher matcher = pattern.matcher(input);
	// StringBuffer sb = new StringBuffer();
	// while (matcher.find())
	// {
	// matcher.appendReplacement(sb, sub);
	// }
	// matcher.appendTail(sb);
	// return sb.toString();
	// }
	
	/**
	 * 判断一个字符串中是否包含符合正则表达式定义的匹配条件的子串
	 * 
	 * @param regularExpression - 正则表达式
	 * @param input - 待检查字符串
	 * @return - 若输入字符串中包含符合正则表达式定义的匹配条件的子串，则返回true，否则返回false
	 */
	// //正则表达式匹配判断
	// public static synchronized boolean exist(String regularExpression, String
	// input)
	// {
	// Pattern pattern = PatternFactory.getPattern(regularExpression);
	// Matcher matcher = pattern.matcher(input);
	// return matcher.find();
	// }
	
	/**
	 * 用"0"补足一个字符串到指定长度
	 * 
	 * @param str - 源字符串
	 * @param size - 补足后应达到的长度
	 * @return - 补零后的结果
	 */
	public static String fillZero(String str, int size)
	{
		String result;
		if (str.length() < size)
		{
			char[] s = new char[size - str.length()];
			for (int i = 0; i < (size - str.length()); i++)
			{
				s[i] = '0';
			}
			result = new String(s) + str;
		}
		else
		{
			result = str;
		}
		return result;
	}
	
	/**
	 * 根据字符串（文件类型或者多行输入类型）获取字符串数组
	 * 
	 * @param str1 String 输入字符串
	 * @return String[] 返回结果
	 */
	public static String[] getStrArryByString(String str1)
	{
		if (str1.indexOf("\t") > 0)
		{
			for (int i = 0; i < str1.length(); i++)
			{
				if (str1.substring(i, i + 1).equals("\t"))
				{
					str1 = str1.substring(0, i) + " "
							+ str1.substring(i + 1, str1.length());
				}
			}
		}
		StringTokenizer stringTokenizer = new StringTokenizer(str1, "\r\n");
		String[] strId = new String[stringTokenizer.countTokens()];
		int i = 0;
		while (stringTokenizer.hasMoreTokens())
		{
			strId[i] = stringTokenizer.nextToken();
			i++;
		}
		return strId;
	}
	
	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * 
	 * @param inStr inStr
	 * @return boolean
	 */
	public static boolean isValid(String inStr)
	{
		if (inStr == null)
		{
			return false;
		}
		else if (inStr.equals(""))
		{
			return false;
		}
		else if (inStr.equals("null"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 判断一个字符串是否为 NUll 或为空
	 * 
	 * @param inStr inStr
	 * @return boolean
	 */
	public static boolean checkNotNull(String str)
	{
		boolean flag = false;
		
		if (str != null && str.trim().length() != 0)
			flag = true;
		return flag;
	}
	
	/**
	 * 获得指定长度的空格
	 * 
	 * @param spaceNum spaceNum
	 * @return String
	 */
	public static String getStrSpace(int spaceNum)
	{
		return getStrWithSameElement(spaceNum, " ");
	}
	
	/**
	 * 获得指定长度的字符串
	 * 
	 * @param num int
	 * @param element String
	 * @return String
	 */
	public static String getStrWithSameElement(int num, String element)
	{
		if (num <= 0)
		{
			return "";
		}
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < num; i++)
		{
			sb.append(element);
		}
		return sb.toString();
	}
	
	/**
	 * 从右或左加空格
	 * 
	 * @param strIn String
	 * @param totalLength int
	 * @param isRightAlign boolean
	 * @return String
	 */
	public static String getFillString(String strIn, int totalLength,
			boolean isRightAlign)
	{
		int spaceLength = 0;
		String spaces = null;
		String strOut = null;
		
		if (strIn == null)
		{
			strIn = "";
		}
		
		spaceLength = totalLength - strIn.length();
		
		if (spaceLength < 0)
		{
			spaceLength = 0;
		}
		spaces = StringUtil.getStrSpace(spaceLength);
		
		if (isRightAlign)
		{
			strOut = spaces + strIn;
		}
		else
		{
			strOut = strIn + spaces;
			
		}
		return strOut;
	}
	
	/**
	 * 以String类型返回错误抛出的堆栈信息
	 * 
	 * @param t Throwable
	 * @return String
	 */
	public static String getStackTrace(Throwable t)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		t.printStackTrace(pw);
		return sw.toString();
	}
	
	/**
	 * 转换字符串第一个字符为大写
	 * 
	 * @param str String
	 * @return String
	 */
	public static String getStrByUpperFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toUpperCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}
		
	}
	
	/**
	 * 转换字符串第一个字符为小写
	 * 
	 * @param str String
	 * @return String
	 */
	public static String getStrByLowerFirstChar(String str)
	{
		try
		{
			return str.substring(0, 1).toLowerCase() + str.substring(1);
		}
		catch (Exception e)
		{
			return "";
		}
		
	}
	
	/**
	 * 通过字符串转换成相应的整型，并返回。
	 * 
	 * @param strValue String 待转换的字符串
	 * @return int 转换完成的整型
	 * */
	public static int getStrToInt(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		int iValue = 0;
		try
		{
			iValue = new java.lang.Integer(strValue.trim()).intValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}
	
	/**
	 * 通过字符串转换成相应的DOUBLE，并返回。
	 * 
	 * @param strValue String 待转换的字符串
	 * @return double 转换完成的DOUBLE
	 * */
	public static double getStrToDouble(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		double dValue = 0;
		try
		{
			dValue = Double.parseDouble(strValue.trim());
		}
		catch (Exception ex)
		{
			dValue = 0;
		}
		return dValue;
	}
	
	/**
	 * 通过字符串转换成相应的短整型，并返回。
	 * 
	 * @param strValue String 待转换的字符串
	 * @return short 转换完成的短整型
	 * */
	public static short getStrToShort(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		short iValue = 0;
		try
		{
			iValue = new java.lang.Short(strValue.trim()).shortValue();
		}
		catch (Exception ex)
		{
			iValue = 0;
		}
		return iValue;
	}
	
	/**
	 * 通过字符串转换成相应的长整型，并返回。
	 * 
	 * @param strValue String 待转换的字符串
	 * @return long 转换完成的长整型
	 * */
	public static long getStrToLong(String strValue)
	{
		if (null == strValue)
		{
			return 0;
		}
		long lValue = 0;
		try
		{
			lValue = new java.lang.Long(strValue.trim()).longValue();
		}
		catch (Exception ex)
		{
			lValue = 0;
		}
		return lValue;
	}
	
	public static String toLengthForEn(String str, int length)
	{
		if (null != str)
		{
			if (str.length() <= length)
			{
				return str;
			}
			else
			{
				str = str.substring(0, length - 2);
				str = str + "..";
				return str;
			}
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 降字符串转换成给定长度的字符串，如超出的话截断，并在最后以两个点结尾
	 * 
	 * @param str String
	 * @param length int
	 * @return String
	 */
	public static String toLengthForIntroduce(String str, int length)
	{
		str = delTag(str);
		
		byte[] strByte = str.getBytes();
		int byteLength = strByte.length;
		char[] charArray;
		StringBuffer buff = new StringBuffer();
		if (byteLength > (length * 2))
		{
			charArray = str.toCharArray();
			int resultLength = 0;
			for (int i = 0; i < charArray.length; i++)
			{
				resultLength += String.valueOf(charArray[i]).getBytes().length;
				if (resultLength > (length * 2))
				{
					break;
				}
				buff.append(charArray[i]);
				
			}
			buff.append("..");
			str = buff.toString();
		}
		
		// str = replace(str, "'", "\\'");
		str = replace(str, "\"", "\\\"");
		str = replace(str, "，", ",");
		return str;
		
	}
	
	public static String delTag(String str)
	{
		str = str + "<>";
		StringBuffer buff = new StringBuffer();
		int start = 0;
		int end = 0;
		
		while (str.length() > 0)
		{
			start = str.indexOf("<");
			end = str.indexOf(">");
			if (start > 0)
			{
				buff.append(str.substring(0, start));
			}
			if (end > 0 && end <= str.length())
			{
				str = str.substring(end + 1, str.length());
			}
			else
			{
				str = "";
			}
			
		}
		String result = buff.toString();
		
		while (result.startsWith(" "))
		{
			
			result = result.substring(result.indexOf(" ") + 1, result.length());
			
		}
		return result;
		
	}
	
	public static final String replace(String line, String oldString,
			String newString)
	{
		if (line == null)
		{
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0)
		{
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0)
			{
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
		
	}
	
	// Replace
	public static String Replace(String source, String oldString,
			String newString)
	{
		if (source == null)
		{
			return null;
		}
		StringBuffer output = new StringBuffer();
		int lengOfsource = source.length();
		int lengOfold = oldString.length();
		int posStart = 0;
		int pos;
		while ((pos = source.indexOf(oldString, posStart)) >= 0)
		{
			output.append(source.substring(posStart, pos));
			output.append(newString);
			posStart = pos + lengOfold;
		}
		if (posStart < lengOfsource)
		{
			output.append(source.substring(posStart));
		}
		return output.toString();
	}
	
	// 此函数前台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtml(String s)
	{
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		s = Replace(s, "\n", "<br>");
		// s = Replace(s, " ", "&nbsp;");
		s = Replace(s, "'", "&#39;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "%", "％");
		// s = Replace(s, "&", "&amp;");
		return s;
	}
	
	// 逆
	public static String unHtml(String s)
	{
		
		// s = Replace(s, "&lt;", "<");
		// s = Replace(s, "&gt;", ">");
		// s = Replace(s, "    ", "\t");
		// s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		// s = Replace(s, "&nbsp;", " ");
		// s = Replace(s, "&amp;", "&");
		// s = Replace(s, "&#39;", "'");
		// s = Replace(s, "&#92;", "\\");
		// s = Replace(s, "％", "%");
		return s;
	}
	
	// 此函数后台使用中，请勿随便修改，不然会造成显示混乱(以前修改版本在下方注释中)
	public static String toHtmlBack(String s)
	{
		// 显示
		s = Replace(s, "&", "&amp;");
		s = Replace(s, "\\", "&#92;");
		s = Replace(s, "\"", "&quot;");
		s = Replace(s, "<", "&lt;");
		s = Replace(s, ">", "&gt;");
		s = Replace(s, "\t", "    ");
		s = Replace(s, "\r\n", "\n");
		// s = Replace(s, "\n", "<br>");
		// s = Replace(s, " ", "&nbsp;");
		// s = Replace(s, "'", "&#39;");
		// s = Replace(s, "%", "%");
		
		return s;
	}
	
	// 逆
	public static String unHtmlBack(String s)
	{
		s = Replace(s, "&lt;", "<");
		s = Replace(s, "&gt;", ">");
		s = Replace(s, "    ", "\t");
		s = Replace(s, "\n", "\r\n");
		s = Replace(s, "<br>", "\n");
		s = Replace(s, "&nbsp;", " ");
		s = Replace(s, "&amp;", "&");
		s = Replace(s, "&#39;", "'");
		s = Replace(s, "&#92;", "\\");
		s = Replace(s, "％", "%");
		return s;
	}
	
	/*
	 * public static String toHtml(String s) { //显示 s = Replace(s, "&",
	 * "&amp;"); s = Replace(s, "\\", "&#92;"); s = Replace(s, "\"", "&quot;");
	 * s = Replace(s, "<", "&lt;"); s = Replace(s, ">", "&gt;"); s = Replace(s,
	 * "\t", "    "); s = Replace(s, "\r\n", "\n"); // s = Replace(s, "\n",
	 * "<br>"); s = Replace(s, " ", "&nbsp;"); // s = Replace(s, "'", "&#39;");
	 * // s = Replace(s, "%", "%");
	 * 
	 * return s; }
	 * 
	 * public static String unHtml(String s) { s = Replace(s, "&lt;", "<"); s =
	 * Replace(s, "&gt;", ">"); s = Replace(s, "    ", "\t"); s = Replace(s,
	 * "\n", "\r\n"); s = Replace(s, "<br>", "\n"); s = Replace(s, "&nbsp;",
	 * " "); s = Replace(s, "&amp;", "&"); s = Replace(s, "&#39;", "'"); s =
	 * Replace(s, "&#92;", "\\"); s = Replace(s, "％", "%"); return s; }
	 */
	// 判断是否含有中文，如果含有中文返回ture
	public static boolean containsChinese(String str)
		throws UnsupportedEncodingException
	{
		
		if (str.length() < (str.getBytes()).length)
			return true;
		
		return false;
		
		// for (int i = 0; i < username.length(); i++) {
		// String unit=Character.toString(username.charAt(i));
		// byte[] unitByte=unit.getBytes("GBK");
		// // ((unitByte[0]+256)*256 + (unitByte[1]+256)) <= 0xFEFE)
		// if (unitByte.length == 2)
		// {
		// return true;
		// }
		// }
		// return false;
		
	}
	
	// public static boolean isEmpty(String str)
	// {
	// if (str == null)
	// return true;
	// return "".equals(str.trim());
	// }
	
	public static String[] split(String str1, String str2)
	{
		return org.apache.commons.lang.StringUtils.split(str1, str2);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>将字符串转成list<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Oct 28, 2013 <br>
	 * 
	 * @param exp 分割符 如","
	 * @param value
	 * @return
	 */
	public static List<String> StringToList(String value, String exp)
	{
		List<String> resultList = new ArrayList<String>();
		String[] vals = split(value, exp);
		for (String val : vals)
		{
			resultList.add(val);
		}
		return resultList;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Jul 30, 2013 <br>
	 * 
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(String[] arrs)
	{
		StringBuffer result = new StringBuffer("");
		if (arrs != null && arrs.length > 0)
		{
			for (int i = 0; i < arrs.length; i++)
			{
				
				if (!result.toString().equals(""))
				{
					result.append(",");
				}
				if (arrs[i] != null && !"".equals(arrs[i].trim()))
				{
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>数字转换成字符串<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Jul 30, 2013 <br>
	 * 
	 * @param arrs
	 * @return
	 */
	public static String arrayToString(Object[] arrs)
	{
		StringBuffer result = new StringBuffer("");
		if (arrs != null && arrs.length > 0)
		{
			for (int i = 0; i < arrs.length; i++)
			{
				
				if (!result.toString().equals(""))
				{
					result.append(",");
				}
				if (arrs[i] != null && !"".equals(arrs[i].toString().trim()))
				{
					result.append(arrs[i]);
				}
			}
		}
		return result.toString();
	}
	
	public static String left(String str, int length)
	{
		return org.apache.commons.lang.StringUtils.left(str, length);
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>替换回车<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Oct 26, 2013 <br>
	 * 
	 * @param str
	 * @return
	 */
	public static String replaceHuiche(String str)
	{
		String after = str.replaceAll("\r\n", "");
		return after;
	}
	
	/**
	 * 根据输入的长度截取字符串，单个单词超过输入长度的强制加<BR>
	 * 换行
	 * 
	 * @param str 输入的字符串
	 * @param len 输入的长度
	 * @return 处理过后的字符串
	 */
	public static String truncateStr(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str))
		{
			
			String strs[] = str.split(" ");
			StringBuffer buff = new StringBuffer();
			if (strs.length > 0)
			{
				for (int i = 0; i < strs.length; i++)
				{
					StringBuffer temp = new StringBuffer();
					while (strs[i].length() > len)
					{
						temp.append(strs[i].substring(0, len) + "<BR>");
						strs[i] = strs[i].substring(len);
					}
					temp.append(strs[i]);
					buff.append(temp.toString() + " ");
				}
				
			}
			return buff.toString();
		}
		else
		{
			return "";
		}
	}
	
	public static String truncateStr2(String str, int len)
	{
		if (str != null && !("").equalsIgnoreCase(str) && len != 0)
		{
			String strs[] = str.split(" ");
			
			StringBuffer buff = new StringBuffer();
			for (int i = 0; i < strs.length; i++)
			{
				StringBuffer temp = new StringBuffer();
				String tempstr = "";
				while (strs[i].length() > len)
				{
					tempstr = strs[i].substring(0, len);
					tempstr = tempstr.replaceAll(" ", "&nbsp; ");
					tempstr = tempstr.replaceAll("<", "&lt; ");
					tempstr = tempstr.replaceAll("\n", "<br> ")
							.replaceAll("\"", "&quot; ")
							.replaceAll("'", "&#39; ");
					tempstr = tempstr + "<br>";
					temp.append(tempstr);
					
					strs[i] = strs[i].substring(len);
				}
				tempstr = strs[i];
				tempstr = tempstr.replaceAll(" ", "&nbsp; ");
				tempstr = tempstr.replaceAll("<", "&lt; ");
				tempstr = tempstr.replaceAll("\n", "<br> ")
						.replaceAll("\"", "&quot; ").replaceAll("'", "&#39; ");
				
				temp.append(tempstr);
				buff.append(temp.toString() + " ");
			}
			
			if (buff.length() > 0)
				return buff.toString().substring(0, buff.length() - 1);
			else
				return str;
		}
		else
		{
			return "";
		}
	}
	
	/**
	 * 编码转换，从unicode转换为GBK
	 * 
	 * @param str 输入字符串
	 * @return str编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String unicodeToGB(String l_S_Source)
		throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("GBK");
			l_S_Desc = new String(l_b_Proc, "ISO8859_1");
		}
		return l_S_Desc;
	}
	
	/**
	 * 编码转换，从GBK转换为unicode
	 * 
	 * @param str 输入字符串
	 * @return str 编码转换后的字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String GBToUnicode(String l_S_Source)
		throws UnsupportedEncodingException
	{
		String l_S_Desc = "";
		if (l_S_Source != null && !l_S_Source.trim().equals(""))
		{
			byte l_b_Proc[] = l_S_Source.getBytes("ISO8859_1");
			l_S_Desc = new String(l_b_Proc, "GBK");
		}
		return l_S_Desc;
	}
	
	/**
	 * Escapes a <code>String</code> according the JavaScript string literal
	 * escaping rules. The resulting string will not be quoted.
	 * 
	 * <p>
	 * It escapes both <tt>'</tt> and <tt>"</tt>. In additional it escapes
	 * <tt>></tt> as <tt>\></tt> (to avoid <tt>&lt;/script></tt>). Furthermore,
	 * all characters under UCS code point 0x20, that has no dedicated escape
	 * sequence in JavaScript language, will be replaced with hexadecimal escape
	 * (<tt>\x<i>XX</i></tt>).
	 */
	public static String javaScriptStringEnc(String s)
	{
		int ln = s.length();
		for (int i = 0; i < ln; i++)
		{
			char c = s.charAt(i);
			if (c == '"' || c == '\'' || c == '\\' || c == '>' || c < 0x20)
			{
				StringBuffer b = new StringBuffer(ln + 4);
				b.append(s.substring(0, i));
				while (true)
				{
					if (c == '"')
					{
						b.append("\\\"");
					}
					else if (c == '\'')
					{
						b.append("\\'");
					}
					else if (c == '\\')
					{
						b.append("\\\\");
					}
					else if (c == '>')
					{
						b.append("\\>");
					}
					else if (c < 0x20)
					{
						if (c == '\n')
						{
							b.append("\\n");
						}
						else if (c == '\r')
						{
							b.append("\\r");
						}
						else if (c == '\f')
						{
							b.append("\\f");
						}
						else if (c == '\b')
						{
							b.append("\\b");
						}
						else if (c == '\t')
						{
							b.append("\\t");
						}
						else
						{
							b.append("\\x");
							int x = c / 0x10;
							b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
							x = c & 0xF;
							b.append((char) (x < 0xA ? x + '0' : x - 0xA + 'A'));
						}
					}
					else
					{
						b.append(c);
					}
					i++;
					if (i >= ln)
					{
						return b.toString();
					}
					c = s.charAt(i);
				}
			} // if has to be escaped
		} // for each characters
		return s;
	}
	
	private static StringUtil instance = null;
	
	public static synchronized StringUtil getInstance()
	{
		if (instance == null)
		{
			instance = new StringUtil();
		}
		return instance;
	}
	
	/**
	 * 将多个连续空格替换为一个,"a  b"-->"a b"
	 * 
	 * @param src
	 * @return
	 * @author WilliamLau
	 * @desc
	 */
	public static String trimContinuousSpace(String src)
	{
		return src.replaceAll("\\s+", " ");
	}
	
	public static String replace(String src, String target, String rWith,
			int maxCount)
	{
		return org.apache.commons.lang.StringUtils.replace(src, target, rWith,
				maxCount);
	}
	
	public static boolean equals(String str1, String str2)
	{
		return org.apache.commons.lang.StringUtils.equals(str1, str2);
	}
	
	public static boolean isAlphanumeric(String str)
	{
		return org.apache.commons.lang.StringUtils.isAlphanumeric(str);
	}
	
	public static boolean isNumeric(String str)
	{
		return org.apache.commons.lang.StringUtils.isNumeric(str);
	}
	
	public static String[] stripAll(String[] strs)
	{
		return org.apache.commons.lang.StringUtils.stripAll(strs);
	}
	
	// public static void main(String[] args)
	// {
	// System.out.println(wcsUnescape("#lt;strong#gt;#lt;span style=#quot;color:#e53333;#quot;#gt;1111111111#lt;/span#gt;#lt;/strong#gt;"));
	// // String testStr = "<input > &    \\r\\n    \\n", newStr;
	// // newStr = toHtml(testStr);
	// // System.out.println(testStr);
	// // System.out.println(newStr);
	// // System.out.println(unHtml(newStr));
	// // String aaa = "中文中文中文bcdefghijk";
	//
	// // String bbb = toLengthForIntroduce(aaa,5);
	// // System.out.println(bbb);
	// // Object aa = null;
	// // String bb = "  aaaa  ";
	//
	// try
	// {
	// // System.out.println(getNotNullStr(aa));
	// // System.out.println(getNotNullStr(bb));
	// // System.out.println(containsChinese(aaa));
	// // System.out.println(containsChinese(aaa));
	// // System.out.println(containsChinese(bb));
	// //String abc = null;
	// //System.out.println(toLengthForEn(abc, 3));
	// /*String num = "05";
	// if(num.indexOf(".")==-1){
	// num = num + ".00";
	// }*/
	// // String str="<p >ksdkks </br> </p>    jkskskeeee <div>  lllll </div>";
	// // System.out.println(delTag(str));
	// //System.out.println(toFloatNumber("5.2"));
	// }
	// catch (Exception e)
	// {
	// // TODO: handle exception
	// e.printStackTrace();
	// }
	//
	// }
	//
	public static String toFloatNumber(String num)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		nf.setMinimumFractionDigits(2);
		return nf.format(Double.parseDouble(num));
	}
	
	public static String toFloatNumber(Double num, int accuracy)
	{
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(accuracy);
		nf.setMinimumFractionDigits(accuracy);
		return nf.format(num);
	}
	
	public static String wcsUnescape(String str)
	{
		str = str.replace("#lt;", "<");
		str = str.replace("#gt;", ">");
		str = str.replace("#quot;", "\"");
		str = str.replace("#amp;amp;", "&");
		str = str.replace("#amp;", "&");
		str = str.replace("#039;", "'");
		return str;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>返回string型的字节数<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Sep 2, 2013 <br>
	 * 
	 * @param str
	 * @return
	 */
	public static int getByteLength(String str)
	{
		if (str == null)
		{
			return 0;
		}
		return str.getBytes().length;
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>详细的功能描述<br>
	 * <b>作者：</b>www.jeecg.org<br>
	 * <b>日期：</b> Sep 2, 2013 <br>
	 * 
	 * @param str 字符
	 * @param limitLen 长度
	 * @return
	 */
	public static String getByteStr(String str, int limitLen)
	{
		StringBuffer sb = new StringBuffer();
		char[] chars = getNotNullStr(str).toCharArray();
		int len = 0;
		for (char c : chars)
		{
			len += getByteLength(String.valueOf(c));
			if (len <= limitLen)
			{
				sb.append(c);
			}
		}
		return sb.toString();
		
	}
	
	/**
	 * 把16进制字符串转化为字节数组
	 * 
	 * @param hex
	 * @return
	 */
	public static byte[] hexToBytes(String hex)
	{
		byte[] buffer = new byte[hex.length() / 2];
		String strByte;
		
		for (int i = 0; i < buffer.length; i++)
		{
			strByte = hex.substring(i * 2, i * 2 + 2);
			buffer[i] = (byte) Integer.parseInt(strByte, 16);
		}
		
		return buffer;
	}
	
	/**
	 * 获取指定字符串的MD5编码
	 * 
	 * @param original 字符串
	 * @return MD5编码
	 */
	public static String MD5Encode(String original)
	{
		String ret = null;
		
		try
		{
			ret = new String(original);
			MessageDigest md = MessageDigest.getInstance("MD5");
			ret = byteArrayToHexString(md.digest(ret.getBytes()));
		}
		catch (Exception ex)
		{
			// empty
		}
		
		return ret;
	}
	
	/**
	 * 转换字节数组为16进制字符串
	 * 
	 * @param b 字节数组
	 * @return 16进制字符串
	 */
	public static String byteArrayToHexString(byte[] b)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
		{
			sb.append(byteToHexString(b[i]));
		}
		
		return sb.toString().toUpperCase();
	}
	
	/**
	 * 转换字节数为16进制字符串
	 * 
	 * @param b byte数值
	 * @return 16进制字符串
	 */
	public static String byteToHexString(byte b)
	{
		int n = b;
		if (n < 0)
		{
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		
		return hexDigits[d1] + hexDigits[d2];
	}
	
	/**
	 * @param content 内容
	 * @param length 指定长度。 超出这个长度就截取字符串。
	 * @param padding 超出长度后，尾加上字符，如"..."，可以为空
	 * @return 返回结果 如果内容没有超出指定的长度。则返回原字符串，超出长度后则截取到指定的长度的内容
	 */
	public static String subStr(String content, Integer length, String padding)
		throws UnsupportedEncodingException
	{
		String str = "";
		int paddSize = StringUtils.isBlank(padding) ? 0 : padding.length();
		// 如果内容为空，或者小于指定的长度。则返回原内容
		if (StringUtils.isBlank(content) || content.length() <= length)
		{
			return content;
		}
		str = content.substring(0, length - paddSize);
		if (StringUtils.isNotBlank(padding))
		{
			str += padding;
		}
		return str;
	}
	
	/**
	 * 生成指定范围的随机数字
	 * 
	 * @param from
	 * @param to
	 * @return
	 */
	public static int randomInt(int from, int to)
	{
		return from + new Random().nextInt(to - from);
	}
	
	/**
	 * 生成指定长度的随机数字
	 * 
	 * @param length 不限制长度
	 * @return
	 */
	public static String randomStr(int length)
	{
		StringBuffer sb = new StringBuffer();
		// 生成随机数字串
		for (int i = 0; i < length; i++)
		{
			sb.append(randomInt(0, 10));
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成指定长度的随机数字，并将数字转化为ascii
	 * 
	 * @param length 不限制长度
	 * @return ascii字符串
	 */
	public static String randomStr2Ascii(int length)
	{
		StringBuffer sb = new StringBuffer();
		
		char[] chars = randomStr(length).toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			sb.append((int) chars[i]);
		}
		
		return sb.toString();
	}
	
	/**
	 * 生成指定长度的随机数字，并将数字转化为ascii
	 * 
	 * @param length 1-8位
	 * @return ascii字符串
	 */
	public static String generalStringToAscii(int length)
	{
		
		int num = 1;
		for (int i = 0; i < length; i++)
		{
			num *= 10;
		}
		
		Random rand = new Random((new Date()).getTime());
		String strRandom = Integer.toString(rand.nextInt(num));
		
		strRandom = StringUtils.leftPad(strRandom, length, '0');
		
		StringBuffer sb = new StringBuffer();
		char[] chars = strRandom.toCharArray();
		for (int i = 0; i < chars.length; i++)
		{
			sb.append((int) chars[i]);
		}
		
		return sb.toString();
	}
	
	/**
	 * 判断字符是否为空
	 * 
	 * @param str 某字符串
	 * @return 为null或为空串则返回true，否则返回false
	 */
	public static boolean isEmpty(String str)
	{
		return str == null || str.length() == 0;
	}
	
	
	public static boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
	public static String resolveLoginAccount(String account)
	{
		/*
		 * if (!"0123456789".contains(account.substring(0, 1))) { // 非数字开头的为账号
		 * // 是否需要账号加密 user.setLoginAccount(encryptFiled(account)); } else if
		 * (account.length() == 11) { // 为电话号码 user.setPhone(account); } else {
		 * // 擎动号 user.setQdId(account); }
		 */
		// 手机号码
		String regEx = "^[0-9]{11}$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(account);
		boolean rs = mat.find();
		
		// 验证是不是USERID
		String regEx1 = "^[0-9]{20}$";
		Pattern pat1 = Pattern.compile(regEx1);
		Matcher mat1 = pat1.matcher(account);
		boolean rs_userID = mat1.find();
		
		//
		
		if (rs)
		{ // 为电话号码
			return "PHONE";
		}
		else if (rs_userID)
		{
			return "PUC_UID";
		}
		// 如果不为纯数字，则为ACCOUNT
		else
		{
			return "LOGIN_NAME";
		}
		
	}
	
	public static String resolveLoginAccount2(String account)
	{
		/*
		 * if (!"0123456789".contains(account.substring(0, 1))) { // 非数字开头的为账号
		 * // 是否需要账号加密 user.setLoginAccount(encryptFiled(account)); } else if
		 * (account.length() == 11) { // 为电话号码 user.setPhone(account); } else {
		 * // 擎动号 user.setQdId(account); }
		 */
		// 手机号码
		String regEx = "^[0-9]{11}$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(account);
		boolean rs = mat.find();
		
		// 验证是不是USERID
		String regEx1 = "^[0-9]{20}$";
		Pattern pat1 = Pattern.compile(regEx1);
		Matcher mat1 = pat1.matcher(account);
		boolean rs_userID = mat1.find();
		
		//
		
		if (rs)
		{ // 为电话号码
			return "cu.PHONE";
		}
		else if (rs_userID)
		{
			return "cu.PUC_UID";
		}
		// 如果不为纯数字，则为ACCOUNT
		else
		{
			return "cu.LOGIN_NAME";
		}
		
	}
	
	/**
	 * 获取支付JSON
	 * 
	 * @param channelType
	 * @param transMoney
	 * @param merchantId
	 * @param merchantName
	 * @param thirdOrderNo
	 * @param transCode
	 * @param payType
	 * @param orderType
	 * @param mallId
	 * @param appId
	 * @param serviceId
	 * @param serviceName
	 * @param deviceTypeId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String payJson(Map param)
	{
		Map map = new HashMap<String, Object>();
		map.put("channelType",
				StringUtil.getNotNullStr(param.get("channelType")));
		if ("0006".equals(StringUtil.getNotNullStr(param.get("channelType"))))
		{
			map.put("certType", StringUtil.getNotNullStr(param.get("certType")));
			map.put("certNo", StringUtil.getNotNullStr(param.get("certNo")));
			map.put("mainAccount",
					StringUtil.getNotNullStr(param.get("mainAccount")));
		}
		
		if ("0007".equals(StringUtil.getNotNullStr(param.get("channelType"))))
		{
			map.put("certType", StringUtil.getNotNullStr(param.get("certType")));
			map.put("certNo", StringUtil.getNotNullStr(param.get("certNo")));
			map.put("mainAccount",
					StringUtil.getNotNullStr(param.get("mainAccount")));
			map.put("payAccount",
					StringUtil.getNotNullStr(param.get("payAccount")));
		}
		
		// 非接交易
		if ("0008".equals(StringUtil.getNotNullStr(param.get("channelType"))))
		{
			map.put("mainAccount",
					StringUtil.getNotNullStr(param.get("mainAccount")));
		}
		
		map.put("transMoney", StringUtil.getNotNullStr(param.get("transMoney"))
				+ "");
		map.put("merchantId", StringUtil.getNotNullStr(param.get("merchantId")));
		map.put("merchantName",
				StringUtil.getNotNullStr(param.get("merchantName")));
		map.put("userRealName",
				StringUtil.getNotNullStr(param.get("userRealName")));
		map.put("payType", StringUtil.getNotNullStr(param.get("payType")));
		map.put("thirdOrderNo",
				StringUtil.getNotNullStr(param.get("thirdOrderNo")));
		map.put("mallId", StringUtil.getNotNullStr(param.get("mallId")));
		map.put("appCode", StringUtil.getNotNullStr(param.get("appId")));
		
		if (!"".equals(StringUtil.getNotNullStr(param.get("transCode"))))
		{
			map.put("transCode",
					StringUtil.getNotNullStr(param.get("transCode")));
		}
		
		map.put("transTime", DateUtil.getFullTime());
		map.put("SERVICE_ID", StringUtil.getNotNullStr(param.get("serviceId")));
		map.put("SERVICE_NAME",
				StringUtil.getNotNullStr(param.get("serviceName")));
		map.put("IS_USER_PAY_FEE",
				StringUtil.getNotNullStr(param.get("standDebit")));// 是否用户承担手续费
		map.put("DEBIT_OR_CREDIT_CARD",
				StringUtil.getNotNullStr(param.get("cardBin")));// 是否支持多费率
		
		if (StringUtils.isNotEmpty(StringUtil.getNotNullStr(param
				.get("deviceTypeId"))))
		{
			String[] deviceTypeArray = StringUtil.getNotNullStr(
					param.get("deviceTypeId")).split(",");
			List deviceList = new ArrayList();
			for (String deviceType : deviceTypeArray)
			{
				if (StringUtils.isEmpty(deviceType))
				{
					continue;
				}
				Map deviceMap = new HashMap();
				deviceMap.put("deviceType", deviceType);
				deviceList.add(deviceMap);
			}
			
			map.put("deviceTypeList", deviceList);
		}
		
		if ("9".equals(StringUtil.getNotNullStr(param.get("orderType")))
				|| "10".equals(StringUtil.getNotNullStr(param.get("orderType")))
				|| "11".equals(StringUtil.getNotNullStr(param.get("orderType")))
				|| "50".equals(StringUtil.getNotNullStr(param.get("orderType"))))
		{
			map.put("voucherType", "10");
		}
		else
		{
			map.put("voucherType", "00");
		}
		
		// 增加白名单功能.如果用户存在白名单.则需要把白名单传递给底座.否则默认为全部卡都支持
		if (param.get("whiteList") != null)
		{
			map.put("whiteList", param.get("whiteList"));
		}
		
		return JSONObject.fromObject(map).toString();
	}
	
	/**
	 * 支付json
	 * 
	 * @param channelType 通道类型
	 * @param transMoney 交易金额
	 * @param merchantId 商户Id
	 * @param thirdOrderNo 订单id
	 * @param transCode 交易代码 路由出的pkid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String payJsonNew(String channelType, Long transMoney,
			String merchantId, String thirdOrderNo, String transCode,
			String payType, String mallId, String appId)
	{
		Map map = new HashMap<String, Object>();
		map.put("channelType", channelType);
		map.put("transMoney", transMoney);
		map.put("merchantId", merchantId);
		map.put("payType", payType);
		map.put("thirdOrderNo", thirdOrderNo);
		map.put("mallId", mallId);
		map.put("appCode", appId);
		map.put("transCode", transCode);
		map.put("transTime", DateUtil.getFullTime());
		
		try
		{
			return com.cy.common.util.json.JSONUtil.toJSONString(map);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 收款json
	 * 
	 * @param channelType
	 * @param transMoney
	 * @param merchantId
	 * @param thirdOrderNo
	 * @param transCode
	 * @param payType
	 * @param orderType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String gatherJson(String channelType, Long transMoney,
			String merchantId, String thirdOrderNo, String transCode,
			String payType, String orderType, String mallId, String appId)
	{
		Map map = new HashMap<String, Object>();
		map.put("channelType", channelType);
		map.put("transMoney", transMoney);
		map.put("merchantId", merchantId);
		map.put("payType", payType);
		map.put("thirdOrderNo", thirdOrderNo);
		map.put("mallId", mallId);
		map.put("appCode", appId);
		map.put("transCode", transCode);
		map.put("transTime", DateUtil.getFullTime());
		if ("9".equals(orderType) || "10".equals(orderType))
		{
			map.put("voucherType", "10");
		}
		else
		{
			map.put("voucherType", "00");
		}
		
		try
		{
			return com.cy.common.util.json.JSONUtil.toJSONString(map);
		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 得到payrouteids
	 * 
	 * @param orderType
	 * @return 1:付款单历史交易记录 2：采购单历史交易记录 3：货品签收 4：送货单 5：采购单应付账单 6：付款单应付账单 7:采购模块
	 *         8：快速付款 9:快速收款 10:应收款 11:卡卡转账 14:恒丰转账 15：非接交易 22:定向付款
	 */
	public static String getPayRouteIds(String orderType)
	{
		if ("1".equals(orderType) || "5".equals(orderType)
				|| "6".equals(orderType) || "22".equals(orderType)
				|| "8".equals(orderType) || "14".equals(orderType))
		{// 付款
			return "0000,0001,0003,0005,0008";
		}
		else if ("11".equals(orderType))
		{// 转账
			return "0006";
		}
		else if ("12".equals(orderType) || "13".equals(orderType))
		{// 快速转账 //通付宝
			return "0007";
		}
		else if ("9".equals(orderType) || "10".equals(orderType))
		{// 收款
			return "0002";
		}
		else if ("15".equals(orderType))
		{
			return "0008";
		}
		else if ("21".equals(orderType))
		{
			return "0009";
		}
		else
		{
			return "0000,0001,0003,0005,0002,0008";
		}
	}
	
	public static String encodeStr(String str)
	{
		try
		{
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//当前页数
		public static int  pageIndexvalue(int noticetype,int pageIndex,int flag) {
			//课程通知被选中
			if(noticetype==1){
				if(flag==1){
					return pageIndex;
				}
				else{
					return 1;
				}
			 }
			//系统通知被选中
			if(noticetype==2){
				if(flag==2){
					return pageIndex;
				}
				else{
					return 1;
				}
		 }
			//全部通知被选中
			if(noticetype==3){
				if(flag==3){
					return pageIndex;
				}
				else{
					return 1;
				}
			 }
			return pageIndex;
	
		}
}
