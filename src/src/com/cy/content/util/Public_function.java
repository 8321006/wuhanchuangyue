package com.cy.content.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.BaseFont;

public class Public_function {
	
	/**
	 * 删除目录中的所有文件及目录名字
	 * @param filePath
	 */
	public static void delFolderByFile(String filePath) {
		try 
		{
			String tempparh = filePath.substring(0,filePath.lastIndexOf("\\"));
			File file = new File(tempparh);
			File[] tempFile = file.listFiles();
			for (int i = 0; i < tempFile.length; i++) {	
				String lastEditDate = uf_getDate(tempFile[i].lastModified(),"yyyyMMdd",2);
				String currentDate = uf_getDate("yyyyMMdd");
				if(!lastEditDate.equals(currentDate))tempFile[i].delete();
				if (tempFile[i].isFile()) {
					System.out.println("File : " + tempFile[i].getName());
				}
				if (tempFile[i].isDirectory()) {
					System.out.println("Directory : " + tempFile[i].getName());
				}
			}
		}catch(Exception ex) { 
			System.out.println("Delete Folder at File is error:"+ex); 
		}
	}
	
	
	
	// 判断日期是否为空
	public static String dataStyle(Date infodate, String format) {
		// TODO Auto-generated method stub
		try {
			if (infodate == null || infodate.equals("")) {
				return "";
			} else {
				SimpleDateFormat dateformat = new SimpleDateFormat(format);
				return dateformat.format(infodate);
			}
		} catch (Exception ee) {
			ee.printStackTrace();
			return null;
		}
	}
//	 判断日期是否为空
	public static String dataStyle(Date infodate) {
		// TODO Auto-generated method stub
		try {
			if (infodate == null || infodate.equals("")) {
				return "";
			} else {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				return dateformat.format(infodate);
			}
		} catch (Exception ee) {
			return dataStyle(infodate, "yyyy-MM-dd");
		}
	}

	// 判断日期是否为空
	public static String dataStyle(String infodate, String format) {
		// TODO Auto-generated method stub
		try {
			if (infodate == null || infodate.trim().equals("")) {
				return "";
			} else {
				SimpleDateFormat dateformat = new SimpleDateFormat(format);
				Date aa = to_Date(infodate, format);
				String temp = dateformat.format(aa);
				return temp;
			}
		} catch (Exception ee) {
			ee.printStackTrace();
			return "";
		}
	}
//	 判断日期是否为空
	public static String dataStyle(String infodate) {
		// TODO Auto-generated method stub
		try {
			if (isNull(infodate).trim().equals("")) {
				return "";
			} else {
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String temp = dateformat.format(dateformat.parse(infodate));
				return temp;
			}
		} catch (Exception ee) {
			return dataStyle(infodate, "yyyy-MM-dd");
		}
	}

	// 判断日期是否为空,如果不为空取分和秒
	public static String getDatamm(String infodate) {
		// TODO Auto-generated method stub
		try {
			if (infodate == null || infodate.trim().equals("")) {
				return "";
			} else {
				String temp = infodate.substring(14);
				return temp;
			}
		} catch (Exception ee) {
			ee.printStackTrace();
			return "";
		}
	}

	// 判断是否为空，是返回“”
	public static String isNull(Object value) {
		// TODO Auto-generated method stub
		if (value == null) {
			return "";
		}
		return value.toString().trim();
	}
//	 判断日期是否为空，是返回“”
	public static String isNullD(Object value) {
		// TODO Auto-generated method stub
		if (value == null) {
			return "";
		}
		return dataStyle(value.toString().trim());
	}
//	 判断日期是否为空，是返回“”
	public static String isNullD(Object value,String format) {
		// TODO Auto-generated method stub
		if (value == null) {
			return "";
		}
		return dataStyle(value.toString().trim(),format);
	}

	// 获取当前的星期
	public static int uf_getDay() {
		Calendar myDate = Calendar.getInstance();
		return myDate.get(myDate.DAY_OF_WEEK) - 1;
	}

	// 获取当前的日期
	public static String uf_getDate(String format) {
		String ss;
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				format);
		java.util.Date myDate = new java.util.Date();
		ss = dateFormat.format(myDate);
		return ss;

	}
