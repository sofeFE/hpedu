package com.hpedu.web.core.order.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

//轮播文章图片
@Data
@TableName("article_img")
public class ArticleImg  implements Serializable {
    @TableId
    private String atid;
    private String bid;
    private String atUrl;
    private LocalDateTime CreateTime;

}
