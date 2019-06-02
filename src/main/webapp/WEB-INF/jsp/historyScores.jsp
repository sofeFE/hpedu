<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="css/public.css" />
<link rel="stylesheet" type="text/css" href="css/index.css?v=1" />
<style type="text/css">
ul.pagination {
    display: inline-block;
    padding: 0;
    margin: 0;
}

ul.pagination li {display: inline;
}

ul.pagination li a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
    border: 1px solid #ddd;
}

.pagination li:first-child a {
    border-top-left-radius: 5px;
    border-bottom-left-radius: 5px;
}

.pagination li:last-child a {
    border-top-right-radius: 5px;
    border-bottom-right-radius: 5px;
}

ul.pagination li a.active {
    background-color: #4CAF50;
    color: white;
    border: 1px solid #4CAF50;
}

ul.pagination li a:hover:not(.active) {background-color: #ddd;}
 
</style>

<div class="content"   style="width:100%;padding:0px;" >
<table class="table table-hover text-center">
      <tr>
        <th  style="text-align:center;">序号</th>
        <th  style="text-align:center;">测验名称</th>
        <!-- <th>科目</th> -->
        <th  style="text-align:center;">测验类型</th>
        <th  style="text-align:center;">总分数</th>
       <!--  <th>题目总数</th> -->
       <!--  <th>答对题数</th>
        <th>答错题数</th> -->
        <th  style="text-align:center;">选择题得分</th>
        <th  style="text-align:center;">简答题得分</th>
        <th  style="text-align:center;width:65px;">总得分</th>
        <th style="text-align:center;">做题日期</th>
      </tr>
       <c:forEach items="${pages.result}" var="p" varStatus="pp">
        <tr>
          <td>${pp.index+1}</td>
          <td>${p.subjectname}-${p.testname}</td>
          <%-- <td>${p.gsbuject}</td> --%>
            
          <c:choose>
	          <c:when test="${p.utype==0 ||p.utype==2}">
	          <td>常规</td>
	          </c:when>
	          <c:when test="${p.utype==1 ||p.utype==3}">
	          <td>竞赛</td>
	          </c:when>
	          <c:when test="${p.utype==4}">
	          <td>小测验</td>
	          </c:when>
          </c:choose>
            
          <td>${p.totalScores}</td>
         <%--  <td>${p.totalNums}</td> --%>
          <%-- <td>${p.rightNum}</td>
          <td>${p.errorNum}</td> --%>
          <td>${p.score}</td>
            
          <c:if test="${p.JDTscore!=null&&(p.isHasJDT==1||p.isHasJDT==2)}">
          <td><a href="${pageContext.request.contextPath}/checUnitTestJDTDetail.html?usid=${p.usid}&gname=${p.gname}" target="_blank" style="text-decoration: underline;color:blue;">${p.JDTscore}</a></td>
          </c:if>
            
           <c:if test="${p.JDTscore==null||(p.JDTscore!=null&&p.isHasJDT!=1&&p.isHasJDT!=2)}">
            <td>${p.JDTscore}</td>
           </c:if>
            
          <c:if test="${p.gotScore==null&&(p.isHasJDT==1||p.isHasJDT==2)}">
           <td style="color:red;">批改中</td>
          </c:if>
          <c:if test="${p.gotScore!=null||(p.gotScore==null&&(p.isHasJDT!=1&&p.isHasJDT!=2))}">
          <td style="color:green;">${p.gotScore}</td>
          </c:if>
            <!-- 使用jsp:setProperty标签将时间戳设置到Date的time属性中 -->
          <td><fmt:formatDate value="${p.ucreateTime}" pattern="yyyy-MM-dd HH:mm" /></td>
        </tr>
   	</c:forEach>
    </table>
</div>
<div style="text-align:center;list-style-type:none;">
    <ul class="pagination">
        <li ><a>共${pages.getTotalPages()}页</a></li>
        <li><a onclick="pageSize(1)">首页</a></li>
        <li ><a onclick="pageSize(${pages.getPrePage()})">上一页</a></li>
        <li ><a onclick="pageSize(${pages.getNextPage()})">下一页</a></li>
        <li><a onclick="pageSize(${pages.getTotalPages()})">尾页</a></li>
        <li ><a>共${pages.totalCount}条</a></li>
        <li><a>当前第${pages.pageNo}页</a></li>
    </ul>
</div>
<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js"	type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
function pageSize(num){
		window.location.href="${pageContext.request.contextPath}/historyScores.html?pageNo="+num;
}
function transforToDate(longDate){
    return new Date(longDate) ;
}
    // alert( "example :" + new Date(20190517004743).Format("yyyy-MM-dd hh:mm:ss")) ;

</script>



