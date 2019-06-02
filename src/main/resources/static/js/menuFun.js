/**
 * 菜单点击触发函数
 */
var cxPath = "/";
// var cxPath="/";
var isChangeTime = false;

function setChangeTimeStatus(flag) {
    isChangeTime = flag;
}

function general_list(url, gsbuject, gclass, gclassify, pageNo, nameType, sort) {
    $("#subFrom").attr("action", cxPath + url);
    $("#gsbuject").val(gsbuject);
    $("#gclassify").val(gclassify);
    $("#gclass").val(gclass);
    try {
        $("#nameType").val(nameType);
        $("#sort").val(sort);
    } catch (e) {
    }
    if (pageNo) {
        $("#pageNo").val(pageNo);
    } else {
        $("#pageNo").val(1);
    }
    $("#subFrom").submit();
}

function contest_list(url, competitionName, cclass, cclassify, pageNo) {
    $("#subFrom").attr("action", cxPath + url);
    $("#competitionName").val(competitionName);
    $("#cclassify").val(cclassify);
    $("#cclass").val(cclass);
    if (pageNo) {
        $("#pageNo").val(pageNo);
    } else {
        $("#pageNo").val(1);
    }
    $("#subFrom").submit();
}

function exam_list(url, etsubject, etclass, etclassify, pageNo) {
    $("#subFrom").attr("action", cxPath + url);
    $("#etsubject").val(etsubject);
    $("#etclassify").val(etclassify);
    $("#etclass").val(etclass);
    if (pageNo) {
        $("#pageNo").val(pageNo);
    } else {
        $("#pageNo").val(1);
    }
    $("#subFrom").submit();
}

$(function () {
    
    $('.thisv').on('click', function () {
        var vid = $(this).children('.no').val();
        var name = $(this).children('a').children('.classification-content-text').children(".name").html();
        var isMore = $("#isMore_" + vid).val();
        var url = "";
        if (isMore == 0) {
            url = "general/general.html";
        } else {
            url = "general/generalMore.html";
        }
        if (isChangeTime) {
            updateLearningTime();
        }
        $("#subFrom").attr("action", cxPath + url);
        $("#className").val(name);
        $("#classNo").val(vid);
        $("#subFrom").submit();
    });
    $('.thisc').on('click', function () {// 课程介绍 div 点击跳转 播放页面.-gd
        var cid = $(this).children('.cno').val();
        var name = $(this).children('a').children('.classification-content-text').children(".cname").html();
        var url = "contest/contest.html";
        if (isChangeTime) {
            updateLearningTime();
        }
        $("#subFrom").attr("action", cxPath + url);
        $("#className").val(name);
        $("#classNo").val(cid);
        $("#subFrom").submit();
    })
    $('.gclassify').on('click', function () {
        var gsbuject = $(this).parents(".subinnerbox-content").siblings(".subinnerbox-title").html().trim();
        var gclassify = $(this).html().trim();
        var gclass = $(this).siblings(".gclass").html().trim();
        var url = "";
        if (gsbuject == '数学' && gclassify == '专题课') {
            url = "general/generalVideoList_zt.html";
        } else {
            url = "general/generalVideoList.html";
        }
        if (isChangeTime) {
            updateLearningTime();
        }
        general_list(url, gsbuject, gclass, gclassify);
    });
    $('.cclassify').on('click', function () {
        var competitionName = $(this).parents(".subinnerbox-content").siblings(".subinnerbox-title").html().trim();
        var cclassify = $(this).html().trim();
        var cclass = $(this).siblings(".cclass").html().trim();
        var url = "contest/contestVideoList.html";
        if (isChangeTime) {
            updateLearningTime();
        }
        contest_list(url, competitionName, cclass, cclassify);
    });
    $('.etclassify').on('click', function () {
        var etsubject = $(this).parents(".subinnerbox-content").siblings(".subinnerbox-title").html().trim();
        var etclassify = $(this).html().trim();
        var etclass = $(this).siblings(".etclass").html().trim();
        var url = "exam/examlist.html";
        if (isChangeTime) {
            updateLearningTime();
        }
        exam_list(url, etsubject, etclass, etclassify);
    });
    $('.thisexam').on('click', function () {
        var etid = $(this).children('.eno').val();
        var name = $(this).children('a').children('.classification-content-text').children(".ename").html();
        var url = "exam/exam.html";
        $("#subFrom").attr("action", cxPath + url);
        $("#etid").val(etid);
        $("#name").val(name);
        $("#subFrom").submit();
    });

    setChangeTimeStatus(false);
});
$(function () {
    $(".exitUser").on("click", function () {
        if (isChangeTime) {
            updateLearningTime();
        }
        window.location.href = cxPath + "/user/exitUser";
    });
});


//修改学生学习时间
function updateLearningTime(){
    if(time && islogin=="1"){
        $.ajax({
            url: cxPath + "user/updateLearningTime",
            type:"post",
            async:true,
            data:{vid:"${contestVideo.cid}",vclassify:1,time:time},
            success:function(result){
                if(result.code == 0) {
                    console.log("changeStuLearnTime success");
                }else{
                    console.error("changeStuLearnTime defeat , "+ result.msg)
                } 
            }
        });
    }
}