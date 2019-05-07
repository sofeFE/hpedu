package com.hpedu.web.core.video.dao;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.pojo.VideoChild;
import com.hpedu.web.core.video.pojo.VideoPdf;
import org.apache.ibatis.annotations.Mapper;


/**
 * 常规赛dao
 * */
@Mapper
public interface GeneralVideoMapper  extends BaseMapper<GeneralVideo> {
	/**
	 * 首页常规赛dao
	 * */
	List<GeneralVideo> findGeneralVideo();
	/**
	 * 根据视频ID查询视频播放
	 * */
	GeneralVideo findGeneralVideoByVid(String vid);
	/**
	 *  该视频相关视频
	 *  @param gsbuject 学科
	 *  @param gclass 年级
	 *  @param gclassify 年级下分类（阅读 写作等）
	 * */
	List<GeneralVideo> findGeneralVideoByVideo(String gsbuject,String gclass,String gclassify,String vid);

	/**
	 * 条件查询常规视频(分页)
	 * 
	 * */
	List<GeneralVideo> searchGeneralVideoList(Map<String, String> map) ;
	int searchGeneralVideoListCount(Map<String, String> map);
	
	/**
	 * 根据ID删除该视频
	 * */
	 void deleteGeneralById(String gid);

	/**
	 * 新增语文常规视频
	 * */
	void addChineseGeneralVideo(GeneralVideo generalVideo);
	/**
	 * 修改视频
	 * */
	 void updateChineseGeneralVideo(GeneralVideo generalVideo);
	 
     /**
	 * 根据年级查前8条常规视频-测验推荐
	 * */
	List<GeneralVideo> findVideoListByExam(Map<String,Object> map);
	/**
	 * 条件查询常规视频
	 * 
	 * */
	List<GeneralVideo> searchGeneralVideoListByGname(String gname) ;
	/**
	 * 根据父视频id查询所有的子视频
	 * 
	 * */
	List<VideoChild> selectAllChildVideo(Map<String,String> map);
	/**
	 * 根据id查询子视频
	 * 
	 * */
	VideoChild selectChildVideoById(String id);
	/**
	 * 批量新增子视频
	 * 
	 * */
	int  insertVideoChild(List<VideoChild> list);
	/**
	 * 批量删除子视频
	 * 
	 * */
	int  deleteVideoChild(String[] list);
	/**
	 * 批量修改子视频
	 * 
	 * */
	int  updateBatchVideoChild(List<VideoChild> list);
	/**
	 * 批量修改 子视频顺序
	 * @param vclist
	 * @return
	 */
	int updateBatchVideoChildSort(List<VideoChild> vclist);
	
	/**
	 * 批量修改子视频
	 * 
	 * */
	int  updateBatchVideoChildName(List<VideoChild> list);
	/**
	 * 新增单个子视频
	 * 
	 * */
	int  insertOneVideoChild(VideoChild vc);
	
	/**
	 * 根据pID删除关联子视频
	 * */
	 void deleteVideoChildById(String pid);
	 /**
		 * 批量新增pdf
		 * map:List<VideoPdf> list,vid
		 * */
		int  insertVideoPdf(Map<String,Object>  map);
		/**
		 * 批量删除pdf
		 * 
		 * */
		int  deleteVideoPdf(String[] list);
		int updateIsKill(String vid,String status);//修改秒杀执行状态
		
		 /**
		 * 修改常规播放次数
		 * */
		int updatePlayCount(String vid);
		void updateVideoEditDate(String dateStr,String weekVal);//批量修改子视频更新时间
		VideoChild getLastNewEditVideoChild(Map<String,String> map);//获取最新更新视频
		
}
