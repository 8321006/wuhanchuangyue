package com.cy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Test;
import com.cy.model.UniversityTransaction;
import com.cy.service.UniversityTransactionService;
@Controller
@RequestMapping(value = "/transaction")
public class UniversityTransactionAction {
	private static final Logger logger = LoggerFactory.getLogger(UniversityTransactionAction.class);
	@Autowired
	private UniversityTransactionService universityTransactionService;
	public static Logger getLogger() {
		return logger;
	}
	/**
	 * 
	* @Title: apply 
	* @Description: 事务列表
	* @param model
	* @param universityTransaction
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/studentlist" ,method=RequestMethod.GET)
	public Object Studentlist(Model model,@ModelAttribute("universityTransaction") UniversityTransaction universityTransaction,HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException {
		//System.out.print("131");
		List<UniversityTransaction> Transactionlist=new ArrayList<UniversityTransaction>();
		int userType =CommonFunc.getUserLogin(httpServletRequest).getUserType();
		//userType 0为学生 1  为老师
		if(userType==0){
			universityTransaction.setUserId(CommonFunc.getUserLogin(httpServletRequest).getUserId());
		  Transactionlist=universityTransactionService.findAll(universityTransaction);
		  }
		if(userType==1){
			universityTransaction.setTeacherId(CommonFunc.getUserLogin(httpServletRequest).getUserId());
		  Transactionlist=universityTransactionService.findAllteacherTransactionList(universityTransaction);
		}
		    model.addAttribute("userType",userType );
		    model.addAttribute("Transactionlist", Transactionlist);
		    return "jsp/user/personalcenterjiaowu";
	}
	
	/**
	 * 
	* @Title: apply 
	* @Description: 学生申请补考by ajax 
	* @param model
	* @param universityTransaction
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	public void apply(Model model,@ModelAttribute("universityTransaction") UniversityTransaction universityTransaction, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		universityTransaction.setId(MD5.toMd5(UID.nextUid()));
		universityTransaction.setTeacherId("123456");
		universityTransaction.setUniversityId(CommonFunc.getUserLogin(httpServletRequest).getUniversityId());
		// 补考
		universityTransaction.setTransactionType(1);
		universityTransaction.setTransactionState(1);
		universityTransaction.setTransactionReason("申请补考");
		universityTransaction.setUserId(CommonFunc.getUserLogin(httpServletRequest).getUserId());
		universityTransaction.setTransactionTime(new Date());
		universityTransactionService.insert(universityTransaction);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("{\"success\":\"true\"}");
		response.getWriter().flush();
	}
	
	/**
	 * 
	* @Title: approve 
	* @Description: 批准事务 
	* @param model
	* @param universityTransaction
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/approve", method = RequestMethod.GET)
	public void approve(Model model,@ModelAttribute("universityTransaction") UniversityTransaction universityTransaction, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		// 通过
		universityTransaction.setTransactionType(1);
		universityTransaction.setTransactionState(2);
		universityTransactionService.update(universityTransaction);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("{\"success\":\"true\"}");
		response.getWriter().flush();
	}
	/**
	 * 
	* @Title: approve 
	* @Description: 学生新增事务 
	* @param model
	* @param universityTransaction
	* @param httpServletRequest
	* @param response
	* @throws ServiceException
	* @throws IOException
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(Model model,@ModelAttribute("universityTransaction") UniversityTransaction universityTransaction, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		// 通过
		universityTransaction.setUserId("1");
		universityTransaction.setClassId("1");
		universityTransaction.setId(MD5.toMd5(UID.nextUid()));
		universityTransaction.setTransactionTime(new Date());
		universityTransactionService.update(universityTransaction);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("{\"success\":\"true\"}");
		response.getWriter().flush();
	}
	@RequestMapping(value = "/agree", method = RequestMethod.POST)
	public void agree(Model model,@ModelAttribute("universityTransaction") UniversityTransaction universityTransaction, 
			HttpServletRequest httpServletRequest,HttpServletResponse response) throws ServiceException, IOException {
		//universityTransaction.setTransactionState(1);
		universityTransaction.setTeacherId(CommonFunc.getUserLogin(httpServletRequest).getUserId());
		universityTransactionService.updateByuserId(universityTransaction);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write("{\"success\":\"true\"}");
		response.getWriter().flush();
	}
}
