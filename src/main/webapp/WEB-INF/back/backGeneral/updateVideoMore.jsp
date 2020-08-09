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
				afterBlur:function(){
					this.sync();
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
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>修改常规${generalVideo.gsbuject}专题课程</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" onsubmit="return sub()" enctype="multipart/form-data" 
    action="${pageContext.request.contextPath}/back/updateVideoMore" >  
      <div class="form-group">
      <input type="hidden" value="${generalVideo.gid}" name="gid">
        <div class="label">
          <label>课程名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${generalVideo.gname}" name="gname"  />
          <div class="tips"></div>
        </div>
      </div>
 <%--封面! --%>      
       <div class="form-group">
        <div class="label">
          <label>封面：</label>
        </div>
        <div class="field">
        <c:if test="${ not empty generalVideo.gvimg}">
           <img id="preview" alt="" name="pic" src="${generalVideo.gvimg}" style="width: 150px;height: 120px;" >
        </c:if>
          <input type="file"  id="f"  name="file1" onchange="change('封面图片','f','preview')" >
        </div>
       </div> 
      
      
 <%--添加pdf --%>     
       <div class="form-group">
        <div class="label">
          <label>PDF：</label>
        </div>
       <div class="field" id="pdf_tab">
          <input type="button" class="button bg-main icon-check-square-o" value="添加讲义" onclick="addPdf('pdf_tab')" >
           <c:if test="${generalVideo.pdflist!=null}">
           <c:forEach items="${generalVideo.pdflist}" var="vc" varStatus="ss">
          <table style="margin:5px;" id="pdf_tab${ss.index}">
		      <tr><td style="width:300px;"><span>${vc.pdfUrl}</span><%-- <input type="file" id="file_pdf${ss.index}"  name="file1" onchange="change('','file_pdf${ss.index}','',1)" > --%></td>
		      <td><input type="button" class="button bg-main icon-check-square-o" onclick="delPDF(${ss.index},'${vc.pdfid}')" value="删除" ></td></tr>
		  </table> 
		   </c:forEach>
		   </c:if>
        </div>
      </div> 
      
 <%--添加视频 --%>     
      <div class="form-group">
        <div class="label">
          <label>专题视频：</label>
        </div>
       <div class="field" id="video_tab">
         <input type="button" class="button bg-main icon-check-square-o" value="添加新视频" onclick="addVideo('video_tab')" >
         <table style="border:1px solid #22CC77;margin:5px;">
         	<tr>
	         	<th style="width:200px;">视频名字</th>
	         	<th style="width:200px;">视频顺序</th>
	         	<th style="width:125px;">视频缩略图</th>
	         	<th style="width:90px;">操作</th>
         	</tr>
         </table>
         <c:forEach items="${generalVideo.vclist}" var="vc" varStatus="ss">
	         <table style="border:1px solid #22CC77;margin:5px;" id="video_tab${ss.index}">
		      <tr >
			      <td style="width:200px;"><!-- 视频名称 -->
			      	<input type="text" style="width:200px;height:40px;margin:5px;" onchange="changeVcname('${vc.vcid}',this.value)"  value="${vc.vcname}" >
			      </td>
			      <td style="width:200px;"><!-- 视频顺序 -->
			      	<input type="number" style="width:200px;height:40px;margin:5px;" onchange="changeSort('${vc.vcid}',this.value)"  value="${vc.sort}" >
			      </td>
			      <td valign="bottom" style="width:125px;"><!-- 视频封面 -->
			      	<c:if test="${not empty vc.cImgUrl}">
			      		<img style="width:125px;" src="${vc.cImgUrl}" >
			      	</c:if>
			      	<c:if test="${ empty vc.cImgUrl}">
			      		<span>暂无缩略图!</span>
			      	</c:if>
			      </td>
			      <td style="width:90px;">
			      	<input type="button" class="button bg-main icon-check-square-o" onclick="delVideo(${ss.index},'${vc.vcid}')" value="删除" >
			      </td>
		      </tr>
	         </table> 
         </c:forEach>
        </div>
      </div>     
 <%-- 其他设置--%>     
        <div class="form-group">
          <div class="label">
            <c:if test="${generalVideo.gsbuject!='英语'}">
              <label>所属年级：</label>
            </c:if>
            <c:if test="${generalVideo.gsbuject=='英语'}">
              <label>所属分类：</label>
            </c:if>
          </div>
          <div class="field">
          <!-- 情况1 -->
           <c:if test="${generalVideo.gsbuject!='英语'}">
              <input type="checkbox" name="gclass" value="三年级"  <c:if test="${fn:contains(generalVideo.gclass,'三年级') }">checked="checked"</c:if>>三年级
              <input type="checkbox" name="gclass" value="四年级" <c:if test="${fn:contains(generalVideo.gclass,'四年级') }">checked="checked"</c:if>>四年级
              <input type="checkbox" name="gclass" value="五年级" <c:if test="${fn:contains(generalVideo.gclass,'五年级') }">checked="checked"</c:if>>五年级
              <input type="checkbox" name="gclass" value="六年级" <c:if test="${fn:contains(generalVideo.gclass,'六年级')  }">checked="checked"</c:if>>六年级
              <input type="checkbox" name="gclass" value="小升初" <c:if test="${fn:contains(generalVideo.gclass,'小升初')  }">checked="checked"</c:if>>小升初
              <input type="checkbox" name="gclass" value="初一年级" <c:if test="${fn:contains(generalVideo.gclass,'初一年级')  }">checked="checked"</c:if>>初一年级
	            <c:if test="${generalVideo.gsbuject=='语文'}">
	              <input type="checkbox" name="gclass" value="古诗" <c:if test="${fn:contains(generalVideo.gclass,'古诗')  }">checked="checked"</c:if>>古诗
	              <input type="checkbox" name="gclass" value="阅读" <c:if test="${fn:contains(generalVideo.gclass,'阅读')  }">checked="checked"</c:if>>阅读
	              <input type="checkbox" name="gclass" value="写作" <c:if test="${fn:contains(generalVideo.gclass,'写作')  }">checked="checked"</c:if>>写作
	           	</c:if>
	       </c:if> 
	         <!-- 情况2 -->
	        <c:if test="${generalVideo.gsbuject=='英语'}">
	         <select name="gclass" class="w50"  style="height:36px;line-height:34px;" >
	               <option value="">--请选择分类--</option>
	               <option value="新概念" <c:if test="${generalVideo.gclass=='新概念' }">selected="selected"</c:if>>新概念</option>
	               <option value="流利英语" <c:if test="${generalVideo.gclass=='流利英语' }">selected="selected"</c:if>>流利英语</option>
	               <option value="语法" <c:if test="${generalVideo.gclass=='语法' }">selected="selected"</c:if>>语法</option>
	               <option value="其他" <c:if test="${generalVideo.gclass=='其他' }">selected="selected"</c:if>>其他</option>
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
         	 <!-- condition1 -->
	         <c:if test="${generalVideo.gsbuject=='语文'}">
	           <select name="gclassify" class="w50"  style="height:36px;line-height:34px;" >
	               <option value="">--请选择类别--</option>
	               <option value="阅读专题" <c:if test="${generalVideo.gclassify=='阅读专题' }">selected="selected"</c:if>>阅读专题</option>
	               <option value="写作专题" <c:if test="${generalVideo.gclassify=='写作专题' }">selected="selected"</c:if>>写作专题</option>
	               <option value="长期班" <c:if test="${generalVideo.gclassify=='长期班' }">selected="selected"</c:if>>长期班</option>
	               <option value="真题分析" <c:if test="${generalVideo.gclassify=='真题分析' }">selected="selected"</c:if>>真题分析</option>
	               <option value="品古诗" <c:if test="${generalVideo.gclassify=='品古诗' }">selected="selected"</c:if>>品古诗</option>
              </select>
	         </c:if>
	         
	         <!-- condition2 -->
	          <c:if test="${generalVideo.gsbuject=='数学'}">
	            <select name="gclassify" class="w50"  style="height:36px;line-height:34px;" >
	               <option value="">--请选择类别--</option>
		           <option value="专题课" <c:if test="${generalVideo.gclassify=='专题课' }">selected="selected"</c:if>>专题课</option>
	              <option value="长期班" <c:if test="${generalVideo.gclassify=='长期班' }">selected="selected"</c:if>>长期班</option>
	              <option value="入学考" <c:if test="${generalVideo.gclassify=='入学考' }">selected="selected"</c:if>>入学考</option>
	              <option value="分班考" <c:if test="${generalVideo.gclassify=='分班考' }">selected="selected"</c:if>>分班考</option>
	            </select>
	          </c:if>
	          
	         <!-- condition3 -->
	          <c:if test="${generalVideo.gsbuject=='科学'}">
	            <select name="gclassify" class="w50"  style="height:36px;line-height:34px;" >
	               <option value="">--请选择类别--</option>
		           <option value="专题课" <c:if test="${generalVideo.gclassify=='专题课' }">selected="selected"</c:if>>专题课</option>
	              <option value="长期班" <c:if test="${generalVideo.gclassify=='长期班' }">selected="selected"</c:if>>长期班</option>
	              <option value="衔接班" <c:if test="${generalVideo.gclassify=='衔接班' }">selected="selected"</c:if>>衔接班</option>
	              <option value="入学考" <c:if test="${generalVideo.gclassify=='入学考' }">selected="selected"</c:if>>入学考</option>
	              <option value="分班考" <c:if test="${generalVideo.gclassify=='分班考' }">selected="selected"</c:if>>分班考</option>
	            </select>
	          </c:if>
	          
	          <!-- condition4 -->
	          <c:if test="${generalVideo.gsbuject=='英语'}">
	           <select name="gclassify" class="w50"  style="height:36px;line-height:34px;">
	              <option value="">--请选择类别--</option>
		          <option value="第一册" <c:if test="${generalVideo.gclassify=='第一册' }">selected="selected"</c:if>>第一册</option>
	              <option value="第二册" <c:if test="${generalVideo.gclassify=='第二册' }">selected="selected"</c:if>>第二册</option>
	              <option value="KET考试" <c:if test="${generalVideo.gclassify=='KET考试' }">selected="selected"</c:if>>KET考试</option>
	              <option value="PET考试" <c:if test="${generalVideo.gclassify=='PET考试' }">selected="selected"</c:if>>PET考试</option>
	              <option value="流利英语" <c:if test="${generalVideo.gclassify=='流利英语' }">selected="selected"</c:if>>流利英语</option>
	              <option value="语法专项" <c:if test="${generalVideo.gclassify=='语法专项' }">selected="selected"</c:if>>语法专项</option>
	            </select>
	          </c:if>
            <div class="tips"></div>
          </div>
        </div>
       <!-- condition5 --> 
       <c:if test="${generalVideo.gsbuject=='数学'}">
         <div class="form-group">
          <div class="label">
            <label>所属类别：</label>
          </div>
          <div class="field">
                 <select name="gclassify2" class="w50"  style="height:36px;line-height:34px;" >
	                  <option value="">--请选择类别--</option>
		              <option value="春季班" <c:if test="${generalVideo.gclassify2=='春季班' }">selected="selected"</c:if>>春季班</option>
		              <option value="暑假班" <c:if test="${generalVideo.gclassify2=='暑假班' }">selected="selected"</c:if>>暑假班</option>
		              <option value="秋季班" <c:if test="${generalVideo.gclassify2=='秋季班' }">selected="selected"</c:if>>秋季班</option>
		              <option value="寒假班" <c:if test="${generalVideo.gclassify2=='寒假班' }">selected="selected"</c:if>>寒假班</option>
		              
		              <option value="计算" <c:if test="${generalVideo.gclassify2=='计算' }">selected="selected"</c:if>>计算</option>
		              <option value="数论" <c:if test="${generalVideo.gclassify2=='数论' }">selected="selected"</c:if>>数论</option>
		              <option value="几何" <c:if test="${generalVideo.gclassify2=='几何' }">selected="selected"</c:if>>几何</option>
		              <option value="计数" <c:if test="${generalVideo.gclassify2=='计数' }">selected="selected"</c:if>>计数</option>
		              <option value="应用题" <c:if test="${generalVideo.gclassify2=='应用题' }">selected="selected"</c:if>>应用题</option>
		              <option value="方程" <c:if test="${generalVideo.gclassify2=='方程' }">selected="selected"</c:if>>方程</option>
		              <option value="行程" <c:if test="${generalVideo.gclassify2=='行程' }">selected="selected"</c:if>>行程</option>
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
            <select name="teacherName" class="w50"  style="height:36px;line-height:34px;">
              <option value="">--请选择主讲教师--</option>
              <c:forEach items="${tclist}" var="t">
              <option value="${t.tname}" <c:if test="${generalVideo.teacherName==t.tname}">selected="selected"</c:if> >${t.tname}</option>
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
            <select id="week" class="w50"  style="height:36px;line-height:34px;width:140px;">
              <option value="">--请选择星期--</option>
              <option value="星期一" <c:if test="${week=='星期一'}">selected="selected"</c:if>>每周一</option>
              <option value="星期二" <c:if test="${week=='星期二'}">selected="selected"</c:if>>每周二</option>
              <option value="星期三" <c:if test="${week=='星期三'}">selected="selected"</c:if>>每周三</option>
              <option value="星期四" <c:if test="${week=='星期四'}">selected="selected"</c:if>>每周四</option>
              <option value="星期五" <c:if test="${week=='星期五'}">selected="selected"</c:if>>每周五</option>
              <option value="星期六" <c:if test="${week=='星期六'}">selected="selected"</c:if>>每周六</option>
              <option value="星期日" <c:if test="${week=='星期日'}">selected="selected"</c:if>>每周日</option>
            </select>
            <select id="timeNum"  class="w50"  style="height:36px;line-height:34px;width:140px;">
              <option value="">--请选择时间--</option>
              <c:forEach begin="0" end="9" var="ss">
               <option value="0${ss}" <c:if test="${timeNum==ss}">selected="selected"</c:if>>0${ss}:00</option>
              </c:forEach>
              <c:forEach begin="10" end="23" var="ss">
                <option value="${ss}" <c:if test="${timeNum==ss}">selected="selected"</c:if>>${ss}:00</option>
              </c:forEach>
            </select>
            <input name="weekVal" id="weekVal" type="hidden" value="${generalVideo.weekVal}">
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
              <option value="0" <c:if test="${generalVideo.gisVip=='0' }">selected="selected"</c:if>>是</option>
              <option value="1" <c:if test="${generalVideo.gisVip=='1' }">selected="selected"</c:if>>否</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
          <div class="form-group">
        <div class="label">
          <label>专柜价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${generalVideo.gmoney}" name="gmoney" placeholder="如果免费则不需要填写该项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀价格：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${generalVideo.killMoney}" name="killMoney" placeholder="如果免费则不需要填写该项" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀活动名称：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${generalVideo.killName}" name="killName" placeholder="如：双十一特惠活动" />
          <div class="tips"></div>
        </div>
      </div>
     <div class="form-group">
        <div class="label">
          <label>秒杀时间起：</label>
        </div>
        <div class="field">
          <input class="Wdate input w50" style="height:40px;width:240px;"  value="${generalVideo.killStartTime}" name="killStartTime" placeholder="例如：2017-05-08 11:50:20" onclick="WdatePicker()" type="text" >
         <%--  <input type="text" class="input w50" value="${generalVideo.killStartTime}" name="killStartTime" placeholder="例如：2017-05-08 11:50:20" /> --%>
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>秒杀时间止：</label>
        </div>
        <div class="field">
          <input class="Wdate input w50" style="height:40px;width:240px;" value="${generalVideo.killEndTime}" name="killEndTime" placeholder="例如：2017-05-08 11:50:20" onclick="WdatePicker()" type="text" >
         <%--  <input type="text" class="input w50" value="${generalVideo.killEndTime}" name="killEndTime" placeholder="例如：2017-05-08 11:50:20" /> --%>
          <div class="tips"></div>
        </div>
      </div>

       <div class="form-group">
          <div class="label">
            <label>是否执行秒杀：</label>
          </div>
          <div class="field">
            <select name="isKill"  class="w50"  style="height:36px;line-height:34px;">
              <option value="">--请选择是否执行秒杀活动--</option>
              <option value="1" <c:if test="${generalVideo.isKill=='1' }">selected="selected"</c:if>>是</option>
              <option value="0" <c:if test="${generalVideo.isKill!='1' }">selected="selected"</c:if>>否</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
      <div class="form-group">
        <div class="label">
          <label>描述：</label>
        </div>
        <div class="field">
          <textarea class="input" name="gintro" cols="100" rows="15" style="width:800px;height:300px;visibility:hidden;">${generalVideo.gintro}</textarea>
          <div class="tips"></div>
        </div>
      </div>
      
      
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
           <input type="hidden" value="${generalVideo.gsbuject}" name="gsbuject">
          <button class="button bg-main icon-check-square-o" id="subbtn"> 提交</button>
        </div>
        <div class="field" id="del_div">
           
        </div>
        <div class="field" id="del_div_pdf">
           
        </div>
        <div class="field" id="changeVcname_div">
           
        </div>
        <div class="field" id="changeSort_div">
           
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
var video_index="${generalVideo.vclist.size()}";
function addVideo(id){
	/* var a = '/"视频图片/"' ;
	var b = '/"vimg/"+video_index' ;
	var c = '/"preview/"' ; */
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
                +'<td style="width:300px;">'
                +'<input type="text" style="width:200px;height:40px;margin:5px;" name="vcname"  placeholder="请输入视频集数，例如：第一集" >'
                +'</td>'
                
                +'<td><input type="button" value="删除" class="button bg-main icon-check-square-o" onclick="delVideo('+video_index+')"></td>'
               +'</tr>'
               
               +'<tr><td>视频排序：</td>'
                +'<td><input type="text" style="width:200px;height:40px;margin:5px;" name="vcname"  placeholder="请输入视频顺序:必须是整数字" ></td>'
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

//删除视频
function delVideo(ii,id){
	if(id){
		$("#del_div").append("<input type='hidden' name='delVcid' value='"+id+"'>");
	}
	  $("#video_tab"+ii).remove(); 
}

//pdf文件
var pdf_index="${generalVideo.pdflist!=null?generalVideo.pdflist.size():0}";
function addPdf(id){
	  var html='<table style="margin:5px;" id="pdf_tab'+pdf_index+'">'+
	      '<tr><td style="width:300px;"><input type="file" id="file_pdf'+pdf_index+'"  name="file1" onchange="change(\'\',\'file_pdf'+pdf_index+'\',\'\',1)" ></td>'+
	      '<td><input type="button" class="button bg-main icon-check-square-o" onclick="delPDF('+pdf_index+')" value="删除" ></td></tr></table>';
    $("#"+id).append(html);  
     ++pdf_index;
}
function status_uploading(obj) {
    $(obj).val("上传中...");
    $(obj).attr("disabled","disabled");
    $(obj).css("background","gray");
}
function status_waitUpload(obj){
    $(obj).val("上传");
    $(obj).removeAttr("disabled");
    $(obj).css("background","#08bbe1");
}

//修改文件id
	function updateCustomerInfo(This,ii){
		if($("#videoUp"+ii).val()==""){
			alert("请选择上传的文件！");
			return false;
		}
		status_uploading(This) ;
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
	           //alert(data.status);
	        	if(data.status=="ok"){
	        		//将视频 路径 填写到 隐藏文本框
	        		$("#hide_file"+ii).val(data.vcid); 
	        		alert("上传成功");
	        	}else{
	        		alert("视频上传失败:"+data.status);
	        	}
	        },
            timeout:10000,
            error :function(){
	            alert("上传异常");
            },
            complete : function(){
                status_waitUpload(This) ;
            }
	    });  
		
	}
  
