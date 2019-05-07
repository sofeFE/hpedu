<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" type="text/css" href="css/regster.css">
<script type="text/javascript">
 var path_ctx="${pageContext.request.contextPath}";
</script>
<script type="text/javascript" src="libs/jquery.1.10.1.min.js"></script>
<script type="text/javascript" src="js/reg.js?v=3"></script>
<style type="text/css">
.input{padding:3% 2%;border:1px solid rgba(92, 184, 92, 0.6); width:99%;border-radius: 4px;}
span,input[type=submit],input[type=button],button{ cursor: pointer;}
</style>
</head>
<body>
	<div class="login">
		<div class="login-content">
			<div class="login-title">
				<h4>注册</h4>
			</div>
			<form  id="form1" method="post"  action="${pageContext.request.contextPath}/reg/user"  onsubmit="return chekLogin(0)">
				<div class="login-content-content">
					<div class="login-content-every">
						<input type="text" name="phoneNo" class="phone" placeholder="手机号" >
					</div>
					<div class="login-content-every">
						<input type="text" class="yanzhengma" name="ycode" placeholder="验证码" >
						<span class="button ycode" id="ycode" onclick="sendYZM(0)" >获取验证码</span>
					</div>
					<div style="text-align:center;">
						<span style="color:green;" id="span_ok"></span>
					</div>
					<div class="login-content-every">
						<input type="password" class="password" name="passWord" placeholder="密码"  >
					</div>
					<div class="login-content-every">
						<input type="password" class="password2" name="password2" placeholder="再次输入密码"  >
					</div>
					<div class="login-content-every">
						<input type="text" name="usedCode" id="usedCode" class="usedCode" placeholder="请输入邀请码" >
					</div>
					<div class="login-content-every">
						<select name="type" style="line-height:35px;width:200px;height:35px;">
						 <option value="0">普通用户</option>
						 <option value="1">学生家长</option>
						</select>
					</div>
					<div class="login-content-every-1">
						<a href="login.html">已有账户登录</a>
					</div>
					<div class="login-content-every">
						<input  id="rregbutton" type="submit" value="注册"  >
					</div>
					<em class="meaa"></em>
				</div>
			</form>
		</div>
	</div>
	</body>
	<script type="text/javascript">
	$(function(){
		var regError="${regError}";
		if(regError.length>0&&regError!="null"){
			alert(regError);
		}
		$("#rregbutton").removeAttr("disabled");
	});
	</script>
</html>