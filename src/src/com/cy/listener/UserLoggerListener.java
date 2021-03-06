package com.cy.listener;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class UserLoggerListener implements HttpSessionListener {
	//private static int activeSessions = 0;

	/* Session创建事件 */
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("UserLoggerListener.sessionCreated()");
		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(1);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count + 1);
		}
		ctx.setAttribute("numSessions", numSessions);
	}

	/* Session失效事件 */
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("UserLoggerListener.sessionDestroyed()");
		ServletContext ctx = event.getSession().getServletContext();
		Integer numSessions = (Integer) ctx.getAttribute("numSessions");
		if (numSessions == null) {
			numSessions = new Integer(0);
		} else {
			int count = numSessions.intValue();
			numSessions = new Integer(count - 1);
		}
		ctx.setAttribute("numSessions", numSessions);

	}
}