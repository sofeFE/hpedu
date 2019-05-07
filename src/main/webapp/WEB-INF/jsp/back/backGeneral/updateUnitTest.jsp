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
<script src="libs/jquery.cookie.js"></script>
<script src="js/upload.js"></script>
<!-- 富文本编辑器 -->
 <link rel="stylesheet" href="css/kindeditor/default.css" />
<link rel="stylesheet" href="css/kindeditor/prettify.css" />
<script charset="utf-8" src="js/plugins/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="js/plugins/kindeditor/zh_CN.js"></script>
<script charset="utf-8" src="js/plugins/kindeditor/prettify.js"></script>
<script type="text/javascript">
var editor2;
KindEditor.ready(function(K) {
	 editor2 = K.create('textarea[name="detail"]', {
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
			afterBlur: function(){this.sync();},
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
<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>测验题修改</strong></div>
     <div class="body-content">
    <form method="post" class="form-x" id="myform">
    <c:forEach items="${utlist}" var="p">
      <div class="form-group">
        <div class="label">
          <label>题目：</label>
        </div>
        <div class="field">
          <textarea class="input" name="ucontent" id="ucontent" placeholder="例如：请选择对创新能力有益的选项？" style="width:300px;height:100px;" >${p.ucontent}</textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>题目图片：</label>
        </div>
        <div class="field">
        <c:if test="${ not empty p.utimg}">
        <img id="preview" alt="" name="pic"   src="${p.utimg}" style="width: 150px;height: 120px;" >
        </c:if>
         <c:if test="${empty p.utimg}">
          <img id="preview" alt="" name="pic"    style="width: 150px;height: 120px;" >
         </c:if>
          <input type="file"  id="f"   class="timgUrl1" name="file1" onchange="change('题目图片','f','preview')">
          <input type="hidden" id="utimg" name="utimg" value="${p.utimg}">
        </div>
      </div>  
       <div class="form-group">
        <div class="label">
          <label>题目类型：</label>
        </div>
        <div class="field">
           <input type="radio" name="testType" value="0" <c:if test="${p.testType==null||p.testType==0}">checked="checked"</c:if> onclick="changeTestType(0)" >选择题
           <input type="radio" name="testType" value="1" <c:if test="${p.testType==1}">checked="checked"</c:if> onclick="changeTestType(1)" >简答题
          <div class="tips"></div>
        </div>
      </div>
             <div class="form-group">
        <div class="label">
          <label>考点：</label>
        </div>
        <div class="field">
           <textarea class="input" name="ponit" id="ponit" placeholder="例如：四则运算巧算" style="width:300px;height:100px;" >${p.ponit}</textarea>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>详解：</label>
        </div>
        <div class="field">
         <%--  <textarea class="input" name="detail" id="detail" placeholder="例如：原式=10000+1000+100+5" style="width:300px;height:100px;" >${p.detail}</textarea> --%>
           <textarea class="input" name="detail" id="detail"  placeholder="例如：原式=10000+1000+100+5" style="width:300px;height:200px;visibility:hidden;" >${p.detail}</textarea>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group" id="choose1">
        <div class="label">
          <label>是否多选：</label>
        </div>
        <div class="field">
           <select   class="w50"  style="height:36px;line-height:34px;width:130px;"  name="isMoreChoose" >
              <option value="0" <c:if test="${p.isMoreChoose==0}">selected="selected"</c:if>>否</option>
              <option value="1" <c:if test="${p.isMoreChoose==1 }">selected="selected"</c:if>>是</option>
            </select>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="choose2">
        <div class="label">
          <label>正确答案：</label>
        </div>
        <div class="field">
          <%-- <input type="text" class="input w50" value="${p.answer}"  name="answer" id="answer" style="width:300px;" placeholder="例如：A"  /> --%>
           <input type="checkbox" name="answer" value="A"  <c:if test="${p.answer.indexOf('A')>-1 }">checked="checked"</c:if>>A
           <input type="checkbox" name="answer" value="B" <c:if test="${p.answer.indexOf('B')>-1 }">checked="checked"</c:if>>B
           <input type="checkbox" name="answer" value="C" <c:if test="${p.answer.indexOf('C')>-1 }">checked="checked"</c:if>>C
           <input type="checkbox" name="answer" value="D" <c:if test="${p.answer.indexOf('D')>-1 }">checked="checked"</c:if>>D
           <input type="checkbox" name="answer" value="E" <c:if test="${p.answer.indexOf('E')>-1 }">checked="checked"</c:if>>E
           <input type="checkbox" name="answer" value="F" <c:if test="${p.answer.indexOf('F')>-1 }">checked="checked"</c:if>>F
            
          <div class="tips"></div>
        </div>
      </div>

      <div class="form-group" id="choose3">
        <div class="label">
          <label>选项：</label>
        </div>
        <div class="field">
          
          <input type="button" class="button bg-main icon-check-square-o" value="添加选项" onclick="add_choose('choose0')" >
          <table style="margin:5px;" id="choose0">
          <!-- 原有测验题 -->
            <c:forEach items="${ucData.get(p.utid)}" var="cs" varStatus="st">
            <tr id="cs_tr_${st.index}" style="padding:2px;"><td>
            <select  class="w50"  style="height:40px;line-height:38px;width:60px;margin-top:2px;" onchange="edit('${cs.csid}')" id="sa_${cs.csid}" >
              <option value="A" <c:if test="${cs.tanswer=='A' }">selected="selected"</c:if>>A.</option>
              <option value="B" <c:if test="${cs.tanswer=='B' }">selected="selected"</c:if>>B.</option>
              <option value="C" <c:if test="${cs.tanswer=='C' }">selected="selected"</c:if>>C.</option>
              <option value="D" <c:if test="${cs.tanswer=='D' }">selected="selected"</c:if>>D.</option>
              <option value="E" <c:if test="${cs.tanswer=='E' }">selected="selected"</c:if>>E.</option>
              <option value="F" <c:if test="${cs.tanswer=='F' }">selected="selected"</c:if>>F.</option>
            </select>
		      <input type="text" class="input w50"  style="width:234px;margin:2px;"  value="${cs.tcontent}" onblur="edit('${cs.csid}')" id="sc_${cs.csid}"  placeholder="选项内容，例如：灵活多变"   />
		      <%-- <input type="text" class="input w50"  style="width:150px;"    value="${cs.tanswer}" onblur="edit('${cs.csid}')" id="sa_${cs.csid}" placeholder="【选项值】例如：A"   /> --%>
		       <input type="button" class="button bg-main icon-check-square-o" value="删除" onclick="del_choose('cs_tr_${st.index}','${cs.csid}')" >
		      </td>
		      </tr>
            </c:forEach>
		  </table> 

        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>分数值：</label>
        </div>
        <div class="field">
          <input type="number" class="input w50"  value="${p.score}" name="score" id="score"  style="width:300px;" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
         <input type="hidden" name="utid" value="${p.utid}">
         <input type="button" class="button bg-main icon-check-square-o" value="提交"  id="subbtn" onclick="sub()" >
         <span id="tip_sp" style="color:green;"></span>
           <div class="tips"></div>
        </div>
      </div>
     </c:forEach>
     <div id="del_div" style="display:none;">
     
     </div>
     <div id="edit_div" style="display:none;">
     
     </div>
    </form>
  </div>
    </div>
   
   
<script type="text/javascript">
var choose_index="${ucData.get(utlist.get(0).utid)==null?0:ucData.get(utlist.get(0).utid).size()}";
//新增选项
function add_choose(tableid){
	var html='<tr id="cs_tr_'+choose_index+'" ><td>'
	+'<select  class="w50"  style="height:40px;line-height:38px;margin-top:2px;width:60px;" name="tanswer" ><option value="A">A.</option><option value="B">B.</option>'
    +'<option value="C">C.</option><option value="D">D.</option><option value="E">E.</option><option value="F">F.</option></select>'
	+'<input type="text" class="input w50"  style="width:234px;margin:2px;"  name="tcontent" placeholder="选项内容，例如：灵活多变" />'
	//+'<input type="text" class="input w50"  style="width:150px;"  name="tanswer" placeholder="【选项值】例如：A"   />'
	+'<input type="button" class="button bg-main icon-check-square-o" value="删除" onclick="del_choose(\'cs_tr_'+choose_index+'\')" ></td></tr>';
    $("#"+tableid).append(html);
    ++choose_index;
}
//删除选项
function del_choose(id,csid){
  $("#"+id).remove();	
  if(csid){//选项id
	  var delHtml='<input type="hidden" name="delId" value="'+csid+'" >';
	  $("#del_div").append(delHtml);
  }
}
var edit_arr=[];
//编辑选项
function edit(csid){
	var val_c=$("#sc_"+csid).val();
	var val_a=$("#sa_"+csid).val();
	 if($.inArray(csid,edit_arr)<0){
		 var editHtml='<input type="hidden" name="editId"  value="'+csid+'" ><input type="hidden" id="c_'+csid+'" name="editContent"  value="'+val_c+'" ><input type="hidden" id="a_'+csid+'" name="editAnswer"  value="'+val_a+'" >';
		  $("#edit_div").append(editHtml);
		  edit_arr.push(csid);
	 }else{
		 $("#c_"+csid).val(val_c);
		 $("#a_"+csid).val(val_a);
	 }
}
function sub(){
	var flag=false;
	 $("#subbtn").val("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 var  ucontent=$("#ucontent").val();
	// var  answer=$("#answer").val();
	 var len=$("input[type='checkbox']:checked").length;
	 var  score=$("#score").val();
     var testType=$("input[name='testType']:checked").val();
	 if(ucontent.length>0&&((testType==0&&len>0)||testType==1)&&score.length>0){
		 flag=true;
	 }else{
		 if(ucontent.length==0){
			 alert("题目不能为空！");
		 }else if(len==0&&testType==0){
			 alert("正确答案至少选择一个！");
		 }else{
			 alert("分数不能为空！");
		 }
	 }
	/* if(flag){
		$("input[name='tcontent']").each(function(){
			if($(this).val().length==0){
				flag=false;
				 alert("测验题的选项内容均不能为空！");
				 return false;
			}
		});
		if(flag){
			$("input[name='tanswer']").each(function(){
				if($(this).val().length==0){
					 flag=false;
					 alert("测验题的选项值均不能为空！");
					 return false;
				}
			});
		}
	}  */
	 if(!flag){
		 sub2();
	 }else{
		 //提交表单
		 submit();
	 }
	
}
function submit(){
	try{
		updateCustomerInfo();
		}catch (e) {
			alert("异步上传图片失败："+e);
		}
	$.ajax({
		url:"${pageContext.request.contextPath}/back/updateUnitTestClass",
		type:"post",
		data:$("#myform").serialize(),
		async:false,
		success:function(res){
			sub2();
			if(res=="ok"){
				$("#tip_sp").html("测验题修改成功！");
				$.cookie("isopen",1);
				$.cookie("vcId_select","${vcId==null?0:vcId}");
				self.location=document.referrer;//返回上一页并刷新
			}else{
				alert(res);
			}
		}
	});
}
function sub2(){
	 $("#subbtn").val("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}
//异步上传文件
function updateCustomerInfo(){
	if($("#f").val()==""){
		return;
	}
	var formdata = new FormData();
    var fileObj = document.getElementById("f").files;
    for (var i = 0; i < fileObj.length; i++) {
        formdata.append("file", fileObj[i]);
    }
    formdata.append("type",1);
    //上传文件
    $.ajax( {
        type: 'POST',
        url:"${pageContext.request.contextPath}/file/upload",
        contentType: false,
        processData: false,
        async:false,
        data: formdata,
        dataType: 'JSON',
        success: function (data) {//上传文件成功,则修改文件状态值
        	if(data.status=="ok"){
        		$("#utimg").val(data.vcid);
        	}else{
        		alert("图片上传失败:"+data.status);
        	}
        	
        }
    });  
	
}
function changeTestType(ii){
	if(ii==0||ii==null){
		$("#choose1").show();
		$("#choose2").show();
		$("#choose3").show();
	}else{
		$("#choose1").hide();
		$("#choose2").hide();
		$("#choose3").hide();
	}
}
$(function(){
	var valsss=$("input[name='testType']:checked").val();
	changeTestType(valsss);
});
</script>
</body></html>