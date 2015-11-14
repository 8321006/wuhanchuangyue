package com.cy.common.util;


public abstract class Constant {
    
    public static String SPLIT_TAG = ",";
	/**
	 * 字符串分隔符_尖角(^)
	 */
	public static String SPLIT_JIANJAO = "^";
	/**
	 * 字符串分隔符_竖杠(|)
	 */
	public static String SPLIT_SHU = "|";
	/**
	 * 特殊字符_星号(*)
	 */
	public static String XING ="*";
	
    public static String TEMPLATE = "view/standard/html/";
	
	public static String WAP_MENU = "wapMenu";
	
	public static int studentrole = 0;
	
	public static int teacherrole = 1;
	
	public static int pageSize = 10;
	
	public static String USER_UNIQUE = "496559bbf1";
	
	public static String SECRET_KEY = "5e8a9c2cbfcfdee062154802ad5b4cf9";
	
	/**
	 * 终端请求参数头定义
	 * @author Administrator
	 *
	 */
	public static interface IOKeys{
		String ACTION_NAME = "ACTION_NAME";
		String APP_SP = "APP_SP";
		String ACTION_INFO = "ACTION_INFO";
		String ACTION_INVOKER = "ACTION_INVOKER";
		String ACTION_MODULE = "ACTION_MODULE";
		String ACTION_RETURN_CODE = "ACTION_RETURN_CODE";
		String ACTION_RETURN_TIPS = "ACTION_RETURN_TIPS";
		String RESPONSECODE = "RESPONSECODE";
		String ACTION_RETURN_MESSAGE = "ACTION_RETURN_MESSAGE";
		
	}
	
    /**
     * 字符
     * 
     */
    public static interface Charset {
        /**
         * GBK字符
         */
        String GBK = "GBK";

        /**
         * UTF-8字符
         */
        String UTF8 = "UTF-8";

        /**
         * 默认字符
         */
        String DEFAULT = UTF8;
    }

    /**
     * 页面字符
     */
    public static interface ContentType {
        String GBK = "text/xml; charset=GBK";
        String UTF8 = "text/xml; charset=UTF-8";
        String DEFAULT = UTF8;
    }
    
    public static String strSmsUrl = "http://www.stongnet.com/sdkhttp/sendsms.aspx";
    public static String strReg = "101100-WEB-HUAX-845867";   //注册号（由华兴软通提供）
    public static String strPwd = "JKQMHKQK";                 //密码（由华兴软通提供）
    public static String strSourceAdd = "";                   //子通道号，可为空（预留参数一般为空）
	
}
