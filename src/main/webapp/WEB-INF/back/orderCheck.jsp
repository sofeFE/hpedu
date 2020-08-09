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
    <div class="panel-head"><strong class="icon-reorder">订单列表</strong></div>
    <table class="table table-hover text-center">
      <tr>
        <th width="10%">序号</th>
        <th>订单编号</th>
        <th>购买视频名称</th>
        <th>视频分类</th>
        <th>购买者</th>
        <th>支付金额</th>
        <th>是否支付</th>
        <th>是否到期</th>
        <th width="10%">支付时间</th>
      </tr>
       <c:forEach items="${pages.result}" var="p" varStatus="pp">
        <tr>
          <td >${pp.index+1}</td>
          <td>${p.orderNo}</td>
          <td>${p.vclassify==0?p.gname:p.cname}</td>
          <td>${p.vclassify==0?'常规':'竞赛'}</td>
          <td>${p.userName}</td>
          <td>${p.omoney}</td>
          <c:if test="${p.oisPay!=1}">
           <td>否</td>
          </c:if>
          <c:if test="${p.oisPay==1}">
           <td style="color:#00CC99;">是</td>
          </c:if>
         <c:if test="${p.isUsed==1}">
           <td>否</td>
          </c:if>
          <c:if test="${p.isUsed!=1}">
           <td style="color:red;">${p.isUsed==0?'是':''}</td>
          </c:if>
           <td>${p.opayTime}</td>
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
function pageSize(num){
window.location.href="${pageContext.request.contextPath}/back/orderCheck.html?pageNo="+num;
		}
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>