package com.ruoyi.project.monitor.job.task;


import org.springframework.stereotype.Component;

/**
 * @author 新建
 *
 */
@Component("myTestTask")
public class MyTestTask {

	public void myTest1(){
		System.out.println("自定义任务。。。。。。。。。。。。。。");
	}

}
