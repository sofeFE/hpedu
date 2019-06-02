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
    <div class="panel-head"><strong class="icon-reorder"> 英语课程列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <form method="get" action="back/backGeneral/english/searchAllEnglishGeneral.html" id="listform">
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/back/backGeneral/english/addEnglishGeneral.html"> 添加内容</a>
            <a class="button border-main icon-plus-square-o" href="${pageContext.request.contextPath}/back/backGeneral/addVideoMore.html?type=2"> 添加专题</a> </li>
         <li>
            <select name="gclass" style="height:40px;line-height:38px;margin-top:1px;width:200px;">
              <option value="" >--请选择类别--</option>
              <option value="新概念" <c:if test="${map['gclass']=='新概念' }">selected="selected"</c:if>>新概念</option>
              <option value="流利英语" <c:if test="${map['gclass']=='流利英语' }">selected="selected"</c:if>>流利英语</option>
              <option value="语法" <c:if test="${map['gclass']=='语法' }">selected="selected"</c:if>>语法</option>
              <option value="其他" <c:if test="${map['gclass']=='其他' }">selected="selected"</c:if>>其他</option>
            </select>
          </li>
              <li>
            <select name="gclassify" style="height:40px;line-height:38px;margin-top:1px;width:200px;">
              <option value="">--请选择分类--</option>
              <option value="第一册" <c:if test="${map['gclassify']=='第一册' }">selected="selected"</c:if>>第一册</option>
              <option value="第二册" <c:if test="${map['gclassify']=='第二册' }">selected="selected"</c:if>>第二册</option>
              <option value="KET考试" <c:if test="${map['gclassify']=='KET考试' }">selected="selected"</c:if>>KET考试</option>
              <option value="PET考试" <c:if test="${map['gclassify']=='PET考试' }">selected="selected"</c:if>>PET考试</option>
              <option value="流利英语" <c:if test="${map['gclassify']=='流利英语' }">selected="selected"</c:if>>流利英语</option>
              <option value="语法专项" <c:if test="${map['gclassify']=='语法专项' }">selected="selected"</c:if>>语法专项</option>
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
          <td>${p.gclassify}</td>
          <td><font color="#00CC99">${p.gclass}</font></td>
          <td>${p.gname}</td>
           <td>${p.teacherName}</td>
           <c:if test="${p.isKill=='1' }">
           <td style="color:#00CC99;">是</td>
          </c:if>
          <c:if test="${p.isKill!='1' }">
           <td style="color:#f00;">否</td>
          </c:if>
          <td>${p.gcreatTime}</td>
          <td><div class="button-group"> 
          <input type="hidden" value="${p.gid}" class="gid">
           <input type="hidden" value="${p.isMore}" id="isMore_${p.gid}">
          <a class="button border-main border-up"><span class="icon-edit"></span> 修改</a>
          <a class="button border-red" ><span class="icon-trash-o"></span> 删除</a> 
           <a class="button border-main icon-plus-square-o" onclick="addUnitTest('${p.gid}','${p.gname}',${p.isMore})" >添加测验题</a>
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
		var gid = $(this).siblings(".gid").val();
		var isMore=$("#isMore_"+gid).val();
	window.location.href="${pageContext.request.contextPath}/back/deleteEnglishGeneral?gid="+gid+"&isMore="+isMore;	
	});
	$(".border-up").on("click",function(){
		var gid = $(this).siblings(".gid").val();
		var isMore=$("#isMore_"+gid).val();
		if(isMore==1){//多关联
			window.location.href="${pageContext.request.contextPath}/back/backGeneral/updateVideoMore.html?gid="+gid;
		}else{
			window.location.href="${pageContext.request.contextPath}/back/backGeneral/english/upEnglishGeneral.html?gid="+gid;
		}
	})
})
function pageSize(num){
window.location.href="${pageContext.request.contextPath}/back/backGeneral/english/searchAllEnglishGeneral.html?gclass="+"${map['gclass']}"+"&"+"gclassify="+"${map['gclassify']}"+"&"+"pageNo="+num;
		}
//添加单元/章节测试题		
function addUnitTest(vid,vname,isMore){
  if(isMore&&isMore==1){//专题多集
	  window.location.href="${pageContext.request.contextPath}/back/backGeneral/addUnitTestMore.html?vid="+vid+"&type=0&vname="+vname+"&typeName=英语";
  }else{//内容单集
	  window.location.href="${pageContext.request.contextPath}/back/backGeneral/addUnitTest.html?vid="+vid+"&type=0&vname="+vname+"&typeName=英语";
  }
}	
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>