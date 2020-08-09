package com.hpedu.web.core.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.video.dao.ContestVideoMapper;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.VideoPdf;
import com.hpedu.web.core.video.service.ContestVideoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ContestVideoServiceImpl extends ServiceImpl<ContestVideoMapper,ContestVideo> implements ContestVideoService {
	@Autowired
	private MyBatisBase myBatisBase ;
	
	
	@Override
	@Cacheable(value="welcomePage"/*,key="contestList"*/)
	public List<ContestVideo> findContestVideoIndex()  {
		
		List<ContestVideo> conlist = baseMapper.findContestVideoIndex();
		return conlist;
	}

	@Override
	public ContestVideo findContestVideoById(String cId)  {
		
		ContestVideo contestVideo = baseMapper.findContestVideoById(cId);
		return contestVideo;
	}

	@Override
	public List<ContestVideo> findContestVideoByVideo(String competitionName,
			String cclass, String cclassify,String vid)  {
		
		List<ContestVideo> conlist  = baseMapper.findContestVideoByVideo(competitionName, cclass, cclassify,vid);
		return conlist;
	}

	@Override
	public Page<ContestVideo> searchContestVideoList(Map<String, String> map,
			int pageNo, int pageSize)  {
		Page<ContestVideo> page =myBatisBase.queryPage("com.hpedu.web.core.video.daoTest.ContestVideoMapper.searchContestVideoList",
				"com.hpedu.web.core.video.daoTest.ContestVideoMapper.searchContestVideoListCount", map, pageNo, pageSize);
		return page;
	}

	@Override
	public void addUSAClass(ContestVideo contestVideo)  {
		
		baseMapper.addUSAClass(contestVideo);
	}

	@Override
	public void updateUSAClass(ContestVideo contestVideo)  {
		
		baseMapper.updateUSAClass(contestVideo);
	}

	@Override
	public void deleteUSAClass(String cid)  {
		
		baseMapper.deleteUSACLass(cid);
	}

	@Override
	public List<ContestVideo> searchContestVideoListByCname(String cname)
			 {
		
		return baseMapper.searchContestVideoListByCname(cname);
	}

	@Override
	public List<ContestVideo> findVideoListByExam(Map<String, Object> map)
			 {
		
		return baseMapper.findVideoListByExam(map);
	}

	@Override
	public List<VideoPdf> selectPdfByVid(@Param("vid") String vid,@Param("type") String type) {
		
		return baseMapper.selectPdfByVid(vid, type);
	}

	@Override
	public int updateIsKill(String vid, String status) {
		
		return baseMapper.updateIsKill(vid, status);
	}

	@Override
	public int updatePlayCount(String vid) {
		
		return baseMapper.updatePlayCount(vid);
	}

}
