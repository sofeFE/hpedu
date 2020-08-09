<%@ page language="java" contentType="text/html;charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<meta charset="utf-8">
<base href="${pageContext.request.contextPath}/">
<title>简答题得分详情</title>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="css/public.css"/>
<link rel="stylesheet" type="text/css" href="css/index.css?v=1"/>
<body>

<div style="width:100%;background:#BDBDBD;font-weight:bold;padding:10px;text-align:left;">
    <ul>
        <li>【${gname}】简答题得分详情</li>
    </ul>
</div>
<c:forEach items="${jdt_list}" var="jdt" varStatus="st">
    <div style="width:100%;background:#EEEEEE;word-break:break-all;margin-top:10px;padding:5px;text-align:left;">
        <font style="font-weight:bold;"> ${st.index+1}.【简答题】（${jdt.score}分）：</font>
        <h4>&nbsp;&nbsp;&nbsp;${jdt.ucontent}&nbsp;&nbsp;</h4>
    </div>

    <div style="width:100%;word-break:break-all;margin-top:5px;;padding-left:10px;">
        您的回答：<textarea class="input" style="width:600px;height:120px;"
                       readonly="readonly">${not empty jdt.stuAnswer?jdt.stuAnswer:'此题您没有作答'}</textarea>
    </div>
    <div style="width:100%;margin-top:5px;padding-left:10px;">
        <p>您的得分：<font
                style="color:#CE0221;font-size:16px;font-weight:bold;text-decoration: underline;">${not empty jdt.stuGotScore?jdt.stuGotScore:'&nbsp;&nbsp;&nbsp;&nbsp;'}</font>
        </p>
        <c:if test="${not empty jdt.coment}">
            <p style="font-size:16px;width:100%;word-break:break-all;"><b>【教师批注】：</b>${jdt.coment}</p>
        </c:if>
        <c:if test="${not empty jdt.detail}">
            <p style="font-size:16px;width:100%;word-break:break-all;"><b>【题目详解】：</b>${jdt.detail}</p>
        </c:if>
    </div>
</c:forEach>
<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
</body>

<script type="text/javascript" src="js/class.js?v=2"></script></html>