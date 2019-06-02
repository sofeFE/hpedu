<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<title>视频列表</title>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/index.css?v=1" />
<link rel="stylesheet" type="text/css"
	href="css/videoList.css" />
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">
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
		<!-- 菜单 -->
		<div class="sunmenu">
				<ul>
					<li><a  href="classindex.html">首页</a></li>
					<li><a  class="routine-menu">常规课</a></li>
					<li><a style="border-bottom: 3px solid #5CB85C;color: #5CB85C;" class="competition-menu">竞赛课</a></li>
					<li><a class="quiz-menu">小测验</a></li>
				</ul>
                <%@include file="../menuPublic.jsp"%>
			</div>
		
		<!-- 登录 -->
         <%@include file="../ckUserInfo.jsp"%>
	</div>
	<!--内容-->
	<div class="content"   style="width:100%;" >
		<div class="classification-content video-chapter" >
			<div class="classification-content-title">
				<h3 style="color: red">${competitionName}${cclass}${cclassify}</h3>
				<c:if test="${pages.totalCount==0}">
					 <h4 style="color:#5CB85C;">即将上线，敬请期待</h4>
				</c:if>
			</div>
			<c:forEach items="${pages.result}" var="cv">
			<div class="classification-content-every thisc">
					<input class="cno" type="hidden" value="${cv.cid}">
						<a>
							<c:if test="${not empty cv.cvimg }">
						     <img src="${cv.cvimg}"/>
						    </c:if>
						     <c:if test="${empty cv.cvimg }">
							<img src="img/class3.jpg"/>
							</c:if>
							<div class="classification-content-text">
								<h6>${cv.cclassify}</h6>
								<p class="name" style="display:none;">${cv.cname}</p>
								<p class="name1" >主讲教师:${cv.teacherName}</p>
							</div>
							<c:if test="${cv.cmoney=='0'}">
							<div class="study">免费
								<span>${cv.cplayNo}人学习</span>
							</div>
							</c:if>
							<c:if test="${cv.cmoney!='0'}">
							<div class="study">￥${cv.cmoney}
								<span>${cv.cplayNo}人学习</span>
							</div>
							</c:if>
						</a>
			</div>
		</c:forEach>
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
		<%@include file="/WEB-INF/jsp/footer.jsp" %>
	</div>

</body>
<script
	src="libs/jquery.1.10.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/public.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/videoList.js"
	type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	    
        function pageSize(num){
        	contest_list("contest/contestVideoList.html","${competitionName}","${cclass}","${cclassify}",num);
			//window.location.href="contest/contestVideoList.html?competitionName="+"${competitionName}"+"&"+"cclass="+"${cclass}"+"&"+"cclassify="+"${cclassify}"+"&"+"pageNo="+num;
		}
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
<script type="text/javascript" src="js/class.js?v=2"></script></html>