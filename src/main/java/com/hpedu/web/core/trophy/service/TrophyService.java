package com.hpedu.web.core.trophy.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.trophy.pojo.StuImg;
import com.hpedu.web.core.trophy.pojo.Trophy;
import org.springframework.web.multipart.MultipartFile;

public interface TrophyService extends IService<Trophy> {
	/**
	 * 查询所有奖杯
	 * */
	List<Trophy> findAllTrophy(Map<String,Object> map);
	/**
	 * 根据ID删除奖杯
	 * */
	void deleteTrophy(String id);
	/**
	 * 新增奖杯
	 * */
	void addTrophy(Trophy trophy, MultipartFile file,MultipartFile[] files);
	/**
	 * 根据ID查询奖杯信息
	 * */
	Trophy findTrophyById(String id);
	/**
	 * 修改奖杯信息
	 * */
	void updateTrophy(Trophy trophy, MultipartFile file, MultipartFile[] files, String[] delImhid);
	 /**
   	 *  修改学员信息显示顺序
   	 * 
   	 * */
     int updateTrophySort(String id1, String sort1, String tid, String sort);
     
     Page<Trophy> searchTrophyList(Map<String, String> map,
 			int pageNo, int pageSize) ;
     
     /**
 	 * 批量新增测验题和答案
 	 * 
 	 * */
 	int  insertStuImgs(List<StuImg> list);
 	/**
 	 * 批量删除测验题和答案
 	 * 
 	 * */
 	int  deleteStuImgs(String[] list);
 	
 	List<StuImg> selectStuImgByTpid(String tpid);

}
