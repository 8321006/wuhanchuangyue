package com.cy.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cy.exception.ServiceException;
import com.cy.mapper.UserMapper;
import com.cy.model.User;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

/**
 * 业务实现层 - 表：t_user
 * @since 2015-06-08 15:47:16
 */
@Service("userService")
public class UserService implements Serializable {
	private static final long serialVersionUID = 1L;

	@Resource
	private UserMapper userMapper;

	public void insert(User entity) throws ServiceException {
		try {
			userMapper.insert(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void delete(User entity) throws ServiceException {
		try {
			userMapper.deleteByPrimaryKey(entity.getUserId());
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Transactional
	public void batchDelete(List<String> ids) throws ServiceException {
		try {
			for (String id : ids) {
				userMapper.deleteByPrimaryKey(id);
			}
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void update(User entity) throws ServiceException {
		try {
			userMapper.updateByPrimaryKey(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void updateSelective(User entity) throws ServiceException {
		try {
			userMapper.updateByPrimaryKeySelective(entity);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public User findByPrimaryKey(String userId) throws ServiceException {
		try {
			return userMapper.selectByPrimaryKey(userId);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	public int insertUserList(List<User> userList) throws ServiceException {
		try {
			return userMapper.insertUserList(userList);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public User selectUser(User user) {
		// TODO Auto-generated method stub
		return this.userMapper.selectUser(user);
	}

	public User findByphone(String phone){
		return userMapper.selectByphone(phone);
	}
	
	public void importOpenFire(User user){
	}

	public List<User> findteacher(User user) {
		// TODO Auto-generated method stub
		return this.userMapper.selectTecher(user);
	}

	public List<User> findByTeacherName(User user) throws ServiceException {
		try {
			return userMapper.selectByTeacherName(user);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public List<User> findbyuserid(String realname) throws ServiceException {
		try {
			return userMapper.findbyuserid(realname);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
	
	//查询学校所有用户
	public List<User> selectUserBySchool(String universityId,String role)throws ServiceException{
			
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("universityId", universityId);
				map.put("role", role);
				/*map.put("courseId", courseId);
				map.put("realName", realName);
				map.put("courseTerm", courseTerm);*/
				return userMapper.selectUserListbySchool(map);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
		
	
	//查询学校所有用户总数
	public String selectUserCountBySchool(String universityId,String role)throws ServiceException{
			
			try {
				HashMap map=new HashMap();
				map.put("universityId", universityId);
				map.put("role", role);
				return userMapper.selectUserListCountbySchool(map);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}
	
		//按课程查询学校所有学生
	public List<User> selectUser(String courseId,String universityId){
			
			return null;
		}
		
	
	public String findbyLoginName(String loginName)throws ServiceException{
		
		try {
			return userMapper.findbyLoginName(loginName);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public int insertTeacher(User user) {
		return this.userMapper.insertTeacher(user);
		
	}
	
	
public List<User> findbyPhone(String phone)throws ServiceException{
		
		try {
			return userMapper.findbyPhone(phone);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

public List<User> findBySchoolCode(User user) throws ServiceException {
	try {
		return userMapper.findBySchoolCode(user);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
}
public void deletebyClassId(String classId) throws ServiceException {
	try {
		userMapper.deletebyClassId(classId);;
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
}

public boolean loginOrBindByQQ(HttpServletRequest request) throws ServiceException {
	System.out.println("===================》》进入qq登录的方法");
    try {
        AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
        String accessToken   = null,
               openID        = null;
        long tokenExpireIn = 0L;
        if (accessTokenObj.getAccessToken().equals("")) {
        	//	                我们的网站被CSRF攻击了或者用户取消了授权
        	//	                做一些数据统计工作
            System.out.print("没有获取到响应参数");
            return true;
        } else {
            accessToken = accessTokenObj.getAccessToken();
            tokenExpireIn = accessTokenObj.getExpireIn();

            request.getSession().setAttribute("demo_access_token", accessToken);
            request.getSession().setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

            // 利用获取到的accessToken 去获取当前用的openid -------- start
            OpenID openIDObj =  new OpenID(accessToken);
            openID = openIDObj.getUserOpenID();
            request.getSession().setAttribute("demo_openid", openID);
            // 利用获取到的accessToken 去获取当前用户的openid --------- end

            //利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息
            UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
            if (userInfoBean.getRet() == 0) {
            	// 用户头像
                request.getSession().setAttribute("user_logo", userInfoBean.getAvatar().getAvatarURL50());
            	// 查询用户是否已经绑定qq
            	List<User> list = findByUserQQ(openID);
            	if(list != null && list.size()!=0){
            		// 已经绑定了qq，则返回User对象，跳转到首页
            		request.getSession().setAttribute("user", list.get(0));
            		return true;
            	}else{
            		// 没有绑定qq，跳转到绑定页面
            		return false;
            	}
            } else {
                //out.println("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
            	return true;
            }

        }
    } catch (QQConnectException e) {
    	throw new ServiceException(e.getMessage(), e);
    }
}

/**
 * 
* @Title: findByUserQQ 
* @Description: 根据qq查询用户是否已经绑定 
* @param qq
* @return
* @throws ServiceException
 */
public List<User> findByUserQQ(String qq) throws ServiceException {
	try {
		return userMapper.findByUserQQ(qq);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
}

public String findbyStudentId(String studentId)throws ServiceException{
	
	try {
		return userMapper.findbystudentId(studentId);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
}

public List<User> selectTeacherBySchool(String universityId,String role,String courseId,String realName,String courseTerm)throws ServiceException{
			
			try {
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("universityId", universityId);
				map.put("role", role);
				map.put("courseId", courseId);
				map.put("realName", realName);
				map.put("courseTerm", courseTerm);
				return userMapper.selectTeacherListbySchool(map);
			} catch (Exception e) {
				throw new ServiceException(e.getMessage(), e);
			}
		}

public int updatebyPhone(User user) {
	return userMapper.updatebyPhone(user);
}

public User selectByphone(String phone)throws ServiceException{
		
		try {
			return userMapper.selectByphone(phone);
		} catch (Exception e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}
//判断当前用户是否已经存在，避免重复导入
public String finduserBystudentId(User user) throws ServiceException {
	try {
		return userMapper.finduserBystudentId(user);
	} catch (Exception e) {
		throw new ServiceException(e.getMessage(), e);
	}
 }
}
	