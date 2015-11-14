package com.cy.web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cy.common.util.CommonFunc;
import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.model.Course;
import com.cy.model.Message;
import com.cy.model.User;
import com.cy.service.MessageService;
import com.cy.service.UserService;
import com.lowagie.text.Cell;
import com.lowagie.text.Font;
import com.lowagie.text.Row;

/**
 * Spring MVC Controler - 表：t_message
 * @since 2015-06-08 16:37:51
 */
@Controller
@RequestMapping(value = "/msg")
public class MessageAction {
	private static final Logger logger = LoggerFactory.getLogger(MessageAction.class);

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private UserService userService;

	/**
	 * 列表页面
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/message", method=RequestMethod.GET)
	public Object listPage() throws ServiceException {
		return "messageAdd";
	}

	/**
	 * 列表数据
	 */
/*	@RequestMapping(value = "/message", method=RequestMethod.POST)
	@ResponseBody
	public Object listData(HttpServletRequest request) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			Criteria<Message> criteria = Criteria.create(Message.class);
			criteria.buildFromRequest(request);
			criteria.sortIfEmpty(Sort.asc("messageId"));
			Pager<Message> pager = messageService.findPage(criteria);
			resultMap.put("result", 1);
			resultMap.put("total", pager.getTotalRecords());
			resultMap.put("rows", pager.getList());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}*/

	/**
	 * 新增页面
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/messageadd")
	public Object addPage(Message message,HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException, IOException {	
		message.setNickname(CommonFunc.getUserLogin(request).getRealName());
		//此种赋值方法覆盖，取值应该直接取当前登录着的user，用以上的那个方法获取当前登录名   
		message.setUser(request.getParameter("userid"));
		message.setMessage(request.getParameter("messageid"));
		List<User> listuser=userService.findbyuserid(message.getUser());
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(listuser)) ;
		response.getWriter().flush();
		response.getWriter().close();
		if(listuser.size()==0)
		{
			return null;
		}
		else
		{
			String userid=listuser.get(0).getUserId();
			message.setUserId(userid);
		}
		message.setFsruser(CommonFunc.getUserLogin(request).getUserId()); 		
		message.setMessageId(MD5.toMd5(UID.nextUid()));
		message.setMessageType(1);
		//暂时先把发送人写死，此处发送人肯定是当前登录人，鉴于当前登录没有完全做好所以写死
		Date day=new Date(); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		System.out.println(df.format(day)); 
		message.setMessagetime(day);
		message.setWthd("1");
		messageService.insert(message);
		Message message2=new Message();
		message2.setUserId(CommonFunc.getUserLogin(request).getUserId());
		message2.setFsruser(CommonFunc.getUserLogin(request).getUserId());
		List<Message> listmessage=messageService.findmessprivesend(message2);
		List<Message> listmessageadmin=messageService.getadminMessage(CommonFunc.getUserLogin(request).getUserId());
		model.put("listmessage", listmessage);
		model.put("listmessageadmin", listmessageadmin);
		
		return "jsp/message/messagecenter";
	}

	/**
	 * 新增保存
	 */
/*	@RequestMapping(value = "/messageadd", method=RequestMethod.POST)
	@ResponseBody
	public Object doAdd(@FormModel("message") Message message) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			ValidatorUtil.validate(message);
			messageService.insert(message);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}*/

	/**
	 * 修改页面
	 */
	@RequestMapping(value = "/messageedit", method=RequestMethod.GET)
	public Object editPage(Integer messageId) {
		ModelAndView model = new ModelAndView();
		try {
			//Message message = messageService.findByPrimaryKey(messageId);
			//model.addObject("message", message);
			model.setViewName("message_edit");
		} catch (Exception e) {
			model.addObject("exception", e.getMessage());
			model.setViewName("/error");
			logger.error(e.getMessage(), e);
		}
		return model;
	}

	/**
	 * 修改保存
	 */
	/*@RequestMapping(value = "/messageedit", method=RequestMethod.POST)
	@ResponseBody
	public Object doEdit(Message message) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			//ValidatorUtil.validate(message);
			messageService.update(message);
			resultMap.put("result", 1);
		} catch (ValidatorException ve) {
			resultMap.put("result", 0);
			resultMap.put("message", ve.getMessage());
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}*/

