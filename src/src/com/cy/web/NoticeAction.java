package com.cy.web;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
















import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.Constant;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Notice;
import com.cy.model.NoticeWithBLOBs;
import com.cy.model.Test;
import com.cy.model.UniversityCourse;
import com.cy.service.MessageService;
import com.cy.service.NoticeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


/**
 * Spring MVC Controler - 表：t_message
 * @since 2015-06-08 16:37:51
 */
@Controller
@RequestMapping(value = "/notice")
public class NoticeAction {
	private static final Logger logger = LoggerFactory.getLogger(NoticeAction.class);

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/noticisyslist")
	public String gocenter(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,HttpServletRequest httpServletRequest,Integer pageIndex) throws ServiceException {
		System.out.println("------------------------------------------------------------------");
		if(pageIndex == null){
			pageIndex = 1;
		}
		int noticeType=1;
		if(CommonFunc.getUserLogin(httpServletRequest).getUserType()==3){
			 noticeType=2;	
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		List<Notice> noticesysAlllist=noticeService.findNoticeCourse(CommonFunc.getUserLogin(request).getUniversityId(), noticeType);
		PageInfo pager = new PageInfo(noticesysAlllist);
		model.put("page", pager);
		model.put("noticesysAlllist", noticesysAlllist);
		return "jsp/admin/noticesystem";
	}
	
	@RequestMapping(value = "/noticeadd", method=RequestMethod.POST)
	public String noticeadd(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,HttpServletRequest httpServletRequest,NoticeWithBLOBs noticewithblobs,Integer pageIndex) throws ServiceException {
		if(pageIndex == null){
			pageIndex = 1;
		}
		int noticeType=1;
		if(CommonFunc.getUserLogin(httpServletRequest).getUserType()==3){
			 noticeType=2;	
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		noticewithblobs.setNoticeId(MD5.toMd5(UID.nextUid()));
		Date sysdate=new Date(); 
		noticewithblobs.setNoticeTime(sysdate);
		noticewithblobs.setNoticetype(noticeType);
		noticewithblobs.setUniversityId(CommonFunc.getUserLogin(request).getUniversityId());
		System.out.println("============================================================================"+noticewithblobs.getNoticeTitle());
		noticeService.insertNotice(noticewithblobs);
		List<Notice> noticesysAlllist=noticeService.findNoticeCourse(CommonFunc.getUserLogin(request).getUniversityId(),noticeType);
		PageInfo pager = new PageInfo(noticesysAlllist);
		model.put("page", pager);
		model.put("noticesysAlllist", noticesysAlllist);
		return "jsp/admin/noticesystem";
	}
	
	@RequestMapping(value = "/findlikenotice")
	public String findlikenotice(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,Integer pageIndex) throws ServiceException, UnsupportedEncodingException {
		String noticetitle=request.getParameter("noticett");		
		noticetitle = URLDecoder.decode(noticetitle,"UTF-8");
		if(noticetitle.equals("请输入要搜索的关键字"))
		{
			noticetitle="";
		}
		System.out.println(noticetitle);
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		List<Notice> findlikenotice=noticeService.findlikenotice(noticetitle,CommonFunc.getUserLogin(request).getUniversityId());
		PageInfo pager = new PageInfo(findlikenotice);
		model.put("page", pager);
		model.put("noticesysAlllist", findlikenotice);
		return "jsp/admin/noticesystem";
	}
	
	@RequestMapping(value = "/delNotice")
	public String delnotice(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,int noticeType,Integer pageIndex) throws ServiceException {
		System.out.println("------------------------------------------------------------------");
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		String noticeid=request.getParameter("noticeid");
		noticeService.delNotice(noticeid);
		List<Notice> noticesysAlllist=noticeService.findNoticeCourse(CommonFunc.getUserLogin(request).getUniversityId(),noticeType);
		PageInfo pager = new PageInfo(noticesysAlllist);
		model.put("page", pager);
		model.put("noticesysAlllist", noticesysAlllist);
		return "jsp/admin/noticesystem";
	}

	
	//平台系统管理员方法
	@RequestMapping(value = "/gosystemNotice")
	public String gosystemNotice(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,Integer pageIndex) throws ServiceException {
		System.out.println("------------------------------------------------------------------");
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
 		List<Notice> noticesystemAlllist=noticeService.findcyadminNoticeAll(CommonFunc.getUserLogin(request).getUniversityId(),2);
		PageInfo pager = new PageInfo(noticesystemAlllist);
		model.put("page", pager);
		model.put("noticesystemAlllist", noticesystemAlllist);
		return "jsp/system/admin_notice";
	}
	
	//系统平台管理员的添加新闻公告的方法
	@RequestMapping(value = "/noticesystemadd", method=RequestMethod.POST)
	public String noticesystemadd(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,NoticeWithBLOBs noticewithblobs,Integer pageIndex) throws ServiceException {
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		noticewithblobs.setNoticeId(MD5.toMd5(UID.nextUid()));
		Date sysdate=new Date(); 
		noticewithblobs.setNoticeTime(sysdate);
		noticewithblobs.setNoticetype(2);
		noticewithblobs.setUniversityId(CommonFunc.getUserLogin(request).getUniversityId());
		System.out.println("============================================================================"+noticewithblobs.getNoticeTitle());
		noticeService.insertsysNotice(noticewithblobs);
		List<Notice> noticesystemAlllist=noticeService.findcyadminNoticeAll(CommonFunc.getUserLogin(request).getUniversityId(),2);
		PageInfo pager = new PageInfo(noticesystemAlllist);
		model.put("page", pager);
		model.put("noticesystemAlllist", noticesystemAlllist);
		return "jsp/system/admin_notice";
	}
	
	//平台管理员的模糊查询的方法，之所以全部分开写，是为了后面项目的扩展性
	@RequestMapping(value = "/findlikesystemnotice")
	public String findlikesystemnotice(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,Integer pageIndex) throws ServiceException, UnsupportedEncodingException {
		String noticetitle=request.getParameter("noticett");		
		noticetitle = URLDecoder.decode(noticetitle,"UTF-8");
		if(noticetitle.equals("请输入要搜索的关键字"))
		{
			noticetitle="";
		}
		
		System.out.println(noticetitle);
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		List<Notice> findlikenotice=noticeService.findlikesystemnotice(noticetitle,CommonFunc.getUserLogin(request).getUniversityId());
		PageInfo pager = new PageInfo(findlikenotice);
		model.put("page", pager);
		model.put("noticesystemAlllist", findlikenotice);
		return "jsp/system/admin_notice";
	}
	//删除新闻通知
	@RequestMapping(value = "/delsysnotice")
	public String delsysnotice(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,Integer pageIndex) throws ServiceException {
		System.out.println("------------------------------------------------------------------");
		String noticeid=request.getParameter("noticeid");
		noticeService.delNotice(noticeid);
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		List<Notice> noticesystemAlllist=noticeService.findcyadminNoticeAll(CommonFunc.getUserLogin(request).getUniversityId(),2);
		PageInfo pager = new PageInfo(noticesystemAlllist);
		model.put("page", pager);
		model.put("noticesystemAlllist", noticesystemAlllist);
		return "jsp/system/admin_notice";
	}
	
  //平台后台查看新闻通知
	@RequestMapping(value = "/deitsysnotice")
	public String deitsysnotice(HttpServletResponse response,
			HttpServletRequest request,HttpServletRequest httpServletRequest,ModelMap model,String noticeId,String noticeTitle,int noticeType,int noticeCharacter,String noticeContent,Integer pageIndex) throws ServiceException ,JsonGenerationException, JsonMappingException, IOException{
		NoticeWithBLOBs noticeWithBLOBs=new NoticeWithBLOBs();
		Date date = new Date();
		noticeWithBLOBs.setNoticeTime(date);
		noticeWithBLOBs.setNoticeId(noticeId);
		noticeWithBLOBs.setNoticeCharacter(noticeCharacter);
		noticeWithBLOBs.setNoticeTitle(new String(noticeTitle.getBytes("iso-8859-1"), "UTF-8"));
		noticeWithBLOBs.setNoticeContent(new String(noticeContent.getBytes("iso-8859-1"), "UTF-8"));
		if(pageIndex == null){
			pageIndex = 1;
		}
		PageHelper.startPage(pageIndex, Constant.pageSize);
		//编辑通知
		noticeService.editNotice(noticeWithBLOBs);
		List<Notice> noticesystemAlllist=noticeService.findcyadminNoticeAll(CommonFunc.getUserLogin(request).getUniversityId(),noticeType);
		if(CommonFunc.getUserLogin(httpServletRequest).getUserType()==2){
			model.put("noticesysAlllist", noticesystemAlllist);
			PageInfo pager = new PageInfo(noticesystemAlllist);
			model.put("page", pager);
			return "jsp/admin/noticesystem";
		}
		else{
			model.put("noticesystemAlllist", noticesystemAlllist);
			PageInfo pager = new PageInfo(noticesystemAlllist);
			model.put("page", pager);
			return "jsp/system/admin_notice";
		}
	}
	@RequestMapping(value = "/findtnotice")
	public void findtnotice(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,String noticeId) throws ServiceException ,JsonGenerationException, JsonMappingException, IOException{
		List<Notice> noticelist=noticeService.findtnoticeBynoticeId(noticeId);
		   ObjectMapper mapper = new ObjectMapper();  
	       ObjectWriter writer = mapper.viewWriter(Test.class);  
	       String jsonObj = writer.writeValueAsString(noticelist);  
			response.getWriter().write(jsonObj);
			response.getWriter().flush();
				
		}
}