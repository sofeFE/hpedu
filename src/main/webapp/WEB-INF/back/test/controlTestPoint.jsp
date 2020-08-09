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
    <form method="get" action="back/test/controlTest" id="listform">
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <!-- 添加测试点 -->
       		<a class="button border-main icon-plus-square-o" 
       			href="${pageContext.request.contextPath}/back/test/addTestPoint">添加知识点</a>
        </li>
        <li>
            <select name="grade" id="grade" style="height:40px;line-height:38px;margin-top:1px;width:200px;">
            <!-- 此处用ajax 动态 获取 年级  -->
              <option value="" >--请选择年级--</option>
            </select>
            
       	</li>
         
        <li>
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="searchTestPoint()" > 查找知识点</a>
        </li>
      </ul>
    </div>
    </form>
    
    
    <!-- 显示已经存在的知识点 -->
    <table class="table table-hover text-center">
      <tr>
        <th width="10%">序号</th>
        <th width="28%">年级</th>
        <th width="28%">知识点</th>
        <th width="34%">操作</th>
      </tr>
      
      <tbody id="myTBody">
      
       <c:forEach items="${pages.result}" var="p" varStatus="pp">
        <tr>
          <td >${pp.index+1}</td>
          <td><font color="#00CC99">${p.grade}</font></td>
          <td>${p.pointName}</td>
          <td>
	          <div class="button-group"> 
		          <input type="hidden" value="${p.id}" class="id">
		          <a class="button border-main border-up"><span class="icon-edit"></span> 修改</a>
		          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
	          </div>
          </td>
        </tr>
   	  </c:forEach>
   	
   	 <tr>
         <td colspan="8">
	         <%@include file="page.jsp" %>
         </td>
      </tr>
      
      
   	</tbody>
   	
      
      
    </table>
  </div>

<script type="text/javascript">

$(function(){
	bindFunction();
	$.ajax({
		url:"${pageContext.request.contextPath}/getAllGrade",
		type:"post",
		dataType:"json",
		success:function(result){
			var options = "" ;
			for(var i =0 ;i < result.length ; i++){
				options += "<option value= '"+result[i]+"'  >"+result[i]+"</option>"
			}
			
			$("#grade").append(options);
		}
	});
	
});

function search(){	
  $("#listform").submit();
}

function bindFunction(){
	//单个删除
	$(".border-red").on("click",function(){
		var id = $(this).siblings(".id").val();
		window.location.href="${pageContext.request.contextPath}/deleteTestPoint?id="+id;	
	});

	//修改
	$(".border-up").on("click",function(){
		var id = $(this).siblings(".id").val();
		window.location.href="${pageContext.request.contextPath}/back/test/updateTestPoint?id="+id;
	})
}

function pageSize(num){
	window.location.href="${pageContext.request.contextPath}/back/test/controlTestPoint?pageNo="+num;
}

function searchTestPoint(num){
	var grade=$("#grade").val();
	$.ajax({
		url:"${pageContext.request.contextPath}/back/test/controlTestPoint_JSON",
		type:"post",
		data:{"grade":grade,"num":num},
		dataType:"json",
		success:function(pages){
			var tbody = "";
			for(var i = 0 ; i <pages.result.length ; i ++){
				var one = pages.result[i];
				tbody += "<tr>";
				tbody += "<td>"+(i+1)+"</td>";
				tbody += "<td><font color='#00CC99'>"+one.grade+"</font></td>";
				tbody += "<td>"+one.pointName+"</td>";
				tbody += "<td><div class='button-group'> "+
		          "<input type='hidden' value='"+one.id+"' class='id'> " +
		          "<a class='button border-main border-up'><span class='icon-edit'></span> 修改</a> "+
		          "<a class='button border-red' ><span class='icon-trash-o'></span> 删除</a> " +
	          		"</div></td>";
				
			}
			tbody+= 
					"<tr>"+
				         "<td colspan='8'>"+
					         "<div class='pagelist'>"+
						         "<em>共"+pages.totalPages+"页</em>&nbsp &nbsp &nbsp"+
						        " <a onclick='searchTestPoint(1)'>首页</a> "+
						        " <a onclick='searchTestPoint("+pages.prePage+")'>上一页</a>"+
						       "  <a onclick='searchTestPoint("+pages.nextPage+")'>下一页</a>"+
						       "  <a onclick='searchTestPoint("+pages.totalPages+")'>尾页</a>&nbsp &nbsp &nbsp"+
						       "  <em>共"+pages.totalCount+"条</em>&nbsp &nbsp &nbsp"+
						        " <em>当前第"+pages.pageNo+"页</em>"+
					         "</div>"+
				        " </td>"+
	     			" </tr>";
			$("#myTBody").html(tbody);
			//绑定事件
			bindFunction();
		}
		
	});
}
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>