function delPDF(ii,id){
	if(id){
		$("#del_div_pdf").append("<input type='hidden' name='delpdfid' value='"+id+"'>");
	}
	  $("#pdf_tab"+ii).remove();
}

function video(i){
	//上传视频
	$('.firminfo-video-input'+i).find("#videoUp"+i).click();
	$('.firminfo-video-input'+i).find("#videoUp"+i).on("change",function(){
		var objUrl = getObjectURL(this.files[0]) ;  //获取图片的路径，该路径不是图片在本地的路径
	       if (objUrl) {
	    	   $('.firminfo-video-input'+i).find("video").attr("src", objUrl) ;      //将图片路径存入src中，显示出图片
	    	   
	         if ($('.firminfo-video-input'+i).find("video").attr("src") !== "") {
		     	$('.firminfo-video-input'+i).find("video").show();
		     }else{
		     	$(".video-file"+i).show();
		     }
	       }
	}); 
}


//建立一個可存取到該fileUrl
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

//防止重复提交
function sub(){
	
	 $("#subbtn").html("提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
	 var week=$("#week").val();
	 var timeNum=$("#timeNum").val();
	 if(week.length>0&&timeNum.length>0){
		  $("#weekVal").val(week+"-"+timeNum);
	 }else{
		 $("#weekVal").val("");
	 }
	 return true;
}
//修改视频名称
function changeVcname(vcid,val){
	if(!$("#change_vcid_"+vcid).val()){
		$("#changeVcname_div").append("<input name='change_vcid' type='hidden' id='change_vcid_"+vcid+"' value='"+vcid+"'>");
		$("#changeVcname_div").append("<input name='change_vcname' type='hidden' id='change_vcname_"+vcid+"' value='"+val+"'>");
	}else{
		$("#change_vcname_"+vcid).val(val);
	}
}
//仿照修改视频 方法, 添加 修改 顺序方法 
function changeSort(vcid,val){
	if(!$("#change_id_"+vcid).val()){
		$("#changeSort_div").append("<input name='change_id' type='hidden' id='change_id_"+vcid+"' value='"+vcid+"'>");
		$("#changeSort_div").append("<input name='change_sort' type='hidden' id='change_sort_"+vcid+"' value='"+val+"'>");
	}else{
		$("#change_sort_"+vcid).val(val);
	}
}



/* $(function(){
	$(".icon-check-square-o").on("click",function(){
		$(".form-x").submit();
	})
}) */

</script>
</body><script type="text/javascript" src="js/class.js?v=2"></script></html>