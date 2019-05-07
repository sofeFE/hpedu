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
    <%--<base href="${pageContext.request.contextPath}/">--%>
    <title>后台管理中心</title>

    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/back/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="/css/back/all-skins.min.css">
    <link rel="stylesheet" href="/css/back/main.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9] >
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper" id="rrapp" v-cloak>
    <header class="main-header">
        <a href="javascript:void(0);" class="logo">
            <!-- mini logo for sidebar mini 50x50 pixels -->
            <span class="logo-mini"><b>人人</b></span>
            <!-- logo for regular state and mobile devices -->
            <span class="logo-lg"><b>人人权限系统</b></span>
        </a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
            <!-- Sidebar toggle button-->
            <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
                <span class="sr-only">Toggle navigation</span>
            </a>
            <div style="float:left;color:#fff;padding:15px 10px;">欢迎 {{user.username}}</div>
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li><a href="javascript:;" @click="updatePassword"><i class="fa fa-lock"></i> &nbsp;修改密码</a></li>
                    <li><a href="logout"><i class="fa fa-sign-out"></i> &nbsp;退出系统</a></li>
                </ul>
            </div>
        </nav>
    </header>

    <!-- =============================================== -->

    <!-- Left side column. contains the sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- /.search form -->
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">导航菜单</li>

                <!-- vue生成的菜单 -->
                <menu-item  v-for="item in menuList" :item="item"></menu-item>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <!-- =============================================== -->
    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <ol class="breadcrumb" id="nav_title" style="position:static;float:none;">
                <li class="active"><i class="fa fa-home" style="font-size:20px;position:relative;top:2px;left:-3px;"></i> &nbsp; 首页</li>
                <li class="active">{{navTitle}}</li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content" style="background:#fff;">
            <iframe :src="main" scrolling="yes" frameborder="0" 
                    style="width:100%;min-height:500px;overflow:visible;background:#fff;" >
                
            </iframe>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <footer class="main-footer">
        <div class="pull-right hidden-xs">
            Version 4.0.0
        </div>
        Copyright &copy; 2019 <a href="https://www.renren.io" target="_blank">renren.io</a> All Rights Reserved
    </footer>

    <!-- Add the sidebar's background. This div must be placed
         immediately after the control sidebar -->
    <div class="control-sidebar-bg"></div>

    <!-- 修改密码 -->
    <div id="passwordLayer" style="display: none;">
        <form class="form-horizontal">
            <div class="form-group">
                <div class="form-group">
                    <div class="col-sm-2 control-label">账号</div>
                    <span class="label label-success" style="vertical-align: bottom;">{{user.username}}</span>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">原密码</div>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" v-model="password" placeholder="原密码"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-2 control-label">新密码</div>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" v-model="newPassword" placeholder="新密码"/>
                    </div>
                </div>
            </div>
        </form>
    </div>

</div>
<script type="text/javascript" src="/libs/jquery.min.js"></script>
<script type="text/javascript" src="/libs/app.js"></script>
<script type="text/javascript" src="/libs/bootstrap.min.js"></script>
<script type="text/javascript" src="/libs/router.js"></script>
<script type="text/javascript" src="/libs/vue.min.js"></script>
<script type="text/javascript" src="/libs/vue-validator.min.js"></script>
<script type="text/javascript" src="/js/index.js"></script>
<script type="text/javascript" src="/plugins/layer/layer.js"></script>

</body>


</html>