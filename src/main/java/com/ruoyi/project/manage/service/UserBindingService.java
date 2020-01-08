package com.ruoyi.project.manage.service;

import java.util.Date;
import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.project.manage.mapper.UserBindingMapper;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.web.service.BaseService;

/**
 * 用户绑定Service业务层处理
 * 
 * @author yanbs
 * @date 2019-11-14
 */
@Service
public class UserBindingService extends BaseService
{
    @Autowired
    private UserBindingMapper userBindingMapper;
    

    /**
     * 修改用户绑定
     * 
     * @param userBinding 用户绑定
     * @return 结果
     */
    @Transactional
    public int updateUserBinding(User user)
    {
        return userBindingMapper.updateUserBinding(user);
    }


	public int selectYjbbOperExists(User user) {
		// TODO Auto-generated method stub
		return userBindingMapper.selectYjbbOperExists(user);
	}


	public int removeYjbbOper(String ids) {
		return userBindingMapper.removeYjbbOper(Long.parseLong(ids));
	}

  
}
