package com.hpedu.web.core.video.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hpedu.util.mybatis.MyBatisBase;
import com.hpedu.util.mybatis.Page;
import com.hpedu.web.core.video.dao.GeneralVideoMapper;
import com.hpedu.web.core.video.pojo.GeneralVideo;
import com.hpedu.web.core.video.pojo.VideoChild;
import com.hpedu.web.core.video.service.GeneralVideoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GeneralVideoServiceImpl extends ServiceImpl<GeneralVideoMapper, GeneralVideo> implements GeneralVideoService {
    @Autowired
    private MyBatisBase myBatisBase;

    @Override
    @Cacheable(value = "welcomePage"/*,key="generalList"*/)
    public List<GeneralVideo> findGeneralVideo() {
        List<GeneralVideo> genlist = baseMapper.findGeneralVideo();
        return genlist;
    }

    @Override
    public GeneralVideo findGeneralVideoByVid(String vid) {

        GeneralVideo gvideo = baseMapper.findGeneralVideoByVid(vid);
        return gvideo;
    }

    @Override
    public List<GeneralVideo> findGeneralVideoByVideo(@Param("gsbuject") String gsbuject,
                                                      @Param("gclass")String gclass,
                                                      @Param("gclassify")String gclassify,
                                                      @Param("vid")String vid) {

        List<GeneralVideo> glist = baseMapper.findGeneralVideoByVideo(gsbuject, gclass, gclassify, vid);
        return glist;
    }

    @Override
    public Page<GeneralVideo> searchGeneralVideoList(Map<String, String> map,
                                                     int pageNo, int pageSize) {
        Page<GeneralVideo> page = myBatisBase.queryPage("com.hpedu.web.core.video.dao.GeneralVideoMapper.searchGeneralVideoList",
                "com.hpedu.web.core.video.dao.GeneralVideoMapper.searchGeneralVideoListCount", map, pageNo, pageSize);
        return page;
    }

    @Override
    public void deleteGeneralById(String gid) {

        baseMapper.deleteGeneralById(gid);
    }

    @Override
    public void addChineseGeneralVideo(GeneralVideo generalVideo) {

        baseMapper.addChineseGeneralVideo(generalVideo);
    }

    @Override
    public void updateChineseGeneralVideo(GeneralVideo generalVideo) {

        baseMapper.updateChineseGeneralVideo(generalVideo);
    }


    @Override
    public List<GeneralVideo> searchGeneralVideoListByGname(String gname) {

        return baseMapper.searchGeneralVideoListByGname(gname);
    }

    @Override
    public List<GeneralVideo> findVideoListByExam(Map<String, Object> map) {

        return baseMapper.findVideoListByExam(map);
    }

    @Override
    public List<VideoChild> selectAllChildVideo(Map<String, String> map) {

        return baseMapper.selectAllChildVideo(map);
    }

    @Override
    public int insertVideoChild(List<VideoChild> list) {

        return baseMapper.insertVideoChild(list);
    }

    @Override
    public int deleteVideoChild(String[] list) {

        return baseMapper.deleteVideoChild(list);
    }

    @Override
    public VideoChild selectChildVideoById(String id) {

        return baseMapper.selectChildVideoById(id);
    }


    @Override
    public void deleteVideoChildById(String pid) {

        baseMapper.deleteVideoChildById(pid);
    }

    @Override
    public int insertVideoPdf(Map<String, Object> map) {

        return baseMapper.insertVideoPdf(map);
    }

    @Override
    public int deleteVideoPdf(String[] list) {

        return baseMapper.deleteVideoPdf(list);
    }

    @Override
    public int updateIsKill(String vid, String status) {

        return baseMapper.updateIsKill(vid, status);
    }

    @Override
    public List<GeneralVideo> searchGeneralVideoList(Map<String, String> map) {

        return baseMapper.searchGeneralVideoList(map);
    }

    @Override
    public int updateBatchVideoChild(List<VideoChild> list) {

        return baseMapper.updateBatchVideoChild(list);
    }

    @Override
    public int insertOneVideoChild(VideoChild vc) {

        return baseMapper.insertOneVideoChild(vc);
    }

    @Override
    public int updatePlayCount(String vid) {

        return baseMapper.updatePlayCount(vid);
    }

    @Override
    public void updateVideoEditDate(String dateStr, String weekVal) {

        baseMapper.updateVideoEditDate(dateStr, weekVal);
    }

    @Override
    public VideoChild getLastNewEditVideoChild(Map<String, String> map) {

        return baseMapper.getLastNewEditVideoChild(map);
    }

    @Override
    public int updateBatchVideoChildName(List<VideoChild> list) {

        return baseMapper.updateBatchVideoChildName(list);
    }

    @Override
    public int updateBatchVideoChildSort(List<VideoChild> vclist) {

        return baseMapper.updateBatchVideoChildSort(vclist);
    }

}
