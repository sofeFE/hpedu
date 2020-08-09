<%@ page language="java" contentType="text/html;charset=utf-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>在线客服</title>
<style type="text/css">
.message-box2 {
 border-radius:10px;
/* border:1px solid #C9E9C0; */

}
.triangle-left {
    width: 0;
    height: 0;
    border-top: 10px solid transparent;
    border-right: 10px solid #F7F7F7;
    border-bottom: 10px solid transparent;
}

.triangle-right {
    width: 0;
    height: 0;
    border-top: 10px solid transparent;
    border-left: 10px solid #E9FBE4;
    border-bottom: 10px solid transparent;
}
</style>
</head>
<body style="padding:0;">
<div style="width:100%;height:550px;margin:0;">
<div style="width:100%;height:40px;line-height:40px;font-family:verdana;background:#46BE8A;color:#fff;">
&nbsp;&nbsp;厚朴教育
</div>
<div style="width:100%;text-align:center;color:#999999;float:left;height:30px;line-height:30px;font-size:14px;" id="showTip">正在连接客服，请稍等...</div>

<!-- 内容显示框 -->
<div style="width:100%;height:380px;font-size:14px;overflow-y:auto;">
<!-- 回答 -->
<div  style="float:left;width:5%;margin:10px 0 10px 0; border-radius:50px;background:#F7F7F7;text-align:center;">
我
</div>
<div style="float:left;margin:10px 0 10px 0;width:95%;" >
<div  style="float:left;padding-top:10px;">
<div class="triangle-left"></div>
</div>
<div style="float:left;background:#F7F7F7;padding:10px;max-width:327px;word-break:break-all;" class="message-box2">
厚朴教育报名时间是什么时候？
</div>
</div>

<!-- 提问 -->
<div  style="float:right;width:10%;margin:10px 0 10px 0; border-radius:50px;background:#E9FBE4;text-align:center;">
客服
</div>
<div style="float:right;margin:10px 0 10px 0;width:90%;" >
<div  style="float:right;padding-top:10px;">
<div class="triangle-right"></div>
</div>
<div style="float:right;background:#E9FBE4;padding:10px;max-width:327px;word-break:break-all;" class="message-box2">
每年一月10号
</div>
</div>

</div>
<!-- 提问框 -->
<div style="width:100%;height:100px;border-top:1px solid #999999;">
<textarea style="width:90%;margin-left:5%;border:1px solid #999999;height:80px;">

</textarea>
<input type="button" value="提交" style="width:60px;height:30px;line-height:30px;
text-align:center;background:#46BE8A;color:#fff;border-radius:5px;float:right;">
</div>
</div>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>