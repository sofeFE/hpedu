<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8"/>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" href="css/back/pintuer.css">
    <link rel="stylesheet" href="css/back/admin.css">
    <script src="libs/jquery.js"></script>
    <script src="libs/pintuer.js"></script>
    <script src="js/upload.js"></script>
    <!-- 富文本编辑器 -->
    <link rel="stylesheet" href="plugins/kindeditor/default.css"/>
    <link rel="stylesheet" href="plugins/kindeditor/prettify.css"/>
    <script charset="utf-8" src="plugins/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="plugins/kindeditor/plugins/code/zh_CN.js"></script>
    <script charset="utf-8" src="plugins/kindeditor/plugins/code/prettify.js"></script>
    <script>
        var editor1;
        KindEditor.ready(function (K) {
            editor1 = K.create('textarea[name="tintro"]', {
                cssPath: '${pageContext.request.contextPath}/plugins/kindeditor/prettify.css',
                //pasteType:1,
                afterCreate: function () {
                    var self = this;
                    K.ctrl(document, 13, function () {
                        self.sync();
                    });
                    K.ctrl(self.edit.doc, 13, function () {
                        self.sync();
                    });
                },
                items: [
                    'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
                    'italic', 'underline', 'strikethrough', 'removeformat', 'hr', '|', '|', 'justifyleft', 'justifycenter', 'justifyright',
                    'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript', 'superscript', '|',
                    '/', 'cut', 'copy', 'paste', '|', 'plainpaste', 'wordpaste', 'selectall', 'removeformat', 'fullscreen', 'print'
                ]
            });
            prettyPrint();
        });
    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增教师信息</strong></div>
    <div class="body-content">
        <form method="post" class="form-x" onsubmit="return sub()"
              action="${pageContext.request.contextPath}/back-authc/addTeacher" enctype="multipart/form-data">
            <div class="form-group">
                <div class="label">
                    <label>教师名称：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="tname" name="tname" data-validate="required:请输入教师姓名"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>教授科目：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" id="subject" name="subject" placeholder="教授科目"
                           data-validate="required:请输入教授科目"/>
                    <div class="tips"></div>
                </div>
            </div>
            <!-- <div class="form-group">
            <div class="label">
              <label>教师称谓：</label>
            </div>
            <div class="field">
              <input type="text" class="input w50" name="title"  placeholder="教师称谓" data-validate="required:请输入教师称谓" />
              <div class="tips"></div>
            </div>
          </div> -->
            <div class="form-group">
                <div class="label">
                    <label>教师头像：</label>
                </div>
                <div class="field">
                    <img id="preview" alt="" name="pic" style="width: 150px;height: 120px;">
                    <input type="file" id="f" class="timgUrl1" name="timgUrl1" onchange="change('教师头像','f','preview')">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>背景图片：</label>
                </div>
                <div class="field">
                    <img id="preview2" alt="" name="pic" style="width: 150px;height: 120px;">
                    <input type="file" id="f2" class="timgUrl1" name="file1" onchange="change('背景图片','f2','preview2')">
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>教师简介：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="tintro" id="tintro" cols="100" rows="15"
                              style="width:800px;height:300px;visibility:hidden;"></textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" id="subbtn" type="submit"> 提交</button>
                </div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript">
    var error = "${error}";
    if (error.length > 0) {
        alert(error);
    }

    function sub() {
        var flag = false
        $("#subbtn").html("提交中...");
        $("#subbtn").attr("disabled", "disabled");
        $("#subbtn").css("background", "gray");
        if ($("#subject").val().length == 0 || $("#tname").val() == 0) {
            sub3();
        } else {
            flag = true;
        }
        return flag;
    }

    function sub3() {
        $("#subbtn").html("提交");
        $("#subbtn").removeAttr("disabled");
        $("#subbtn").css("background", "#08bbe1");
    }

    /* function change() {
        var pic = document.getElementById("preview"),
            file = document.getElementById("f");

        var ext=file.value.substring(file.value.lastIndexOf(".")+1).toLowerCase();

         // gif在IE浏览器暂时无法显示
         if(ext!='png'&&ext!='jpg'&&ext!='jpeg'){
             alert("图片的格式必须为png或者jpg或者jpeg格式！");
             return;
         }
         var isIE = navigator.userAgent.match(/MSIE/)!= null,
             isIE6 = navigator.userAgent.match(/MSIE 6.0/)!= null;

         if(isIE) {
            file.select();
            var reallocalpath = document.selection.createRange().text;

            // IE6浏览器设置img的src为本地路径可以直接显示图片
             if (isIE6) {
                pic.src = reallocalpath;
             }else {
                // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
                 pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
                 // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
                 pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
             }
         }else {
            html5Reader(file);
         }
    }

     function html5Reader(file){
         var file = file.files[0];
         var reader = new FileReader();
         reader.readAsDataURL(file);
         reader.onload = function(e){
             var pic = document.getElementById("preview");
             pic.src=this.result;
         }
     } */
</script>
</body>
<script type="text/javascript" src="js/class.js?v=2"></script></html>