<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<base href="${pageContext.request.contextPath}/">
	<title>视频列表</title>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/video.css"/>
		<link rel="stylesheet" type="text/css" href="css/index.css?v=3"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">
	</head>
<body>
<div class="header">
	<%@include file="/WEB-INF/jsp/header.jsp" %>
</div>
<div class='cont'>
		<div class="section">
		  <c:if test="${type==0 }">
			<div class="content" style="height:auto;">
				<div class="course">
						<h4>GENERAL  COURSE</h4>
						<h5>常<span>规课程</span>视频</h5>
					</div>
				<div class="classification-content"   >
				<c:forEach items="${pages.result}" var="g">
					<div class="classification-content-every thisv" >
						<input class="no" type="hidden" value="${g.gid}">
						<input id="isMore_${g.gid}" type="hidden" value="${g.isMore==null?0:g.isMore}">
						<a>
						 <c:if test="${g.gvimg==null||g.gvimg.length()==0 }">
							<img src="img/class3.jpg"/>
						</c:if>	
						 <c:if test="${g.gvimg!=null&&g.gvimg.length()>0 }">
							     <img src="${g.gvimg}"/>
						</c:if>
							<div class="classification-content-text">
								<h6>${not empty g.gclassify2?g.gclassify2:(g.gclass=='新概念'?g.gname:g.gclassify)}</h6>
								<%-- <p class="name">${g.gname}</p> --%>
								<p class="name" style="display:none;">${g.gname}</p>
								<p class="name1" >主讲教师:${g.teacherName}</p>
							</div>
							<c:if test="${g.gmoney=='0'||g.gmoney==null}">
								<div class="study" >免费
								<span style="width:80px;">${g.gplayNo}人学习</span>
							</div>
							</c:if>
							<c:if test="${g.gmoney!='0'&&g.gmoney!=null}">
							<div class="study" >￥${g.gmoney}
								<span style="width:80px;">${g.gplayNo}人学习</span>
							</div>
							</c:if>
						</a>
					</div>	
					</c:forEach>	
				</div>
			</div>
			</c:if>
			 <c:if test="${type==1 }">
			<div class="content" style="height:auto;">
				<div class="course">
						<h4>CONTEST  COURSE</h4>
						<h5>竞<span>赛课程</span>视频</h5>
					</div>
				<div class="classification-content">
		        <c:forEach items="${pages.result}" var="c">
					<div class="classification-content-every thisc">
					<input class="cno" type="hidden" value="${c.cid}">
						<a>
						 <c:if test="${c.cvimg==null||c.cvimg.length()==0 }">
							<img src="img/class1.jpg"/>
						</c:if>	
						 <c:if test="${c.cvimg!=null&&c.cvimg.length()>0 }">
							     <img src="${c.cvimg}"/>
						</c:if>
							
							<div class="classification-content-text">
								<h6>${c.cclassify}</h6>
								<p class="cname" style="display:none;">${c.cname}</p>
								<p class="cname1" >主讲教师:${c.teacherName}</p>
							</div>
							<c:if test="${c.cmoney=='0'||c.cmoney==null}">
							<div class="study">免费
								<span style="width:80px;">${c.cplayNo}人学习</span>
							</div>
							</c:if>
							<c:if test="${c.cmoney!='0'&&c.cmoney!=null}">
							<div class="study">￥${c.cmoney}
								<span style="width:80px;">${c.cplayNo}人学习</span>
							</div>
							</c:if>
						</a>
					</div>
					</c:forEach>
				</div>
			</div>
			</c:if>
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
		<div class="foot">
			<%@include file="/WEB-INF/jsp/footer.jsp" %>
		</div>
		<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
		<script src="js/public.js" type="text/javascript" charset="utf-8"></script><script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	function pageSize(num){
		window.location.href="${pageContext.request.contextPath}/order/showAllVideo.html?pageNo="+num+"&type=${type}";
	}

</script>

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
	</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>
