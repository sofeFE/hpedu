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
    <link rel="stylesheet" href="/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" href="/kindeditor/plugins/code/prettify.css"/>
    <script charset="utf-8" src="/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="/kindeditor/lang/zh-CN.js"></script>
    <script charset="utf-8" src="/kindeditor/plugins/code/prettify.js"></script>
    <script>
        var editor1;
        KindEditor.ready(function (K) {

            var editor = K.editor({
                imageFileTypes:'*.jpg;*.jpeg;*.png;*.webp',
                imageSizeLimit:'5MB',
                imageUploadLimit:1,
                uploadJson: '/uploadMulti'
                , extraFileUploadParams: {relativePath: '/teacherImg/'}
            });

            $("input[id^='uploadButton_']").each(function (index, v) {
                K(v).bind("click",function(){
                    
                    editor.loadPlugin('multiimage', function () {

                        editor.plugin.multiImageDialog({
                            clickFn: function (resultMap) {//点击插入后执行的动作.返回数组
                                var div = K('#J_imageView' + (index+1));//
                                div.html('');
                                div.append('<img src="' + resultMap[0].url + '" style="width: 150px;height: 120px;">');
                                K('#url_' + (index+1)).val(resultMap[0].url);
                                editor.hideDialog();
                            }/*, afterError: function (html) {
                                alert('上传异常');
                            }*/
                        });
                    });
                })
            });
            
            
            
            
            
            
            editor1 = K.create('textarea[name="tintro"]', {
                cssPath: '${pageContext.request.contextPath}/kindeditor/plugins/code/prettify.css',
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
                    '/', 'cut', 'copy', 'paste', '|', 'plainpaste', 'wordpaste', 'selectall', 'removeformat', 'fullscreen'
                ]
            });
            prettyPrint();

        });


    </script>
</head>
<body>
<div class="panel admin-panel">
    <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改教师信息</strong></div>
    <div class="body-content">
        <form id="form" class="form-x"
        <%-- method="post"  onsubmit="return sub()"
       action="${pageContext.request.contextPath}/back/teacherUpdate" enctype="multipart/form-data"--%> >
            <div class="form-group">
                <div class="label">
                    <label>教师名称：</label>
                </div>
                <input type="hidden" name="tid" value="${teacher.tid}">
                <div class="field">
                    <input type="text" class="input w50" value="${teacher.tname}" id="tname" name="tname"
                           data-validate="required:请输入教师姓名"/>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="form-group">
                <div class="label">
                    <label>教授科目：</label>
                </div>
                <div class="field">
                    <input type="text" class="input w50" name="subject" id="subject" value="${teacher.subject}"
                           data-validate="required:请输入教授科目"/>
                    <div class="tips"></div>
                </div>
            </div>
            
            <div class="form-group">
                <div class="label">
                    <label>教师头像：</label>
                </div>
                <div class="field" >
                    <div id="J_imageView1">
                        
                        <c:if test="${ not empty teacher.timgUrl}">
                            <img  src="${teacher.timgUrl}" style="width: 150px;height: 120px;">
                        </c:if>
                        <c:if test="${empty teacher.timgUrl}">
                            <img  style="width: 150px;height: 120px;">
                        </c:if>
                    <%--
                    <button onclick="asynchronizeUpload('/teacherImg/','uploadButton_1')">上传</button>
                    <span class="callback"></span>--%>
                    </div>
                    <input type="button" id="uploadButton_1" value="选择图片">
                    <input type="hidden" id="url_1" name="timgUrl" >
                </div>
                
            </div>

            <div class="form-group">
                <div class="label">
                    <label>背景图片：</label>
                </div>
                <div class="field" >
                    <div >
                        <div id="J_imageView2">
                            <c:if test="${ not empty teacher.backImg}">
                                <img  src="${teacher.backImg}" style="width: 150px;height: 120px;">
                            </c:if>
                            <c:if test="${empty teacher.backImg}">
                                <img style="width: 150px;height: 120px;">
                            </c:if>
                        </div>
                        <input type="button" id="uploadButton_2" value="选择图片">
                        <input type="hidden" id="url_2" name="backImg" >
                    </div>
                    <%--<input type="hidden" class="fileName" name="backImg">
                    <button onclick="asynchronizeUpload('/teacherImg/','uploadButton_2')">上传</button>
                    <span class="callback"></span>--%>

                    
                </div>
                
            </div>

            
            <div class="form-group">
                <div class="label">
                    <label>教师简介：</label>
                </div>
                <div class="field">
                    <textarea class="input" name="tintro" id="tintro" cols="10" rows="15"
                              style="width:200px;height:200px;visibility:hidden;">${teacher.tintro}</textarea>
                    <div class="tips"></div>
                </div>
            </div>
            <div class="clear"></div>
            <div class="form-group">
                <div class="label">
                    <label></label>
                </div>
                <div class="field">
                    <button class="button bg-main icon-check-square-o" id="subbtn" <%--type="submit"--%>> 提交</button>
                </div>
            </div>
        </form>
        <%--<form id="fileUploadForm">
            <input type="hidden" id="showid"/>
            <input type="file" name="uploadFile" style="display: none;" id="button">

        </form>--%>

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

    $("#subbtn").click(function () {
        if (sub()) {
            var form = new FormData(document.getElementById("form"));
            $.ajax({
                url: "${pageContext.request.contextPath}/back-authc/teacherUpdate",
                type: "post",
                data: form,
                processData: false,
                contentType: false,
                dataType: "json",
                success: function (ResultBean) {//ResultBean
                    if (ResultBean.code == 0) {
                        alert("success");
                        location.href = "/back/teacher.html";
                    } else {
                        alert(ResultBean.msg);
                        sub3();
                    }
                },
                error: function () {
                    sub3();
                    alert("ajax:修改出现错误");
                }

            });
        }

    });


</script>
<script type="text/javascript">
    $("#button").change(function () {
        var showid = $("#showid").val();
        change('背景图片', 'button', showid);
        $("#showid").val("value", "");
    });
    function asynchronizeUpload(relativePath, id) {
        //以表单对象为参数,构建FormData对象
        var form = new FormData(document.getElementById('fileUploadForm'));//document.getElementById('fileUploadForm')
        form.append("relativePath", relativePath);
        $.ajax({
            url: "/upload",
            type: "post",
            data: form,
            processData: false,
            contentType: false,
            dataType: "json",
            success: function (result) {
                alert("success");
                $("#" + id).siblings(".callback").val("上传成功");
                $("#" + id).siblings(".fileName").val(result);
            },
            error: function () {
                alert("上传异常");
            }
        });
    }

</script>

</body>
</html>