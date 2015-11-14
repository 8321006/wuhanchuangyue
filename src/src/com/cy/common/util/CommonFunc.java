package com.cy.common.util;

import javax.servlet.http.HttpServletRequest;
import com.cy.model.User;

public class CommonFunc {
	
	public static void login(HttpServletRequest request,User user){
		request.getSession().setAttribute("user", user);
	}
	
	public static User getUserLogin(HttpServletRequest request){
		return (User)request.getSession().getAttribute("user");
	}

}
