<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<base href="${pageContext.request.contextPath}/">
<title>教师详情</title>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/index.css?v=1" />
<link rel="stylesheet" type="text/css"
	href="css/user.css" />
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">
</head>
<body>
	<div class="header">
<!-- logo图 和搜素-->
		<%@include file="../videoSearch.jsp"%>	
			<!--菜单-->
			<div class="sunmenu">
			<ul>
				<li><a href="classindex.html">首页</a></li>
				<li><a class="routine-menu">常规课</a></li>
				<li><a class="competition-menu">竞赛课</a></li>
				<li><a class="quiz-menu">小测验</a></li>
			</ul>
			               <%@include file="../menuPublic.jsp"%>
		</div>
		<!-- 登录 -->
         <%@include file="../ckUserInfo.jsp"%>
</div>
	<!--内容-->
	<div class="content" >
			<div class="user-info"  style="padding:2% 5% 2% 5%;">
			<table style="width:99%;height:auto;">
			  <tr><td style="text-align:right;width:1%;"><img src="${teacher.timgUrl}" style="width:160px;height:240px"/></td>
			  <td style="text-align:left;padding:10px;width:50%;word-break:break-all;">
			      ${teacher.subject}
			  </td></tr>
			 <tr><th style="text-align:left;font-size:18px;color:#309BEE;padding-left:20px;" colspan="2">教师荣誉</th></tr>
			 <tr><td style="padding:10px;word-break:break-all;width:100%;background:#F7F7F7;" colspan="2"> ${teacher.tintro}</td></tr>
			</table>
			
		</div>
</div>
	
	<div class="foot">
		<div class="foot-content">
			<div class="foot-content-menu">
				<ul>
					<li><a href="" style="cursor: pointer">网站首页</a></li>
					<li><a href="" style="cursor: pointer">企业合作</a></li>
					<li><a href="" style="cursor: pointer">人才招聘</a></li>
					<li><a href="" style="cursor: pointer">联系我们</a></li>
					<li><a href="" style="cursor: pointer">常见问题</a></li>
				</ul>
			</div>
			<p>Copyright © 2016 imooc.com All Rights Reserved | 京ICP备
				13046642号-2</p>
		</div>
	</div>
</body>
<script
	src="libs/jquery.1.10.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/public.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
setChangeTimeStatus(false);
</script>
<script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
	<form action="" id="subFrom" method="post">
	     <!-- 常规视频菜单 -->
	     <input type="hidden" name="gsbuject" id="gsbuject">
	     <input type="hidden" name="gclass" id="gclass">
	     <input type="hidden" name="gclassify" id="gclassify">
	      <!-- 常规单个 -->
	     <input type="hidden" name="className" id="className">
	     <input type="hidden" name="classNo" id="classNo">
	      <!-- 竞赛视频菜单 -->
	     <input type="hidden" name="competitionName" id="competitionName">
	     <input type="hidden" name="cclassify" id="cclassify">
	     <input type="hidden" name="cclass" id="cclass">
	     <!-- 小测验菜单 -->
	     <input type="hidden" name="etsubject" id="etsubject">
	     <input type="hidden" name="etclassify" id="etclassify">
	     <input type="hidden" name="etclass" id="etclass">
	     <!-- 分页参数 -->
	     <input type="hidden" name="pageNo" id="pageNo">
	</form>
</html>
