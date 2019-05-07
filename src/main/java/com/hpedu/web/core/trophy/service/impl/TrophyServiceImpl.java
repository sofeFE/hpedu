package com.hpedu.web.core.trophy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.codeUtil.UUIDUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.trophy.dao.TrophyMapper;
import com.hpedu.web.core.trophy.pojo.StuImg;
import com.hpedu.web.core.trophy.pojo.Trophy;
import com.hpedu.web.core.trophy.service.TrophyService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class TrophyServiceImpl  extends ServiceImpl<TrophyMapper,Trophy> implements TrophyService {

	@Autowired
	private MyBatisBase myBatisBase ;
	
	@Override
	public List<Trophy> findAllTrophy(Map<String,Object> map)  {
		
		List<Trophy> trolist = baseMapper.findAllTrophy(map);
		return trolist;
	}
	@Override
	public Page<Trophy> searchTrophyList(Map<String, String> map,
			int pageNo, int pageSize)  {
		Page<Trophy> page = myBatisBase.queryPage("com.hpedu.web.core.trophy.dao.TrophyMapper.findAllTrophy",
				"com.hpedu.web.core.trophy.dao.TrophyMapper.searchTrophyListCount", map, pageNo, pageSize);
		return page;
	}
	@Override
	public void deleteTrophy(String id)  {
		
		baseMapper.deleteTrophy(id);
	}

	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,rollbackFor = {Exception.class})
	public void addTrophy(Trophy trophy, MultipartFile file,MultipartFile[] files)  {
		try {
			handleTrophy(trophy, files,file ) ;
			baseMapper.addTrophy(trophy);
			List<StuImg> stuList = trophy.getStuList();
			if (stuList.size() > 0) {
				insertStuImgs(stuList);
			}
		} catch (Exception e) {
			throw new RuntimeException() ;
		}

	}
	private void handleTrophy(Trophy trophy, MultipartFile[] files, MultipartFile pimgUrl1) throws Exception {

		List<StuImg> imgList = new ArrayList<StuImg>();

		myBatisBase.upload("/trophyImg/", pimgUrl1, trophy, Trophy.class, "setPimgUrl", String.class);

		String id = StringUtils.isBlank(trophy.getPid()) ? UUIDUtil.getUUID() : trophy.getPid();
		trophy.setPid(id);
		trophy.setSort(String.valueOf(new Date().getTime()));

		if (files != null) {
			Long sort = new Date().getTime();
			int i = 0;
			StuImg img;
			for (MultipartFile file : files) {//学员答案
				img = new StuImg();
				myBatisBase.upload("/trophyImg/", file, img, StuImg.class, "setStUrl", String.class);
				img.setStid(UUIDUtil.getUUID());
				img.setSort(String.valueOf(sort + i++));

				img.setTpid(id);
				imgList.add(img);
				img = null;
			}
		}
		trophy.setStuList(imgList);
	}
	@Override
	public Trophy findTrophyById(String id)  {
		
		Trophy trophy = baseMapper.findTrophyById(id);
		return trophy;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED ,rollbackFor = {Exception.class})
	public void updateTrophy(Trophy trophy, MultipartFile file, MultipartFile[] files, String[] delImhid)  {
		try {
			handleTrophy(trophy, files, file);
			baseMapper.updateTrophy(trophy);
			List<StuImg> stuList = trophy.getStuList();
			if (stuList.size() > 0) {
				insertStuImgs(stuList);
			}
			if (delImhid != null && delImhid.length > 0) {
				deleteStuImgs(delImhid);
			}
			
			
		} catch (Exception e) {
			throw new RuntimeException() ;
		}
	}

	@Override
	@Transactional(readOnly = false ,propagation = Propagation.REQUIRED)
	public int updateTrophySort(String id1, String sort1, String id2, String sort2) {
		List<Trophy> list = new ArrayList<>();
		list.add( new Trophy().setPid(id1).setSort(sort1));
		list.add( new Trophy().setPid(id2).setSort(sort2));
		return baseMapper.updateTrophySort(list);
	}
	@Override
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED)
	public int insertStuImgs(List<StuImg> list) {
		
		return baseMapper.insertStuImgs(list);
	}
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED ,rollbackFor = {Exception.class})
	public int deleteStuImgs(String[] list) {
		
		return baseMapper.deleteStuImgs(list);
	}
	@Override
	public List<StuImg> selectStuImgByTpid(String tpid) {
		
		return baseMapper.selectStuImgByTpid(tpid);
	}

}
