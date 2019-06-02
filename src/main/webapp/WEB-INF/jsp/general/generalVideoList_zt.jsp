<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8" />
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
		<%@include file="/WEB-INF/jsp/header.jsp" %>
</div>
	<!--内容-->
	<div class="content"  style="width:100%;" >
		<div class="classification-content video-chapter"  >
			<div class="classification-content-title">
			   <c:choose>
			    <c:when test="${gclass!='古诗'&&gclass!='阅读'&&gclass!='写作'&&gclass!='流利英语'&&gclass!='语法'&&gclass!='其他'}">
				  <h3 style="color: red">${gsbuject}${gclass}${gclassify}</h3>
				</c:when>
				<c:otherwise>
				 <h3 style="color: red">${gsbuject}${gclassify}</h3>
				</c:otherwise>
				</c:choose>
			<c:if test="${totalCount==0}">
			 <h4 style="color:#5CB85C;">
			            即将上线，敬请期待
			 </h4>
			</c:if>
			</div>
			
			<div class="classification" >
				 <c:if test="${jisuan.size() > 0 }"> 
				   <div class="classification-content" style="padding: 5px;"> 
				    <div class="classification-titile"> 
				     <h4>计算</h4> 
				    </div> 
				    <c:forEach items="${jisuan}" var="gv"> 
				     <div class="classification-content-every thisv"> 
				      <input class="no" type="hidden" value="${gv.gid}" /> 
				      <input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}" /> 
				      <a> 
				       <c:if test="${not empty gv.gvimg }"> 
				        <img src="${gv.gvimg}" /> 
				       </c:if> 
				       <c:if test="${empty gv.gvimg }"> 
				        <img src="img/class3.jpg" /> 
				       </c:if> 
				       <div class="classification-content-text"> 
				        <h6>${gv.gclassify}</h6> 
				        <%-- <p class="name">${gv.gname}</p> --%>
				        <p class="name" style="display: none;">${gv.gname}</p> 
				        <p class="name1">主讲教师:${gv.teacherName}</p> 
				       </div> 
				       <c:if test="${gv.gmoney=='0'}"> 
				        <div class="study">免费 <span>${gv.gplayNo}人学习</span> </div> 
				       </c:if> 
				       <c:if test="${gv.gmoney!='0'}"> 
				        <div class="study"> ￥${gv.gmoney} <span>${gv.gplayNo}人学习</span> </div> 
				       </c:if> 
				      </a> 
				     </div> 
				    </c:forEach> 
				   </div> 
				  </c:if>
				  
				<c:if test="${shulun.size()>0 }">
				<div class="classification-content" style="padding:5px;">
				<div class="classification-titile">
					<h4>数论</h4>
				</div>
				    <c:forEach items="${shulun}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study" >￥${gv.gmoney}
												<span style="width:100px;">${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
						</div>
				</c:if>
				
					
				<c:if test="${jihe.size()>0 }">
				<div class="classification-content" style="padding:5px;">
				<div class="classification-titile">
					<h4>几何</h4>
				</div>
				    <c:forEach items="${jihe}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study">￥${gv.gmoney}
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
						</div>
				</c:if>
				
				
				<c:if test="${jishu.size()>0 }">
				<div class="classification-content" style="padding:5px;">
				<div class="classification-titile">
					<h4>计数</h4>
				</div>
				    <c:forEach items="${jishu}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study">￥${gv.gmoney}
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
						</div>
				</c:if>
				
				
								<c:if test="${yingyongti.size()>0 }">
								<div class="classification-content"style="padding:5px;" >
				<div class="classification-titile">
					<h4>应用题</h4>
				</div>
				    <c:forEach items="${yingyongti}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study">￥${gv.gmoney}
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
							</div>
				</c:if>
			
				
								<c:if test="${fangcheng.size()>0 }">
								<div class="classification-content" style="padding:5px;">
				<div class="classification-titile">
					<h4>方程</h4>
				</div>
				    <c:forEach items="${fangcheng}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study">￥${gv.gmoney}
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
						</div>
				</c:if>
				
				
								<c:if test="${xingcheng.size()>0 }">
								<div class="classification-content" style="padding:5px;">
				<div class="classification-titile">
					<h4>行程</h4>
				</div>
				    <c:forEach items="${xingcheng}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study">￥${gv.gmoney}
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
							</div>
				</c:if>
			
				
			<c:if test="${qita.size()>0 }">
			<div class="classification-content" style="padding:5px;">
				<div class="classification-titile">
					<h4>专题其他</h4>
				</div>
				    <c:forEach items="${qita}" var="gv">
							<div class="classification-content-every thisv">
									<input class="no" type="hidden" value="${gv.gid}">
									<input id="isMore_${gv.gid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
										<a>
										    <c:if test="${not empty gv.gvimg }">
										     <img src="${gv.gvimg}"/>
										    </c:if>
										     <c:if test="${empty gv.gvimg }">
											<img src="img/class3.jpg"/>
											</c:if>
											<div class="classification-content-text">
												<h6>${gv.gclassify}</h6>
												<%-- <p class="name">${gv.gname}</p> --%>
											    <p class="name" style="display:none;">${gv.gname}</p>
												<p class="name1" >主讲教师:${gv.teacherName}</p>
											</div>
											<c:if test="${gv.gmoney=='0'}">
											<div class="study">免费
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
											<c:if test="${gv.gmoney!='0'}">
											<div class="study">￥${gv.gmoney}
												<span>${gv.gplayNo}人学习</span>
											</div>
											</c:if>
										</a>
							</div>
						</c:forEach>
						</div>
				</c:if>
				
		</div>
	</div>
		
			
		</div>
		<%-- 	<div style="text-align:center;list-style-type:none;">
		<ul class="pagination">
			<li ><a>共${page.getTotalPages()}页</a></li>
			<li><a onclick="pageSize(1)">首页</a></li>
			<li ><a onclick="pageSize(${page.getPrePage()})">上一页</a></li>
			<li ><a onclick="pageSize(${page.getNextPage()})">下一页</a></li>
			<li><a onclick="pageSize(${page.getTotalPages()})">尾页</a></li>
			<li ><a>共${page.totalCount}条</a></li>
			<li><a>当前第${page.pageNo}页</a></li>
		</ul>
	</div> --%>
	</div>
	<div class="foot">
		<%@include file="/WEB-INF/jsp/footer.jsp" %>
	</div>


</body>
<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="js/videoList.js" type="text/javascript" charset="utf-8"></script>
<script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	
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
	
<script type="text/javascript" src="js/class.js?v=2"></script></html>