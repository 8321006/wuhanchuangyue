package com.cy.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseTeacherAnalysisMapper;
import com.cy.model.CourseTeacherAnalysis;

/**
 * 业务实现层 - 表：t_course_teacher_analysis
 * @since 2015-07-29 11:12:42
 */
@Service("courseTeacherAnalysisService")
public class CourseTeacherAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseTeacherAnalysisMapper courseTeacherAnalysisMapper;

	@Autowired
	private CourseLearnAnalysisService courseLearnAnalysisService;
	
	public int insert(CourseTeacherAnalysis entity) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int delete(CourseTeacherAnalysis entity) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseTeacherAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseTeacherAnalysis entity) throws ServiceException {
		try {
			courseTeacherAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int updateSelective(CourseTeacherAnalysis entity) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseTeacherAnalysis findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public CourseTeacherAnalysis getTotalByDay(String classId) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.getTotalByDay(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public CourseTeacherAnalysis findLastAnalysisByClass(String classId) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.findLastAnalysisByClass(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<String> findAllClass() throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.findAllClass();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public String findClassByTeacherAndCourse(String courseId, String teacherId) throws ServiceException {
		try {
			return courseTeacherAnalysisMapper.findClassByTeacherAndCourse(courseId, teacherId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public List<CourseTeacherAnalysis> findByTimebucket(String classId,String startTime) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.findByTimebucket(classId, startTime);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int countStudentsLess(String classId,int less) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.countStudentsLess(classId, less);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countFinishInClass(String classId) throws ServiceException{
		try {
			Integer res = courseTeacherAnalysisMapper.countFinishInClass(classId);
			return res == null ? 0 :res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countStudentsLessToMore(String classId,int less,int more) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.countStudentsLessToMore(classId, less, more);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countStudentsMore(String classId,int more) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.countStudentsMore(classId, more);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countStudentsInClass(String classId) throws ServiceException{
		try {
			Integer res =  courseTeacherAnalysisMapper.countStudentInClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countSixInClass(String classId) throws ServiceException{
		try {
			Integer res =  courseTeacherAnalysisMapper.countSixInClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countEightInClass(String classId) throws ServiceException{
		try {
			Integer res =  courseTeacherAnalysisMapper.countEightInClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countTenInClass(String classId) throws ServiceException{
		try {
			Integer res =  courseTeacherAnalysisMapper.countTenInClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int calAvgLearningRate(String classId) throws ServiceException{
		try {
			Integer res = courseTeacherAnalysisMapper.calAvgLearningRate(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int calAvgScoreInClass(String classId) throws ServiceException{
		try {
			Integer res = courseTeacherAnalysisMapper.calAvgScoreInClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public int countPassInClass(String classId) throws ServiceException{
		try {
			Integer res = courseTeacherAnalysisMapper.countPassInClass(classId);
			return res == null ? 0 : res;
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public Map<String, Object> countStudentByScoure(String classId,int less,int more) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.countStudentByScoure(classId, less, more);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public Map<String, Object> calRateByScore(String classId,int less,int more) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.calRateByScore(classId, less, more);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<String> getTermsByUniversity(String universityId) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.getTermsByUniversity(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, String>> getClassByTermAndUniversity(String universityId,String courseTerm) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.getClassByTermAndUniversity(universityId, courseTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<Map<String, String>> getTeacherAndClass() throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.getTeacherAndClass();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public CourseTeacherAnalysis getCurrentDay(String classId) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.getCurrentDay(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseTeacherAnalysis getTotalToInClass(String classId) throws ServiceException{
		try {
			return courseTeacherAnalysisMapper.getTotalToInClass(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public void updateClassJob(String classId,String courseId,String teacherId){
		System.out.println("CourseTeacherAnalysisService.updateClassJob()");
		try {
			courseLearnAnalysisService.updateInClassJob(classId);
			CourseTeacherAnalysis res = getCurrentDay(classId);
			CourseTeacherAnalysis entity = getTotalToInClass(classId);
			if(entity == null){
				entity = new CourseTeacherAnalysis();
				entity.setAnalysisTime(new Date());
				entity.setClassId(classId);
				entity.setTeacherId(teacherId);
				entity.setCourseId(courseId);
				entity.setLearnRate(calAvgLearningRate(classId));
				entity.setExamCount(calAvgScoreInClass(classId));
			}
			if(res == null){
				insert(entity);
			}else{
				entity.setId(res.getId());
				entity.setLearnRate(calAvgLearningRate(classId));
				entity.setExamCount(calAvgScoreInClass(classId));
				update(entity);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void updateAllJob(){
		System.out.println("CourseTeacherAnalysisService.updateAllJob()");
		try {
			//查询当前开课的班级Id、课程Id，老师Id的信息
			List<Map<String, String>> tc = getTeacherAndClass();
			for (Map<String, String> map : tc) {
				String classId = map.get("classId");
				String teacherId = map.get("teacherId");
				String courseId = map.get("courseId");
				//System.out.println("classId ==" + classId);
				updateClassJob(classId, courseId, teacherId);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}