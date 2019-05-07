<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
	<meta charset="utf-8" />
	<title>竞赛视频</title>
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="css/index.css?v=1"/>
	<link rel="stylesheet" type="text/css" href="css/video.css"/>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">
	<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/main.js" type="text/javascript"></script>
	<script src="libs/bootstrap.min.js"></script>
	<style type="text/css">
		 .video-title2 ul{
			overflow: auto;
			/* border-bottom: 1px solid #d9dde1; */
		}
		.video-title2 ul li{
			padding: 2% 5%;
			float: left;
			font-size: 16px;
			cursor: default;
		}
		.video-title2 ul li:hover{
			color: red;
		}
	</style>
</head>
<body>
<div class="header">
<!-- logo图 和搜素-->
	<%@include file="../videoSearch.jsp"%>	
		<!--菜单-->
		<div class="sunmenu">
			<ul>
				<li><a href="classindex.html" >首页</a></li>
				<li><a class="routine-menu">常规课</a></li>
				<li><a  class="competition-menu  bg">竞赛课</a></li>
				<li><a class="quiz-menu">小测验</a></li>
			</ul>
	              <%@include file="../menuPublic.jsp"%>
		</div>
		<!-- 登录 -->
       <%@include file="../ckUserInfo.jsp"%>
</div>
		<!-- 视频 -->
		<div class="content">
		<div class="content-video" style="background:url('/img/bg23.png') ">
			<table style="width: 100%; height: 577px;">
				<tr>
					<td style="height: 577px;">
						<div id="myvideo" style="width: 100%; height: 100%;">Loading the player...</div>
					</td>
					<td style="width: 17%; border-left: 1px solid #fff;">
						<div style="height: 577px; width: 100%; overflow: auto;">
							<ul class="ce">
								<li><a href="javascript:void(0)" class="xz">播放目录</a>
									<ul class="er" style="display: block;">
										<li class="choose_li"
											style="word-break: break-all; word-wrap: break-word;"><a
											href="javascript:void(0)" style="color: black;"
											onclick="playVideo('${contestVideo.cvideoUrl}')">${contestVideo.cname}</a>
										</li>
									</ul>
								</li>
								<li><a href="javascript:" class="xz2">章节测试</a>
									<ul class="er2" style="display: block;">
										<li><a href="javascript:void(0)" id="testName"
											onclick="openUnitTest()">本章测验题</a></li>
									</ul>
								</li>
								<li><a href="javascript:" class="xz2">讲义下载</a>
									<ul class="er2" style="display: block;">
										<c:if test="${contestVideo.pdflist.size()==0}">
											<li><a href="javascript:void(0)">尚未上传讲义</a></li>
										</c:if>
										<c:forEach items="${contestVideo.pdflist}" var="pdf">
											<li><a href="javascript:void(0)"
												onclick="dowbLoad('${pdf.pdfUrl}')">${pdf.pdfUrl.replace("/pdf/","")}</a></li>
										</c:forEach>
									</ul>
								</li>
							</ul>
						</div>
					</td>
				</tr>
			</table>
		</div>
		<!-- 测验题弹出框 -->
		<button class="btn btn-primary btn-lg" style="display:none;" id="openBtn" data-toggle="modal" data-target="#msk_test">测验题弹框按钮</button>
		<div class="modal fade" id="msk_test" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" style="width: 850px;">
				<div class="modal-content" style="width: 850px;">
					<div class="modal-body" style="width: 850px;">
						<h4 style="color: #5CB85C;" id="h4_tip">测试题加载中...</h4>
						<div id="unitTest_div" style="display: none;"></div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					</div>
				</div>
			</div>
		</div>
		<script type="text/javascript" src="libs/jwplayer.js"></script>
		<script>jwplayer.key="WebEzzv0/FjWreLKIGCDbPbSN4WiZ+rC9+HPjg==";</script>
		<script type="text/javascript">
			function playVideo(url){
				changeVideoPlayNum();
				//切换视频播放
				initPlayer(playerInstance,url,true);
			}
			function openUnitTest(){
				$("#openBtn").click();
			} 
			
			var isBuy = "${isBuy}";//是否购买过
			var vvip = 0;
			var islogin = 0;
			var userIsVip = 0;
			var endTime;
			var timeII;
			var count = 7200;//2小时
			var playerInstance;
			var time;//播放时间
			var isPlay = false;
            var playerlimittime = 300;
			$(function() {
				
				playerInstance = jwplayer('myvideo');
				
				//			  	会员的标识是会员为1
				var bpn = $('.bpns').val();
				islogin = "${user==null?0:1}";//是否登录
				userIsVip = islogin == 0 ? 0 : "${user.isVip}";
				endTime = islogin == 0 ? ""
						: "${user.endTime==null?'':user.endTime}";
				vvip = "${contestVideo.cisVip}";
				//初始化视频  
				initPlayer(playerInstance, "${contestVideo.cvideoUrl}", false);
				$("#closeErweima").click(function() {//关闭二维码时候判断是否继续播放
					clearInterval(timeII);//停止计时器
					$.ajax({
						url : "${pageContext.request.contextPath}/order/checkOrderIsBuy",
						type : "post",
						data : {
							vid : "${contestVideo.cid}",
							vclassify : 1,
							uid : "${user.uid}"
						},
						async : false,
						success : function(res) {
							if (res == "ok") {
								isBuy = 1;
								playerInstance.play();
								$(".paybtn").removeAttr("onclick");
								$(".paybtn").html("已支付");
								$(".paybtn").css({
									background : "gray",
									color : "white"
								});
							}
						}
					});
								});
			});
			//设置播放器参数
			function initPlayer(playerInstance, url, autostart) {
				var playerwidth = $("#myvideo").width();
				var playerheight = $("#myvideo").height();
				playerInstance.setup({
					//视频文件路径
					file: url,
					//未播放前图片路径
					image: "/img/back.jpg",
					//播放器宽度
					width: playerwidth,
					//播放器高度
					height: playerheight,
					//设置默认播放器的渲染模式。
					primary: "flash",
					//设置视频铺满屏幕变换了形状
					stretching: "exactfit",
					autostart: autostart
					//是否自动播放
				});
				//监控播放时间  
				playerInstance.onTime(function() {
					//获取当前的播放时间  
					time = playerInstance.getPosition();
					if (!isPlay) {
						$.post("/changeVideoPlayTime", 
						{
							vid: "${contestVideo.cid}",
							vclassify: 1
						},
						function(resultBean) {
						    alert(resultBean.msg)
						});
						isPlay = true;
					}
					//不回答问题不能进行播放下面的视频  
					//if(vvip==1){
					if (vvip == 0) { //数据库中：0：是；1：否 (20170326-update)
						if (isNeedBuy()) {
							if (time >= playerlimittime) {
								//				  		让播放条滑动到设定的时间段
								playerInstance.seek(playerlimittime);
								//						暂停当前播放视频
								playerInstance.pause();
								payErweima();
							}
						}
					}
				});
			
			}
			function isNeedBuy() {
				if ((userIsVip != 1 && isBuy == "0") || (userIsVip == 1 && (!compareTo(endTime) 
						|| (compareTo(endTime) && !isRight(islogin))) && isBuy == "0") || islogin == "0") {
					return true;
				}
				return false;
			}
			function isRight(islogin) {
				if (islogin == "0") {
					return false;
				} else {
					var rightContent = "${user.rightContent}";
					if (rightContent.length == 0) {
						return true;
					} else {
						var class_g = "${contestVideo.cclass}";
						var gclassify = "${contestVideo.cclassify}";
						var gsbuject = "${contestVideo.competitionName}";
						if (class_g.indexOf("古诗") > -1
								|| class_g.indexOf("阅读") > -1
								|| class_g.indexOf("写作") > -1
								|| class_g.indexOf("语法") > -1
								|| class_g.indexOf("流利英语") > -1) {
							class_g = "";
						}
						if (class_g == "") {
							return rightContent.indexOf(gsbuject + gclassify) > -1 ? true
									: false;
						}
						var class_arr = class_g.split(",");
						for ( var i in class_arr) {
							if (rightContent.indexOf(gsbuject + class_arr[i]
									+ gclassify) > -1) {
								return true;
							}
						}
						return false;
					}
				}
			}
			function payErweima() {
				if (islogin == "0") {
					alert("您还没有登录！");
					return;
				}
				//发送请求到服务器生成订单号和二维码
				$.ajax({
					url: "${pageContext.request.contextPath}/order/getErweimaImg",
					data: {
						vid: "${contestVideo.cid}",
						vclassify: 1,
						uid: "${user.uid}"
					},
					type: "post",
					async: false,
					success: function(data) {
						var data = eval("(" + data + ")");
						var code = data.url;
						//修改二维码图片等信息
						if (code.length > 0) {
							var competitionName = "${contestVideo.competitionName}";
							var cclass = "${contestVideo.cclass}";
							var cclassify = "${contestVideo.cclassify}";
							var cname = "${contestVideo.cname}";
							if (cname.length > 0) {
								cname = "【" + cname + "】";
							}
							$("#bodyDes").html(competitionName + cclass + cclassify + cname); //视频名称
							$(".msk h3").html("￥" + data.omoney); //视频价格
							var url = "${pageContext.request.contextPath}/qr_codeImg?code_url=" + code;
							$("#erweimaImg").attr("src", url);
							$('.msk').show();
							timeStart(); //支付计时开始
						} else {
							alert("支付二维码生成失败，请联系客服！");
						}
					}
				});
			}
			function readPdf(url, This) {
				var flag = false;
				if (vvip == 0) {
					if (isNeedBuy()) {
						if (islogin == "0") {
							alert("您还没有登录！");
						} else {
							alert("您还没有购买此视频！");
						}
					} else {
						flag = true;
					}
				} else {
					flag = true;
				}
				if (flag) {
					$(This).attr("href", url);
					$(This).attr("target", "_blank");
				}
			}
			function dowbLoad(url) {
				var flag = false;
				if (vvip == 0) {
					if (isNeedBuy()) {
						if (islogin == "0") {
							alert("您还没有登录！");
						} else {
							alert("您还没有购买此视频，不能下载！");
						}
					} else {
						flag = true;
					}
				} else {
					flag = true;
				}
				if (flag) {
					window.open("${pageContext.request.contextPath}/download/file?url=" + url);
				}
			}
			function timeStart() {
				count = 7200;
				setSpanTime(count);
				var ii = setInterval(function() {
					timeII = ii;
					if (count > 0) {
						count--;
						setSpanTime(count);
					} else {
						$("#closeErweima").click();
					}

				}, 1000);
			}
			function setSpanTime(seconds) {
				$(".msk h4 span").html(getTimeVal(seconds));
			}
			function getTimeVal(seconds) {
				var val = "";
				var h = Math.floor(seconds / 3600);
				var m = Math.floor(seconds % 3600 / 60);
				var s = Math.floor(seconds % 3600 % 60);
				h = h < 0 ? 0 : h;
				m = m < 0 ? 0 : m;
				s = s < 0 ? 0 : s;
				val = h + "时" + m + "分" + s + "秒";
				return val;
			}
			//日期比大小	  
			function compareTo(endTime) {
				var flag = false;
				if (endTime.length > 0) {
					var currdate = new Date();
					//日期比较大小
					var endDate = new Date(Date.parse(endTime));
					if (endDate >= currdate) {
						flag = true;
					}
				}
				return flag;
			}
		</script>
			<c:if test="${!contestVideo.cmoney.equals('0')}">
			<div class="content-title" style="margin-top:0px;margin-bottom:0px;">
				<div class="content-title-content">
				<!-- 原价 -->
				 <c:if test="${killInfo==null||killInfo.timeType==2||contestVideo.isKill==0}">
						<div class="video-title2" style="margin-top:0px;">
							<ul style="margin-top:0px;padding-top:0px;padding-bottom:0px;">
								<li class="chapter2" style="color: red;height:60px;margin-top:0px;">视频价格</li>
							</ul>
						</div>
						<div class="classification-content video-chapter2" style="height:70px;line-height:70px;padding-top:1px;margin:0px;background:#E7442E;width:95%;">
						    
							     <table style="width:95%;"><tr><td style="color:white;font-weight:bold;padding-left:10px;">￥${contestVideo.cmoney}</td>
							     <td>
							     <c:if test="${isBuy==0}">
							     <div class="user-submit" style="height:60px;" >
								   <span style="height:46px;line-height:40px;background:#FEE97D;color:#E9623C;cursor:pointer;"  class="paybtn" onclick="payErweima()"  >去支付</span>
							      </div>
							      </c:if>
							      <c:if test="${isBuy!=0}">
							      <div class="user-submit" style="height:60px;" >
								   <span style="height:46px;line-height:40px;background:gray;color:white;"  >已支付</span>
							      </div>
							      </c:if>
							     </td></tr></table>
						</div>
				</c:if>
				<!-- 优惠价或原价 -->
				 <c:if test="${killInfo!=null&&killInfo.timeType!=2&&contestVideo.isKill==1}">
						<div class="video-title2" style="margin-top:0px;">
							<ul style="margin-top:0px;padding-top:0px;padding-bottom:0px;">
								<li class="chapter2" style="color: red;height:60px;margin-top:0px;">
								 <c:if test="${not empty contestVideo.killName}">【${contestVideo.killName}】</c:if>
								   &nbsp;&nbsp;
								<span style="color: red;" id="time_span" >   <c:if test="${killInfo.timeType==0}">
								    离活动开始还有:${killInfo.showTimerText}
								   </c:if>
								   <c:if test="${killInfo.timeType==1}">
								      还剩:${killInfo.showTimerText}
								   </c:if>
								   </span>
								</li>
							</ul>
						</div>
						<div class="classification-content video-chapter2" style="height:70px;line-height:70px;padding-top:1px;margin:0px;background:#E7442E;">
						    
							     <table style="width:95%;"><tr>
							      <td style="color:white;font-weight:bold;padding-left:10px;width:100px;">￥${contestVideo.killMoney}</td>
							       <td style="color:white;padding-left:5px;">专柜价：<a style='text-decoration:line-through;color:white;'>￥${contestVideo.cmoney}</a></td>
							     <td>
							      <c:if test="${killInfo.timeType==1 }">
								       <c:if test="${isBuy==0}">
									      <div class="user-submit" style="height:60px;" >
										   <span style="height:46px;line-height:40px;background:#FEE97D;color:#E9623C;cursor:pointer;" class="paybtn" onclick="payErweima()">马上抢</span>
									      </div>
								      </c:if>
								       <c:if test="${isBuy!=0}">
									      <div class="user-submit" style="height:60px;" >
										   <span style="height:46px;line-height:40px;background:gray;color:white;">已支付</span>
									      </div>
								      </c:if>
							      </c:if>
							      <c:if test="${killInfo.timeType==0 }">
								       <c:if test="${isBuy==0}">
									      <div class="user-submit" style="height:60px;" >
										   <span style="height:46px;line-height:40px;background:#FEE97D;color:#E9623C;cursor:pointer;" class="paybtn" onclick="payErweima()">去支付</span>
									      </div>
								      </c:if>
								       <c:if test="${isBuy!=0}">
									      <div class="user-submit" style="height:60px;" >
										   <span style="height:46px;line-height:40px;background:gray;color:white;"  >已支付</span>
									      </div>
								      </c:if>
							      </c:if>
							      
							     </td></tr></table>
						</div>
				</c:if>
			   </div>
			</div>
			</c:if>
			<script type="text/javascript">
			var timmer="${killInfo==null?0:killInfo.totalTime}";
			timmer=timmer/1000;
			var timeType="${killInfo==null?2:killInfo.timeType}";
			var  isKill="${contestVideo.isKill}";
			 $(function(){
				if(timeType!=2&&isKill==1){
					if(timeType==1){//活动进行中
						timeStart_money2(timmer);
					}else{//活动未开始
						timeStart_money(timmer);
					}
				}
		   }); 
			 var timeII_money;
			 function timeStart_money(timmer){
				  setSpanTime_money(timmer);
				  var ii=setInterval(function(){
					  timeII_money=ii;
						  if(timmer>0){
							  timmer--;
							  setSpanTime_money(timmer);
						  }else{
							  clearInterval(timeII_money);//停止计时器
								$.ajax({
				        			url:"${pageContext.request.contextPath}/order/getEndKillTime",
				        			type:"post",
				        			data:{vid:"${contestVideo.cid}",vclassify:1},
				        			async:false,
						  		    success:function(res){
						  		    	timmer=eval("("+res+")").totalTime/1000;
						  		    	timeStart_money2(timmer);
						  		    	$(".paybtn").html("马上抢");
						  		    }
				        		 });
						  }
						 
					  }, 1000);
			  }
			  function setSpanTime_money(seconds){
				  $("#time_span").html("离活动开始还有:"+getTimeVal2(seconds));
			  }
			  function timeStart_money2(timmer){
				  setSpanTime_money2(timmer);
				  var ii=setInterval(function(){
					  timeII_money=ii;
						  if(timmer>0){
							  timmer--;
							  setSpanTime_money2(timmer);
						  }else{
							  clearInterval(timeII_money);//停止计时器
							  $("#time_span").html("活动已结束");
							  $(".paybtn").html("去支付");
						  }
						 
					  }, 1000);
			  
			  }
			  function setSpanTime_money2(seconds){
				  $("#time_span").html("还剩:"+getTimeVal2(seconds));
			  }
			  function getTimeVal2(seconds){
				    var val="";
				    var day=Math.floor(seconds/(3600*24));
				    var h=Math.floor((seconds-day*3600*24)/3600);
					var m=Math.floor((seconds-day*3600*24-h*3600)/60);
					var s=Math.floor(seconds-day*3600*24-h*3600-m*60);
					 day=day<0?0:day;
					 h=h<0?0:h;
					 m=m<0?0:m;
					 s=s<0?0:s;
					val=day+"天"+h+"时"+m+"分"+s+"秒";
					return val;
				}
			</script>
			<div class="content-title">
				<div class="content-title-content">
					<div class="video-title">
						<ul>
							<li class="chapter" style="color: red;cursor: pointer">简介</li>
							<li class="chapter_jy" style="cursor: pointer">推荐视频</li> 
							<li class="comment" style="cursor: pointer">评论</li>
						</ul>
					</div>
					<div class="classification-content video-chapter" style="padding-bottom:10px;">
					  ${generalVideo.gintro}
					</div>
				    <div class="video-chapter_jy" style="display: none;">
						<div class="classification-content">
						 	           <!-- 视频 -->	
							<c:forEach items="${conList}" var="c">
								<div class="classification-content-every thisc">
									<input class="cno" type="hidden" value="${c.cid}">
									<a>
								 	  <c:if test="${c.cvimg==null||c.cvimg.length()==0 }">
										<img src="img/class5.jpg"/>
									  </c:if>	
									   <c:if test="${c.cvimg!=null&&c.cvimg.length()>0 }">
									     <img src="${c.cvimg}"/>
									   </c:if>
										<div class="classification-content-text">
											<h6>${c.cclassify}</h6>
										<%-- 	<p>${c.cname}</p> --%>
											<p  style="display:none;">${c.cname}</p>
										     <p >主讲教师:${c.teacherName}</p>
										</div>
									</a>
								</div>
								</c:forEach>
				       </div>
				</div>
					<div class="video-comment" >
						<div class="video-every" id="subEval_div">
						<c:if test="${empty user }">
						<div class="user-name-one">登陆后评论</div>
						<div class="user-info"><textarea placeholder="说说你对该视频的看法" disabled="disabled" ></textarea></div>
							<div class="user-submit" onclick="subEvaluation(0)">
								<span class="btn btn-success">提交评论</span>
						    </div>
						</c:if>
						<c:if test="${! empty user }">
							<div class="user-name-one">${user.userName}:</div>
							<div class="user-info"><textarea placeholder="说说你对该视频的看法，最多660字" name="evaluation" id="evaluation"></textarea></div>
							<div class="user-submit" onclick="subEvaluation(1)" id="subbtn">
								<span>提交评论</span>
							</div>
							<span style="color:green;" id="ok_span"></span>
							</c:if>
						</div>
						<c:forEach items="${elist}" var="e">
						<div class="video-every">
							<div class="user-name-one">${e.uname}:</div>
							<div class="user-info">
								<p>${e.evaluation}</p>
							</div>
							<div class="user-submit">
								<div>${e.ecreatTime}</div>
							</div>
						</div>
						</c:forEach>
					</div>
				</div>
			</div>

			
		</div>
		<div class="foot">
			<div class="foot-content">
				<div class="foot-content-menu">
					<ul>
						<li><a href="" style="cursor: pointer">网站首页</a></li>
						<li><a href="" style="cursor: pointer">企业合作</a></li>
						<li><a href="" style="cursor: pointer">人才招聘</a></li>
						<li><a href="" style="cursor: pointer">联系我们</a></li>
						<li><a href="" style="cursor: pointer">常见问题</a></li>
					</ul>
				</div>
				<p>Copyright © 2016 imooc.com All Rights Reserved | 京ICP备 13046642号-2</p>
			</div>
		</div>
	
		<div class="msk">
			<div class="modal-dialog">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title" id="myModalLabel">购买课程</h4>
		      </div>
		      <div class="modal-body">
		        <div class="study-subject">
		        	<h5 style="font-size:16px;" id="bodyDes">六年级小升初真题试炼上集</h5>
		        	<h3>￥188.99</h3>
		        	<div class="payment-all" style="text-align:center;">
		        		<div class="playment-mode">
		        			<div class="playment-mode-every">
		        				<h5>微信</h5>
		        				<h4>距离支付结束还剩<span style="color:red;font-size:15px;">2小时0分0秒</span></h4>
		        				<div class="playment-mode-img" style="height:280px;">
		        					<img src="" id="erweimaImg"  />
		        				</div>
		        			</div>
		        			
		        		</div>
		        	</div>
		        </div>
		      </div>
		      <div class="modal-footer">
		      	<span class="close-msk" data-dismiss="modal" id="closeErweima">关闭</span>
		      </div>
		    </div>
		  </div>
		</div>
	</body>
	<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
	<script src="js/video.js?v=1" type="text/javascript" charset="utf-8"></script>
	<script type="text/javascript">
	 var vid="${contestVideo.cid}";
		function checkBox(This,hide_id){
		    var val_h=$("#"+hide_id).val();
		    var arr=[];
		    if(val_h.length>0){
		    	arr=val_h.split(",");
		    }
			if($(This).is(":checked")){//勾选
				arr.push($(This).val());
			}else{//去除勾选
				arr.splice($.inArray($(This).val(),arr),1);
			}
			$("#"+hide_id).val(arr.join(","));
		}
		
		
		//初始化章节测验html
		function initUnitTests(vid,type,isRedo){
			isRedo=isRedo?1:0;
			 //加载章节测试题
			  $.post(
			      "${pageContext.request.contextPath}/back/getUnitTestsHtmlByVid",
				  {vid:vid,utype:type,isRedo:isRedo,vname:"${contestVideo.cname}"},
				  function(res){
				  res=eval("("+res+")");
				  if(res.status=="ok"){
					  if(res.data.length>0){
						 if(isRedo==1){//重做
							 $("#unit_testAll").html(res.data);
						 }else{//初始化
							 $("#unitTest_div").html(res.data);
						 }
						  $("#unitTest_div").show();
						  $("#h4_tip").hide();
					  }else{
						  changeHtml("即将上线，敬请期待！","#5CB85C");
					  }
				  }else{
					  changeHtml("测试题加载失败,请联系管理员！","red");
				  }
			  });
		}
		function changeHtml(html,color){
			  $("#h4_tip").show();
			  $("#unitTest_div").hide();
			  $("#unitTest_div").html("");
			  $("#h4_tip").css("color",color);
			  $("#h4_tip").html(html);
		}
		//展开全部测验题
		function slideDown_div2(This){
		    if(islogin==0){
		    	alert("您还没有登录！");
		    }else{
		    	 if($("#more_div").is(":hidden")){
		    		  $("#more_div").slideDown();
		    		  $(This).val("隐藏");
		    	  }else{
		    		  $("#more_div").slideUp();
		    		  $(This).val("展开全部");
		    	  }
		    }
	      }
		//提交打分
		function submitAnswer(){
			if(islogin==0){
		    	alert("您还没有登录！");
		    	return;
		    }
		    $("#submitAnswer_btn").attr("disabled",true);
	        var right_answer=[];
	        var user_answer=[];
	        var score=[];
	        var ids=[];
	        $("input[name='right_answer']").each(function(){
	            var val=$(this).val();
	            if(val.length>0){
	            	val=val.split(",").sort().join(",");
	            }
	        	right_answer.push(val);
	        });
	        $("input[name='user_answer']").each(function(){
	        	var val=$(this).val();
	            if(val.length>0){
	            	val=val.split(",").sort().join(",");
	            }
	        	user_answer.push(val);
	        	ids.push($(this).attr("id").replace("hide_",""));
	        });
	        $("input[name='score']").each(function(){
	        	score.push($(this).val());
	        });
	        //判断计分
	        var totalScore=0;
	        var right_num=0;
	        var error_num=0;
	        for(var i in ids){
	        	var right_a=right_answer[i];
	        	var user_a=user_answer[i];
	        	var score_a=score[i];
	        	if(right_a==user_a&&right_a.length>0){
	        		++right_num;
	        		totalScore+=parseInt(score_a);
	        		$("#sp_"+ids[i]).hide();
	        		$("#hideDiv_"+ids[i]).hide();
	        	}else{
	        		++error_num;
	        		$("#sp_"+ids[i]).show();
	        		$("#hideDiv_"+ids[i]).show();
	        	}
	        }
	        
	        
	        var isHasJDT=$("#isHasJDT").val();
	        var  an_html="";
	        if(isHasJDT!=1){
	            var prev_text="";
	        	if(isHasJDT==2){
	        		prev_text="选择题";
	        	}
	        	an_html=prev_text+'答对'+right_num+'题，答错'+error_num+'题，总共得分：<font style="color:#CE0221;font-size:25px;font-weight:bold;">'+totalScore+'</font><br>';
	        }
	        var JDT_answer=[];
	        if(isHasJDT!=0){
	        	 an_html+="<font style=\"color:green;font-weight:bold;\">简答题已提交，等待老师打分。查看方式：个人信息-编辑|查看-历史分数</font>";
	        	  $("textarea[name='JDT_answer']").each(function(){
	        	     var jdt_answer=$(this).val();
	        	     var jdt_id=$(this).attr("id").replace("JDT_","");
	        	     var obj={};
	        	     obj.jdt_answer=jdt_answer;
	        	     obj.jdt_id=jdt_id;
	        	     JDT_answer.push(obj);
	        	     $("#sp_"+jdt_id).show();
	         		 $("#hideDiv_"+jdt_id).show();
	              });
	        }
		    $("#result_tests").html(an_html);
			$("#result_tests").show();
			//将学生成绩入库
			$.post("${pageContext.request.contextPath}/back/addUserScore",{vid:vid,utype:1,score:totalScore,rightNum:right_num,errorNum:error_num,
				isHasJDT:isHasJDT,totalNums:$("#nums_hide").val(),totalScores:$("#scores_hide").val(),teacherName:"${contestVideo.teacherName}",JDT_answer:JSON.stringify(JDT_answer)},function(res){});
		}
		function redo(){
			initUnitTests(vid,1,1);
			//$("#result_tests").hide();
			//$("#submitAnswer_btn").attr("disabled",false);
		}
	$(function(){
		initUnitTests(vid,1);
		 setChangeTimeStatus(true);
	});
	var sub_flag=true;
	function subEvaluation(type){
		if(type==0){
			alert("请登录后再评论！");
		}else{
		  if(sub_flag){
			  sub_flag=false;
			  $("#subbtn").unbind();
			  $("#subbtn span").html("评论提交中...");
			  $("#subbtn span").css({background:"gray"});
			  var evaluation=$("#evaluation").val();
				var len=evaluation.length;
				if(len==0){
					alert("评论内容不可为空！");
				}else if(len>660){
					alert("评论内容最多660字！");
				}else{
						var vclassify=1;
						var uname="${user.userName}"; 
						$.ajax({
							url:"${pageContext.request.contextPath}/eval/addEvaluation",
							data:{evaluation:evaluation,vid:vid,vclassify:vclassify,uname:uname},
							type:"post",
						    async:false,
						    success:function(res){
						    	if(res.indexOf("ok")>-1){
						    		$("#ok_span").html("评论新增成功!");
						    		$("#evaluation").val("");
						    		var ecreatTime=res.split("_")[1];
						    		//追加一行评论信息
						    		var addhtml='<div class="video-every"><div class="user-name-one">'+uname+':</div><div class="user-info">'
										       +'<p>'+evaluation+'</p></div><div class="user-submit"><div>'+ecreatTime+'</div></div></div>';
						    		$("#subEval_div").after(addhtml);
						    		setTimeout(function(){$("#ok_span").html("");},2000);
						    	}else{
						    		alert(res);
						    	}
						    }
						}); 
				}
				 $("#subbtn").click(function(){
					 subEvaluation(type);
				 });
				  $("#subbtn span").html("提交评论");
				  $("#subbtn span").css({background:"rgb(92,184,92)"});
		  }else{
			  alert("不可重复提交！");
		  }
		  sub_flag=true;
	 }
	}
	
	
	
	
	//浏览器关闭
	window.onbeforeunload = function closeWindow(e){
		e = e || window.event;
	    if (e){
	    	changeVideoPlayNum();
	    	//alert("后退");
	    }
	};
	var agent="<%=request.getHeader("user-agent")%>";
	//浏览器后退
	jQuery(document).ready(function($) {
	  if(agent.indexOf("Safari")>-1){
		 return;
	  }
		  if (window.history && window.history.pushState) {
		    $(window).on('popstate', function() {
		      var hashLocation = location.hash;
		      var hashSplit = hashLocation.split("#!/");
		      var hashName = hashSplit[1];

		      if (hashName !== '') {
		        var hash = window.location.hash;
		        if (hash === '') {
		         //alert("后退");
		         changeVideoPlayNum();
		          window.history.back();
		        }
		      }
		    });
		    window.history.pushState('forward', null, "");
		  }
		});
	</script>
	<script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
	<form action="" id="subFrom" method="post">
	     <!-- 常规视频菜单 -->
	     <input type="hidden" name="gsbuject" id="gsbuject">
	     <input type="hidden" name="gclass" id="gclass">
	     <input type="hidden" name="gclassify" id="gclassify">
	      <!-- 常规单个 -->
	     <input type="hidden" name="className" id="className">
	     <input type="hidden" name="classNo" id="classNo">
	      <!-- 竞赛视频菜单 -->
	     <input type="hidden" name="competitionName" id="competitionName">
	     <input type="hidden" name="cclassify" id="cclassify">
	     <input type="hidden" name="cclass" id="cclass">
	     <!-- 小测验菜单 -->
	     <input type="hidden" name="etsubject" id="etsubject">
	     <input type="hidden" name="etclassify" id="etclassify">
	     <input type="hidden" name="etclass" id="etclass">
	     <!-- 分页参数 -->
	     <input type="hidden" name="pageNo" id="pageNo">
	</form>
</html>
