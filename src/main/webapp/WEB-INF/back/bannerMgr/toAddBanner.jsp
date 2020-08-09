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
var editor1;
var editor2;
		KindEditor.ready(function(K) {
			 editor1 = K.create('textarea[name="article"]', {
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
				afterBlur: function(){this.sync();},
				items:[
				         'undo', 'redo', '|', 'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					        'italic', 'underline', 'strikethrough', 'removeformat', 'hr', '|', '|', 'justifyleft', 'justifycenter', 'justifyright',
				        'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript','superscript', '|',
				        '/', 'cut', 'copy', 'paste', '|','plainpaste', 'wordpaste', 'selectall' ,'removeformat','fullscreen','print'
				]
			});
			 editor1 = K.create('#content', {
					cssPath : '${pageContext.request.contextPath}/plugins/kindeditor/prettify.css',
					pasteType:1,
					afterBlur: function(){this.sync();},
					items:['copy']
				});
			prettyPrint();
		});
	</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增轮播图片</strong></div>
  <div class="body-content">
    <form method="post" class="form-x"  action="${pageContext.request.contextPath}/back/addBannerClass" onsubmit="return sub()" enctype="multipart/form-data">  
      <div class="form-group">
        <div class="label">
          <label>图片标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" required="required" name="title" id="title" placeholder="最多20字"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>图片简介：</label>
        </div>
        <div class="field">
         <!--  <input type="text" class="input w50" placeholder="最多50字" name="content" id="content" /> -->
          <textarea name="content" id="content" cols="120" rows="20" style="width:300px;height:100px;visibility:hidden;"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group ">
        <div class="label">
          <label>所属项目：</label>
        </div>
        <div class="field ">
          <!-- <input type="text" class="input w50" name="belong" /> -->
          <select name="belong" id="belong" class="input w50" onchange="changeBelong()">
          	<option value="0" selected="selected">网站</option>
          	<option value="1">公众号</option>
          </select>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>轮播图片：</label>
        </div>
        <div class="field">
        <img id="preview" alt="" name="pic"   src="" style="width:200px;height:70px;" >
          <input type="file"  id="f" data-validate="required:请上传轮播图片"   class="timgUrl1" name="file1" onchange="change('轮播图片','f','preview')" >
        </div>
      </div> 
      
      <!-- 网站轮播图 属性 -->
      <div class="form-group" id="img">
        <div class="label">
          <label>文章图片：</label>
        </div>
       <div class="field" id="img_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加文章图片" onclick="addImg('img_tab')" >
          <table style="margin:5px;" id="img_tab0">
		      <tr><td style="width:300px;"> <img id="preview0" alt=""    style="width: 150px;height: 120px;" >
		      <input type="file" id="file_img0"  name="file2" onchange="change('','file_img0','preview0')" ></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" style="visibility: hidden;" value="删除" ></td></tr>
		  </table> 
        </div>
      </div>  
      <div class="form-group" id="articleContent">
        <div class="label">
          <label>文章内容：</label>
        </div>
        <div class="field">
          <textarea name="article" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;"></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      <!-- 公众号轮播图属性 -->
      <div class="form-group" id="category">
        <div class="label">
          <label>类别：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" placeholder="例如:常规课/竞赛课" name="category"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="subject">
        <div class="label">
          <label>科目：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" placeholder="例如:数学,华杯" name="subject"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="grade">
        <div class="label">
          <label>年级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" placeholder="例如:四年级" name="grade"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="classify">
        <div class="label">
          <label>类别：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  placeholder="例如:专题课/古诗" name="classify"/>
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
function sub(){
	var flag=false;
	var title=$("#title").val();
	sub2();
	if(title.length>20){
		alert("图片标题不得多于20字");
	}else{
		var content=$("#content").val();
		var belong = $("#belong").val();
		if(belong == 0 && content.length > 50 ){
			alert("网站图片简介不得多于50字");
		}else if(belong == 1 && content.length > 500){
			alert("设置的外部链接过长,请审查是否有误或联系技术人员");
		}else if($("#f").val().length>0){//轮播图 是否上传.
			flag=true;
		}
	}
	if(!flag){
		sub3();
	}
	return flag;
}
function sub2(){
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
}
function sub3(){
	 $("#subbtn").html("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}
var index=1;//题
function addImg(id){
	  var html='<table style="margin:5px;" id="img_tab'+index+'">'
	      +'<tr><td style="width:300px;"> <img id="preview'+index+'" alt=""   src="" style="width: 150px;height: 120px;" >'
	      +'<input type="file" id="file_img'+index+'"  name="file2" onchange="change(\'\',\'file_img'+index+'\',\'preview'+index+'\')" ></td>'+
	      '<td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg('+index+')" value="删除" ></td></tr></table>';
    $("#"+id).append(html); 
     ++index;
}

function delImg(ii){
	$("#img_tab"+ii).remove();
}

function changeBelong(){
	var ii = $("#belong").val();
	if(ii==0){//网站
		$("#img").show();
		$("#articleContent").show();
		
		$("#category").hide();
		$("#subject").hide();
		$("#grade").hide();
		$("#classify").hide();
	}else{//公众号
		$("#img").hide();
		$("#articleContent").hide();
		
		$("#category").show();
		$("#subject").show();
		$("#grade").show();
		$("#classify").show();
	}	 
}

$(function(){
	$("#category").hide();
	$("#subject").hide();
	$("#grade").hide();
	$("#classify").hide();
	
})
 
 
</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>