	/**
	 * 删除
	 * @throws ServiceException 
	 * @throws IOException 
	 */
/*	@RequestMapping(value = "/messagedelete", method=RequestMethod.POST)
	@ResponseBody
	public Object doDelete(List<Integer> ids) {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		try {
			messageService.batchDelete(ids);
			resultMap.put("result", 1);
		} catch (Exception e) {
			resultMap.put("result", 0);
			resultMap.put("message", e.getMessage());
			logger.error(e.getMessage(), e);
		}
		return resultMap;
	}*/
	
	
	//查询个人私信,系统消息传递参数
	@RequestMapping(value="/getlist")
    public void messageList(HttpServletResponse response,
			HttpServletRequest request, String type,ModelMap model) throws ServiceException, IOException
    {
		//CommonFunc.getUserLogin(request).getUserId();
		Message message2=new Message();
		message2.setUserId(CommonFunc.getUserLogin(request).getUserId());
		message2.setFsruser(CommonFunc.getUserLogin(request).getUserId());
		List<Message> listmessage=messageService.findMessageList(message2);
		List<Message> listmessageadmin=messageService.getadminMessage(CommonFunc.getUserLogin(request).getUserId());
		List<Message> norederCount=messageService.getnorederCount(message2);
		System.out.println(norederCount.size());
		response.setContentType("text/html;charset=UTF-8");
		JSONObject json =new JSONObject();
		if(listmessage!=null && listmessage.size()>0){
			json.put("letter", norederCount.size());
		}else{
			json.put("letter", 0);
		}
		if(listmessageadmin!=null && listmessageadmin.size()>0){
			json.put("sysmsg", listmessageadmin.size());
		}else{
			json.put("sysmsg", 0);
		}
		json.put("allmes", json.getInt("letter")+json.getInt("sysmsg"));
		response.getWriter().print(json) ;
		response.getWriter().flush();		
    }
	//查询个人私信
		@RequestMapping(value="/getletter")
	    public String personnalletter(HttpServletResponse response,
				HttpServletRequest request, String type,ModelMap model) throws ServiceException
	    {
			Message message2=new Message();
			message2.setUserId(CommonFunc.getUserLogin(request).getUserId());
			message2.setFsruser(CommonFunc.getUserLogin(request).getUserId());
			messageService.updateReader(CommonFunc.getUserLogin(request).getUserId());
			List<Message> listmessage=messageService.findMessageList(message2);
			List<Message> listmessageadmin=messageService.getadminMessage(CommonFunc.getUserLogin(request).getUserId());
			model.put("listmessage", listmessage);
			model.put("listmessageadmin", listmessageadmin);
			model.put("msgtype", "0");
	    	return "jsp/message/messagecenter";
	    }
		
		
		//点击站内信调用
		@RequestMapping(value="/getclickznx")
	    public void getclickznx(HttpServletResponse response,
				HttpServletRequest request) throws ServiceException, IOException
	    {
			Message message2=new Message();
			message2.setUserId(CommonFunc.getUserLogin(request).getUserId());
			message2.setFsruser(CommonFunc.getUserLogin(request).getUserId());
			List<Message> listmessage=messageService.findMessageList(message2);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(JSONArray.fromObject(listmessage)) ;
			response.getWriter().flush();
			response.getWriter().close();
	    }
	//查询个人系统消息
	@RequestMapping(value="/getsysmsg")
	  public String systemmsg(HttpServletResponse response,
				HttpServletRequest request, String type,ModelMap model) throws ServiceException
	    {
			Message message2=new Message();
			message2.setUserId(CommonFunc.getUserLogin(request).getUserId());
			message2.setFsruser(CommonFunc.getUserLogin(request).getUserId());
			List<Message> listmessage=messageService.findMessageList(message2);
			List<Message> listmessageadmin=messageService.getadminMessage(CommonFunc.getUserLogin(request).getUserId());
			model.put("listmessage", listmessage);
			model.put("listmessageadmin", listmessageadmin);
			model.put("msgtype", "1");
	    	return "jsp/message/messagecenter";
	    }
	/**把此查询系统消息的方法全部移到查询个人私信里面去了（就是以上方法）加载私信的时候就查出系统消息
	//查询系统消息
	@RequestMapping(value="/getlistadmin")
    public String messageListadmin(HttpServletResponse response,Message message,
			HttpServletRequest request, String type,ModelMap model) throws ServiceException
    {
		List<Message> listmessageadmin=messageService.getadminMessage();
		model.put("listmessageadmin", listmessageadmin);
		return "jsp/message/messagecenter";
		List<Message> listmessage=messageService.getadminMessage(message);
		model.put("listmessage", listmessage);
    	return "messageList";
    }
	**/
	
