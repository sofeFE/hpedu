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
<div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>【${vname}】测验题新增</strong></div>
     <div class="body-content">
     <div  id="show_more" style="display:none;">
        <table class="table table-hover text-center" id="tests_table">
      <tr>
        <th>序号</th>
        <th>题目</th>
        <th>题目图片</th>
        <th>题目类型</th>
        <th>是否多选</th>
        <th>答案</th>
        <th>选项</th>
        <th>分数</th>
        <th width="20%">操作</th>
      </tr>
      <c:forEach items="${utlist}" var="p" varStatus="pp">
      <tr id="tr_${p.utid}">
	      <td>${pp.index+1}</td>
          <c:if test="${not empty p.ucontent && p.ucontent.length()>30}">
	      <td>${p.ucontent.substring(0,30)}...</td>
	      </c:if>
           <c:if test="${empty p.ucontent || p.ucontent.length()<=30}">
	      <td>${p.ucontent}</td>
	      </c:if>
          <td><c:if test="${not empty p.utimg}"><img src="${p.utimg}" style="width:40px;height:40px;"></c:if></td>
          <td>${p.testType==null||p.testType==0?'选择题':'简答题'}</td>
          <td>${p.testType!=1?(p.isMoreChoose==1?'是':'否'):''}</td>
	      <td>${p.answer}</td>
	      <td style="text-align:left;color:#00CC99;">
	       <c:forEach items="${ucData.get(p.utid)}" var="cs">
	        ${cs.tanswer}.${cs.tcontent}<br>
	       </c:forEach>
	       </td>
	      <td>${p.score}</td>
	      <td>
	          <div class="button-group"> 
		          <a class="button border-main border-up" onclick="toEdit('${p.utid}')" ><span class="icon-edit"></span> 修改</a>
		          <a class="button border-red"  onclick="del('${p.utid}')" ><span class="icon-trash-o"></span> 删除</a> 
	          </div>
	      </td>
      </tr>
      </c:forEach>
    </table>
     
     </div>
     
    <form method="post" class="form-x" id="myform">  
      <input type="hidden" name="vid" id="vid" value="${vid}">
      <input type="hidden" name="vname" id="vname" value="${vname}">
      <input type="hidden" name="type" id="type" value="${type}">
      <input type="hidden" name="typeName" id="typeName" value="${typeName}">
      <input type="hidden" name="isMore" id="isMore" value="${isMore}"> 
      <input type="hidden" name="index" id="index" value="${utlist.size()+1}"> 
      <div class="form-group">
        <div class="label">
          <label>查看已有题目：</label>
        </div>
        <div class="field">
         <input type="button" class="button bg-main icon-check-square-o" id="slid_btn" value="展开" onclick="slideDown_div(this)" >
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>题目：</label>
        </div>
        <div class="field">
          <textarea class="input" name="ucontent" id="ucontent" placeholder="例如：请选择对创新能力有益的选项？" style="width:300px;height:100px;" ></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>题目图片：</label>
        </div>
        <div class="field">
        <img id="preview" alt="" name="pic"   style="width: 150px;height: 120px;" >
         <input type="file"  id="f"  class="timgUrl1" name="file1" onchange="change('题目图片','f','preview')" >
         <input type="hidden" id="utimg" name="utimg">
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>题目类型：</label>
        </div>
        <div class="field">
           <input type="radio" name="testType" value="0" checked="checked" onclick="changeTestType(0)" >选择题
           <input type="radio" name="testType" value="1" onclick="changeTestType(1)" >简答题
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>考点：</label>
        </div>
        <div class="field">
           <textarea class="input" name="ponit" id="ponit" placeholder="例如：四则运算巧算" style="width:300px;height:100px;" ></textarea>
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>详解：</label>
        </div>
        <div class="field">
         <!--  <textarea class="input" name="detail" id="detail" placeholder="例如：原式=10000+1000+100+5" style="width:300px;height:100px;" ></textarea> -->
          <textarea class="input" name="detail" id="detail"  placeholder="例如：原式=10000+1000+100+5" style="width:300px;height:200px;visibility:hidden;" ></textarea>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group" id="choose1">
        <div class="label">
          <label>是否多选：</label>
        </div>
        <div class="field">
           <select  class="w50" style="height:36px;line-height:34px;width:130px;" name="isMoreChoose" >
              <option value="0">否</option>
              <option value="1">是</option>
            </select>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group"  id="choose2">
        <div class="label">
          <label>正确答案：</label>
        </div>
        <div class="field">
         <!--  <input type="text" class="input w50"  name="answer" id="answer" style="width:300px;" placeholder="例如：A"  /> -->
           <input type="checkbox" name="answer" value="A" >A
           <input type="checkbox" name="answer" value="B" >B
           <input type="checkbox" name="answer" value="C" >C
           <input type="checkbox" name="answer" value="D" >D
           <input type="checkbox" name="answer" value="E" >E
           <input type="checkbox" name="answer" value="F" >F
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
		      <tr id="cs_tr_0"><td>
		      <select  class="w50"  style="height:40px;line-height:38px;margin-top:2px;width:60px;" name="tanswer" >
              <option value="A">A.</option>
              <option value="B">B.</option>
              <option value="C">C.</option>
              <option value="D">D.</option>
              <option value="E">E.</option>
              <option value="F">F.</option>
             </select>
		      <input type="text" class="input w50"  style="width:234px;margin:2px;"  name="tcontent" placeholder="选项内容，例如：灵活多变"   />
		      <!-- <input type="text" class="input w50"  style="width:150px;"  name="tanswer" placeholder="【选项值】例如：A"   /> -->
		      <input type="button" class="button bg-main icon-check-square-o" value="删除" onclick="del_choose('cs_tr_0')" >
		      </td>
		      </tr>
		  </table> 
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>分数值：</label>
        </div>
        <div class="field">
          <input type="number" class="input w50"  name="score" id="score"  style="width:300px;" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
         <input type="button" class="button bg-main icon-check-square-o" value="提交"  id="subbtn" onclick="sub()" >
           <input type="button" class="button bg-main icon-check-square-o" value="返回" onclick="window.history.back()" >
           <div class="tips"></div>
        </div>
      </div>
     
    </form>
  </div>
    </div>
   
   
