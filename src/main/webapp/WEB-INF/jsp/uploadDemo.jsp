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
        var uploadbutton ;
        KindEditor.ready(function (K) {
            var editor = K.editor({
                // allowImageUpload: false,
                uploadJson: '/uploadDemo'
                //, imgPath:'/teacherImg/'
                , extraFileUploadParams: {relativePath: '/teacherImg/'}
            });
            K('#image3').click(function() {
                editor.loadPlugin('image', function () {

                    editor.plugin.imageDialog({
                        imageUrl: K('#url3').val(),
                        showRemote: false,
                        // showLocal: false,
                        clickFn: function (resultMap) {//点击后执行的动作吧.
                            var div = K('#J_imageView');
                            div.html('');
                            div.append('<img src="' + resultMap.url + '">');
                            K('#url3').val(url);
                            // editor.hideDialog();
                        },
                        uploadbutton: K.uploadbutton({
                            // button: K('#image3'),
                            // fieldName: 'imgFile',
                            // url: '/uploadDemo',
                            afterUpload: function (data) {
                                editor.hideLoading();
                                if (data.error === 0) {
                                    // K.formatUrl(data.url, 'absolute');
                                    var url = data.url;
                                    K('#url3').val(url);
                                } else {
                                    alert(data.message);
                                }
                            },
                            afterError: function (str) {
                                alert('上传异常: ' + str);
                            }
                        })

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
