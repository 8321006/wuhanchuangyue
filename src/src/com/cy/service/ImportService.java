package com.cy.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cy.common.util.MD5;
import com.cy.common.util.StringUtil;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.mapper.CourseClassMapper;
import com.cy.mapper.MyCourseMapper;
import com.cy.mapper.UserMapper;
import com.cy.model.CourseClass;
import com.cy.model.MyCourse;
import com.cy.model.UniversityCourse;
import com.cy.model.User;

/**
 * 业务实现层 - 表：t_user
 * @since 2015-06-08 15:47:16
 */
@Service("importService")
public class ImportService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private MyCourseMapper myCourseMapper;
	
	@Resource
	private CourseClassMapper courseClassMapper;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CourseClassService courseClassService;
	
	@Autowired
	private MyCourseService myCourseService;
	
	@Autowired
	private UniversityCourseService universityCourseService;
	
	@Autowired
	private UniversityService universityService;
	
	@Autowired
	private CourseService courseService;

	public int insertMyCourceList(List<MyCourse> myCourceList) throws ServiceException {
		try {
			return myCourseMapper.insertMyCourseList(myCourceList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int insertCourseClassList(List<CourseClass> courseClassList) throws ServiceException {
		try {
			return courseClassMapper.insertCourseClassList(courseClassList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public void insert(List<User> listUser, List<MyCourse> listMyCourse,List<UniversityCourse> universityCourselist,List<CourseClass> listCourceClass) throws ServiceException {	
		for(CourseClass courseClass:listCourceClass){
			String universityCourseId = courseClass.getUniversityCourseId();
			UniversityCourse universityCourse=new UniversityCourse();
			universityCourse.setCourseId(courseClass.getCourseId());
			universityCourse.setUniversityId(courseClass.getUniversityId());
			universityCourse.setCourseTerm(courseClass.getCourseTerm());
			List<UniversityCourse> Universitylist = universityCourseService.findByPrimaryKey(universityCourse);
			String classId = courseClassService.selectbyid(universityCourseId);
			if(Universitylist.size()>0){
				//删除t_my_course
				myCourseService.deleteclassId(universityCourse);
				//删除t_course_class
				courseClassService.deletebyid(universityCourse);
				//删除t_university_course
				universityCourseService.deletebyterm(universityCourse);	
			} 
			}
			if(listUser.size()>0){
				userService.insertUserList(listUser);
			}
			insertMyCourceList(listMyCourse);
			insertCourseClassList(listCourceClass);
			universityCourseService.insertUniversitycourse(universityCourselist);
		
	}
	// 文件路径
	public String insertusers(MultipartHttpServletRequest multipartRequest,HttpServletRequest request) throws ServiceException{
		 SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/HH");
		String  message="";
         /** 构建文件保存的目录* */
         String logoPathDir = "/business/shops/upload/"
                 + dateformat.format(new Date());
         /** 得到文件保存目录的真实路径* */
         String logoRealPathDir = request.getSession().getServletContext()
                 .getRealPath(logoPathDir);
         /** 根据真实路径创建目录* */
         File logoSaveFile = new File(logoRealPathDir);
         if (!logoSaveFile.exists())
             logoSaveFile.mkdirs();
         /** 页面控件的文件流* */
         MultipartFile multipartFile = multipartRequest.getFile("file");
         /** 获取文件的后缀* */
         String suffix = multipartFile.getOriginalFilename().substring(
                 multipartFile.getOriginalFilename().lastIndexOf("."));
         /** 使用UUID生成文件名称* */
         String logImageName = UUID.randomUUID().toString() + suffix;// 构建文件名称
        /** 拼成完整的文件保存路径加文件* */
         String fileName = logoRealPathDir + File.separator + logImageName;
         
         File file = new File(fileName);
         try {
             multipartFile.transferTo(file);
         } catch (IllegalStateException e) {
             e.printStackTrace();
         } catch (IOException e) {
             e.printStackTrace();
         }
         /** 打印出上传到服务器的文件的绝对路径* */
         System.out.println("****************"+fileName+"**************");
         
         
         
		
  
         String fullName = multipartFile.getOriginalFilename().split("\\.")[0];
         String schoolName = fullName.substring(0,fullName.indexOf("("));
         //从文件名称获取课程学期
         String courseTerm=fullName.substring(fullName.indexOf("(")+1,fullName.length()-1);
         // 学校Id
         String universityId="";
           // 判断t_university是否已经存在该学校
         if(null!=universityService.findByUniversityName(schoolName))
         {
        	 universityId=universityService.findByUniversityName(schoolName).getUniversityId();
         }else
         {
        	 message+="您的学校名称有误，请检查后再重新导入！";
         }
         Set<User> userSet=new HashSet<User>(); 
		 List<User> listUser=null;
		List<MyCourse> listMyCourse=new ArrayList();
		List<CourseClass> listCourceClass=new ArrayList();
		List<UniversityCourse> universityCourselist=new ArrayList();
		Map<String,String> courseMap = new HashMap<String,String>();
		Map<String,String> courseMap2 = new HashMap<String,String>();
		String classId="";
	//	String path="f://students.xlsx";//文件路径   
		try {    
			File files = new File(fileName);   
			 Map<String,List<String[]>> resultMap = getData(files, schoolName,universityId,courseTerm);   
			 if (resultMap.isEmpty()) {
				 message+="导入失败！您导入的课程名称不正确！请重新导入！";
			 }
			 else
					{     
						for(String key : resultMap.keySet()){
							String universityCourseId=key.split("_")[0];
							String courseId=key.split("_")[1];
							String courseName=key.split("_")[2];
							//判断当前的课程在当前学校，当前学期是否是否已经开课，
						int courseStartStatus=universityCourseService.courseStartStatus(universityId,courseTerm,courseId);
						if(courseStartStatus==1){
							universityCourselist.clear();
							message="导入失败，"+courseName+"课程已经开课,不能选课！";
							break;
						}
							//add course_class 信息
							CourseClass courceclass=new CourseClass();
							classId = MD5.toMd5(UID.nextUid());
							courceclass.setClassId(classId);
							//courseMap2.put(key, classId);
							courceclass.setUniversityId(universityId);
							courceclass.setCourseTerm(courseTerm);
							courceclass.setClassName(schoolName+courseName+"班");
							courceclass.setCourseId(courseId);
							courceclass.setCourseStartTime(new Date());
							courceclass.setCourseEndTime(new Date());
							courceclass.setUniversityCourseId(universityCourseId);
							System.out.println("courseClass >>>>>>>>>>>>>>>>" + courceclass);
							listCourceClass.add(courceclass);
							//将信息写入到universitycourse中
							UniversityCourse universityCourse=new UniversityCourse();
							universityCourse.setId(universityCourseId);
							universityCourse.setClassId(classId);
							universityCourse.setCourseTerm(courseTerm);
							universityCourse.setCourseId(courseId);
							universityCourse.setUniversityId(universityId);
							universityCourselist.add(universityCourse);
							List<String[]> result=resultMap.get(key);
							
							int rowLength = result.size();    
							for (int i = 0; i < rowLength; i++) 
							{      
								User user=new User();
								String[] rowRes=result.get(i);								      
								String userId = MD5.toMd5(UID.nextUid());
								user.setUniversityId(universityId);
								user.setLoginPassword(MD5.toMd5("123456"));
								user.setRealName(rowRes[1]);
								user.setLoginName(rowRes[2]);
								user.setStudentId(rowRes[2]);							
							 // user.setPhone(rowRes[4]);
								user.setEmail(rowRes[4]);
								user.setUserType(0);    
								if(userSet.contains(user))
								{
									for(Iterator ite=userSet.iterator();ite.hasNext();)
									{
										User user1=(User)ite.next();
										if(user1.getStudentId().equals(user.getStudentId()))
										{
											userId=user1.getUserId();
										}
									}
								}
								
										MyCourse mycource=new MyCourse();
										mycource.setType("0");
										mycource.setId(MD5.toMd5(UID.nextUid()));
										mycource.setCourseName(rowRes[0]);
										// 根据课程名称查询课程Id
										if(null==(userService.finduserBystudentId(user))){
											mycource.setUserId(userId);
										}
										else{
											mycource.setUserId(userService.finduserBystudentId(user));
										}
										user.setUserId(userId);
										//String courseId = courseService.findCourseByName(rowRes[0]).getCourseId();
										mycource.setCourseId(courseId);
										mycource.setClassId(classId);
										System.out.println("mycource >>>>>>>>>>>>>>>>" + mycource);
										  listMyCourse.add(mycource);
								
										
																		
										//listUser.add(user);
									
//									else if(j==3)
//									{
//										CourseClass courceclass=new CourseClass();
//										courceclass.setClassName(result[i][3]);
//										listCourceClass.add(courceclass);
//									}
										//判断当前用户在t_user表中是否已经存在userService.f
										 if(null==(userService.finduserBystudentId(user))){
										  userSet.add(user);
										  }
										
							    }
							  
								listUser=new ArrayList(userSet);
								System.out.println(listUser+"      "+listMyCourse+"     "+universityCourselist+"    "+listCourceClass);     
							}    
						 } 
					
				
		}
		
			 catch (Exception e) 
			 {   
			    e.printStackTrace();  
			 } 		
		if(universityCourselist.size()>0){
		insert(listUser,listMyCourse, universityCourselist,listCourceClass);}
//		userService.insertUserList(listUser);
//		importService.insertMyCourceList(listMyCourse);
//		importService.insertCourseClassList(listCourceClass);
		System.out.println(listUser+"      "+listMyCourse+"     "+listCourceClass);
		return message;
	}
	
	/**   * 读取EXCEL   
	 * *    
	 * * @param firstrow 
	 *   遍历sheet从第几行开始读取   
	 * * @return 读取后返回数组  
	 *  */ 
	@SuppressWarnings("deprecation") 
	public   Map<String,List<String[]>> getData(File file, String schoolName,String universityId,String courseTerm) 
	throws FileNotFoundException, IOException,ServiceException
	{
		 String ucid ="";
		 List<String[]> result=null;
		 Map<String,List<String[]>> resultMap = new HashMap<String,List<String[]>>(); 
		 int rowSize = 0;   
		 BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
		// POIFSFileSystem fs = new POIFSFileSystem(in);   
		// HSSFWorkbook wb = new HSSFWorkbook(fs);  
		 XSSFWorkbook wb = new XSSFWorkbook(in);   
		// HSSFCell cell = null;
		 XSSFCell cell = null;
		 for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets();
				 sheetIndex++) { 
			 result = new ArrayList<String[]>(); 
			// HSSFSheet st = wb.getSheetAt(sheetIndex);  
			 XSSFSheet st = wb.getSheetAt(sheetIndex);
			 String courseName=st.getSheetName();
			 //判断该门课程在该学期是否被该学校选,没有就把关系补上,有就直接导入学生信息
			 String res=universityCourseService.findCourseUniversity(courseName,universityId,courseTerm);
			 String courseId="";
			 if("".equals(res) || null==res || "0".equals(res))
			 {
				 //如果没有课程和大学的关系就补上
				 ucid = MD5.toMd5(UID.nextUid());
				//在t_course表查询该课程名称是否存在
				 if(null!=courseService.findCourseByName(courseName))
				 {
					 courseId = courseService.findCourseByName(courseName).getCourseId();
				 }else{
					 resultMap.clear();
					 break;
					
				 }
				 //判断该关系
				//将学校与课程关系插入到 t_university_course
				// universityCourseService.insertForImport(ucid,courseId,courseName,universityId,schoolName,courseTerm);
				 
				 
			 }else{
				 ucid =  universityCourseService.findCourseIdByCondition(courseName,universityId,courseTerm);
			 }
			 
			 
			 
		 for (int rowIndex = 1; rowIndex <= st.getLastRowNum(); 
				rowIndex++) {
			//HSSFRow row = st.getRow(rowIndex);   
			 XSSFRow row = st.getRow(rowIndex); 
			if (row == null) 
			{      
				continue;          
			}
			int tempRowSize = row.getLastCellNum() + 1;     
			if (tempRowSize > rowSize) 
			{     
				rowSize = tempRowSize;      
			}
			String[] values = new String[rowSize];     
			//Arrays.fill(values, "");     
			boolean hasValue = false; 
			for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
				String value = "";
				cell = row.getCell(columnIndex); 
				if (cell != null) { 
					switch (cell.getCellType()) { 
					case HSSFCell.CELL_TYPE_STRING://读取的格式为字符串
						value = cell.getStringCellValue(); 
						break;
					case HSSFCell.CELL_TYPE_NUMERIC://读取的格式为数组       
						//如果格式为日期格式，自定义格式输出 
						if (HSSFDateUtil.isCellDateFormatted(cell)) {        
							Date date = cell.getDateCellValue(); 
							if (date != null) { 
								value = new SimpleDateFormat("yyyy-MM-dd").format(date);
							} else { 
								value = ""; 
							}
						} else { 
							//如果格式为数值，自定义格式输出 
							value = new DecimalFormat("0.00").format(cell.getNumericCellValue()); 
						}
						break; 
					    case HSSFCell.CELL_TYPE_FORMULA:
					    	// 导入时如果为公式生成的数据则无值 
					    	value = "";
					    	break; 
					    	// 导入时如果为空
					     case HSSFCell.CELL_TYPE_BLANK: 
					    	 break;
					     case HSSFCell.CELL_TYPE_ERROR: 
					    	 value = "";
					    	 break;
					    	// 导入时如果为BOOLEAN型 自定义格式输出 
					     case HSSFCell.CELL_TYPE_BOOLEAN:
					    	 value = (cell.getBooleanCellValue() == true ? "Y" : "N"); 
					    	 break;
					    	 default:
					    		 value = ""; 
					}
				}
				if(!StringUtil.isEmpty(value)){
				values[columnIndex] = rightTrim(value);
				hasValue = true; 
				}      
			}
			if (hasValue) { 
				result.add(values);
			}
		}
		
		 resultMap.put(ucid+"_"+courseId+"_"+courseName, result);
	}
		
		 in.close(); 
		 return resultMap;
							
				 			
	}
	
	/** 
	  * 去掉字符串右边的空格   *  
	  * @param str 要处理的字符串   
	  * * @return 处理后的字符串   
	  * */ 
	 public static String rightTrim(String str) 
	 {   
		 if (str == null) {    
			 return "";  
			 }   
		 int length = str.length();   
		 for (int i = length - 1; i >= 0; i--) {    
			 if (str.charAt(i) != 0x20) {     
				 break;    
				 }    
			 length--;   
			 }   
		 return str.substring(0, length);  
    } 
}