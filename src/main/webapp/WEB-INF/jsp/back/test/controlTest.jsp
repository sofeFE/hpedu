<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib  prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
        <li> <!-- 添加测试题 -->
       		<a class="button border-main icon-plus-square-o" 
       		href="${pageContext.request.contextPath}/back/test/addTest">添加测试题</a>
        </li>
        <li> <!-- 添加测试点 -->
       		<a class="button border-main icon-plus-square-o" 
       		href="${pageContext.request.contextPath}/back/test/controlTestPoint">管理知识点</a>
        </li>
        
        <li>年级:
            <select id="grade" name="grade" onchange="getTestPoint()" style="height:40px;line-height:38px;margin-top:1px;width:200px;">
              <option value="" >--请选择年级--</option>
            </select>
        </li>
        
        <li> 知识点:
         <select name="testPointId" id="testPointId" style="height:40px;line-height:38px;margin-top:1px;width:200px;" >
           <option value="">--请选择知识点--</option>
         </select>
        </li>
        
        <li>
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="searchTest(0)" > 查找测试题</a>
          </li>
      </ul>
    </div>
    </form>
    <table class="table table-hover text-center">
      <tr>
        <th width="5%">序号</th>
        <th width="5%">年级</th>
        <th width="10%">知识点</th>
        <th width="10%">题目</th>
        <th width="7%">题目类型</th>
        <th>答案</th>
        <th width="5%">分数</th>
        <th width="8%">更新时间</th>
        <th width="35%">操作</th>
      </tr>
      <tbody id="myTBody">
       <c:forEach items="${pages.result}" var="test" varStatus="pp">
        <tr>
          <td>${pp.index+1}</td><!-- 序号 -->
          <td><font color="#00CC99">${test.testPoint.grade }</font></td><!-- 年级 -->
          <td>${test.testPoint.pointName }</td><!-- 知识点 -->
          <td> ${test.testTitle}</td><!-- 题目 -->
          <td>${test.testType != 0 ? "填空":"选择"}</td><!-- 题目类型 -->
          <td>${test.testAnswer }</td><!-- 答案 -->
          <td> ${test.score }</td><!--分数 -->
          <td><fmt:formatDate value="${test.createTime}" type="both" /></td><!-- 更新时间 -->
          <td>
	          <div class="button-group"> 
		          <input type="hidden" value="${test.id }" class="id">
		          <input type="hidden" value="${test.testType }" class="testType">
		          <a class="button border-main border-up"><span class="icon-edit"></span> 修改</a>
		          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
	          </div>
          </td>
        </tr>
   	</c:forEach>
   	
   	<!-- 
      <tr>
      </tr>
       -->
      <tr>
         <td colspan="9">
         	<%@include file="page.jsp" %>
         </td>
      </tr>
      
      </tbody>
    </table>
  </div>

<script type="text/javascript">
//格式化毫秒
function getLocalTime(nS) { 
	return new Date(parseInt(nS)).toLocaleString().replace(/:\d{1,2}$/,' '); 
} 

$(function(){
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
	//搜索 单个删除
	modifyFunction();
	
})
function pageSize(num){
	window.location.href="${pageContext.request.contextPath}/back/test/controlTest?pageNo="+num; 
}
function modifyFunction(){//绑定事件
	//delete
	$(".border-red").on("click",function(){
		var id = $(this).siblings(".id").val();
		var testType = $(this).siblings(".testType").val();
		window.location.href="${pageContext.request.contextPath}/deleteTest?id="+id+"&testType="+testType;	
	});
	//update
	 $(".border-up").on("click",function(){
		var gid = $(this).siblings(".id").val();
		//window.location.href="${pageContext.request.contextPath}/back/exam/updateExam.html?id="+id;
	}) 
}
//填充知识点下拉框
function getTestPoint(){
	var grade = $("#grade").val();
	if(grade!=""){
		$.ajax({
			url:"${pageContext.request.contextPath}/getTestPointByGrade",
			type:"post",
			data:{"grade":grade},
			dataType:"json",
			success:function(result){
				var options = "" ;
				for(var i =0 ;i < result.length ; i++){
					options += "<option value= '"+result[i].tpid+"'  >"+result[i].pointName+"</option>"
				}
				$("#testPointId").html(options);
			}
		});
	}else{
		$("#testPointId").html("<option value=''>请选择年级</option>");
	}
}

//搜索.

function searchTest(num){
	var grade = $("#grade").val();
	var testPointId=$("#testPointId").val();
	$.ajax({
		url:"${pageContext.request.contextPath}/back/test/controlTest_JSON",
		type:"post",
		data:{"grade":grade,"testPointId":testPointId,"num":num},
		dataType:"json",
		success:function(pages){
			var tbody = "";
			for(var i = 0 ; i <pages.result.length ; i ++){
				var one = pages.result[i];
				var testType = one.testType==0?"选择":"填空" ;
				tbody += "<tr>";
				tbody += "<td>"+(i+1)+"</td>";//序号
				tbody += "<td><font color='#00CC99'>"+one.testPoint.grade+"</font></td>";//年级
				tbody += "<td>"+one.testPoint.pointName+"</td>";//知识点
				tbody += "<td>"+one.testTitle+"</td>";//题目
				tbody += "<td>"+testType+"</td>";//题目类型
				tbody += "<td>"+one.testAnswer+"</td>";//答案 
				tbody += "<td>"+one.score+"</td>";//分数
				tbody += "<td>"+getLocalTime(one.createTime)+"</td>";//更新时间
				
				tbody += "<td><div class='button-group'> "+
		          "<input type='hidden' value='"+one.id+"' class='id'> " +
		          "<input type='hidden' value='"+ one.testType +"' class='testType'>" +
		          "<a class='button border-main border-up'><span class='icon-edit'></span> 修改</a> "+ 
		          "<a class='button border-red' ><span class='icon-trash-o'></span> 删除</a> " +
	          		"</div></td>";
				
			}
			tbody+= 
					"<tr>"+
				         "<td colspan='9'>"+
					         "<div class='pagelist'>"+
						         "<em>共"+pages.totalPages+"页</em>&nbsp &nbsp &nbsp"+
						        " <a onclick='searchTest(1)'>首页</a> "+
						        " <a onclick='searchTest("+pages.prePage+")'>上一页</a>"+
						       "  <a onclick='searchTest("+pages.nextPage+")'>下一页</a>"+
						       "  <a onclick='searchTest("+pages.totalPages+")'>尾页</a>&nbsp &nbsp &nbsp"+
						       "  <em>共"+pages.totalCount+"条</em>&nbsp &nbsp &nbsp"+
						        " <em>当前第"+pages.pageNo+"页</em>"+
					         "</div>"+
				        " </td>"+
	     			" </tr>";
			$("#myTBody").html(tbody);
			//绑定事件
			modifyFunction();
		}
		
	});
}
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>