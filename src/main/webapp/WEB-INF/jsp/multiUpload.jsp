<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    request.setCharacterEncoding("UTF-8");
    String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>KindEditor JSP</title>
    <link rel="stylesheet" href="/kindeditor/themes/default/default.css"/>
    <link rel="stylesheet" href="/kindeditor/plugins/code/prettify.css"/>
    <script charset="utf-8" src="/kindeditor/kindeditor-all.js"></script>
    <script charset="utf-8" src="/kindeditor/lang/zh-CN.js"></script>
    <script charset="utf-8" src="/kindeditor/plugins/code/prettify.js"></script>
    <script>
        
        /* 注释了kindeditor-all.js  5674 行.*/
        var uploadbutton ;
        KindEditor.ready(function (K) {
            var editor = K.editor({
                imageFileTypes:'*.jpg;*.jpeg;*.png;*.webp',
                imageSizeLimit:'5MB',
                imageUploadLimit:1,
                uploadJson: '/uploadMulti'
                // ,upload_url:'/uploadMulti'
                
                // allowImageUpload: false,
                //, imgPath:'/teacherImg/'
                , extraFileUploadParams: {relativePath: '/teacherImg/'}
            });
            
            K('#image3').click(function() {
                editor.loadPlugin('multiimage', function () {

                    editor.plugin.multiImageDialog({
                        
                        // imageUrl: K('#url3').val(),
                        // showRemote: false,
                        // showLocal: false,
                        clickFn: function (resultMap) {//点击插入后执行的动作.返回数组
                            var div = K('#J_imageView');
                            div.html('');
                            div.append('<img src="' + resultMap[0].url + '">');
                            K('#url3').val(resultMap[0].url);
                            editor.hideDialog();
                        }, afterError: function (html) {
                            alert('上传异常');
                        }
                        

                    });


                });
            });
        });

        /*--------------------------------------------
         

              
        */
    </script>
</head>
<body>
<%=htmlData%>
<div>
    <input type="text" id="url3" value=""/>
    <input type="button" id="image3" value="选择图片"/>（本地上传）
    <div id="J_imageView"></div>

</div>
</body>
<script type="text/javascript" >

    /*
    KindEditor.ready(function (K) {
        $("input[id^='uploadButton_']").each(function (i, v) {
            var obj = this;
            var index = i;
            var uploadbutton = K.uploadbutton({
                button: obj,
                fieldName: 'imgFile',
                url: '/upload',
                afterUpload: function (data) {
                    if (data.error === 0) {
                        var url = K.formatUrl(data.url, 'absolute');
                        K('#img_url_' + index).val(url);
                    } else {
                        alert(data.message);
                    }
                },
                afterError: function (str) {
                    alert('上传异常: ' + str);
                }
            });
            uploadbutton.fileBox.change(function (e) {
                uploadbutton.submit();
            });
        });
    });*/
</script>
</html>
<%!
    private String htmlspecialchars(String str) {
        str = str.replaceAll("&", "&amp;");
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        str = str.replaceAll("\"", "&quot;");
        return str;
    }
%>


<%--

--%>
