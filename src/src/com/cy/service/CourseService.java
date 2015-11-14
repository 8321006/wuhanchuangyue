package com.cy.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cy.common.util.ExcelUtil;
import com.cy.common.util.MD5;
import com.cy.common.util.StringUtil;
import com.cy.common.util.UID;
import com.cy.commons.ftp.client.MCFtpClientFactory;
import com.cy.commons.ftp.model.MCFtpFile;
import com.cy.exception.ServiceException;
import com.cy.mapper.CourseMapper;
import com.cy.mapper.PaperMapper;
import com.cy.mapper.TestMapper;
import com.cy.model.Course;
import com.cy.model.CourseChapter;
import com.cy.model.CourseCount;
import com.cy.model.CourseNote;
import com.cy.model.CourseResource;
import com.cy.model.MCFile;
import com.cy.model.Video;

/**
 * 业务实现层 - 表：t_course
 * @since 2015-06-08 15:47:16
 */
@Service("courseService")
public class CourseService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseMapper courseMapper;
	
	@Resource
	private PaperMapper paperMapper;
	
	@Resource
	private TestMapper testMapper;
	
	@Resource
	private MCFileService fileService;
	
	@Resource
	private QuestionService questionService;
	
	@Autowired
	private CourseChapterService courseChapterService;

	public void insert(Course entity) throws ServiceException {
		try {
			courseMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	public void delete(Course entity) throws ServiceException {
		try {
			courseMapper.deleteByPrimaryKey(entity.getCourseId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	**/

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				courseMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(Course entity) throws ServiceException {
		try {
			courseMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(Course entity) throws ServiceException {
		try {
			courseMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public Course findByPrimaryKey(String courseId) throws ServiceException {
		try {
			return courseMapper.selectByPrimaryKey(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Course> findmyIncourse(Map<String,String> condition) throws ServiceException{
		try {
			return courseMapper.findmyIncourse(condition);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	//根据日期查询当天需要做的事情（行事历）
	public List<Course> findByxsl(Date datetime) throws ServiceException{
		try {
			return courseMapper.findByxsl(datetime);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	@Transactional
	public void insertCourses(String path,String courseName,String userId,HttpServletRequest request) throws Exception{
    	String filesuffix = "";
    	if(new File(path+courseName.split("\\.")[0]+"."+"xls").exists()){
    		filesuffix = "xls";
    	}else if(new File(path+courseName.split("\\.")[0]+"."+"xlsx").exists()){
    		filesuffix = "xlsx";
    	}
    	List<Map<String,String>> course = ExcelUtil.ExcelToList(path+courseName.split("\\.")[0]+"."+filesuffix);
    	Course thecourse = new Course();
    	String courseId = MD5.toMd5(UID.nextUid());
    	Date date = new Date();
    	thecourse.setCourseId(courseId);
    	thecourse.setCourseName(courseName.split("\\.")[0]);
    	thecourse.setCourseDesc(course.get(0).get("资源介绍"));
    	thecourse.setCourseAuthor(course.get(0).get("课程老师"));
    	thecourse.setCourseAuthorDetail(course.get(0).get("老师介绍"));
    	String type = course.get(0).get("分类");
    	if(!StringUtil.isEmpty(type)){
    		Course coursetype = courseMapper.findCourseType(type);
    		if(coursetype != null && coursetype.getCourseType() != null)
    		thecourse.setCourseType(coursetype.getCourseType());
    	}
    	
    	thecourse.setCourseCreateTime(date);
    	//不用放入课程的文件
    	List<String> notfile = new ArrayList<String>();
    	notfile.add(courseName.split("\\.")[0]+".xls");
    	notfile.add(courseName.split("\\.")[0]+".xlsx");
    	notfile.add(courseName);
    	if(course.get(0).get("课程封面图") != null){
    		notfile.add(course.get(0).get("课程封面图"));
    		String coverpath = request.getServletContext().getRealPath("/")+"/images/coursecover/";
        	//生成图片的名称
    		String coverfileName = String.valueOf(new Date().getTime())+"."+course.get(0).get("课程封面图").split("\\.")[1];
    		File coverfile = new File(coverpath+coverfileName);
    		File beforecopyfile = new File(path+"/"+course.get(0).get("课程封面图"));
    		InputStream inStream = new FileInputStream(beforecopyfile);
    		OutputStream outStream = new FileOutputStream(coverfile);
    		byte[] buffer = new byte[1024]; 
    		int byteread = 0; // 读取的字节数 
    		while ((byteread = inStream.read(buffer)) != -1) {  
    			outStream.write(buffer, 0, byteread);  
            }
    		inStream.close();
    		outStream.close();
    		thecourse.setCourseCoverUrl("/images/coursecover/"+coverfileName);
    	}
    	if(course.get(0).get("标签") != null){
    		thecourse.setCourseTag(course.get(0).get("标签"));
    	}
    	//插入课程
    	courseMapper.insertSelective(thecourse);
    	List<CourseChapter> coursechapters = new ArrayList<CourseChapter>();
    	int totalplan = 0;
    	for(int i = 0 ; i<course.size() ; i++){
    		Map<String,String> chapter = course.get(i);
    		CourseChapter coursechapter = new CourseChapter();
    		String chapterId = MD5.toMd5(UID.nextUid());
    		coursechapter.setChapterId(chapterId);
    		coursechapter.setChapterIndex(chapter.get("章节"));
    		coursechapter.setChapterName(chapter.get("课时"));
    		coursechapter.setChapterVideoFile(chapter.get("地址"));
    		coursechapter.setCourseId(courseId);
    		coursechapter.setCreatTime(date);
    		coursechapter.setCreator(userId);
    		coursechapter.setIndex(i);
    		coursechapter.setPlan(Integer.valueOf(chapter.get("教学计划")));
    		totalplan+=Integer.valueOf(chapter.get("教学计划"));
    		coursechapter.setPlantotal(totalplan);
    		coursechapter.setOutline(chapter.get("教学大纲"));
    		if(chapter.get("作业") != null){
    			if(chapter.get("地址") != null){
    				//作业
    				questionService.uploadPaper(path+chapter.get("作业"), chapterId,0);
    				coursechapter.setStatus("0");
    				notfile.add(chapter.get("作业"));
    			}else{
    				//考试
    				String[] papers = chapter.get("作业").split(",");
    				for(String paper:papers){
    					questionService.uploadPaper(path+paper, chapterId,1);
    					notfile.add(paper);
    				}
    				coursechapter.setStatus("1");
    			}
    			
    		}else{
    			coursechapter.setStatus("2");
    		}
    		coursechapters.add(coursechapter);
    	}
    	//插入章节
    	courseMapper.insertCourseChapters(coursechapters);
    	//上传资源
    	List<String> localFilePaths = new ArrayList<String>();
    	File rootfile = new File(path);
    	for(File leaffile : rootfile.listFiles()){
    		if(!notfile.contains(leaffile.getName())){
    			localFilePaths.add(leaffile.getPath());
    		}
    	}
    	List files = new MCFtpClientFactory().config().bulidMCFtpClient(true).uploadFiles( localFilePaths);
    	List<CourseResource> courseResources = new ArrayList<CourseResource>();
    	for(Object file : files){
    		CourseResource courseResource = new CourseResource();
    		MCFtpFile mcfile = (MCFtpFile) file;
    		MCFile storefile = new MCFile();
    		String fileId = MD5.toMd5(UID.nextUid());
    		storefile.setFileId(fileId);
    		storefile.setFileName(mcfile.getFileName().split("-")[1]);
    		storefile.setFileSuffix(mcfile.getFileSuffix());
    		storefile.setFileSize((int)mcfile.getFileSize());
    		storefile.setFilePath(mcfile.getFilePath());
    		storefile.setFileTime(mcfile.getFileTime());
    		courseResource.setResourceId(fileId);
    		courseResource.setCourseId(courseId);
    		try {
    			fileService.insert(storefile);
    			courseResources.add(courseResource);
			} catch (ServiceException e) {
				throw new Exception();
			}
    	}
    	if(courseResources.size()>0){
    		courseMapper.insertCourseResource(courseResources);
    	}
	}
	//统计该课程是否在数据库中已经存在
	public int courseSelect(String courseName) {
		return courseMapper.courseSelect(courseName);
	}
	
	public List<Course> findAllCourse(){
		return courseMapper.findAllCourses();
	}
	
	public List<Course> findCourseByType(String type){
		return courseMapper.findCourseByType(type);
	}
	
	public Course findCourseTypeById(String typeId){
		return courseMapper.findCourseTypeById(typeId);
	}
	
	// 根据课程名称查询Id
	public Course findCourseByName(String courseName){
		return courseMapper.findCourseByName(courseName);
	}
	
	public List<CourseNote> findMyNotes(String userId,String courseId){
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("userId", userId);
		condition.put("courseId", courseId);
		return courseMapper.findMyNotes(condition);
	}
	
	public List<Course> searchCourse(String searchname){
		return courseMapper.searchCourse(searchname);
	}
	
	public int insertVideoHistory(Video video){
		return courseMapper.insertVideoHistory(video);
	}
	
	public Video findLatestVideoHistory(Video video){
		return courseMapper.findLatestVideoHistory(video);
	}
	
	public int addnote(CourseNote courseNote){
		return courseMapper.addnote(courseNote);
	}
	
	public int countcourseById(String courseId){
		return courseMapper.countcourseById(courseId);
	}
	
	public CourseCount countcourse(String userId){
		return courseMapper.countcourse(userId);
	}
	
	public int countcourseByCondition(String courseId,String classId){
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("courseId", courseId);
		condition.put("classId", classId);
		return courseMapper.countcourseByCondition(condition);
	}
	
	public int countcoursevideos(String courseId){
		return courseMapper.countcoursevideos(courseId);
	}
	
	public String getpercent(String userId,String courseId,String classId){
		int total =  paperMapper.getPaperListByCourseId(courseId).size();
		Map<String,String> condition = new HashMap<String,String>();
		condition.put("userId", userId);
		condition.put("classId", classId);
		int alreay = testMapper.queryTestRecordByUserIdandPaperId(condition).size();
		return alreay+"/"+total;
		
	}

}