package com.hpedu.web.core.index.task;

import com.hpedu.web.core.index.service.VisitorService;
import org.springframework.stereotype.Component;

/**
 * 定时任务类
 * 定期将人数更新到数据库.
 * @author Administrator
 */
@Component
public class VisitorTask {
	
//	@Autowired
	private VisitorService visitorService ;



}
