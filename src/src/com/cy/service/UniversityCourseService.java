package com.cy.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.common.util.MD5;
import com.cy.common.util.UID;
import com.cy.exception.ServiceException;
import com.cy.mapper.CourseClassMapper;
import com.cy.mapper.CourseMapper;
import com.cy.mapper.UniversityCourseMapper;
import com.cy.model.CourseClass;
import com.cy.model.MyCourse;
import com.cy.model.Paper;
import com.cy.model.UniversityCourse;
import com.cy.model.User;

/**
 * 业务实现层 - 表：t_university_course
 * @since 2015-07-10 16:35:13
 */
@Service("universityCourseService")
public class UniversityCourseService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private UniversityCourseMapper universityCourseMapper;
	
	@Resource
	private CourseMapper courseMapper;
	@Resource
	private CourseClassMapper courseClassMapper;

	public void insert(UniversityCourse entity) throws ServiceException {
		try {
			universityCourseMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(UniversityCourse entity) throws ServiceException {
		try {
			universityCourseMapper.deleteByPrimaryKey(entity.getId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				universityCourseMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(UniversityCourse entity) throws ServiceException {
		try {
			universityCourseMapper.updateByPrimaryKey(entity);
			MyCourse mycourse = new MyCourse();
			mycourse.setId(MD5.toMd5(UID.nextUid()));
			mycourse.setClassId(entity.getClassId());
			mycourse.setCourseId(entity.getCourseId());
			mycourse.setClassName(entity.getCourseName());
			mycourse.setUserId(entity.getTeacherId());
			//删除这个课程的负责人的数据
			mycourse.setUserId(entity.getTeacherId());
			courseMapper.deleteCourseId(mycourse);
			//将本次课程设置的信息插入到mycourse表中
			courseMapper.addmycourse(mycourse);
			//更新courseclass，将表中的负责人改为本次设置的负责人
			CourseClass courseClass=new CourseClass();
			courseClass.setClassId(mycourse.getClassId());
			courseClass.setCourseId(mycourse.getCourseId());
			courseClass.setTeacherId(entity.getTeacherId());
			courseClass.setTeacherName(entity.getTeacherName());
			courseClassMapper.updateByClassId(courseClass);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(UniversityCourse entity) throws ServiceException {
		try {
			universityCourseMapper.updateByPrimaryKeySelective(entity);
			
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<UniversityCourse> findByPrimaryKey(UniversityCourse universityCourse) throws ServiceException {
		try {
			return universityCourseMapper.selectByPrimaryKey(universityCourse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*public Pager<UniversityCourse> findPage(Criteria<UniversityCourse> criteria) throws ServiceException {
		try {
			if (criteria == null) {
				 criteria = Criteria.create(UniversityCourse.class);
			}
			criteria.pagination(true);
			List<UniversityCourse> list = universityCourseMapper.selectByCriteria(criteria);
			return new Pager<UniversityCourse>(criteria, list);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
*/
	public List<UniversityCourse> findAll() throws ServiceException {
		try {

			return universityCourseMapper.selectAll();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	public List<UniversityCourse> finduniversityCourseDate(List<String> courseIdlist) throws ServiceException {
		try {

			return universityCourseMapper.finduniversityCourseDate(courseIdlist);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	public UniversityCourse findLearningByCourse(String classId) throws ServiceException {
		try {		
			return universityCourseMapper.findLearningByCourse(classId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	//修改课程开课的时间
	public void updatecourseTimeByCourseId(UniversityCourse courseId) throws ServiceException {
		try {
			universityCourseMapper.updatecourseTimeByCourseId(courseId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	/*课程搜索
	 * 
	 */
	public List<UniversityCourse> findcourseName(UniversityCourse universityCourse) throws ServiceException {
			try {

				return universityCourseMapper.findcourseName(universityCourse);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}

	public List<UniversityCourse> findAllByUniversity(UniversityCourse universityId) throws ServiceException {
		try {

			return universityCourseMapper.findAllByUniversity(universityId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
/* 根据学期搜索*/
	public List<UniversityCourse> findtermtime(UniversityCourse termStartTime) throws ServiceException {
		try {

			return universityCourseMapper.findtermtime(termStartTime);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	
	public String findCourseUniversity(String courseName,String universityId,String courseTerm) throws ServiceException {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("courseName", courseName);
			map.put("universityId", universityId);
			map.put("courseTerm", courseTerm);
			return universityCourseMapper.findCourseUniversity(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	
	public int insertForImport(String ucId,String courseId,String courseName,String universityId,String universityName,String courseTerm) throws ServiceException {
		try {
			Map<String,String> map=new HashMap<String,String>();
			map.put("id", ucId);
			map.put("courseId", courseId);
			map.put("courseName", courseName);
			map.put("universityId", universityId);
			map.put("universityName", universityName);
			map.put("courseTerm", courseTerm);
			
			return universityCourseMapper.insertForImport(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<UniversityCourse> searchcourseName(String courseTerm,String universityId) {
		
		return universityCourseMapper.searchcourseName(courseTerm,universityId);
	}

	public List<UniversityCourse> searchCourseterm(String universityId) {
		
		return universityCourseMapper.searchCourseterm(universityId);
	}

	public List<UniversityCourse> findAllBycourseTerm(UniversityCourse universityId) throws ServiceException {
		try {
		// TODO Auto-generated method stub
		return universityCourseMapper.findAllBycourseTerm(universityId);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
}

	public List<UniversityCourse> findThisBycourseTerm(
			UniversityCourse universityCourse) throws ServiceException {
		try {
			return universityCourseMapper.findThisBycourseTerm(universityCourse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public String findCourseIdByCondition(String courseName,String universityId,String courseTerm) throws ServiceException {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("courseName", courseName);
			map.put("universityId", universityId);
			map.put("courseTerm", courseTerm);
			return universityCourseMapper.findCourseIdByCondition(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<User> searchResult(String courseTerm,String universityId,String courseId,String realName) {
		
		return universityCourseMapper.searchResult(courseTerm,universityId,courseId,realName);
	}

	public List<Map<String, Object>> deletemsg(String courseTerm,
			String universityId, String courseId) {
		return universityCourseMapper.deletemsg(universityId,courseTerm,courseId);
	}

	public List<UniversityCourse> searchTeachercoursename(String universityId) {
		
		return universityCourseMapper.searchTeachercoursename(universityId);
	}

	public List<User> teacherSearch(String universityId, String courseId,String realName) {
		return universityCourseMapper.teacherSearch(universityId,courseId,realName);
	}

	public String selectbyfile(String universityName, String courseTerm,String courseName) {
		return universityCourseMapper.selectbyfile(universityName, courseTerm,courseName);
	}
	//删除t_university_course
	public void deletebyterm(UniversityCourse universityCourse) {
		universityCourseMapper.deletebyterm(universityCourse);
	}

	public UniversityCourse selectbyuniversityId(String universityId) {
		return universityCourseMapper.selectbyuniversityId(universityId);
	}
	/**
	 * @Description: 试卷列表
	 * @author pear
	 * @throws ServiceException
	 */
	public List<UniversityCourse> findpaperBycourseId(
			UniversityCourse universityCourse) throws ServiceException {
		try {
			return universityCourseMapper.findpaperBycourseId(universityCourse);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	
	}
	//将课程指定的期末考试的试卷插入到表中
	public void insertcoursePaper(UniversityCourse universityCourse) {
		//删除该课程已经选择的试卷
		universityCourseMapper.deletecoursePaper(universityCourse);
		//设置该课程期末考试的试卷
		universityCourse.setId(MD5.toMd5(UID.nextUid()));
		universityCourseMapper.insertcoursePaper(universityCourse);
	}

	public int insertUniversitycourse(List<UniversityCourse> universityCourselist) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return universityCourseMapper.insertUniversitycourse(universityCourselist);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
}

	public List<UniversityCourse> findAllCourse() throws ServiceException {
		try {
			return universityCourseMapper.findAllCourse();
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int courseStartStatus(String universityId, String courseTerm,
			String courseId) throws ServiceException {
		try {
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("universityId", universityId);
			map.put("courseTerm", courseTerm);
			map.put("courseId", courseId);
			return universityCourseMapper.courseStartStatus(map);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
		
	}
	/**
	 * 
	* @Title: examlist
	* @Description: 查询学生的考试列表
	* @param userId
	* @return
	 */
	public List<UniversityCourse> examlist(String userId)throws ServiceException {
		try {
		return universityCourseMapper.examlist(userId);
	}catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
	}
	}