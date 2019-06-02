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
<title>批改管理</title>
<link rel="stylesheet" href="css/back/pintuer.css">
<link rel="stylesheet" href="css/back/admin.css">
<script src="libs/jquery.js"></script>
<script src="libs/pintuer.js"></script>
</head>
<body style="background-color:#f2f9fd;padding:0px;margin:0px;" >
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
      <td id="gname_${t.usid}">${t.testname}</td>  
       <c:choose>
	          <c:when test="${t.utype==0 || t.utype==2}">
	          <td>常规</td>
	          </c:when>
	          <c:when test="${t.utype==1 || t.utype==3}">
	          <td>竞赛</td>
	          </c:when>
	          <c:when test="${t.utype==4}">
	          <td>小测验</td>
	          </c:when>
       </c:choose>
      <td>${t.subjectname}</td>
      <td>${t.ucreateTime}</td>
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
		window.location.href="${pageContext.request.contextPath}/back/doCorrect.html?usid="+id+"&gname="+gname;
	});
});
function pageSize(num){
	window.location.href="${pageContext.request.contextPath}/back/correctManger.html?pageNo="+num;
}
</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>