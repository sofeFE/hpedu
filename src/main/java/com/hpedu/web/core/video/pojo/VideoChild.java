package com.hpedu.web.core.video.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

//视频多关联的子视频
@TableName("video_child")
public class VideoChild {
    @TableId
    private String vcid;
    private String pid;// 父关联视频id
    private String vcname;// 视频名称
    private String vcideoUrl;// 路径
    private String vclassify;// 视频子分类（如:春季班/暑假班/秋季班/寒假班）
    private Integer vctype;// 类型:0:常规；1：竞赛
    private String sort;//顺序--时间戳
    private Date editDate;//视频更新时间 ? 干嘛用的?

    private String cImgUrl;//视频关联的图片.


    public String getcImgUrl() {
        return cImgUrl;
    }

    public void setcImgUrl(String cImgUrl) {
        this.cImgUrl = cImgUrl;
    }

    public String getVcid() {
        return vcid;
    }

    public void setVcid(String vcid) {
        this.vcid = vcid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getVcname() {
        return vcname;
    }

    public void setVcname(String vcname) {
        this.vcname = vcname;
    }

    public String getVcideoUrl() {
        return vcideoUrl;
    }

    public void setVcideoUrl(String vcideoUrl) {
        this.vcideoUrl = vcideoUrl;
    }

    public String getVclassify() {
        return vclassify;
    }

    public void setVclassify(String vclassify) {
        this.vclassify = vclassify;
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

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }


}
