<%@ page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<base href="${pageContext.request.contextPath}/">
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
<script  type="text/javascript" src="plugins/My97DatePicker/WdatePicker.js"></script>

<!-- 富文本编辑器 -->
<link rel="stylesheet" href="plugins/kindeditor/default.css" />
<link rel="stylesheet" href="plugins/kindeditor/prettify.css" />
<script charset="utf-8" src="plugins/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="plugins/kindeditor/plugins/code/zh_CN.js"></script>
<script charset="utf-8" src="plugins/kindeditor/plugins/code/prettify.js"></script>
<script>
var editor1;
		KindEditor.ready(function(K) {
			 editor1 = K.create('textarea[name="gintro"]', {
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
			prettyPrint();
		});
	</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加常规语文课程</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" enctype="multipart/form-data" action="${pageContext.request.contextPath}/back/addChineseGeneralVideo" >  
      <div class="form-group">
        <div class="label">
          <label>课程名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="gname"/>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>视频：</label>
        </div>
       <div class="firminfo-video">
            <div class="firminfo-video-input">
                <video class="video-jhhh" height="215" controls="controls" src=""></video>
                 <span class="video-file" style="color: red; width: 20px;" >点我上传视频</span>
                <input type="file" class="videoUp"  id="videoUp" value="" style="display: none" data-validate="required:请上传视频" name="file1">
            </div>
        </div>
      </div>   
      <div class="form-group">
        <div class="label">
          <label>封面：</label>
        </div>
        <div class="field">
        <img id="preview" alt="" name="pic"   src="" style="width: 150px;height: 120px;" >
          <input type="file"  id="f" data-validate="required:请上传封面图"   name="file1" onchange="change('封面图片','f','preview')" >
        </div>
      </div>
     
      <div class="form-group">
        <div class="label">
          <label>PDF：</label>
        </div>
       <div class="field" id="pdf_tab">
          <input type="button" class="button bg-main " value="添加讲义" onclick="addPdf('pdf_tab')" >
          <table style="margin:5px;" id="pdf_tab0">
		      <tr><td style="width:300px;"><input type="file" id="file_pdf0"  name="file1" onchange="change('','file_pdf0','',1)" ></td><td><input type="button" class="button bg-main " style="visibility: hidden;" value="删除" ></td></tr>
		  </table> 
        </div>
      </div>    
        <div class="form-group">
          <div class="label">
            <label>所属年级：</label>
          </div>
          <div class="field">
            
                   <input type="checkbox" name="gclass" value="三年级" >三年级
	               <input type="checkbox" name="gclass" value="四年级" >四年级
	               <input type="checkbox" name="gclass" value="五年级" >五年级
	               <input type="checkbox" name="gclass" value="小升初" >小升初
	               <input type="checkbox" name="gclass" value="初一年级" >初一年级
	               <input type="checkbox" name="gclass" value="古诗" >古诗
	               <input type="checkbox" name="gclass" value="阅读" >阅读
	               <input type="checkbox" name="gclass" value="写作" >写作
            <div class="tips"></div>
          </div>
        </div>
         <div class="form-group">
          <div class="label">
            <label>所属类别：</label>
          </div>
          <div class="field">
            <select name="gclassify" class="w50" style="height:36px;line-height:34px;" >
              <option value="">--请选择类别--</option>
              <option value="长期班">长期班</option>
              <option value="真题分析">真题分析</option>
              <option value="品古诗">品古诗</option>
              <option value="阅读专题">阅读专题</option>
              <option value="写作专题">写作专题</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
         <div class="form-group">
          <div class="label">
            <label>主讲教师：</label>
          </div>
          <div class="field">
            <select name="teacherName" class="w50" style="height:36px;line-height:34px;">
              <option value="">--请选择主讲教师--</option>
              <c:forEach items="${tclist}" var="t">
              <option value="${t.tname}">${t.tname}</option>
             </c:forEach>
            </select>
            <div class="tips"></div>
          </div>
        </div>
          <div class="form-group">
          <div class="label">
            <label>是否为VIP视频：</label>
          </div>
          <div class="field">
            <select name="gisVip" class="w50" style="height:36px;line-height:34px;" >
              <option value="1">--请选择是否为VIP视频--</option>
              <option value="0">是</option>
              <option value="1">否</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
          <div class="form-group">
        <div class="label">
          <label>专柜价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="gmoney" placeholder="如果免费则不需要填写该项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="killMoney" placeholder="如果免费则不需要填写该项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀活动名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50"  name="killName" placeholder="如：双十一特惠活动" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀时间起：</label>
        </div>
        <div class="field">
         <input class="Wdate input w50" style="height:40px;width:240px;"  name="killStartTime" placeholder="例如：2017-05-08 11:50:20" onclick="WdatePicker()" type="text" >
         <!--  <input type="text" class="input w50"  name="killStartTime" placeholder="例如：2017-05-08 11:50:20" /> -->
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀时间止：</label>
        </div>
        <div class="field">
        <input class="Wdate input w50" style="height:40px;width:240px;"  name="killEndTime" placeholder="例如：2017-05-08 11:50:20" onclick="WdatePicker()" type="text" >
         <!--  <input type="text" class="input w50"  name="killEndTime" placeholder="例如：2017-05-08 11:50:20" /> -->
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
          <div class="label">
            <label>是否执行秒杀：</label>
          </div>
          <div class="field">
            <select name="isKill" class="w50" style="height:36px;line-height:34px;">
              <option value="">--请选择是否执行秒杀活动--</option>
              <option value="1">是</option>
              <option value="0">否</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      <div class="form-group">
        <div class="label">
          <label>描述：</label>
        </div>
        <div class="field">
          <textarea class="input" name="gintro" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;"></textarea>
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
$(function(){
	var error="${error}";
	if(error.length>0){
		alert(error);
	}
	//上传视频
	$('.video-file').click(function() {
		$('.firminfo-video-input').find("input").click();
		$('.firminfo-video-input').find("input").on("change",function(){
			var objUrl = getObjectURL(this.files[0]) ;  //获取图片的路径，该路径不是图片在本地的路径
		       if (objUrl) {
		    	   $('.firminfo-video-input').find("video").attr("src", objUrl) ;      //将图片路径存入src中，显示出图片
		         if ($('.firminfo-video-input').find("video").attr("src") !== "") {
			     	$('.firminfo-video-input').find("video").show();
			     }else{
			     	$(".video-file").show();
			     }
		       }
		});
	});
	//建立一個可存取到該filerl
	function getObjectURL(file) {
	  var url = null ;
	  if (window.URL.createObjectURL!="") { // basic
	    url = window.URL.createObjectURL(file) ;
	  } else if (window.URL!="") { // mozilla(firefox)
	    url = window.URL.createObjectURL(file) ;
	  } else if (window.webkitURL!="") { // webkit or chrome
	    url = window.webkitURL.createObjectURL(file) ;
	  }
	  return url ;
	};
});
//pdf文件
var pdf_index=1;
function addPdf(id){
	  var html='<table style="margin:5px;" id="pdf_tab'+pdf_index+'">'+
	      '<tr><td style="width:300px;"><input type="file" id="file_pdf'+pdf_index+'"  name="file1" onchange="change(\'\',\'file_pdf'+pdf_index+'\',\'\',1)" ></td>'+
	      '<td><input type="button" class="button bg-main " onclick="delPDF('+pdf_index+')" value="删除" ></td></tr></table>';
    $("#"+id).append(html);  
     ++pdf_index;
}
function delPDF(ii){
	  $("#pdf_tab"+ii).remove();
}
function sub(){
	var flag=false;
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 if($("#videoUp").val().length==0||$("#f").val().length==0){
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
</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>