//	 获取当前的日期
	public static java.util.Date uf_getDate() {
		String ss;
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		java.util.Date myDate = new java.util.Date();
		ss = dateFormat.format(myDate);
		return to_Date(ss,"yyyy-MM-dd HH:mm:ss");

	}
	// 字符串转换成日期
	public static java.util.Date to_Date(Object obj, String format) {
		try {
			if (obj == null)
				return null;
			java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
					format);
			return dateFormat.parse(obj.toString());
		} catch (Exception e) {
			// e.printStackTrace();
			return null;
		}

	}

	// 获取当前的日期，并对时进行增量操作
	public static String uf_getDate(String format, int inc) {
		String ss;
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				format);
		java.util.Calendar myDate = java.util.Calendar.getInstance();
		myDate.add(java.util.Calendar.DAY_OF_MONTH, inc);
		ss = dateFormat.format(myDate.getTime());
		return ss;

	}
	
	// 将时间格式化，并对时进行增量操作
	public static String uf_getDate(long scrDate, String format, int inc) {
		String ss;
		java.text.SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(
				format);
		java.util.Calendar myDate = java.util.Calendar.getInstance();
		myDate.setTime(new Date(scrDate));		
		myDate.add(java.util.Calendar.DATE, inc);
		ss = dateFormat.format(myDate.getTime());
		return ss;

	}

	/**
	 * @author 简单的加密解密实现(还可以用一种复杂的基于随机数生成密文的实现）
	 * 
	 * 
	 */
	private static byte[] bytes;

	private static String encoding;// 加密后的字符串

	private static String decoding;// 解密后的原字符串

	private static int COUNT = 10000;// 随机生成0到9999的整数

	/**
	 * 加密
	 * 
	 * @param xx
	 *            要加密的字符串
	 * @return 加密后的字符串
	 * @throws Exception
	 */
	public static String toEncode(String xx) throws Exception {
		try {
			// Random rand=new Random();
			// int randnumber=rand.nextInt(COUNT);
			// String numberString2=String.valueOf(rand.nextInt(COUNT));
			// String
			// randString=String.valueOf(randnumber)+xx.trim()+";"+numberString2;
			// if(randnumber<10)
			// randString="000"+randString;
			// else if (randnumber<100)
			// randString="00"+randString;
			// else if(randnumber<1000)
			// randString="0"+randString;

			// 20060301
			String randString = xx.trim();

			bytes = randString.getBytes("utf-8");// string to byte
			encoding = new sun.misc.BASE64Encoder().encode(bytes); // byte to
			// ascii
			//System.out.println("转换之后的密码"+encoding);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return encoding;
	}

	/**
	 * 解密
	 * 
	 * @param yy
	 *            待解密的字符串
	 * @return 解密后的原字符串
	 * @throws Exception
	 */
	public static String toDecode(String yy){
		try {
			bytes = new sun.misc.BASE64Decoder().decodeBuffer(yy.trim());// ascii
			// to
			// byte
			decoding = new String(bytes, "utf-8");// byte to string
			//System.out.println("转换之后的密码"+decoding);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		// String[] s=decoding.split(";");
		// return s[0].substring(4);
		return decoding;

	}
	public static String getProperty(String fileName,String keyName){
  	    Properties properties = new InitPropLoader().LoadPropertie(fileName);  		
	    return properties.getProperty(keyName);
   }
}
class InitPropLoader{ 	
    public Properties LoadPropertie(String fileName){
    	Properties properties=new Properties();
    	InputStream in = null;    
//    	String source = new InitNomalPropLoader().getHome()+"\\"+fileName;
//    	String source = "/"+fileName;
        try {
//	      in = getClass().getResourceAsStream(soucse);
//	      properties.load(new FileInputStream(source));
	      in = getClass().getResourceAsStream("/"+fileName);
	      properties.load(in);
	    }
	    catch (Exception e) {
	      System.err.println("Error reading  properties!");
//	      e.printStackTrace();	      
	    }
	    finally {
	      try {
	        if (in != null) {
	          in.close();
	        }
	      }catch (Exception e) {}
	    }
	    return properties;
    }
}



