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
<div class="panel admin-panel">
  <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong></div>
  <div class="padding border-bottom">  
  <button type="button" class="button border-yellow"><span class="icon-plus-square-o"></span> 添加内容</button>
  </div>
  <table class="table table-hover text-center">
    <tr>
      <th width="10%">序号</th>
      <th width="10%">等级</th>
      <th width="20%">等级描述</th>
      <th width="20%">等级下限</th>
      <th width="20%">等级上限</th>
      <th>操作</th>
    </tr>
   <c:forEach items="${ulist}" var="t" varStatus="tt">
    <tr id="trss_${t.ulid}">
    <td>${tt.index+1}</td>
      <td>${t.level}</td>   
      <td>${t.des}</td>
      <td>${t.minNum}</td>
      <td>${t.maxNum}</td>
      <td><div class="button-group">
      <input type="hidden" class="tech" value="${t.ulid}">
      <a class="button border-main border-up" ><span class="icon-edit"></span> 修改</a>
      <a class="button border-red"><span class="icon-trash-o"></span> 删除</a>
      </div></td>
    </tr>
    </c:forEach>
  </table>
</div>
<script type="text/javascript">
$(function(){
	$('.border-up').on("click",function(){
		var id = $(this).siblings(".tech").val();
	 window.location.href="${pageContext.request.contextPath}/back/userLevelUp.html?ulid="+id;
	});
	$('.border-red').on('click',function(){
		var id = $(this).siblings(".tech").val();
	window.location.href="${pageContext.request.contextPath}/back/deleteUserLevel?ulid="+id;
	});
	$('.border-yellow').on('click',function(){
	window.location.href="${pageContext.request.contextPath}/back/userLevelAdd.html";	
	});
});

</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>