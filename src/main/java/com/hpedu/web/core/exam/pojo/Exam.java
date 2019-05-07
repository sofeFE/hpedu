package com.hpedu.web.core.exam.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 小测验
 */
@TableName("exam")
public class Exam {
    @TableField(exist = false)
    List<ExamImg> imgList;//测验题图片和答案
    @TableId
    private String etid;//主键
    private String etname;//测试名称
    private String etsubject;//测试科目
    private String etclass;//班级
    private String etclassify;//班级下分类（例：真题、上学期/下学期）
    private String etimg;//是否只是试卷
    private Date etcreatTime;//创建时间
    private String etestNo;//测验人数
    //private String answer;//答案
    private String fmImg;//封面
    private String pdf;//题pdf
    private String answerPdf;//答案pdf
    private String teacherName;//批改教师


    public String getEtid() {
        return etid;
    }

    public void setEtid(String etid) {
        this.etid = etid;
    }

    public String getEtname() {
        return etname;
    }

    public void setEtname(String etname) {
        this.etname = etname;
    }

    public String getEtsubject() {
        return etsubject;
    }

    public void setEtsubject(String etsubject) {
        this.etsubject = etsubject;
    }

    public String getEtclass() {
        return etclass;
    }

    public void setEtclass(String etclass) {
        this.etclass = etclass;
    }

    public String getEtclassify() {
        return etclassify;
    }

    public void setEtclassify(String etclassify) {
        this.etclassify = etclassify;
    }


    public String getEtcreatTime() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(etcreatTime);
    }

    public void setEtcreatTime(Date etcreatTime) {
        this.etcreatTime = etcreatTime;
    }

    public String getEtestNo() {
        return etestNo;
    }

    public void setEtestNo(String etestNo) {
        this.etestNo = etestNo;
    }

    public String getFmImg() {
        return fmImg;
    }

    public void setFmImg(String fmImg) {
        this.fmImg = fmImg;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getEtimg() {
        return etimg;
    }

    public void setEtimg(String etimg) {
        this.etimg = etimg;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getAnswerPdf() {
        return answerPdf;
    }

    public void setAnswerPdf(String answerPdf) {
        this.answerPdf = answerPdf;
    }

    public List<ExamImg> getImgList() {
        return imgList;
    }

    public void setImgList(List<ExamImg> imgList) {
        this.imgList = imgList;
    }

}
