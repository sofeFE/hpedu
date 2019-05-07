<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css"
	href="css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="css/public.css" />
<link rel="stylesheet" type="text/css"
	href="css/videoList.css" />
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

<div class="content"   style="width:100%;padding:0px;overflow-y:hidden; " >
		<div class="classification-content video-chapter" style="margin:0px;">			
			<c:forEach items="${pages.result}" var="gv">
			<div class="classification-content-every thisv2" 
			onclick="openVideo('${gv.vid}','${gv.vname}','${gv.vctype}','${gv.isMore==null?0:gv.isMore}')">
					<%-- <input class="no" type="hidden" value="${gv.vid}">
					<input id="isMore_${gv.vid}" type="hidden" value="${gv.isMore==null?0:gv.isMore}">
					<input id="vctype_${gv.vid}" type="hidden" value="${gv.vctype}"> --%>
						<a>
						    <c:if test="${not empty gv.img }">
						     <img src="${gv.img}"/>
						    </c:if>
						     <c:if test="${empty gv.img }">
							<img src="img/class3.jpg"/>
							</c:if>
							<div class="classification-content-text">
							   <h6>${not empty gv.classify2?gv.classify2:(gv.gclass=='新概念'?gv.vname:gv.classify)}</h6>
							   <%-- <p class="name">${gv.gname}</p> --%>
							    <p class="name" style="display:none;">${gv.vname}</p>
								<p class="name1" >主讲教师:${gv.teacherName}</p>
							</div>
							<c:if test="${gv.money=='0'}">
								<div class="study">免费
									<span>${gv.playno}人学习</span>
								</div>
							</c:if>
							<c:if test="${gv.money!='0'}">
								<div class="study">￥${gv.money}
									<span>${gv.playno}人学习</span>
								</div>
							</c:if>
						</a>
			</div>
		</c:forEach>
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
	<script
	src="libs/jquery.1.10.1.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/public.js"
	type="text/javascript" charset="utf-8"></script>
<script src="js/videoList.js"
	type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
/* $(function(){
 	$('.thisv2').on('click',function(){
		var vid = $(this).children('.no').val();
		var name=$(this).children('a').children('.classification-content-text').children(".name").html();
		var vctype=$("#vctype_"+vid).val();
		var isMore=$("#isMore_"+vid).val();
		if(vctype==0){
			if(isMore==0){
				top.location.href="general/general.html?className="+name+"&"+"classNo="+vid;
			}else{
				top.location.href="general/generalMore.html?className="+name+"&"+"classNo="+vid;
			}
		}else{
			top.location.href="contest/contest.html?className="+name+"&"+"classNo="+vid;	
		}
		
	});
 }); */
function pageSize(num){
		window.location.href="${pageContext.request.contextPath}/learnVideoPage.html?pageNo="+num;
}
function openVideo(vid,name,vctype,isMore){
	if(vctype==0){
		if(isMore==0){
			top.location.href="general/general.html?className="+name+"&"+"classNo="+vid;
		}else{
			top.location.href="general/generalMore.html?className="+name+"&"+"classNo="+vid;
		}
	}else{
		top.location.href="contest/contest.html?className="+name+"&"+"classNo="+vid;	
	}
}
</script>

	
</div>
	

