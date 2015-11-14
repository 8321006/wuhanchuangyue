package com.cy.web;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.CourseClass;
import com.cy.model.MyCourse;
import com.cy.model.Notice;
import com.cy.model.UniversityCourse;
import com.cy.model.User;
import com.cy.service.CourseService;
import com.cy.service.ImportService;
import com.cy.service.UniversityCourseService;
import com.cy.service.UniversityService;
import com.cy.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * Spring MVC Controler - 表：t_message
 * @since 2015-06-08 16:37:51
 */
@Controller
@RequestMapping(value = "/cy")
public class ImportAction {
	private static final Logger logger = LoggerFactory.getLogger(ImportAction.class);

	@Autowired
	private ImportService importService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private UniversityCourseService universityCourseService;
	
	@Autowired
	private UserService userService;

	/**
	 * 新增页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/xkdr",method = RequestMethod.POST)
	public Object dr(HttpServletRequest request,Model model,
            HttpServletResponse response) throws ServiceException {
		System.out.println("===============================================================");
		//messageService.insert(message);
		/**判断文件后缀是 xls还是xlsx文件
		if(fileName.toLowerCase().endsWith("xls")){ 
			return new HSSFWorkbook(is);  
			} 
		if(fileName.toLowerCase().endsWith("xlsx")){  
			return new XSSFWorkbook(is); 
			} 
			**/
		 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		 String message=importService.insertusers(multipartRequest,request);
		 model.addAttribute("message", message);
		 return "redirect:/cy/userImportList.action";
	}
	
	@RequestMapping(value = "/userImportList")
	public String userimportList(HttpServletRequest request,Model model,String message,Integer pageIndex,HttpServletResponse response) throws ServiceException, UnsupportedEncodingException {
		 if(null!=message)
			{
			 message=new String(message.getBytes("iso-8859-1"), "UTF-8");
		    }
		 if(pageIndex == null){
				pageIndex = 1;
			}
			PageHelper.startPage(pageIndex, Constant.pageSize);
			List<UniversityCourse> courselist=universityCourseService.findAllCourse();
			PageInfo pager = new PageInfo(courselist);
			model.addAttribute("page", pager);
		model.addAttribute("courselist",courselist);
		model.addAttribute("message",message);
		 return "jsp/system/admin_addstudent";
	 }
}