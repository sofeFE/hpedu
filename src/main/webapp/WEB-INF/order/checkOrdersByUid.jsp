<%@ page language="java" contentType="text/html;charset=utf-8" 
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<meta charset="utf-8">
<base href="${pageContext.request.contextPath}/">
<title>购买记录查看</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css?v=1"/>
<!-- 表格样式 -->
<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">

<style>
	.panel-head{background-color:#fcfcfc;padding:10px 15px;border-radius:4px 4px 0 0;border-bottom:solid 1px #ddd;}
</style>
</head>
<body>
	<div class="header">
		<%@include file="/WEB-INF/header.jsp" %>
</div>		
	<!--内容-->
	<div class="content" style="padding:10px;">
	
${msg }
  <div class="panel admin-panel" >
    <div class="panel-head"><strong class="icon-reorder">购买记录</strong></div>
    <table class="table table-hover text-center">
      <tr>
        <th width="10%">序号</th>
        <th>订单编号</th>
        <th>购买视频名称</th>
        <th>视频分类</th>
        <th>支付金额</th>
        <th>是否到期</th>
        <th width="10%">支付时间</th>
      </tr>
       <c:forEach items="${olist}" var="p" varStatus="pp">
        <tr>
          <td>${pp.index+1}</td>
          <td>${p.orderNo}</td>
          <td>${p.vclassify==0?p.gname:p.cname}</td>
          <td>${p.vclassify==0?'常规':'竞赛'}</td>
          <td>${p.omoney}</td>
           <c:if test="${p.isUsed==1}">
           <td>否</td>
          </c:if>
          <c:if test="${p.isUsed!=1}">
           <td style="color:red;">${p.isUsed==0?'是':''}</td>
          </c:if>
          <td>${p.opayTime}</td>
        </tr>
   	</c:forEach>
    </table>
  </div>
</div>  
<div class="foot" style=" position: fixed; bottom: 0; width: -webkit-fill-available;">
<%@include file="/WEB-INF/footer.jsp" %>
</div>

<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
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
	</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>