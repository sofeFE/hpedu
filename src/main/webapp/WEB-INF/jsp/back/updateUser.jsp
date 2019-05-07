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

<link rel="stylesheet" href="${pageContext.request.contextPath}/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ztree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script> 
</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>审核学生家长</strong></div>
  <div class="body-content">
    <form method="post" class="form-x"  action="${pageContext.request.contextPath}/back/updateUserClass" onsubmit="return checkSub()" >  
      <div style="float:left;margin-right:20px;" class="div_left">
      <div class="form-group">
        <input type="hidden" value="${user.uid}" name="uid">
        <div class="label" style="width:120px;">
          <label>家长昵称：</label>
        </div>
        <div class="field" style="width:250px;">
          <input type="text"style="width:250px;" class="input w50" value="${user.userName}"  disabled="disabled" />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
          <div class="label" style="width:120px;">
            <label>家长手机号：</label>
          </div>
          <div class="field" style="width:250px;">
             <input type="text" style="width:250px;" class="input w50" value="${user.phoneNo}"  disabled="disabled" />
             <input type="hidden" value="${user.phoneNo}" name="phoneNo" />
            <div class="tips"></div>
          </div>
        </div>
      <c:if test="${not empty user.headImgUrl}">
	       <div class="form-group">
	        <div class="label" style="width:120px;">
	          <label>家长头像：</label>
	        </div>
	        <div class="field" style="width:250px;">
	          <img src="${user.headImgUrl}" style="width:50px;height:50px;">
	          <div class="tips"></div>
	        </div>
	      </div>
      </c:if>
       <div class="form-group">
          <div class="label" style="width:120px;">
            <label>当前审核时间：</label>
          </div>
          <div class="field" style="width:250px;">
             <input type="text" style="width:250px;" class="input w50"  id="shTime"  />
            <div class="tips"></div>
          </div>
        </div>
       <div class="form-group">
          <div class="label" style="width:120px;">
            <label>是否设为VIP用户：</label>
          </div>
          <div class="field" style="width:250px;">
              <select name="isVip"  class="w50" style="height:36px;line-height:34px;width:250px;" id="isVip">
              <option value="0" <c:if test="${user.isVip=='0' }">selected="selected"</c:if>>否</option>
              <option value="1" <c:if test="${user.isVip=='1' }">selected="selected"</c:if>>是</option>
            </select>
            <div class="tips"></div>
          </div>
       </div>
        <div class="form-group">
          <div class="label" style="width:120px;">
            <label>VIP类型：</label>
          </div>
          <div class="field" style="width:250px;">
            <select name="freeType" class="w50" style="height:36px;line-height:34px;width:250px;"  id="freeType" onchange="changeVip()">
              <option value="">--请选择VIP类型--</option>
              <option value="1月" <c:if test="${user.freeType=='1月' }">selected="selected"</c:if>>1月</option>
              <option value="6月" <c:if test="${user.freeType=='6月' }">selected="selected"</c:if>>6月</option>
              <option value="1年" <c:if test="${user.freeType=='1年' }">selected="selected"</c:if>>1年</option>
            </select>
            <div class="tips"></div>
          </div>
        </div>
        <div class="form-group">
          <div class="label" style="width:120px;">
            <label>VIP到期时间：</label>
          </div>
          <div class="field" style="width:250px;">
             <input type="text" style="width:250px;" class="input w50" value="${user.endTime}" name="endTime2" id="endTime" />
            <div class="tips"></div>
          </div>
        </div>
        <div class="form-group">
          <div class="label" style="width:120px;">
            <label>是否发短信提醒：</label>
          </div>
          <div class="field" style="width:250px;">
            <input type="radio" name="isSend" value="1" checked="checked">是
	        <input type="radio" name="isSend" value="0" >否
            <div class="tips"></div>
          </div>
       </div>
      <div class="clear"></div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
         <input type="hidden" value="${userType}" name="userType">
          <button class="button bg-main icon-check-square-o" id="subbtn">提交审核</button>
        </div>
      </div>
      </div>
       <div style="float:left;">
        <div class="form-group">
        <div class="label" style="width:120px;">
          <label>vip授权内容：<br/><a href="javascript:void(0);" id="selectAll">全选</a></label>
        </div>
        <div class="field" style="width:250px;">
            <ul id="treeDemo" class="ztree"></ul>
            <input type="hidden" name="rightContent" id="rightContent" value="${user.rightContent}" >
          <div class="tips"></div>
        </div>
        </div>
       </div>
    </form>
  </div>
