package com.hpedu.web.core.user.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data//用户章节测试分数
@TableName("userscore")
public class UserScore  implements Serializable {
    @TableId
    private String usid;
    private String uid;// 关联用户id
    private String vid;//关联视频id
    private Integer utype;//视频类型：0：单个常规；1：单个竞赛；2：多集常规；3：多集竞赛
    private Long ucreateTime;//创建时间
    private Integer rightNum;//正确个数
    private Integer errorNum;//错误个数

    private Integer totalNums;//题目总个数
    private Integer isHasJDT;//是否含有简答题（0：选择题，1：简答题；2：简答题和选择题）
    private String teacherName;//批改教师
    
    private Integer totalScores;//题目总分
    private Integer score;// 选择题得分
    private Integer JDTscore;// 简答题得分
    private Integer gotScore;// 总得分
    
    private Integer isDone ; //是否已经批改过试卷
    private String JDT_answer ;
    
   
}
	
	
