<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8" />
<title>视频列表</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/index.css?v=1" />
<link rel="stylesheet" type="text/css" href="css/videoList.css" />
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
.chunji,.chunji p{
background:#DAF8C6;color:#000;
}
.shujia,.shujia p{
background:#FCF4B3;color:#000;
}
.qiuji,.qiuji p{
background:#FEF2E9;color:#000;
}
.hanjia,.hanjia p{
background:#E1F6F9;color:#000;
}		   

</style>
</head>
<body>
	<div class="header">
		<%@include file="/WEB-INF/jsp/header.jsp" %>
</div>
	<!--内容-->
	<div class="content"   style="width:100%;" >
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
			<c:if test="${page.totalCount==0}">
			 <h4 style="color:#5CB85C;">
			            即将上线，敬请期待
			 </h4>
			</c:if>
			<c:if test="${page.totalCount>0&&gclass=='新概念'}">
			<div style="width:100%;padding:0 35%;margin-bottom:10px;"> 
			 <table><tr><td>
			  <select id="nameType_select"   style="width:160px;height:36px;line-height:34px;">
			      <option value="">--请选择课程区间--</option>
		          <option value="1-50" <c:if test="${nameType=='1-50' }">selected="selected"</c:if>>1-50</option>
		          <option value="51-100" <c:if test="${nameType=='51-100' }">selected="selected"</c:if>>51-100</option>
		          <option value="101-144" <c:if test="${nameType=='101-144' }">selected="selected"</c:if>>101-144</option>
		      </select>
		      </td>
		      <td>
		        <select id="sort_select"   style="width:60px;height:36px;line-height:34px;">
			       <option value="desc" <c:if test="${sort=='desc' }">selected="selected"</c:if>>倒序</option>
			       <option value="asc" <c:if test="${sort=='asc' }">selected="selected"</c:if>>正序</option>
		      </select>
		      </td>
		      <td>
		      
		       &nbsp;<input type="button" class="btn btn-primary" id="btn" value="查询" onclick="pageSize(1)">
		       </td>
		       </tr></table>
		    </div>   
			</c:if>
			</div>
			
			<c:forEach items="${page.result}" var="gv">
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
							<c:choose>
							<c:when test="${gv.gclassify2=='春季班'}">
							<div class="classification-content-text chunji">
							</c:when>
							<c:when test="${gv.gclassify2=='暑假班'}">
							<div class="classification-content-text shujia">
							</c:when>
							<c:when test="${gv.gclassify2=='秋季班'}">
							<div class="classification-content-text qiuji" >
							</c:when>
							<c:when test="${gv.gclassify2=='寒假班'}">
							<div class="classification-content-text hanjia">
							</c:when>
							<c:otherwise>
							<div class="classification-content-text">
							</c:otherwise>
							</c:choose>
							<h6>${not empty gv.gclassify2?gv.gclassify2:(gv.gclass=='新概念'?gv.gname:gv.gclassify)}</h6>
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
			<div style="text-align:center;list-style-type:none;">
		<ul class="pagination">
			<li ><a>共${page.getTotalPages()}页</a></li>
			<li><a onclick="pageSize(1)">首页</a></li>
			<li ><a onclick="pageSize(${page.getPrePage()})">上一页</a></li>
			<li ><a onclick="pageSize(${page.getNextPage()})">下一页</a></li>
			<li><a onclick="pageSize(${page.getTotalPages()})">尾页</a></li>
			<li ><a>共${page.totalCount}条</a></li>
			<li><a>当前第${page.pageNo}页</a></li>
		</ul>
	</div>
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
	   
       function pageSize(num){
    	   var nameType=$("#nameType_select").val();
    	   var sort=$("#sort_select").val();
    	   general_list("general/generalVideoList.html","${gsbuject}","${gclass}","${gclassify}",num,nameType,sort);
			//window.location.href="general/generalVideoList.html?gsbuject="+"${gsbuject}"+"&"+"gclass="+"${gclass}"+"&"+"gclassify="+"${gclassify}"+"&"+"pageNo="+num;
		}
	</script>
	<script src="js/menuFun.js?v=2" type="text/javascript" charset="utf-8"></script>
	<form action="" id="subFrom" method="post">
	     <!-- 常规视频菜单 -->
	     <input type="hidden" name="gsbuject" id="gsbuject">
	     <input type="hidden" name="gclass" id="gclass">
	     <input type="hidden" name="gclassify" id="gclassify">
	      <!-- 常规单个 -->
	     <input type="hidden" name="className" id="className">
	     <input type="hidden" name="classNo" id="classNo">
	     <input type="hidden" name="nameType" id="nameType">
	     <input type="hidden" name="sort" id="sort">
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