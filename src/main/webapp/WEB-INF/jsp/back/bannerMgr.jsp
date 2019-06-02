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
    <div class="panel-head"><strong class="icon-reorder"> 轮播图片列表</strong></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/back/bannerMgr/toAddBanner.html">添加轮播图片</a> </li>
     </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th width="4%">序号</th>
        <th width="6%">所属项目</th>
        <th width="6%">图片标题</th>
        <th>图片简介</th>
        <th>图片预览</th>
        <th width="31%">操作</th>
      </tr>
      <c:forEach items="${blist}" var="p" varStatus="pp">
      <tr id="trss_${p.bid}">
	      <td>${pp.index+1}</td>
	      <td>${(empty p.belong || p.belong==0 )?"网站":"公众号"}</td>
	      <td>${p.title}</td>
	      <td><div style="width:auto;height:auto;display:block;word-break: break-all;word-wrap: break-word;">${p.content}</div></td>
	      <td><c:if test="${not empty p.imgUrl}"><img src="${p.imgUrl}" style="width:100px;height:50px;">  </c:if></td>
	      <td>
	          <div class="button-group"> 
		          <input type="hidden" value="${p.bid}" class="bid">
		          <input type="hidden" value="${p.sort}" id="hide_sort_${p.bid}">
		          <a class="button border-main border-up"><span class="icon-edit"></span> 修改</a>
		          <a class="button border-main border-up2"><span class="icon-sort-up"></span> 上移</a>
		          <a class="button border-main border-up3"><span class="icon-sort-down"></span> 下移</a>
		          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
	          </div>
	      </td>
      </tr>
      </c:forEach>
    </table>
  </div>
  <script type="text/javascript">
  $(function(){
		//单个删除
		$(".border-red").on("click",function(){
			var bid = $(this).siblings(".bid").val();
		window.location.href="${pageContext.request.contextPath}/back/deleteBanner?bid="+bid;	
		});
		//修改内容
		$(".border-up").on("click",function(){
			var bid = $(this).siblings(".bid").val();
			window.location.href="${pageContext.request.contextPath}/back/bannerMgr/updateBanner.html?bid="+bid;
		});
		
		//修改顺序
		$(".border-up2").on("click",function(){
			var bid = $(this).siblings(".bid").val();
			changeSort(0,bid);
		});
		$(".border-up3").on("click",function(){
			var bid = $(this).siblings(".bid").val();
			changeSort(1,bid);
		});
	});
  //上移/下移调整顺序
  function changeSort(type,id){
  	 var cur=$("#trss_"+id);
  	 var change_obj=type==0?cur.prev():cur.next();
  	 var change_tr_id=change_obj.attr("id");
  	 if(change_tr_id){
  		 var cur_sort=$("#hide_sort_"+id).val();//当前的顺序值
  		 var change_id=change_tr_id.replace("trss_","");
  		 var new_sort=$("#hide_sort_"+change_id).val();//交换的顺序值
  		 //修改库顺序
  		 $.post("${pageContext.request.contextPath}/back/updateBannerSort",{id1:id,sort1:new_sort,id2:change_id,sort2:cur_sort},function(res){
  			
  			 if(res=="ok"){
  				 //修改页面顺序值
  				 $("#hide_sort_"+change_id).val(cur_sort);
  				 $("#hide_sort_"+id).val(new_sort)
  				 //调整表格行位置
  				 if(type==0){
  					 change_obj.before(cur);
  				 }else{
  					 change_obj.after(cur);
  				 }
  			 }else{
  				 alert(res);
  			 }
  		 });	
  	 }else{
  		 alert(type==0?"已经是第一个，不可再上移！":"已经是最后一个，不可再下移！"); 
  	 }
  }
  </script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>