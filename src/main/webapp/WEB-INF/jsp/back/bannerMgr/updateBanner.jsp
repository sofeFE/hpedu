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
			 editor2 = K.create('#content', {
				cssPath : '${pageContext.request.contextPath}/plugins/kindeditor/prettify.css',
				pasteType:1,
				 afterBlur: function(){
					var self = this;
					self.sync('content');
				}, 
				items:['copy']
			});
			prettyPrint();
		});
	</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改轮播图片</strong></div>
  <div class="body-content">
    <form method="post" class="form-x"  action="${pageContext.request.contextPath}/back/updateBannerClass"  onsubmit="return sub()" enctype="multipart/form-data">  
      <div class="form-group">
        <div class="label">
          <label>图片标题：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="title" id="title" placeholder="最多20字" value="${banner.title }" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>图片简介：</label>
        </div>
        <div class="field">
          <%-- <input type="text" class="input w50" name="content" id="content" placeholder="最多50字" value="${banner.content}" /> --%>
          <textarea name="content" id="content" cols="120" rows="20" style="width:500px;height:300px;visibility:hidden;">${banner.content}</textarea>
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
          	<option value="0" <c:if test="${empty banner.belong || banner.belong==0 }"> selected="selected"</c:if> >网站</option>
          	<option value="1" <c:if test="${banner.belong==1 }"> selected="selected"</c:if> >公众号</option>
          </select>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>轮播图片：</label>
        </div>
        <div class="field">
           <img id="preview" src="${banner.imgUrl}" style="width:200px;height:70px;" >
          <input type="file" id="f" class="timgUrl1" name="file1" onchange="change('轮播图片','f','preview')" >
        </div>
      </div>  
      <div class="form-group" id="articleImg">
        <div class="label">
          <label>文章图片：</label>
        </div>
       <div class="field" id="img_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加文章图片" onclick="addImg('img_tab','')" >
           <c:forEach items="${alist}" var="e" varStatus="ss">
          <table style="margin:5px;" id="img_tab${ss.index}">
		      <tr><td style="width:300px;"><img id="preview${ss.index}" alt=""  src="${e.atUrl}" style="width: 150px;height: 120px;" ></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg(${ss.index},'${e.atid}')" value="删除"></td></tr>
		  </table> 
		   </c:forEach>
        </div>
      </div> 
      
      <!-- 公众号轮播图属性 -->
      <div class="form-group" id="category">
        <div class="label">
          <label>类别：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${banner.category }"  placeholder="例如:常规课/竞赛课" name="category"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="subject">
        <div class="label">
          <label>科目：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${banner.subject }"  placeholder="例如:数学,华杯" name="subject"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="grade">
        <div class="label">
          <label>年级：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${banner.grade }"   placeholder="例如:四年级" name="grade"  />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="classify">
        <div class="label">
          <label>类别：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${banner.classify }"   placeholder="例如:专题课/古诗" name="classify"/>
          <div class="tips"></div>
        </div>
      </div>
      
      <div class="field" id="del_div_img">
           
      </div> 
      <div class="form-group" id="articleContent">
        <div class="label">
          <label>文章内容：</label>
        </div>
        <div class="field">
          <textarea name="article" cols="120" rows="20" id="article"  style="width:800px;height:300px;visibility:hidden;">${banner.article}</textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
           <input type="hidden"  name="bid"  value="${banner.bid}" />
          <button class="button bg-main icon-check-square-o" type="submit" id="subbtn"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>
<input style="display:none;" id="error" value="${error }"/>
<script type="text/javascript">
var error=$("#error").val();
if(error.length>0){
	alert(error);
}
var index="${alist.size()}";//题
function addImg(id){
	  var html='<table style="margin:5px;" id="img_tab'+index+'">'
	      +'<tr><td style="width:300px;"> <img id="preview'+index+'" alt=""   src="" style="width: 150px;height: 120px;" >'
	      +'<input type="file" id="file_img'+index+'"  name="file2" onchange="change(\'\',\'file_img'+index+'\',\'preview'+index+'\')" ></td>'+
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
	var flag=true;
	sub2();
	var title=$("#title").val();
	if(title.length>20){
		alert("图片标题不得多于20字");
		flag = false ;
	}else{
		var content=$("#content").val();
		var belong = $("#belong").val();
		if(belong == 0 && content.length > 50 ){
			alert("网站图片简介不得多于50字");
			flag = false ;
		}else if(belong == 1 && content.length > 500 ){
			alert("图片简介中内容长度不正常,请审查是否有误或联系技术人员");
			flag = false ;
		}
		//修改的时候 并不必须 修改.
		
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


$(function(){
	var belong = $("#belong").val();
	if(belong == 0){
		$("#category").hide();
		$("#subject").hide();
		$("#grade").hide();
		$("#classify").hide();
	}else{
		$("#articleImg").hide();
		$("#articleContent").hide();
	}
});

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


</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>