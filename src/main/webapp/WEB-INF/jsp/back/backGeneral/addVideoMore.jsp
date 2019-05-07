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
<link rel="stylesheet" href="css/kindeditor/default.css" />
<link rel="stylesheet" href="css/kindeditor/prettify.css" />
<script charset="utf-8" src="js/plugins/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="js/plugins/kindeditor/zh_CN.js"></script>
<script charset="utf-8" src="js/plugins/kindeditor/prettify.js"></script>
<script>
var editor1;
		KindEditor.ready(function(K) {
			 editor1 = K.create('textarea[name="gintro"]', {
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
			prettyPrint();
		});
	</script>
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加${subject}常规课程专题</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" enctype="multipart/form-data" action="${pageContext.request.contextPath}/back/addGeneralMore" >  
      <div class="form-group">
        <div class="label">
          <label>课程名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="" name="gname"  />
          <div class="tips"></div>
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
          <input type="button" class="button bg-main icon-check-square-o" value="添加讲义" onclick="addPdf('pdf_tab')" >
          <table style="margin:5px;" id="pdf_tab0">
		      <tr><td style="width:300px;"><input type="file" id="file_pdf0"  name="file1" onchange="change('','file_pdf0','',1)" ></td><td><input type="button" class="button bg-main icon-check-square-o" style="visibility: hidden;" value="删除" ></td></tr>
		  </table> 
        </div>
      </div> 
       <div class="form-group">
        <div class="label">
          <label>专题视频：</label>
        </div>
       <div class="field" id="video_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加新视频" onclick="addVideo('video_tab')" >
         <table style="border:1px solid #22CC77;margin:5px;" id="video_tab0">
		      <tr>
			      <td colspan="3">
			        <div class="firminfo-video">
	                     <div class="firminfo-video-input0">
			                <video class="video-jhhh"  style="height:150px;margin:0px;" controls="controls" src=""></video>
			                 <span class="video-file0" style="color: red; width: 20px;" onclick="video(0)">点我选择视频</span>
			                 <input type="file" class="videoUp" id="videoUp0" style="display: none" >
			                <input type="hidden" name="files2" id="hide_file0">
			             </div>
			               <input type="button" class="button bg-main icon-check-square-o" value="上传"  onclick="updateCustomerInfo(this,0)" >
			         </div>       
			      </td>
		      </tr>
		      <tr>
			      <td>视频名称：</td>
			      <td style="width:200px;"><input type="text" style="width:200px;height:40px;margin:5px;"  name="vcname" required="required" placeholder="请输入视频集数，例如：第一集" ></td>
			      <td><input type="button" class="button bg-main icon-check-square-o" style="visibility: hidden;" value="删除" ></td>
		      </tr>
		      <tr>
			      <td>视频排序：</td>
			      <td style="width:200px;"><input type="number" style="width:200px;height:40px;margin:5px;"  name="sort" required="required" placeholder="请输入视频序号" ></td>
		      </tr>
		      <tr>
		      	<td>视频图片：</td>
               	<td style="width:300px;">
               	<img src="" id="vimg" style="height:150px;margin:0px;" alt=""  >
                <input type="file" id="vimgfile" name="imgFile"  onchange="change('视频图片','vimgfile','vimg')"  >' 
               </td>
              </tr>
         </table> 
        </div>
      </div> 
      <script type="text/javascript">
      //视频
      var video_index=1;
      function addVideo(id){
          
          var html='<table style="border:1px solid #22CC77;margin:5px;" id="video_tab'+video_index+'">'
          +'<tr><td colspan="3">'
           +'<div class="firminfo-video">'
            +'<div class="firminfo-video-input'+video_index+'">'
             +'<video class="video-jhhh"  style="height:150px;margin:0px;" controls="controls" src=""></video>'
             +' <span class="video-file'+video_index+'" style="color: red; width: 20px;"  onclick="video('+video_index+')" >点我选择视频</span>'
             +'<input type="file" class="videoUp"  id="videoUp'+video_index+'" style="display: none" >'
             +'<input type="hidden" name="files2" id="hide_file'+video_index+'">'
            +'</div>'
             +'<input type="button" class="button bg-main icon-check-square-o" value="上传" onclick="updateCustomerInfo(this,'+video_index+')" >'
        	+'</div>'
         	+'</td>'
          +'</tr>'
          
          +'<tr><td>视频名称：</td>'
           +'<td style="width:200px;">'
           +'<input type="text" style="width:200px;height:40px;margin:5px;" name="vcname"  required="required" placeholder="请输入视频集数，例如：第一集" >'
           +'</td>'
           
           +'<td><input type="button" value="删除" class="button bg-main icon-check-square-o" onclick="delVideo('+video_index+')"></td>'
          +'</tr>'
          
          +'<tr><td>视频排序：</td>'
           +'<td><input type="number" style="width:200px;height:40px;margin:5px;" name="sort"   required="required" placeholder="请输入视频顺序:必须是整数字" ></td>'
          +'</tr>'
          
          
          +'<tr><td>视频图片：</td>'
          +'<td style="width:300px;">'
          +'<img src="" id="vimg'+video_index+'" style="height:150px;margin:0px;" alt=""  >'
           +'<input type="file" id="vimgfile'+video_index+'" name="imgFile"  onchange="change(\'视频图片\',\'vimgfile'+video_index+'\',\'vimg'+video_index+'\')"  >' 
          +'</td>'
         +'</tr>'
          
         +'</table>';
          
           $("#"+id).append(html); 
	       ++video_index;
      }
      function delVideo(ii){
    	  $("#video_tab"+ii).remove();
      }
      //pdf文件
      var pdf_index=1;
      function addPdf(id){
    	  var html='<table style="margin:5px;" id="pdf_tab'+pdf_index+'">'+
		      '<tr><td style="width:300px;"><input type="file" id="file_pdf'+pdf_index+'"  name="file1" onchange="change(\'\',\'file_pdf'+pdf_index+'\',\'\',1)" ></td>'+
		      '<td><input type="button" class="button bg-main icon-check-square-o" onclick="delPDF('+pdf_index+')" value="删除" ></td></tr></table>';
          $("#"+id).append(html);  
	       ++pdf_index;
      }
      function delPDF(ii){
    	  $("#pdf_tab"+ii).remove();
      }
    //修改文件id
  	function updateCustomerInfo(This,ii){
  		if($("#videoUp"+ii).val()==""){
  			alert("请选择上传的文件！");
  			return false;
  		}
  		$(This).val("上传中...");
  		$(This).attr("disabled","disabled");
  		$(This).css("background","gray");
  		var formdata = new FormData();
  	    var fileObj = document.getElementById("videoUp"+ii).files;
  	    for (var i = 0; i < fileObj.length; i++) {
  	        formdata.append("file", fileObj[i]);
  	    }
  	    formdata.append("vctype",0);
  	    //上传文件
  	    $.ajax( {
  	        type: 'POST',
  	        url:"${pageContext.request.contextPath}/file/upload",
  	        contentType: false,
  	        processData: false,
  	        data: formdata,
  	        dataType: 'JSON',
  	        success: function (data) {//上传文件成功,则修改文件状态值
  	        	if(data.status=="ok"){
  	        		$("#hide_file"+ii).val(data.vcid);//
  	        		$(This).val("已完成");
  	        	}else{
  	        		alert("视频上传失败:"+data.status);
  	        		$(This).val("上传");
  	        	}
  	        	$(This).removeAttr("disabled");
	        	$(This).css("background","#08bbe1");
  	        }
  	    });  
  		
  	}
      
      </script>          
        <div class="form-group">
          <div class="label">
            <c:if test="${subject!='英语'}">
              <label>所属年级：</label>
            </c:if>
            <c:if test="${subject=='英语'}">
              <label>所属分类：</label>
            </c:if>
          </div>
          <div class="field">
         	 	<!--condition1  -->
	            <c:if test="${subject!='英语'}">
	               <input type="checkbox" name="gclass" value="三年级">三年级
	               <input type="checkbox" name="gclass" value="四年级">四年级
	               <input type="checkbox" name="gclass" value="五年级">五年级
	               <input type="checkbox" name="gclass" value="六年级">六年级
	               <input type="checkbox" name="gclass" value="小升初">小升初
	               <input type="checkbox" name="gclass" value="初一年级">初一年级
	                <c:if test="${subject=='语文'}">
	               <input type="checkbox" name="gclass" value="古诗">古诗
	               <input type="checkbox" name="gclass" value="阅读">阅读
	               <input type="checkbox" name="gclass" value="写作">写作
	               </c:if>
	            </c:if>
	            <!--condition2  --> 
	             <c:if test="${subject=='英语'}">
	               <select name="gclass" class="w50"  style="height:36px;line-height:34px;"  >
		               <option value="">--请选择分类--</option>
		               <option value="新概念">新概念</option>
		               <option value="流利英语">流利英语</option>
		               <option value="语法">语法</option>
		               <option value="其他">其他</option>
	                </select>
	             </c:if>
            <div class="tips"></div>
       	</div>
       </div>
        <div class="form-group">
          <div class="label">
            <label>所属分类：</label>
          </div>
          <div class="field">
	         <c:if test="${subject=='语文'}">
	           <select name="gclassify"  class="w50"  style="height:36px;line-height:34px;"  >
	               <option value="">--请选择类别--</option>
	               <option value="长期班">长期班</option>
	               <option value="真题分析">真题分析</option>
	               <option value="品古诗">品古诗</option>
	               <option value="阅读专题">阅读专题</option>
	               <option value="写作专题">写作专题</option>
              </select>
	         </c:if>
	          <c:if test="${subject=='数学'}">
	            <select name="gclassify"  class="w50"  style="height:36px;line-height:34px;"  >
	               <option value="">--请选择类别--</option>
		           <option value="专题课">专题课</option>
	              <option value="长期班">长期班</option>
	              <option value="入学考">入学考</option>
	              <option value="分班考">分班考</option>
	            </select>
	          </c:if>
	          <c:if test="${subject=='科学'}">
	            <select name="gclassify"  class="w50"  style="height:36px;line-height:34px;"  >
	               <option value="">--请选择类别--</option>
		           <option value="专题课">专题课</option>
	              <option value="长期班">长期班</option>
	              <option value="衔接班">衔接班</option>
	              <option value="入学考">入学考</option>
	              <option value="分班考">分班考</option>
	            </select>
	          </c:if>
	          <c:if test="${subject=='英语'}">
	           <select name="gclassify"  class="w50"  style="height:36px;line-height:34px;"  >
	              <option value="">--请选择类别--</option>
		          <option value="第一册">第一册</option>
	              <option value="第二册">第二册</option>
	              <option value="KET考试">KET考试</option>
	              <option value="PET考试">PET考试</option>
	              <option value="流利英语">流利英语</option>
	              <option value="语法专项">语法专项</option>
	            </select>
	          </c:if>
            <div class="tips"></div>
          </div>
        </div>
        
       <c:if test="${subject=='数学'}">
         <div class="form-group">
	          <div class="label">
	            <label>所属类别：</label>
	          </div>
	          <div class="field">
	                 <select name="gclassify2"  class="w50"  style="height:36px;line-height:34px;" >
		                  <option value="">--请选择类别--</option>
			              <option value="春季班">春季班</option>
			              <option value="暑假班">暑假班</option>
			              <option value="秋季班">秋季班</option>
			              <option value="寒假班">寒假班</option>
			              
			              <option value="计算">计算</option>
			              <option value="数论">数论</option>
			              <option value="几何">几何</option>
			              <option value="计数">计数</option>
			              <option value="应用题">应用题</option>
			              <option value="方程">方程</option>
			              <option value="行程">行程</option>
		            </select>
	            <div class="tips"></div>
	          </div>
        </div>
      </c:if>
      
      
        <div class="form-group">
         <div class="label">
           <label>主讲教师：</label>
         </div>
         <div class="field">
           <select name="teacherName"  class="w50"  style="height:36px;line-height:34px;" >
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
            <label>定时更新时间：</label>
          </div>
          <div class="field">
            <select id="week"  class="w50"  style="height:36px;line-height:34px;width:140px;" >
              <option value="">--请选择星期--</option>
              <option value="星期一">每周一</option>
              <option value="星期二">每周二</option>
              <option value="星期三">每周三</option>
              <option value="星期四">每周四</option>
              <option value="星期五">每周五</option>
              <option value="星期六">每周六</option>
              <option value="星期日">每周日</option>
            </select>
            <select id="timeNum"  class="w50"  style="height:36px;line-height:34px;width:140px;" >
              <option value="">--请选择时间--</option>
              <c:forEach begin="0" end="9" var="ss">
               <option value="0${ss}">0${ss}:00</option>
              </c:forEach>
              <c:forEach begin="10" end="23" var="ss">
                <option value="${ss}">${ss}:00</option>
              </c:forEach>
            </select>
            <input name="weekVal" id="weekVal" type="hidden">
            <div class="tips"></div>
          </div>
        </div>
          <div class="form-group">
          <div class="label">
            <label>是否为VIP视频：</label>
          </div>
          <div class="field">
            <select name="gisVip"  class="w50"  style="height:36px;line-height:34px;" >
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
            <select name="isKill" class="w50"  style="height:36px;line-height:34px;">
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
          <input type="hidden" value="${subject}" name="gsbuject">
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
function video(i){
	//上传视频
		 $('.firminfo-video-input'+i).find("input").click();
		$('.firminfo-video-input'+i).find("input").on("change",function(){
			var objUrl = getObjectURL(this.files[0]) ;  //获取图片的路径，该路径不是图片在本地的路径
		       if (objUrl) {
		    	   $('.firminfo-video-input'+i).find("video").attr("src", objUrl) ;      //将图片路径存入src中，显示出图片
		         if ($('.firminfo-video-input'+i).find("video").attr("src") != "") {
			     	$('.firminfo-video-input'+i).find("video").show();
			     }else{
			     	$(".video-file"+i).show();
			     }
		       }
		}); 
	
}
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
function sub(){
	var flag=false;
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 var flag2 = true ;
	 $("input[name='files2']").each(function(index,element){
		 if($(element).val() == ""){
			flag2 = false ;
		 	return false ;
		 }
	 })
	 
	 if($("#f").val().length==0){
		 alert()
		 sub3();
	 }else if(!flag2){//说明 视频文件 未上传,不通过
		 alert("视频文件未上传,不能提交")
		 sub3();
	 } else{
		 flag=true;
	 }
	 if(flag){
		 var week=$("#week").val();
		 var timeNum=$("#timeNum").val();
		 if(week.length>0&&timeNum.length>0){
			  $("#weekVal").val(week+"-"+timeNum);
		 }else{
			 $("#weekVal").val("");
		 }
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