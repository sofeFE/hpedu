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
<body style="background-color:#f2f9fd;padding:0px;margin:0px;" >
<div class="header bg-main">
  <div class="logo margin-big-left fadein-top">
    <h1><img src="${pageContext.request.contextPath}/jsp/back/images/y.jpg" class="radius-circle rotate-hover" height="50" alt="" />厚朴教育试卷批改中心</h1>
  </div>
  <div class="head-l"><a class="button button-little bg-green" href="${pageContext.request.contextPath}/classindex.html" target="_blank"><span class="icon-home"></span> 前台首页</a> &nbsp;&nbsp;<a class="button button-little bg-red" href="back/backlogin.html"><span class="icon-power-off"></span> 退出登录</a> </div>
</div>
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder">待批改试卷</strong></div>
  <table class="table table-hover text-center">
    <tr>
      <th width="5%">序号</th>
      <th>测验名称</th>
      <th>测验类型</th>
      <th>科目</th>
      <th>做题日期</th>
      <th>操作</th>
    </tr>
   <c:forEach items="${pages.result}" var="t" varStatus="tt">
    <tr>
    <td>${tt.index+1}</td>
      <td id="gname_${t.usid}">${t.gname}</td>  
       <c:choose>
	          <c:when test="${t.utype==0 ||t.utype==2}">
	          <td>常规</td>
	          </c:when>
	          <c:when test="${t.utype==1 ||t.utype==3}">
	          <td>竞赛</td>
	          </c:when>
	          <c:when test="${t.utype==4}">
	          <td>小测验</td>
	          </c:when>
       </c:choose>
      <td>${t.gsbuject}</td>
      <td>${t.createTime}</td>
      <td><div class="button-group">
      <input type="hidden" class="tech" value="${t.usid}">
      <a class="button border-main border-up4" ><span class="icon-edit"></span> 去批改</a>
      </div></td>
    </tr>
    </c:forEach>
     <tr>
      </tr>
      <tr>
         <td colspan="8"><div class="pagelist"><em>共${pages.getTotalPages()}页</em>&nbsp &nbsp &nbsp<a onclick="pageSize(1)">首页</a> <a onclick="pageSize(${pages.getPrePage()})">上一页</a><a onclick="pageSize(${pages.getNextPage()})">下一页</a><a onclick="pageSize(${pages.getTotalPages()})">尾页</a>&nbsp &nbsp &nbsp<em>共${pages.totalCount}条</em>&nbsp &nbsp &nbsp<em>当前第${pages.pageNo}页</em></div></td>
      </tr>
  </table>
</div>
<script type="text/javascript">
$(function(){
	$(".border-up4").on("click",function(){
		var id = $(this).siblings(".tech").val();
		var gname=$("#gname_"+id).html();
		window.location.href="${pageContext.request.contextPath}/back/goToCorrectUnitTest.html?usid="+id+"&gname="+gname;
	});
});
function pageSize(num){
	window.location.href="${pageContext.request.contextPath}/back/correctIndex.html?pageNo="+num;
}
</script>
</body></html>