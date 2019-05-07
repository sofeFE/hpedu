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
<link rel="stylesheet" href="css/kindeditor/default.css" />
<link rel="stylesheet" href="css/kindeditor/prettify.css" />
<script charset="utf-8" src="js/plugins/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="js/plugins/kindeditor/zh_CN.js"></script>
<script charset="utf-8" src="js/plugins/kindeditor/prettify.js"></script>

<script>
var editor1,editor2,editor3;
		KindEditor.ready(function(K) {
			/*  editor1 = K.create('textarea[name="heartContent"]', {
				cssPath : '${pageContext.request.contextPath}/jsp/css/kindeditor/prettify.css',
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
			 editor2 = K.create('textarea[name="info"]', {
					cssPath : '${pageContext.request.contextPath}/jsp/css/kindeditor/prettify.css',
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
					cssPath : '${pageContext.request.contextPath}/jsp/css/kindeditor/prettify.css',
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
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增学员信息</strong></div>
  <div class="body-content">
    <form  id="formID" class="form-x"  >  
      <div class="form-group">
        <div class="label">
          <label>学员名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" id="pname" name="pname" data-validate="required:请输入学员名称" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>学员简介：</label>
        </div>
        <div class="field">
          <textarea class="input" name="common" style="width:400px;height:100px;" ></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>学员头像图片：</label>
        </div>
        <div class="field">
        <img id="preview" alt="" name="pic"  style="width: 150px;height: 120px;" >
          <input type="file"  id="f" data-validate="required:请上传学员头像"   class="timgUrl1" name="pimgUrl1" onchange="change('学员头像','f','preview')" >
        </div>
      </div> 
      
       <div class="form-group">
        <div class="label">
          <label>学员风采：</label>
        </div>
       <div class="field" id="img_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加学员风采图片" onclick="addImg('img_tab')" >
          <table style="margin:5px;" id="img_tab0">
		      <tr><td style="width:300px;"> <img id="preview0" alt=""    style="width: 150px;height: 120px;" >
		      <input type="file" id="file_img0"  name="file1" onchange="change('','file_img0','preview0')" ></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" style="visibility: hidden;" value="删除" ></td></tr>
		  </table> 
        </div>
      </div> 
      <div class="form-group">
        <div class="label">
          <label>心得：</label>
        </div>
        <div class="field">
           <!-- <textarea class="input" name="heartContent" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;"></textarea> -->
             <textarea class="input" name="heartContent" style="width:400px;height:100px;" ></textarea>
          <div class="tips"></div>
        </div>
      </div> 
       
      <div class="form-group">
        <div class="label">
          <label>标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="title" />
          <div class="tips"></div>
        </div>
      </div>  
      <div class="form-group">
        <div class="label">
          <label>学员档案：</label>
        </div>
        <div class="field">
          <textarea class="input" name="info"  cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;" ></textarea>
          <div class="tips"></div>
        </div>
      </div> 
       <div class="form-group">
        <div class="label">
          <label>故事标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="pintrotitle" />
          <div class="tips"></div>
        </div>
      </div>  
      <div class="form-group">
        <div class="label">
          <label>成长故事：</label>
        </div>
        <div class="field">
         <!--  <textarea class="input" name="pintro" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;" ></textarea> -->
           <textarea class="input" name="pintro" cols="100" rows="15" style="width:800px;height:300px;" ></textarea>
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
var error="${error}";
if(error.length>0){
	alert(error);
}
var index=1;//题
function addImg(id){
	  var html='<table style="margin:5px;" id="img_tab'+index+'">'
	      +'<tr><td style="width:300px;"> <img id="preview'+index+'" alt=""   src="" style="width: 150px;height: 120px;" >'
	      +'<input type="file" id="file_img'+index+'"  name="file1" onchange="change(\'\',\'file_img'+index+'\',\'preview'+index+'\')" ></td>'+
	      '<td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg('+index+')" value="删除" ></td></tr></table>';
    $("#"+id).append(html); 
     ++index;
    
}
function delImg(ii){
	$("#img_tab"+ii).remove();
}
function sub(){
	var flag=false;
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 if($("#pname").val().length==0||$("#f").val().length==0){
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
$("#formID").onsubmit(function(){
    if(sub()){
      $.ajax({
          url:'/back-authc/addTrophy' ,
          type:'post',
          data:new FormData( $("#formID") ) ,
          processData: false,
          contentType: false,
          dataType:'json',
          success: function(result){
              if(result.code == 0){
                  location.href="/back/trophy.html" ;
              }
          },
          complete : function(){
              sub3() ;
          }
      });
    }
    
})


</script>
</body></html>