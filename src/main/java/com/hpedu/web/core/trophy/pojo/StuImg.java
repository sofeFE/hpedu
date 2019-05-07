package com.hpedu.web.core.trophy.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("stu_img")
@Data
public class StuImg {
    @TableId
    private String stid;
    private String tpid; //关联学生id
    private String stUrl;//学生图片路径
    private String sort;//上传顺序



}
