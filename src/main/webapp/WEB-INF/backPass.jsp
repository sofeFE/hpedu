<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="UTF-8">
    <title>找回密码页面</title>
    <link rel="stylesheet" type="text/css" href="css/regster.css">
    <script type="text/javascript">
        var path_ctx = "${pageContext.request.contextPath}";
    </script>
    <script type="text/javascript" src="libs/jquery.1.10.1.min.js"></script>
    <script type="text/javascript" src="js/reg.js?v=1"></script>
    <style type="text/css">
        span, input[type=submit], input[type=button], button {
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="login">
    <div class="login-content">
        <div class="login-title">
            <h4>找回密码</h4>
        </div>
        <form action="${pageContext.request.contextPath}/reg/backPass" method="post" onsubmit="return chekLogin(1)">
            <div class="login-content-content">
                <div class="login-content-every">
                    <input type="text" name="phoneNo" class="phone" placeholder="手机号">
                </div>
                <div class="login-content-every">
                    <input type="text" class="yanzhengma" name="ycode" placeholder="验证码">
                    <span class="button" id="ycode" onclick="sendYZM(1)">获取验证码</span>
                </div>
                <div style="text-align:center;">
                    <span style="color:green;" id="span_ok"></span>
                </div>
                <div class="login-content-every">
                    <input type="password" class="password" name="passWord" placeholder="密码">
                </div>
                <div class="login-content-every">
                    <input type="password" class="password2" name="password2" placeholder="再次输入密码">
                </div>
                <div class="login-content-every-1">
                    <a href="login.html">已有账户登录</a>
                </div>
                <div class="login-content-every">
                    <input type="submit" id="rregbutton" value="找回密码">
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    $(function () {
        var regError = "${regError}";
        if (regError.length > 0 && regError != "null") {
            alert(regError);
        }
        $("#rregbutton").removeAttr("disabled");
    });
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>