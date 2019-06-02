<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<base href="${pageContext.request.contextPath}/">
	<title>个人信息</title>
	
	<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="css/index.css?v=1" />
	<link rel="stylesheet" type="text/css" href="css/user.css" />
	<link rel="stylesheet" href="css/iconfont.css">
	<link rel="stylesheet" href="css/commen.css">
	<link rel="stylesheet" href="css/reset.css">
		<style type="text/css">
	.input {
		font-size: 14px;
		padding: 5px;
		border: solid 1px #ddd;
		width: 100%;
		line-height: 20px;
		display: block;
		border-radius: 3px;
		-webkit-appearance: none;
	}
	</style>
</head>
<body>
	<div class="header">
		<%@include file="/WEB-INF/jsp/header.jsp" %>
	</div>
	<!--内容-->
	<div class="content" >
		<!-- <div class="title">
				个人信息
			</div> -->
		<div class="user-info" >
			<div class="user-titile">
				<ul>
					<li><h3 class="user-info-content">用户信息</h3></li>
					<li><h3 class="modify-password-all">修改信息</h3></li>
					<li><h3 class="learnTimeTable">学习时间表</h3></li>
					<li><h3 class="mysubject">学习中(${learnCount})</h3></li>
					<li><h3 class="historyScore">历史分数</h3></li>
				</ul>
			</div>
			
		   <!-- 学习时间表 -->
		   <div class="learnTimeTable-all" style="display:none;text-align:center;">
		     <div style="padding:0 35%;"> <table>
		      <tr><th>学习总时长：</th><th colspan="2" id="totalTime_th"></th></tr>
		      <tr><th>当前月累计：</th><th colspan="2" id="monthTime_th"></th></tr>
				 
		      <tr><th colspan="3">&nbsp;</th></tr>
		      <tr>
				  <td>
				  <select id="year" name="year" style="width:100px; height:36px;line-height:34px;">
				  <c:forEach items="${yearList }" var="y">
					   <option value="${y }" <c:if test="${y==year }">selected="selected"</c:if> >${y }年</option>
				  </c:forEach>
				  </select>
				  </td>
		      <td>
		      <select id="month" name="month"  style="width:100px;height:36px;line-height:34px;">
		      <c:forEach begin="1" end="12" var="m">
		       	<option value="${m}" <c:if test="${m==month }">selected="selected"</c:if> >${m }月</option>
		      </c:forEach>
		      </select>
		      </td>
				  
		      <td>
		        &nbsp;<input type="button" class="btn btn-primary" id="btn" value="查询" onclick="changeLearnChart(0)">
		      </td>
		      </tr>
		      </table>
		      </div>
		         <h4 style="color:#5CB85C;display:none;" id="tip_h4"> 抱歉，当前日期没有学习记录 </h4>
			   
				  <h4 style="color:#5CB85C;display:none;" id="tip_h4_prev"> 上半月 </h4>
		         <canvas id="prev_Chart" width="820" height="300"></canvas>
			   
		          <h4 style="color:#5CB85C;display:none;" id="tip_h4_next"> 下半月 </h4>
		         <canvas id="next_Chart" width="820" height="300" style="display:none;"></canvas>
		   </div>
			
		   <!-- 学习中课程 -->
		   <div class="mysubject-all" style="display:none;">
		     <iframe  src="${pageContext.request.contextPath}/learnHistory.html" style="margin:0;padding:0;width:100%;height:650px;border:0;"></iframe>
		     </div>
			
		     <!-- 历史测验分数 -->
		   <div class="historyScore-all" style="display:none;">
             <iframe style="margin:0;padding:0;width:100%;height:700px;border:0;" src="${pageContext.request.contextPath}/historyScores.html"> </iframe>
		   </div>
		   
		   <!-- 基本信息 -->
			<div class="user-content user-all-info">
				<div class="user-content-every">
					<label for="">头像：</label>
					<c:if test="${not empty user.headImgUrl}">
						<img alt="" style="width: 160px; height: 130px;" src="${user.headImgUrl }">
					</c:if>
					<c:if test="${ empty user.headImgUrl}">
						<img style="width: 150px; height: 120px;"
							src="${pageContext.request.contextPath}/img/K.jpg">
					</c:if>
				</div>
				<div class="user-content-every">
					<label for="">昵称：</label>
					<c:if test="${not empty user.userName}">
						<input type="text" name="" id="" value="${user.userName}"
							 disabled="disabled" />
					</c:if>
					<c:if test="${empty user.userName}">
						<b><em style="color: red;font-style:normal;">您还没有昵称哦，快去添加一个吧 !</em></b>
					</c:if>
				</div>
				<div class="user-content-every">
					<label for="">email：</label>
					<c:if test="${not empty user.email}">
						<input type="text" name="" id="" value="${user.email}"
							 disabled="disabled" />
					</c:if>
					<c:if test="${ empty user.email}">
						<b><em style="color: red;font-style:normal;">您还没有完善邮箱信息哦 !</em></b>
					</c:if>
				</div>
				<div class="user-content-every">
					<label for="">注册时间:</label> <input type="text" name="" id=""
						value="${user.regTime}" disabled="disabled" />
				</div>
				<c:if test="${user.isVip==1}">
				<div class="user-content-every" style="width:550px;">
					<label for="" style="width:110px;">VIP到期时间:</label> <input type="text" name="" id=""
						value="${user.endTime}" disabled="disabled" style="width:300px;" />
				</div>
				</c:if>
				<div class="user-content-every">
					<label for="" style="width:110px;">我的邀请码:</label> <img alt="" src="${user.yqCodeUrl}">
				</div>
				<div class="user-content-every">
					<label for="">学习等级:</label> 
					<c:if test="${leval!=null&&leval>0}">
					  <c:forEach  begin="1" end="${leval }" >
					    <img  src="${pageContext.request.contextPath}/img/starh.png">
					  </c:forEach>
					</c:if>
				</div>
				<div class="user-content-every"></div>
			</div>
			
			<%-- 修改信息--%>
			<form action="${pageContext.request.contextPath}/user/updateUserNews" onsubmit="return sub()" method="post" enctype="multipart/form-data">
				<div class="user-content modify-password">
					<div class="user-content-every">
						<label>头像：</label>
						<div class="field" style="margin-left: 111px;margin-top: -50px;">
						<img id="preview" alt="" src="${user.headImgUrl }" name="pic" style="width: 150px; height: 120px;">
						  <input type="file" id="f"  class="timgUrl1" name="timgUrl1" onchange="change()">
						</div>
					</div>
					<div class="user-content-every">
						<label for="">昵称：</label> 
						<input type="text" name="userName"  value="${user.userName}" placeholder="请输入昵称"  required="required" />
					</div>
					<div class="user-content-every">
						<label for="">email：</label> 
						<input type="email" name="email"  value="${user.email}" placeholder="请输入email" required="required"  />
					</div>
					<div class="user-content-every">
					<label for="">&nbsp;</label>
					<input type="hidden" name="uid" value="${user.uid}">
					<input  type="submit" id="subbtn"   value="修改" >
					</div>
				</div>
			</form>
		</div>
	</div>
	<div class="foot">
		<%@include file="/WEB-INF/jsp/footer.jsp" %>
	</div>
