package com.hpedu.web.core.video.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.video.pojo.ContestVideo;
import com.hpedu.web.core.video.pojo.VideoPdf;

/**
 * 竞赛servic
 * */
public interface ContestVideoService extends IService<ContestVideo> {
	/**
	 * 首页查询推荐竞赛视频
	 * */
	List<ContestVideo> findContestVideoIndex();
	/**
	 * 根据竞赛视频ID播放
	 * @param cId （竞赛视频ID）
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
	 * 导航栏分页查询符合条件视频列表
	 * @param Map 参数map
	 * */
	Page<ContestVideo> searchContestVideoList(Map<String,String>map,int pageNo,int pageSize);
	
	/**
	 * 新增竞赛课程
	 * */
	void addUSAClass(ContestVideo contestVideo);
	/**
	 * 修改竞赛视频信息
	 * */
	void updateUSAClass(ContestVideo contestVideo);
	/**
	 * 删除竞赛视频
	 * */
	void deleteUSAClass(String cid);
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
