<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8"/>
    <title>常规视频_多集</title>
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="css/index.css?v=1"/>
    <link rel="stylesheet" type="text/css" href="css/video.css"/>
    <script src="libs/jquery.1.10.1.min.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>
    <script src="js/main.js" type="text/javascript"></script>
    <script src="libs/bootstrap.min.js"></script>
    <script src="js/menuFun.js" type="text/javascript" charset="utf-8"></script>
    <link rel="stylesheet" href="css/iconfont.css">
    <link rel="stylesheet" href="css/commen.css">
    <link rel="stylesheet" href="css/reset.css">
    <style type="text/css">
        .video-title2 ul {
            overflow: auto;
            /* border-bottom: 1px solid #d9dde1; */
        }

        .video-title2 ul li {
            padding: 2% 5%;
            float: left;
            font-size: 16px;
            cursor: default;
        }

        .video-title2 ul li:hover {
            color: red;
        }

        {
        :
        :
        -webkit-scrollbar-thumb
        }
    </style>
</head>
<body>
<div class="header">
    <%@include file="/WEB-INF/header.jsp" %>
</div>
<!-- 视频 -->
<div class="content">
    <div class="content-video" style="background:url('/img/bg23.png') ">
        <table style="width:100%;height:577px;">
            <tr>
                <td style="height:577px;">
                    <div id="myvideo" style="width:100%;height:100%;">Loading the player...</div>
                </td>
                <td style="width:17%;border-left:1px solid #fff;">
                    <div style="height:577px;width:100%;overflow:auto;">
                        <ul class="ce">
                            <li>
                                <a href="javascript:" class="xz">播放目录</a>
                                <ul class="er" style="display:block;">
                                    <c:forEach items="${generalVideo.vclist}" var="gl" varStatus="st">
                                        <c:if test="${st.index==0 }">
                                            <li id="vli_${st.index}" class="choose_li">
                                                <a href="javascript:void(0)" style="color:black;"
                                                   onclick="playVideo('${gl.vcideoUrl}',${st.index},'${gl.vcid}','${gl.vcname}',true)">${gl.vcname}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${st.index>0 }">
                                            <li id="vli_${st.index}">
                                                <a href="javascript:void(0)"
                                                   onclick="playVideo('${gl.vcideoUrl}',${st.index},'${gl.vcid}','${gl.vcname}',true)">${gl.vcname}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript:" class="xz2">章节测试</a>
                                <ul class="er2" style="display:block;">
                                    <li><a href="javascript:void(0)" id="testName"
                                           onclick="openUnitTest()">${videoChild.vcname}测验题</a></li>
                                </ul>
                            </li>
                            <li>
                                <a href="javascript:" class="xz2">讲义下载</a>
                                <ul class="er2" style="display:block;">
                                    <c:if test="${generalVideo.pdflist.size()==0}">
                                        <li><a href="javascript:void(0)">尚未上传讲义</a></li>
                                    </c:if>
                                    <c:forEach items="${generalVideo.pdflist}" var="pdf">
                                        <li><a href="javascript:void(0)"
                                               onclick="dowbLoad('${pdf.pdfUrl}')">${pdf.pdfUrl.replace("/pdf/","")}</a>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </td>
            </tr>
        </table>
    </div>
    <script type="text/javascript" src="plugins/jwplayer/jwplayer.js"></script>
    <script>jwplayer.key="WebEzzv0/FjWreLKIGCDbPbSN4WiZ+rC9+HPjg==";</script>
    
    
    <!-- 测验题弹出框 -->
    <button class="btn btn-primary btn-lg" style="display:none;" id="openBtn" data-toggle="modal"data-target="#msk_test"> 测验题弹框按钮 </button>
    <div class="modal fade" id="msk_test" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="width:850px;">
            <div class="modal-content" style="width:850px;">
                <div class="modal-body" style="width:850px;">
                    <h4 style="color:#5CB85C;" id="h4_tip"> 测试题加载中...</h4>
                    <div id="unitTest_div" style="display:none;">
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                </div>
            </div>
        </div>
    </div>


    <c:if test="${not empty weekVal }">
        <div style="width:100%;color:gray;font-size:16px;">【${weekVal}更新】</div>
    </c:if>
    <c:if test="${!generalVideo.gmoney.equals('0')}">
        <div class="content-title" style="margin-top:0px;margin-bottom:0px;">
            <div class="content-title-content">
                <!-- 原价 -->
                <c:if test="${killInfo==null||killInfo.timeType==2||generalVideo.isKill==0}">
                    <div class="video-title2" style="margin-top:0px;">
                        <ul style="margin-top:0px;padding-top:0px;padding-bottom:0px;">
                            <li class="chapter2" style="color: red;height:60px;margin-top:0px;">视频价格</li>
                        </ul>
                    </div>
                    <div class="classification-content video-chapter2"
                         style="height:70px;line-height:70px;padding-top:1px;margin:0px;background:#E7442E;width:95%;">

                        <table style="width:95%;">
                            <tr>
                                <td style="color:white;font-weight:bold;padding-left:10px;">￥${generalVideo.gmoney}</td>
                                <td>
                                    <c:if test="${isBuy==0}">
                                        <div class="user-submit" style="height:60px;">
                                            <span style="height:46px;line-height:40px;background:#FEE97D;color:#E9623C;cursor:pointer;"
                                                  class="paybtn" onclick="callPayFunction()">去支付</span>
                                        </div>
                                    </c:if>
                                    <c:if test="${isBuy!=0}">
                                        <div class="user-submit" style="height:60px;">
                                            <span style="height:46px;line-height:40px;background:gray;color:white;">已支付</span>
                                        </div>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
                <!-- 优惠价或原价 -->
                <c:if test="${killInfo!=null&&killInfo.timeType!=2&&generalVideo.isKill==1}">
                    <div class="video-title2" style="margin-top:0px;">
                        <ul style="margin-top:0px;padding-top:0px;padding-bottom:0px;">
                            <li class="chapter2" style="color: red;height:60px;margin-top:0px;">
                                <c:if test="${not empty generalVideo.killName}">【${generalVideo.killName}】</c:if>
                                &nbsp;&nbsp;
                                <span style="color: red;" id="time_span">   <c:if test="${killInfo.timeType==0}">
                                    离活动开始还有:${killInfo.showTimerText}
                                </c:if>
								   <c:if test="${killInfo.timeType==1}">
                                       还剩:${killInfo.showTimerText}
                                   </c:if>
								   </span>
                            </li>
                        </ul>
                    </div>
                    <div class="classification-content video-chapter2"
                         style="height:70px;line-height:70px;padding-top:1px;margin:0px;background:#E7442E;">

                        <table style="width:95%;">
                            <tr>
                                <td style="color:white;font-weight:bold;padding-left:10px;width:100px;">
                                    ￥${generalVideo.killMoney}</td>
                                <td style="color:white;padding-left:5px;">专柜价：<a
                                        style='text-decoration:line-through;color:white;'>￥${generalVideo.gmoney}</a>
                                </td>
                                <td>
                                    <c:if test="${killInfo.timeType==1 }">
                                        <c:if test="${isBuy==0}">
                                            <div class="user-submit" style="height:60px;">
                                                <span style="height:46px;line-height:40px;background:#FEE97D;color:#E9623C;cursor:pointer;"
                                                      class="paybtn" onclick="callPayFunction()">马上抢</span>
                                            </div>
                                        </c:if>
                                        <c:if test="${isBuy!=0}">
                                            <div class="user-submit" style="height:60px;">
                                                <span style="height:46px;line-height:40px;background:gray;color:white;">已支付</span>
                                            </div>
                                        </c:if>
                                    </c:if>
                                    <c:if test="${killInfo.timeType==0 }">
                                        <c:if test="${isBuy==0}">
                                            <div class="user-submit" style="height:60px;">
                                                <span style="height:46px;line-height:40px;background:#FEE97D;color:#E9623C;cursor:pointer;"
                                                      class="paybtn" onclick="callPayFunction()">去支付</span>
                                            </div>
                                        </c:if>
                                        <c:if test="${isBuy!=0}">
                                            <div class="user-submit" style="height:60px;">
                                                <span style="height:46px;line-height:40px;background:gray;color:white;">已支付</span>
                                            </div>
                                        </c:if>
                                    </c:if>

                                </td>
                            </tr>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
    </c:if>

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
                    <c:forEach items="${glist}" var="gl">
                        <div class="classification-content-every thisv">
                            <input class="no" type="hidden" value="${gl.gid}">
                            <input id="isMore_${gl.gid}" type="hidden" value="${gl.isMore==null?0:gl.isMore}">
                            <a>
                                <c:if test="${gl.gvimg==null||gl.gvimg.length()==0 }">
                                    <img src="img/class5.jpg"/>
                                </c:if>
                                <c:if test="${gl.gvimg!=null&&gl.gvimg.length()>0 }">
                                    <img src="${gl.gvimg}"/>
                                </c:if>
                                <div class="classification-content-text">
                                    <h6>${not empty gl.gclassify2?gl.gclassify2:(gl.gclass=='新概念'?gl.gname:gl.gclassify)} </h6>
                                        <%-- <p>${gl.gname}</p> --%>
                                    <p style="display:none;">${gl.gname}</p>
                                    <p>主讲教师:${gl.teacherName}</p>
                                </div>
                            </a>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="video-comment">
                <div class="video-every" id="subEval_div">
                    <c:if test="${empty user }">
                        <div class="user-name-one">登陆后评论</div>
                        <div class="user-info"><textarea placeholder="说说你对该视频的看法" disabled="disabled"></textarea></div>
                        <div class="user-submit" onclick="subEvaluation(0)">
                            <span class="btn btn-success">提交评论</span>
                        </div>
                    </c:if>
                    <c:if test="${! empty user }">
                        <div class="user-name-one">${user.userName}:</div>
                        <div class="user-info"><textarea placeholder="说说你对该视频的看法，最多660字" name="evaluation"
                                                         id="evaluation"></textarea></div>
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
    <%@include file="/WEB-INF/footer.jsp" %>
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
                    <div class="payment-all">
                        <div class="playment-mode">
                            <div class="playment-mode-every">
                                <h5>微信支付</h5>
                                <h4>距离支付结束还剩<span style="color:red;font-size:15px;">2小时0分0秒</span></h4>
                                <div class="playment-mode-img" style="height:280px;">
                                    <img src="" id="erweimaImg"/>
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
<script type="text/javascript" src="js/videoPlayerCommon.js"></script>
<script type="text/javascript">

    var isBuy="${isBuy}";//是否购买过
    var vvip=0;
    var islogin=0;
    var userIsVip=0;
    var endTime;
    var timeII;
    var count=7200;//2小时
    var playerInstance;
    var time;
    var isPlay=false;
    var playerlimittime = 300;

    var timmer = "${killInfo==null?0:killInfo.totalTime}";
    timmer = timmer / 1000;
    var timeType = "${killInfo==null?2:killInfo.timeType}";
    var isKill = "${generalVideo.isKill}";
    var vid = "${generalVideo.gid}";
    var vname = "${generalVideo.gname}" ;
    var teacherName = "${generalVideo.teacherName}" ;

    var videoClassify =0 ;
    var userName = "${user.userName}";
    
    $(function(){
        playerInstance = jwplayer('myvideo');
//	会员的标识 :会员为1
        var bpn = $('.bpns').val();//此变量未用到啊??
        islogin="${user==null?0:1}";//是否登录
        userIsVip=islogin==0?0:"${user.isVip}";
        endTime=islogin==0?"":"${user.endTime==null?'':user.endTime}";
        vvip = "${generalVideo.gisVip}";
        
        initPlayer(playerInstance,"${generalVideo.gvideoUrl}",false);
        
        /*god damn it*/
        setChangeTimeStatus(true);
        
        /*初始化优惠活动*/
        if (timeType != 2 && isKill == 1) {
            if (timeType == 1) {//活动进行中
                specialOffer_underway(timmer);
            } else {//活动未开始
                specialOffer_notStarted(timmer);
            }
        }
        /*初始化单元测试*/
        initUnitTests(vid, videoClassify);
        

        vcId = "${videoChild.vcid}";
        if (vcId != "") {
            initUnitTests(vcId, 2);
        }
        //异步更新下一集
        editVideoLastNew();
        
        /*绑定关闭二维码事件*/
        $("#closeErweima").click(function(){//关闭二维码时候判断是否继续播放
            clearInterval(timeII);//停止计时器
            $.ajax({
                url:"/order/checkOrderIsBuy",
                type:"post",
                data:{vid:"${generalVideo.gid}",vclassify:0,uid:"${user.uid}"},
                async:false,
                success:function(res){
                    if(res=="ok"){
                        isBuy=1;
                        playerInstance.play();
                        $(".paybtn").removeAttr("onclick");
                        $(".paybtn").html("已支付");
                        $(".paybtn").css({background:"gray",color:"white"});
                    }
                }
            });
        });
    });

    function editVideoLastNew() {
        var editTimerNum = "${editTimerNum}";
        if (editTimerNum != "" && editTimerNum > 0) {
            setTimeout(function () {
                $.post("${pageContext.request.contextPath}/general/getLastNewVideo",
                    {gid: "${generalVideo.gid}", lastVcid: "${lastVcid}"},
                    function (data) {
                        if (data) {
                            var li_size = $(".er").children("li").length;
                            var li_html = "";
                            var videoClassify = 0 ;
                            if (li_size == 0) {
                                li_html = '<li id="vli_0"  class="choose_li">' +
                                    '<a href="javascript:void(0)" style="color:black;" onclick="playVideo(' + data.vcideoUrl + ',0,' + data.vcid + ',true)">'+
                                    data.vcname +
                                    '</a>' +
                                    '</li>';
                                // playVideo(data.vcideoUrl, 0, data.vcid,  false);
                            } else {
                                li_html = '<li id="vli_"' + li_size + '>' +
                                    '<a href="javascript:void(0)"  onclick="playVideo('+data.vcideoUrl + ',' + videoClassify + ',' + data.vcid + ',true)">'+
                                    data.vcname + 
                                    '</a>' +
                                    '</li>';
                            }
                            $(".er").append(li_html);
                        }
                    });
            }, editTimerNum);
        }
    }
    var agent = "<%=request.getHeader("user-agent")%>";
    
    function hasRight(islogin){
        if(islogin == "0"){
            return false;
        }else{
            var rightContent="${user.rightContent}";
            if(rightContent.length == 0){
                return true;
            }else{
                var class_g="${generalVideo.gclass}";
                var gclassify="${generalVideo.gclassify}";
                var gclassify2="${generalVideo.gclassify2}";
                var gsbuject="${generalVideo.gsbuject}";
                /*逻辑感人:  */
                if(class_g.indexOf("古诗")>-1||class_g.indexOf("阅读")>-1||
                    class_g.indexOf("写作")>-1||class_g.indexOf("语法")>-1||
                    class_g.indexOf("流利英语")>-1){
                    class_g = "";
                }
                /*逻辑感人*/
                if(gclassify2!=""){
                    gclassify="";
                }
                if(class_g==""){
                    return rightContent.indexOf(gsbuject+gclassify+gclassify2)>-1?true:false;
                }
                var class_arr=class_g.split(",");
                for(var i in class_arr){
                    if(rightContent.indexOf(gsbuject+class_arr[i]+gclassify+gclassify2)>-1){
                        return true;
                    }
                }
                return false;
            }
        }
    }
    function callPayFunction(){
        var vid = "${generalVideo.gid}";
        var uid = "${user.uid} ";
        payErweima(vid,uid,videoClassify) ;
    }
    
    function callBackAfterRequestQRcode(data){
            var url = data.url;
            //修改二维码图片等信息
            if(url.length>0){
                var gsbuject="${generalVideo.gsbuject} ";
                var gclass="${generalVideo.gclass} ";
                var gclassify="${generalVideo.gclassify} ";
                var gname= "${generalVideo.gname} ";
                if(gclass.indexOf("古诗")>-1
                    ||gclass.indexOf("阅读")>-1
                    ||gclass.indexOf("写作")>-1
                    ||gclass.indexOf("语法")>-1
                    ||gclass.indexOf("流利英语")>-1
                    ||gclass.indexOf("其他")>-1)
                {
                    gclass="";
                }
                if(gname.length>0){
                    gname="【"+gname+"】";
                }
                $("#bodyDes").html(gsbuject+gclass+gclassify+gname);//视频名称
                $(".msk h3").html("￥"+data.omoney);//视频价格
                var url="/qr_codeImg?code_url="+url;
                $("#erweimaImg").attr("src",url);
                $('.msk').show();
                timeStart();//支付计时开始
            }else{
                alert("支付二维码生成失败，请联系客服！");
            }
       
    }
  
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
<script type="text/javascript" src="js/class.js?v=2"></script></html>
