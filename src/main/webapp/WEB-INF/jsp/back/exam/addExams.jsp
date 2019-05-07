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
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增${exam.etsubject}</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" action="${pageContext.request.contextPath}/back/updateExams" enctype="multipart/form-data">  
      <div class="form-group">
        <div class="label">
          <label>课程名称：</label>
        </div>
        <div class="field">
         ${exam.etname}
          <input type="hidden" name="etid" value="${exam.etid}">
           <input type="hidden" name="etsubject" value="${exam.etsubject}" >
          <input type="hidden" name="etimg" value="1">
          <div class="tips"></div>
        </div>
      </div>
        <div class="form-group">
        <div class="label">
          <label>题图片：</label>
        </div>
       <div class="field" id="img_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加题图片" onclick="addImg('img_tab','')" >
           <c:forEach items="${imgList}" var="e" varStatus="ss">
           <c:if test="${e.type==0 }">
          <table style="margin:5px;" id="img_tab${ss.index}">
		      <tr><td style="width:300px;"><img id="preview${ss.index}" alt=""  src="${e.examUrl}" style="width: 150px;height: 120px;" >
		      <%-- <input type="file"   id="file_img${ss.index}"  name="file1" onchange="change('','file_img${ss.index}','preview${ss.index}')" > --%></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" onclick="delImg(${ss.index},'','${e.id}')" value="删除" ></td></tr>
		  </table> 
		  </c:if>
		   </c:forEach>
        </div>
      </div> 
      <div class="field" id="del_div_img">
           
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
          <label>答案图片：</label>
        </div>
       <div class="field" id="imga_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加答案图片" onclick="addImg('imga_tab','a')" >
           <c:forEach items="${imgList}" var="e" varStatus="ss">
           <c:if test="${e.type==1 }">
          <table style="margin:5px;" id="imga_tab${ss.index}">
		      <tr><td style="width:300px;"><img id="previewa${ss.index}" alt=""  src="${e.answerUrl}" style="width: 150px;height: 120px;" >
		     <%--  <input type="file"   id="filea_img${ss.index}"  name="file1a" onchange="change('','filea_img${ss.index}','previewa${ss.index}')" > --%></td>
		      <td><input type="button" class="button bg-main icon-check-square-o"  onclick="delImg(${ss.index},'a','${e.id}')" value="删除" ></td></tr>
		  </table> 
		  </c:if>
		   </c:forEach>
        </div>
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
          <button class="button bg-main icon-check-square-o" id="subbtn"  > 提交</button>
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
var exam_index="${imgList.size()}";//题
if(exam_index==""){
	exam_index=0;
}
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
</script>
</body></html>