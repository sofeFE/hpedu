<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <base href="${pageContext.request.contextPath}/">
    <title>后台管理中心</title>
    <link rel="stylesheet" href="css/back/pintuer.css">
    <link rel="stylesheet" href="css/back/admin.css">
    <link rel="stylesheet" href="css/back/AdminLTE.min.css">
    <script src="libs/jquery.js"></script>
</head>
<body style="background-color:#f2f9fd;padding:0px;margin:0px;">


<div class="header bg-main">
    <div class="logo margin-big-left fadein-top">
        <h1><img src="/img/back/y.jpg" class="radius-circle rotate-hover" height="50" alt=""/>厚朴教育后台管理中心</h1>
    </div>
    <div class="head-l">
        <a class="button button-little bg-green" href="${pageContext.request.contextPath}/classindex.html"
           target="_blank">
            <span class="icon-home"></span> 前台首页
        </a> &nbsp;&nbsp;
        <a class="button button-little bg-red" href="/logout">
            <span class="icon-power-off"></span> 退出登录
        </a>

        <span style="color: white;font-size: 20px;">${visitorNum }人访问厚朴教育网站</span>
    </div>
</div>


<%--菜单列表开始--%>
<%--<div class="leftnav">--%>
    <%--<div class="leftnav-title" style="height:40px;">
        <strong><span class="icon-list"></span>菜单列表</strong>
    </div>--%>
    <%--原本内容位置--%>
    <div id="rrapp">
        <!-- /.search form -->
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="header">导航菜单</li>

            <!-- vue生成的菜单 -->
            <menu-item
                    v-for="each in menuList"
                    v-bind:item="each">
            </menu-item>
        </ul>
    </div>

<%--</div>--%>
<%--菜单列表结束--%>

<script type="text/javascript">
    $(function () {
        $(".leftnav h2").click(function () {
            $(this).next().slideToggle(200);
            $(this).toggleClass("on");
        })
        $(".leftnav ul li a").click(function () {
            $("#a_leader_txt").text($(this).text());
            $(".leftnav ul li a").removeClass("on");
            $(this).addClass("on");
        })
    });

    function makingYQCode() {
        // alert("正在执行");
        $.post("${pageContext.request.contextPath}/user/makingYQCode", {}, function (data) {
            alert("ok");
        });
    }
</script>
<script type="text/javascript" src="/jquery.min.js"></script>
<script type="text/javascript" src="app.js"></script>
<script type="text/javascript" src="bootstrap.min.js"></script>
<script type="text/javascript" src="/router.js"></script>
<script type="text/javascript" src="/vue.min.js"></script>
<script type="text/javascript" src="/vue-validator.min.js"></script>
<script type="text/javascript" src="/index.js"></script>
<ul class="bread">
    <li><a href="${pageContext.request.contextPath}/back/info.html" target="right" class="icon-home"> 首页</a></li>
    <li><a id="a_leader_txt">网站信息</a></li>
    <li><b>当前语言：</b><span style="color:red;">中文</span></li>
</ul>


<div class="admin">
    <iframe scrolling="auto" rameborder="0" src="${pageContext.request.contextPath}/back/info.html" name="right"
            width="100%" height="99.9%">
    </iframe>
</div>
<div style="text-align:center;">
</div>
</body>
</html>