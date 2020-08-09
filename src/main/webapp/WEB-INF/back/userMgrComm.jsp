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
    <div class="panel-head"><strong class="icon-reorder"> 普通用户列表</strong></div>
    <table class="table table-hover text-center">
      <tr>
        <th width="10%">序号</th>
        <th>用户昵称</th>
        <th>用户手机号</th>
        <th>用户头像</th>
        <th width="10%">注册时间</th>
        <th width="31%">操作</th>
      </tr>
        <c:forEach items="${pages.result}" var="p" varStatus="pp">
        <tr>
          <td>${pp.index+1}</td>
          <td>${p.userName}</td>
          <td>${p.phoneNo}</td>
          <td><c:if test="${not empty p.headImgUrl}"><img src="${p.headImgUrl}" style="width:40px;height:40px;"></c:if></td>
          <td>${p.regTime}</td>
          <td><div class="button-group"> 
          <input type="hidden" value="${p.uid}" class="uid">
          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
          </div></td>
        </tr>
       </c:forEach>
      <tr>
      </tr>
      <tr>
        <td colspan="10"><div class="pagelist"><em>共${pages.getTotalPages()}页</em>&nbsp &nbsp &nbsp<a onclick="pageSize(1)">首页</a> <a onclick="pageSize(${pages.getPrePage()})">上一页</a><a onclick="pageSize(${pages.getNextPage()})">下一页</a><a onclick="pageSize(${pages.getTotalPages()})">尾页</a>&nbsp &nbsp &nbsp<em>共${pages.totalCount}条</em>&nbsp &nbsp &nbsp<em>当前第${pages.pageNo}页</em></div></td>
      </tr>
    </table>
  </div>

<script type="text/javascript">
$(function(){
	//单个删除
	$(".border-red").on("click",function(){
		var uid = $(this).siblings(".uid").val();
	  window.location.href="${pageContext.request.contextPath}/back/deleteCommUser?uid="+uid;	
	});
})
function pageSize(num){
window.location.href="${pageContext.request.contextPath}/back/userMgrComm.html?pageNo="+num;
}
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>