package com.cy.web;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cy.commons.ftp.client.MCFtpClient;
import com.cy.commons.ftp.client.MCFtpClientFactory;
import com.cy.exception.ServiceException;
import com.cy.model.CourseData;
import com.cy.model.Test;
import com.cy.model.User;
import com.cy.service.CourseDataService;

@Controller
@RequestMapping(value = "/coursedata")
public class CourseDataAction{

    @Autowired
    private CourseDataService courseDataService; 
    
    @RequestMapping(value = "/detail")
    public String coursedetail(HttpServletResponse response,
            HttpServletRequest request,String thecourseId,ModelMap model) throws ServiceException, IOException, Exception {
        //从session读取userId
        User userInfo = (User) request.getSession().getAttribute("user");
        //String userId = userInfo.getUserId();
        
        //查出userId对应的courseId和courseName属性
        List<CourseData> courseIdAndNameList = courseDataService.findAllCourseIdByuserId(userInfo);
        if(courseIdAndNameList.size()== 0){
        	return "jsp/user/personalcenter";
        }
        model.put("courses", courseIdAndNameList);
        
        List<CourseData> courseDataList  = new ArrayList<CourseData> ();
        //默认显示第一个courseId对应的有关课程资料内容
        String courseId="";
        if(StringUtils.isEmpty(thecourseId)){
            courseDataList = courseDataService.findCourseDataByCourseId(courseIdAndNameList.get(0).getCourseId());
            courseId = courseIdAndNameList.get(0).getCourseId();
        }else{
            courseDataList = courseDataService.findCourseDataByCourseId(thecourseId);
            courseId=thecourseId;
        } 
        model.put("courseData", courseDataList);
        model.put("courseId", courseId);
        
        return "jsp/user/personalcenterright2";
    }
    @RequestMapping(value = "/detailcourse",method=RequestMethod.POST)
	@ResponseBody
    public void course(HttpServletResponse response, HttpServletRequest request,String courseId,ModelMap model) throws ServiceException, IOException, Exception {
    	System.out.print("courseId"+courseId);
    	 List<CourseData> courseDataList  = new ArrayList<CourseData> (); 
    	courseDataList = courseDataService.findCourseDataByCourseId(courseId);
    	 ObjectMapper mapper = new ObjectMapper();  
	       ObjectWriter writer = mapper.viewWriter(Test.class);  
	       String jsonObj = writer.writeValueAsString(courseDataList);  
			response.getWriter().write(jsonObj);
			response.getWriter().flush();
    }
        //从session读取userId
    @RequestMapping(value = "/download")
    public void download(HttpServletResponse response,
            HttpServletRequest request,String path,ModelMap model) throws Exception  {
            MCFtpClientFactory factory = new MCFtpClientFactory();
            MCFtpClient mc = factory.config().bulidMCFtpClient(true);
            String os = System.getProperty("os.name");
        //    System.out.println(file.substring(file.lastIndexOf('/')+1)); 
            if(os.toLowerCase().startsWith("win")){
            	File temp = new File(request.getServletContext().getRealPath("/") + "upload\\temp\\");
            	if(!temp.exists()){
            		temp.mkdir();
            	}
                String localPath = request.getServletContext().getRealPath("/") + "upload\\temp" + path;
                File filedata = new File(localPath);
                System.out.print(path);
                System.out.print(mc.getFtp().printWorkingDirectory());
                mc.downloadFile(path, new File(localPath));
                path=localPath;
            }else{
            	path ="/usr/local/resource/upload/"+path;
            }
                File file = new File(path);
               // 取得文件名。
                String filename = file.getName();
                // 取得文件的后缀名。
                String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

                // 以流的形式下载文件。
                InputStream fis = new BufferedInputStream(new FileInputStream(path));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                 // 清空response
                response.reset();
                 // 设置response的Header
                response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("UTF-8"), "ISO8859-1"));
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                response.setContentType("application/octet-stream");
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
    }

    @RequestMapping(value = "/coursedatalist")
    public String coursedatalist(HttpServletResponse response, 
                                                             HttpServletRequest request,
                                                             String thecourseId,
                                                             ModelMap model) 
                   throws ServiceException, IOException, Exception {
        //从session读取userId(管理员ID)
        User userInfo = (User) request.getSession().getAttribute("user");
        String userId = userInfo.getUserId();
        //user_id——>university_id——>course_id
        List<CourseData> courseIdAndNameList = new ArrayList<CourseData>();
        courseIdAndNameList = courseDataService.findAllCourseIdByUniversityId(userId);
        List<CourseData> courseDataList  = new ArrayList<CourseData> ();
        String courseId ="";
        if(StringUtils.isEmpty(thecourseId)){
            courseDataList = courseDataService.findCourseDataByCourseId(courseIdAndNameList.get(0).getCourseId());
            courseId = courseIdAndNameList.get(0).getCourseId();
        }else{
            courseDataList = courseDataService.findCourseDataByCourseId(thecourseId);
            courseId=thecourseId;
        } 
        model.put("courseId", courseId);
        model.put("courses", courseIdAndNameList);
        model.put("courseData", courseDataList);
        
        return "jsp/admin/coursedatalist";
    }
}
