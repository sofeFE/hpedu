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
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改数学测验</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" enctype="multipart/form-data" action="${pageContext.request.contextPath}/back/updateMathExam" >  
      <div class="form-group">
      <input type="hidden" value="${exam.etid}" name="etid">
      <input type="hidden" value="数学测验" name="etsubject">
        <div class="label">
          <label>课程名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${exam.etname}" name="etname"  />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>封面图片：</label>
        </div>
        <div class="field">
            <c:if test="${ not empty exam.fmImg}">
           <img id="previewfm0"    src="${exam.fmImg}" style="width: 150px;height: 120px;" >
         </c:if>
          <c:if test="${empty exam.fmImg}">
            <img id="previewfm0"   style="width: 150px;height: 120px;" >
          </c:if>
          <input type="file"  id="fm0"   class="timgUrl1" name="fm" onchange="change('测验封面','fm0','previewfm0')" >
        </div>
      </div>   
       <div class="form-group">
        <div class="label">
          <label>题图片：</label>
        </div>
       <div class="field" id="img_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加题图片" onclick="addImg('img_tab','')" >
           <c:forEach items="${exam.imgList}" var="e" varStatus="ss">
           <c:if test="${e.type==0 }">
          <table style="margin:5px;" id="img_tab${ss.index}">
		      <tr><td style="width:300px;"><img id="preview${ss.index}" alt=""  src="${e.examUrl}" style="width: 150px;height: 120px;" >
		      <%-- <input type="file"   id="file_img${ss.index}"  name="file1" onchange="change('','file_img${ss.index}','preview${ss.index}')" > --%></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg(${ss.index},'','${e.id}')"  value="删除"></td></tr>
		  </table> 
		  </c:if>
		   </c:forEach>
        </div>
      </div>  
       <div class="form-group">
        <div class="label">
          <label>题PDF文件：</label>
        </div>
        <div class="field">
        <span>${exam.pdf}</span>
        <input type="file" id="file_pdffm0"  name="fm" onchange="change('题PDF文件','file_pdffm0','',1)" >
        </div>
      </div>
        <div class="form-group">
          <div class="label">
            <label>所属年级：</label>
          </div>
          <div class="field">
            <select name="etclass"  class="w50"  style="height:36px;line-height:34px;" id="etclass" data-validate="required:请选择所属年级">
              <option value="">--请选择年级--</option>
              <option value="三年级" <c:if test="${exam.etclass=='三年级' }">selected="selected"</c:if>>三年级</option>
              <option value="四年级" <c:if test="${exam.etclass=='四年级' }">selected="selected"</c:if>>四年级</option>
              <option value="五年级" <c:if test="${exam.etclass=='五年级' }">selected="selected"</c:if>>五年级</option>
              <option value="六年级" <c:if test="${exam.etclass=='六年级' }">selected="selected"</c:if>>六年级</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
        
         <div class="form-group">
          <div class="label">
            <label>所属类别：</label>
          </div>
          <div class="field">
            <select name="etclassify"  class="w50"  style="height:36px;line-height:34px;" id="etclassify" data-validate="required:请选择所属分类">
              <option value="">--请选择类别--</option>
              <option value="上学期" <c:if test="${exam.etclassify=='上学期' }">selected="selected"</c:if>>上学期</option>
              <option value="下学期" <c:if test="${exam.etclassify=='下学期' }">selected="selected"</c:if>>下学期</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      <%-- <div class="form-group">
        <div class="label">
          <label>答案：</label>
        </div>
        <div class="field">
          <textarea class="input" name="answer" style=" height:90px;" data-validate="required:请填写答案">${exam.answer}</textarea>
          <div class="tips"></div>
        </div>
      </div> --%>
       <div class="form-group">
        <div class="label">
          <label>答案图片：</label>
        </div>
       <div class="field" id="imga_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加答案图片" onclick="addImg('imga_tab','a')" >
           <c:forEach items="${exam.imgList}" var="e" varStatus="ss">
           <c:if test="${e.type==1 }">
          <table style="margin:5px;" id="imga_tab${ss.index}">
		      <tr><td style="width:300px;"><img id="previewa${ss.index}" alt=""  src="${e.answerUrl}" style="width: 150px;height: 120px;" >
		      <%-- <input type="file"   id="filea_img${ss.index}"  name="file1a" onchange="change('','filea_img${ss.index}','previewa${ss.index}')" > --%></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg(${ss.index},'a','${e.id}')" value="删除"></td></tr>
		  </table> 
		  </c:if>
		   </c:forEach>
        </div>
      </div> 
      <div class="field" id="del_div_img">
           
      </div>
       <div class="form-group">
        <div class="label">
          <label>答案PDF文件：</label>
        </div>
        <div class="field">
        <span>${exam.answerPdf}</span>
        <input type="file" id="file_pdffm2"  name="fm" onchange="change('答案PDF文件','file_pdffm2','',1)" >
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" id="subbtn" > 提交</button>
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
var exam_index="${exam.imgList.size()}";//题
var answer_index=exam_index;//答案
function addImg(id,type){
	var index=type==""?exam_index:answer_index;
	  var html='<table style="margin:5px;" id="img'+type+'_tab'+index+'">'
	      +'<tr><td style="width:300px;"> <img id="preview'+type+index+'" alt=""   src="" style="width: 150px;height: 120px;" >'
	      +'<input type="file" id="file'+type+'_img'+index+'"  name="file1'+type+'" onchange="change(\'\',\'file'+type+'_img'+index+'\',\'preview'+type+index+'\')" ></td>'+
	      '<td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg('+index+',\''+type+'\')" value="删除" ></td></tr></table>';
    $("#"+id).append(html); 
     if(type=="") {
    	 ++exam_index;
     }else{
    	 ++answer_index;
     }
    
}
function delImg(ii,type,id){
	if(id){
		$("#del_div_img").append("<input type='hidden' name='delImhid' value='"+id+"'>");
	}
	
	  $("#img"+type+"_tab"+ii).remove();
}
function sub(){
	var flag=false
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 if($("#etclass option:selected").val().length==0||$("#etclassify option:selected").val()==0){
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