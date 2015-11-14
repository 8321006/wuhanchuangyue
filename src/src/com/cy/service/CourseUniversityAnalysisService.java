package com.cy.service;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.CourseUniversityAnalysisMapper;
import com.cy.model.CourseUniversityAnalysis;

/**
 * 业务实现层 - 表：t_course_university_analysis
 * @since 2015-08-19 16:56:37
 */
@Service("courseUniversityAnalysisService")
public class CourseUniversityAnalysisService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private CourseUniversityAnalysisMapper courseUniversityAnalysisMapper;
	@Autowired
	private CourseTeacherAnalysisService courseTeacherAnalysisService;

	public void insert(CourseUniversityAnalysis entity) throws ServiceException {
		try {
			courseUniversityAnalysisMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(CourseUniversityAnalysis entity) throws ServiceException {
		try {
			courseUniversityAnalysisMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<Integer> ids) throws ServiceException {
		try {
			for (Integer id : ids) {
				courseUniversityAnalysisMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(CourseUniversityAnalysis entity) throws ServiceException {
		try {
			courseUniversityAnalysisMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(CourseUniversityAnalysis entity) throws ServiceException {
		try {
			courseUniversityAnalysisMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public CourseUniversityAnalysis findByPrimaryKey(Integer id) throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public CourseUniversityAnalysis getOneByClass(String universityId,String classId,String courseTerm) throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getOneByClass(universityId, classId,courseTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<CourseUniversityAnalysis> getAllGroup(String universityId) throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getAllGroup(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<CourseUniversityAnalysis> getAnalysisByTerm(String universityId,String courseTerm ) throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getAnalysisByTerm(universityId, courseTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<CourseUniversityAnalysis> getAllByTerm(String courseTerm ) throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getAllByTerm(courseTerm);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<String> getAllTerm(String universityId ) throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getAllTerm(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<String> getTerms() throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getTerms();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	public List<String> getAllUniversity() throws ServiceException {
		try {
			return courseUniversityAnalysisMapper.getAllUniversity();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public void updateAllGroup(String universityId) throws ServiceException {
		System.out.println("CourseUniversityAnalysisService.updateAllGroup()");
		try {
			/*
			 * courseTeacherAnalysisService方法--
			 */
			courseTeacherAnalysisService.updateAllJob();
			List<CourseUniversityAnalysis> groups = getAllGroup(universityId);
			for (CourseUniversityAnalysis item : groups) {
				System.out.println("item >>>>>>>>>>>>>>> " + item);
					String classId = item.getClassId();
					String courseTerm = item.getCourseTerm();
					CourseUniversityAnalysis analysis = getOneByClass(universityId, classId,courseTerm);
					System.out.println("analysis >>>> " + analysis);
					if(analysis == null){
					int total = courseTeacherAnalysisService.countStudentsInClass(classId);
					item.setImportCount(total);
					int fin = courseTeacherAnalysisService.countFinishInClass(classId);
					item.setFinishCount(fin);
					int pass = courseTeacherAnalysisService.countPassInClass(classId);
					item.setPassCount(pass);
					item.setFailCount(total - pass);
					item.setScoreSix(courseTeacherAnalysisService.countSixInClass(classId));
					item.setScoreEight(courseTeacherAnalysisService.countEightInClass(classId));
					item.setScoreTen(courseTeacherAnalysisService.countTenInClass(classId));
						insert(item);
					}else{
						//item.setId(analysis.getId());
						update(analysis);
					}
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateAllJob() {
		try {
			List<String> uList = getAllUniversity();
			for (String universityId : uList) {
			updateAllGroup(universityId);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}