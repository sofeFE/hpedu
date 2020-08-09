<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="${pageContext.request.contextPath}/">
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/back/pintuer.css">
<link rel="stylesheet" href="css/back/admin.css">
<script src="libs/jquery.js"></script>
<script src="libs/pintuer.js"></script>
<script src="js/upload.js"></script>

<!-- 富文本编辑器 -->
 <link rel="stylesheet" href="plugins/kindeditor/default.css" />
<link rel="stylesheet" href="plugins/kindeditor/prettify.css" />
<script charset="utf-8" src="plugins/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="plugins/kindeditor/plugins/code/zh_CN.js"></script>
<script charset="utf-8" src="plugins/kindeditor/plugins/code/prettify.js"></script>
<script>
var editor1,editor2,editor3;
		KindEditor.ready(function(K) {
			 editor2 = K.create('textarea[name="info"]', {
					cssPath : '${pageContext.request.contextPath}/plugins/kindeditor/prettify.css',
					pasteType:1,
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
						});
					},
					items:[
					         'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						        'italic', 'underline', 'strikethrough', 'removeformat', 'hr', '|', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|',
					        '/', 'cut', 'copy', 'paste', '|','plainpaste', 'wordpaste', 'selectall' ,'removeformat','fullscreen','print'
					]
					
				});
			 /* editor3 = K.create('textarea[name="pintro"]', {
					cssPath : '${pageContext.request.contextPath}/plugins/kindeditor/prettify.css',
					pasteType:1,
					afterCreate : function() {
						var self = this;
						K.ctrl(document, 13, function() {
							self.sync();
						});
						K.ctrl(self.edit.doc, 13, function() {
							self.sync();
						});
					},
					items:[
					         'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
						        'italic', 'underline', 'strikethrough', 'removeformat', 'hr', '|', '|', 'justifyleft', 'justifycenter', 'justifyright',
					        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|',
					        '/', 'cut', 'copy', 'paste', '|','plainpaste', 'wordpaste', 'selectall' ,'removeformat','fullscreen','print'
					]
					
				}); */
			prettyPrint();
		});
</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改学员信息</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" action="${pageContext.request.contextPath}back-authc/updatTrophy" enctype="multipart/form-data">  
      <div class="form-group">
        <div class="label">
          <label>学员名称：</label>
        </div>
       <input type="hidden" name="pid" value="${trophy.pid}">
        <div class="field">
          <input type="text" class="input w50" id="pname" name="pname" value="${trophy.pname}" data-validate="required:请输入学员名称" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>学员简介：</label>
        </div>
        <div class="field">
          <textarea class="input" name="common" style="width:400px;height:100px;" >${trophy.common}</textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>学员头像：</label>
        </div>
        <div class="field">
          <c:if test="${ not empty trophy.pimgUrl}">
          <img id="preview" alt="" name="pic"   src="${trophy.pimgUrl}" style="width: 150px;height: 120px;" >
        </c:if>
         <c:if test="${  empty trophy.pimgUrl}">
         <img id="preview" alt="" name="pic"   style="width: 150px;height: 120px;" >
         </c:if>
          <input type="file"  id="f"    class="timgUrl1" name="pimgUrl1" onchange="change('学员头像','f','preview')" >
        </div>
      </div>   
      <div class="form-group">
        <div class="label">
          <label>学员风采：</label>
        </div>
       <div class="field" id="img_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加学员风采图片" onclick="addImg('img_tab','')" >
           <c:forEach items="${trophy.stuList}" var="e" varStatus="ss">
          <table style="margin:5px;" id="img_tab${ss.index}">
		      <tr><td style="width:300px;"><img id="preview${ss.index}" alt=""  src="${e.stUrl}" style="width: 150px;height: 120px;" >
		      <%-- <input type="file"   id="file_img${ss.index}"  name="file1" onchange="change('','file_img${ss.index}','preview${ss.index}')" > --%></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg(${ss.index},'${e.stid}')" value="删除"></td></tr>
		  </table> 
		   </c:forEach>
        </div>
      </div> 
      <div class="field" id="del_div_img">
           
      </div> 
       <div class="form-group">
        <div class="label">
          <label>心得：</label>
        </div>
        <div class="field">
         <%--  <textarea class="input" name="heartContent" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;" >${trophy.heartContent}</textarea> --%>
           <textarea class="input" name="heartContent" style="width:400px;height:100px;">${trophy.heartContent}</textarea>
          <div class="tips"></div>
        </div>
      </div>  
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${trophy.title}" name="title" />
          <div class="tips"></div>
        </div>
      </div>  
      <div class="form-group">
        <div class="label">
          <label>学员档案：</label>
        </div>
        <div class="field">
          <textarea class="input" name="info" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;" >${trophy.info}</textarea>
          <div class="tips"></div>
        </div>
      </div> 
       <div class="form-group">
        <div class="label">
          <label>故事标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="pintrotitle" value="${trophy.pintrotitle}" />
          <div class="tips"></div>
        </div>
      </div>   
      <div class="form-group">
        <div class="label">
          <label>成长故事：</label>
        </div>
        <div class="field">
        <%--   <textarea class="input" name="pintro"  cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;" >${trophy.pintro}</textarea> --%>
            <textarea class="input" name="pintro"  cols="100" rows="15" style="width:800px;height:300px;" >${trophy.pintro}</textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" id="subbtn"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<script type="text/javascript">
var error="${error}";
if(error.length>0){
	alert(error);
}
var index="${trophy.stuList.size()}";//题
function addImg(id){
	  var html='<table style="margin:5px;" id="img_tab'+index+'">'
	      +'<tr><td style="width:300px;"> <img id="preview'+index+'" alt=""   src="" style="width: 150px;height: 120px;" >'
	      +'<input type="file" id="file_img'+index+'"  name="file1" onchange="change(\'\',\'file_img'+index+'\',\'preview'+index+'\')" ></td>'+
	      '<td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg('+index+')" value="删除" ></td></tr></table>';
    $("#"+id).append(html); 
    ++index;
    
}
function delImg(ii,id){
	if(id){
		$("#del_div_img").append("<input type='hidden' name='delImhid' value='"+id+"'>");
	}
	$("#img_tab"+ii).remove();
}
function sub(){
	var flag=false;
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 if($("#pname").val().length==0){
		 sub3();
	 }else{
		 flag=true;
	 }
   return flag;
}
function sub3(){
	 $("#subbtn").html("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
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
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>