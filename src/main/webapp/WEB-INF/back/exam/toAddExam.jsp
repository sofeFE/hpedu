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
<script src="js/upload.js"></script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增${subject}</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" action="${pageContext.request.contextPath}/back/addExamClass" enctype="multipart/form-data">  
      <div class="form-group">
        <div class="label">
          <label>课程名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="etname"  />
          <input type="hidden" name="etsubject" value="${subject}">
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>封面图片：</label>
        </div>
        <div class="field">
        <img id="previewfm0"    style="width: 150px;height: 120px;" >
          <input type="file"  id="fm0"    class="timgUrl1" name="fm" onchange="change('测验封面','fm0','previewfm0')" >
        </div>
      </div>
       <c:if test="${!subject.equals('英语测验')}">
         <div class="form-group">
          <div class="label">
            <label>所属年级：</label>
          </div>
          <div class="field">
            <select name="etclass"  class="w50"  style="height:36px;line-height:34px;" id="etclass" data-validate="required:请选择所属年级">
              <option value="">--请选择年级--</option>
              <option value="三年级">三年级</option>
              <option value="四年级">四年级</option>
              <option value="五年级">五年级</option>
              <option value="六年级">六年级</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
        </c:if>
         <div class="form-group">
          <div class="label">
            <label>所属类别：</label>
          </div>
          <div class="field">
            <select name="etclassify"  class="w50"  style="height:36px;line-height:34px;" id="etclassify" data-validate="required:请选择所属分类">
              <option value="">--请选择类别--</option>
             <c:if test="${subject.equals('语文测验')}">
              <option value="上/下学期">上/下学期</option>
            </c:if> 
            <c:if test="${subject.equals('数学测验')}">
              <option value="上学期">上学期</option>
              <option value="下学期">下学期</option>
            </c:if>
            <c:if test="${subject.equals('英语测验')}">
              <option value="三至六年级">三至六年级</option>
            </c:if>
            </select>
            <div class="tips"></div>
          </div>
        </div>  
         <div class="form-group">
          <div class="label">
            <label>批改教师：</label>
          </div>
          <div class="field">
            <select name="teacherName" class="w50"  style="height:36px;line-height:34px;" >
              <option value="">--请选择批改教师--</option>
               <c:forEach items="${tclist}" var="t">
              <option value="${t.tname}">${t.tname}</option>
             </c:forEach>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="subbtn" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
var error="${error}";
if(error.length>0){
	alert(error);
}

function sub(){
	var flag=false
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 if(($("#etclass")&&$("#etclass option:selected").val().length==0)||$("#etclassify option:selected").val()==0){
		 sub3();
	 }else{
		 flag=true;
	 }
    return flag;
}
function sub3(){
	 $("#subbtn").html("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}

</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>