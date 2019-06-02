<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <base href="${pageContext.request.contextPath}/">
    <title>登录</title>
    <link rel="stylesheet" type="text/css" href="/css/back/pintuer.css">
    <link rel="stylesheet" type="text/css" href="/css/back/admin.css">
    <script type="text/javascript" src="/libs/jquery.js"></script>
    <script type="text/javascript" src="/libs/pintuer.js"></script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="xs6 xm4 xs3-move xm4-move">
            <div style="height:150px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <form id="checkLogin">
                <div class="panel loginbox">
                    <div class="text-center margin-big padding-big-top"><h1>厚朴教育管理中心</h1></div>
                    <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" class="input input-big" name="userName" id="userName" value="admin"
                                       placeholder="登录账号" data-validate="required:请填写账号"/>
                                <span class="icon icon-user margin-small"></span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="password" class="input input-big" name="passWord" id="passWord"
                                       value="admin" placeholder="登录密码"/>
                                <span class="icon icon-key margin-small"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="field field-icon-right">
                                <input type="text" autocomplete="off" id="captcha" class="input input-big" style="width: 150px;display: unset" name="captcha" placeholder="验证码">
                                <img src="captcha.jpg" id="SecurityCode" style="float:right" onclick="refresh()" alt=""/>
                                <a href="javascript:void(0)" style="display: block;" onclick="refresh()">刷新验证码</a>
                                <%--<span class="icon  margin-small"></span>--%>
                            </div>
                        </div>
                        <%--<div class="form-group">
                            <input type="radio" name="loginType" value="0" checked="checked">管理员
                            <input type="radio" name="loginType" value="1">批改教师
                        </div>--%>
                    </div>
                    <div style="padding:30px;"><input type="button"  class="button button-block bg-main text-big input-big" id="subbtn"
                                                      value="登录"></div>
                    <div id="errorMessage" style="text-align:center;color:red;padding-bottom:30px;"></div>
                </div>
            </form>
            <script type="text/javascript">
                function sub() {
                    var flag = false;
                    $("#errorMessage").html();
                    $("#subbtn").val("登录中...");
                    $("#subbtn").attr("disabled", "disabled");
                    //$("#subbtn").css("background","gray");
                    if ($("#userName").val().length == 0) {
                        sub3();
                    } else {
                        flag = true;
                    }
                    return flag;
                }

                function sub3() {
                    $("#subbtn").val("登录");
                    $("#subbtn").removeAttr("disabled");
                    //$("#subbtn").css("background","#08bbe1");
                }

                //fuck
                $("#subbtn").click(function () {
                    if (sub()) {
                        $.ajax({
                            url: "back/backUserLogin",
                            type: "post",
                            data: {
                                userName: $("#userName").val(),
                                passWord: $("#passWord").val(),
                                // loginType: $(":checked").val(),
                                captcha: $("#captcha").val()
                            },
                            dataType: "json",
                            success: function (data) {
                                checkResult(data);
                            }
                            ,complete:function(){
                                sub3();
                            }
                        });
                    }
                })

                function checkResult(data) {
                    if (data.code == 0) {
                        // 登录成功,
                        var url = "/back/backindex.html";
                        location.href = url;
                    } else {
                        $("#errorMessage").html(data.msg);
                    }
                }

                function refresh() {
                    $("#SecurityCode").attr("src", "captcha.jpg?t=" + $.now() );
                }

            </script>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>