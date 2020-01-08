package com.ruoyi.project.manage.mapper;

import com.ruoyi.project.system.user.domain.User;

import java.util.List;

/**
 * 用户绑定Mapper接口
 * 
 * @author yanbs
 * @date 2019-11-14
 */
public interface UserBindingMapper 
{
	public int updateUserBinding(User user);

	public int selectYjbbOperExists(User user);

	public int removeYjbbOper(Long userId);
}
