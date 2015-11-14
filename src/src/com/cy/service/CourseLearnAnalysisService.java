package com.cy.service;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.util.StringUtil;
import com.cy.exception.ServiceException;
import com.cy.mapper.CourseLearnAnalysisMapper;
import com.cy.model.Course;
import com.cy.model.CourseClass;
import com.cy.model.CourseLearnAnalysis;
import com.cy.model.User;
/**
 * 业务实现层 - 表：t_course_learn_analysis
 * 
 * @since 2015-07-20 16:33:23
 */
@Service("courseLearnAnalysisService")
public class CourseLearnAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Resource
	private CourseLearnAnalysisMapper courseLearnAnalysisMapper;
	
	@Autowired
	private UniversityCourseService universityCourseService;
	@Autowired
	private CourseClassService courseClassService;
	@Autowired
	private UserService userService;
	@Autowired
	private TestService testService;
	@Autowired
	private CourseService courseService;

	public int insert(CourseLearnAnalysis entity) throws ServiceException {
		try {
			return courseLearnAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int delete(CourseLearnAnalysis entity) throws ServiceException {
		try {
			return courseLearnAnalysisMapper.deleteByPrimaryKey(entity.getLearnId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseLearnAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseLearnAnalysis entity) throws ServiceException {
		try {
			courseLearnAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseLearnAnalysis entity)
			throws ServiceException {
		try {
			courseLearnAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseLearnAnalysis findByPrimaryKey(Integer learnId)
			throws ServiceException {
		try {
			return courseLearnAnalysisMapper.selectByPrimaryKey(learnId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * description : 汇总课程的章节数
	 * @param courseId
	 * @throws ServiceException
	 * @return int
	 * @version : 2015年7月21日 上午8:53:30
	 */
	public int countChaptersByCourseId(String courseId) throws ServiceException {
		try {
			if (StringUtil.isNotEmpty(courseId)) {
				return courseLearnAnalysisMapper
						.countChaptersByCourseId(courseId);
			}
			return 0;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/**
	 * 
	 * description : 根据课程id和学生id查找对应的班级id
	 * @param courseId
	 * @param userId
	 * @throws ServiceException
	 * @return String
	 * @version : 2015年7月21日 上午8:54:09
	 */
/*	public String findLearningClassIdByCourseId(String courseId, String userId)
			throws ServiceException {
		return findClassIdByCourseId(courseId, userId, COURSE_LEARNING);
	}
	public String findFinishClassIdByCourseId(String courseId, String userId)
			throws ServiceException {
		return findClassIdByCourseId(courseId, userId, COURSE_FINISHED);
	}*/
	
/*	private String findClassIdByCourseId(String courseId, String userId,int courseType) throws ServiceException{
		try {
				return courseLearnAnalysisMapper.findClassIdByCourseId(courseId,userId,courseType);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}*/
	
	/**
	 * 
	 * description : 汇总指定课程已完成的人数
	 * @param courseId
	 * @throws ServiceException
	 * @return int
	 * @version : 2015年7月21日 上午8:55:06
	 */
/*	public int countFinishStudentsByCourseId(String courseId)
			throws ServiceException {
		return countStudentsByCourseId(courseId, COURSE_FINISHED);
	}

	*//**
	 * 
	 * description : 汇总指定课程正在学习的人数
	 * @param courseId
	 * @throws ServiceException
	 * @return int
	 * @version : 2015年7月21日 上午8:55:49
	 *//*
	public int countLearningStudentsByCourseId(String courseId)
			throws ServiceException {
		return countStudentsByCourseId(courseId, COURSE_LEARNING);
	}
	*//**
	 * 
	 * description : 汇总指定课程被收藏的人数
	 * @param courseId
	 * @throws ServiceException
	 * @return int
	 * @version : 2015年7月21日 上午8:56:08
	 *//*
	public int countFavoriteStudentsByCourseId(String courseId)
			throws ServiceException {
		return countStudentsByCourseId(courseId, COURSE_FAVORITE);
	}
	*/
	
	/*private int countStudentsByCourseId(String courseId, int courseType)
			throws ServiceException {
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if (StringUtil.isNotEmpty(courseId)) {
				args.put("courseId", courseId);
				args.put("courseType", courseType);
				return courseLearnAnalysisMapper.countStudentsByCourseId(args);
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	*/

	public int countTasksByClassAndUser(String classId,String userId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.countTasksByClassAndUser(classId, userId);
			return res == null ? 0 : res ;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * description : 统计学习进度 %
	 * @param courseId
	 * @param userId
	 * @throws ServiceException
	 * @return int
	 * @version : 2015年7月22日 上午10:12:09
	 */
	public int calPersonalLearningRate(String courseId,String classId, String userId) throws ServiceException{
		
		try {
			Integer res = courseLearnAnalysisMapper.calPersonalLearningRate(userId, classId, courseId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseClass findByCourseId(String courseId) throws ServiceException{
		try {
			if(StringUtil.isNotEmpty(courseId)){			
				return courseLearnAnalysisMapper.findByCourseId(courseId);
			}
			return null;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int countPaperByCourse(String courseId) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.countPaperByCourse(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * description : 统计提交的评论
	 * @param classId
	 * @param userId
	 * @throws ServiceException
	 * @version : 2015年7月22日 上午9:41:34
	 */
	public int countCommentPostByCourse(String classId,String userId) throws ServiceException{
		try {
			Map<String, Object> args = new HashMap<String, Object>();
			if(StringUtil.isNotEmpty(classId)){
				args.put("classId", classId);
			}
			if(StringUtil.isNotEmpty(userId)){
				args.put("userId", userId);
			}
			return courseLearnAnalysisMapper.countCommentPostsByCourse(args);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	/**
	 * 
	 * description : 统计评论回复
	 * @param classId
	 * @param userId
	 * @throws ServiceException
	 * @return int
	 * @version : 2015年7月22日 上午9:42:17
	 */
	public int countCommentByClassAndUser(String classId,String userId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.countCommentByClassAndUser(userId, classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int countQuizsByClassAndUser(String userId,String classId) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.countQuizsByClassAndUser(userId, classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int countFinishByClass(String classId) throws ServiceException {		
		try {
			return courseLearnAnalysisMapper.countFinishByClass(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<Map<String, Object>> countStudentAndClass() throws ServiceException{
		try {
			return courseLearnAnalysisMapper.countStudentAndClass();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, Object>> countStudentGroup(String classId) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.countStudentGroup(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int getRankLearningInClass(String classId,String userId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.getRankLearningInClass(classId, userId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int getRankCommentInClass(String classId,String studentId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.getRankCommentInClass(classId,studentId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int getRankQuizInClass(String classId,String studentId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.getRankQuizInClass(classId,studentId);
				return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int getRankTaskInClass(String classId,String studentId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.getRankTaskInClass(classId,studentId);
				return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int getRankVideoInClass(String classId,String studentId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.getRankVideoInClass(classId,studentId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseLearnAnalysis getLastAnalysisByCourseAndUser(String classId,String studentId) throws ServiceException{
		try {
				return courseLearnAnalysisMapper.getLastAnalysisByCourseAndUser(studentId, classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public CourseLearnAnalysis getCurrentDay(String classId,String userId) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.getCurrentDay(userId, classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public CourseLearnAnalysis getTotalToByUserInClass(String userId,String classId,String courseId) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.getTotalToByUserInClass(userId, classId, courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<CourseLearnAnalysis> findByTimebucket(String studentId,String classId,String startTime) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.findByTimebucket(studentId,classId,startTime);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public Integer getExamScoreByUser(String classId,String userId) throws ServiceException{
		try {
			Integer score = courseLearnAnalysisMapper.getExamScoreByUser(userId, classId);
				return score == null ? 0 : score;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int getRankExamInClass(String classId,String userId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.getRankExamInClass(classId, userId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<CourseLearnAnalysis> getLastInClass(String classId) throws ServiceException{
		try {
			return courseLearnAnalysisMapper.getLastInClass(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countVideoMins(String userId,String classId) throws ServiceException{
		try {
			Integer mins = courseLearnAnalysisMapper.countVideoMins(userId, classId);
			return mins == null ? 0 : mins;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int countStudentsByClass(String classId) throws ServiceException{
		try {
			Integer res = courseLearnAnalysisMapper.countStduentByClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/**
	 * 
	 * description : 更新个人的当天汇总记录
	 * @param classId 班级id
	 * @param courseId 课程id
	 * @param userId 用户id
	 * @return void
	 * @version : 2015年8月25日 下午3:25:38
	 */
	public void updatePersonalJob(String classId,String courseId,String userId){
		try {
			System.out.println("CourseLearnAnalysisService.updatePersonalJob()");
			//CourseLearnAnalysis analysis = getTotalToByUserInClass(userId, classId, courseId);
			//getCurrentDay--查询t_course_learn_analysis--查询当前学生在当前班级的数据
			CourseLearnAnalysis result = getCurrentDay(classId, userId);
			//findByPrimaryKey---当前学生的信息
			User user = userService.findByPrimaryKey(userId);
			//System.out.println("user getRealName>>==>>>>>>>>>>>> " + user.getRealName());
			//findByPrimaryKey--查询当前课程的信息以及选课人数
			Course course = courseService.findByPrimaryKey(courseId);
			if(result == null){
				result = new CourseLearnAnalysis();
				result.setClassId(classId);
				result.setStudentId(userId);
				result.setStudentName(user.getRealName());
				result.setCourseId(courseId);
				result.setClassName(course.getCourseName());
				result.setAnalysisTime(new Date());
				result.setLearnRate(calPersonalLearningRate(courseId, classId,userId));
				result.setCommentCount(countCommentByClassAndUser(userId, classId));
				result.setQuizCount(countQuizsByClassAndUser(userId, classId));
				result.setTaskCount(countTasksByClassAndUser(classId, userId));
				result.setExamCount(getExamScoreByUser(userId, classId));
				result.setVideoCount(countVideoMins(userId, classId));
				result.setTotalScore(Math.round(testService.getScoreByCourseAndUser(courseId, classId, userId, user.getUniversityId()).getTotalScore()));
				//将信息插入到t_course_learn_analysis表中
				insert(result);
			}else{
				result.setStudentName(user.getRealName());
				result.setLearnRate(calPersonalLearningRate(courseId, classId,userId));
				result.setCommentCount(countCommentByClassAndUser(userId, classId));
				result.setQuizCount(countQuizsByClassAndUser(userId, classId));
				result.setTaskCount(countTasksByClassAndUser(classId, userId));
				result.setExamCount(getExamScoreByUser(userId, classId));
				result.setVideoCount(countVideoMins(userId, classId));
				result.setTotalScore(Math.round(testService.getScoreByCourseAndUser(courseId, classId, userId, user.getUniversityId()).getTotalScore()));
				//更新t_course_learn_analysis
				updateSelective(result);
			}
			//System.out.println("result  ==== >>>>>>>>>>>>  " + result);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * description : 更新所有的个人汇总记录
	 * @return void
	 * @version : 2015年8月25日 下午3:28:10
	 */
	public void updateAllJob(){
		System.out.println("CourseLearnAnalysisService.doUpdateJob()");
		try {
			List<Map<String, Object>> list = countStudentAndClass();
			for (Map<String, Object> map : list) {
				String userId = (String) map.get("userId");
				String classId = (String) map.get("classId");
				String courseId = (String) map.get("courseId");
				updatePersonalJob(classId, courseId, userId);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	public void updateInClassJob(String classId){
		//System.out.println("CourseLearnAnalysisService.doUpdateJob()");
		try {
			//countStudentGroup---当前开课班级学生列表
			List<Map<String, Object>> list = countStudentGroup(classId);
			for (Map<String, Object> map : list) {
				String userId = (String) map.get("userId");
				String courseId = (String) map.get("courseId");
				updatePersonalJob(classId, courseId, userId);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}

}