package com.hpedu.web.core.index.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.web.core.index.service.VisitorService;
import com.hpedu.web.core.user.dao.UserMapper;
@Service
public class VisitorServiceImpl implements VisitorService{
	@Autowired
	private MyBatisBase myBatisBase ;
	
	@Autowired
	UserMapper mapper ;
	
	/**更新一般是 从缓存往数据库更新, 所以此处不用更新缓存*/
//	@CachePut("")
	@Override
	public void updateVisitorNum(int i) {
//		UserMapper mapper = myBatisBase.getMapper( UserMapper.class);
		mapper.updateVisitorNum(i);
	}

}
