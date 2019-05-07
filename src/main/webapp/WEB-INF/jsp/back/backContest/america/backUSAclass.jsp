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
    <div class="panel-head"><strong class="icon-reorder">美国大联盟杯课程列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <form method="get" action="back/backContest/america/backUSAclass.html" id="listform">
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/back/backContest/america/toAddUSAClass.html"> 添加内容</a> </li>
         <li>
            <select name="cclass"  style="width:200px;height:40px;line-height:38px;margin-top:1px;">
              <option value="" >--请选择类别--</option>
              <option value="三四年级" <c:if test="${map['cclass']=='三四年级' }">selected="selected"</c:if>>三四年级</option>
              <option value="五六年级" <c:if test="${map['cclass']=='五六年级' }">selected="selected"</c:if>>五六年级</option>
            </select>
          </li>
              <li>
            <select name="cclassify" style="width:200px;height:40px;line-height:38px;margin-top:1px;" >
              <option value="">--请选择分类--</option>
              <option value="真题" <c:if test="${map['cclassify']=='真题' }">selected="selected"</c:if>>真题</option>
              <option value="专题" <c:if test="${map['cclassify']=='专题' }">selected="selected"</c:if>>专题</option>
            </select>
          </li>
        <li>
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 查找课程</a>
          </li>
      </ul>
    </div>
    </form>
    <table class="table table-hover text-center">
      <tr>
        <th width="10%">序号</th>
        <th>课程分类</th>
        <th>课程年级</th>
        <th>课程名称</th>
        <th>主讲教师</th>
        <th>秒杀是否执行</th>
        <th>更新时间</th>
        <th width="25%">操作</th>
      </tr>
       <c:forEach items="${pages.result}" var="p" varStatus="pp">
        <tr>
          <td >${pp.index+1}</td>
          <td>${p.cclassify}</td>
          <td><font color="#00CC99">${p.cclass}</font></td>
          <td>${p.cname}</td>
          <td>${p.teacherName}</td>
          <c:if test="${p.isKill=='1' }">
           <td style="color:#00CC99;">是</td>
          </c:if>
          <c:if test="${p.isKill!='1' }">
           <td style="color:#f00;">否</td>
          </c:if>
          <td>${p.ccreatTime}</td>
          <td><div class="button-group"> 
          <input type="hidden" value="${p.cid}" class="cid">
          <a class="button border-main border-up"><span class="icon-edit"></span> 修改</a>
          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
          <a class="button border-main icon-plus-square-o" onclick="addUnitTest('${p.cid}','${p.cname}',${p.isMore})" >添加测验题</a>
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
function changesearch(){	
	  $("#listform").submit();
	}
$(function(){
	//搜索
	//单个删除
	$(".border-red").on("click",function(){
		var cid = $(this).siblings(".cid").val();
	window.location.href="${pageContext.request.contextPath}/back/deleteUSAClass?cid="+cid;	
	});
	$(".border-up").on("click",function(){
		var cid = $(this).siblings(".cid").val();
		window.location.href="${pageContext.request.contextPath}/back/backContest/america/toUpdateUSAClass.html?cid="+cid;
	})
})
function pageSize(num){
window.location.href="${pageContext.request.contextPath}/back/backContest/america/backUSAclass.html?cclass="+"${map['cclass']}"+"&"+"cclassify="+"${map['cclassify']}"+"&"+"pageNo="+num;
		}
//添加单元/章节测试题		
function addUnitTest(vid,vname,isMore){
  if(isMore&&isMore==1){//专题多集
	  window.location.href="${pageContext.request.contextPath}/back/backGeneral/addUnitTestMore.html?vid="+vid+"&type=1&vname="+vname+"&typeName=美国大联盟";
  }else{//内容单集
	  window.location.href="${pageContext.request.contextPath}/back/backGeneral/addUnitTest.html?vid="+vid+"&type=1&vname="+vname+"&typeName=美国大联盟";
  }
}		

</script>
</body>
</html>