</body>
<script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
<script src="libs/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<script src="js/public.js" type="text/javascript" charset="utf-8"></script>
<script src="/libs/Chart-1.0.1-beta.4.js"></script>
<script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
$(function(){
	$(".user-info-content").click(function(){
		$(this).css("color","#5CB85C");
		$(".user-all-info").show();
		
		$(".modify-password-all").css("color","#000");
		$(".modify-password").hide();
		$(".learnTimeTable").css("color","#000");
		$(".learnTimeTable-all").hide();
		$(".mysubject").css("color","#000");
		$(".mysubject-all").hide();
		$(".historyScore").css("color","#000");
		$(".historyScore-all").hide();
	});
	$(".modify-password-all").click(function(){
		$(this).css("color","#5CB85C");
		$(".modify-password").show();
		
		$(".user-info-content").css("color","#000");
		$(".user-all-info").hide();
		$(".learnTimeTable").css("color","#000");
		$(".learnTimeTable-all").hide();
		$(".mysubject").css("color","#000");
		$(".mysubject-all").hide();
		$(".historyScore").css("color","#000");
		$(".historyScore-all").hide();
	});
	
	$(".learnTimeTable").click(function(){
		$(this).css("color","#5CB85C");
		$(".learnTimeTable-all").show();
		
		$(".user-info-content").css("color","#000");
		$(".user-all-info").hide();
		$(".modify-password-all").css("color","#000");
		$(".modify-password").hide();
		$(".mysubject").css("color","#000");
		$(".mysubject-all").hide();
		$(".historyScore").css("color","#000");
		$(".historyScore-all").hide();
	});
	
	$(".mysubject").click(function(){
		$(this).css("color","#5CB85C");
		$(".mysubject-all").show();
		
		$(".user-info-content").css("color","#000");
		$(".user-all-info").hide();
		$(".modify-password-all").css("color","#000");
		$(".modify-password").hide();
		$(".learnTimeTable").css("color","#000");
		$(".learnTimeTable-all").hide();
		$(".historyScore").css("color","#000");
		$(".historyScore-all").hide();
	});
	$(".historyScore").click(function(){
		$(this).css("color","#5CB85C");
		$(".historyScore-all").show();
		
		$(".user-info-content").css("color","#000");
		$(".user-all-info").hide();
		$(".modify-password-all").css("color","#000");
		$(".modify-password").hide();
		$(".learnTimeTable").css("color","#000");
		$(".learnTimeTable-all").hide();
		$(".mysubject").css("color","#000");
		$(".mysubject-all").hide();
	});
});
	function sub(){
		$("#subbtn").val("修改提交中...");
		$("#subbtn").attr("disabled","disabled");
		return true;
	}
	function change() {
		var pic = document.getElementById("preview"), file = document.getElementById("f");

		var ext = file.value.substring(file.value.lastIndexOf(".") + 1).toLowerCase();

		// gif在IE浏览器暂时无法显示
		if (ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
			alert("图片的格式必须为png或者jpg或者jpeg格式！");
			return;
		}
		var isIE = navigator.userAgent.match(/MSIE/) != null, isIE6 = navigator.userAgent.match(/MSIE 6.0/) != null;

		if (isIE) {
			file.select();
			var reallocalpath = document.selection.createRange().text;

			// IE6浏览器设置img的src为本地路径可以直接显示图片
			if (isIE6) {
				pic.src = reallocalpath;
			} else {
				// 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
				pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\""
						+ reallocalpath + "\")";
				// 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
				pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
			}
		} else {
			html5Reader(file);
		}
	}

	function html5Reader(file) {
		var file = file.files[0];
		var reader = new FileReader();
		reader.readAsDataURL(file);
		reader.onload = function(e) {
			var pic = document.getElementById("preview");
			pic.src = this.result;
		}
	}
