<%@ page language="java" import="java.io.*" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>5xx</title>
</head>
<body>
    <div>

        <img src="/img/500.jpg" >
        <p >
            You're not supposed to be here... Why don't you just go on  <a href="/index" ><img src="/img/500-save.jpg"></a>
        </p>
        <!---->
    </div>
<div>

    <span id="exceptionMessage">
       异常信息: ${errMsg}
        <%--errors:${errors}<br>
        error:${error}<br>
        message:${message}<br>
        exception:${exception}<br>
        trace:${trace}<br>
        path:${path}<br>--%>
    </span>
</div>

</body>
<script src="/libs/jquery.js"></script>
<script type="text/javascript">

</script>
<script type="text/javascript" src="js/class.js?v=2"></script></html>