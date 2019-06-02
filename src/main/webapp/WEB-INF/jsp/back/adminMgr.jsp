<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
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
    <div class="panel-head"><strong class="icon-reorder"> 管理员列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li><a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/back/toAddAdmin.html">添加管理员</a> </li>
     </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="10%">序号</th>
        <th>管理员账号</th>
        <th>注册时间</th>
        <th width="10%">操作</th>
      </tr>
      <c:forEach items="${alist}" var="p" varStatus="pp">
      <tr>
	      <td>${pp.index+1}</td>
          <td>${p.userName}</td>
	      <td>${p.regTime}</td>
	      <td>
	          <div class="button-group"> 
		          <input type="hidden" value="${p.uid}" class="uid">
		          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
	          </div>
	      </td>
      </tr>
      </c:forEach>
    </table>
  </div>
  <script type="text/javascript">
  $(function(){
	  var error="${error}";
		if(error.length>0){
			alert(error);
		}
		//单个删除
		$(".border-red").on("click",function(){
			var uid = $(this).siblings(".uid").val();
		window.location.href="${pageContext.request.contextPath}/back/deleteAdmin?uid="+uid;	
		});
	})
  </script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>