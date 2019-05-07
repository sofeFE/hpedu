<%@ page language="java" import="java.io.*" contentType="text/html;charset=utf-8"  
    pageEncoding="utf-8"%> 
    <!-- logo图 -->
		<div class='img'><img src="img/logo.png" /></div>
		<!-- 搜索 -->
		 <div class="search">
			<input type="text" class="text" id="search_keyword">
			<i class='iconfont icon-sousuo' onclick="searchVideo('search_keyword')"></i>
		</div>
<script>
function searchVideo(id){
	var keyword=$("#"+id).val();
	if(keyword.length>0){
		window.location.href="${pageContext.request.contextPath}/order/searchVideo.html?keyword="+keyword;
	}else{
		alert("搜索的视频名称不能为空!");
	}
}

</script>		
		