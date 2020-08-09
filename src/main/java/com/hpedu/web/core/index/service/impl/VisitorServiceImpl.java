package com.hpedu.web.core.index.service.impl;

import com.hpedu.web.core.index.service.VisitorService;
import com.hpedu.web.core.user.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
@Service
public class VisitorServiceImpl implements VisitorService{

	@Autowired
	UserMapper mapper ;
	
	@CachePut(value = "specialCache",key="#root.methodName",cacheManager = "specialCacheManager")
	@Override
	public int updateVisitorNum(int i) {
//		UserMapper dao = myBatisBase.getMapper( UserMapper.class);
//		mapper.updateVisitorNum(i);
		return i ;
	}





}
