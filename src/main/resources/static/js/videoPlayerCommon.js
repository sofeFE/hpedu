



/**------------------------------------支付相关---------------------------------*/
/*二维码支付方法 ? 

*/
function payErweima(vid,uid,subject,clazz,classify,name){//
    if(islogin=="0"){
        alert("您还没有登录！");
        return;
    }
    //发送请求到服务器生成订单号和二维码
    $.ajax({
        url:"/order/getErweimaImg",
        data:{vid:vid,vclassify:0,uid:uid},
        type:"post",
        dataType:"json",
        async :true ,
        success:function(data){
            // var  data=eval("("+data+")");
            var code=data.url;
            //修改二维码图片等信息
            if(code.length>0){
                var gsbuject=subject;
                var gclass=clazz;
                var gclassify=classify;
                var gname=name;
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
                var url="/qr_codeImg?code_url="+code;
                $("#erweimaImg").attr("src",url);
                $('.msk').show();
                timeStart();//支付计时开始
            }else{
                alert("支付二维码生成失败，请联系客服！");
            }
        }
    });
}


/**------------------------------------视频播放相关---------------------------------*/
//设置播放器参数
function initPlayer(playerInstance,url,autostart){
    var playerwidth = $("#myvideo").width();
    var playerheight = $("#myvideo").height();
    //初始化视频  
    playerInstance.setup({
        //视频文件路径
        file:url,
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
        autostart:autostart//是否自动播放
    });
    //监控播放时间  
    playerInstance.onTime(function(){
        //获取当前的播放时间  
        time = playerInstance.getPosition();
        if(!isPlay){
            $.post("/changeVideoPlayTime",
                {vid:"${generalVideo.gid}",vclassify:0},
                function(){

                });
            isPlay=true;
        }
        //播放视频 判断  
        //if(vvip==1){
        if(vvip==0){//数据库中：0：是；1：否 (20170326-update)
            if(isNeedBuy()){
                if(time >= playerlimittime){
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





function readPdf(url,This){
    var  flag=false;
    if(vvip==0){
        if(isNeedBuy()){
            if(islogin=="0"){
                alert("您还没有登录！");
            }else{
                alert("您还没有购买此视频！");
            }
        }else{
            flag=true;
        }
    }else{
        flag=true;
    }
    if(flag){
        $(This).attr("href",url);
        $(This).attr("target","_blank");
    }
}
function dowbLoad(url){
    var flag=false;
    if(vvip==0){
        if(isNeedBuy()){
            if(islogin=="0"){
                alert("您还没有登录！");
            }else{
                alert("您还没有购买此视频，不能下载！");
            }
        }else{
            flag=true;
        }
    }else{
        flag=true;
    }
    if(flag){
        window.open("/download/file?url="+url);
    }
}
//是否需要购买或登录
function isNeedBuy(){
    if((userIsVip != 1 &&isBuy=="0")||
        (userIsVip ==1 && (!compareTo(endTime) || (compareTo(endTime)&&!isRight(islogin)))&&isBuy=="0")||
        islogin=="0"){
        return true;
    }
    return false;
}
function isRight(islogin){
    if(islogin=="0"){
        return false;
    }else{
        var rightContent="${user.rightContent}";
        if(rightContent.length==0){
            return true;
        }else{
            var class_g="${generalVideo.gclass}";
            var gclassify="${generalVideo.gclassify}";
            var gclassify2="${generalVideo.gclassify2}";
            var gsbuject="${generalVideo.gsbuject}";
            if(class_g.indexOf("古诗")>-1||class_g.indexOf("阅读")>-1||
                class_g.indexOf("写作")>-1||class_g.indexOf("语法")>-1||
                class_g.indexOf("流利英语")>-1){
                class_g="";
            }
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
function timeStart(){
    count=7200;
    setSpanTime(count);
    var ii=setInterval(function(){
        timeII=ii;
        if(count>0){
            count--;
            setSpanTime(count);
        }else{
            $("#closeErweima").click();
        }

    }, 1000);
}
function setSpanTime(seconds){
    $(".msk h4 span").html(getTimeVal(seconds));
}
function getTimeVal(seconds){
    var val="";
    var h=Math.floor(seconds/3600);
    var m=Math.floor(seconds%3600/60);
    var s=Math.floor(seconds%3600%60);
    h=h<0?0:h;
    m=m<0?0:m;
    s=s<0?0:s;
    val=h+"时"+m+"分"+s+"秒";
    return val;
}
//日期比大小	  
function compareTo(endTime){
    var flag=false;
    if(endTime.length>0){
        var currdate=new Date();
        //日期比较大小
        var endDate = new Date(Date.parse(endTime));
        if(endDate>=currdate){
            flag=true;
        }
    }
    return flag;
}
function playVideo(url){
    changeStuLeanTime();
    //切换视频播放
    initPlayer(playerInstance,url,true);
}
/**打开单元测试*/
function openUnitTest(){
    $("#openBtn").click();
}
//修改学生学习时间
function changeStuLeanTime(){
    if(time&&islogin=="1"){
        $.ajax({
            url:"/user/changeStuLeanTime",
            type:"post",
            
            data:{vid:"${generalVideo.gid}",vclassify:0,time:time},
            success:function(){

            }
        });
    }
}



/**------------------------------------单元测试相关---------------------------------*/
/*重做*/
function redo(){
    initUnitTests(vid,0,1);
    //$("#result_tests").hide();
    //$("#submitAnswer_btn").attr("disabled",false);
}
//初始化章节测验html
function initUnitTests(vid,type,isRedo){
    isRedo=isRedo?1:0;
    //加载章节测试题
    $.post("/back/getUnitTestsHtmlByVid",
        {vid:vid,utype:type,isRedo:isRedo,vname:"${generalVideo.gname}"},
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
/*做题打钩的方法*/
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
        an_html=prev_text+'答对'+right_num+'题，答错'+error_num+
            '题，总共得分：<font style="color:#CE0221;font-size:25px;font-weight:bold;">'+totalScore+'</font><br>';
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
    $.post(
        "/back/addUserScore",
        {vid:vid,
            utype:0,
            score:totalScore,
            rightNum:right_num,
            errorNum:error_num,
            isHasJDT:isHasJDT,
            totalNums:$("#nums_hide").val(),
            totalScores:$("#scores_hide").val(),
            teacherName:"${generalVideo.teacherName}",
            JDT_answer:JSON.stringify(JDT_answer)
        },
        function(res){});

}

/**------------------------------------评论相关---------------------------------*/
var sub_flag=true;
/*发表评论*/
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
                var vclassify=0;
                var uname="${user.userName}";
                $.ajax({
                    url:"/eval/addEvaluation",
                    data:{evaluation:evaluation,vid:vid,vclassify:vclassify,uname:uname},
                    type:"post",
                    
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


/**------------------------------------优惠活动相关---------------------------------*/
var timeII_money;
function specialOffer_notStarted(timmer){
    specialOfferTime_set("离活动开始还有:",timmer);
    if(timmer&&timmer==0){
        clearInterval(timeII_money);//停止计时器
        $.ajax({
            url:"/order/getEndKillTime",
            type:"post",
            data:{vid:"${generalVideo.gid}",vclassify:0},
            
            success:function(res){
                timmer=eval("("+res+")").totalTime/1000;
                specialOffer_underway(timmer);
                $(".paybtn").html("马上抢");
            }
        });
    }
    var ii=setInterval(function(){
        timeII_money=ii;
        if(timmer>0){
            timmer--;
            specialOfferTime_set("离活动开始还有:",timmer);
        }
    }, 1000);
}

function specialOffer_underway(timmer){
    specialOfferTime_set("还剩:",timmer);

    if(timmer&&timmer==0){
        clearInterval(timeII_money);//停止计时器
        $("#time_span").html("活动已结束");
        $(".paybtn").html("去支付");
    }
    var ii=setInterval(function(){
        timeII_money=ii;
        if(timmer>0){
            timmer--;
            specialOfferTime_set("还剩:",timmer);
        }
    }, 1000);

}
function specialOfferTime_set(content ,seconds){//"离活动开始还有:"
    $("#time_span").html( content + getTimeVal2(seconds));
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

//浏览器关闭
window.onbeforeunload = function closeWindow(e) {
    e = e || window.event;
    if (e) {
        changeStuLeanTime();
        //alert("后退");
    }
};
var agent = "<%=request.getHeader('user-agent')%>";
//浏览器后退
jQuery(document).ready(function ($) {
    if (agent.indexOf("Safari") > -1) {
        return;
    }
    if (window.history && window.history.pushState) {
        $(window).on('popstate', function () {
            var hashLocation = location.hash;
            var hashSplit = hashLocation.split("#!/");
            var hashName = hashSplit[1];

            if (hashName !== '') {
                var hash = window.location.hash;
                if (hash === '') {
                    //alert("后退");
                    changeStuLeanTime();
                    window.history.back();
                }
            }
        });
        window.history.pushState('forward', null, "");
    }
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
                        if (li_size == 0) {
                            li_html = "<li id=\"vli_0\"  class=\"choose_li\"><a href=\"javascript:void(0)\"  style=\"color:black;\" " +
                                "onclick=\"playVideo('" + data.vcideoUrl + "',0,'" + data.vcid + "','" + data.vcname + "',true)\">"
                                + data.vcname + "</a></li>";
                            playVideo(data.vcideoUrl, 0, data.vcid, data.vcname, false);
                        } else {
                            li_html = "<li id=\"vli_" + li_size + "\" ><a href=\"javascript:void(0)\"  onclick=\"playVideo('"
                                + data.vcideoUrl + "'," + li_size + ",'" + data.vcid + "','" + data.vcname + "',true)\">"
                                + data.vcname + "</a></li>";
                        }
                        $(".er").append(li_html);
                    }
                });
        }, editTimerNum);
    }
}