<%@ page language="java" import="java.io.*" contentType="text/html;charset=utf-8"  
    pageEncoding="utf-8"%> 
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<div class="logo">

			<c:if test="${ empty user  }">		
				<a href="login.html" class='right'>登录/注册</a>
				<a href="back/backindex.html" >管理系统</a>
			</c:if>
			<c:if test="${not empty user  }">	
			    <div class="not-login-in">
				<c:if test="${! empty user }">
					<div class="user">
					   <div class="user-img">
							<c:if test="${ empty user.headImgUrl}">
								<img src="img/K.jpg" /> 
							</c:if>
							<c:if test="${ not empty user.headImgUrl}">
								<img src="${user.headImgUrl}" /> 
							</c:if>
						   
						</div>
						
						<div class="user-name" style="padding-top:0px;">
						    &nbsp;
							<a href="javascript:showSetting()" >
									<c:if test="${not empty user.userName}">
										${user.userName}<span class="glyphicon glyphicon-chevron-down"></span>
									</c:if>
									<c:if test="${empty user.userName}">
									  ${user.phoneNo}<span class="glyphicon glyphicon-chevron-down"></span>
									</c:if>
							</a>
							<a href="back/backindex.html" >管理系统</a>
						</div>
						<div class="user-setting">
							<ul>
								<li style="height:40px;line-height: 40px;font-size: 17px;padding:5px;">个人信息
									<a href="${pageContext.request.contextPath}/userNews.html" 
									   class="glyphicon glyphicon-pencil" >编辑|查看</a>
								</li>
								<li style="height:40px;line-height: 40px;font-size: 17px;padding:5px;">购买记录
									<a href="${pageContext.request.contextPath}/order/checkOrdersByUid.html" 
									   class="glyphicon glyphicon-eye-open" >查看</a>
								</li>
								<li class="exitUser" onclick="quit()"
									style="border: 2px solid;background: #5cb85c;
									 color: #fff;height:40px;line-height:40px;text-align: center;">退出
									
								</li>
							</ul>
						</div>
					</div>
				</c:if>
				
				</div>
                    
			</c:if>

			

		</div>
<script>
function showSetting(){
	if($(".user-setting").is(":hidden")){
		 $(".user-setting").css("display","block");
	}else{
		 $(".user-setting").css("display","none");
	}
}
function quit(){
    $.ajax({
		url:'/user/exitUser' ,
		type:'get',
		resultType:'json',
		success: function(result){
		    if(result.code == 0){
		        location.href = '/classindex.html' ;
			}else{
		     	alert(result.msg);   
			}
		}
	})
}
</script>		
		