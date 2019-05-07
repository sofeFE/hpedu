package com.hpedu.web.core.video.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

//视频关联pdf文件
@TableName("video_pdf")
public class VideoPdf {
    @TableId
    private String pdfid;
    private String vid;// 关联视频id
    private String pdfUrl;// pdf路径
    private Integer vctype;//视频类型：0：常规；1：竞赛
    private String sort;// 上传顺序

    public String getPdfid() {
        return pdfid;
    }

    public void setPdfid(String pdfid) {
        this.pdfid = pdfid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public Integer getVctype() {
        return vctype;
    }

    public void setVctype(Integer vctype) {
        this.vctype = vctype;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }


}
