package com.ruoyi.project.manage.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.manage.service.UserBindingService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;
import com.ruoyi.project.system.user.service.UserServiceImpl;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户绑定Controller
 * 
 * @author yanbs
 * @date 2019-11-14
 */
@Controller
@RequestMapping("/manage/binding")
public class UserBindingController extends BaseController
{
    private String prefix = "manage/binding";
    
    @Autowired
    private UserBindingService userBindingService;
    @Autowired
    private IUserService userService;

    @RequiresPermissions("manage:binding:view")
    @GetMapping()
    public String binding()
    {
        return prefix + "/binding";
    }

    /**
     * 查询用户绑定列表
     */
    @RequiresPermissions("manage:binding:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(User user)
    {
        startPage();
        List<User> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    /**
     * 修改用户绑定
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap)
    {
        User user = userService.selectUserById(userId);
        mmap.put("user", user);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户绑定
     */
    @RequiresPermissions("manage:binding:edit")
    @Log(title = "用户绑定", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(User user)
    {
    	
        return toAjax(userBindingService.updateUserBinding(user));
    }
    
    @RequiresPermissions("manage:binding:edit")
    @Log(title = "用户解绑", businessType = BusinessType.UPDATE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userBindingService.removeYjbbOper(ids));
    }
    
    /**
     * 描述：去饮酒报备系统检查用户名密码是否正确
     * @param operator_id
     * @param password
     * @return 1：成功登录 -1:用户名不存在 -2:用户名与密码不匹配 -3:无法连接数据库 2:该账户已被绑定
     * @author yanbs
     * @Date 2019-11-14
     * 
     */
    @PostMapping( "/checkOperAndPwd")
    @ResponseBody
    public String checkOperAndPwd(String operator_id, String password, Long userId){
    	User user = new User();
    	user.setUserId(userId);
    	user.setYjbbOper(operator_id);
    	int isExists = userBindingService.selectYjbbOperExists(user);
    	if(isExists == 1){
    		return "2";
    	}
    	String param = "operator_id=" + operator_id + "&password=" + password;
    	String result = HttpUtils.sendPost("http://218.92.34.254:8091/sys/mobile/loginmi/checkOperAndPwd.do", param);
    	return result;
    }
}