</div>
<script type="text/javascript">
var checked = false ;
$(function(){
	 var shError="${shError}";
		if(shError.length>0&&shError!="null"){
			alert(shError);
		}
		var ddate=new Date();
		var year=ddate.getFullYear();
		var month=ddate.getMonth()+1;
		var day=ddate.getDate();
		$("#shTime").val(year+"-"+month+"-"+day);
		changeVip();
		
		
		//全部选择方法 -trouble
		
		/* $("#selectAll").on("click",function(){
			checked = !checked ;
			treeObj.checkAllNodes(checked);//为true 时 全部勾选, 为false 时, 取消全部勾选.
			if(checked)  $("#selectAll").text("全选");
			else   $("#selectAll").text("全取消");
		});	 */
		
		$("#selectAll").live("click",function(){
			checked = !checked ;
			treeObj.checkAllNodes(checked);//为true 时 全部勾选, 为false 时, 取消全部勾选.
			if(checked)  $("#selectAll").text("全取消");
			else   $("#selectAll").text("全选");
		});
});

var error="${error}";
if(error.length>0){
	alert(error);
}
function checkSub(){
	var flag=false;
	sub();
	var isVip=$("#isVip option:selected").val();
	if(isVip==0){//不是vip用户
		   flag=true;
	}else{
		var freeType=$("#freeType option:selected").val();
		var endTime=$("#endTime").val();
		 if(freeType.length==0||endTime.length==0){
			 alert("请选择VIP类型或者填写VIP到期时间！");
		 }else{
			 //判断日期格式
			 var zhengZe=/^[0-9]{4}-[0-1]?[0-9]{1}-[0-3]?[0-9]{1}$/;
		      if(!zhengZe.test(endTime)){
		          alert("VIP到期时间不符合日期格式：xxxx-xx-xx");
		      }else{
		    	  flag=true;
		      }
		 }
	}
	if(!flag){
		sub2();
	}
	if(flag){//设置权限内容
		var checkNodes = treeObj.getCheckedNodes(true);
		 var rightArr=[];
		 for(var i in checkNodes){
			 rightArr.push(checkNodes[i].realVal);
		} 
		 $("#rightContent").val(rightArr.join("##"));
	}
	return flag;
}
function sub(){
	 $("#subbtn").html("审核提交中...");
	 $("#subbtn").attr("disabled","disabled");
	 $("#subbtn").css("background","gray");
}
function sub2(){
	 $("#subbtn").html("提交审核");
	 $("#subbtn").removeAttr("disabled");
	 $("#subbtn").css("background","#08bbe1");
}
function changeVip(){
	var val=$("#freeType").val();//vip类型
	if(val.length==0){
		 $("#endTime").val("");
		return;
	}
	var regTime=$("#shTime").val();//审核时间
	var dateArr=regTime.split("-");
	//计算到期时间
	var regDate = new Date(dateArr[0],dateArr[1]-1,dateArr[2]);
	var baseKey=1;
	if(val!="1月"){
		baseKey=val=="1年"?12:6;
	}
	 // 先获取当前时间
    var curDate = regDate.getTime();
    // 将1月的时间单位换算成毫秒
    var allMonth = 365 / 12 * 24 * 3600 * 1000*baseKey;
    var pastResult = curDate+ allMonth;  // 半年前的时间（毫秒单位）
 
    // 日期函数，定义起点为半年前
    var pastDate = new Date(pastResult),
        pastYear = pastDate.getFullYear(),
        pastMonth = pastDate.getMonth() + 1,
        pastDay = pastDate.getDate();
	  var endTime=pastYear + '-' + pastMonth + '-' + pastDay;
	   $("#endTime").val(endTime);
}


 var setting = {
		check: {
			enable: true,
			chkboxType:{Y: "ps",N: "s" }
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		/* callback: {
	        onAsyncSuccess: zTreeOnAsyncSuccess  
	    } */  
};
 var firstAsyncSuccessFlag = 0;
 function zTreeOnAsyncSuccess(event, treeId, msg) {  
	if (firstAsyncSuccessFlag == 0) {  
	     try {  
	    	 
	      } catch (err) {  
	         
	      }  
               
  	}
}
	
 
 //授权内容树 
   var treeObj;
	$(document).ready(function(){
		var menus_json='${menus}';
		$.fn.zTree.init($("#treeDemo"), setting,eval(menus_json));
		treeObj = $.fn.zTree.getZTreeObj("treeDemo");
		//勾选权限内容并展开
		var rightContent=$("#rightContent").val();
	    if(rightContent.length>0){
	    	var AllcheckNodes = treeObj.getNodesByParam("nocheck", "0", null);
			for(var i in AllcheckNodes){
				if(rightContent.indexOf(AllcheckNodes[i].realVal)>-1){
					 var node=AllcheckNodes[i];
					 treeObj.checkNode(node, true, false);
					 treeObj.expandNode(node.getParentNode(), true, false, true);
				}
			}
	    }	
	}); 
</script>
</body>
</html>