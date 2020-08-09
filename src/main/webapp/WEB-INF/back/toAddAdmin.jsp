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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>添加管理员</strong></div>
  <div class="body-content">
    <form method="post" class="form-x"  action="${pageContext.request.contextPath}/back/addAdminClass"  onsubmit="return checkAdd()" >  
      <div class="form-group">
        <div class="label">
          <label>管理员账号：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="userName" id="userName"  data-validate="required:请输入管理员账号" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>管理员密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="passWord" name="passWord" data-validate="required:请输入管理员密码" />
          <div class="tips"></div>
        </div>
      </div>     
      
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" id="subbtn"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
$(function(){
	var error="${error}";
	if(error.length>0){
		alert(error);
	}
	
	 var addError="${addError}";
		if(addError.length>0&&addError!="null"){
			alert(addError);
		}
});
function checkAdd(){
	var flag=false;
	var name=$("#userName").val();
	var pwd=$("#passWord").val();
	if(name.length>0&&pwd.length>0){
		sub2();
		$.ajax({
			type:"post",
			url:"${pageContext.request.contextPath}/back/checkAdminIsExit",
			data:{name:name},
			async:false,
			success:function(res){
				if(res==0){
					flag=true;
				}else{
					alert("管理员账号已存在，添加失败！");
				}
			}
		});
	}else{
		alert("管理员账号或密码不能为空！");
	}
	if(!flag){
		sub3();
	}
	return flag;
}
function sub2(){
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
}
function sub3(){
	 $("#subbtn").html("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}
</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>