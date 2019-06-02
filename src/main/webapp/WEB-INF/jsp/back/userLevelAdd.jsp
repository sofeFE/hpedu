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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增学习等级</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()"  action="${pageContext.request.contextPath}/back/userLevelAddClass">  
      <div class="form-group">
        <div class="label">
          <label>等级：</label>
        </div>
        <div class="field">
          <input type="number" class="input w50" id="level" name="level"  />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>等级描述：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="des" name="des"  placeholder="例如：学习新手"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>等级下限：</label>
        </div>
        <div class="field">
          <input type="number" class="input w50" id="minNum" name="minNum"  placeholder="等级最小课时"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>等级上限：</label>
        </div>
        <div class="field">
          <input type="number" class="input w50" id="maxNum" name="maxNum"  placeholder="等级最大课时"  />
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
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
    return true;
}

</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>