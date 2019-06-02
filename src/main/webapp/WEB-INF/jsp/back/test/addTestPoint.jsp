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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增测试知识点</strong></div>
  <div class="body-content">
    <form method="post" id="form" class="form-x" onsubmit="return sub()"  >  
    	<input style="display:none;" name="id" id="id" value="${testPoint.id }">
      <div class="form-group">
        <div class="label">
          <label>年级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="grade" value="${testPoint.grade }" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>知识点名字：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="pointName" value="${testPoint.pointName }" />
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="clear"></div>
      
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="subbtn"  type="button"> 提交</button>
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
	var flag=true;
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
    return flag;
}
$("#subbtn").on("click",function(){
	var id = $("#id").val();
	if( id!= null && id!=''){
		$("#form").prop("action","${pageContext.request.contextPath}/updateTestPoint");
	}else{
		$("#form").prop("action","${pageContext.request.contextPath}/addTestPoint");
	}
	$("#form").submit();
});

</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>