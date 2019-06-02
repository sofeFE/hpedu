<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/back/pintuer.css">
<link rel="stylesheet" href="css/back/admin.css">
<script src="libs/jquery.js"></script>
<script src="libs/pintuer.js"></script>
</head>
<body>
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath}/jsp/back/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />厚朴教育试卷批改中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="${pageContext.request.contextPath}/classindex.html" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="back/backlogin.html"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="panel admin-panel">
<div class="panel-head"><strong class="icon-reorder">【${gname}】简答题答题详情</strong></div>

<form method="post" class="form-x"  action="${pageContext.request.contextPath}/back/addUnitTestScoresClass"  onsubmit="return sub()" >
<c:forEach items="${jdt_list}" var="jdt" varStatus="st">
    <div style="width:100%;background:#EEEEEE;word-break:break-all;margin-top:10px;padding:5px;text-align:left;">
     <font style="font-weight:bold;"> ${st.index+1}.【简答题】（${jdt.score}分）：</font>
     <h4>&nbsp;&nbsp;&nbsp;${jdt.ucontent}&nbsp;&nbsp;</h4>
    </div>
    
    <div style="width:100%;word-break:break-all;margin-top:5px;padding-left:10px;margin-bottom: 10px;">
      <table>
      <tr><td>学生答案：</td>
      <td><textarea class="input"  style="width:600px;height:120px;"  readonly="readonly">${not empty jdt.stuAnswer?jdt.stuAnswer:'此题他没有作答'}</textarea>
            <input type="hidden" name="eeid" value="${jdt.eeid}">
           </td>
      </tr>
      <tr><td>您的给分：</td><td> <input type="text" class="input w50"  name="stuGotScore" onblur="checkScore(this,${jdt.score})" style="width:300px;" /></td></tr>
      <tr><td>您的批注：</td><td> <input type="text" class="input w50"  name="coment"  style="width:500px;" /></td></tr>
      <tr><td>题目答案：</td><td><a href="javascript:void(0)" style="text-decoration: underline;color:blue;" onclick="show(this,'${jdt.eeid}')">查看详解</a></td></tr>
      <tr><td></td><td><div style="display:none;word-break:break-all;width:100%;" id="${jdt.eeid}_detail">${jdt.detail}</div></td></tr>
     </table>       
    </div>
</c:forEach>
<input type="hidden" name="usid" value="${usid}">
<input type="hidden" name="gname" value="${gname}">
<span style="visibility:hidden;"> &nbsp;&nbsp;您的给分：</span>
<button class="button bg-main icon-check-square-o" type="submit" id="subbtn">提交打分</button>
<button class="button bg-main icon-check-square-o" type="button" onclick="back()">返回</button>
</form>
</div>
<script type="text/javascript">
$(function(){
	var error="${error}";
	if(error.length>0){
		alert(error);
	}
});
function sub(){
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 return true;
}
function checkScore(This,maxScore){
	var val=$(This).val();
	var res="";
	if(isNaN(val)){
		res="给分必须是数值!";
	}else{
		 if(parseInt(val)<val){
			 res="给分必须是整数!";
		 }else{
			 if(val>maxScore){
				 res="给分不能大于分数"+maxScore;
			 }
		 }
	}
	if(res.length>0){
		alert(res);
		$(This).val("");
	}
}
function back(){
	window.history.go(-1);
}
function show(This,eeid){
	 if($("#"+eeid+"_detail").is(":hidden")){
	      $("#"+eeid+"_detail").slideDown();
		  $(This).html("隐藏详解");
	  }else{
		  $("#"+eeid+"_detail").slideUp();
		  $(This).html("查看详解");
	  }
}
</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>