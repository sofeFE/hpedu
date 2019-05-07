<%@ page language="java" import="java.io.*" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>404</title>
</head>
<script src="/libs/jquery.js"></script>
<script type="text/javascript">

    // $("#exceptionMessage").html(message) ;
</script>
<body>

<div style="fill: #00aaee">
    <img src="/img/404-1.jpg" >
    <!---->
    <a href="/index" ><img src="/img/500-save.jpg"></a>
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
</html>