package com.hpedu.web.core.video.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.pojo.VideoPdf;
import org.apache.ibatis.annotations.Mapper;

/**
 * 竞赛dao
 * */
@Mapper

public interface ContestVideoMapper extends BaseMapper<ContestVideo> {
	/**
	 * 首页查询推荐竞赛视频
	 * */
	List<ContestVideo> findContestVideoIndex();
	/**
	 * 根据竞赛视频ID 点击播放
	 * @param cId (竞赛视频ID)
	 * */
	ContestVideo findContestVideoById(String cId);
	/**
	 * 查询该竞赛相关视频
	 * @param competitionName 竞赛名称（例：美国大联盟杯）
	 * @param cclass 年级（例：三四年级）
	 * @param cclassify 年级下分类（例：真题讲解）
	 * */
	List<ContestVideo> findContestVideoByVideo(String competitionName,String cclass,String cclassify,String vid);
	/**
	 * 条件查询 竞赛 分页
	 * @param map集合
	 * */
	List<ContestVideo> searchContestVideoList(Map<String,String> map);
	int searchContestVideoListCount(Map<String,String> map);
	
	/**
	 * 新增大联盟杯课程
	 * */
	void addUSAClass(ContestVideo contestVideo);
	/**
	 * 修改竞赛视频信息
	 * */
	void updateUSAClass(ContestVideo contestVideo);
	/**
	 * 删除竞赛视频
	 * */
	void deleteUSACLass(String cid);
	/**
	 * 竞赛视频模糊查询
	 * */
	List<ContestVideo> searchContestVideoListByCname(String cname);
	 /**
		 * 根据年级查前8条竞赛视频-测验推荐
		 * */
    List<ContestVideo> findVideoListByExam(Map<String,Object> map);
    
    List<VideoPdf> selectPdfByVid(String vid,String type);
    
    int updateIsKill(String vid,String status);//修改秒杀执行状态
    
    /**
	 * 修改竞赛播放次数
	 * */
	int updatePlayCount(String vid);
}