<script type="text/javascript">
function changeTestType(ii){
	if(ii==0){
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
	//是否展开列表
	var isopen=$.cookie("isopen");
	if(isopen!=null&&isopen==1){
		 $("#show_more").slideDown();
		  $("#slid_btn").val("隐藏");
		  $.cookie("isopen",null);
	}
});
var choose_index=1;
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
function del_choose(id){
  $("#"+id).remove();	
}

function slideDown_div(This){
	  if($("#show_more").is(":hidden")){
		  $("#show_more").slideDown();
		  $(This).val("隐藏");
	  }else{
		  $("#show_more").slideUp();
		  $(This).val("展开");
	  }
}

function sub(){
	var flag=false;
	 $("#subbtn").val("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 var  ucontent=$("#ucontent").val();
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
		url:"${pageContext.request.contextPath}/back/addUnitTestClass",
		type:"post",
		data:$("#myform").serialize(),
		async:false,
		success:function(res){
			res=eval("("+res+")");
			sub2();
			if(res.status=="ok"){
				alert("测验题新增成功！");
				//清空表单数据
				$("#ucontent").val("");
				$("input[name='answer']").removeAttr("checked");//取消全选   
				$("#score").val("");
			 	$("input[name='tcontent']").each(function(){
					 $(this).val("");
				});
			 	$("#index").val(parseInt($("#index").val())+1);
				
				$("#utimg").val("");
				$("#f").val("");
				$("#preview").removeAttr("src");
				$("#ponit").val("");
				$("#detail").val("");
				  editor2.html('');
				//更新已有测验题列表
				$("#tests_table").append(res.data);
			}else{
				alert(res.data);
			}
		}
	});
}
function sub2(){
	 $("#subbtn").val("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}
function del(utid){
	 $.post("${pageContext.request.contextPath}/back/delUnitTestClass",{utid:utid},function(res){
	    	if(res=="ok"){
	    		$("#tr_"+utid).remove();
	    	}else{
	    		alert(res);
	    	}
	    });
}
function toEdit(utid){
	window.open("${pageContext.request.contextPath}/back/backGeneral/updateUnitTest.html?utid="+utid,'_self');
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
	           //alert(data.status);
	        	if(data.status=="ok"){
	        		$("#utimg").val(data.vcid);
	        	}else{
	        		alert("图片上传失败:"+data.status);
	        	}
	        	
	        }
	    });  
		
	}

</script>
</body></html>