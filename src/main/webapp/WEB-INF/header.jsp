<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/5/17
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" language="java" %>

    <!-- logo图 和搜素-->
    <%@include file="videoSearch.jsp" %>
    <!--菜单-->
    <div class="sunmenu">
        <ul>
            <li><a href="classindex.html">首页</a></li>
            <li><a class="routine-menu">常规课</a></li>
            <li><a class="competition-menu  bg">竞赛课</a></li>
            <li><a class="quiz-menu">小测验</a></li>
        </ul>
        <%@include file="menuPublic.jsp" %>
    </div>
    <!-- 登录 -->
    <%@include file="ckUserInfo.jsp" %>

