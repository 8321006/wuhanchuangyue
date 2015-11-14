package com.cy.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.exception.ServiceException;
import com.cy.service.UserLoggerService;

/**
 * Spring MVC Controler - 表：t_user_logger
 * @since 2015-08-28 14:42:22
 */
@Controller
@RequestMapping(value = "/userLogger")
public class UserLoggerAction {
	private static final Logger logger = LoggerFactory.getLogger(UserLoggerAction.class);

	@Autowired
	private UserLoggerService userLoggerService;

	@RequestMapping(value = "/list")
	@ResponseBody
	public Object list(String universityId,HttpServletRequest request) {
		List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
			try {
				resultMap = userLoggerService.getLogCurrMonth(universityId);
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		return resultMap;
	}
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Object listAll() {
		List<Map<String,Object>> resultMap = new ArrayList<Map<String,Object>>();
		try {
			resultMap = userLoggerService.getAllLogCurrMonth();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return resultMap;
	}
}