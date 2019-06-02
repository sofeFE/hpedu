package com.hpedu.web.core.trophy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.trophy.pojo.StuImg;
import com.hpedu.web.core.trophy.pojo.Trophy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TrophyMapper extends BaseMapper<Trophy> {

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
	void addTrophy(Trophy trophy);
	/**
	 * 根据ID查询奖杯信息
	 * */
	Trophy findTrophyById(String id) ;
	/**
	 * 修改奖杯信息
	 * */
	void updateTrophy(Trophy trophy);
	
	 /**
   	 *  修改学员信息显示顺序
   	 * 
   	 * */
     int updateTrophySort(List<Trophy> list);
     
    
 	int searchTrophyListCount(Map<String,String> map);
 	
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
