<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<title>教师列表</title>
    <link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/index.css?v=1">
	<link rel="stylesheet" href="css/swiper.css">
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<style type="text/css">
ul.pagination {
    display: inline-block;
    padding: 0;
    margin: 0;
}

ul.pagination li {display: inline;
}

ul.pagination li a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
    border: 1px solid #ddd;
}

.pagination li:first-child a {
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
}

.pagination li:last-child a {
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
}

ul.pagination li a.active {
    background-color: #4CAF50;
    color: white;
    border: 1px solid #4CAF50;
}

ul.pagination li a:hover:not(.active) {background-color: #ddd;}
</style>
</head>
<body>
	<div class="header">
<!-- logo图 和搜素-->
		<%@include file="../videoSearch.jsp"%>	
			<!--菜单-->
			<div class="sunmenu">
				<ul>
					<li><a  href="classindex.html">首页</a></li>
					<li><a  class="routine-menu">常规课</a></li>
					<li><a  class="competition-menu">竞赛课</a></li>
					<li><a class="quiz-menu">小测验</a></li>
				</ul>
				               <%@include file="../menuPublic.jsp"%>
			</div>
	<!-- 登录 -->
         <%@include file="../ckUserInfo.jsp"%>
</div>
	<!--内容-->
	<div class="content"  style="width:100%;" >
		<div class='cont'>
		<div class="section">
		 			<!-- 学员展示 -->
			<div class="student">
					<div class="pupil">
						<h4>TEACHER RECOMMENDED</h4>
						<h5>名师推荐</h5>
					</div>
					<ul class="trainee">
					 <c:forEach items="${pages.result}" var="tl" >
						<li id="${tl.tid}" name="teacher_li" class="cur_pointer">
							<img style="height:88%" src="${tl.timgUrl}" alt="">
							<div class="class-pic">
								<h4>${tl.tnamePinyin }</h4>
								<p>${tl.tname}</p>
								<div class="look">
									<i class="iconfont icon-mulu f18"></i>
									<a href="javascript:showTeacher('${tl.tid}')">了解更多</a>
								</div>
							</div>
						</li>
					</c:forEach>	
					</ul>	
			</div>
		</div>	
		</div>
			<div style="text-align:center;list-style-type:none;">
		<ul class="pagination">
			<li ><a>共${pages.getTotalPages()}页</a></li>
			<li><a onclick="pageSize(1)">首页</a></li>
			<li ><a onclick="pageSize(${pages.getPrePage()})">上一页</a></li>
			<li ><a onclick="pageSize(${pages.getNextPage()})">下一页</a></li>
			<li><a onclick="pageSize(${pages.getTotalPages()})">尾页</a></li>
			<li ><a>共${pages.totalCount}条</a></li>
			<li><a>当前第${pages.pageNo}页</a></li>
		</ul>
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
	<script type="text/javascript">
      function pageSize(num){
			window.location.href="${pageContext.request.contextPath}/teacher/teacherList.html?pageNo="+num;
		}
	 //根据id显示教师信息
	  function showTeacher(id){
		  window.location.href="${pageContext.request.contextPath}/teacher/checkTeacher.html?id="+id;
	  }
	  setChangeTimeStatus(false);
	</script>
	<script  type="text/javascript" src="libs/jquery.js"></script>
<script type="text/javascript" src="libs/swiper.js"></script>
<script type="text/javascript" src="js/class.js?v=2"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js"></script>
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