	/**
	 * 列表页面
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	@RequestMapping(value = "/messageGetid")
	public Object listPage(HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException, IOException {
		System.out.println("================================================================================");
		String messageId=(String) request.getParameter("messageId");
		List<Message> messageGetidList=messageService.getMessageListId(messageId);
	//	model.put("messageGetidList", messageGetidList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(messageGetidList)) ;
		response.getWriter().flush();
		response.getWriter().close();
		return  "jsp/message/messagecenter";
	}
	
	@RequestMapping(value = "/messprivatedetail")
	public Object messprivatedetail(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,Message message) throws ServiceException, IOException {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		String messuserid=request.getParameter("messuserid");
		List<Message> messpridetail=messageService.messdetail(messuserid);
 		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(messpridetail)) ;
		response.getWriter().flush();
		response.getWriter().close();
		return  "jsp/message/messagecenter";
	}
	
	@RequestMapping(value = "/messprivatesend")
	public Object messprivatesend(HttpServletResponse response,
			HttpServletRequest request,ModelMap model,Message message) throws ServiceException, IOException {
		System.out.println("-----------------------------------------------------------------------------------");
		String messuserid=request.getParameter("messuserid");
		String sendxx=request.getParameter("sendxx");
		String fsruserid=request.getParameter("fsruserid");
			
		message.setMessageId(MD5.toMd5(UID.nextUid()));
		message.setMessageType(1);
		message.setMessuserid(messuserid);
		message.setMessage(sendxx);		
		Date day=new Date(); 
		message.setMessagetime(day);
		message.setFsruser(CommonFunc.getUserLogin(request).getUserId());
		message.setUser(CommonFunc.getUserLogin(request).getRealName());
		message.setNickname(CommonFunc.getUserLogin(request).getRealName());
		message.setUserId(fsruserid);
		message.setWthd("1");
		messageService.insertsend(message);
		List<Message> messpridetail=messageService.messdetail(messuserid);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(messpridetail)) ;
		response.getWriter().flush();
		response.getWriter().close();
		
		/**
		messageService.insertsend(message);
		String messageId=(String) request.getParameter("messageId");
		List<Message> messageGetidList=messageService.getMessageListId(messageId);
	//	model.put("messageGetidList", messageGetidList);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(JSONArray.fromObject(messageGetidList)) ;
		response.getWriter().flush();
		response.getWriter().close();
		**/
		return  "jsp/message/messagecenter";
	}

	
	/**
	 * 回复私信
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/hfmessage", method=RequestMethod.POST)
	public Object hf(Message message) throws ServiceException {
		message.setMessageType(2);
		messageService.insert(message);
		return "messageAdd";
	}
	
	/**
	 * 评论
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/plmessage")
	public Object pinglun(Message message,HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		List<Message> listmessage=messageService.getplList();
		model.put("listPlmessage", listmessage);
		return "pinglunAdd";
	}
	
	/**
	 * 评论
	 * @throws ServiceException 
	 */
	@RequestMapping(value = "/pinglunAdd",method=RequestMethod.POST)
	public Object pinglunInsert(Message message,HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		message.setMessageType(3);
		message.setFsruser("CheRy");
		Date day=new Date(); 
		//SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		//System.out.println(df.format(day)); 
		message.setMessagetime(day);
		messageService.insert(message);
		List<Message> listmessage=messageService.getplList();
		model.put("listPlmessage", listmessage);
		return "pinglunAdd";
	}


	
	
	//导入导出
	@RequestMapping(value="/xkyhdrdc")
    public String drdc() throws ServiceException, IOException
    {
		/**
		   HSSFWorkbook wb = new HSSFWorkbook();  
		         // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet  
		         HSSFSheet sheet = wb.createSheet("学生表一");  
		           // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short  
		           HSSFRow row = sheet.createRow((int) 0);  
		           // 第四步，创建单元格，并设置值表头 设置表头居中  
		           HSSFCellStyle style = wb.createCellStyle();  
		           style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式  
		     
		           HSSFCell cell = row.createCell((short) 0);  
		           cell.setCellValue("学号");  
		           cell.setCellStyle(style);  
		           cell = row.createCell((short) 1);  
		           cell.setCellValue("姓名");  
		         cell.setCellStyle(style);  
		           cell = row.createCell((short) 2);  
		          cell.setCellValue("年龄");  
		           cell.setCellStyle(style);  
		           cell = row.createCell((short) 3);  
		          cell.setCellValue("生日");  
		           cell.setCellStyle(style);  
		     
		           // 第五步，写入实体数据 实际应用中这些数据从数据库得到，  
		    
		         for (int i = 0; i <3; i++)  
		           {  
		               row = sheet.createRow((int) i + 1);  
		              // 第四步，创建单元格，并设置值  
		               row.createCell((short) 0).setCellValue(1);  
		               row.createCell((short) 1).setCellValue(2);  
		              row.createCell((short) 2).setCellValue(5);  
		              cell = row.createCell((short) 3);  

		           }  
		       // 第六步，将文件存到指定位置  
		      try  
		      {  
		             FileOutputStream fout = new FileOutputStream("E:/students.xls");  
		              wb.write(fout);  
		             fout.close();  
		         }  
		         catch (Exception e)  
		        {  
		             e.printStackTrace();  
		        }  
		       
            **/		     
    	return "drdc";
    }
	
	
	//我的问题(此方法用来展示我个人所提的所有问题和问题解答)
	@RequestMapping(value="/wdwt")
    public String wdwt(Message message,HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException
    {
		message.setMessageType(5);
		List<Message> listmessage=messageService.getplList();	
    	return "wdwt";
    }
	
	
	
	@RequestMapping(value = "/messagecenter")
	public String gocenter(HttpServletResponse response,
			HttpServletRequest request,ModelMap model) throws ServiceException {
		System.out.println("===============================================================");
		return "jsp/message/messagecenter";
	}

	
}