package com.cy.web;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.common.util.CommonFunc;
import com.cy.exception.ServiceException;
import com.cy.model.UniversityCourse;
import com.cy.service.MyCourseService;
import com.cy.service.UniversityCourseService;


/**
 * Spring MVC Controler - 表：t_message
 * @since 2015-06-08 16:37:51
 */
@Controller
@RequestMapping(value = "/cy")
public class CalendarAction {
	private static final Logger logger = LoggerFactory.getLogger(CalendarAction.class);

	/**
	@Autowired
	private CourseService courseService;

	@Autowired
	private PaperService paperService;
	**/
	
	@Autowired
	private MyCourseService myCourseService;
	
	@Autowired
	private UniversityCourseService universityCourseService;
	 
	
	/** 因需求变更，这些代码现在完全不符合业务功能规则了  所以全部注释  重新写逻辑了
	@RequestMapping(value="/finddate",method=RequestMethod.POST)
	@ResponseBody
	public List<Course> findcourse(@RequestParam("date") String date,HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException, ParseException{
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<=====================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+date);
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");	
		Date date2=sim.parse(date);
		

		//此处方法是获取点击当前时间的时候获取点击当前时间的前一天
		Calendar c = Calendar.getInstance(); 
		Date date3=null; 
		try { 
		date3 = sim.parse(date); 
		} catch (ParseException e) { 
		e.printStackTrace(); 
		} 
		c.setTime(date3); 
		int day=c.get(Calendar.DATE); 
		c.set(Calendar.DATE,day+1); 
		String dayBefore=sim.format(c.getTime()); 
		System.out.println("当前日期的前一天："+dayBefore);

		
		
		List<Course> listCourse=courseService.findByxsl(date2);
		model.put("listCourse", listCourse);		
		return listCourse;
	}
	
	@RequestMapping(value="/findpaper",method=RequestMethod.POST)
	@ResponseBody
	public List<Paper> findpaper(@RequestParam("date") String date,HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException, ParseException{
		System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<=====================>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+date);
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");	
		Date date2=sim.parse(date);
		List<Paper> listpaper=paperService.findpaper(date2);
		model.put("listpaper", listpaper);		
		return listpaper;
	}
	**/
	
	

	@RequestMapping(value="/findcourseid",method=RequestMethod.POST)
	@ResponseBody
	public List<Map> findcourseid(HttpServletResponse response,
			HttpServletRequest request) throws ServiceException, ParseException{
		String subcourseIdlist="";
		List<String> courseIdlist=myCourseService.findcourseid(CommonFunc.getUserLogin(request).getUserId());
		/**
		for(int i=0;i<courseIdlist.size();i++)
		{
			subcourseIdlist=subcourseIdlist+"'"+courseIdlist.get(i).toString()+"',";
		}
		subcourseIdlist=subcourseIdlist.substring(0,subcourseIdlist.length()-1);
		System.out.println(subcourseIdlist);
		**/
		List<Map> listDate=new ArrayList<Map>();
		List<UniversityCourse> UniversityCourseList=universityCourseService.finduniversityCourseDate(courseIdlist);
		
		//此处方法是获取点击当前时间的时候获取点击当前时间的前一天
		SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance(); 
		
		if(CommonFunc.getUserLogin(request).getUserType()==0)
		{
		for(int i=0;i<UniversityCourseList.size();i++)
			{
				Map map=(Map) UniversityCourseList.get(i);
				Date startdate=(Date) map.get("courseStartTime");
				Integer panltotal=(Integer) map.get("plantotal");
				String chaptername=(String) map.get("chaptername");
				System.out.println(startdate+"     "+panltotal+"       "+chaptername);
				
				Date date3=null; 
				String date2=null;
				try { 
				date2 = sim.format(startdate); 
				date3 = sim.parse(date2); 
				
				} catch (ParseException e) { 
				e.printStackTrace(); 
				} 
				c.setTime(date3); 
				int day=c.get(Calendar.DATE); 
				c.set(Calendar.DATE,day+panltotal-1); 
				String dayBefore=sim.format(c.getTime()); 
				System.out.println("教学计划后的日期为："+dayBefore);
				
				Map dqMap=new HashMap();
				dqMap.put("chaptername", chaptername);
				dqMap.put("dayBefore", dayBefore);
				
				listDate.add(dqMap);
			}
		}
		else
		{
			for(int i=0;i<UniversityCourseList.size();i++)
			{
				Map map=(Map) UniversityCourseList.get(i);
				Date startdate=(Date) map.get("courseStartTime");
				Integer panltotal=(Integer) map.get("plantotal");
				String chaptername=(String) map.get("chaptername");
				System.out.println(startdate+"     "+panltotal+"       "+chaptername);
				
				Date date3=null; 
				String date2=null;
				try { 
				date2 = sim.format(startdate); 
				date3 = sim.parse(date2); 
				
				} catch (ParseException e) { 
				e.printStackTrace(); 
				} 
				c.setTime(date3); 
				int day=c.get(Calendar.DATE); 
				c.set(Calendar.DATE,day+panltotal+1); 
				String dayBefore=sim.format(c.getTime()); 
				System.out.println("教学计划后的日期为："+dayBefore);
				
				Map dqMap=new HashMap();
				dqMap.put("chaptername", chaptername);
				dqMap.put("dayBefore", dayBefore);
				
				listDate.add(dqMap);
			}
		}
		
		
		System.out.println(listDate.size());
		System.out.println(UniversityCourseList.size());

		return listDate;
	}
 










}