</script>
<script type="text/javascript">
$(function(){
    
	//初始化累计学习时间
	getTimeVal2("${user==null?0:user.learnTime}","totalTime_th");
	//初始化学习时间表
	changeLearnChart();
	
	
});


//初始化学习时间表
function changeLearnChart(ii){
	if(ii==0){
		$("#btn").val("查询中...");
		$("#btn").attr("disabled",true);
	}
	var year=$("#year").val();
	var month=$("#month").val();
	$.ajax({
		type:"post",
		data:{year:year,month:month},
		url:"${pageContext.request.contextPath}/getUserlearnList",
		success:function(learnList){
			
			if(learnList.length==0){//没有记录
			  $("#tip_h4").show();
			  
			  $("#prev_Chart").hide();
			  $("#next_Chart").hide();
			  $("#tip_h4_prev").hide();
			  $("#tip_h4_next").hide();
			  
			  $("#monthTime_th").html("0秒");
			}else{ //有记录
			  $("#tip_h4").hide();
				var dateArr_preHelf=[];
				var durationArr_preHelf=[];
				var dateArr_nextHelf=[];
				var durationArr_nextHelf=[];
				var totalDuration_m=0;
				
				for(var i in learnList){
					totalDuration_m += learnList[i].time==null ? 0:learnList[i].time;
					if(i<16){
						dateArr_preHelf.push(learnList[i].learnDate);
						durationArr_preHelf.push(learnList[i].time);
					}else{
						dateArr_nextHelf.push(learnList[i].learnDate);
						durationArr_nextHelf.push(learnList[i].time);
					}
				}
                
				//初始化当前月累计时间
				getTimeVal2(parseInt(totalDuration_m*60),"monthTime_th");
				/*图标渲染*/
				setDataToChart(dateArr_preHelf,durationArr_preHelf,"prev_Chart");
				
				if(dateArr_nextHelf.length > 0){//数据超过16天
					 $("#tip_h4_prev").show();
					 $("#tip_h4_next").show();
					 
					setDataToChart(dateArr_nextHelf,durationArr_nextHelf,"next_Chart");
				}else{
					 $("#tip_h4_prev").hide();
					 
					 $("#tip_h4_next").hide();
					 $("#next_Chart").hide();
				}
			}
			
			if(ii==0){
				$("#btn").val("查询");
				$("#btn").attr("disabled",false);
			}
		}
	});
}
//日期秒转换成年月日
function getTimeVal2(seconds,id){
    var val="";
    var day=Math.floor(seconds/(3600*24));
    var h=Math.floor((seconds-day*3600*24)/3600);
	var m=Math.floor((seconds-day*3600*24-h*3600)/60);
	var s=Math.floor(seconds-day*3600*24-h*3600-m*60);
	 day=day<0?0:day;
	 h=h<0?0:h;
	 m=m<0?0:m;
	 s=s<0?0:s;
	val=(day>0?day+"天":"")+(h>0?h+"时":"")+(m>0?m+"分":"")+s+"秒";
	$("#"+id).html(val);
}
var options  = {
    scaleOverlay : false,  // 网格线是否在数据线的上面
    scaleOverride : false, // 是否用硬编码重写y轴网格线
    scaleSteps : null, //y轴刻度的个数
    scaleStepWidth : null, //y轴每个刻度的宽度
    scaleStartValue : null,  //y轴的起始值
    scaleLineColor : "rgba(0,0,0,.1)",// x轴y轴的颜色
    scaleLineWidth : 1,// x轴y轴的线宽  
    scaleShowLabels : true,// 是否显示y轴的标签
    scaleFontFamily : "'Arial'",// 标签的字体
    scaleFontSize : 12,// 标签字体的大小
    scaleFontStyle : "normal",// 标签字体的样式
    scaleFontColor : "#666",// 标签字体的颜色
    scaleShowGridLines : false,// 是否显示网格线
    scaleGridLineColor : "rgba(0,0,0,.05)",    // 网格线的颜色
    scaleGridLineWidth : 1, // 网格线的线宽
    scaleBeginAtZero: false, // y轴标记是否从0开始
    scaleShowHorizontalLines: true, // 是否显示横向线
    scaleShowVerticalLines: true, // 是否显示竖向线
    barShowStroke : true, // 是否显示线
    barStrokeWidth : 2,   // 线宽
    barValueSpacing : 5,// 柱状块与x值所形成的线之间的距离
    barDatasetSpacing : 1,// 在同一x值内的柱状块之间的间距
    animation : true,//是否有动画效果
    animationSteps : 60,//动画的步数
    animationEasing : "easeOutQuart",// 动画的效果
    showTooltips: false, // 是否显示提示 
    // 图例
    // 动画完成后调用的函数(每个柱上显示对应的数据)
    onAnimationComplete: function () {
        var ctx = this.chart.ctx;
        ctx.font = this.scale.font;
        ctx.fillStyle = this.scale.textColor;
        ctx.textAlign = 'center';
        ctx.textBaseline = 'bottom';

        this.datasets.forEach(function (dataset){
            dataset.bars.forEach(function (bar) {
                var value=bar.value;
                var valueStr=value==null?"0":value+"";
                var showTitle_Y="";
                if(valueStr.indexOf(".")>-1){//有小数
                    var m=valueStr.substring(0,valueStr.indexOf("."));
                    var s=parseInt((value-m)*60);
                    showTitle_Y=m+"分钟"+s+"秒";
                }else{
                    showTitle_Y=value+"分钟";
                }
                ctx.fillText(showTitle_Y, bar.x, bar.y);
            });
        });
    } /*null*/
};
function setDataToChart(dateArr,timeArr,id){
	$("#"+id).show();
    var canvas = $("#" + id);
    canvas.attr("width",820);
    canvas.attr("height",300);
    var ctx = document.getElementById(id).getContext("2d");
	var data = {
		labels :dateArr ,
		datasets : [
			{
				barItemName: "name1",
				fillColor : "#95B8E7",
				strokeColor : "rgba(220,220,220,1)",
				data : timeArr
			}
		]
	};
    new Chart(ctx).Bar(data,options);
	
}
function dfdf(ctx) {
    var data = {
        /// 表现在X轴上的数据，数组形式
        labels : ["January","February","March","April","May","June","July"],
        /// 第一条线
        datasets : [
            {
                /// 曲线的填充颜色
                fillColor : "rgba(220,220,220,0.5)",
                /// 填充块的边框线的颜色
                strokeColor : "rgba(220,220,220,1)",
                /// 表示数据的圆圈的颜色
                pointColor : "rgba(220,220,220,1)",
                /// 表示数据的圆圈的边的颜色
                pointStrokeColor : "#fff",
                data : [65,59,90,81,56,55,40]
            },
            /// 第二条线
            {
                fillColor : "rgba(151,187,205,0.5)",
                strokeColor : "rgba(151,187,205,1)",
                pointColor : "rgba(151,187,205,1)",
                pointStrokeColor : "#fff",
                data : [28,48,40,19,96,27,100]
            }
        ]
    }
    /// 动画效果
    var options = {

        //Boolean - If we show the scale above the chart data            
        scaleOverlay : false,

        //Boolean - If we want to override with a hard coded scale
        scaleOverride : false,

        //** Required if scaleOverride is true **
        //Number - The number of steps in a hard coded scale
        scaleSteps : null,
        //Number - The value jump in the hard coded scale
        scaleStepWidth : null,
        //Number - The scale starting value
        scaleStartValue : null,

        //String - Colour of the scale line    
        scaleLineColor : "rgba(0,0,0,.1)",

        //Number - Pixel width of the scale line    
        scaleLineWidth : 1,

        //Boolean - Whether to show labels on the scale    
        scaleShowLabels : true,

        //Interpolated JS string - can access value
        <%--scaleLabel : "<%=value%>",--%>

        //String - Scale label font declaration for the scale label
        scaleFontFamily : "'Arial'",

        //Number - Scale label font size in pixels    
        scaleFontSize : 12,

        //String - Scale label font weight style    
        scaleFontStyle : "normal",

        //String - Scale label font colour    
        scaleFontColor : "#666",

        ///Boolean - Whether grid lines are shown across the chart
        scaleShowGridLines : true,

        //String - Colour of the grid lines
        scaleGridLineColor : "rgba(0,0,0,.05)",

        //Number - Width of the grid lines
        scaleGridLineWidth : 1,

        //Boolean - Whether the line is curved between points
        bezierCurve : true,

        //Boolean - Whether to show a dot for each point
        pointDot : true,

        //Number - Radius of each point dot in pixels
        pointDotRadius : 3,

        //Number - Pixel width of point dot stroke
        pointDotStrokeWidth : 1,

        //Boolean - Whether to show a stroke for datasets
        datasetStroke : true,

        //Number - Pixel width of dataset stroke
        datasetStrokeWidth : 2,

        //Boolean - Whether to fill the dataset with a colour
        datasetFill : true,

        //Boolean - Whether to animate the chart
        animation : true,

        //Number - Number of animation steps
        animationSteps : 60,

        //String - Animation easing effect
        animationEasing : "easeOutQuart",

        //Function - Fires when the animation is complete
        onAnimationComplete : null

    }
    /// 创建对象，生成图表
    new Chart(ctx).Line(data,options);
}


// function getConfigs(){
	 // 柱状图选项设置
    
	// return options;
// }
 
	
</script>

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
<script type="text/javascript" src="js/class.js?v=2"></script></html>
