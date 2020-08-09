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

<script type="text/javascript">
var editor2;
KindEditor.ready(function(K) {
	 editor2 = K.create('textarea[name="testDetail"]', {
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
	prettyPrint();
});
</script>

</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>新增测试知识点</strong></div>
  <div class="body-content">
    <form method="post" id="myform" class="form-x" onsubmit="return sub()"  >  
    	
      <div class="form-group">
        <div class="label">
          <label>年级：</label>
        </div>
        <div class="field">
            <select id="grade" class="w50" style="height:36px;line-height:34px;" 
            onchange="getTestPoint()">
              <option value="">--请选择年级--</option>
            </select>
            <div class="tips"></div>
        </div>
      </div>
      
      <div class="form-group">
        <div class="label">
          <label>知识点名字：</label>
        </div>
        <div class="field">
            <select name="testPointId" id="testPointId" class="w50" style="height:36px;line-height:34px;" >
              <option value="">--请选择知识点--</option>
            </select>
            <div class="tips"></div>
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
          <label>题目：</label>
        </div>
        <div class="field">
          <textarea class="input" name="testTitle" id="testTitle" placeholder="例如：请选择对创新能力有益的选项？" style="width:300px;height:100px;" ></textarea>
          <div class="tips"></div>
        </div>
      </div>
      
     
      <div class="form-group" id="choose1">
        <div class="label">
          <label>是否多选：</label>
        </div>
        <div class="field">
           <select  class="w50" style="height:36px;line-height:34px;width:130px;" name="isMoreChoose" id="isMoreChoose" >
              <option value="0">否</option>
              <option value="1">是</option>
            </select>
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
		      <tr id="cs_tr_0">
			      <td>
			      	<select  class="w50"  style="height:40px;line-height:38px;margin-top:2px;width:60px;" name="option" >
		              <option value="A">A.</option>
		              <option value="B">B.</option>
		              <option value="C">C.</option>
		              <option value="D">D.</option>
		              <option value="E">E.</option>
		              <option value="F">F.</option>
	             	</select>
				      <input type="text" class="input w50"  style="width:234px;margin:2px;"  name="optionContent" placeholder="选项内容，例如：灵活多变"   />
				      <input type="button" class="button bg-main icon-check-square-o" value="删除" onclick="del_choose('cs_tr_0')" >
			      </td>
		      </tr>
		      
		  </table> 
        </div>
      </div>
      
      <div class="form-group"  id="choose2">
        <div class="label">
          <label>正确答案：</label>
        </div>
        <div class="field">
           <input type="checkbox" name="testAnswer" value="A" >A
           <input type="checkbox" name="testAnswer" value="B" >B
           <input type="checkbox" name="testAnswer" value="C" >C
           <input type="checkbox" name="testAnswer" value="D" >D
           <input type="checkbox" name="testAnswer" value="E" >E
           <input type="checkbox" name="testAnswer" value="F" >F
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group"  id="choose4" >
        <div class="label">
          <label>正确答案：</label>
        </div>
        <div class="field">
           <input type="text" name="testAnswer" value="" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>详解：</label>
        </div>
        <div class="field">
          <textarea class="input" name="testDetail" id="detail"  placeholder="例如：原式=10000+1000+100+5" style="width:300px;height:200px;visibility:hidden;" ></textarea>
          <div class="tips"></div>
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
	if(ii==0){//选择题
		$("#choose1").show();
		$("#choose2").show();
		$("#choose3").show();
		$("#choose4").hide();
	}else{//填空题
		$("#choose1").hide();
		$("#choose2").hide();
		$("#choose3").hide();
		$("#choose4").show();
	}
}

$(function(){
	$("#choose4").hide();//
	
	$.ajax({
		url:"${pageContext.request.contextPath}/getAllGrade",
		type:"post",
		dataType:"json",
		success:function(result){
			var options = "" ;
			for(var i =0 ;i < result.length ; i++){
				options += "<option value= '"+result[i]+"'  >"+result[i]+"</option>"
			}
			$("#grade").append(options);
		}
	});
	
});

function getTestPoint(){
	var grade = $("#grade").val();
	if(grade!=""){
		$.ajax({
			url:"${pageContext.request.contextPath}/getTestPointByGrade",
			type:"post",
			data:{"grade":grade},
			dataType:"json",
			success:function(result){
				var options = "" ;
				for(var i =0 ;i < result.length ; i++){
					options += "<option value= '"+result[i].tpid+"'  >"+result[i].pointName+"</option>"
				}
				$("#testPointId").html(options);
			}
		});
	}else{
		$("#testPointId").html("<option value=''>请选择年级</option>");
	}
	
}


var choose_index=1;
//新增选项
function add_choose(tableid){
	if( $("select[name='option']").length <= 5){
		var html='<tr id="cs_tr_'+choose_index+'" ><td>'
			+'<select  class="w50"  style="height:40px;line-height:38px;margin-top:2px;width:60px;" name="option" >'
				+'<option value="A">A.</option>'
				+'<option value="B">B.</option>'
			    +'<option value="C">C.</option>'
			    +'<option value="D">D.</option>'
			    +'<option value="E">E.</option>'
			    +'<option value="F">F.</option>'
			+'</select>'
			+'<input type="text" class="input w50"  style="width:234px;margin:2px;"  name="optionContent" placeholder="选项内容，例如：灵活多变" />'
			+'<input type="button" class="button bg-main icon-check-square-o" value="删除" onclick="del_choose(\'cs_tr_'+choose_index+'\')" >'
		+'</td></tr>';
		
	    $("#"+tableid).append(html);
	    ++choose_index;
	}
}
//删除选项
function del_choose(id){
  $("#"+id).remove();	
}


//提交校验
function sub(){
	var flag=false;
	//防止重复提交
	 $("#subbtn").val("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 
	 var  testTitle=$("#testTitle").val();
	 var len=$("input[type='checkbox']:checked").length;
	 var  score=$("#score").val();
	 var testType=$("input[name='testType']:checked").val();
	 var optionContent = $("#testPointId").val();
	 var isMoreChoose = $("#isMoreChoose").val();
	 if(testTitle.length>0 
			 && ( (testType==0 &&( (isMoreChoose==1 && len > 1 ) || (isMoreChoose==0 && len == 1)  ) ) || testType==1 ) 
			 && score.length>0 
			 && optionContent!=""){
		 flag=true;
	 }else{
		 if(testTitle.length==0){
			 alert("题目不能为空！");
		 } else if(optionContent==""){
			 alert("未选择知识点");
		 } 
		 else if(!( (testType==0 &&( (isMoreChoose==1 && len > 1 ) || (isMoreChoose==0 && len == 1)  ) ) || testType==1 )){
			 alert("正确答案个数设置不正确");
		 } 
		 else{
			 alert("分数不能为空！");
		 }
	 }
	 if(!flag){
		 sub2();
	 }else{
		 //提交表单
		 submit();
	 }
}

//ajax 提交 --需要更换.
function submit(){
	$.ajax({
		url:"${pageContext.request.contextPath}/addTest",
		type:"post",
		data:$("#myform").serialize(),
		async:false,
		success:function(res){
			res=eval("("+res+")");
			sub2();
			if(res.status=="ok"){
				alert("测验题新增成功！");
				window.location.href = "${pageContext.request.contextPath}/back/test/controlTest" ;//添加成功后 跳转.
			}else{
				alert(res.data);
			}
		}
	});
}

//恢复 可提交模式
function sub2(){
	 $("#subbtn").val("提交");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}

</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>